/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.Vector;

import Enum.GioiTinh;
import Enum.PhuCap;

/**
 *
 * @author ACER
 */
public class NhanVien extends NhanSu{
    private ChucVu chucVu;
    private TaiKhoan taiKhoan;
    private boolean BHYT;
    private boolean BHXH;
    private PhongBan ban;
    private String email;
    private float heSoLuong;
    private float thamNiem;

//    public NhanVien(ChucVu chucVu, boolean BHYT, boolean BHXH, PhongBan ban, String email, float heSoLuong, float thamNiem, String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam, String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh) {
//        super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
//        this.chucVu = chucVu;
//        this.BHYT = BHYT;
//        this.BHXH = BHXH;
//        this.ban = ban;
//        this.email = email;
//        this.heSoLuong = heSoLuong;
//        this.thamNiem = thamNiem;
//    }
//
//    public NhanVien(ChucVu chucVu, TaiKhoan taiKhoan, boolean BHYT, boolean BHXH, PhongBan ban, String email, float heSoLuong, float thamNiem, String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam, String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh) {
//        super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
//        this.chucVu = chucVu;
//        this.taiKhoan = taiKhoan;
//        this.BHYT = BHYT;
//        this.BHXH = BHXH;
//        this.ban = ban;
//        this.email = email;
//        this.heSoLuong = heSoLuong;
//        this.thamNiem = thamNiem;
//    }
//    
//    public NhanVien(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh,
//			Date ngayVaoLam, String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh,
//			ChucVu chucVu, TaiKhoan taiKhoan, boolean bHYT, boolean bHXH, PhongBan ban, String email, float heSoLuong,
//			float thamNiem) {
//		super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
//		this.chucVu = chucVu;
//		this.taiKhoan = taiKhoan;
//		BHYT = bHYT;
//		BHXH = bHXH;
//		this.ban = ban;
//		this.email = email;
//		this.heSoLuong = heSoLuong;
//		this.thamNiem = thamNiem;
//	}
    
    

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

	public NhanVien(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam, String CCCD,
		DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh, ChucVu chucVu, TaiKhoan taiKhoan, boolean bHYT,
		boolean bHXH, PhongBan ban, String email, float heSoLuong, float thamNiem) {
	super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
	this.chucVu = chucVu;
	this.taiKhoan = taiKhoan;
	BHYT = bHYT;
	BHXH = bHXH;
	this.ban = ban;
	this.email = email;
	this.heSoLuong = heSoLuong;
	this.thamNiem = thamNiem;
}
	
	





	public NhanVien(String maNhanSu, String ho, String ten, String soDienThoai, Date ngaySinh, Date ngayVaoLam,
			String CCCD, DiaChi diaChi, PhuCap phuCap, GioiTinh gioiTinh, String anh, ChucVu chucVu, boolean bHYT,
			boolean bHXH, PhongBan ban, String email, float heSoLuong, float thamNiem) {
		super(maNhanSu, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gioiTinh, anh);
		this.chucVu = chucVu;
		BHYT = bHYT;
		BHXH = bHXH;
		this.ban = ban;
		this.email = email;
		this.heSoLuong = heSoLuong;
		this.thamNiem = thamNiem;
	}





	public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public boolean isBHYT() {
        return BHYT;
    }

    public void setBHYT(boolean BHYT) {
        this.BHYT = BHYT;
    }

    public boolean isBHXH() {
        return BHXH;
    }

    public void setBHXH(boolean BHXH) {
        this.BHXH = BHXH;
    }

    public PhongBan getBan() {
        return ban;
    }

    public void setBan(PhongBan ban) {
        this.ban = ban;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public float getThamNiem() {
        return thamNiem;
    }

    public void setThamNiem(float thamNiem) {
        this.thamNiem = thamNiem;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "chucVu=" + chucVu + ", taiKhoan=" + taiKhoan + ", BHYT=" + BHYT + ", BHXH=" + BHXH + ", ban=" + ban + ", email=" + email + ", heSoLuong=" + heSoLuong + ", thamNiem=" + thamNiem + '}';
    }
    
    public Vector<String> duLieuFormCapNhat(){
    	Vector<String> data = new Vector<>();
    	data.add(maNhanSu);
    	data.add(ho);
    	data.add(ten);
    	data.add(CCCD);
    	data.add(soDienThoai);
    	data.add(gioiTinh.getGioiTinh());
    	data.add(ban.getTenPhongBan());
    	data.add(chucVu.getTenChucVu());
    	data.add(diaChi.toString());
    	return data;
    }
    
   
}
