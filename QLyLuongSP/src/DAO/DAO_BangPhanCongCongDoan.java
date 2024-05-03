package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import entity.BangPhanCongCongDoan;
import entity.CongDoan;
import entity.CongNhan;

public class DAO_BangPhanCongCongDoan {
	private ArrayList<BangPhanCongCongDoan> ds;
	private DAO_CongDoan congDoan_Dao;
	private CongNhan_Dao congNhan_Dao;
	public DAO_BangPhanCongCongDoan() {
		ds = new ArrayList<BangPhanCongCongDoan>();
	}
	
	

	public ArrayList<BangPhanCongCongDoan> getDs() {
		return ds;
	}

	public ArrayList<BangPhanCongCongDoan> layDanhSachPhanCongCongDoanTheoMaCongDoan(String maCongDoan) {
	    ArrayList<BangPhanCongCongDoan> data = new ArrayList<>();
	    try {
	        Connection conn = Database.getConnectTion();
	        String sql = "SELECT [maCongDoan], [maCongNhan], [soLuong], [ngayPhanCong] FROM [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan] WHERE [maCongDoan] = ?";
	        try (PreparedStatement statement = conn.prepareStatement(sql)) {
	            statement.setString(1, maCongDoan);
	            ResultSet rs = statement.executeQuery();

	            congDoan_Dao = new DAO_CongDoan();
	            congNhan_Dao = new CongNhan_Dao();

	            while (rs.next()) {
	                String maCongNhan = rs.getString("maCongNhan");
	                int soLuongPhanCong = rs.getInt("soLuong");
	                LocalDate ngayPhanCong = rs.getDate("ngayPhanCong").toLocalDate();

	                CongDoan congDoan = congDoan_Dao.timCongDoanTheoMa(maCongDoan);
	                CongNhan congNhan = congNhan_Dao.timCongNhanBangMa(maCongNhan);
	                BangPhanCongCongDoan bangPhanCong = new BangPhanCongCongDoan(soLuongPhanCong, congDoan, congNhan, ngayPhanCong);
	                data.add(bangPhanCong);
	            }
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return data;
	}
	
	public int laySoLuongPhanCong(String maCongDoan) {
	    Connection conn = Database.getConnectTion();
	    String sql = "SELECT COUNT(*) AS soDong FROM BangPhanCongCongDoan WHERE maCongDoan = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        // Thiết lập tham số sau đó thực hiện truy vấn
	        stmt.setString(1, maCongDoan);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            return rs.getInt("soDong");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng kết nối sau khi sử dụng
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    // Trả về 0 nếu có lỗi hoặc không tìm thấy dữ liệu
	    return 0;
	}
	
	
	
	public void themPhanCongCongDoan(BangPhanCongCongDoan phanCong) {
	    try {
	        Connection conn = Database.getConnectTion();
	        String sql = "INSERT INTO [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan] " +
	                "VALUES (?, ?, ?, ?, ?)";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(2, phanCong.getCongDoan().getMaCongDoan());
	        statement.setString(3, phanCong.getCongNhanDamNhan().getMaNhanSu());
	        statement.setInt(1, phanCong.getSoLuongPhanCong());
	        statement.setDate(4, java.sql.Date.valueOf(phanCong.getNgayPhanCong()));
	        statement.setInt(5, phanCong.getSoLuongPhanCong());
	        statement.executeUpdate();
	        conn.close();

	        // Thêm vào danh sách
	        ds.add(phanCong);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Xử lý lỗi, có thể hiển thị thông báo lỗi
	    }
	}
	public void suaPhanCongCongDoan(BangPhanCongCongDoan phanCong) {
	    try {
	        Connection conn = Database.getConnectTion();
	        String sql = "UPDATE [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan] " +
	                     "SET maCongDoan = ?, maCongNhan = ?, soLuong = ?, ngayPhanCong = ?, soLuongConLai = ? " +
	                     "WHERE maCongDoan = ? AND maCongNhan = ?";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, phanCong.getCongDoan().getMaCongDoan());
	        statement.setString(2, phanCong.getCongNhanDamNhan().getMaNhanSu());
	        statement.setInt(3, phanCong.getSoLuongPhanCong());
	        statement.setDate(4, java.sql.Date.valueOf(phanCong.getNgayPhanCong()));
	        statement.setInt(5, phanCong.getSoLuongPhanCong());
	        // Điều kiện WHERE
	        statement.setString(6, phanCong.getCongDoan().getMaCongDoan()); // Điều kiện WHERE MaCongDoan = ?
	        statement.setString(7, phanCong.getCongNhanDamNhan().getMaNhanSu()); // Điều kiện WHERE MaNhanSu = ?
	        statement.executeUpdate();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static int tinhTongSoLuongPhanCongTheoMaCongDoan(String maCongDoan) {
		int tongSoLuongPhanCong = 0;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			conn = Database.getConnectTion();
			String sql = "SELECT [soLuong] FROM [BangPhanCongCongDoan] WHERE [maCongDoan] = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, maCongDoan);
			rs = statement.executeQuery();

			while (rs.next()) {
				tongSoLuongPhanCong += rs.getInt("soLuong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return tongSoLuongPhanCong;
	}
	public void xoaPhanCongCongDoanVaKhoiDs(String maCongDoan, String maCongNhan) {
	    try {
	        Connection conn = Database.getConnectTion();
	        String sql = "DELETE FROM [QuanLyLuongSanPham].[dbo].[BangPhanCongCongDoan] " +
	                     "WHERE [maCongDoan] = ? AND [maCongNhan] = ?";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, maCongDoan);
	        statement.setString(2, maCongNhan);

	        statement.executeUpdate();
	        conn.close();

	        // Xóa khỏi danh sách
	        java.util.Iterator<BangPhanCongCongDoan> iterator = ds.iterator();
	        while (iterator.hasNext()) {
	            BangPhanCongCongDoan phanCong = iterator.next();
	            if (phanCong.getCongDoan().getMaCongDoan().equals(maCongDoan) &&
	                phanCong.getCongNhanDamNhan().getMaNhanSu().equals(maCongNhan)) {
	                iterator.remove();
	                break;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Xử lý lỗi, có thể hiển thị thông báo lỗi
	    }
	}
	

	public void setDs(ArrayList<BangPhanCongCongDoan> ds) {
		this.ds = ds;
	}
}
