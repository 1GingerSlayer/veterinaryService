/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOGetAllUsluga extends AbstractSO {

    private ArrayList<Usluga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> usluge = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Usluga>) (ArrayList<?>) usluge;
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }

}
