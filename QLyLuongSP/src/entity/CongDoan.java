/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

import Enum.LoaiCongDoan;

public class CongDoan {
    private String maCongDoan;
    private double luongTren1Sp;
    private int soCongNhan;
    private int soLuongThanhPhan;
    private SanPham sanPham;
    private LoaiCongDoan loaiCongDoan;
    private LocalDate ngayChiaCongDoan;
    private int trangThai;
    
    

    public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public CongDoan(String maCongDoan, double luongTren1Sp, int soCongNhan, int soLuongThanhPhan, SanPham sanPham,
			LoaiCongDoan loaiCongDoan, LocalDate ngayChiaCongDoan, int trangThai) {
		super();
		this.maCongDoan = maCongDoan;
		this.luongTren1Sp = luongTren1Sp;
		this.soCongNhan = soCongNhan;
		this.soLuongThanhPhan = soLuongThanhPhan;
		this.sanPham = sanPham;
		this.loaiCongDoan = loaiCongDoan;
		this.ngayChiaCongDoan = ngayChiaCongDoan;
		this.trangThai = trangThai;
	}

	// Constructor
    public CongDoan(String maCongDoan, double luongTren1Sp, int soCongNhan, int soLuongThanhPhan,
                    SanPham sanPham, LoaiCongDoan loaiCongDoan, LocalDate ngayChiaCongDoan) {
        this.maCongDoan = maCongDoan;
        this.luongTren1Sp = luongTren1Sp;
        this.soCongNhan = soCongNhan;
        this.soLuongThanhPhan = soLuongThanhPhan;
        this.sanPham = sanPham;
        this.loaiCongDoan = loaiCongDoan;
        this.ngayChiaCongDoan = ngayChiaCongDoan;
    }

	public CongDoan(String maCongDoan) {
		// TODO Auto-generated constructor stub
	}

	public String getMaCongDoan() {
        return maCongDoan;
    }

    public void setMaCongDoan(String maCongDoan) {
        this.maCongDoan = maCongDoan;
    }

    public double getLuongTren1Sp() {
        return luongTren1Sp;
    }

    public void setLuongTren1Sp(double luongTren1Sp) {
        this.luongTren1Sp = luongTren1Sp;
    }

    public int getSoCongNhan() {
        return soCongNhan;
    }

    public void setSoCongNhan(int soCongNhan) {
        this.soCongNhan = soCongNhan;
    }

    public int getSoLuongThanhPhan() {
        return soLuongThanhPhan;
    }

    public void setSoLuongThanhPhan(int soLuongThanhPhan) {
        this.soLuongThanhPhan = soLuongThanhPhan;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public LoaiCongDoan getLoaiCongDoan() {
        return loaiCongDoan;
    }

    public void setLoaiCongDoan(LoaiCongDoan loaiCongDoan) {
        this.loaiCongDoan = loaiCongDoan;
    }

    public LocalDate getNgayChiaCongDoan() {
        return ngayChiaCongDoan;
    }

    public void setNgayChiaCongDoan(LocalDate ngayChiaCongDoan) {
        this.ngayChiaCongDoan = ngayChiaCongDoan;
    }


}
