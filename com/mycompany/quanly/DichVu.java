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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class DichVu {
    protected String maDichVu;
    
    protected String tenDichVu;
    
    protected long giaDichVu;
    
    private String ghiChu;
    
    private static List<DichVu> cacDichVu = new ArrayList<>();
    
    private String url = "D:\\Data\\DichVu.txt";
    private File file = new File(url);
            
    private String form = "%-15s%-30s%-10s%n";
    private String[] header = new String[] {"Ma Dich Vu","Ten Dich Vu","Gia Dich Vu"};
    
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
                ma = "D001";
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
        
        public static final void hienThi(List<DichVu> dichVu, DichVu dv){
            System.out.format(dv.getForm(), dv.getHeader());
            for (Object[] row : toTable(dichVu, dichVu.size())){
                System.out.format(dv.getForm(), row);
            }
        }
        
        public static final void hienThi(DichVu dichVu){
            System.out.format(dichVu.getForm(), dichVu.getHeader());
            Object[] row = new String[] {dichVu.maDichVu,
                                         dichVu.tenDichVu,
                                         String.valueOf(dichVu.giaDichVu)};
            System.out.format(dichVu.getForm(), row);
        }
        
        public static final void hienThiFile(DichVu dv){
                System.out.format(dv.getForm(), dv.getHeader());
                for (Object[] row : toTable(getCacDichVu(),getCacDichVu().size())){
                    System.out.format(dv.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<DichVu> dichVu, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {dichVu.get(i).maDichVu,
                                         dichVu.get(i).tenDichVu,
                                         (dichVu.get(i).giaDichVu == 0? "- - - -" :
                                         String.valueOf(dichVu.get(i).giaDichVu))};
            }
            return table;
        }
        
    //  phuong thuc chuc nang cua dich vu
	public void them() throws FileNotFoundException, IOException {
            try (FileWriter fileWriter = new FileWriter(getFile(), true)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    writer.print(this.maDichVu + ",");
                    writer.print(this.tenDichVu + ",");
                    writer.print(Long.toString(this.giaDichVu) + ",");
                    writer.print(this.getGhiChu() + ",");
                    writer.println();
                }
            } catch (Exception e){
                System.out.println(e);
            }
            cacDichVu.add(this);
	}
        
        public void capNhat(String noiDung) throws IOException {
            String[] string = noiDung.split(",");
            for (String s: string){
                s.trim();
            }
            for (int i = 0; i < string.length; i++){
                this.capNhat(string[i], i+1);
            }
            rewriteFile();
	}
        
	public void capNhat(String noiDung, int viTriSua) throws IOException {
            if (viTriSua == 1){
                this.tenDichVu = noiDung.toUpperCase();
            }
            else if (viTriSua == 2){
                this.giaDichVu = Long.parseLong(noiDung);
            }
            else{
                this.setGhiChu(noiDung);
            }
            rewriteFile();
	}
        
        //  phuong thuc ho tro cap nhat
        public DichVu tim(String maDV){
            DichVu dv = new DichVu();
            for (DichVu s: cacDichVu){
                if (s.maDichVu.toLowerCase().equals(maDV.toLowerCase())){
                    dv = s;
                    break;
                }
            }
            return dv;
        }
        //
        
	public void xoa() throws IOException {
            cacDichVu.remove(this);
            rewriteFile();
	}
        
        // ghi lai file / overwrite file
        public void rewriteFile() throws IOException{
            try (FileWriter fileWriter = new FileWriter(file, false)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    for (DichVu s: cacDichVu){
                        writer.print(s.maDichVu + ",");
                        writer.print(s.tenDichVu + ",");
                        writer.print(Long.toString(s.giaDichVu) + ",");
                        writer.print(s.getGhiChu() +",");
                        writer.println();
                    }
                }
            } catch (Exception e){
                 System.out.println(e);
            }
        }

	public List<DichVu> traCuu(String noiDung){
            List<DichVu> dv = new ArrayList<>();
            for (DichVu d: cacDichVu){
                if (d.tenDichVu.toLowerCase().contains(noiDung.toLowerCase())){
                    dv.add(d);
                }
            }
            return dv;
	}    
        
    public void init() throws FileNotFoundException {
        cacDichVu.add(new Karaoke());
        cacDichVu.add(new TrangTriPhoiCanh());
        cacDichVu.add(new ThueCaSi());
        try (Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                String maDV = scanner.next();
                String tenDV = scanner.next();
                long gia = Long.parseLong(scanner.next());
                String ghiChu = scanner.next();
                DichVu dichVu = new DichVu(maDV, tenDV, gia, ghiChu);
                cacDichVu.add(dichVu);
                scanner.nextLine();
            }
        } catch (Exception e){
            System.out.println(e);
        }
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
    public static List<DichVu> getCacDichVu() {
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

    /**
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * @return the header
     */
    public String[] getHeader() {
        return header;
    }

    List<DichVu> Nhap() {
        int flag = -1, chon, soBan;
        String ma, date, mon, dvu;
        Scanner scanner = new Scanner(System.in);
        List<DichVu> dichVu = new ArrayList<>();
        DichVu dv = new DichVu();
        hienThiFile(dv);
        scanner.useDelimiter("\n");
        System.out.println("(Nhap so 0 neu khong chon dich vu di kem)");
        do{
            do {
                System.out.print("Chon dich vu (vd: D001,..): ");
                dvu = scanner.next().toUpperCase();
                String[] cacDV = dvu.split(",");
                int i = 1;
                try {
                    i = Integer.parseInt(cacDV[0].trim());
                } catch (Exception e){
                    flag = -1;
                }
                if (i == 0) flag = 0;
                else {
                    for (String s: cacDV){
                        for (DichVu d: getCacDichVu()){
                            if (s.trim().equals(d.getMaDichVu())){
                                dichVu.add(d);
                                flag = 0;
                            }
                        }
                    }
                }
            }
            while (flag == -1);
            do{
                System.out.print("Luu lua chon? \t 1.Yes  |  2.No\nChon: ");
                String s = scanner.nextLine();
                try {
                    chon = Integer.parseInt(s);
                } catch (Exception e){
                    chon = -1;
                }
            }
            while (chon!=1&&chon!=2);
            if (chon ==1)
                break;
            else
                continue;
        }
        while (true);
        return dichVu;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    void xuat(String form, NumberFormat vnFormat, String dv) {
        System.out.printf(form, this.tenDichVu, vnFormat.format(this.giaDichVu) + dv);
    }
    
    
    
}
