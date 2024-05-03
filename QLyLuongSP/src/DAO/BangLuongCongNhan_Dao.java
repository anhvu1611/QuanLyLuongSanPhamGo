package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import entity.BangLuongCongNhan;


import entity.CongNhan;

public class BangLuongCongNhan_Dao {
	private Connection con = ConnectDB.getConnection();


	// Lấy bảng tổng số lượng theo công nhân theo tháng năm
	public ArrayList<BangLuongCongNhan> layBangLuongTheoThangNam(int nam, int thang) {
		ArrayList<BangLuongCongNhan> bangLuongCongNhan = new ArrayList<>();
		String sql = "SELECT  CONCAT('BLCN', CONVERT(VARCHAR, GETDATE(), 112)) AS MaBangLuong,\r\n"
				+ "		bcccn.maCongNhan,\r\n" + "		sum(soLuongHoanThanh) as tongSoSanPham,\r\n"
				+ "		SUM(bcccn.soLuongHoanThanh * cd.luongTrenMotCongDoan * cm.heSoLuongTheoChuyenMon * \r\n"
				+ "			CASE \r\n" + "				WHEN bcccn.calam in (N'Ca Ba') THEN 1.5\r\n"
				+ "				ELSE 1\r\n" + "			END\r\n" + "		) AS luongTheoSanPham,\r\n"
				+ "		phuCap,\r\n"
				+ "		SUM(bcccn.soLuongHoanThanh * cd.luongTrenMotCongDoan * cm.heSoLuongTheoChuyenMon * \r\n"
				+ "			CASE \r\n" + "				WHEN bcccn.calam in (N'Ca Một', N'Ca Hai') THEN 1\r\n"
				+ "				ELSE 1.5\r\n" + "			END\r\n" + "\r\n" + "		) + phuCap AS tongLuong,\r\n"
				+ "		FORMAT(GETDATE(), 'yyyy-MM-dd') as ngayLap\r\n" + "FROM \r\n"
				+ "		BangChamCongCongNhan bcccn\r\n"
				+ "LEFT JOIN BangPhanCongCongDoan pccd ON bcccn.maCongNhan = pccd.maCongNhan\r\n"
				+ "LEFT JOIN CongDoan cd ON pccd.maCongDoan = cd.maCongDoan\r\n"
				+ "LEFT JOIN CongNhan cn ON bcccn.maCongNhan = cn.maCongNhan\r\n"
				+ "LEFT JOIN ChuyenMon cm ON cn.maChuyenMon = cm.maChuyenMon\r\n"
				+ "WHERE YEAR(bcccn.ngayChamCong) = ? AND MONTH(bcccn.ngayChamCong) = ? AND bcccn.soLuongHoanThanh > 0\r\n"
				+ "GROUP BY  phuCap,bcccn.maCongNhan";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, nam);
			stmt.setInt(2, thang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maBangLuong = rs.getString(1);
				String maCongNhan = rs.getString(2);
				CongNhan cn = CongNhan_Dao.timCongNhanBangMa(maCongNhan);
				int tongSoSanPham = rs.getInt(3);
				Double luongTheoSanPham = rs.getDouble(4);
				Double tongLuong = rs.getDouble(6);
				Date ngayLap = rs.getDate(7);
				BangLuongCongNhan blcn = new BangLuongCongNhan(maBangLuong, cn, tongSoSanPham, luongTheoSanPham,
						tongLuong, ngayLap);
				bangLuongCongNhan.add(blcn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bangLuongCongNhan;
	}

	public boolean themBangLuong(BangLuongCongNhan bangLuong) throws SQLException {
		Connection con = Database.getConnectTion();
		PreparedStatement stmt = null;
		int n = 0;
		String sql = "insert into BangLuongCongNhan values (?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bangLuong.getMaBangLuongCongNhan());
			stmt.setDate(2, new java.sql.Date(bangLuong.getNgayLap().getTime()));
			stmt.setString(3, bangLuong.getCongNhan().getMaNhanSu());

			stmt.setDouble(4, bangLuong.getLuongTheoSanPham());
			stmt.setDouble(5, bangLuong.getTongLuong());
			stmt.setInt(6, bangLuong.getSoLuongSanPham());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			stmt.close();
		}
		return n > 0;
	}

	public ArrayList<Vector<String>> layBangLuongTheoMaCongNhanVaThang(int thang) {
		ArrayList<Vector<String>> bangLuongCongNhan = new ArrayList<>();
		ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<>();
		String sql = "SELECT \r\n"
				+ "    CONCAT(\r\n"
				+ "        'BLCN',\r\n"
				+ "        maCongNhan,\r\n"
				+ "        FORMAT(GETDATE(), 'MM'),\r\n"
				+ "        RIGHT(YEAR(GETDATE()), 2)\r\n"
				+ "    ) AS MaBangLuong,\r\n"
				+ "    maCongNhan,\r\n"
				+ "    tenCongNhan,\r\n"
				+ "    tenChuyenMon,\r\n"
				+ "    SUM(tongSoLuong) AS soLuongSanPham,\r\n"
				+ "    SUM(tongTien) AS luongSanPham,\r\n"
				+ "    750000 AS phuCap,\r\n"
				+ "    SUM(tongTien) + 750000 AS tongTienNhan,\r\n"
				+ "    GETDATE() AS ngayLap\r\n"
				+ "FROM \r\n"
				+ "    dbo.dsCanTinhLuong(?)\r\n"
				+ "GROUP BY \r\n"
				+ "    maCongNhan,\r\n"
				+ "    tenCongNhan,\r\n"
				+ "    tenChuyenMon;\r\n";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, thang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Vector<String> data = new Vector<>();
				String maBangLuong = rs.getString(1);
				data.add(maBangLuong);
				String maCongNhanSql = rs.getString(2);
				data.add(maCongNhanSql);
				String tenCongNhan = rs.getString(3);
				data.add(tenCongNhan);
				String tenChuyenMon = rs.getString(4);
				data.add(tenChuyenMon);
				int soLuongSanPham = rs.getInt(5);
				data.add(soLuongSanPham + "");
				double luongSanPham = rs.getDouble(6);
				data.add(luongSanPham + "");
				double phuCap = rs.getDouble(7);
				data.add(phuCap + "");
				double tongTien = rs.getDouble(8);
				data.add(tongTien + "");
				Date ngayLap = rs.getDate(9);
				data.add(ngayLap.toString());
				bangLuongCongNhan.add(data);
				dsBangLuong.add(new BangLuongCongNhan(maBangLuong, new CongNhan(maCongNhanSql), soLuongSanPham, luongSanPham, tongTien, ngayLap));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		themDSBangLuong(dsBangLuong);
		return bangLuongCongNhan;
	}
	
