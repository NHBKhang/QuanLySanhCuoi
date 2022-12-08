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
public class ThucUong extends Menu {

    private String hangSx;
    private final String url = "D:\\Data\\ThucUong.txt";
    private File file = new File(url);
    
    private String[] header = new String[]{"Ma Mon","Ten Mon","Gia Mon","Hang San Xuat"};

    public ThucUong(String maMon, String tenMon, int giaMon, String hangSx) {
        super(maMon, tenMon, giaMon);
        this.hangSx = hangSx;
    }
    
    public ThucUong(){}

    private static List<ThucUong> monUong = new ArrayList<>();

    public void init() {
        ThucUong tu = new ThucUong();
        try ( Scanner d = new Scanner(file)) {
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

    public void them() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.maMon + ",");
                writer.print(this.tenMon + ",");
                writer.print(Integer.toString(this.giaMon) + ",");
                writer.print(this.hangSx + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        getMonUong().add(this);
    }

    public void capNhat(String noiDung) throws IOException {
        String[] b = noiDung.split(",");
        for (int i = 0; i < b.length; i++) {
            this.capNhat(b[i], i + 1);
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
            this.hangSx = (noiDung.toUpperCase());
        }
        rewriteFile();
    }

    //Phuong Thuc Ho Tro Xoa
    public ThucUong tim(String maMon) {
        ThucUong drink = new ThucUong();
        for (ThucUong x : getMonUong()) {
            if (x.getMaMon().toLowerCase().equals(maMon.toLowerCase())) {
                drink = x;
                break;
            }
        }
        return drink;
    }

    public void xoaThucUong(String ma) throws IOException {
        monUong.remove(this);
        rewriteFile();
    }

    public List<ThucUong> tim(String noiDung, int i) throws FileNotFoundException {
        List<ThucUong> tu = new ArrayList<>();
        for (ThucUong drink : getMonUong()) {
            if (i == 1) {
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

    public void rewriteFile() throws IOException {
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
    
     public static void hienThi(List<ThucUong> thucUong, ThucUong tu){
            System.out.format(tu.getForm(), tu.getHeader());
            for (Object[] row : toTable(thucUong, thucUong.size())){
                System.out.format(tu.getForm(), row);
            }
        }
        
        public static void hienThi(ThucUong thucUong){
            System.out.format(thucUong.getForm(), thucUong.getHeader());
            Object[] row = new String[] {thucUong.maMon,
                                         thucUong.tenMon,
                                         String.valueOf(thucUong.giaMon),
                                         thucUong.hangSx};
            System.out.format(thucUong.getForm(), row);
        }
        
        public static void hienThiFile(ThucUong tu){
                System.out.format(tu.getForm(), tu.getHeader());
                for (Object[] row : toTable(getMonUong(), getMonUong().size())){
                    System.out.format(tu.getForm(), row);
               }
        }
             
        //  tao bang
        private static Object[][] toTable(List<ThucUong> uong, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {uong.get(i).maMon,
                                         uong.get(i).tenMon,
                                         String.valueOf(uong.get(i).giaMon),
                                         uong.get(i).hangSx};
            }
            return table;
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
    public static List<ThucUong> getMonUong() {
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

    /**
     * @return the header
     */
    public String[] getHeader() {
        return header;
    }

    @Override
    public String getMaMon() {
        return super.maMon;
    }

}
