/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Veterinar;
import domain.KategorijaKlijenta;
import domain.Klijent;
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

    public ArrayList<Veterinar> getAllVeterinar() throws Exception {
        SOGetAllVeterinar so = new SOGetAllVeterinar();
        so.templateExecute(new Veterinar(-1, "", "", "", "")); //forica, namerno posledjujem -1 jer onda nece da filtrira veterinara po id nego ce uci u else kada radi filter sa like
        return so.getLista();                                   //namerno prosledjujem prazan string jer like '%%' sa praznim stringom vraca sve veterinare (bigbrain)
    }

    public void logout(Veterinar ulogovani) {
        ulogovaniVeterinari.remove(ulogovani);
    }

}
