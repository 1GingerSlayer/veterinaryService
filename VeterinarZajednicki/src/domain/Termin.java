/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nina
 */
public class Termin extends AbstractDomainObject {

    private int terminID;
    private Date datumVremePocetka;
    private double iznosBezPopusta;
    private double popust;
    private double konacanIznos;
    private Veterinar veterinar;
    private Klijent klijent;
    private ArrayList<StavkaTermina> stavkeTermina;

    public Termin() {
    }

    public Termin(int terminID, Date datumVremePocetka, double iznosBezPopusta, double popust,
            double konacanIznos, Veterinar veterinar, Klijent klijent, ArrayList<StavkaTermina> stavkeTermina) {
        this.terminID = terminID;
        this.datumVremePocetka = datumVremePocetka;
        this.iznosBezPopusta = iznosBezPopusta;
        this.popust = popust;
        this.konacanIznos = konacanIznos;
        this.veterinar = veterinar;
        this.klijent = klijent;
        this.stavkeTermina = stavkeTermina;
    }

    public int getTerminID() {
        return terminID;
    }

    public void setTerminID(int terminID) {
        this.terminID = terminID;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public double getIznosBezPopusta() {
        return iznosBezPopusta;
    }

    public void setIznosBezPopusta(double iznosBezPopusta) {
        this.iznosBezPopusta = iznosBezPopusta;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getKonacanIznos() {
        return konacanIznos;
    }

    public void setKonacanIznos(double konacanIznos) {
        this.konacanIznos = konacanIznos;
    }

    public Veterinar getVeterinar() {
        return veterinar;
    }

    public void setVeterinar(Veterinar veterinar) {
        this.veterinar = veterinar;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String nazivTabele() {
        return " termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN VETERINAR V ON (V.VETERINARID = T.VETERINARID) "
                + " JOIN Klijent kl ON (kl.KlijentID = t.KlijentID) "
                + " JOIN KategorijaKlijenta kk ON (kk.KategorijaKlijentaID = kl.KategorijaKlijentaID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Veterinar v = new Veterinar(
                    rs.getInt("v.VeterinarID"),
                    rs.getString("v.Ime"),
                    rs.getString("v.Prezime"),
                    rs.getString("v.Username"),
                    rs.getString("v.Password")
            );
            KategorijaKlijenta kk = new KategorijaKlijenta(
                    rs.getInt("kk.KategorijaKlijentaID"),
                    rs.getString("kk.Naziv")
            );
            Klijent kl = new Klijent(
                    rs.getInt("kl.KlijentID"),
                    rs.getString("kl.Ime"),
                    rs.getString("kl.Prezime"),
                    rs.getString("kl.Email"),
                    rs.getString("kl.Telefon"),
                    kk
            );
            Termin t = new Termin(
                    rs.getInt("t.TerminID"),
                    rs.getTimestamp("t.DatumVremePocetka"),
                    rs.getDouble("t.IznosBezPopusta"),
                    rs.getDouble("t.Popust"),
                    rs.getDouble("t.KonacanIznos"),
                    v, kl, new ArrayList<>()
            );
            lista.add(t);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVremePocetka, IznosBezPopusta, Popust, KonacanIznos, VeterinarID, KlijentID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + iznosBezPopusta + ", " + popust + ", " + konacanIznos + ", "
                + veterinar.getVeterinarID() + ", " + klijent.getKlijentID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumVremePocetka = '" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "IznosBezPopusta = " + iznosBezPopusta + ", "
                + "KonacanIznos = " + konacanIznos + " ";
    }

    @Override
    public String uslov() {
        return " terminID = " + terminID;
    }

    @Override
    public String dodatniUslov() {
        if (terminID != -1) {
            return " WHERE terminID = " + terminID;
        }

        String uslovPretrage = " WHERE LOWER(KL.IME) LIKE '%" + klijent.getIme() + "%' "
                + "AND LOWER(KL.PREZIME) LIKE '%" + klijent.getPrezime() + "%' "
                + "AND LOWER(V.IME) LIKE '%" + veterinar.getIme() + "%' "
                + "AND LOWER(V.PREZIME) LIKE '%" + veterinar.getPrezime() + "%' ";

        return uslovPretrage;
    }

    @Override
    public String orderBy() {
        return " ORDER BY TERMINID ASC ";
    }

    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }

    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        this.stavkeTermina = stavkeTermina;
    }
}
