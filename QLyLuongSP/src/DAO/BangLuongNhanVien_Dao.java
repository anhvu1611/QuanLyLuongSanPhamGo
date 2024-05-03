package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


import ConnectDB.Database;
import entity.BangLuongNhanVien;
import entity.NhanVien;

public class BangLuongNhanVien_Dao {
	private ArrayList<BangLuongNhanVien> ds;
	private BangLuongNhanVien bangLuongNhanVien = null;
	private static NhanVien_Dao qlNhanVien = null;
	
	public BangLuongNhanVien_Dao() {
		ds = new ArrayList<>();  
		qlNhanVien = new NhanVien_Dao();
	}
	
	public static ArrayList<Vector<String>> layDanhSachCoTheTinhLuongTheoThangVaNam(int month, int year) throws SQLException {
	    ArrayList<Vector<String>> data = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        String sql = "SELECT * FROM layBangNhanVienTinhLuong(?, ?)";
	        con = Database.getConnectTion();
	        statement = con.prepareStatement(sql);
	            statement.setInt(1, month);
	            statement.setInt(2, year);

	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                String maNhanVien = resultSet.getString("maNhanVien");
	                String tenNhanVien = resultSet.getString("tenNhanVien");
	                String tenChucVu = resultSet.getString("tenChucVu");
	                String tenPhongBan = resultSet.getString("tenPhongBan");
	                Vector<String> duLieu = new Vector<String>();
	                duLieu.add(maNhanVien);
	                duLieu.add(tenNhanVien);
	                duLieu.add(tenChucVu);
	                duLieu.add(tenPhongBan);
	                
	                data.add(duLieu);
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
			con.close();
			statement.close();
		}

