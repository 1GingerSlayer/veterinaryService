/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Veterinar;
import domain.KategorijaKlijenta;
import domain.Klijent;
import domain.Usluga;
import domain.Termin;
import java.util.ArrayList;
import so.login.SOLogin;
import so.kategorija_klijenta.SOGetAllKategorijaKlijenta;
import so.kategorija_klijenta.SOAddKategorijaKlijenta;
import so.kategorija_klijenta.SODeleteKategorijaKlijenta;
import so.kategorija_klijenta.SOUpdateKategorijaKlijenta;
import so.klijent.SOAddKlijent;
import so.klijent.SODeleteKlijent;
import so.klijent.SOGetAllKlijent;
import so.klijent.SOUpdateKlijent;
import so.termin.SOAddTermin;
import so.termin.SODeleteTermin;
import so.termin.SOGetAllTermin;
import so.termin.SOUpdateTermin;
import so.usluga.SOAddUsluga;
import so.usluga.SODeleteUsluga;
import so.usluga.SOGetAllUsluga;
import so.usluga.SOUpdateUsluga;

import so.veterinar.SOGetAllVeterinar;

/**
 *
 * @author Nina
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Veterinar> ulogovaniVeterinari = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Veterinar> getUlogovaniVeterinari() {
        return ulogovaniVeterinari;
    }

    public void setUlogovaniVeterinari(ArrayList<Veterinar> ulogovaniVeterinari) {
        this.ulogovaniVeterinari = ulogovaniVeterinari;
    }

    public Veterinar login(Veterinar veterinar) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(veterinar);
        return so.getUlogovani();
    }

    public void addKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        (new SOAddKategorijaKlijenta()).templateExecute(kategorijaKlijenta);
    }

    public void deleteKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        (new SODeleteKategorijaKlijenta()).templateExecute(kategorijaKlijenta);
    }

    public void updateKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        (new SOUpdateKategorijaKlijenta()).templateExecute(kategorijaKlijenta);
    }

    public ArrayList<KategorijaKlijenta> getAllKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        SOGetAllKategorijaKlijenta so = new SOGetAllKategorijaKlijenta();
        so.templateExecute(kategorijaKlijenta);
        return so.getLista();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        (new SOAddKlijent()).templateExecute(klijent);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        (new SODeleteKlijent()).templateExecute(klijent);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        (new SOUpdateKlijent()).templateExecute(klijent);
    }

    public ArrayList<Klijent> getAllKlijent(Klijent klijent) throws Exception {
        SOGetAllKlijent so = new SOGetAllKlijent();
        so.templateExecute(klijent);
        return so.getLista();
    }

    public void addUsluga(Usluga usluga) throws Exception {
        (new SOAddUsluga()).templateExecute(usluga);
    }

    public void deleteUsluga(Usluga usluga) throws Exception {
        (new SODeleteUsluga()).templateExecute(usluga);
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        (new SOUpdateUsluga()).templateExecute(usluga);
    }

    public ArrayList<Usluga> getAllUsluga(Usluga usluga) throws Exception {
        SOGetAllUsluga so = new SOGetAllUsluga();
        so.templateExecute(usluga);
        return so.getLista();
    }

    public void addTermin(Termin t) throws Exception {
        (new SOAddTermin()).templateExecute(t);
    }

    public void deleteTermin(Termin t) throws Exception {
        (new SODeleteTermin()).templateExecute(t);
    }

    public void updateTermin(Termin t) throws Exception {
        (new SOUpdateTermin()).templateExecute(t);
    }

    public ArrayList<Termin> getAllTermin(Termin termin) throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        so.templateExecute(termin);
        return so.getLista();
    }

    public ArrayList<Veterinar> getAllVeterinar() throws Exception {
        SOGetAllVeterinar so = new SOGetAllVeterinar();
        so.templateExecute(new Veterinar(-1, "", "", "", "")); //forica, namerno posledjujem -1 jer onda nece da filtrira veterinara po id nego ce uci u else kada radi filter sa like
        return so.getLista();                                   //namerno prosledjujem prazan string jer like '%%' sa praznim stringom vraca sve veterinare (bigbrain)
    }

    public int getBrojTermina(int veterinarID, String tip) throws Exception {
        Termin t = new Termin();                //prazan objekat termin
        t.setFilterVeterinarID(veterinarID);    //u taj termin postavljam id veterinara tj vezem ga za veterinara
        t.setTipBrojanja(tip);                  //ukupno, buduci, danas, ovde ce da mi okine dodatniUslov u terminu (morala sam da dodam dodatan if)
        return getAllTermin(t).size();          //vracam velicinu liste termina za vezanog veterinara (broj termina u toj listi)
    }

    public void logout(Veterinar ulogovani) {
        ulogovaniVeterinari.remove(ulogovani);
    }

}
