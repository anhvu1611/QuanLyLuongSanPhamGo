package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.crypto.Data;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import Enum.CaLamCongNhan;
import Enum.CaLamNhanVien;
import Enum.TrangThai;
import entity.BangChamCongCongNhan;
import entity.BangChamCongNhanVien;
import entity.BangPhanCongCongDoan;
import entity.CongNhan;
import entity.NhanSu;
import entity.NhanVien;


public class BangChamCongCongNhan_Dao {
	private Connection con = ConnectDB.getConnection();
	private BangPhanCongCongDoan_Dao bangPhanCong_Dao = new BangPhanCongCongDoan_Dao();
	private NhanVien_Dao nhanVien_dao = new NhanVien_Dao();
	private CongNhan_Dao congNhan_dao = new CongNhan_Dao();
	
	public int laySoLuongDaChamCongTheoMaCongDoan(String maCongDoan) {
	    int soLuong = 0;
	    Connection con = null;
//	    String sql = "SELECT SUM(bcccn.soLuongHoanThanh) AS soLuong " +
//	                 "FROM BangChamCongCongNhan bcccn " +
//	                 "JOIN CongNhan cn ON cn.maCongNhan = bcccn.maCongNhan " +
//	                 "JOIN BangPhanCongCongDoan bcc ON bcc.maCongNhan = cn.maCongNhan " +
//	                 "WHERE bcc.maCongDoan = ? " +
//	                 "GROUP BY bcc.maCongDoan";
	    String sql = "select sum(soLuongHoanThanh)  from BangChamCongCongNhan where maCongDoan = ?";

	    try  {
	        con = Database.getConnectTion();
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maCongDoan);  
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            soLuong = rs.getInt(1);
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return soLuong;
	}
	
	//tìm chấm công theo mã công nhân
	public ArrayList<BangChamCongCongNhan> timChamCongTheoMa(String maCongNhan) {
		ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
		String sql = "select * from BangChamCongCongNhan where maCongNhan= ? and soLuongHoanThanh!=0";
		BangChamCongCongNhan bcccn = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, maCongNhan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maCCCN = rs.getString(1);
				Date date = rs.getDate(3);
				boolean trangThai = rs.getBoolean(2);
				int soLuong = rs.getInt(4);
				NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				//CongNhan congNhan = congNhan_dao.timChuyenMonPhuCapCongNhanBangMa(rs.getString(6));	
				CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString(6));	
				//kiểm tra hằng
				CaLamCongNhan caLam = null;
				String caLamCongNhan = rs.getNString(5).trim();
				
