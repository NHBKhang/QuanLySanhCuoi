/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class Karaoke extends DichVu{
    private String thoiGianThue;
    
    public Karaoke(){
        super("D001", "Karaoke", 300000);
    }

    public Karaoke(String thoiGianThue) {
        super("D001", "Karaoke", 300000);
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
    
    public List<DichVu> Nhap() {
        Scanner scanner = new Scanner(System.in);
        String[] thoiGian; 
        String t;
        int flag = -1;
        System.out.println("(thoi gian thue khong qua 5 tieng)");
        do{
            System.out.print("Nhap thoi gian thue (vd: 2:30): ");
            t = scanner.nextLine();
            String[] cat = t.split(" ");
            thoiGian = cat[0].split(":");
            if (thoiGian.length <= 2){
                try{
                    int[] time = {0,0};
                    for (int i = 0; i < thoiGian.length; i++){
                        time[i] = Integer.parseInt(thoiGian[i]);
                    }
                    if(time[0] > 0 && time[0] <= 5) flag = 0;
                } catch (Exception e){
                    flag = -1;
                }
            }
            else 
                flag = -1;
        }
        while (flag == -1);
        this.setThoiGianThue(t);
        return null;
    }
    
    @Override
    public String toString() {
        return super.getMaDichVu() + "#" + this.thoiGianThue;
    }
    
    @Override
    public long getGiaDichVu() {
        double thoiGian = 0;
        String[] t = this.thoiGianThue.split(":");
        double[] time ={0f,0f};
        for (int i = 0; i < t.length; i++){
            time[i] = Double.parseDouble(t[i]);
        }
        thoiGian = time[0] + time[1]/60;
        return (long) (super.getGiaDichVu() * thoiGian);
    }
    
    @Override
    void xuat(String form, NumberFormat vnFormat, String dv) {
        String[] t;
        t = this.thoiGianThue.split(":");
        double phut, gio = Double.parseDouble(t[0]);
        try{
            phut = Double.parseDouble(t[1]) / 60;
        } catch (Exception e){
            phut = 0;
        }
        String so = String.valueOf(gio + phut);
        System.out.printf(form, super.tenDichVu,so + " x " + vnFormat.format(super.giaDichVu) + dv);
    }
    
}
