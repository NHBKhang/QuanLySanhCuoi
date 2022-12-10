/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class TrangTriPhoiCanh extends DichVu{
    private String yeuCau = "";
    
    public TrangTriPhoiCanh(){
        super("D002", "Trang tri phoi canh", 1000000);
    }

    public TrangTriPhoiCanh(String yeuCau) {
        super("D002", "Trang tri phoi canh", 1000000);
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
    
        public List<DichVu> Nhap() {
        Scanner scanner = new Scanner(System.in);
        String yc;
        System.out.print("Nhap yeu cau trang tri: ");
        yc = scanner.nextLine();
        this.setYeuCau(yc.trim());
        return null;
    }   
        
    @Override
    public String toString() {
        return super.getMaDichVu() + "#" + ("".equals(this.yeuCau)? "*" : this.yeuCau);
    }
}
