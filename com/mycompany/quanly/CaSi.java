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
    }

    public void capNhat(String noiDung, int viTriSua) throws IOException {
        if (viTriSua == 1) {
            this.setTenCaSi(noiDung.toUpperCase());
        } else {
            this.setGiaCaSi(Long.parseLong(noiDung));
        }
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
    
    public static List<CaSi> traCuu(String noiDung, int i) throws FileNotFoundException {
        List<CaSi> cs = new ArrayList<>();
        for (CaSi singer : cacCaSi) {
            if (i == 1) {
                if (singer.tenCaSi.toLowerCase().contains(noiDung.toLowerCase())) {
                    cs.add(singer);
                }
            } 
            else {
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
      
    //   nhap cho quan ly
    public void nhap(){
        int chon = 0, flag = -1;
        Scanner scanner = new Scanner(System.in);
        init();
        hienThiFile(this);
        System.out.println("1.Them Ca Si | 2.Cap nhat Ca Si | 3.Xoa Ca Si | 4.Tra cuu Ca Si");
        do {
            System.out.print("Chon: ");
            String s = scanner.nextLine();
            String[] cat = s.split(" ");
            try {
                chon = Integer.parseInt(cat[0]);
                if (chon > 0 && chon < 5) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        //  them
        CaSi caSi = new CaSi();
        if (chon == 1){
            do{
                flag = -1;
                System.out.print("Nhap ten ca si: ");
                String tenDV = scanner.nextLine().toUpperCase();
                long giaDV = 0;
                String[] cat;
                do{
                    System.out.print("Nhap gia ca si: ");
                    String a = scanner.nextLine();            
                    cat = a.split(" ");
                    try {
                        giaDV = Long.parseLong(cat[0].trim());
                        if (giaDV > 10000) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                try {
                    CaSi cs = new CaSi(tenDV, giaDV);
                    caSi = cs;
                    System.out.println("- Tao ca si moi thanh cong -");
                } catch (FileNotFoundException ex) {
                    System.out.println("- Tao ca si moi khong thanh cong -");
                }
                System.out.println("Luu lua chon?\t1.Luu | 2.Khong luu va nhap lai | 3.Thoat");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon <= 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    try {
                        caSi.them();
                        System.out.println("- Luu ca si thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Luu ca si khong thanh cong -");
                    }
                    break;
                }
                else if (chon == 2)
                    continue;
                else
                    return;
            }
        while (true);
        }
        //  cap nhat
        else if (chon == 2){
            System.out.println("1.Cap nhat 1 thuoc tinh | 2.Cap nhat 1 dong");
            do {
                flag = -1;
                System.out.print("Chon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(cat[0].trim());
                    if (chon > 0 && chon < 3) flag = 0;
                    else flag = -1;
                } catch (Exception e){
                    flag = -1;
                }                
            }
            while (flag == -1);
            do{
                flag = -1;
                System.out.print("Chon ca si (vd:1): ");
                String ma = scanner.nextLine().toUpperCase();
                String[] cat = ma.split(" ");
                caSi = tim(cat[0].trim());
                if (caSi.maCaSi != null || caSi.maCaSi == "") flag = 0;
                else flag = -1;
            }
            while (flag == -1);
            //  cap nhat 1 thuoc tinh
            String[] cat;
            if (chon == 1){
                System.out.println("1.Ten Ca Si | 2.Gia Ca Si");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                do{
                    flag = -1;
                    do{
                        flag = -1;
                        System.out.print("Nhap noi dung sua: ");
                        String s = scanner.nextLine().toUpperCase();
                        try{
                            caSi.capNhat(s.trim(), chon);
                            flag = 0;
                        } catch (Exception e){
                            System.out.println("- Nhap sai thong tin -");
                            flag = -1;
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat ca si " + caSi.tenCaSi + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
                    do {
                        flag = -1;
                        System.out.print("Chon: ");
                        String st = scanner.nextLine();
                        cat = st.split(" ");
                        try {
                            chon = Integer.parseInt(cat[0].trim());
                            if (chon > 0 && chon <= 3) flag = 0;
                            else flag = -1;
                        } catch (Exception e){
                            flag = -1;
                        }                
                    }
                    while (flag == -1);
                    if (chon == 1){
                        try{
                            rewriteFile();
                            flag = 0;
                            System.out.println("- Cap nhat ca si thanh cong -");
                        } catch (Exception e){
                            System.out.println("- Cap nhat ca si khong thanh cong -");
                            flag = -1;
                        }
                        break;
                    }
                    else if (chon == 2)
                        continue;
                    else
                        return;
                }
                while (flag == -1);
            }
            //  cap nhat 1 dong
            else{
                do{
                    do{
                        flag = -1;
                        System.out.print("Nhap noi dung sua (vd: Karik, 1200000): ");
                        String s = scanner.nextLine().toLowerCase();
                        cat = s.split(",");
                        if (cat.length != 4) flag = -1;
                        else{
                            try{
                                caSi.capNhat(s.trim());
                                flag = 0;
                            } catch (Exception e){
                                System.out.println("- Nhap sai thong tin -");
                                flag = -1;
                            }
                        }
                    }
                    while (flag == -1);
                    System.out.println("Cap nhat dich vu " + caSi.tenCaSi + "?\t1.Cap nhat | 2.Khong va nhap lai | 3.Thoat");
                    do {
                        flag = -1;
                        System.out.print("Chon: ");
                        String st = scanner.nextLine();
                        cat = st.split(" ");
                        try {
                            chon = Integer.parseInt(cat[0]);
                            if (chon > 0 && chon <= 3) flag = 0;
                            else flag = -1;
                        } catch (Exception e){
                            flag = -1;
                        }                
                    }
                    while (flag == -1);
                    if (chon == 1){
                        try {
                            rewriteFile();
                            System.out.println("- Cap nhat ca si thanh cong -");
                        } catch (IOException ex) {
                            System.out.println("Cap nhat ca si khong thanh cong");
                        }
                    }
                    else if (chon == 2)
                        flag = -1;
                    else
                        return;
                }
                while (flag == -1);                
            }
        }
        //   xoa sanh cuoi
        else if (chon == 3){
            do{
                do{
                    flag = -1;
                    System.out.print("Chon ca si (vd:1): ");
                    String ma = scanner.nextLine().toUpperCase();
                    String[] cat = ma.split(" ");
                    caSi = tim(cat[0].trim());
                    if (caSi.maCaSi != null || caSi.maCaSi == "") flag = 0;
                    else flag = -1;
                }
                while (flag == -1);
                System.out.println("Xoa ca si " + caSi.tenCaSi + "?\t1.Xoa | 2.Khong va nhap lai | 3.Thoat");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0]);
                        if (chon > 0 && chon <= 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    try {
                        caSi.xoa();
                        System.out.println("- Xoa ca si thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Xoa ca si khong thanh cong -");
                    }
                    break;
                }
                else if (chon == 2)
                    continue;
                else
                    return;
                }
            while (true);
        }
        //  tra cuu
        else{
            do{
                System.out.println("1.Ten Ca Si | 2.Gia Ca Si");
                do {
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0].trim());
                        if (chon > 0 && chon < 3) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                System.out.print("Nhap ten ca si can tim: ");
                String s = scanner.nextLine();
                try{
                    List <CaSi> cs = traCuu(s, chon);
                    if (cs.size() != 0){
                        hienThi(cs, this);
                        flag = 0;
                    }
                    else{
                        System.out.println("- Khong tim thay ca si nao phu hop -");
                        flag = -1;
                    }
                } catch (Exception e){
                    System.out.println("- Nhap sai thong tin -");
                    flag = -1;
                }
            }
            while (flag == -1);
            System.out.println("Tim thanh cong\n1.Tim lai | 2.Thoat tim kiem");
            do {
                flag = -1;
                System.out.print("Chon: ");
                String st = scanner.nextLine();
                String[] cat = st.split(" ");
                try {
                    chon = Integer.parseInt(cat[0].trim());
                    if (chon > 0 && chon < 3) flag = 0;
                    else flag = -1;
                } catch (Exception e){
                    flag = -1;
                }                
            }
            while (flag == -1);
            if (chon == 1){
                flag = -1;
            }
            else
                flag = 0;
        }
        while (flag == -1);
    }
}
