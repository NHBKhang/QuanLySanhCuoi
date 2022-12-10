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
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class BuoiTiec {
    private String tenBuoiTiec;
    private SanhCuoi sanhCuoi;
    private long donGiaSanh;
    private String thoiDiem;
    private String ngayThue;
    private List<ThucAn> thucAn;
    private List<ThucUong> thucUong;
    private long donGiaMenu;
    private List<DichVu> dichVu;
    private long donGiaDV;
    
    private int soBan;
    private int buoi;
    
    private String url = "D:\\Data\\test.txt";
    private File file = new File(url);
    
    public BuoiTiec() throws FileNotFoundException{
        this.tenBuoiTiec = nextMa(docMa());
    }
    
        //        phuong thuc ho tro tao ma so moi
        private String nextMa(String ma){
            if (ma == null){
                ma = "P00001";
            }
            else {
                String[] catMa = ma.split("P") ;
                int nextMa = Integer.parseInt(catMa[1]) + 1;
                ma = String.format("P%05d", nextMa);
            }
            return ma;  
        }
        
        private String docMa() throws FileNotFoundException{
            String ma = null;
            if (getFile().length() != 0){
                try (Scanner scanner = new Scanner(getFile())){   
                    scanner.useDelimiter(",");
                    while (scanner.hasNextLine()){
                        ma = scanner.next();
                        scanner.nextLine();
                    }
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }
            return ma;
        }
    
    public void xuatHoaDon(){
        String dv = " VND", form = "%-30s%-10s\n", f = "%30s%5s\n";
        Locale vn = new Locale("en", "US");
        NumberFormat vnFormat = NumberFormat.getInstance(vn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%18s%12s\n", this.thoiDiem.toUpperCase(), this.ngayThue);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf(form, "Ten Sanh", "Gia Thue");
        System.out.printf(form, this.sanhCuoi.getTenSanh(), vnFormat.format(this.donGiaSanh) + dv);
        System.out.println("----------------------------------------------");
        System.out.printf(form, "Ten Mon", "Gia Mon");
        for (ThucAn s: thucAn){
            System.out.printf(form, s.tenMon, soBan + " x "+ vnFormat.format(s.giaMon) + dv);
        }
        for (ThucUong s: thucUong){
            System.out.printf(form, s.tenMon, soBan + " x "+  vnFormat.format(s.giaMon) + dv);
        }
        System.out.printf(f, "Tong: ", vnFormat.format(this.donGiaMenu) + dv);
        System.out.println("----------------------------------------------");
        System.out.printf(form, "Ten Dich Vu", "Gia Dich Vu");
        for (DichVu s: dichVu){
            s.xuat(form, vnFormat, dv);
        }
        System.out.printf(f, "Tong: ", vnFormat.format(this.donGiaDV) + dv);
        System.out.println("----------------------------------------------");
        System.out.printf(f, "Tong: ", vnFormat.format(this.donGiaDV + this.donGiaMenu + this.donGiaSanh) + dv);
    }
    
    public void them() throws IOException{
        try(FileWriter fileW = new FileWriter(getFile(), true)){
            try(PrintWriter print = new PrintWriter(fileW)){
                print.print(this.tenBuoiTiec + "," + this.sanhCuoi.getMaSanh() +
                        "," + this.sanhCuoi.getGia().getBuoi().getBuoi() + ",");
                for (int i = 0; i < thucAn.size(); i++){
                    print.print(thucAn.get(i).maMon);
                    if (thucAn.size() > i + 1)
                        print.print("-");
                    else
                        print.print(",");
                }
                for (int i = 0; i < thucUong.size(); i++){
                    print.print(thucUong.get(i).maMon);
                    if (thucUong.size() > i + 1)
                        print.print("-");
                    else
                        print.print(",");
                }
                for (DichVu d: dichVu){
                    print.print(d.toString());
                    if (dichVu.indexOf(d) + 1 < dichVu.size())
                        print.print("-");
                    else
                        print.print(",");
                }
                print.print(this.donGiaDV + this.donGiaMenu + this.donGiaSanh + ",");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * @return the tenBuoiTiec
     */
    public String getTenBuoiTiec() {
        return tenBuoiTiec;
    }

    /**
     * @return the sanhCuoi
     */
    public SanhCuoi getSanhCuoi() {
        return sanhCuoi;
    }

    /**
     * @param sanhCuoi the sanhCuoi to set
     */
    public void setSanhCuoi(SanhCuoi sanhCuoi) {
        this.sanhCuoi = sanhCuoi;
    }

    /**
     * @return the donGiaSanh
     */
    public long getDonGiaSanh() {
        return donGiaSanh;
    }

    /**
     * @param donGiaSanh the donGiaSanh to set
     */
    public void setDonGiaSanh(long donGiaSanh) {
        this.donGiaSanh = donGiaSanh;
    }

    /**
     * @return the thoiDiem
     */
    public String getThoiDiem() {
        return thoiDiem;
    }

    /**
     * @param thoiDiem the thoiDiem to set
     */
    public void setThoiDiem(String thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    /**
     * @return the ngayThue
     */
    public String getNgayThue() {
        return ngayThue;
    }

    /**
     * @param ngayThue the ngayThue to set
     */
    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    /**
     * @return the donGiaMenu
     */
    public long getDonGiaMenu() {
        return donGiaMenu;
    }

    /**
     * @param donGiaMenu the donGiaMenu to set
     */
    public void setDonGiaMenu(long donGiaMenu) {
        this.donGiaMenu = donGiaMenu;
    }

    /**
     * @return the dichVu
     */
    public List<DichVu> getDichVu() {
        return dichVu;
    }

    /**
     * @param dichVu the dichVu to set
     */
    public void setDichVu(List<DichVu> dichVu) {
        this.dichVu = dichVu;
    }

    /**
     * @return the donGiaDV
     */
    public long getDonGiaDV() {
        return donGiaDV;
    }

    /**
     * @param donGiaDV the donGiaDV to set
     */
    public void setDonGiaDV(long donGiaDV) {
        this.donGiaDV = donGiaDV;
    }

    /**
     * @return the thucAn
     */
    public List<ThucAn> getThucAn() {
        return thucAn;
    }

    /**
     * @param thucAn the thucAn to set
     */
    public void setThucAn(List<ThucAn> thucAn) {
        this.thucAn = thucAn;
    }

    /**
     * @return the thucUong
     */
    public List<ThucUong> getThucUong() {
        return thucUong;
    }

    /**
     * @param thucUong the thucUong to set
     */
    public void setThucUong(List<ThucUong> thucUong) {
        this.thucUong = thucUong;
    }

    /**
     * @return the soBan
     */
    public int getSoBan() {
        return soBan;
    }

    /**
     * @param soBan the soBan to set
     */
    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    /**
     * @return the buoi
     */
    public int getBuoi() {
        return buoi;
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
    
}
