package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import Enum.CaLamCongNhan;
import Enum.LoaiCongDoan;
import entity.CongDoan;
import entity.SanPham;

public class CongDoan_Dao {
	private Connection con = Database.getConnectTion();
	private SanPham_Dao sanPham_dao = new SanPham_Dao();
	public CongDoan timCongDoanTheoMa(String maCongDoan) {
		String sql = "select * from CongDoan where maCongDoan = ?";
		CongDoan congDoan = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, maCongDoan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				SanPham sanPham = sanPham_dao.timSanPhamTheoMa(rs.getString(5));
				LoaiCongDoan loaiCongDoan = LoaiCongDoan.CATGO;
				congDoan = new CongDoan(rs.getString(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), sanPham, loaiCongDoan,null);
				//kiểm tra hằng
				String loai = rs.getString(6).trim();
				
				if(loai.equalsIgnoreCase("Cắt Gỗ")) {
					loaiCongDoan = LoaiCongDoan.CATGO;
				}else if(loai.equalsIgnoreCase("Chế Tác")) {
					loaiCongDoan = LoaiCongDoan.CHETAC;
				}else if(loai.equalsIgnoreCase("Lắp Ráp")) {
					loaiCongDoan = LoaiCongDoan.LAPRAP;
				}else {
					loaiCongDoan = LoaiCongDoan.BEMAT;
				}
				java.sql.Date ngayChiaCongDoanSQL = rs.getDate(7);
				congDoan = new CongDoan(rs.getString(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), sanPham, loaiCongDoan, rs.getDate(7).toLocalDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congDoan;
	}
	
	public static CongDoan timCongDoanKhiCoMa(String maCongDoan) {
		String sql = "select * from CongDoan where maCongDoan = ?";
		CongDoan congDoan = null;
		Connection con = Database.getConnectTion();
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, maCongDoan);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				SanPham sanPham = SanPham_Dao.timSanPhamTheoMa(rs.getString(5));
				LoaiCongDoan loaiCongDoan = null;
				String loai = rs.getString(6).trim();
				loaiCongDoan = LoaiCongDoan.findEnumByString(loai);
				congDoan = new CongDoan(rs.getString(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), sanPham, loaiCongDoan, rs.getDate(7).toLocalDate(), rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congDoan;
	}
	
	public static ArrayList<CongDoan> layDanhSachCongDoanKhiCoMaSanPham(String maSanPham) {
		ArrayList<CongDoan> ds = new ArrayList<>();
		try {
			Connection con = Database.getConnectTion();
			String sql = "select * from CongDoan where maSanPham = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSanPham);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				SanPham sanPham = SanPham_Dao.timSanPhamTheoMa(rs.getString(5));
				
				String loai = rs.getString(6).trim();
				LoaiCongDoan loaiCongDoan = LoaiCongDoan.findEnumByString(loai);
				System.out.println(loaiCongDoan);
				ds.add(new CongDoan(rs.getString(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), sanPham, loaiCongDoan, rs.getDate(7).toLocalDate(), rs.getInt(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	public static boolean themCongDoan(CongDoan congDoan) {
		int n = 0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "INSERT INTO [CongDoan] "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, congDoan.getMaCongDoan());
            statement.setInt(2, congDoan.getSoCongNhan());
            statement.setInt(3, congDoan.getSoLuongThanhPhan());
            statement.setDouble(4, congDoan.getLuongTren1Sp());
            statement.setString(5, congDoan.getSanPham().getMaSanPham());
            statement.setString(6, congDoan.getLoaiCongDoan().toString());
            statement.setDate(7, java.sql.Date.valueOf(congDoan.getNgayChiaCongDoan()));
            statement.setInt(8, congDoan.getTrangThai());
            n = statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
	
	public static boolean suaCongDoan(CongDoan congDoan) {
		int n = 0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "update [CongDoan] "+
                    "set soCongNhan = ?, soLuongThanhPhan = ?, luongTrenMotCongDoan = ?, maSanPham = ?, loaiCongDoan = ?, ngayChiaCongDoan = ?, trangThai =? where maCongDoan = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(8, congDoan.getMaCongDoan());
            statement.setInt(1, congDoan.getSoCongNhan());
            statement.setInt(2, congDoan.getSoLuongThanhPhan());
            statement.setDouble(3, congDoan.getLuongTren1Sp());
            statement.setString(4, congDoan.getSanPham().getMaSanPham());
            statement.setString(5, congDoan.getLoaiCongDoan().toString());
            statement.setDate(6, java.sql.Date.valueOf(congDoan.getNgayChiaCongDoan()));
            statement.setInt(7, congDoan.getTrangThai());
            n = statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
	public boolean updateTrangThaiCongDoan() {
		try {
            Connection conn = Database.getConnectTion();
            String sql = "UPDATE CongDoan\r\n"
            		+ "SET trangThai = 1\r\n"
            		+ "FROM (\r\n"
            		+ "    SELECT maCongDoan, SUM(soLuongHoanThanh) as tongSoLuong\r\n"
            		+ "    FROM BangChamCongCongNhan\r\n"
            		+ "    GROUP BY maCongDoan\r\n"
            		+ ") AS subquery\r\n"
            		+ "WHERE CongDoan.maCongDoan = subquery.maCongDoan\r\n"
            		+ "AND subquery.tongSoLuong = CongDoan.soLuongThanhPhan";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean updateTrangThaiSanPham(String maSanPham) {		
		try {
            Connection conn = Database.getConnectTion();
            String sql = "UPDATE SanPham\r\n"
            		+ "SET trangThaiSanPham = 1\r\n"
            		+ "WHERE maSanPham = ?\r\n"
            		+ "AND (SELECT COUNT(*) FROM CongDoan WHERE maSanPham = ? AND trangThai = 1) = 4;\r\n"
            		+ "";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maSanPham);
            statement.setString(2, maSanPham);
            statement.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
}