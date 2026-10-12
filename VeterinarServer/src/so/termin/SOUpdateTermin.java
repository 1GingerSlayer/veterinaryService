/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaTermina;
import domain.Termin;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOUpdateTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        if (!t.getDatumVremePocetka().after(new Date())) {
            throw new Exception("Datum pocetka mora da bude posle danasnjeg dana!");
        }

        if (t.getStavkeTermina().isEmpty()) {
            throw new Exception("Termin mora da ima barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Termin termin = (Termin) ado;

        //azuriram termin
        DBBroker.getInstance().update(termin);

        //radim delete samo za uklonjene, update za zadrzane, insert za nove
        //znaci, kad updateujem neke trenutne ce biti promenjene, neke nece biti dirane uopste, neke ce se pojaviti nove
        // ovde uzimam stare stavke iz baze
        ArrayList<StavkaTermina> stareStavke
                = (ArrayList<StavkaTermina>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaTermina(termin, 0, null, null, 0, 0));

        //radim preko hashmape jer mi nije dovoljno da pamtim stavku samo po terminuID, vise stavki moze da ima isti terminID, moram da sacuvam i rb (slozen pk)
        //hocu da u mapu stavim stavku termina i njen redni broj
        HashMap<Integer, StavkaTermina> mapaStarih = new HashMap<>();
        for (StavkaTermina st : stareStavke) {
            mapaStarih.put(st.getRb(), st);
        }

        //sad pravim hashmapu za nove stavke na isti nacin, cuvam stavku i njen redni broj u mapi
        HashMap<Integer, StavkaTermina> mapaNovih = new HashMap<>();
        for (StavkaTermina nova : termin.getStavkeTermina()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        // ako imam manje stavki u novim nego u starim znaci da je nesto obrisano, pa zato radim delete za rb koji su samo u starim i onda update za ostale
        // ako imam vise stavki u novim nego u starim znaci da je nesto dodato, pa cu za rb koji su samo u novim da uradim insert a za ostale update
        // ako imam isto stavki u starim i novim znaci da nista nije brisano ni dodavano, samo updateujem rb svih
        for (StavkaTermina stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) { // delete (brisem rb koji su samo u starim)
                DBBroker.getInstance().delete(stara);
            }
        }

        for (StavkaTermina nova : termin.getStavkeTermina()) {
            if (mapaStarih.containsKey(nova.getRb())) { // update (azuriram rb koji su i u starim i u novim, tj nista dodato nista obrisano)
                DBBroker.getInstance().update(nova);
            }
        }

        for (StavkaTermina nova : termin.getStavkeTermina()) {
            if (!mapaStarih.containsKey(nova.getRb())) { // insert (dodam rb koji su u novim a nije ih bilo u starim)
                DBBroker.getInstance().insert(nova);
            }
        }

    }

}
