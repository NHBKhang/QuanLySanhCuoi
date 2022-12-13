package com.mycompany.quanly;

import static com.mycompany.quanly.QuanLy.isValid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GiaThue {
        
        private final static double giaCuoiTuan = 1.2;
        private double heSoGiaThue = 1;
        private double heSoKhac = 1;
        private double heSoTong = 0;

        

	private boolean cuoiTuan;
        
        private SanhCuoi sanhCuoi;
        
        private final  String url = "D:\\Data\\GiaThue.txt";
        File file = new File(url);
        
        private String date;
        private HeSoGiaThue buoi;
        
    /**
     *
     * @param gia
     * @throws java.io.IOException
     */
        public void them() throws IOException {       
            if (file.length() == 0 || !ngayDaTonTai(date)) {
                try(FileWriter fileWriter = new FileWriter(file, true)){
                    try(PrintWriter writer = new PrintWriter(fileWriter)){
                        writer.printf("%s\s",this.date);
                        writer.print(this.heSoKhac);
                        writer.println();
                    } catch (Exception ex){
                        System.out.print(ex);
                    }
                } catch (Exception ex){
                    System.out.print(ex);
                }
            }
            else {
                System.out.println("Ban muon viet lai ngay " + this.date + "?");
                System.out.println("1.Co | 2.Khong");
                Scanner scanner = new Scanner(System.in);
                int chon = 0, flag = -1;
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
                if (chon == 1){
                    List<GiaThue> gia = dsNgay();
                    for (GiaThue g: gia){
                        if (g.date.equals(this.date)){
                            g.heSoKhac = this.heSoKhac;
                            break;
                        }
                    }
                    rewriteFile(gia);
                }
                else {
                    return;
                }
            }   
	}
        
        public void xoa(String date){
            GiaThue price = new GiaThue();
            List<GiaThue> gia = dsNgay();
            for (GiaThue y : gia) {
                if (y.date.equals(date)) {
                   price = y;
                    break;
                }
            }
            if (price.date == null || price.date == ""){
                System.out.println("Khong thay ngay phu hop");
            }
            else {
                gia.remove(price);
                rewriteFile(gia);
            }
        }
        
        private boolean ngayDaTonTai(String date) throws FileNotFoundException{
            try (Scanner scanner = new Scanner(file)){
                while (scanner.hasNextLine()){
                    String ngay = scanner.next();
                    if (ngay == null ? date == null : ngay.equals(date)){
                        return true;
                    }
                    else {
                        scanner.nextLine();
                    }
                }
                
            } catch (Exception e) {
                System.out.print(e);
            }
            return false;
        }
        
        //  phuong thuc them ngay moi
	public GiaThue(String date,  double heSoKhac) {
            this.date = date;
            this.heSoKhac = heSoKhac;       
	}
        public GiaThue(){}
        public GiaThue(HeSoGiaThue heSoGia, String date) throws FileNotFoundException {
            this.heSoGiaThue = heSoGia.getHeSoThoiDiem();
            this.buoi = heSoGia;
            this.date = date;
            try (Scanner scanner = new Scanner (file)){
                while (scanner.hasNextLine()){
                    if (file.length() == 0) break;
                    String ngay = scanner.next();
                    if (this.date == null ? ngay == null : this.date.equals(ngay)){
                        double heSo = Double.parseDouble(scanner.next());
                        this.heSoKhac = heSo;
                        break;
                    }
                    else {
                        scanner.nextLine();
                    }
                }
            } catch (Exception e) {
                System.out.print(e);
            }
            if (isCuoiTuan(date)){
                this.heSoTong = giaCuoiTuan + this.heSoGiaThue + this.heSoKhac - 2;
            } 
            else {
                this.heSoTong = this.heSoGiaThue + this.heSoKhac - 1;
            }            
        }
        
    private void rewriteFile(List<GiaThue> dsNgay) {
        try(FileWriter fileWriter = new FileWriter(file, false)){
            try(PrintWriter writer = new PrintWriter(fileWriter)){
                for (GiaThue g: dsNgay){
                    writer.printf("%s\s",g.date);
                    writer.println(g.heSoKhac);
                }
            } 
        } catch (Exception ex){
            System.out.print(ex);
        }
    }
        
    private List<GiaThue> dsNgay(){
        List<GiaThue> gia = new ArrayList<>();
        try (Scanner s = new Scanner(file)){
            while (s.hasNextLine()){
                if (file.length() == 0) break;
                String ngay = s.next();                 
                double heSo = Double.parseDouble(s.next());
                s.nextLine();
                GiaThue g = new GiaThue(ngay, heSo);
                gia.add(g);
            }
        }catch (Exception ex) {
            System.out.println(ex);
        }
        return gia;
    }
    
        private String form = "%-15s%-10s%n";
        private String[] header = new String[] {"Ngay","He So"};
        private void hienThiFile(GiaThue gt){
            System.out.format(gt.form, gt.header);
            List<GiaThue> gia = dsNgay();
            for (Object[] row : toTable(gia, gia.size())){
                System.out.format(gt.form, row);
           }
        }
        
        //  tao bang
        private final Object[][] toTable(List<GiaThue> price, int size) {
            final Object[][] table = new String[size][];
            for (int i = 0; i < size; i++){
                table[i] = new String[] {price.get(i).date,
                                         String.valueOf(price.get(i).heSoKhac)};
            }
            return table;
        }
    
  //   nhap cho quan ly
    public void nhap(){
        int chon = 0, flag = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Them ngay | 2.Xoa ngay");
        do {
            System.out.print("Chon: ");
            String s = scanner.nextLine();
            String[] cat = s.split(" ");
            try {
                chon = Integer.parseInt(cat[0]);
                if (chon > 0 && chon < 3) flag = 0;
                else flag = -1;
            } catch (Exception e){
                flag = -1;
            }                
        }
        while (flag == -1);
        //  them
        GiaThue gia = new GiaThue();
        if (chon == 1){
            do{
                flag = -1;
                String[] cat;
                do{
                    System.out.print("Nhap ngay (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    cat = date.split(" ");
                }
                while (!isValid(cat[0]));
                String ngay = cat[0];
                double heSo = 0;
                do{
                    System.out.print("Nhap he so (vd: 1.2): ");
                    String a = scanner.nextLine();            
                    cat = a.split(" ");
                    try {
                        heSo = Double.parseDouble(cat[0].trim());
                        if (heSo > 1) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }
                }
                while (flag == -1);
                GiaThue gt = new GiaThue(ngay, heSo);
                gia = gt;
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
                        gia.them();
                        System.out.println("- Luu ngay thanh cong -");
                    } catch (IOException ex) {
                        System.out.println("- Luu ngay khong thanh cong -");
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
        //   xoa ngay
        else{
            do{
                hienThiFile(this);
                flag = -1;
                String[] cat;
                do{
                    System.out.print("Nhap ngay (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    cat = date.split(" ");
                }
                while (!isVal(cat[0]));
                String ngay = cat[0];
                System.out.println("Xoa ngay " + ngay + "?\t1.Xoa | 2.Khong va nhap lai | 3.Thoat");
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
                    xoa(ngay);
                    System.out.println("- Xoa ngay thanh cong -");
                    break;
                }
                else if (chon == 2)
                    continue;
                else
                    return;
                }
            while (true);
        }
    }
    
    private boolean isVal(String date) {
            String dateFormat = "dd/MM/yyyy";
            DateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            try {
                sdf.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        
    /**
     *
     * @return
     */
        
    /**
     * @return the cuoiTuan
     */
    public static boolean isCuoiTuan(String date) {
        String[] ngay = date.split("/");
        ArrayList<Integer> arrList = new ArrayList<>();
        for (String i: ngay){
            int j = Integer.parseInt(i);
            arrList.add(j);            
        }
        int[] arr = arrList.stream().mapToInt(i->i).toArray();
        LocalDate localDate = LocalDate.of(arr[2],arr[1],arr[0]);
        DayOfWeek day = localDate.getDayOfWeek();
        if (day.getValue() ==7 || day.getValue() == 6){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return the heSoKhac
     * @throws java.io.FileNotFoundException
     */
    public double getHeSoKhac() {
        return heSoKhac;
    }

    /**
     * @param heSoKhac the heSoKhac to set
     */
    public void setHeSoKhac(double heSoKhac) {
        this.heSoKhac = heSoKhac;
    }


    /**
     * @return the heSoTong
     * @throws java.io.FileNotFoundException
     */
    public double getHeSoTong(){
        return heSoTong;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
        if (isCuoiTuan(date)){
            this.heSoTong = giaCuoiTuan + this.heSoGiaThue + this.heSoKhac - 2;
        }
        else{
            this.heSoTong = this.heSoGiaThue + this.heSoKhac - 1;
        }
    }

    /**
     * @return the buoi
     */
    public HeSoGiaThue getBuoi() {
        return buoi;
    }





}
