package com.mycompany.quanly;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SanhCuoi {
	private String maSanh = null;

	private String tenSanh;

	private int viTri;

	private long sucChua;
        
        private long giaThue;
        
        private final String url = "D:\\Data\\SanhCuoi.txt";
        private File file = new File (getUrl());
        
        private final String ngatFile =",";
        
        
        private static List<SanhCuoi> cacSanh = new ArrayList<>();
        
        private String form = "%-10s%-15s%-10s%-10s%-10s%n";
        private String[] header = new String[] {"Ma Sanh","Ten Sanh","Vi Tri","Suc Chua","Gia Thue"};
        
//        phuong thuc chuc nang cua sanh cuoi
	public void them() throws FileNotFoundException, IOException {
            try (FileWriter fileWriter = new FileWriter(getFile(), true)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    writer.print(this.maSanh + ngatFile);
                    writer.print(this.tenSanh + ngatFile);
                    writer.print(Integer.toString(this.viTri) + ngatFile);
                    writer.print(Long.toString(this.sucChua) + ngatFile);
                    writer.print(this.getGiaThue() + ngatFile);
                    writer.println();
                }
            } catch (Exception e){
                System.out.println(e);
            }
            cacSanh.add(this);
	}
        
        public void capNhat(String noiDung) throws IOException {
            String[] string = noiDung.split(this.ngatFile);
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
                this.tenSanh = noiDung.toUpperCase();
            }
            else if (viTriSua == 2){
                this.viTri = Integer.parseInt(noiDung);
            }
            else if (viTriSua == 3){
                this.sucChua = Long.parseLong(noiDung);
            }
            else{
                this.giaThue = Long.parseLong(noiDung);
            }
            rewriteFile();
	}
        
        //  phuong thuc ho tro cap nhat
        public SanhCuoi tim(String maSanh){
            SanhCuoi sanh = new SanhCuoi();
            for (SanhCuoi s: cacSanh){
                if (s.maSanh.toLowerCase().equals(maSanh.toLowerCase())){
                    sanh = s;
                }
            }
            return sanh;
        }
        //
        
	public void xoa(String ma) throws IOException {
            cacSanh.remove(this);
            rewriteFile();
	}
        
        // ghi lai file / overwrite file
        public void rewriteFile() throws IOException{
            try (FileWriter fileWriter = new FileWriter(file, false)){
                try (PrintWriter writer = new PrintWriter(fileWriter)){
                    for (SanhCuoi s: cacSanh){
                        writer.print(s.maSanh + ngatFile);
                        writer.print(s.tenSanh + ngatFile);
                        writer.print(Integer.toString(s.viTri) + ngatFile);
                        writer.print(Long.toString(s.sucChua) + ngatFile);
                        writer.print(s.getGiaThue() + ngatFile);
                        writer.println();
                    }
                }
            } catch (Exception e){
                 System.out.println(e);
            }
        }

	public List<SanhCuoi> traCuu(String noiDung, int i){
            List<SanhCuoi> sc = new ArrayList<>();
            for (SanhCuoi sanh: cacSanh){
                if (i == 0){
                    if (sanh.tenSanh.toLowerCase().contains(noiDung.toLowerCase())){
                        sc.add(sanh);
                    }
                }
                else if (i == 1){
                    if (sanh.viTri == Integer.parseInt(noiDung)){
                        sc.add(sanh);
                    }
                }
                else {
                    if (sanh.sucChua >= Long.parseLong(noiDung)){
                        sc.add(sanh);
                    }
                }
            }
            return sc;
	}
        // ket thuc chuc nang
        
        public static void hienThi(List<SanhCuoi> sanhCuoi, SanhCuoi sc){
            System.out.format(sc.getForm(), sc.getHeader());
            for (Object[] row : toTable(sanhCuoi, sanhCuoi.size())){
                System.out.format(sc.getForm(), row);
            }
        }
        
        public static void hienThi(SanhCuoi sanhCuoi){
            System.out.format(sanhCuoi.getForm(), sanhCuoi.getHeader());
            Object[] sanh = new String[] {sanhCuoi.maSanh,
                                          sanhCuoi.tenSanh,
                                          String.valueOf(sanhCuoi.viTri),
                                          String.valueOf(sanhCuoi.sucChua),
                                          String.valueOf(sanhCuoi.giaThue)};
            System.out.format(sanhCuoi.getForm(), sanh);
        }
        
        public static void hienThiFile(SanhCuoi sanhCuoi){
                System.out.format(sanhCuoi.getForm(), sanhCuoi.getHeader());
                for (Object[] row : toTable(getCacSanh(), getCacSanh().size())){
                    System.out.format(sanhCuoi.getForm(), row);
               }
        }
        
        //  tao bang
        private static Object[][] toTable(List<SanhCuoi> sanh, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {sanh.get(i).maSanh,
                                         sanh.get(i).tenSanh,
                                         String.valueOf(sanh.get(i).viTri),
                                         String.valueOf(sanh.get(i).sucChua),
                                         String.valueOf(sanh.get(i).giaThue)};
            }
            return table;
        }
        

        
        
    //      phuong thuc khoi tao sanh cuoi da co
            public SanhCuoi(String maSanhCuoi, String tenSanh, int viTri, long sucChua, long giaThue){
                this.maSanh = maSanhCuoi.toUpperCase();
                this.tenSanh = tenSanh.toUpperCase();
                this.viTri = viTri;
                this.sucChua = sucChua;
                this.giaThue = giaThue;

            }        
        
    //      phuong thuc khoi tao khong tham so
            public SanhCuoi(){}

        
    //      phuong thuc khoi tao sanh cuoi moi
            public SanhCuoi(String tenSanh, int viTri, long sucChua, long giaThue) throws FileNotFoundException{
                this.maSanh = nextMaSanh(docMaSanh());
                this.tenSanh = tenSanh.toUpperCase();
                this.viTri = viTri;
                this.sucChua = sucChua;
                this.giaThue = giaThue;
            }
        
