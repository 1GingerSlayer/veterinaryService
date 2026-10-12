/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Klijent;
import domain.KategorijaKlijenta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nina
 */
public class TableModelKlijenti extends AbstractTableModel implements Runnable {

    private ArrayList<Klijent> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Kategorija klijenta"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private String parametarEmail = "";
    private String parametarTelefon = "";
    private String parametarKategorija = "";
    private Klijent klijent = new Klijent(-1, "", "", "", "", new KategorijaKlijenta(-1, ""));

    public TableModelKlijenti() {
        try {
            lista = ClientController.getInstance()
                    .getAllKlijent(new Klijent(-1, "", "", "", "", new KategorijaKlijenta(-1, "")));
        } catch (Exception ex) {
            Logger.getLogger(TableModelKlijenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Klijent k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKlijentID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTelefon();
            case 5:
                return k.getKategorijaKlijenta();

            default:
                return null;
        }
    }

    public Klijent getSelectedKlijent(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelKlijenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarIme(String ime) {
        this.parametarIme = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezime = prezime;
        refreshTable();
    }

    public void setParametarEmail(String email) {
        this.parametarEmail = email;
        refreshTable();
    }

    public void setParametarTelefon(String telefon) {
        this.parametarTelefon = telefon;
        refreshTable();
    }

    public void setParametarKategorija(String kategorija) {
        this.parametarKategorija = kategorija;
        refreshTable();
    }

    public void refreshTable() {
        try {

            klijent.setIme(parametarIme.toLowerCase());
            klijent.setPrezime(parametarPrezime.toLowerCase());
            klijent.setEmail(parametarEmail.toLowerCase());
            klijent.setTelefon(parametarTelefon);
            klijent.getKategorijaKlijenta().setNaziv(parametarKategorija.toLowerCase());

            lista = ClientController.getInstance().getAllKlijent(klijent);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }

}
