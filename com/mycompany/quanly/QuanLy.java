/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.quanly;

import static com.mycompany.quanly.CaSi.hienThiFile;
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
    private static SanhCuoi sc = new SanhCuoi();
    private static DichVu dv = new DichVu();
    private static ThucAn ta = new ThucAn();
    private static ThucUong tu = new ThucUong();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        sc.init();
        dv.init();
        ta.init();
        tu.init();
        intro();
    
    }
    
    private static void intro() throws FileNotFoundException, IOException{
        System.out.println("Chao mung den voi he thong cua nha hang tiec cuoi");
        System.out.println("Dang nhap voi  \t  1.Guest  |  2.Admin | 3.Bao cao va thong ke");
        int chon = 1, flag = -1;
        do{
            System.out.print("Chon: ");
            String s = scanner.nextLine();
            String[] cat = s.split(" ");
            try {
                chon = Integer.parseInt(cat[0].trim());
                if (chon != 1 && chon != 2 && chon != 3) chon = -1;
            } catch (Exception e){
                chon = -1;
            }
        }
        while (chon == -1);
        if (chon == 1)
            choThue();
        else if (chon == 2)
            quanLy();
        else{
            System.out.println("1.Bao Cao | 2.Thong Ke");
            do {
                System.out.print("Chon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(cat[0]);
                    if (chon > 0 && chon <= 2) flag = 0;
                    else flag = -1;
                } catch (Exception e){
                    flag = -1;
                }                
            }
            while (flag == -1);
            if (chon == 1){
                DoanhThu dt = new DoanhThu();
                dt.init();
                System.out.println("1.Bao Cao Thang | 2.Bao Cao Quy");
                do {
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0]);
                        if (chon > 0 && chon <= 2) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){                    
                    dt.baoCaoThang();
                }
                else{
                    dt.baoCaoQuy();
                }
            }
            else{
                TanSoThue ts = new TanSoThue();
                ts.init();
                System.out.println("1.Tan so sanh thue | 2.Tan so sanh thue theo nam");
                do {
                    System.out.print("Chon: ");
                    String s = scanner.nextLine();
                    String[] cat = s.split(" ");
                    try {
                        chon = Integer.parseInt(cat[0]);
                        if (chon > 0 && chon <= 2) flag = 0;
                        else flag = -1;
                    } catch (Exception e){
                        flag = -1;
                    }                
                }
                while (flag == -1);
                if (chon == 1){
                    ts.thongKe();
                }
                else{
                    ts.thongKeNam();
                }
            }
        }
    }

    private static void choThue() throws FileNotFoundException, IOException{
        int chon = 0, soBan = 20, flag = -1;
        BuoiTiec tiec = new BuoiTiec();        
        do {
            SanhCuoi sanh = sc.Nhap();
            tiec.setSanhCuoi(sanh);
            tiec.setDonGiaSanh(sanh.getGiaTong());
            tiec.setThoiDiem(sanh.getGia().getBuoi().getThoiDiem());
            tiec.setNgayThue(sanh.getGia().getDate());
            long giaTongTA = 0, giaTongTU = 0;
            List<ThucAn> thucAn = ta.Nhap();
            tiec.setThucAn(thucAn);
            for (ThucAn t: thucAn){
                giaTongTA += t.getGiaMon();
            }
            List<ThucUong> thucUong = tu.Nhap();
            tiec.setThucUong(thucUong);
            for (ThucUong t: thucUong){
                giaTongTU += t.getGiaMon();
            }
            do{
                System.out.print("Nhap so ban (toi thieu 5 ban): ");
                String i = scanner.nextLine();
                String[] a = i.split(" ");
                try {
                    soBan = Integer.parseInt(a[0].trim());
                    if (soBan >= 5) flag = 0;
                    else flag = -1;
                } catch (Exception e){
                    flag = -1;
                }
            }
            while (flag == -1);
            tiec.setSoBan(soBan);
            tiec.setDonGiaMenu((giaTongTA + giaTongTU) * soBan);
            long giaTongDV = 0;
            List<DichVu> dichVu = dv.Nhap();
            for (DichVu d: dichVu){
                if (d.getClass() != new DichVu().getClass()){
                    d.Nhap();
                    giaTongDV += d.getGiaDichVu();
                }
            }
            tiec.setDichVu(dichVu);
            tiec.setDonGiaDV(giaTongDV);
            do{
                flag = -1;
                System.out.print("Luu lua chon va xuat hoa don?\n"
                        + "1.Luu va xuat hoa don  |  2.Khong luu va nhap lai  |  3.Thoat\nChon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(cat[0].trim());
                    if (chon <= 0 && chon > 3) flag = -1;
                    else flag = 0;
                } catch (Exception e){
                    flag = -1;
                }
            }
            while (flag == -1);
            if (chon == 1)
                break;
            else if (chon == 2)
                continue;
            else
                return;
        }
        while (true);
        tiec.xuatHoaDon();
        tiec.them();
    }

    private static void quanLy() {
        int flag = 0, chon = 0;
        do{
            System.out.println("1.Sanh Cuoi | 2.Menu | 3.Dich Vu | 4.Khac");
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
                sc.nhap();
            }
            else if(chon == 2){
                System.out.println("1.Thuc An | 2.Thuc Uong");
                do {
                    flag = -1;
                    System.out.print("Chon: ");
                    String s = scanner.next();
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
                if (chon == 1)
                    ta.nhap();
                else
                    tu.nhap();
            }
            else if (chon == 3){
                dv.nhap();
            }
            else {
                System.out.println("1.Ca Si | 2.Ngay Thue Sanh Cuoi ");
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
                    CaSi cs = new CaSi();
                    cs.nhap();
                }
                else{
                    GiaThue gt = new GiaThue();
                    gt.nhap();
                }
            }
            do{
                System.out.print("1.Tiep tuc  |  2.Thoat \nChon: ");
                String s = scanner.nextLine();
                String[] cat = s.split(" ");
                try {
                    chon = Integer.parseInt(s.trim());
                    if (chon <= 0 && chon > 2) chon = -1;
                } catch (Exception e){
                    chon = -1;
                }
            }
            while (chon!=1&&chon!=2);
            if (chon == 1)
                continue;
            else
                return;
        }
        while (true);
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