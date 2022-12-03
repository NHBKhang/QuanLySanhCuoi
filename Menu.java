/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Menu {
    private int maMon;
    private String tenMon;
    private int giaMon;

    public Menu(int maMon, String tenMon, int giaMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaMon = giaMon;
    }

    private List<Menu> ds = new ArrayList<>();

    public void initA() {
        ThucAn ta = new ThucAn();
        try ( Scanner c = new Scanner("C:\\Data\\ThucAn.txt")) {
            c.useDelimiter(",");
            while (c.hasNext()) {
                int maMon = c.nextInt();
                String tenMon = c.next();
                int giaMon = c.nextInt();
                boolean anChay = c.nextBoolean();
                ThucAn n = new ThucAn(maMon, tenMon, giaMon, anChay);
                ds.add(n);
                c.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void initB() {
        ThucUong tu = new ThucUong();
        try ( Scanner d = new Scanner("C:\\Data\\ThucUong.txt")) {
            d.useDelimiter(",");
            while (d.hasNext()) {
                int maMon = d.nextInt();
                String tenMon = d.next();
                int giaMon = d.nextInt();
                String hangSx = d.next();
                ThucUong m = new ThucUong(maMon, tenMon, giaMon, hangSx);
                ds.add(m);
                d.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void hienThi (){
        
    }

    /**
     * @return the maMon
     */
    public int getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(int maMon) {
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
     * @return the ds
     */
    public List<Menu> getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(List<Menu> ds) {
        this.ds = ds;
    }

}
