/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kategorija_klijenta;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.KategorijaKlijenta;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SODeleteKategorijaKlijenta extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KategorijaKlijenta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KategorijaKlijenta!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
