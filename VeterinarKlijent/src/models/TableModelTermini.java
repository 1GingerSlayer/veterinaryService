/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Klijent;
import domain.Veterinar;
import domain.Termin;
import domain.KategorijaKlijenta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nina
 */
public class TableModelTermini extends AbstractTableModel implements Runnable {

    private ArrayList<Termin> lista;
    private String[] kolone = {"ID", "Klijent", "Datum i vreme", "Ukupna cena"};
    private String parametarImeKlijenta = "";
    private String parametarPrezimeKlijenta = "";
    private String parametarImeVeterinara = "";
    private String parametarPrezimeVeterinara = "";
    private String parametarUsluga = "";
    private String parametarDatum = "";
    private Termin termin = new Termin(-1, null, 0, 0, 0,
            new Veterinar(-1, "", "", "", ""),
            new Klijent(-1, "", "", "", "", new KategorijaKlijenta(-1, "")), null);

    public TableModelTermini() {
        try {
            lista = ClientController.getInstance()
                    .getAllTermin(new Termin(-1, null, 0, 0, 0,
                            new Veterinar(-1, "", "", "", ""),
                            new Klijent(-1, "", "", "", "", new KategorijaKlijenta(-1, "")), null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
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
        Termin t = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        switch (column) {
            case 0:
                return t.getTerminID();
            case 1:
                return t.getKlijent();
            case 2:
                return sdf.format(t.getDatumVremePocetka());
            case 3:
                return t.getKonacanIznos() + "din";

            default:
                return null;
        }
    }

    public Termin getSelectedTermin(int row) {
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
            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarImeKlijenta(String ime) {
        this.parametarImeKlijenta = ime;
        refreshTable();
    }

    public void setParametarPrezimeKlijenta(String prezime) {
        this.parametarPrezimeKlijenta = prezime;
        refreshTable();
    }

    public void setParametarImeVeterinara(String ime) {
        this.parametarImeVeterinara = ime;
        refreshTable();
    }

    public void setParametarPrezimeVeterinara(String prezime) {
        this.parametarPrezimeVeterinara = prezime;
        refreshTable();
    }

    public void setParametarUsluga(String usluga) {
        this.parametarUsluga = usluga;
        refreshTable();
    }

    public void setParametarDatum(String datum) {
        this.parametarDatum = datum;
        refreshTable();
    }

    public void refreshTable() {
        try {

            termin.getKlijent().setIme(parametarImeKlijenta.toLowerCase());
            termin.getKlijent().setPrezime(parametarPrezimeKlijenta.toLowerCase());
            termin.getVeterinar().setIme(parametarImeVeterinara.toLowerCase());
            termin.getVeterinar().setPrezime(parametarPrezimeVeterinara.toLowerCase());
            termin.setFilterUslugaNaziv(parametarUsluga.toLowerCase());
            termin.setFilterDatum(parametarDatum);

            lista = ClientController.getInstance().getAllTermin(termin);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Termin> getLista() {
        return lista;
    }

}