				if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
					caLam = CaLamCongNhan.CAMOT;
				}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
					caLam = CaLamCongNhan.CAHAI;
				}else {
					caLam = CaLamCongNhan.CABA;
				}
				BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhan(rs.getString(6));
				
				BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
				dsCCCN.add(bangChamCong);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsCCCN;
		
	}
	//tìm chấm công tháng theo mã công nhân
	public ArrayList<BangChamCongCongNhan> timChamCongTheoThangVaTheoMa(String maCongNhan, int thang) {
		ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
		String sql = "select * from BangChamCongCongNhan where maCongNhan= ? and soLuongHoanThanh!=0 and MONTH(ngayChamCong)=?";
		BangChamCongCongNhan bcccn = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, maCongNhan);
			stmt.setInt(2, thang);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maCCCN = rs.getString(1);
				Date date = rs.getDate(3);
				boolean trangThai = rs.getBoolean(2);
				int soLuong = rs.getInt(4);
				NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				//CongNhan congNhan = congNhan_dao.timChuyenMonPhuCapCongNhanBangMa(rs.getString(6));	
				CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString(6));	
				//kiểm tra hằng
				CaLamCongNhan caLam = null;
				String caLamCongNhan = rs.getString(5).trim();
				if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
					caLam = CaLamCongNhan.CAMOT;
				}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
					caLam = CaLamCongNhan.CAHAI;
				}else {
					caLam = CaLamCongNhan.CABA;
				}
				BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhan(rs.getString(6));
				
				BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
				dsCCCN.add(bangChamCong);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsCCCN;
		
	}
	
	//lấy danh sách chấm công công nhân
	public ArrayList<BangChamCongCongNhan> layDanhSachChamCongCN(int thang) {
		String sql = "select * from BangChamCongCongNhan where MONTH(ngayChamCong) = ? ";
		ArrayList<BangChamCongCongNhan> dsCCCN = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, thang);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String userInput;
				String maCCCN = rs.getString(1);
				Date date = rs.getDate(3);
				boolean trangThai = rs.getBoolean(2);
				int soLuong = rs.getInt(4);
				NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString(6));	
				//kiểm tra hằng
				CaLamCongNhan caLam = null;
				String caLamCongNhan = rs.getString(5).trim();
				System.out.println("Ca làm DAO: " + caLamCongNhan);
				if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
					caLam = CaLamCongNhan.CAMOT;
				}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
					caLam = CaLamCongNhan.CAHAI;
				}else {
					caLam = CaLamCongNhan.CABA;
				}	
				BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhan(congNhan.getMaNhanSu());
				if(bangPhanCong==null)
					continue;
				
				BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
				
				
				dsCCCN.add(bangChamCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCCCN;
	}
	
	//lấy danh sách chấm công công nhân hôm nay
	public ArrayList<BangChamCongCongNhan> layDanhSachChamCongCNHomNay(String homNay) {
		String sql = "select * from BangChamCongCongNhan where ngayChamCong= ? ";
		ArrayList<BangChamCongCongNhan> dsCCCNHomNay = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, homNay);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maCCCN = rs.getString(1);
				Date date = rs.getDate(3);
				boolean trangThai = rs.getBoolean(2);
				int soLuong = rs.getInt(4);
				NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString(7));
				CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString(6));	
				//kiểm tra hằng
				CaLamCongNhan caLam = null;
				String caLamCongNhan = rs.getString(5).trim();
				if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
					caLam = CaLamCongNhan.CAMOT;
				}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
					caLam = CaLamCongNhan.CAHAI;
				}else {
					caLam = CaLamCongNhan.CABA;
				}
				BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhan(rs.getString(6));
				if(bangPhanCong== null)
					continue;
				BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
				dsCCCNHomNay.add(bangChamCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCCCNHomNay;
	}
	//Thêm chấm công công nhân
	public boolean themChamcongCongNhan(BangChamCongCongNhan cccnMoi, String maNguoiChamCong) {
		String sql = "insert into BangChamCongCongNhan (maBangChamCongCongNhan, trangThai,ngayChamCong,soLuongHoanThanh,calam,maCongNhan,maNhanVienChamCong, maCongDoan)\r\n"
				+ "values (?,?,?,?,?,?,?,?)";
		java.sql.Date sqlDate = new java.sql.Date(cccnMoi.getNgayChamCong().getTime());

		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, cccnMoi.getMaBangChamCong());
			stmt.setBoolean(2, cccnMoi.isTrangThai());
			stmt.setDate(3, sqlDate);
			stmt.setInt(4, cccnMoi.getSoLuongSanPhamHoanThanh());
			stmt.setString(5, cccnMoi.getCaLam().toString());
			stmt.setString(6, cccnMoi.getChamCongDoan().getCongNhanDamNhan().getMaNhanSu());
			//Sửa lại người chấm
			stmt.setString(7,  maNguoiChamCong);
			stmt.setString(8, cccnMoi.getChamCongDoan().getCongDoan().getMaCongDoan());
			stmt.executeUpdate();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Lấy mã chấm công lớn nhất
			public String layMaChamCongCaoNhat() {
				String sql = "select MAX(maBangChamCongCongNhan) from BangChamCongCongNhan";
				try (PreparedStatement stmt = con.prepareStatement(sql)) {
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getString(1));
						if(rs.getString(1)==null) {
							break;
						}
						else return rs.getString(1);
					}					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "CCCN0000";
			}

			public ArrayList<BangChamCongCongNhan> timChamCongTheoNguoiNhanLuongVaThangCham(String hoTen, int i) {
				ArrayList<BangChamCongCongNhan> ds= new ArrayList<>();
				try {
					Connection con = Database.getConnectTion();
					String Sql = "select * from [dbo].[BangChamCongCongNhan] bcc join CongNhan cn on cn.maCongnhan = bcc.maCongNhan\r\n"
							+ "where ho+' '+ten = ? and month(ngayChamCong) =? and soLuongHoanThanh >0";
					PreparedStatement statement = con.prepareStatement(Sql);
					statement.setNString(1, hoTen);
					statement.setInt(2, i);
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						String maCCCN = rs.getString(1);
						Date date = rs.getDate(3);
						boolean trangThai = rs.getBoolean(2);
						int soLuong = rs.getInt(4);
						NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString("maNhanVienChamCong"));
						CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString("maCongNhan"));	
						//kiểm tra hằng
						CaLamCongNhan caLam = null;
						String caLamCongNhan = rs.getString(5).trim();
						if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
							caLam = CaLamCongNhan.CAMOT;
						}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
							caLam = CaLamCongNhan.CAHAI;
						}else {
							caLam = CaLamCongNhan.CABA;
						}
						BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhanVaNgayCham(rs.getString("maCongNhan"), date);
						if(bangPhanCong== null)
							continue;
						BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
						ds.add(bangChamCong);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				return ds;
			}
			
			
