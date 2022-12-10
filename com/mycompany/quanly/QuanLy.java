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
    
    }
    
    private static void intro() throws FileNotFoundException, IOException{
        System.out.println("Chao mung den voi he thong cua nha hang tiec cuoi");
        System.out.println("Dang nhap voi  \t  1.Guest  |  2.Admin");
        int chon = 1;
        do{
            System.out.print("Chon: ");
            chon = scanner.nextInt();
        }
        while (chon!=1&&chon!=2);
        if (chon == 1)
            choThue();
        else
            quanLy();
    }

    private static void choThue() throws FileNotFoundException, IOException{
        int chon, soBan = 20, flag = -1;
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
                try {
                    soBan = Integer.parseInt(scanner.next());
                    if (soBan >= 5) flag = 0;
                } catch (Exception e){
                    flag = -1;
                }
            }
            while (flag == -1);
            tiec.setDonGiaMenu((giaTongTA + giaTongTU) * soBan);
            long giaTongDV = 0;
            List<DichVu> dichVu = dv.Nhap();
            for (DichVu d: dichVu){
                d.Nhap();
                giaTongDV += d.getGiaDichVu();
            }
            tiec.setDichVu(dichVu);
            tiec.setDonGiaDV(giaTongDV);
            do{
                System.out.print("Luu lua chon va xuat hoa don?\n"
                        + "1.Luu va xuat hoa don  |  2.Khong luu va nhap lai  |  3.Thoat\nChon: ");
                String s = scanner.nextLine();
                try {
                    chon = Integer.parseInt(s);
                } catch (Exception e){
                    chon = -1;
                }
            }
            while (chon!=1&&chon!=2);
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