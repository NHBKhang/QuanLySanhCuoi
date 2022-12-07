/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.util.List;


/**
 *
 * @author ADMIN
 */
abstract class Menu {
    protected String maMon;
    protected String tenMon;
    protected int giaMon;
    
    protected String form = "%-10s%-20s%-12s%-10s%n";

    protected Menu(String maMon, String tenMon, int giaMon) {
        this.maMon = maMon;
        if (tenMon != null)
            this.tenMon = tenMon.toUpperCase();
        this.giaMon = giaMon;
    }
    
    protected Menu(){}
    
    protected abstract void init();

    /**
     * @return the maMon
     */
    public String getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    /**
     * @return the tenMon
     */
    public String getTenMon() {
        return tenMon;
    }

    /**
     * @param tenMon the tenMon to set
     */
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    /**
     * @return the giaMon
     */
    public int getGiaMon() {
        return giaMon;
    }

    /**
     * @param giaMon the giaMon to set
     */
    public void setGiaMon(int giaMon) {
        this.giaMon = giaMon;
    }

    /**
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * @return the header
     */
    public abstract String[] getHeader();

    
}
