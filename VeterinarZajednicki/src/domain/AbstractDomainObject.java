/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nina
 */
public abstract class AbstractDomainObject implements Serializable {

    public abstract String nazivTabele();

    public abstract String alijas();

    public abstract String join();

    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;

    public abstract String koloneZaInsert();

    public abstract String vrednostiZaInsert();

    public abstract String vrednostiZaUpdate();

    public abstract String uslov();

    public abstract String dodatniUslov();

    public abstract String orderBy();

}
