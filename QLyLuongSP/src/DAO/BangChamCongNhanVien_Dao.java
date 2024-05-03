package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import ConnectDB.ConnectDB;
import Enum.CaLamNhanVien;
import Enum.TrangThai;
import entity.BangChamCongNhanVien;
import entity.NhanVien;

public class BangChamCongNhanVien_Dao {
	private Connection con = ConnectDB.getConnection();
	private NhanVien_Dao nhanVien_dao = new NhanVien_Dao();

	//lấy danh sách tất cả chấm công nhân viên
	public ArrayList<BangChamCongNhanVien> layDanhSachChamCongNV() {
		String sql = "select * from BangChamCongNhanVien";
		ArrayList<BangChamCongNhanVien> dsCCNV = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString(1);
				Date ngayChamCong = rs.getDate(3);
				NhanVien nv = nhanVien_dao.timNhanVienTheoMa(rs.getString(6));
				TrangThai trangThai = null;
				String trangThaiNV = rs.getString(2).trim();
				if(trangThaiNV.equalsIgnoreCase("Đi làm")) {
					trangThai = TrangThai.COLAM;
				}else if(trangThaiNV.equalsIgnoreCase("Đi trễ")) {
					trangThai = TrangThai.DITRE;
				}else if(trangThaiNV.equalsIgnoreCase("Nghỉ có phép")) {
					trangThai = TrangThai.NGHICOPHEP;
				}else {
					trangThai = TrangThai.NGHIKHONGPHEP;
				}
				String ghiChu = rs.getString(4);
				CaLamNhanVien caLam = null;
				String caLamNhanVien = rs.getString(5).trim();
				
				if(caLamNhanVien.equalsIgnoreCase("Full-time")) {
					caLam = CaLamNhanVien.FULLTIME;
				}else if(caLamNhanVien.equalsIgnoreCase("Part-time")) {
					caLam = CaLamNhanVien.PARTTIME;
				}else {
					caLam = CaLamNhanVien.TANGCA;
				}
				NhanVien nvCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				BangChamCongNhanVien bangChamCong = new BangChamCongNhanVien(maChamCong, ngayChamCong, nvCham, trangThai, ghiChu, caLam, nv);
				dsCCNV.add(bangChamCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCCNV;
	}
	
	//lấy danh sách tất cả chấm công nhân viên
		public ArrayList<BangChamCongNhanVien> layDanhSachChamCongTheoPhongBanHomNay(String phongBan) {
			String sql = "select maBangChamCongNhanVien, trangThai, ngayChamCong, ghiChu, caLam, maNhanVienDuocChamCong, maNhanVienChamCong, soGio\r\n"
					+ "from BangChamCongNhanVien bccnv\r\n"
					+ "left join NhanVien nv on bccnv.maNhanVienDuocChamCong = nv.maNhanVien\r\n"
					+ "left join PhongBan pb on nv.maPhongBan = pb.maPhongBan\r\n"
					+ "where ngayChamCong=FORMAT(GETDATE(), 'yyyy-MM-dd') and pb.tenPhongBan = ?";
			
			ArrayList<BangChamCongNhanVien> dsCCNV = new ArrayList<>();
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setNString(1, phongBan);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maChamCong = rs.getString(1);
					Date ngayChamCong = rs.getDate(3);
					NhanVien nv = nhanVien_dao.timNhanVienTheoMa(rs.getString(6));
					TrangThai trangThai = null;
					String trangThaiNV = rs.getString(2).trim();
					if(trangThaiNV.equalsIgnoreCase("Đi làm")) {
						trangThai = TrangThai.COLAM;
					}else if(trangThaiNV.equalsIgnoreCase("Đi trễ")) {
						trangThai = TrangThai.DITRE;
					}else if(trangThaiNV.equalsIgnoreCase("Nghỉ có phép")) {
						trangThai = TrangThai.NGHICOPHEP;
					}else {
						trangThai = TrangThai.NGHIKHONGPHEP;
					}
					String ghiChu = rs.getString(4);
					CaLamNhanVien caLam = null;
					String caLamNhanVien = rs.getString(5).trim();
					
					if(caLamNhanVien.equalsIgnoreCase("Full-time")) {
						caLam = CaLamNhanVien.FULLTIME;
					}else if(caLamNhanVien.equalsIgnoreCase("Part-time")) {
						caLam = CaLamNhanVien.PARTTIME;
					}else {
						caLam = CaLamNhanVien.TANGCA;
					}
					NhanVien nvCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
					int soGioTangCa = rs.getInt("soGio");
					BangChamCongNhanVien bangChamCong = new BangChamCongNhanVien(maChamCong, ngayChamCong, nvCham, trangThai, ghiChu, caLam, nv, soGioTangCa);
					dsCCNV.add(bangChamCong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsCCNV;
		}
	
	//lấy danh sách chấm công nhân viên hôm nay
	public ArrayList<BangChamCongNhanVien> layDanhSachChamCongHomNay(String homNay) {
		String sql = "select * from BangChamCongNhanVien where ngayChamCong=?";
		ArrayList<BangChamCongNhanVien> dsCCNVHomNay = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, homNay);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maChamCong = rs.getString(1);
				Date ngayChamCong = rs.getDate(3);
				NhanVien nv = nhanVien_dao.timNhanVienTheoMa(rs.getString(6));	
				TrangThai trangThai = null;
				String trangThaiNV = rs.getString(2).trim();
				if(trangThaiNV.equalsIgnoreCase("Đi làm")) {
					trangThai = TrangThai.COLAM;
				}else if(trangThaiNV.equalsIgnoreCase("Đi trễ")) {
					trangThai = TrangThai.DITRE;
				}else if(trangThaiNV.equalsIgnoreCase("Nghỉ có phép")) {
					trangThai = TrangThai.NGHICOPHEP;
				}else {
					trangThai = TrangThai.NGHIKHONGPHEP;
				}			
				String ghiChu = rs.getString(4);			
				CaLamNhanVien caLam = null;
				String caLamNhanVien = rs.getString(5).trim();
				if(caLamNhanVien.equalsIgnoreCase("Full-time")) {
					caLam = CaLamNhanVien.FULLTIME;
				}else if(caLamNhanVien.equalsIgnoreCase("Part-time")) {
					caLam = CaLamNhanVien.PARTTIME;
				}else {
					caLam = CaLamNhanVien.TANGCA;
				}				
				NhanVien nvCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				int soGioTangCa = rs.getInt("soGio");
				BangChamCongNhanVien bangChamCong = new BangChamCongNhanVien(maChamCong, ngayChamCong, nvCham, trangThai, ghiChu, caLam, nv,soGioTangCa);
				dsCCNVHomNay.add(bangChamCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCCNVHomNay;
	}
	
	//Lấy danh sách nhân viên chưa chấm công hôm nay
		public ArrayList<NhanVien> layDanhSachChuaCham() {
			ArrayList<String> dsMaChuaCham = layDanhSachMaNhanVienChuaChamCong();
			ArrayList<NhanVien> dsNhanVienChuaCham = new ArrayList<NhanVien>();
			for(String nvChuaCham : dsMaChuaCham) {
				NhanVien nv = nhanVien_dao.timNhanVienTheoMa(nvChuaCham);
				if (dsNhanVienChuaCham.contains(nv)) {
					dsNhanVienChuaCham.remove(nv);
		        }
				dsNhanVienChuaCham.add(nv);
			}
			return dsNhanVienChuaCham;
			
		}
		
		
		//Lấy danh sách tất cả mã nhân viên
		public ArrayList<String> layDanhSachMaNhanVien() {
			String sql = "select maNhanVien from NhanVien ";
			ArrayList<String> dsMaNhanVien = new ArrayList<String>();
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					String maNhanVien = rs.getString(1);
					dsMaNhanVien.add(maNhanVien);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsMaNhanVien;
		}
		
		
		//Lấy danh sách mã nhân viên đã chấm hôm nay
		public ArrayList<String> layDanhSachMaNhanVienDaChamCong() {
			String sql = "select maNhanVienDuocChamCong from BangChamCongNhanVien where ngayChamCong=FORMAT(GETDATE(), 'yyyy-MM-dd')";
			ArrayList<String> dsMaNhanVienChuaChamCong = new ArrayList<String>();
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					String maChamCong = rs.getString(1);
					dsMaNhanVienChuaChamCong.add(maChamCong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsMaNhanVienChuaChamCong;
		}
		//Lấy danh sách mã nhân viên chưa chấm hôm nay
		public ArrayList<String> layDanhSachMaNhanVienChuaChamCong() {
			ArrayList<String> dsTatCaMaNhanVien = layDanhSachMaNhanVien();
			ArrayList<String> dsMaDaCham = layDanhSachMaNhanVienDaChamCong();
			ArrayList<String> dsMaChuaCham = new ArrayList<String>();
			if(dsMaDaCham.isEmpty()) {
				dsMaChuaCham = dsTatCaMaNhanVien;
			}else {
				dsMaChuaCham = new ArrayList<>(new HashSet<>(dsTatCaMaNhanVien));
				dsMaChuaCham.removeAll(dsMaDaCham);
				Collections.sort(dsMaChuaCham);
			}
			return dsMaChuaCham;
			
		}
		


		
		
		//Thêm chấm công nhân viên
		public boolean themChamCongNhanVien(BangChamCongNhanVien ccnvMoi) {
			String sql = "INSERT INTO BangChamCongNhanVien (maBangChamCongNhanVien, trangThai, ngayChamCong,ghiChu, caLam, maNhanVienDuocChamCong, maNhanVienChamCong, soGio)\r\n"
					+ "values (?,?,?,?,?,?,?,? ) ";
			java.sql.Date sqlDate = new java.sql.Date(ccnvMoi.getNgayChamCong().getTime());
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, ccnvMoi.getMaChamCong());
				stmt.setString(2,ccnvMoi.getTrangThai().toString() );
				stmt.setDate(3, sqlDate);
				stmt.setString(4, ccnvMoi.getGhiChu());
				stmt.setString(5, ccnvMoi.getCaLam().toString());
				stmt.setString(6, ccnvMoi.getNhanVienDuocChamCong().getMaNhanSu());
				stmt.setString(7,  ccnvMoi.getDoiTuongChamCong().getMaNhanSu());
				stmt.setInt(8, ccnvMoi.getSoGioTangCa());
				stmt.executeUpdate();
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return false;
		}
		//Xóa chấm công nhân viên
		public boolean xoaChamCongNhanVien(String maChamCong) {
			int dem = 0;

			String sql = "delete from BangChamCongNhanVien where maBangChamCongNhanVien = ?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, maChamCong);

				dem = stmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return dem > 0;
		}
		
		//cập nhật trạng thái chấm công
		public boolean capNhatChamCongNV(BangChamCongNhanVien nhanVienDuocCapNhat) {
			String sql = "update BangChamCongNhanVien set trangThai = ? where maBangChamCongNhanVien = ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, nhanVienDuocCapNhat.getTrangThai().toString());
				stmt.setString(2, nhanVienDuocCapNhat.getMaChamCong());
				stmt.executeUpdate();
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return false;
		}	
		
		//Lấy mã chấm công lớn nhất
		public String layMaChamCongCaoNhat() {
			String sql = "select MAX(maBangChamCongNhanVien) from BangChamCongNhanVien";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					return rs.getString(1);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		//Lấy danh sách tất cả mã nhân viên theo phòng ban
		public ArrayList<String> layDanhSachMaNhanVienTheoPhongBan(String tenPhongBan) {
			ArrayList<String> dsMaNhanVienTheoPhongBan = new ArrayList<String>();
			String sql = "select maNhanVien \r\n"
					+ "from NhanVien nv\r\n"
					+ "left join PhongBan pb on nv.maPhongBan = pb.maPhongBan\r\n"
					+ "where pb.tenPhongBan = ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setNString(1, tenPhongBan);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					dsMaNhanVienTheoPhongBan.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsMaNhanVienTheoPhongBan;		
		}
		//Lấy danh sách mã nhân viên chưa chấm hôm nay
		public ArrayList<String> layDanhSachMaNhanVienChuaChamCongTheoPhongBan(String tenPhongBan) {
			ArrayList<String> dsMaNhanVienTheoPhongBan = layDanhSachMaNhanVienTheoPhongBan(tenPhongBan);
			ArrayList<String> dsMaDaCham = layDanhSachMaNhanVienDaChamCong();
			ArrayList<String> dsMaChuaCham = new ArrayList<String>();
			if(dsMaDaCham.isEmpty()) {
				dsMaChuaCham = dsMaNhanVienTheoPhongBan;
			}else {
				dsMaChuaCham = new ArrayList<>(new HashSet<>(dsMaNhanVienTheoPhongBan));
				dsMaChuaCham.removeAll(dsMaDaCham);
				Collections.sort(dsMaChuaCham);
			}
			return dsMaChuaCham;
			
		}
		
		//Lấy danh sách nhân viên chưa chấm công hôm nay
		public ArrayList<NhanVien> layDanhSachChuaChamTheoPhongBan(String tenPhongBan) {
			ArrayList<String> dsMaChuaCham = layDanhSachMaNhanVienChuaChamCongTheoPhongBan(tenPhongBan);
			ArrayList<NhanVien> dsNhanVienChuaCham = new ArrayList<NhanVien>();
			for(String nvChuaCham : dsMaChuaCham) {
				NhanVien nv = nhanVien_dao.timNhanVienTheoMa(nvChuaCham);
				if (dsNhanVienChuaCham.contains(nv)) {
					dsNhanVienChuaCham.remove(nv);
		        }
				dsNhanVienChuaCham.add(nv);
			}
			return dsNhanVienChuaCham;
			
		}
}
