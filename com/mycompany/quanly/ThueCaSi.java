/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import static com.mycompany.quanly.CaSi.hienThiFile;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KHANG
 */
public class ThueCaSi extends DichVu{
    private List<CaSi> caSi = new ArrayList<>();
    
    private List<Integer> soLuong = new ArrayList<>();
    
    public ThueCaSi(){
        super("D003", "Thue Ca Si", 0);
    }
    
    public ThueCaSi(List<CaSi> caSi, List<Integer> soLuong){
       super("D003", "Thue Ca Si", 0);
       this.caSi = caSi;
       this.soLuong = soLuong;
    }

    /**
     * @return the caSi
     */
    public List<CaSi> getCaSi() {
        return caSi;
    }

    /**
     * @param caSi the caSi to set
     */
    public void setCaSi(List<CaSi> caSi) {
        this.caSi = caSi;
    }

    /**
     * @return the soLuong
     */
    public List<Integer> getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(List<Integer> soLuong) {
        this.soLuong = soLuong;
    }
    
    public List<DichVu> Nhap() {
        Scanner scanner = new Scanner(System.in);
        String t;
        int flag = -1;
        CaSi cs = new CaSi();
        caSi = cs.Nhap();
        System.out.println("(chon so bai toi da 5 bai)");
        for (CaSi s: caSi){
            System.out.println("Ca Si " + s.getTenCaSi());
            do{
                System.out.print("Nhap so bai (vd: 2): ");
                t = scanner.nextLine();
                try{
                    int so = Integer.parseInt(t.trim());
                    if (so > 0 && so <= 5){ 
                        flag = 0;
                        soLuong.add(so);
                    }
                    else
                        flag = -1;
                }catch (Exception e){
                    flag = -1;
               }

            }
            while (flag == -1);
        }
        return null;
    }
        
    @Override
    public String toString() {
        String tail = "";
        for (int i = 0; i < caSi.size(); i++){
            tail += "#" + caSi.get(i).getMaCaSi() + "#" + soLuong.get(i);
            if (caSi.size() >= i + 2)
                tail += "&";
        }
        return this.getMaDichVu() + tail;
    }
    
    @Override
    void xuat(String form, NumberFormat vnFormat, String dv) {
        System.out.printf(form, super.tenDichVu, "");
        for (int i = 0; i < caSi.size(); i++){
            System.out.printf("  %-28s%-15s\n", this.caSi.get(i).getTenCaSi(),
                this.soLuong.get(i) + " x " + vnFormat.format(this.caSi.get(i).getGiaCaSi()) + dv);
        }
    }
    
    @Override
    public long getGiaDichVu(){
        long giaTong = 0;
        for (int i = 0; i < caSi.size(); i++){
            giaTong += this.caSi.get(i).getGiaCaSi() * this.soLuong.get(i);
        }
        return giaTong;
    }

}
