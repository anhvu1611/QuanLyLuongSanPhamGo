/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

import Enum.LoaiGo;

/**
 *
 * @author ACER
*/
public class SanPham {
    private String maSanPham;
    private String tenSP;
    private double giaThanh;
    private LocalDate thoiGianSanXuatDuKien;
    private LoaiGo chatGo;
    private String anh;
    private String kichThuoc;
    private int trangThai;
    private int soLuong;
    private double khoiLuong;
    // Constructor
   
    public String getMaSanPham() {
        return maSanPham;
    }
    
    public SanPham(String maSanPham) {
		// TODO Auto-generated constructor stub
    	this.maSanPham = maSanPham;
	}
    
    
    

	public int getTrangThai() {
		return trangThai;
	}




	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}




	public SanPham(String maSanPham, String tenSP, double giaThanh, LocalDate thoiGianSanXuatDuKien, LoaiGo chatGo,
			String anh, String kichThuoc, int trangThai) {
		super();
		this.maSanPham = maSanPham;
		this.tenSP = tenSP;
		this.giaThanh = giaThanh;
		this.thoiGianSanXuatDuKien = thoiGianSanXuatDuKien;
		this.chatGo = chatGo;
		this.anh = anh;
		this.kichThuoc = kichThuoc;
		this.trangThai = trangThai;
	}

	


	public SanPham(String maSanPham, String tenSP, double giaThanh, LocalDate thoiGianSanXuatDuKien, LoaiGo chatGo,
			String anh, String kichThuoc, int soLuong, double khoiLuong) {
		super();
		this.maSanPham = maSanPham;
		this.tenSP = tenSP;
		this.giaThanh = giaThanh;
		this.thoiGianSanXuatDuKien = thoiGianSanXuatDuKien;
		this.chatGo = chatGo;
		this.anh = anh;
		this.kichThuoc = kichThuoc;
		this.soLuong = soLuong;
		this.khoiLuong = khoiLuong;
		this.trangThai = 0;
	}




	public int getSoLuong() {
		return soLuong;
	}




	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}




	public double getKhoiLuong() {
		return khoiLuong;
	}




	public void setKhoiLuong(double khoiLuong) {
		this.khoiLuong = khoiLuong;
	}




	public SanPham(String maSanPham, String tenSP, double giaThanh, LocalDate thoiGianSanXuatDuKien, LoaiGo chatGo,
			String anh, String kichThuoc, int trangThai, int soLuong, double khoiLuong) {
		super();
		this.maSanPham = maSanPham;
		this.tenSP = tenSP;
		this.giaThanh = giaThanh;
		this.thoiGianSanXuatDuKien = thoiGianSanXuatDuKien;
		this.chatGo = chatGo;
		this.anh = anh;
		this.kichThuoc = kichThuoc;
		this.trangThai = trangThai;
		this.soLuong = soLuong;
		this.khoiLuong = khoiLuong;
	}




	public SanPham(String maSanPham, String tenSP, double giaThanh, LocalDate thoiGianSanXuatDuKien, LoaiGo chatGo,
			String anh, String kichThuoc) {
		super();
		this.maSanPham = maSanPham;
		this.tenSP = tenSP;
		this.giaThanh = giaThanh;
		this.thoiGianSanXuatDuKien = thoiGianSanXuatDuKien;
		this.chatGo = chatGo;
		this.anh = anh;
		this.kichThuoc = kichThuoc;
	}

	public String getKichThuoc() {
		return kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public LocalDate getThoiGianSanXuatDuKien() {
        return thoiGianSanXuatDuKien;
    }

    public void setThoiGianSanXuatDuKien(LocalDate thoiGianSanXuatDuKien) {
        this.thoiGianSanXuatDuKien = thoiGianSanXuatDuKien;
    }

    public LoaiGo getChatGo() {
        return chatGo;
    }

    public void setChatGo(LoaiGo chatGo) {
        this.chatGo = chatGo;
    }
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
}