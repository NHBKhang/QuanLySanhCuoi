/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class DoanhThu {

    private String tenTiec;
    private String maSanh;
    private String ngay;
    private int giaTien;

    private final String url = "C:\\Data\\ThongTinThue.txt";
    private File file = new File(getUrl());

    public DoanhThu(String ngay, int giaTien) {
        this.ngay = ngay;
        this.giaTien = giaTien;
    }

    private static List<DoanhThu> ds = new ArrayList<>();

    public void init() {
        DoanhThu dt = new DoanhThu();
        try ( Scanner a = new Scanner(getFile())) {
            a.useDelimiter(",");
            while (a.hasNext()) {
                a.next();
                a.next();
                a.next();
                String ngay = a.next();
                a.next();
                a.next();
                a.next();
                int giaTien = a.nextInt();
                DoanhThu h = new DoanhThu(ngay, giaTien);
                getDs().add(h);
                a.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void baoCaoThang() {
        Scanner dt= new Scanner(System.in);
        System.out.printf("Nhap Thang:");
        String thang=dt.next();
        System.out.printf("Nhap Nam:");
        String nam=dt.next();
        int tong=0;
        DoanhThu date = new DoanhThu();
        for (DoanhThu h : getDs()) {
            String [] catNgay = h.getNgay().split("/");
                if(catNgay[1].equals(thang)&&catNgay[2].equals(nam)){
                    tong=tong+h.getGiaTien();   
                }
        }
        System.out.println("Doanh Thu Thang "+thang+" la:"+tong);
    }
    
  
    public DoanhThu() {
        this(null,0);
    }

    /**
     * @return the maSanh
     */
    public String getMaSanh() {
        return maSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the tenTiec
     */
    public String getTenTiec() {
        return tenTiec;
    }

    /**
     * @param tenTiec the tenTiec to set
     */
    public void setTenTiec(String tenTiec) {
        this.tenTiec = tenTiec;
    }

    /**
     * @return the ngay
     */
    public String getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    /**
     * @return the giaTien
     */
    public int getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    /**
     * @return the tong
     */
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the ds
     */
    public static List<DoanhThu> getDs() {
        return ds;
    }

    /**
     * @param aDs the ds to set
     */
    public static void setDs(List<DoanhThu> aDs) {
        ds = aDs;
    }

}
