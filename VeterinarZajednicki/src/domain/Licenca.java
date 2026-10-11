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
public class Licenca extends AbstractDomainObject {

    private int licencaID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Licenca(int licencaID, String naziv) {
        this.licencaID = licencaID;
        this.naziv = naziv;
    }

    public Licenca() {
    }

    @Override
    public String nazivTabele() {
        return " Licenca ";
    }

    @Override
    public String alijas() {
        return " l ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Licenca l = new Licenca(rs.getInt("LicencaID"),
                    rs.getString("l.naziv"));

            lista.add(l);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " licencaID = " + licencaID;
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return " ORDER BY LICENCAID ASC ";
    }

    public int getLicencaID() {
        return licencaID;
    }

    public void setLicencaID(int licencaID) {
        this.licencaID = licencaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
