package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import Enum.LoaiCongDoan;
import entity.BangChamCongNhanVien;
import entity.BangPhanCongCongDoan;
import entity.CongDoan;
import entity.CongNhan;
import entity.SanPham;


public class BangPhanCongCongDoan_Dao {
	private Connection con = ConnectDB.getConnection();
	private CongDoan_Dao congDoan_dao = new CongDoan_Dao();
	private CongNhan_Dao congNhan_dao = new CongNhan_Dao();
	private SanPham_Dao sanPham_dao = new SanPham_Dao();
	//Lấy danh sách phân công
	public List<BangPhanCongCongDoan> layDanhSachPhanCongCN() {
		List<BangPhanCongCongDoan> dsPCCN = new ArrayList<BangPhanCongCongDoan>();
		BangPhanCongCongDoan bangPCCN;
		String sql = "select * from BangPhanCongCongDoan where soLuongConLai >0";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CongDoan congDoan = congDoan_dao.timCongDoanTheoMa(rs.getString(2));
				CongNhan congNhan = congNhan_dao.timCongNhanBangMa(rs.getString(3));
				
				bangPCCN = new BangPhanCongCongDoan(rs.getInt(1), congDoan, congNhan, rs.getInt(5));
				dsPCCN.add(bangPCCN);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPCCN;
	}
	
	
	//Tìm phân công theo mã công nhân
	public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhan(String maCongNhan) {
		BangPhanCongCongDoan bangPhanCong = null;
		String sql = "select * from BangPhanCongCongDoan where maCongNhan = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCongNhan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int soLuong = rs.getInt("soLuong");
				String maCongDoan = rs.getString("maCongDoan");
				String ma = rs.getString("maCongNhan");
				LocalDate ngayPhanCong = rs.getDate("ngayPhanCong").toLocalDate();
				int soLuongConLai = rs.getInt("soLuongConLai");
				bangPhanCong = new BangPhanCongCongDoan(soLuong, CongDoan_Dao.timCongDoanKhiCoMa(maCongDoan), CongNhan_Dao.timCongNhanBangMa(ma), ngayPhanCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangPhanCong;
	}
	
	
	public BangPhanCongCongDoan timPhanCongCongDoanTheoMaCongNhanVaNgayCham(String maCongNhan, Date ngayCham) {
		BangPhanCongCongDoan bangPhanCong = null;
		String sql = "select * from BangPhanCongCongDoan where maCongNhan = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCongNhan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int soLuong = rs.getInt("soLuong");
				String maCongDoan = rs.getString("maCongDoan");
				String ma = rs.getString("maCongNhan");
				LocalDate ngayPhanCong = rs.getDate("ngayPhanCong").toLocalDate();
				int soLuongConLai = rs.getInt("soLuongConLai");
				bangPhanCong = new BangPhanCongCongDoan(soLuong, CongDoan_Dao.timCongDoanKhiCoMa(maCongDoan), CongNhan_Dao.timCongNhanBangMa(ma), ngayPhanCong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangPhanCong;
	}
	
	//Tìm phân công theo mã công nhân và mã công đoạn
	public BangPhanCongCongDoan timTheoMaCongNhanVaMaCongDoan(String maCongNhan, String maCongDoan) {
		BangPhanCongCongDoan bangPhanCong = null;
		CongDoan_Dao congDoan_Dao = new CongDoan_Dao();
		CongNhan_Dao congNhan_Dao = new CongNhan_Dao();
		String sql = "select * from BangPhanCongCongDoan where maCongNhan = ? and maCongDOan = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCongNhan);
			stmt.setString(2, maCongDoan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				bangPhanCong = new BangPhanCongCongDoan(rs.getInt(1),congDoan_Dao.timCongDoanTheoMa(maCongDoan),congNhan_Dao.timCongNhanBangMa(maCongNhan), rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bangPhanCong;
	}

	//Update lại số lượng bảng phân công
	public boolean capNhatSoLuongBangCongDoan(int soLuongConLai, String maCongDoan, String maCongNhan) {
		
		String sql = "update BangPhanCongCongDoan set soLuongConLai = ? where maCongDoan = ? and maCongNhan = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, soLuongConLai);
			stmt.setString(2, maCongDoan);
			stmt.setString(3, maCongNhan);
			stmt.executeUpdate();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoan() {
		ArrayList<BangPhanCongCongDoan> data = new ArrayList<>();
		try {
			Connection conn = Database.getConnectTion();
			String sql = "SELECT TOP (1000) [maCongDoan]\r\n"
					+ "      ,[maCongNhan]\r\n"
					+ "      ,[tenCongDoan]\r\n"
					+ "      ,[tenCongNhan]\r\n"
					+ "      ,[soLuongPhanCong]\r\n"
					+ "      ,[ngayPhanCong]\r\n"
					+ "      ,[maSanPham]\r\n"
					+ "      ,[tenSanPham]\r\n"
					+ "  FROM [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan]";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maCongDoan = rs.getString("maCongDoan");
				String maCongNhan = rs.getString("maCongNhan");
				int soLuongPhanCong = rs.getInt("soLuongPhanCong");
				LocalDate ngayPhanCong = rs.getDate("ngayPhanCong").toLocalDate();

				CongDoan congDoan = congDoan_dao.timCongDoanTheoMa(maCongDoan) ;			
				CongNhan congNhan = congNhan_dao.timCongNhanTheoMa(maCongNhan);
				BangPhanCongCongDoan bangPhanCong = new BangPhanCongCongDoan(soLuongPhanCong, congDoan, congNhan, ngayPhanCong);
				data.add(bangPhanCong);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
