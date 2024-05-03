/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;


/**
 *
 * @author ACER
 */
public class BangPhanCongCongDoan {
    private int soLuongPhanCong;
    private CongDoan congDoan;
    private CongNhan congNhanDamNhan;
    private LocalDate ngayPhanCong;
    private int soLuongConLai;
    
    
    
    public BangPhanCongCongDoan(int soLuongPhanCong, CongDoan congDoan, CongNhan congNhanDamNhan,
			LocalDate ngayPhanCong) {
		super();
		this.soLuongPhanCong = soLuongPhanCong;
		this.congDoan = congDoan;
		this.congNhanDamNhan = congNhanDamNhan;
		this.ngayPhanCong = ngayPhanCong;
	}


	


	public BangPhanCongCongDoan(int soLuongPhanCong, CongDoan congDoan, CongNhan congNhanDamNhan, int soLuongConLai) {
		super();
		this.soLuongPhanCong = soLuongPhanCong;
		this.congDoan = congDoan;
		this.congNhanDamNhan = congNhanDamNhan;
		this.soLuongConLai = soLuongConLai;
	}



	// Constructor
    public BangPhanCongCongDoan(int soLuongPhanCong, CongDoan congDoan, CongNhan congNhanDamNhan) {
        this.soLuongPhanCong = soLuongPhanCong;
        this.congDoan = congDoan;
        this.congNhanDamNhan = congNhanDamNhan;
    }

    
    public BangPhanCongCongDoan(int soLuongPhanCong, CongDoan congDoan, CongNhan congNhanDamNhan,
			LocalDate ngayPhanCong, int soLuongConLai) {
		super();
		this.soLuongPhanCong = soLuongPhanCong;
		this.congDoan = congDoan;
		this.congNhanDamNhan = congNhanDamNhan;
		this.ngayPhanCong = ngayPhanCong;
		this.soLuongConLai = soLuongConLai;
	}





	public BangPhanCongCongDoan(CongNhan congNhanDamNhan) {
		super();
		this.congNhanDamNhan = congNhanDamNhan;
	}

	



	public BangPhanCongCongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	// Getters and Setters
	
    public int getSoLuongPhanCong() {
        return soLuongPhanCong;
    }

    public LocalDate getNgayPhanCong() {
		return ngayPhanCong;
	}



	public void setNgayPhanCong(LocalDate ngayPhanCong) {
		this.ngayPhanCong = ngayPhanCong;
	}



	public void setSoLuongPhanCong(int soLuongPhanCong) {
        this.soLuongPhanCong = soLuongPhanCong;
    }

    public CongDoan getCongDoan() {
        return congDoan;
    }

    public void setCongDoan(CongDoan congDoan) {
        this.congDoan = congDoan;
    }

    public CongNhan getCongNhanDamNhan() {
        return congNhanDamNhan;
    }

    public void setCongNhanDamNhan(CongNhan congNhanDamNhan) {
        this.congNhanDamNhan = congNhanDamNhan;
    }



	public int getSoLuongConLai() {
		return soLuongConLai;
	}



	public void setSoLuongConLai(int soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}
    
}
