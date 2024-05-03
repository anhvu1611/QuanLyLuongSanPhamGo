/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.util.Date;
import java.util.Vector;

import Enum.CaLamCongNhan;
import Enum.GioiTinh;
import Enum.PhuCap;

/**
 *
 * @author ACER
 */
public class CongNhan extends NhanSu{
    private boolean trangThai;
    private ChuyenMon chuyenMon;
    private CaLamCongNhan caLam;
    

    public CongNhan(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh, boolean trangThai,
			ChuyenMon chuyenMon, CaLamCongNhan caLam) {
		super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
		this.trangThai = trangThai;
		this.chuyenMon = chuyenMon;
		this.caLam = caLam;
	}
    
    public CongNhan(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh, boolean trangThai,
			ChuyenMon chuyenMon) {
		super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
		this.trangThai = trangThai;
		this.chuyenMon = chuyenMon;
		this.caLam = null;
	}
    
    public CongNhan(String maNhanSu, String ho, String ten) {
		super(maNhanSu, ho, ten);
	}
    
	public CongNhan(String maNhanSu, String ho, String ten, PhuCap phuCap, ChuyenMon chuyenMon) {
		super(maNhanSu, ho, ten, phuCap);
		this.chuyenMon = chuyenMon;
	}

	public CongNhan(String maNhanSu) {
		super(maNhanSu);
		// TODO Auto-generated constructor stub
	}
    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public ChuyenMon getChuyenMon() {
        return chuyenMon;
    }
    
    
    public void setChuyenMon(ChuyenMon chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

	public CaLamCongNhan getCaLam() {
		return caLam;
	}

	public void setCaLam(CaLamCongNhan caLam) {
		this.caLam = caLam;
	}

	public Vector<String> duLieuFormCapNhat() {
		Vector<String> data = new Vector<>();
		data.add(maNhanSu);
		data.add(ho);
		data.add(ten);
		data.add(soDienThoai);
		data.add(CCCD);
		data.add(diaChi.toString());
		data.add(gioiTinh.getGioiTinh());
		data.add(chuyenMon.getTenChuyenMon());
		data.add((trangThai==true)?"Tham Gia":"Không tham gia");
		return data;
	}
    
	
}
