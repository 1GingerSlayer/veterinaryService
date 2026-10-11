/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Veterinar;
import java.util.ArrayList;
import so.login.SOLogin;
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

    public ArrayList<Veterinar> getAllVeterinar() throws Exception {
        SOGetAllVeterinar so = new SOGetAllVeterinar();
        so.templateExecute(new Veterinar(-1, "", "", "", "")); //forica, namerno posledjujem -1 jer onda nece da filtrira veterinara po id nego ce uci u else kada radi filter sa like
        return so.getLista();                                   //namerno prosledjujem prazan string jer like '%%' sa praznim stringom vraca sve veterinare (bigbrain)
    }

    public void logout(Veterinar ulogovani) {
        ulogovaniVeterinari.remove(ulogovani);
    }

}
