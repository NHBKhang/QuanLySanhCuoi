/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

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
    private final String url = "D:\\Data\\ThucAn.txt";
    private File file = new File(getUrl());
    
    private String[] header = new String[] {"Ma Mon","Ten Mon","Gia Mon","An Chay"};

    public ThucAn(String maMon, String tenMon, int giaMon, boolean anChay) {
        super(maMon, tenMon, giaMon);
        this.anChay = anChay;
    }
    
    public ThucAn() {}

    private static List<ThucAn> monAn = new ArrayList<>();

    public void init() {
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
    public void them() throws FileNotFoundException, IOException {
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
    public void capNhat(String noiDung) throws IOException {
        String[] a = noiDung.split(",");
        for (int i = 0; i < a.length; i++) {
            this.capNhat(a[i], i + 1);
        }
        rewriteFile();
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setMaMon(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setTenMon(noiDung.toUpperCase());
        } else if (viTriSua == 3) {
            this.setGiaMon(Integer.parseInt(noiDung));
        } else {
            this.anChay = Boolean.parseBoolean(noiDung);
        }
        rewriteFile();
    }

    //  phuong thuc ho tro cap nhat va xoa
    public ThucAn timXoa(String maMon) {
        ThucAn food = new ThucAn();
        for (ThucAn s : getMonAn()) {
            if (s.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                food = s;
                break;
            }
        }
        return food;
    }

    public void xoaThucAn(String ma) throws IOException {
        monAn.remove(this);
        rewriteFile();
    }

    public List<ThucAn> traCuu(String noiDung, int i) throws FileNotFoundException {
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
    public void rewriteFile() throws IOException {
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

    
    public static void hienThi(List<ThucAn> thucAn, ThucAn ta){
            System.out.format(ta.getForm(), ta.getHeader());
            for (Object[] row : toTable(thucAn, thucAn.size())){
                System.out.format(ta.getForm(), row);
            }
        }
        
        public static void hienThi(ThucAn thucAn){
            System.out.format(thucAn.getForm(), thucAn.getHeader());
            Object[] sanh = new String[] {thucAn.maMon,
                                         thucAn.tenMon,
                                         String.valueOf(thucAn.giaMon),
                                         (thucAn.anChay == true? "Co": "Khong")};
            System.out.format(thucAn.getForm(), sanh);
        }
        
        public static void hienThiFile(ThucAn thucAn){
                System.out.format(thucAn.getForm(), thucAn.getHeader());
                for (Object[] row : toTable(getMonAn(), getMonAn().size())){
                    System.out.format(thucAn.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<ThucAn> an, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {an.get(i).maMon,
                                         an.get(i).tenMon,
                                         String.valueOf(an.get(i).giaMon),
                                         (an.get(i).anChay == true? "Co": "Khong")};
            }
            return table;
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

    /**
     * @return the header
     */
    public String[] getHeader() {
        return header;
    }
}
