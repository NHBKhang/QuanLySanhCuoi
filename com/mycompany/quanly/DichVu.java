/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class DichVu {
    private String maDichVu;
    
    private String tenDichVu;
    
    private long giaDichVu;
    
    private String ghiChu;
    
    private List<DichVu> cacDichVu = new ArrayList<>();
    
    private String url = "D:\\Data\\DichVu.txt";
    private File file = new File(url);
    
    //phuong thuc khoi tao khong tham so
    public DichVu(){}
    
    //phuong thuc khoi tao dich vu da co
    public DichVu(String maDichVu, String tenDichVu, long giaDichVu){
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
    }
    
    //phuong thuc khoi tao dich vu moi
    public DichVu(String tenDichVu, long giaDichVu, String ghiChu) throws FileNotFoundException{
        this.maDichVu = nextMa(docMa());
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.ghiChu = ghiChu;
    }
    
    //phuong thuc khoi tao dich vu da co tu file
    public DichVu(String maDichVu, String tenDichVu, long giaDichVu, String ghiChu){
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
        this.ghiChu = ghiChu;
    }
    
    //        phuong thuc ho tro tao ma so moi
        private String nextMa(String ma){
            if (ma == null){
                ma = "D004";
            }
            else {
                String[] catMa = ma.split("D") ;
                int nextMa = Integer.parseInt(catMa[1]) + 1;
                ma = String.format("D%03d", nextMa);
            }
            return ma;  
        }
        
        private String docMa() throws FileNotFoundException{
            String ma = null;
            if (file.length() != 0){
                try (Scanner scanner = new Scanner(file)){   
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

    /**
     * @return the maDichVu
     */
    public String getMaDichVu() {
        return maDichVu;
    }

    /**
     * @return the tenDichVu
     */
    public String getTenDichVu() {
        return tenDichVu;
    }

    /**
     * @param tenDichVu the tenDichVu to set
     */
    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    /**
     * @return the giaDichVu
     */
    public long getGiaDichVu() {
        return giaDichVu;
    }

    /**
     * @param giaDichVu the giaDichVu to set
     */
    public void setGiaDichVu(long giaDichVu) {
        this.giaDichVu = giaDichVu;
    }

    /**
     * @return the cacDichVu
     */
    public List<DichVu> getCacDichVu() {
        return cacDichVu;
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

    public void init() throws FileNotFoundException {
        DichVu dv = new DichVu();
        dv.getCacDichVu().add(new Karaoke());
        dv.getCacDichVu().add(new TrangTriPhoiCanh());
        dv.getCacDichVu().add(new ThueCaSi());
        try (Scanner scanner = new Scanner(dv.getFile())){
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                String maDV = scanner.next();
                String tenDV = scanner.next();
                long gia = Long.parseLong(scanner.next());
                String ghiChu = scanner.next();
                DichVu dichVu = new DichVu(maDV, tenDV, gia, ghiChu);
                dv.getCacDichVu().add(dichVu);
                scanner.nextLine();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    
}
