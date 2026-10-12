/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer.util;

/**
 *
 * @author Nina
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_KATEGORIJA_KLIJENTA = 2;
    public static final int DELETE_KATEGORIJA_KLIJENTA = 3;
    public static final int UPDATE_KATEGORIJA_KLIJENTA = 4;
    public static final int GET_ALL_KATEGORIJA_KLIJENTA = 5;

    public static final int ADD_KLIJENT = 6;
    public static final int DELETE_KLIJENT = 7;
    public static final int UPDATE_KLIJENT = 8;
    public static final int GET_ALL_KLIJENT = 9;

    public static final int ADD_USLUGA = 10;
    public static final int DELETE_USLUGA = 11;
    public static final int UPDATE_USLUGA = 12;
    public static final int GET_ALL_USLUGA = 13;

    public static final int ADD_TERMIN = 14;
    public static final int DELETE_TERMIN = 15;
    public static final int UPDATE_TERMIN = 16;
    public static final int GET_ALL_TERMIN = 17;
}
