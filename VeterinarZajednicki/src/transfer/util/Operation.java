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
}
