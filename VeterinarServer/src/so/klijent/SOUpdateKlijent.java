/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.KategorijaKlijenta;
import domain.Klijent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import so.AbstractSO;

/**
 *
 * @author Nina
 */
public class SOUpdateKlijent extends AbstractSO {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern TELEFON_PATTERN
            = Pattern.compile("^06[0-9]{8}$");

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }

        Klijent k = (Klijent) ado;

        if (!EMAIL_PATTERN.matcher(k.getEmail()).matches()) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!TELEFON_PATTERN.matcher(k.getTelefon()).matches()) {
            throw new Exception("Telefon mora biti u formatu 06XXXXXXXX!");
        }

        // pre je bilo .select(ado), ali ado ovde ima pravi klijentID pa dodatniUslov() vraca WHERE KLIJENTID = id,
        // tj. samo klijenta koji se menja. for each petlja njega preskace pa se provera duplikata nikad ne izvrsi.
        // sa praznim klijentom dodatniUslov() vraca sve klijente pa petlja proverava email i telefon kod svih ostalih
        ArrayList<Klijent> klijenti = (ArrayList<Klijent>) (ArrayList<?>) DBBroker.getInstance()
                .select(new Klijent(-1, "", "", "", "", new KategorijaKlijenta(-1, "")));

        for (Klijent klijent : klijenti) {
            if (klijent.getKlijentID() != k.getKlijentID()) {
                if (klijent.getEmail().equals(k.getEmail())) {
                    throw new Exception("Klijent sa tim emailom vec postoji!");
                }
                if (klijent.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Klijent sa tim telefonom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
