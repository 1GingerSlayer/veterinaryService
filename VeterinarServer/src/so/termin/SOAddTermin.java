/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaTermina;
import domain.Termin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOAddTermin extends AbstractSO {

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
        // ps mi u dbbrokeru ima statement return generated keys
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int noviTerminID = tableKeys.getInt(1);

        // moram da setujem id novog termina jer ce mi trebati za stavke termina, ako ne setujem ostace mi -1 jer sam tako stavila na main formi u btn sacuvaj
        Termin noviTermin = (Termin) ado;
        noviTermin.setTerminID(noviTerminID);

        //da bih dodala termin moram prvo da dodam sve stavke termina, dodajem jednu po jednu u listu stavki
        for (StavkaTermina stavkaTermina : noviTermin.getStavkeTermina()) {
            stavkaTermina.setTermin(noviTermin); //za svaku stavku u listi setujem id termina koji mi je vratio return generated keys pa sam setovala u noviTermin
            DBBroker.getInstance().insert(stavkaTermina);
        }

    }

}
