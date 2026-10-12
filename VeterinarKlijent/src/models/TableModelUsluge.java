/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Usluga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nina
 */
public class TableModelUsluge extends AbstractTableModel implements Runnable {

    private ArrayList<Usluga> lista;
    private String[] kolone = {"ID", "Naziv", "Cena", "Trajanje"};
    private String parametarNaziv = "";
    private Usluga usluga = new Usluga(-1, "", "", 0, 0);

    public TableModelUsluge() {
        try {
            lista = ClientController.getInstance()
                    .getAllUsluga(new Usluga(-1, "", "", 0, 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelUsluge.class.getName()).log(Level.SEVERE, null, ex);
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
        Usluga u = lista.get(row);

        switch (column) {
            case 0:
                return u.getUslugaID();
            case 1:
                return u.getNaziv();
            case 2:
                return u.getCena() + "din";
            case 3:
                return u.getTrajanjeMin() + "min";

            default:
                return null;
        }
    }

    public Usluga getSelectedUsluga(int row) {
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
            Logger.getLogger(TableModelUsluge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarNaziv(String naziv) {
        this.parametarNaziv = naziv;
        refreshTable();
    }

    public void refreshTable() {
        try {

            usluga.setNaziv(parametarNaziv.toLowerCase());

            lista = ClientController.getInstance().getAllUsluga(usluga);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }

}
