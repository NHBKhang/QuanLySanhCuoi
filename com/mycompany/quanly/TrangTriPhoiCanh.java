/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

/**
 *
 * @author KHANG
 */
public class TrangTriPhoiCanh extends DichVu{
    private String yeuCau;
    
    public TrangTriPhoiCanh(){
        super("D002", "Trang tri phoi canh", 100000);
    }

    public TrangTriPhoiCanh(String yeuCau) {
        super("D002", "Trang tri phoi canh", 100000);
        this.yeuCau = yeuCau;
    }

    /**
     * @return the yeuCau
     */
    public String getYeuCau() {
        return yeuCau;
    }

    /**
     * @param yeuCau the yeuCau to set
     */
    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }
}
