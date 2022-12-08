/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanly;

import java.util.List;

/**
 *
 * @author KHANG
 */
public class BuoiTiec {
    private String tenBuoiTiec;
    private SanhCuoi sanhCuoi;
    private long donGiaSanh;
    private String thoiDiem;
    private String ngayThue;
    private List<ThucAn> thucAn;
    private List<ThucUong> thucUong;
    private long donGiaMenu;
    private List<DichVu> dichVu;
    private long donGiaDV;
    
    public BuoiTiec(){}

    /**
     * @return the tenBuoiTiec
     */
    public String getTenBuoiTiec() {
        return tenBuoiTiec;
    }

    /**
     * @return the sanhCuoi
     */
    public SanhCuoi getSanhCuoi() {
        return sanhCuoi;
    }

    /**
     * @param sanhCuoi the sanhCuoi to set
     */
    public void setSanhCuoi(SanhCuoi sanhCuoi) {
        this.sanhCuoi = sanhCuoi;
    }

    /**
     * @return the donGiaSanh
     */
    public long getDonGiaSanh() {
        return donGiaSanh;
    }

    /**
     * @param donGiaSanh the donGiaSanh to set
     */
    public void setDonGiaSanh(long donGiaSanh) {
        this.donGiaSanh = donGiaSanh;
    }

    /**
     * @return the thoiDiem
     */
    public String getThoiDiem() {
        return thoiDiem;
    }

    /**
     * @param thoiDiem the thoiDiem to set
     */
    public void setThoiDiem(String thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    /**
     * @return the ngayThue
     */
    public String getNgayThue() {
        return ngayThue;
    }

    /**
     * @param ngayThue the ngayThue to set
     */
    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    /**
     * @return the donGiaMenu
     */
    public long getDonGiaMenu() {
        return donGiaMenu;
    }

    /**
     * @param donGiaMenu the donGiaMenu to set
     */
    public void setDonGiaMenu(long donGiaMenu) {
        this.donGiaMenu = donGiaMenu;
    }

    /**
     * @return the dichVu
     */
    public List<DichVu> getDichVu() {
        return dichVu;
    }

    /**
     * @param dichVu the dichVu to set
     */
    public void setDichVu(List<DichVu> dichVu) {
        this.dichVu = dichVu;
    }

    /**
     * @return the donGiaDV
     */
    public long getDonGiaDV() {
        return donGiaDV;
    }

    /**
     * @param donGiaDV the donGiaDV to set
     */
    public void setDonGiaDV(long donGiaDV) {
        this.donGiaDV = donGiaDV;
    }

    /**
     * @return the thucAn
     */
    public List<ThucAn> getThucAn() {
        return thucAn;
    }

    /**
     * @param thucAn the thucAn to set
     */
    public void setThucAn(List<ThucAn> thucAn) {
        this.thucAn = thucAn;
    }

    /**
     * @return the thucUong
     */
    public List<ThucUong> getThucUong() {
        return thucUong;
    }

    /**
     * @param thucUong the thucUong to set
     */
    public void setThucUong(List<ThucUong> thucUong) {
        this.thucUong = thucUong;
    }
    
}
