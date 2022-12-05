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
public class ThucUong extends Menu {

    private String hangSx;
    private final String url = "C:\\Data\\ThucUong";
    private File file = new File(getUrl());

    public ThucUong(String maMon, String tenMon, int giaMon, String hangSx) {
        super(maMon, tenMon, giaMon);
        this.hangSx = hangSx;
    }

    private static List<ThucUong> monUong = new ArrayList<>();

    public void initB() {
        ThucUong tu = new ThucUong();
        try ( Scanner d = new Scanner("C:\\Data\\ThucUong.txt")) {
            d.useDelimiter(",");
            while (d.hasNext()) {
                String maMon = d.next();
                String tenMon = d.next();
                int giaMon = d.nextInt();
                String hangSx = d.next();
                ThucUong m = new ThucUong(maMon, tenMon, giaMon, hangSx);
                getMonUong().add(m);
                d.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void themThucUong() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.getMaMon() + ",");
                writer.print(this.getTenMon() + ",");
                writer.print(Integer.toString(this.getGiaMon()) + ",");
                writer.print(this.hangSx + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        getMonUong().add(this);
    }

    public void capNhatB(String noiDung) throws IOException {
        String[] b = noiDung.split(",");
        for (int i = 0; i < b.length; i++) {
            this.capNhatB(b[i], i + 1);
        }
        rewriteFileB();
    }

    public void capNhatB(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setMaMon(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setTenMon(noiDung.toUpperCase());
        } else if (viTriSua == 3) {
            this.setGiaMon(Integer.parseInt(noiDung));
        } else {
            this.hangSx = (noiDung.toUpperCase());
        }
        rewriteFileB();
    }

    //Phuong Thuc Ho Tro Xoa
    public ThucUong timXoaB(String maMon) {
        ThucUong drink = new ThucUong();
        for (ThucUong x : getMonUong()) {
            if (x.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                drink = x;
            }
        }
        return drink;
    }

    public void xoaThucUong(String ma) throws IOException {
        for (ThucUong x : getMonUong()) {
            if (x.maMon.toLowerCase().equals(ma.toLowerCase())) {
                System.out.println(x.maMon.toLowerCase().equals(ma.toLowerCase()));
                System.out.println(x.maMon);
                getMonUong().remove(x);
                break;
            }
        }
        rewriteFileB();
    }

    public List<ThucUong> timThucUong(String noiDung, int i) throws FileNotFoundException {
        List<ThucUong> tu = new ArrayList<>();
        for (ThucUong drink : getMonUong()) {
            if (i == 0) {
                if (drink.maMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    tu.add(drink);
                }
            } else if (i == 1) {
                if (drink.tenMon.toLowerCase().contains(noiDung.toLowerCase())) {
                    tu.add(drink);
                }
            } else if (i == 2) {
                if (drink.giaMon >= Integer.parseInt(noiDung)) {
                    tu.add(drink);
                }
            } else {
                if (drink.hangSx.toLowerCase().contains(noiDung.toLowerCase())) {
                    tu.add(drink);
                }
            }
        }
        return tu;
    }

    private String nextMaThucUong(String maMon) {
        if (maMon == null) {
            maMon = "200001";
        } else {
            int nextMa = (Integer.parseInt(maMon) + 1);
            maMon = Integer.toString(nextMa);
        }
        return maMon;
    }

    private String docMaThucUong() throws FileNotFoundException {
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

    public void rewriteFileB() throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file, false)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                for (ThucUong x : getMonUong()) {
                    writer.print(x.getMaMon() + ",");
                    writer.print(x.getTenMon() + ",");
                    writer.print(Integer.toString(x.getGiaMon()) + ",");
                    writer.print(x.getHangSx() + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    ThucUong() {
        this(null, null, 0, null);
    }

    /**
     * @return the hangSx
     */
    public String getHangSx() {
        return hangSx;
    }

    /**
     * @param hangSx the hangSx to set
     */
    public void setHangSx(String hangSx) {
        this.hangSx = hangSx;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the monUong
     */
    public List<ThucUong> getMonUong() {
        return monUong;
    }

    /**
     * @return the fileB
     */
    public File getFile() {
        return file;
    }

    /**
     * @param aMonUong the monUong to set
     */
    public static void setMonUong(List<ThucUong> aMonUong) {
        monUong = aMonUong;
    }
}
