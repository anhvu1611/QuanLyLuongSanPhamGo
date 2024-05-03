package entity;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;

import utils.GhiThue;

public class BangLuongNhanVien implements Comparable<BangLuongNhanVien> {
	private String maBangLuong;
	private Date ngayLap;
	private float soNgayDiLam;
	private int soNgayLamCaNgay;
	private int soNgayLamNuaNgay;
	private int soNgayVangCoPhep;
	private int soNgayTre;
	private int soGioTangCa;
	private int soNgayVangKhongPhep;
	private NhanVien nhanVien;
	
	
	public BangLuongNhanVien(String maBangLuong, Date ngayLap, int soNgayDiLam, 
			int soNgayLamNguyenNgay, int soNgayLamNuaNgay, int soNgayVangCoPhep, int soNgayTre, int soGioTangCa,
			int soNgayVangKhongPhep, NhanVien nhanVien) {
		super();
		this.maBangLuong = maBangLuong;
		this.ngayLap = ngayLap;
		this.soNgayDiLam = soNgayDiLam;
		this.soNgayLamCaNgay = soNgayLamNguyenNgay;
		this.soNgayLamNuaNgay = soNgayLamNuaNgay;
		this.soNgayVangCoPhep = soNgayVangCoPhep;
		this.soNgayTre = soNgayTre;
		this.soGioTangCa = soGioTangCa;
		this.soNgayVangKhongPhep = soNgayVangKhongPhep;
		this.nhanVien = nhanVien;
	}
	
	

	public BangLuongNhanVien() {
		// TODO Auto-generated constructor stub
	}



	public String getMaBangLuong() {
		return maBangLuong;
	}



	public void setMaBangLuong(String maBangLuong) {
		this.maBangLuong = maBangLuong;
	}



