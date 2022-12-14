/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import static com.mycompany.quanly.SanhCuoi.getCacSanh;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
class DoanhThu extends BaoCaoThongKe{
    public DoanhThu(String ngay, int giaTien) {
        this.ngay = ngay;
        this.giaTien = giaTien;
    }
    public DoanhThu() {}
    
    public void init() {
        try ( Scanner a = new Scanner(file)) {
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
                DoanhThu dt = new DoanhThu(ngay, giaTien);
                getDs().add(dt);
                a.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void baoCaoThang() {
        int flag = -1;
        Scanner dt = new Scanner(System.in);
        String thang;
        do {
            System.out.print("Nhap Thang: ");
            thang = dt.nextLine();
            String[] cat = thang.split(" ");
            try {
                int chon = Integer.parseInt(cat[0].trim());
                if (chon > 0 && chon <= 12) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        flag = -1;
        String nam;
        do {
            System.out.print("Nhap Nam: ");
            nam = dt.nextLine();
            String[] cat = nam.split(" ");
            try {
                int chon = Integer.parseInt(cat[0].trim());
                if (chon > 0) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        int tong = 0;
        DoanhThu date = new DoanhThu();
        for (DoanhThu h : getDs()) {
            String[] catNgay = h.getNgay().split("/");
            if (catNgay[1].equalsIgnoreCase(thang) && catNgay[2].equals(nam)) {
                tong = tong + h.getGiaTien();
            }
        }
        System.out.println("Doanh Thu Thang " + thang + "/" + nam + " la: " + tong);
    }
    
    public void baoCaoQuy() {
        int flag = -1;
        Scanner dt = new Scanner(System.in);
        int quy = 0;
        do {
            System.out.print("Nhap Quy (1, 2, 3, 4): ");
            String s = dt.nextLine();
            String[] cat = s.split(" ");
            try {
                quy = Integer.parseInt(cat[0].trim());
                if (quy > 0 && quy <= 4) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        flag = -1;
        String nam;
        do {
            System.out.print("Nhap Nam: ");
            nam = dt.nextLine();
            String[] cat = nam.split(" ");
            try {
                int chon = Integer.parseInt(cat[0].trim());
                if (chon > 0) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        int sum = 0;
        DoanhThu date = new DoanhThu();
        for (DoanhThu h : getDs()) {
            String[] catNgay = h.getNgay().split("/");
            if (catNgay[2].equals(nam)) {
                if (quy == 1) {
                    //for (DoanhThu h : getDs()) {
                    //String[] catNgay = h.getNgay().split("/");
                    if (Integer.parseInt(catNgay[1]) == Quy.QUY1.getM() || Integer.parseInt(catNgay[1]) == Quy.QUY1.getN() || Integer.parseInt(catNgay[1]) == Quy.QUY1.getK()) {
                        sum = sum + h.getGiaTien();
                    }
                }
                if (quy == 2) {
                    //for (DoanhThu h : getDs()) {
                    // String[] catNgay = h.getNgay().split("/");
                    if (Integer.parseInt(catNgay[1]) == Quy.QUY2.getM() || Integer.parseInt(catNgay[1]) == Quy.QUY2.getN() || Integer.parseInt(catNgay[1]) == Quy.QUY2.getK()) {
                        sum = sum + h.getGiaTien();
                    }
                }
                if (quy == 3) {
                    //for (DoanhThu h : getDs()) {
                    //String[] catNgay = h.getNgay().split("/");
                    if (Integer.parseInt(catNgay[1]) == Quy.QUY3.getM() || Integer.parseInt(catNgay[1]) == Quy.QUY3.getN() || Integer.parseInt(catNgay[1]) == Quy.QUY3.getK()) {
                        sum = sum + h.getGiaTien();
                    }
                }

                if (quy == 4) {
                    //for (DoanhThu h : getDs()) {
                    //String[] catNgay = h.getNgay().split("/");
                    if (Integer.parseInt(catNgay[1]) == Quy.QUY4.getM() || Integer.parseInt(catNgay[1]) == Quy.QUY4.getN() || Integer.parseInt(catNgay[1]) == Quy.QUY4.getK()) {
                        sum = sum + h.getGiaTien();
                    }
                }
            }
        }
        System.out.println("Doanh Thu Quy " + quy + " nam " + nam + " la:" + sum);
    }
}

class TanSoThue extends BaoCaoThongKe{
    private int dem = 0;
    
    private String form = "%-15s%-10s%n";
    private String[] header = new String[] {"Ten Sanh", "Tan So"};
    
    public TanSoThue(String tenSanh, String maSanh) {
        this.tenSanh = tenSanh;
        this.maSanh = maSanh;
        this.dem = 0;
    }
    public TanSoThue() {}
    
    public void inc(){
        this.dem++;
    }
    
    public void init() {
        for (SanhCuoi s: getCacSanh()){
            TanSoThue ts = new TanSoThue(s.getTenSanh(), s.getMaSanh());
            getTs().add(ts);
        }
    }
    
    public void thongKe() {
        try ( Scanner a = new Scanner(file)) {
            a.useDelimiter(",");
            while (a.hasNext()) {
                a.next();
                String maSanh = a.next();
                //  add tan so thue
                for (TanSoThue t: getTs()){
                    if (t.maSanh.equalsIgnoreCase(maSanh)){
                        t.inc();
                    }
                }
                a.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        sapXep();
        System.out.println("TAN SO CAC SANH CUOI DUOC THUE");
        this.hienThiFile();
    }
    
    public void thongKeNam() {
        int flag = -1;
        Scanner dt = new Scanner(System.in);
        String nam;
        do {
            System.out.print("Nhap Nam: ");
            nam = dt.nextLine();
            String[] cat = nam.split(" ");
            try {
                int chon = Integer.parseInt(cat[0].trim());
                if (chon > 0) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        try ( Scanner a = new Scanner(file)) {
            a.useDelimiter(",");
            while (a.hasNext()) {
                a.next();
                String maSanh = a.next();
                a.next();
                String ngay = a.next();
                String[] cat = ngay.split("/");
                //  add tan so thue
                if (cat[2].equalsIgnoreCase(nam)){
                    for (TanSoThue t: getTs()){
                        if (t.maSanh.equalsIgnoreCase(maSanh)){
                            t.inc();
                        }
                    }
                }
                a.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        sapXep();
        System.out.println("TAN SO CAC SANH CUOI DUOC THUE " + nam);
        this.hienThiFile();
    }

    public void sapXep(){
        getTs().sort((p1, p2) -> {
            int t1 = p1.dem;
            int t2 = p2.dem;
            return (t1 < t2 ? 1 : (t1 > t2 ? -1 : 0));
        });
    }
    
    public void hienThiFile(){
            System.out.format(this.form, this.header);
            for (Object[] row : toTable(getTs(), getTs().size())){
                System.out.format(this.form, row);
           }
    }

    //  tao bang
    private Object[][] toTable(List<TanSoThue> tst, int size) {
        final Object[][] table = new String[size][];
        for (int i = 0; i < size; i++){
            table[i] = new String[] {tst.get(i).tenSanh,
                                     String.valueOf(tst.get(i).dem)};
        }
        return table;
    }
}

public abstract class BaoCaoThongKe {

    protected String tenTiec;
    protected String maSanh;
    protected String tenSanh;
    protected String ngay;
    protected int giaTien;

    private final String url = "D:\\Data\\ThongTinThue.txt";
    protected File file = new File(url);


    private static List<TanSoThue> ts = new ArrayList<>();
    private static List<DoanhThu> ds = new ArrayList<>(); 
    
    public abstract void init();

    /**
     * @return the maSanh
     */
    public String getTenSanh() {
        return tenSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(String tenSanh) {
        this.tenSanh = tenSanh;
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

    /**
     * @return the ts
     */
    public static List<TanSoThue> getTs() {
        return ts;
    }

    /**
     * @param aTs the ts to set
     */
    public static void setTs(List<TanSoThue> aTs) {
        ts = aTs;
    }


    
}

enum Quy {
    QUY1(1, 2, 3),
    QUY2(4, 5, 6),
    QUY3(7, 8, 9),
    QUY4(10, 11, 12);
    private int m;
    private int n;
    private int k;

    private Quy(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
    }

    /**
     * @return the m
     */
    public int getM() {
        return m;
    }

    /**
     * @param m the m to set
     */
    public void setM(int m) {
        this.m = m;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(int k) {
        this.k = k;
    }
}
