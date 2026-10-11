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
public class Klijent extends AbstractDomainObject {

    private int klijentID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private KategorijaKlijenta kategorijaKlijenta;

    @Override
    public String toString() {
        return ime + " " + prezime + " (Kategorija klijenta: " + kategorijaKlijenta + ")";
    }

    public Klijent(int klijentID, String ime, String prezime, String email, String telefon, KategorijaKlijenta kategorijaKlijenta) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.kategorijaKlijenta = kategorijaKlijenta;
    }

    public Klijent() {
    }

    @Override
    public String nazivTabele() {
        return " Klijent ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN KATEGORIJAKLIJENTA KK ON (KK.KATEGORIJAKLIJENTAID = K.KATEGORIJAKLIJENTAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            KategorijaKlijenta kk = new KategorijaKlijenta(rs.getInt("kategorijaKlijentaID"),
                    rs.getString("kk.naziv"));

            Klijent k = new Klijent(rs.getInt("klijentID"), rs.getString("ime"),
                    rs.getString("prezime"), rs.getString("email"),
                    rs.getString("telefon"), kk);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, telefon, kategorijaKlijentaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + kategorijaKlijenta.getKategorijaKlijentaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + " kategorijaKlijentaID = " + kategorijaKlijenta.getKategorijaKlijentaID();
    }

    @Override
    public String uslov() {
        return " klijentID = " + klijentID;
    }

    @Override
    public String dodatniUslov() {
        if (klijentID != -1) {
            return " WHERE KLIJENTID = " + klijentID;
        }
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(EMAIL) LIKE '%" + email + "%' "
                + "AND TELEFON LIKE '%" + telefon + "%' "
                + "AND LOWER(KK.NAZIV) LIKE '%" + kategorijaKlijenta.getNaziv() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY KLIJENTID ASC ";
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaKlijenta getKategorijaKlijenta() {
        return kategorijaKlijenta;
    }

    public void setKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) {
        this.kategorijaKlijenta = kategorijaKlijenta;
    }

}
