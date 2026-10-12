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
public class SOAddKategorijaKlijenta extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KategorijaKlijenta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KategorijaKlijenta!");
        }
        KategorijaKlijenta kk = (KategorijaKlijenta) ado;
        if (kk.getNaziv() == null || kk.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv kategorije klijenta ne sme biti prazan!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
