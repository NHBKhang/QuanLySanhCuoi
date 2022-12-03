/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

/**
 *
 * @author KHANG
 */
public class ThueCaSi extends DichVu{
    private CaSi caSi;
    
    private int soLuong;
    
    public ThueCaSi(){
        super("D003", "Thue Ca Si", 120000);
    }
    
    public ThueCaSi(CaSi caSi, int soLuong){
       super("D003", "Thue Ca Si", 120000);
       this.caSi = caSi;
       this.soLuong = soLuong;
    }

    /**
     * @return the caSi
     */
    public CaSi getCaSi() {
        return caSi;
    }

    /**
     * @param caSi the caSi to set
     */
    public void setCaSi(CaSi caSi) {
        this.caSi = caSi;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
