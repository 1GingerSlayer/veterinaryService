/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Nina
 */
public class Veterinar extends AbstractDomainObject {

    private int veterinarID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Veterinar() {
    }

    public Veterinar(int veterinarID, String ime, String prezime, String username, String password) {
        this.veterinarID = veterinarID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veterinar other = (Veterinar) obj;
        if (!Objects.equals(this.veterinarID, other.veterinarID)) {
            return false;
        }
        return true;
    }

    public int getVeterinarID() {
        return veterinarID;
    }

    public void setVeterinarID(int veterinarID) {
        this.veterinarID = veterinarID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " veterinar ";
    }

    @Override
    public String alijas() {
        return " v ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Veterinar v = new Veterinar(rs.getInt("veterinarID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(v);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslov() {
        return " veterinarID = " + veterinarID;
    }

    @Override
    public String dodatniUslov() {
        if (veterinarID != -1) {
            return " WHERE veterinarID = " + veterinarID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(USERNAME) LIKE '%" + username + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY VETERINARID ASC ";
    }

}
