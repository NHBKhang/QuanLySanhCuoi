/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
/**
 *
 * @author ADMIN
 */
public class ThucAn extends Menu {

    private boolean anChay = true;
    public ThucAn(int maMon,String tenMon,int giaMon,boolean anChay){
        super(maMon,tenMon,giaMon);
        this.anChay=anChay;
    }

    public ThucAn() {
        this(0,null,0,false);
    }

    /**
     * @return the anChay
     */
    public boolean isAnChay() {
        return anChay;
    }

    /**
     * @param anChay the anChay to set
     */
    public void setAnChay(boolean anChay) {
        this.anChay = anChay;
    }
}