	    return data;
	}

	
	public ArrayList<BangLuongNhanVien> taoBangLuongNhanVien(String ma, int thang, int nam){
		ArrayList<BangLuongNhanVien> data = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.getConnectTion();
            String sql = "SELECT * FROM dbo.taoBangLuongNhanVien(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            preparedStatement.setInt(2, thang);
            preparedStatement.setInt(3, nam);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	String maBangLuong = resultSet.getString("maBangLuongNhanVien");           	
            	Date ngayLap = resultSet.getDate("ngayLap");
            	int soNgayDiLam = resultSet.getInt("soNgayDiLam");
            	int soNgayVangCoPhep = resultSet.getInt("soNgayVangCoPhep");
            	int soNgayVangKhongPhep = resultSet.getInt("soNgayVangKhongPhep");
            	int soNgayTre = resultSet.getInt("soNgayTre");
            	String maNhanVien = resultSet.getString("maNhanVien");
            	int soGioTangCa = resultSet.getInt("soGioTangCa");
            	int soNgayLamCaNgay = resultSet.getInt("soNgayLamCaNgay");
            	int soNgayLamNuaNgay = resultSet.getInt("soNgayLamNuaNgay");
            	NhanVien nv = qlNhanVien.timNhanVienTheoMa(maNhanVien);
            	BangLuongNhanVien bangLuongNhanVien = new BangLuongNhanVien(maBangLuong, ngayLap, soNgayDiLam, soNgayLamCaNgay, soNgayLamNuaNgay, soNgayVangCoPhep, soNgayTre, soGioTangCa, soNgayVangKhongPhep, nv);
            	data.add(bangLuongNhanVien);
            	themBangLuong(bangLuongNhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return data;
	}
	
	
	public boolean themBangLuong(BangLuongNhanVien bangLuong) throws SQLException{
        Connection con = Database.getConnectTion();
        PreparedStatement stmt = null;
        int n=0;
        String sql = "insert into BangLuongNhanVien values (?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, bangLuong.getMaBangLuong());
            stmt.setDate(2, new java.sql.Date(bangLuong.getNgayLap().getTime()));
            stmt.setFloat(3, bangLuong.getSoNgayDiLam());
            stmt.setInt(4, bangLuong.getSoNgayVangCoPhep());            
            stmt.setInt(5, bangLuong.getSoNgayVangKhongPhep());
            stmt.setInt(6, bangLuong.getSoNgayTre());
            stmt.setString(7, bangLuong.getNhanVien().getMaNhanSu());
            stmt.setInt(8, bangLuong.getSoGioTangCa());
            stmt.setInt(9, bangLuong.getSoNgayLamNguyenNgay());
            stmt.setInt(10, bangLuong.getSoNgayLamNuaNgay());
            n = stmt.executeUpdate();
            ds.add(bangLuong);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
			con.close();
			stmt.close();
		}
        return n>0;
    }
	
	public ArrayList<BangLuongNhanVien> layDanhSachThongTinTuBangChamCongVaThemBangLuong(int month, int year) throws SQLException{
		ArrayList<BangLuongNhanVien> data = new ArrayList<>();
		Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        String sql = "SELECT * FROM tinhLuongToanBoNhanVienTheoThangVaNam(?, ?)";
	        con = Database.getConnectTion();
	        statement = con.prepareStatement(sql);
	            statement.setInt(1, month);
	            statement.setInt(2, year);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	String maBangLuong = resultSet.getString("maBangLuongNhanVien");           	
	            	Date ngayLap = resultSet.getDate("ngayLap");
	            	int soNgayDiLam = resultSet.getInt("soNgayDiLam");
	            	int soNgayVangCoPhep = resultSet.getInt("soNgayVangCoPhep");
	            	int soNgayVangKhongPhep = resultSet.getInt("soNgayVangKhongPhep");
	            	int soNgayTre = resultSet.getInt("soNgayTre");
	            	String maNhanVien = resultSet.getString("maNhanVien");
	            	int soGioTangCa = resultSet.getInt("soGioTangCa");
	            	int soNgayLamCaNgay = resultSet.getInt("soNgayLamCaNgay");
	            	int soNgayLamNuaNgay = resultSet.getInt("soNgayLamNuaNgay");
	            	NhanVien nv = qlNhanVien.timNhanVienTheoMa(maNhanVien);
	            	bangLuongNhanVien = new BangLuongNhanVien(maBangLuong, ngayLap, soNgayDiLam, soNgayLamCaNgay, soNgayLamNuaNgay, soNgayVangCoPhep, soNgayTre, soGioTangCa, soNgayVangKhongPhep, nv);
	            	data.add(bangLuongNhanVien);
	            	ds.add(bangLuongNhanVien);
	            	themBangLuong(bangLuongNhanVien);
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
			con.close();
			statement.close();
		}

	    return data;
	}

	public BangLuongNhanVien layBangLuongTheoMa(String ma) {
		for (BangLuongNhanVien bangLuongNhanVien : ds) {
			if(bangLuongNhanVien.getMaBangLuong().equalsIgnoreCase(ma)) {
				return bangLuongNhanVien;
			}
		}
		return null;
	}
	
	public static BangLuongNhanVien timBangLuongBangMa(String maBangLuong) {
		String sql = "select * from BangLuongNhanVien where maBangLuongNhanVien = ?";
		BangLuongNhanVien bangLuongNhanVien = null;
		Connection con = Database.getConnectTion();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maBangLuong);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String maBangLuongSQl = resultSet.getString("maBangLuongNhanVien");           	
            	Date ngayLap = resultSet.getDate("ngayLap");
            	int soNgayDiLam = resultSet.getInt("soNgayDiLam");
            	int soNgayVangCoPhep = resultSet.getInt("soNgayVangCoPhep");
            	int soNgayVangKhongPhep = resultSet.getInt("soNgayVangKhongPhep");
            	int soNgayTre = resultSet.getInt("soNgayTre");
            	String maNhanVien = resultSet.getString("maNhanVien");
            	int soGioTangCa = resultSet.getInt("soGioTangCa");
            	int soNgayLamCaNgay = resultSet.getInt("soNgayLamCaNgay");
            	int soNgayLamNuaNgay = resultSet.getInt("soNgayLamNuaNgay");
            	NhanVien nv = qlNhanVien.timNhanVienTheoMa(maNhanVien);
            	bangLuongNhanVien = new BangLuongNhanVien(maBangLuongSQl, ngayLap, soNgayDiLam, soNgayLamCaNgay, soNgayLamNuaNgay, soNgayVangCoPhep, soNgayTre, soGioTangCa, soNgayVangKhongPhep, nv);
			}
			con.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangLuongNhanVien;
	}
	
	public static ArrayList<Vector<String>> layDanhSachThongKeTopNNhanVien(int tuThang, int tuNam, int toiThang, int toiNam, int soLuongThongKe, String kieuTangGiam, String tieuChi) {
	    ArrayList<Vector<String>> ds = new ArrayList<>();
	    String sql = "SELECT TOP " +soLuongThongKe+" "+
	            "SUM(LuongThucNhan) AS LuongThucNhan, " +
	            "SUM(NgayLam) AS NgayLam, " +
	            "LuongCoBan, " +
	            "HeSoLuong, " +
	            "SUM(KhauTruBaoHiem) AS KhauTru, " +
	            "SUM(LuongTruocThue) AS LuongTruocThue, " +
	            "MaNhanVien " +
	            "FROM dbo.[thongKeTheoThoiGian](?, ?, ?, ?) " +
	            "GROUP BY MaNhanVien, LuongCoBan, HeSoLuong " +
	            "ORDER BY "+tieuChi+" "+ kieuTangGiam;

	    Connection con = Database.getConnectTion();
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, tuThang);
	        stmt.setInt(2, tuNam);
	        stmt.setInt(3, toiThang);
	        stmt.setInt(4, toiNam);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Vector<String> data = new Vector<>();
	            double luongThucNhan = rs.getDouble("LuongThucNhan");
	            data.add(String.valueOf(luongThucNhan));
	            int soNgayLam = rs.getInt("NgayLam");
	            data.add(String.valueOf(soNgayLam));
	            double LuongCoBan = rs.getDouble("LuongCoBan");
	            data.add(String.valueOf(LuongCoBan));
	            float HeSoLuong = rs.getFloat("HeSoLuong");
	            data.add(String.valueOf(HeSoLuong));
	            double luongTruocThue = rs.getDouble("LuongTruocThue");
	            data.add(String.valueOf(luongTruocThue));
	            double KhauTruBaoHiem = rs.getDouble("KhauTru");
	            data.add(String.valueOf(KhauTruBaoHiem));
	            String MaNhanVien = rs.getString("MaNhanVien");
	            data.add(MaNhanVien);
	            ds.add(data);
	        }
	        con.close();
	        rs.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ds;
	}

	
	public static ArrayList<Vector<String>> layDanhSachThongKeTop5NhanVienLuongThapNhat(int thang, int nam) {
		ArrayList<Vector<String>> ds = new ArrayList<>();
		String sql = "select Top 5 * from dbo.danhSachThongKe(?, ?)\r\n"
				+ "order by LuongThucNhan asc, MaNhanVien asc";
		Connection con = Database.getConnectTion();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Vector<String> data = new Vector<>();
				double luongThucNhan = rs.getDouble("LuongThucNhan");
				data.add(luongThucNhan+"");
				int soNgayLam = rs.getInt("NgayLam");
				data.add(soNgayLam+"");
				double LuongCoBan = rs.getDouble("LuongCoBan");
				data.add(LuongCoBan+"");
				float HeSoLuong = rs.getFloat("HeSoLuong");
				data.add(HeSoLuong+"");
				double luongTruocThue = rs.getDouble("LuongTruocThue");
				data.add(luongTruocThue+"");
				double KhauTruBaoHiem = rs.getDouble("KhauTruBaoHiem");
				data.add(KhauTruBaoHiem+"");
				String MaNhanVien = rs.getString("MaNhanVien");
				data.add(MaNhanVien);
				ds.add(data);
			}
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
}
