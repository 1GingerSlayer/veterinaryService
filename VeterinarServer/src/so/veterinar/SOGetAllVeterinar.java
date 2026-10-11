/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veterinar;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Veterinar;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOGetAllVeterinar extends AbstractSO {

    private ArrayList<Veterinar> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Veterinar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Veterinar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> veterinari = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Veterinar>) (ArrayList<?>) veterinari;
    }

    public ArrayList<Veterinar> getLista() {
        return lista;
    }
}
