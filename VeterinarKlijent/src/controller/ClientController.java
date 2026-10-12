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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Nina
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Veterinar login(Veterinar veterinar) throws Exception {
        return (Veterinar) sendRequest(Operation.LOGIN, veterinar);
    }

    public void logout(Veterinar ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        sendRequest(Operation.ADD_KATEGORIJA_KLIJENTA, kategorijaKlijenta);
    }

    public void deleteKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        sendRequest(Operation.DELETE_KATEGORIJA_KLIJENTA, kategorijaKlijenta);
    }

    public void updateKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        sendRequest(Operation.UPDATE_KATEGORIJA_KLIJENTA, kategorijaKlijenta);
    }

    public ArrayList<KategorijaKlijenta> getAllKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) throws Exception {
        return (ArrayList<KategorijaKlijenta>) sendRequest(Operation.GET_ALL_KATEGORIJA_KLIJENTA, kategorijaKlijenta);
    }

    public void addKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.ADD_KLIJENT, klijent);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.DELETE_KLIJENT, klijent);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.UPDATE_KLIJENT, klijent);
    }

    public ArrayList<Klijent> getAllKlijent(Klijent klijent) throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operation.GET_ALL_KLIJENT, klijent);
    }

    public void addUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.ADD_USLUGA, usluga);
    }

    public void deleteUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.DELETE_USLUGA, usluga);
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.UPDATE_USLUGA, usluga);
    }

    public ArrayList<Usluga> getAllUsluga(Usluga usluga) throws Exception {
        return (ArrayList<Usluga>) sendRequest(Operation.GET_ALL_USLUGA, usluga);
    }

    public void addTermin(Termin t) throws Exception {
        sendRequest(Operation.ADD_TERMIN, t);
    }

    public void deleteTermin(Termin t) throws Exception {
        sendRequest(Operation.DELETE_TERMIN, t);
    }

    public void updateTermin(Termin t) throws Exception {
        sendRequest(Operation.UPDATE_TERMIN, t);
    }

    public ArrayList<Termin> getAllTermin(Termin termin) throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, termin);
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        //posalji zahtev
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        //primi odgovor
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        //ako ima gresku baci exception a response ce da ga uhvati, ako nema greske znaci da je sve u redu pa posalji zahtev
        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
