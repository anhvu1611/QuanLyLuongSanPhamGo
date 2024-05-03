/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

import Enum.GioiTinh;
import Enum.PhuCap;

/**
 *
 * @author ACER
 */
public class NhanSu {
    protected String maNhanSu;
    protected String ho;
    protected String ten;
    protected String soDienThoai;
    protected Date ngaySinh;
    protected Date ngayVaoLam;
    protected String CCCD;
    protected DiaChi diaChi;
    protected PhuCap phuCap;
    protected GioiTinh gioiTinh;
    protected String anh;


    public NhanSu() {
    }
    
    public NhanSu(String maNhanSu) {
		super();
		this.maNhanSu = maNhanSu;
	}

	public NhanSu(String maNhanSu, String ho, String ten) {
		super();
		this.maNhanSu = maNhanSu;
		this.ho = ho;
		this.ten = ten;
	}
	


	public NhanSu(String maNhanSu, String ho, String ten, PhuCap phuCap) {
		super();
		this.maNhanSu = maNhanSu;
		this.ho = ho;
		this.ten = ten;
		this.phuCap = phuCap;
	}

	public NhanSu(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam, String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh) {
        this.maNhanSu = maNhanSu;
        this.ho = ho;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.phuCap = phuCap;
        this.gioiTinh = gioiTinh;
        this.anh = anh;
    }

    public String getMaNhanSu() {
        return maNhanSu;
    }

    public void setMaNhanSu(String maNhanSu) {
        this.maNhanSu = maNhanSu;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public PhuCap getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(PhuCap phuCap) {
        this.phuCap = phuCap;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
