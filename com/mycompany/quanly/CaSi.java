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
public class CaSi {
    private String maCaSi;
    private String tenCaSi;
    private int giaCaSi;
    private String url="C:\\Data\\CaSi";
    private File file = new File(getUrl());
    
    public CaSi(String maCaSi,String tenCaSi,int giaCaSi){
        this.maCaSi=maCaSi;
        this.tenCaSi = tenCaSi;
        this.giaCaSi=giaCaSi;
    }
    
    private static List<CaSi> cacCaSi = new ArrayList<>();
    
    public void init() {
        CaSi cs = new CaSi();
        try ( Scanner c = new Scanner(getFile())) {
            c.useDelimiter(",");
            while (c.hasNext()) {
                String maCaSi = c.next();
                String tenCaSi = c.next();
                int giaCaSi = c.nextInt();
                CaSi e = new CaSi(maCaSi, tenCaSi, giaCaSi);
                cacCaSi.add(e);
                c.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void themCaSi() throws FileNotFoundException, IOException {
        try ( FileWriter fileWriter = new FileWriter(getFile(), true)) {
            try ( PrintWriter writer = new PrintWriter(fileWriter)) {
                writer.print(this.getMaCaSi()+ ",");
                writer.print(this.getTenCaSi() + ",");
                writer.print(Integer.toString(this.getGiaCaSi()) + ",");
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
            this.capNhat(a[i], i + 1);
        }
        rewriteFile();
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setMaCaSi(noiDung.toUpperCase());
        } else if (viTriSua == 2) {
            this.setTenCaSi(noiDung.toUpperCase());
        } else {
            this.setGiaCaSi(Integer.parseInt(noiDung));
        }
        rewriteFile();
    }
    
    public CaSi timXoa(String maCaSi) {
        CaSi singer = new CaSi();
        for (CaSi y : cacCaSi) {
            if (y.getMaCaSi().toLowerCase().equals(maCaSi.toLowerCase())) {
                singer = y;
            }
        }
        return singer;
    }

    public void xoaCaSi(String ma) throws IOException {
        for (CaSi y : cacCaSi ) {
            if (y.maCaSi.toLowerCase().equals(ma.toLowerCase())) {
                System.out.println(y.maCaSi.toLowerCase().equals(ma.toLowerCase()));
                System.out.println(y.maCaSi);
                cacCaSi.remove(y);
                break;
            }
        }
        rewriteFile();
    }
    
    public static List<CaSi> timCaSi(String noiDung, int i) throws FileNotFoundException {
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
                if (singer.giaCaSi >= Integer.parseInt(noiDung)) {
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
                    writer.print(Integer.toString(y.getGiaCaSi()) + ",");
                    writer.println();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public CaSi(){
        this(null,null,0);
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
     * @return the giaCaSi
     */
    public int getGiaCaSi() {
        return giaCaSi;
    }

    /**
     * @param giaCaSi the giaCaSi to set
     */
    public void setGiaCaSi(int giaCaSi) {
        this.giaCaSi = giaCaSi;
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
}