//			public ArrayList<Vector<String>> laydsChamCongTheoNguoiNhanLuongVaThangCham(String hoTen, int i) {
//				ArrayList<Vector<String>> ds= new ArrayList<>();
//				try {
//					Connection con = Database.getConnectTion();
//					String Sql = "select * from [dbo].[BangChamCongCongNhan] bcc join CongNhan cn on cn.maCongnhan = bcc.maCongNhan\r\n"
//							+ "where ho+' '+ten = ? and month(ngayChamCong) =? and soLuongHoanThanh >0";
//					PreparedStatement statement = con.prepareStatement(Sql);
//					statement.setNString(1, hoTen);
//					statement.setInt(2, i);
//					ResultSet rs = statement.executeQuery();
//					int in = 0;
//					while(rs.next()) {
//						String maCCCN = rs.getString(1);
//						Date date = rs.getDate(3);
//						boolean trangThai = rs.getBoolean(2);
//						int soLuong = rs.getInt(4);
//						NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(rs.getString("maNhanVienChamCong"));
//						CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString("maCongNhan"));	
//						//kiểm tra hằng
//						CaLamCongNhan caLam = null;
//						String caLamCongNhan = rs.getString(5).trim();
//						if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
//							caLam = CaLamCongNhan.CAMOT;
//						}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
//							caLam = CaLamCongNhan.CAHAI;
//						}else {
//							caLam = CaLamCongNhan.CABA;
//						}
//						BangPhanCongCongDoan bangPhanCong = bangPhanCong_Dao.timPhanCongCongDoanTheoMaCongNhanVaNgayCham(rs.getString("maCongNhan"), date);
//						if(bangPhanCong== null)
//							continue;
//						BangChamCongCongNhan bangChamCong = new BangChamCongCongNhan(maCCCN, date, trangThai, soLuong, nguoiCham, caLam, bangPhanCong);
//						ds.add(bangChamCong);
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				return ds;
//			}
			
			
		public ArrayList<Vector<String>> layDanhSachChamCongTheoMaCongNhanVaThang(String maCongNhan, int thang){
			ArrayList<Vector<String>> ds = new ArrayList<>();
			try {
				Connection con = Database.getConnectTion();
				String sql = "select * from dbo.dsChamCongTinhLuongTheoMa(?, ?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, maCongNhan);
				statement.setInt(2, thang);
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					String maSanPham = rs.getString(1);
					String tenSanPham = rs.getString(2);
					String maCongDoan = rs.getString(3);
					String loaiCongDoan = rs.getString(4);
					String caLam = rs.getString(5);
					int tong = rs.getInt(6);
					double tongTien = rs.getDouble(7);
					Vector<String> data = new Vector<>();
					data.add(maSanPham);
					data.add(tenSanPham);
					data.add(maCongDoan);
					data.add(loaiCongDoan);
					data.add(caLam);
					data.add(tong+"");
					data.add(tongTien+"");
					ds.add(data);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return ds;
		}
			
			
}
