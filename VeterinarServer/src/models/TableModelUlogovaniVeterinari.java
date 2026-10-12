/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ServerController;
import domain.Veterinar;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nina
 */
public class TableModelUlogovaniVeterinari extends AbstractTableModel implements Runnable {
//stavila sam da implementira runnable jer hocu real time da prikaze kad se veterinar uloguje/izloguje sa klijenta u tabeli na serveru

    private ArrayList<Veterinar> lista = new ArrayList<>();
    private int[] ukupno = new int[0];
    private int[] buduci = new int[0];
    private int[] danas = new int[0];
    private boolean[] online = new boolean[0];

    private String[] kolone = {"Ime", "Prezime", "Username", "Status",
        "Ukupno termina", "Buduci termini", "Termini danas"};

    public TableModelUlogovaniVeterinari() {
        refreshTable();
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
        Veterinar v = lista.get(row);
        switch (column) {
            case 0:
                return v.getIme();
            case 1:
                return v.getPrezime();
            case 2:
                return v.getUsername();
            case 3:
                return online[row] ? "Online" : "Offline";
            case 4:
                return ukupno[row];
            case 5:
                return buduci[row];
            case 6:
                return danas[row];
            default:
                return null;
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(3000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public final void refreshTable() {
        try {
            ArrayList<Veterinar> svi = ServerController.getInstance().getAllVeterinar();
            ArrayList<Veterinar> ulogovani = ServerController.getInstance().getUlogovaniVeterinari();

            ArrayList<Veterinar> poredjani = new ArrayList<>();
            for (Veterinar v : ulogovani) {
                poredjani.add(v);
            }
            for (Veterinar v : svi) {
                if (!ulogovani.contains(v)) {
                    poredjani.add(v);
                }
            }

            int n = poredjani.size();
            int[] u = new int[n];
            int[] b = new int[n];
            int[] d = new int[n];
            boolean[] on = new boolean[n];

            for (int i = 0; i < n; i++) {
                Veterinar v = poredjani.get(i);
                on[i] = ulogovani.contains(v);
                u[i] = ServerController.getInstance().getBrojTermina(v.getVeterinarID(), "UKUPNO");
                b[i] = ServerController.getInstance().getBrojTermina(v.getVeterinarID(), "BUDUCI");
                d[i] = ServerController.getInstance().getBrojTermina(v.getVeterinarID(), "DANAS");
            }

            this.lista = poredjani;
            this.ukupno = u;
            this.buduci = b;
            this.danas = d;
            this.online = on;

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