	public Date getNgayLap() {
		return ngayLap;
	}



	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}



	public float getSoNgayDiLam() {
		return soNgayDiLam;
	}



	public void setSoNgayDiLam(int soNgayDiLam) {
		this.soNgayDiLam = soNgayDiLam;
	}


	public int getSoNgayLamNguyenNgay() {
		return soNgayLamCaNgay;
	}



	public void setSoNgayLamNguyenNgay(int soNgayLamNguyenNgay) {
		this.soNgayLamCaNgay = soNgayLamNguyenNgay;
	}



	public int getSoNgayLamNuaNgay() {
		return soNgayLamNuaNgay;
	}



	public void setSoNgayLamNuaNgay(int soNgayLamNuaNgay) {
		this.soNgayLamNuaNgay = soNgayLamNuaNgay;
	}



	public int getSoNgayVangCoPhep() {
		return soNgayVangCoPhep;
	}



	public void setSoNgayVangCoPhep(int soNgayVangCoPhep) {
		this.soNgayVangCoPhep = soNgayVangCoPhep;
	}



	public int getSoNgayTre() {
		return soNgayTre;
	}



	public void setSoNgayTre(int soNgayTre) {
		this.soNgayTre = soNgayTre;
	}



	public int getSoGioTangCa() {
		return soGioTangCa;
	}



	public void setSoGioTangCa(int soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}



	public int getSoNgayVangKhongPhep() {
		return soNgayVangKhongPhep;
	}



	public void setSoNgayVangKhongPhep(int soNgayVangKhongPhep) {
		this.soNgayVangKhongPhep = soNgayVangKhongPhep;
	}



	public NhanVien getNhanVien() {
		return nhanVien;
	}



	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
	/*
	 * Thu nhap tinh thuế là thu nhập của mỗi cá nhân chịu ảnh hưởng của thuế 
	 * Công thức: Thu Nhập tính thuế = thu Nhập cá nhân - (bảo hiểm - 11000000(tiền giảm trừ gia cảnh))
	 * nếu thu nhập tính thuế < 0 thì không bị ảnh hưởng bởi thuế
	 * */

	public double layThuNhapTinhThueCaNhan() {
		double thuNhapTinhThue = tongThuNhapCaNhanTruocThue()-khauTruTuBaoHiemYTeVaBaoHiemXaHoi()-11000000;
		if(thuNhapTinhThue <= 0) {
			return 0;
		}else {
			return Double.parseDouble(new DecimalFormat("#.##").format(thuNhapTinhThue));
		}
	}

	public double layThuePhaiTra() {
		double thuNhapTinhThue = layThuNhapTinhThueCaNhan();
		if(thuNhapTinhThue == 0) {
			return 0;
		}
		if(thuNhapTinhThue<=5000000) {
			return GhiThue.THUE_TNCN_DUOI_5TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue >5000000 && thuNhapTinhThue <=10000000) {
			return (GhiThue.THUE_TNCN_TU_5_TOI_10TRIEU*thuNhapTinhThue>250000)?250000:GhiThue.THUE_TNCN_TU_5_TOI_10TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue>10000000 && thuNhapTinhThue<=18000000) {
			return (GhiThue.THUE_TNCN_TU_10_TOI_18TRIEU*thuNhapTinhThue>750000)?750000:GhiThue.THUE_TNCN_TU_10_TOI_18TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue>=18000000 && thuNhapTinhThue<=32000000) {
			return (GhiThue.THUE_TNCN_TU_18_TOI_32TRIEU*thuNhapTinhThue>1650000)?1650000:GhiThue.THUE_TNCN_TU_18_TOI_32TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue>32000000 && thuNhapTinhThue<=52000000) {
			return (GhiThue.THUE_TNCN_TU_32_TOI_50TRIEU*thuNhapTinhThue>3250000)?3250000:GhiThue.THUE_TNCN_TU_32_TOI_50TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue>52000000 && thuNhapTinhThue<=80000000) {
			return (GhiThue.THUE_TNCN_TU_50_TOI_80TRIEU*thuNhapTinhThue>5850000)?5850000:GhiThue.THUE_TNCN_TU_50_TOI_80TRIEU*thuNhapTinhThue;
		}else if(thuNhapTinhThue>80000000) {
			return (GhiThue.THUE_TNCN_TREN_80*thuNhapTinhThue>9850000)?9850000:GhiThue.THUE_TNCN_TREN_80*thuNhapTinhThue;
		}else {
			return 0;
		}
	}
	
	
	public double khauTruTuBaoHiemYTeVaBaoHiemXaHoi() {
		double baoHiemYTe = 0, baoHiemXaHoi = 0;
		if(nhanVien.isBHXH()) {
			baoHiemXaHoi = 0.08;
		}
		if(nhanVien.isBHYT()) {
			baoHiemYTe = 0.015;
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double tongThuNhap = Double.parseDouble(decimalFormat.format(tongThuNhapCaNhanTruocThue()*baoHiemXaHoi+tongThuNhapCaNhanTruocThue()*baoHiemYTe));
		return tongThuNhap;
	}
	
	public double layLuongThucChoNhanVien() {
		return tongThuNhapCaNhanTruocThue()-khauTruTuBaoHiemYTeVaBaoHiemXaHoi()-layThuePhaiTra();
	}
	
	public double tongThuNhapTheoNgayCongLamViec() {
		float luongCoSo = this.getNhanVien().getChucVu().getHeSoLuong();
		float heSo = this.getNhanVien().getHeSoLuong();
		int vangCoPhep =0;
		if(soNgayVangCoPhep<=2) {
			vangCoPhep =0;
		}else {
			vangCoPhep = soNgayVangCoPhep;
		}
		double luong1ngay = (luongCoSo*heSo)/26;
		double tongThuNhap = luong1ngay*(soNgayDiLam-soNgayVangKhongPhep-vangCoPhep)-soNgayTre*50000+soGioTangCa*((luongCoSo*heSo)/26/8*1.5);
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        tongThuNhap = Double.parseDouble(decimalFormat.format(tongThuNhap));
		return tongThuNhap;
	}
	
	public double tongThuNhapCaNhanTruocThue() {
        double totalIncome = tongThuNhapTheoNgayCongLamViec() + nhanVien.getPhuCap().getGiaTri();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedTotalIncome = decimalFormat.format(totalIncome);
        return Double.parseDouble(formattedTotalIncome);
	}
	
	public Vector<String> hienThiDanhSachBangLuong(){
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		Vector<String> data = new Vector<>();
		data.add(maBangLuong);
		data.add(nhanVien.getMaNhanSu());
		data.add(nhanVien.getHo()+ " "+nhanVien.getTen());
		data.add(nhanVien.getChucVu().getTenChucVu());
		data.add(nhanVien.getBan().getTenPhongBan());
		data.add(nhanVien.getChucVu().getHeSoLuong()+"");
		data.add(nhanVien.getHeSoLuong()+"");
		data.add(layThuePhaiTra()+"");
		data.add(decimalFormat.format(layLuongThucChoNhanVien()));
		return data;
	}



	public int getSoNgayLamCaNgay() {
		return soNgayLamCaNgay;
	}



	public void setSoNgayLamCaNgay(int soNgayLamCaNgay) {
		this.soNgayLamCaNgay = soNgayLamCaNgay;
	}



	public void setSoNgayDiLam(float soNgayDiLam) {
		this.soNgayDiLam = soNgayDiLam;
	}



	@Override
	public String toString() {
		return "BangLuongNhanVien [soNgayDiLam=" + soNgayDiLam + ", soNgayLamCaNgay=" + soNgayLamCaNgay
				+ ", soNgayLamNuaNgay=" + soNgayLamNuaNgay + ", soNgayVangCoPhep=" + soNgayVangCoPhep + ", soNgayTre="
				+ soNgayTre + ", soGioTangCa=" + soGioTangCa + ", layThuePhaiTra()=" + layThuePhaiTra()
				+ ", khauTruTuBaoHiemYTeVaBaoHiemXaHoi()=" + khauTruTuBaoHiemYTeVaBaoHiemXaHoi()
				+ ", layLuongThucChoNhanVien()=" + layLuongThucChoNhanVien() + ", tongThuNhapTheoNgayCongLamViec()="
				+ tongThuNhapTheoNgayCongLamViec() + ", tongThuNhapCaNhanTruocThue()=" + tongThuNhapCaNhanTruocThue()
				+ "]";
	}



	@Override
	public int compareTo(BangLuongNhanVien o) {
		return Double.compare(this.layLuongThucChoNhanVien(), o.layLuongThucChoNhanVien());
	}
}	
