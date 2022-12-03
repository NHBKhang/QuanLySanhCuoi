/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
/**
 *
 * @author ADMIN
 */
public class ThucUong extends Menu {
    private String hangSx;
    public ThucUong (int maMon, String tenMon, int giaMon, String hangSx) {
        super (maMon, tenMon, giaMon);
        this.hangSx=hangSx;
    }

    ThucUong() {
        this(0,null,0,null);
    }
    /**
     * @return the hangSx
     */
    public String getHangSx() {
        return hangSx;
    }

    /**
     * @param hangSx the hangSx to set
     */
    public void setHangSx(String hangSx) {
        this.hangSx = hangSx;
    }
}
