/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.Veterinar;
import forms.MainForm;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Nina
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Veterinar ulogovani;
    private MainForm mf;

    private Session() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Veterinar ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Veterinar getUlogovani() {
        return ulogovani;
    }

    public MainForm getMf() {
        return mf;
    }

    public void setMf(MainForm mf) {
        this.mf = mf;
    }

}