	public static ArrayList<Vector<String>> layDanhSachTopNCongNhanLuong(int tuThang, int denThang, int tuNam, int denNam, int soLuong, String cachSapXep, String tieuChiSapXep){
		ArrayList<Vector<String>> ds = new ArrayList<>();
		try {
			Connection con = Database.getConnectTion();
			String sql = "select Top 5 maCongNhan, tongLuongTheoSanPham = sum(luongCongNhanTheoSanPham), tongLuongThuc = sum(luongThucCuaCongNhan), tongSoSanPham = sum(tongSoCaLam) from BangLuongCongNhan\r\n"
					+ "  where month(ngayLap)>= ? and month(ngayLap)<=? and year(ngayLap)>= ? and year(ngayLap)<=?\r\n"
					+ "  group by maCongNhan\r\n"
					+ "  order by "+tieuChiSapXep+" "+cachSapXep;
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, tuThang);
			statement.setInt(2, denThang);
			statement.setInt(3, tuNam);
			statement.setInt(4, denNam);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Vector<String> data = new Vector<>();
				String maCongNhan = rs.getString(1);
				double tongSoLuongSanPham = rs.getDouble(2);
				double tongLuongThuc = rs.getDouble(3);
				int tongSoCaLam = rs.getInt(4);
				data.add(maCongNhan);
				data.add(tongSoLuongSanPham+"");
				data.add(tongLuongThuc+"");
				data.add(tongSoCaLam+"");
				ds.add(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	private void themDSBangLuong(ArrayList<BangLuongCongNhan> dsBangLuong) {
		try {
			Connection con = Database.getConnectTion();
			PreparedStatement stmt = null;
			String sql = "insert BangLuongCongNhan values";
			for (BangLuongCongNhan bangLuongCongNhan : dsBangLuong) {
				if(dsBangLuong.indexOf(bangLuongCongNhan)!= 0) {
					sql = sql+ ",("+"'"+bangLuongCongNhan.getMaBangLuongCongNhan()+"'"+","+"'"+bangLuongCongNhan.getNgayLap().toString()+"'"+","+"'"+bangLuongCongNhan.getCongNhan().getMaNhanSu()+"'"+","+bangLuongCongNhan.getLuongTheoSanPham()+","+bangLuongCongNhan.getTongLuong()+","+bangLuongCongNhan.getSoLuongSanPham()+")";
				}else {

					sql = sql+ " ("+"'"+bangLuongCongNhan.getMaBangLuongCongNhan()+"'"+","+"'"+bangLuongCongNhan.getNgayLap().toString()+"'"+","+"'"+bangLuongCongNhan.getCongNhan().getMaNhanSu()+"'"+","+bangLuongCongNhan.getLuongTheoSanPham()+","+bangLuongCongNhan.getTongLuong()+","+bangLuongCongNhan.getSoLuongSanPham()+")";}
			}
			stmt = con.prepareStatement(sql);
			System.out.println(sql);
			stmt.executeUpdate();
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<String> layDanhSachCoTheThongKe(){
		ArrayList<String> ds = new ArrayList<>();
		try {
			Connection con = Database.getConnectTion();
			String sql = "select distinct maCongNhan from BangLuongCongNhan";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ds.add(rs.getString(1));
			}
			con.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public static ArrayList<BangLuongCongNhan> layThongKeTungNhanVien(int tuThang, int denThang, int tuNam, int denNam, String maCN){
		ArrayList<BangLuongCongNhan> ds = new ArrayList<>();
		try {
			Connection con = Database.getConnectTion();
			String sql = "select * from BangLuongCongNhan where month(ngayLap)>= ? and month(ngayLap)<=? and year(ngayLap)>= ? and year(ngayLap)<=? and maCongNhan = ? ";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, tuThang);
			statement.setInt(2, denThang);
			statement.setInt(3, tuNam);
			statement.setInt(4, denNam);
			statement.setString(5, maCN);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String maBangLuong = rs.getString(1);
				Date ngayLap = rs.getDate(2);
				String maCongNhan = rs.getString(3);
				double luongTheoSanPham = rs.getDouble(4);
				double luongThucNhan = rs.getDouble(5);
				int tongSoSanPham = rs.getInt(6);
				BangLuongCongNhan blcn = new BangLuongCongNhan(maBangLuong, new CongNhan(maCongNhan), tongSoSanPham, luongTheoSanPham, luongThucNhan, ngayLap);
				ds.add(blcn);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	
}


