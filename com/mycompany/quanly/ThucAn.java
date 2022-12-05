/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ThucAn extends Menu {

    private boolean anChay = true;
    private final String url = "C:\\Data\\ThucAn";
    private File file = new File(getUrl());

    public ThucAn(String maMon, String tenMon, int giaMon, boolean anChay) {
        super(maMon, tenMon, giaMon);
        this.anChay = anChay;
    }

    private static List<ThucAn> monAn = new ArrayList<>();

    public void initA() {
        ThucAn ta = new ThucAn();
        try ( Scanner c = new Scanner(file)) {
            c.useDelimiter(",");
            while (c.hasNext()) {
                String maMon = c.next();
                String tenMon = c.next();
                int giaMon = c.nextInt();
                boolean anChay = c.nextBoolean();
                ThucAn n = new ThucAn(maMon, tenMon, giaMon, anChay);
                getMonAn().add(n);
                c.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Them phan tu//
    public void themThucAn() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.getMaMon() + ",");
                writer.print(this.getTenMon() + ",");
                writer.print(Integer.toString(this.getGiaMon()) + ",");
                writer.print(Boolean.toString(this.anChay) + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        getMonAn().add(this);
    }

    //Phuong Thuc Ho Tro//
    public void capNhatA(String noiDung) throws IOException {
        String[] a = noiDung.split(",");
        for (int i = 0; i < a.length; i++) {
            this.capNhatA(a[i], i + 1);
        }
        rewriteFileA();
    }

    public void capNhatA(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setMaMon(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setTenMon(noiDung.toUpperCase());
        } else if (viTriSua == 3) {
            this.setGiaMon(Integer.parseInt(noiDung));
        } else {
            this.anChay = Boolean.parseBoolean(noiDung);
        }
        rewriteFileA();
    }

    //Phuong Thuc Ho Tro Xoa
    public ThucAn timXoaA(String maMon) {
        ThucAn food = new ThucAn();
        for (ThucAn s : getMonAn()) {
            if (s.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                food = s;
            }
        }
        return food;
    }

    public void xoaThucAn(String ma) throws IOException {
        for (ThucAn s : getMonAn()) {
            if (s.maMon.toLowerCase().equals(ma.toLowerCase())) {
                System.out.println(s.maMon.toLowerCase().equals(ma.toLowerCase()));
                System.out.println(s.maMon);
                getMonAn().remove(s);
                break;
            }
        }
        rewriteFileA();
    }

    public static List<ThucAn> timThucAn(String noiDung, int i) throws FileNotFoundException {
        List<ThucAn> ta = new ArrayList<>();
        for (ThucAn food : getMonAn()) {
            if (i == 0) {
                if (food.maMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    ta.add(food);
                }
            } else if (i == 1) {
                if (food.tenMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    ta.add(food);
                }
            } else if (i == 2) {
                if (food.giaMon >= Integer.parseInt(noiDung)) {
                    ta.add(food);
                }
            } else {
                if (Boolean.toString(food.anChay).toLowerCase().contains(noiDung.toLowerCase())) {
                    ta.add(food);
                }
            }

        }
        return ta;
    }

    //Tao ma moi//
    private String nextMaThucAn(String maMon) {
        if (maMon == null) {
            maMon = "100001";
        } else {
            int nextMa = (Integer.parseInt(maMon) + 1);
            maMon = Integer.toString(nextMa);
        }
        return maMon;
    }

    private String docMaThucAn() throws FileNotFoundException {
        String maMon = null;
        File file = new File(this.url);
        if (file.length() != 0) {
            try ( Scanner scanner = new Scanner(file)) {
                scanner.useDelimiter(",");
                while (scanner.hasNextLine()) {
                    maMon = scanner.next();
                    scanner.nextLine();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return maMon;
    }

    //Viet lai//
    public void rewriteFileA() throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file, false)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                for (ThucAn s : getMonAn()) {
                    writer.print(s.getMaMon() + ",");
                    writer.print(s.getTenMon() + ",");
                    writer.print(Integer.toString(s.getGiaMon()) + ",");
                    writer.print(Boolean.toString(s.anChay) + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ThucAn() {
        this(null, null, 0, false);
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

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the monAn
     */
    public static List<ThucAn> getMonAn() {
        return monAn;
    }

    /**
     * @param aMonAn the monAn to set
     */
    public static void setMonAn(List<ThucAn> aMonAn) {
        monAn = aMonAn;
    }
}