//        phuong thuc ho tro tao ma so moi
        private String nextMaSanh(String maSanh){
            if (maSanh == null){
                maSanh = "S001";
            }
            else {
                String[] catMaSanh = maSanh.split("S") ;
                int nextMa = Integer.parseInt(catMaSanh[1]) + 1;
                maSanh = String.format("S%03d", nextMa);
            }
            return maSanh;  
        }
        
        private String docMaSanh() throws FileNotFoundException{
            String maSanh = null;
            File file = new File(this.url);
            if (file.length() != 0){
                try (Scanner scanner = new Scanner(file)){   
                    scanner.useDelimiter(this.ngatFile);
                    while (scanner.hasNextLine()){
                        maSanh = scanner.next();
                        scanner.nextLine();
                    }
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }
            return maSanh;
        }
        
        

    /**
     * @return the maSanh
     */
    public String getMaSanh() {
        return maSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    /**
     * @return the tenSanh
     */
    public String getTenSanh() {
        return tenSanh;
    }

    /**
     * @param tenSanh the tenSanh to set
     */
    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    /**
     * @return the viTri
     */
    public int getViTri() {
        return viTri;
    }

    /**
     * @param viTri the viTri to set
     */
    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    /**
     * @return the sucChua
     */
    public long getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(long sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the ngatFile
     */
    public String getNgatFile() {
        return ngatFile;
    }

    /**
     * @return the giaThue
     */
    public long getGiaThue() {
        return giaThue;
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

    /**
     * @return the cacSanh
     */
    public static List<SanhCuoi> getCacSanh() {
        return cacSanh;
    }

    /**
     * @param giaThue the giaThue to set
     */
    public void setGiaThue(long giaThue) {
        this.giaThue = giaThue;
    }


    public void init() throws FileNotFoundException {
        SanhCuoi sc = new SanhCuoi();
        try (Scanner scanner = new Scanner(file)){
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()){
                String maSC = scanner.next();
                String tenSanh = scanner.next();
                int viTri = Integer.parseInt(scanner.next());
                long sucChua = Long.parseLong(scanner.next());
                long giaThue = Long.parseLong(scanner.next());
                SanhCuoi sanh = new SanhCuoi(maSC, tenSanh, viTri, sucChua, giaThue);
                sc.getCacSanh().add(sanh);
                scanner.nextLine();
            }
        } catch (Exception e){
            System.out.println(e);
        } 
    }

    List<DichVu> getCacDichVu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
