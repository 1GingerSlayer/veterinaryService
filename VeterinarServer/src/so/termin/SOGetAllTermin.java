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
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOGetAllTermin extends AbstractSO {

    private ArrayList<Termin> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // prvo vracam sve termine (bez stavki termina jer je lista stavki trenutno null)
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Termin>) (ArrayList<?>) termini;

        // foreach za da proverim svaki termin
        for (Termin trenutniTermin : lista) {

            StavkaTermina stavkaTermina = new StavkaTermina(); //pravim praznu stavku
            stavkaTermina.setTermin(trenutniTermin); //onda za tu stavku zakacim trenutni termin da bih u dbbrokeru mogla da uradim uslov za select (dodatniUslov)
            //ovako u dbbrokeru mogu da pretrazujem pomocu terminID i da mi vrati i stavku koja ima taj terminID

            //sad vracam sve stavke koje sam vezala za termin
            ArrayList<StavkaTermina> stavkeTrenutnogTermina
                    = (ArrayList<StavkaTermina>) (ArrayList<?>) DBBroker
                            .getInstance().select(stavkaTermina);

            //setujem za trenutni termin celu listu stavki (imace isti terminID)
            trenutniTermin.setStavkeTermina(stavkeTrenutnogTermina);
        }

    }

    public ArrayList<Termin> getLista() {
        return lista;
    }

}
