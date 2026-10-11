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
public class KategorijaKlijenta extends AbstractDomainObject {

    private int kategorijaKlijentaID;
    private String naziv;

    public KategorijaKlijenta() {
    }

    public KategorijaKlijenta(int kategorijaKlijentaID, String naziv) {
        this.kategorijaKlijentaID = kategorijaKlijentaID;
        this.naziv = naziv;
    }

    public int getKategorijaKlijentaID() {
        return kategorijaKlijentaID;
    }

    public void setKategorijaKlijentaID(int kategorijaKlijentaID) {
        this.kategorijaKlijentaID = kategorijaKlijentaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String nazivTabele() {
        return " KategorijaKlijenta ";
    }

    @Override
    public String alijas() {
        return " kk ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            KategorijaKlijenta kk = new KategorijaKlijenta(
                    rs.getInt("kk.KategorijaKlijentaID"),
                    rs.getString("kk.Naziv"));

            lista.add(kk);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " KategorijaKlijentaID = " + kategorijaKlijentaID;
    }

    @Override
    public String dodatniUslov() {
        if (kategorijaKlijentaID != -1) {
            return " WHERE kategorijaKlijentaID = " + kategorijaKlijentaID;
        }
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY KATEGORIJAKLIJENTAID ASC ";
    }
}
