/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

/**
 *
 * @author KHANG
 */
public class Karaoke extends DichVu{
    private String thoiGianThue;
    
    public Karaoke(){
        super("D001", "Karaoke", 120000);
    }

    public Karaoke(String thoiGianThue) {
        super("D001", "Karaoke", 120000);
        this.thoiGianThue = thoiGianThue;
    }

    /**
     * @return the thoiGianThue
     */
    public String getThoiGianThue() {
        return thoiGianThue;
    }

    /**
     * @param thoiGianThue the thoiGianThue to set
     */
    public void setThoiGianThue(String thoiGianThue) {
        this.thoiGianThue = thoiGianThue;
    }
    
    
}
