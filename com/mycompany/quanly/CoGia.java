/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

/**
 *
 * @author KHANG
 */
public class CoGia {
    private SanhCuoi sanhCuoi;
    private GiaThue giaThue;
    
    private long giaTong;
    
    public CoGia(SanhCuoi sanhCuoi, GiaThue giaThue){
        this.sanhCuoi = sanhCuoi;
        this.giaThue = giaThue;
        this.giaTong = (long) (sanhCuoi.getGiaThue() * giaThue.getHeSoTong());
    }

    /**
     * @return the giaTong
     */
    public long getGiaTong() {
        return giaTong;
    }
}
