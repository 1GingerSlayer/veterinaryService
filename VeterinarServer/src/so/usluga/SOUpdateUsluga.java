/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Usluga;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOUpdateUsluga extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }

        Usluga u = (Usluga) ado;

        if (u.getTrajanjeMin() < 5 || u.getTrajanjeMin() > 180) {
            throw new Exception("Trajanje mora biti izmedju 5 i 180 minuta!");
        }

        if (u.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od 0!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
