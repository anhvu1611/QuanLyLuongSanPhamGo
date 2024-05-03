/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Date;

import Enum.CaLamCongNhan;

/**
 *
 * @author ACER
 */
public class BangChamCongCongNhan {
    private String maBangChamCong;
    private Date ngayChamCong;
    private boolean trangThai;
    private int soLuongSanPhamHoanThanh;
    private NhanVien nguoiChamCong;
    private CaLamCongNhan caLam;
    private BangPhanCongCongDoan chamCongDoan;

    public BangChamCongCongNhan(String maBangChamCong, Date ngayChamCong, boolean trangThai, int soLuongSanPhamHoanThanh, NhanVien nguoiChamCong, CaLamCongNhan caLam, BangPhanCongCongDoan chamCongDoan) {
        this.maBangChamCong = maBangChamCong;
        this.ngayChamCong = ngayChamCong;
        this.trangThai = trangThai;
        this.soLuongSanPhamHoanThanh = soLuongSanPhamHoanThanh;
        this.nguoiChamCong = nguoiChamCong;
        this.caLam = caLam;
        this.chamCongDoan = chamCongDoan;
    }

    public String getMaBangChamCong() {
        return maBangChamCong;
    }

    public void setMaBangChamCong(String maBangChamCong) {
        this.maBangChamCong = maBangChamCong;
    }

    public Date getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(Date ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoLuongSanPhamHoanThanh() {
        return soLuongSanPhamHoanThanh;
    }

    public void setSoLuongSanPhamHoanThanh(int soLuongSanPhamHoanThanh) {
        this.soLuongSanPhamHoanThanh = soLuongSanPhamHoanThanh;
    }

    public NhanVien getNguoiChamCong() {
        return nguoiChamCong;
    }

    public void setNguoiChamCong(NhanVien nguoiChamCong) {
        this.nguoiChamCong = nguoiChamCong;
    }

    public CaLamCongNhan getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLamCongNhan caLam) {
        this.caLam = caLam;
    }

    public BangPhanCongCongDoan getChamCongDoan() {
        return chamCongDoan;
    }

    public void setChamCongDoan(BangPhanCongCongDoan chamCongDoan) {
        this.chamCongDoan = chamCongDoan;
    }
    

}
