/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nina
 */
public class VeterinarLicenca extends AbstractDomainObject {

    private Licenca licenca;
    private Veterinar veterinar;
    private Date datumIzdavanja;

    public VeterinarLicenca(Licenca licenca, Veterinar veterinar, Date datumIzdavanja) {
        this.licenca = licenca;
        this.veterinar = veterinar;
        this.datumIzdavanja = datumIzdavanja;
    }

    public VeterinarLicenca() {
    }

    @Override
    public String nazivTabele() {
        return " VeterinarLicenca ";
    }

    @Override
    public String alijas() {
        return " vl ";
    }

    @Override
    public String join() {
        return " JOIN VETERINAR V ON (V.VETERINARID = VL.VETERINARID)\n"
                + "JOIN LICENCA L ON (L.LICENCAID = VL.LICENCAID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Veterinar v = new Veterinar(rs.getInt("VeterinarID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Licenca l = new Licenca(rs.getInt("LicencaID"),
                    rs.getString("l.naziv"));

            VeterinarLicenca vl = new VeterinarLicenca(l, v,
                    rs.getDate("datumIzdavanja"));

            lista.add(vl);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (LicencaID, VeterinarID, datumIzdavanja) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + licenca.getLicencaID() + ", " + veterinar.getVeterinarID() + ", "
                + "'" + new java.sql.Date(datumIzdavanja.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " veterinarID = " + veterinar.getVeterinarID();
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    public Veterinar getVeterinar() {
        return veterinar;
    }

    public void setVeterinar(Veterinar veterinar) {
        this.veterinar = veterinar;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

}
