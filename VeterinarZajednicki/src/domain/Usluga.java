/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nina
 */
public class Usluga extends AbstractDomainObject {

    private int uslugaID;
    private String naziv;
    private String opis;
    private double cena;
    private int trajanjeMin;

    public Usluga() {
    }

    public Usluga(int uslugaID, String naziv, String opis, double cena, int trajanjeMin) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.trajanjeMin = trajanjeMin;
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getTrajanjeMin() {
        return trajanjeMin;
    }

    public void setTrajanjeMin(int trajanjeMin) {
        this.trajanjeMin = trajanjeMin;
    }

    @Override
    public String toString() {
        return naziv + " (Cena: " + cena + "din)";
    }

    @Override
    public String nazivTabele() {
        return " usluga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Usluga u = new Usluga(
                    rs.getInt("u.uslugaID"),
                    rs.getString("u.Naziv"),
                    rs.getString("u.Opis"),
                    rs.getDouble("u.Cena"),
                    rs.getInt("u.TrajanjeMin")
            );
            lista.add(u);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Opis, Cena, TrajanjeMin) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', " + cena + ", " + trajanjeMin;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Cena = " + cena + ", TrajanjeMin = " + trajanjeMin + " ";
    }

    @Override
    public String uslov() {
        return " uslugaID = " + uslugaID;
    }

    @Override
    public String dodatniUslov() {
        if (uslugaID != -1) {
            return " WHERE uslugaID = " + uslugaID;
        }
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%'  ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY USLUGAID ASC ";
    }
}
