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
public class CaSi {

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
    private String maCaSi;
    private String tenCaSi;
    private long giaCaSi;
    private String url="D:\\Data\\CaSi.txt";
    private File file = new File(url);
    private String form = "%-10s%-25s%-10s%n";
    private String[] header = new String[] {"Ma Ca Si","Ten Ca Si","Gia Ca Si"};
    
    public CaSi(String maCaSi, String tenCaSi, long giaCaSi){
        this.maCaSi=maCaSi;
        this.tenCaSi = tenCaSi;
        this.giaCaSi=giaCaSi;
    }
    
    public CaSi (String tenCaSi, long giaCaSi) throws FileNotFoundException{
        this.maCaSi = nextMaCaSi(docMaCaSi());
        this.tenCaSi = tenCaSi;
        this.giaCaSi=giaCaSi;
    }
    
    private static List<CaSi> cacCaSi = new ArrayList<>();
    
    public void init() {
        try ( Scanner c = new Scanner(file)) {
            c.useDelimiter(",");
            while (c.hasNext()) {
                String maCaSi = c.next();
                String tenCaSi = c.next();
                long giaCaSi = Long.parseLong(c.next());
                CaSi e = new CaSi(maCaSi, tenCaSi, giaCaSi);
                cacCaSi.add(e);
                c.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void them() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(maCaSi+ ",");
                writer.print(this.tenCaSi + ",");
                writer.print(Long.toString(this.getGiaCaSi()) + ",");
                writer.println();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        cacCaSi.add(this);
    }
    
     public void capNhat(String noiDung) throws IOException {
        String[] a = noiDung.split(",");
        for (int i = 0; i < a.length; i++) {
            this.capNhat(a[i].trim(), i + 1);
        }
        rewriteFile();
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setTenCaSi(noiDung.toUpperCase());
        } else {
            this.setGiaCaSi(Long.parseLong(noiDung));
        }
        rewriteFile();
    }
    
    public CaSi tim(String maCaSi) {
        CaSi singer = new CaSi();
        for (CaSi y : cacCaSi) {
            if (y.getMaCaSi().toLowerCase().equals(maCaSi.toLowerCase())) {
                singer = y;
                break;
            }
        }
        return singer;
    }

    public void xoa() throws IOException {
        cacCaSi.remove(this);
        rewriteFile();
    }
    
    public static List<CaSi> tim(String noiDung, int i) throws FileNotFoundException {
        List<CaSi> cs = new ArrayList<>();
        for (CaSi singer : cacCaSi) {
            if (i == 0) {
                if (singer.maCaSi.toLowerCase().contains(noiDung.toLowerCase())) {
                    cs.add(singer);
                }
            } else if (i == 1) {
                if (singer.tenCaSi.toLowerCase().contains(noiDung.toLowerCase())) {
                    cs.add(singer);
                }
            } else {
                if (singer.getGiaCaSi() >= Long.parseLong(noiDung)) {
                    cs.add(singer);
                }
            }

        }
        return cs;
    }
    
    private String nextMaCaSi(String maCaSi) {
        if (maCaSi == null) {
            maCaSi = "1";
        } else {
            int nextMa = (Integer.parseInt(maCaSi) + 1);
            maCaSi = Integer.toString(nextMa);
        }
        return maCaSi;
    }

    private String docMaCaSi() throws FileNotFoundException {
        String maCaSi = null;
        File file = new File(this.url);
        if (file.length() != 0) {
            try ( Scanner scanner = new Scanner(file)) {
                scanner.useDelimiter(",");
                while (scanner.hasNextLine()) {
                    maCaSi = scanner.next();
                    scanner.nextLine();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return maCaSi;
    }
    
    public void rewriteFile() throws IOException {
        try ( FileWriter fileWriter = new FileWriter(file, false)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                for (CaSi y :cacCaSi) {
                    writer.print(y.getMaCaSi() + ",");
                    writer.print(y.getTenCaSi() + ",");
                    writer.print(Long.toString(y.getGiaCaSi()) + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public CaSi(){}
    
    public static void hienThi(List<CaSi> caSi, CaSi cs){
            System.out.format(cs.getForm(), cs.getHeader());
            for (Object[] row : toTable(caSi, caSi.size())){
                System.out.format(cs.getForm(), row);
            }
        }
        
        public static void hienThi(CaSi caSi){
            System.out.format(caSi.getForm(), caSi.getHeader());
            Object[] row = new String[] {caSi.maCaSi,
                                          caSi.tenCaSi,
                                          String.valueOf(caSi.giaCaSi)};
            System.out.format(caSi.getForm(), row);
        }
        
        public static void hienThiFile(CaSi cs){
                System.out.format(cs.getForm(), cs.getHeader());
                for (Object[] row : toTable(getCacCaSi(), getCacCaSi().size())){
                    System.out.format(cs.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<CaSi> caSi, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {caSi.get(i).maCaSi,
                                         caSi.get(i).tenCaSi,
                                         String.valueOf(caSi.get(i).giaCaSi)};
            }
            return table;
        }
    /**
     * @return the maCaSi
     */
    public String getMaCaSi() {
        return maCaSi;
    }

    /**
     * @param maCaSi the maCaSi to set
     */
    public void setMaCaSi(int maCaSi) {
        this.setMaCaSi(maCaSi);
    }

    /**
     * @return the tenCaSi
     */
    public String getTenCaSi() {
        return tenCaSi;
    }

    /**
     * @param tenCaSi the tenCaSi to set
     */
    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the cacCaSi
     */
    public static List<CaSi> getCacCaSi() {
        return cacCaSi;
    }

    /**
     * @param aCacCaSi the cacCaSi to set
     */
    public static void setCacCaSi(List<CaSi> aCacCaSi) {
        cacCaSi = aCacCaSi;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param maCaSi the maCaSi to set
     */
    public void setMaCaSi(String maCaSi) {
        this.maCaSi = maCaSi;
    }

    /**
     * @return the giaCaSi
     */
    public long getGiaCaSi() {
        return giaCaSi;
    }

    /**
     * @param giaCaSi the giaCaSi to set
     */
    public void setGiaCaSi(long giaCaSi) {
        this.giaCaSi = giaCaSi;
    }
    
      public List<CaSi> Nhap() {
        Scanner scanner = new Scanner(System.in);
        String[] list; 
        String t;
        int flag = -1;
        List<CaSi> caSi = new ArrayList<>();
        CaSi cs = new CaSi();
        cs.init();
        hienThiFile(cs);
        System.out.println("(chon toi thieu 1 ca si va toi da 5 ca si)");
        do{
            System.out.print("Nhap ca si muon thue (vd: 1,2,..): ");
            t = scanner.nextLine();
            list = t.split(",");
            if (list.length > 0 && list.length <= 5){
                try{
                    int[] ma = new int[5];
                    for (int i = 0; i < list.length; i++){
                        ma[i] = Integer.parseInt(list[i].trim());
                        for (CaSi c: getCacCaSi()){
                            if (ma[i] == Integer.parseInt(c.maCaSi)){
                                caSi.add(c);
                                flag = 0;
                            }
                        }
                    }
                } catch (Exception e){
                    flag = -1;
                }
            }
            else 
                flag = -1;
        }
        while (flag == -1);
        return caSi;
    }
}
