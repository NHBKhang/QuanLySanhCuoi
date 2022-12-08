/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.quanly;

import static com.mycompany.quanly.SanhCuoi.hienThi;
import static com.mycompany.quanly.SanhCuoi.hienThiFile;
import static com.mycompany.quanly.DichVu.hienThi;
import static com.mycompany.quanly.DichVu.hienThiFile;
import static com.mycompany.quanly.SanhCuoi.getCacSanh;
import static com.mycompany.quanly.ThucAn.getMonAn;
import static com.mycompany.quanly.ThucAn.hienThiFile;
import static com.mycompany.quanly.ThucUong.getMonUong;
import static com.mycompany.quanly.ThucUong.hienThiFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author KHANG
 */
public class QuanLy {
    static SanhCuoi sc = new SanhCuoi();
    static DichVu dv = new DichVu();
    static ThucAn ta = new ThucAn();
    static ThucUong tu = new ThucUong();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        sc.init();
        dv.init();
        ta.init();
        tu.init();
        intro();
        
//        System.out.print("Hãy nhập tên sảnh:");
//        String tenSanh = scanner.nextLine();
//        System.out.print("Hãy vị trí sảnh:");
//        int viTri = scanner.nextInt();
//        System.out.print("Hãy sức chứa sảnh:");
//        long sucChua = scanner.nextInt();

//        String ngay = "30/1/2022";
//        GiaThue gia = new GiaThue(new HeSoGiaThue(1), ngay);
//        hienThiFile(sc);
//        hienThi(sc.tim("r", 0), sc);
//        System.out.println(sc.getMaSanh());
//        System.out.println(sc.getTenSanh());
//        System.out.println(sc.getViTri());
//        System.out.println(sc.getSucChua());



    
    
    
    }
    
    private static void intro(){
        System.out.println("Chao mung den voi he thong cua nha hang tiec cuoi");
        System.out.println("Dang nhap voi  \t  1.Guest  |  2.Admin");
        int chon = 1;
//        do{
//            System.out.print("Chon: ");
//            chon = scanner.nextInt();
//        }
//        while (chon!=1&&chon!=2);
        if (chon == 1)
            choThue();
        else
            quanLy();
    }

    private static void choThue(){
        int flag = -1, chon, buoi, soBan;
        String ma, date, mon;
        BuoiTiec tiec = new BuoiTiec();
        scanner.useDelimiter("\n");
        
//        SanhCuoi sanh = new SanhCuoi();
//        hienThiFile(sc);
//        do{
//            do{
//                System.out.print("Chon sanh (vd:S001): ");
//                ma = scanner.next().toUpperCase();
//                for (SanhCuoi s: getCacSanh()){
//                    if (s.getMaSanh().equals(ma)){
//                        sanh = s;
//                        flag = 0;
//                    }
//                }
//            }
//            while (flag == -1);
//            flag = -1;
//            do{
//                System.out.print("Nhap ngay (dd/mm/yyyy): ");
//                date = scanner.next();
//            }
//            while (!isValid(date));
//            do{
//                System.out.print("1.Sang | 2.Chieu | 3.Toi\nChon Buoi: ");
//                buoi = scanner.nextInt();
//            }
//            while (buoi!=1&&buoi!=2&&buoi!=3);
//            do{
//                System.out.print("Luu lua chon? \t 1.Yes  |  2.No\nChon: ");
//                chon = scanner.nextInt();
//            }
//            while (chon!=1&&chon!=2);
//            if (chon ==1)
//                break;
//            else
//                continue;
//        }
//        while (true);
//        GiaThue gia = null;
//        try {
//            gia = new GiaThue(new HeSoGiaThue(buoi - 1), date);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        sanh.setGia(gia);

//        List<ThucAn> thucAn = new ArrayList<>();
//        hienThiFile(ta);
//        System.out.println("(Chon toi thieu 5 mon)");
//        do {
//            System.out.print("Chon mon (vd: 1,2): ");
//            mon = scanner.next();
//            String[] cacMon = mon.split(",");
//            for (int i = 0; i < cacMon.length; i++){
//                try {
//                    int s = Integer.parseInt(cacMon[i].trim());
//                    if (s >= getMonAn().size())
//                        cacMon[i] = String.format("1%05d", s);
//                } catch (Exception e) {
//                    flag = -1;
//                }
//            }
//            for (ThucAn t: getMonAn()){
//                for (String a: cacMon){
//                    if (t.getMaMon().equals(a)){
//                        thucAn.add(t);
//                        flag = 0;
//                    }
//                }
//            }
//        }
//        while (flag == -1 || thucAn.size() < 5);
//        flag = -1;

//        List<ThucUong> thucUong = new ArrayList<>();
//        hienThiFile(tu);
//        do {
//            System.out.print("Chon nuoc uong (vd: 1,2): ");
//            mon = scanner.next();
//            String[] cacMon = mon.split(",");
//            for (int i = 0; i < cacMon.length; i++){
//                try {
//                    int s = Integer.parseInt(cacMon[i].trim());
//                    if (s >= getMonUong().size())
//                        cacMon[i] = String.format("2%05d", s);
//                } catch (Exception e) {
//                    flag = -1;
//                }
//            }
//            for (ThucUong t: getMonUong()){
//                for (String a: cacMon){
//                    if (t.getMaMon().equals(a)){
//                        thucUong.add(t);
//                        flag = 0;
//                    }
//                }
//            }
//        }
//        while (flag == -1 || thucUong.size() < 1);
//        flag = -1;

//        do{
//            System.out.print("Nhap so ban (toi thieu 5 ban): ");
//            soBan = Integer.parseInt(scanner.next());
//        }
//        while (soBan < 5);

        List<DichVu> dichVu = new ArrayList<>();
        hienThiFile(dv);
        
    }

    private static void quanLy() {
        
    }
    
        public static boolean isValid(String date) {
            String dateFormat = "dd/MM/yyyy";
            DateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            long millis = System.currentTimeMillis();
            Date date1, date2 = new Date(millis);
            try {
                date1 = sdf.parse(date);
            } catch (ParseException e) {
                return false;
            }
            if (date1.compareTo(date2) >= 0){
                return true;
            }
            return false;
        }
        
    
}