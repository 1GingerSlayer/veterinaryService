/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.KategorijaKlijenta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nina
 */
public class TableModelKategorijeKlijenta extends AbstractTableModel implements Runnable {

    private ArrayList<KategorijaKlijenta> lista;
    private String[] kolone = {"ID", "Naziv"};
    private String parametarNaziv = "";
    private KategorijaKlijenta kategorijaKlijenta = new KategorijaKlijenta(-1, "");

    public TableModelKategorijeKlijenta() {
        try {
            lista = ClientController.getInstance()
                    .getAllKategorijaKlijenta(new KategorijaKlijenta(-1, ""));
        } catch (Exception ex) {
            Logger.getLogger(TableModelKategorijeKlijenta.class.getName()).log(Level.SEVERE, null, ex);
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
        KategorijaKlijenta kk = lista.get(row);
        switch (column) {
            case 0:
                return kk.getKategorijaKlijentaID();
            case 1:
                return kk.getNaziv();
            default:
                return null;
        }
    }

    public KategorijaKlijenta getSelectedKategorijaKlijenta(int row) {
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
            Logger.getLogger(TableModelKategorijeKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNaziv(String naziv) {
        this.parametarNaziv = naziv;
        refreshTable();
    }

    public void refreshTable() {
        try {
            kategorijaKlijenta.setNaziv(parametarNaziv.toLowerCase());
            lista = ClientController.getInstance().getAllKategorijaKlijenta(kategorijaKlijenta);
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<KategorijaKlijenta> getLista() {
        return lista;
    }
}
