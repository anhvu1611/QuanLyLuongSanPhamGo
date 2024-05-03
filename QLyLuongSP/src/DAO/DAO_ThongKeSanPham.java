package DAO;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectDB.ConnectDB;
import entity.SanPham;



public class DAO_ThongKeSanPham {
	private Connection con;
	private DAO_SanPham sanPham_dao = new DAO_SanPham();
	public DAO_ThongKeSanPham() {
		// TODO Auto-generated constructor stub
		con = ConnectDB.getInstance().getConnection();
	}
	//---------------Thống kê------------------
	
		//Thống kê theo số lượng sản phẩm hoàn thành
		public ArrayList<ArrayList<String>> soLuongSanPham(int tuThang, int tuNam, int denThang, int denNam, String tieuChi1, String tieuChi2) {
			ArrayList<String> temp;
			ArrayList<ArrayList<String>> dsSanPham = new ArrayList<ArrayList<String>>();
			String sqlStart = "SELECT Top(5)\r\n"
					+ "    YEAR([thoiGianSanXuatDuKien]) AS Year,\r\n"
					+ "    MONTH([thoiGianSanXuatDuKien]) AS Month,\r\n"
					+ "    SUM([soLuongSanPham]) AS TotalQuantity\r\n"
					+ "FROM\r\n"
					+ "    [QuanLyLuongSanPham].[dbo].[SanPham]\r\n"
					+ "WHERE\r\n"
					+ "	trangThaiSanPham=0 \r\n"
					+ "	AND MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "	AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "GROUP BY\r\n"
					+ "    YEAR([thoiGianSanXuatDuKien]), MONTH([thoiGianSanXuatDuKien])\r\n"
					+ "ORDER BY\r\n"
					+ "	TotalQuantity desc";
			String sqlYear = "SELECT Top(5)\r\n"
					+ "		Month = null, \r\n "
					+ "    YEAR([thoiGianSanXuatDuKien]) AS Year,\r\n"
					+ "    SUM([soLuongSanPham]) AS TotalQuantity\r\n"
					+ "FROM\r\n"
					+ "    [QuanLyLuongSanPham].[dbo].[SanPham]\r\n"
					+ "WHERE\r\n"
					+ "	trangThaiSanPham=0 \r\n"
					+ "	AND MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "	AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "GROUP BY\r\n"
					+ "    YEAR([thoiGianSanXuatDuKien])\r\n"
					+ "ORDER BY\r\n"
					+ "	TotalQuantity desc";
			String sql;
			
			if(tieuChi1.equalsIgnoreCase("Thống kê theo tháng") && tieuChi2.equalsIgnoreCase("Top 5 cao nhất")) {
				sql = sqlStart; 
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo tháng") && tieuChi2.equalsIgnoreCase("Top 10 cao nhất")) {
					 sql = sqlStart.replace("Top(5)", "Top(10)");
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo tháng") && tieuChi2.equalsIgnoreCase("Top 5 thấp nhất")) {
					 sql = sqlStart.replace("desc", "asc");
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo tháng") && tieuChi2.equalsIgnoreCase("Top 10 thấp nhất")){
					 sql = "SELECT Top(10)\r\n"
							+ "    YEAR([thoiGianSanXuatDuKien]) AS Year,\r\n"
							+ "    MONTH([thoiGianSanXuatDuKien]) AS Month,\r\n"
							+ "    SUM([soLuongSanPham]) AS TotalQuantity\r\n"
							+ "FROM\r\n"
							+ "    [QuanLyLuongSanPham].[dbo].[SanPham]\r\n"
							+ "WHERE\r\n"
							+ "	trangThaiSanPham=0 \r\n"
							+ "	AND MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
							+ "	AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
							+ "GROUP BY\r\n"
							+ "    YEAR([thoiGianSanXuatDuKien]), MONTH([thoiGianSanXuatDuKien])\r\n"
							+ "ORDER BY\r\n"
							+ "	TotalQuantity asc";
			}
			else if(tieuChi1.equalsIgnoreCase("Thống kê theo năm") && tieuChi2.equalsIgnoreCase("Top 5 cao nhất")){
				sql = sqlYear;
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo năm") && tieuChi2.equalsIgnoreCase("Top 10 cao nhất")) {
				sql = sqlYear.replace("Top(5)", "Top(10)");
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo năm") && tieuChi2.equalsIgnoreCase("Top 5 thấp nhất")) {
				sql = sqlYear.replace("desc", "asc");
			}else if(tieuChi1.equalsIgnoreCase("Thống kê theo năm") && tieuChi2.equalsIgnoreCase("Top 10 thấp nhất")) {
				sql = sqlYear.replace("desc", "asc");
				sql = sqlYear.replace("Top(5)", "Top(10");
			}else {
				sql = "SELECT\r\n"
						+ "		Month = null, \r\n "
						+ "    YEAR([thoiGianSanXuatDuKien]) AS Year,\r\n"
						+ "    SUM([soLuongSanPham]) AS TotalQuantity\r\n"
						+ "FROM\r\n"
						+ "    [QuanLyLuongSanPham].[dbo].[SanPham]\r\n"
						+ "WHERE\r\n"
						+ "	trangThaiSanPham=0 \r\n"
						+ "	AND MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
						+ "	AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
						+ "GROUP BY\r\n"
						+ "    YEAR([thoiGianSanXuatDuKien])\r\n"
						+ "ORDER BY\r\n"
						+ "	Year ";
			}

			
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, tuThang);
				stmt.setInt(2, denThang);
				stmt.setInt(3, tuNam);
				stmt.setInt(4, denNam);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					temp = new ArrayList<>();
					temp.add(rs.getString(1));
					temp.add(rs.getString(2));
					temp.add(rs.getString(3));
					dsSanPham.add(temp);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsSanPham;
		}
		
		
		//thống kê số lượng sản phẩm khách yêu cầu theo chất gỗ
		
		public ArrayList<ArrayList<String>> soLuongSanPhamTheoChatGo(int tuThang, int tuNam, int denThang, int denNam, String tieuChi1, String tieuChi2){
			ArrayList<ArrayList<String>> dsSanPhamTheoChatGo = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp;
			
			String sqlStart = "SELECT Top(5)\r\n"
					+ "    chatGo,\r\n"
					+ "    COUNT(*) AS soLuongSanPham\r\n"
					+ "FROM\r\n"
					+ "    SanPham\r\n"
					+ "WHERE\r\n"
					+ "	MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "	and YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "GROUP BY\r\n"
					+ "    chatGo\r\n"
					+ "ORDER BY \r\n"
					+ "	soLuongSanPham DESC";
			String sql;
			if(tieuChi2.equalsIgnoreCase("Top 5 cao nhất")) {
				sql = sqlStart;
			}else if(tieuChi2.equalsIgnoreCase("Top 10 cao nhất")) {
				sql = sqlStart.replace("Top(5)", "Top(10)");
			}else if(tieuChi2.equalsIgnoreCase("Top 5 thấp nhất")) {
				sql = sqlStart.replace("DESC", "ASC");
			}else {
				sql = sqlStart.replace("Top(5)", "Top(10)");
				sql = sqlStart.replace("DESC", "ASC");
			}
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, tuThang);
				stmt.setInt(2, denThang);
				stmt.setInt(3, tuNam);
				stmt.setInt(4, denNam);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					temp = new ArrayList<>();
					temp.add(rs.getString(1));
					temp.add(rs.getString(2));
					dsSanPhamTheoChatGo.add(temp);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsSanPhamTheoChatGo;
		}
		
		//Thống kê top giá thành sản phẩm
		public ArrayList<ArrayList<String>> sanPhamTheoGiaThanh(int tuThang, int tuNam, int denThang, int denNam, String tieuChi1, String tieuChi2) {
			ArrayList<ArrayList<String>> dsSanPhamTheoGia = new ArrayList<ArrayList<String>>();
			ArrayList<String> temp;
			String sqlStart = "select Top(5) giaThanh, tenSanPham from SanPham\r\n"
					+ "where MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
					+ "order by giaThanh DESC";
			String sql;
			if(tieuChi2.equalsIgnoreCase("Top 5 cao nhất")) {
				sql = sqlStart;
			}else if(tieuChi2.equalsIgnoreCase("Top 10 cao nhất")) {
				sql = sqlStart.replace("Top(5)", "Top(10)");
			}else if(tieuChi2.equalsIgnoreCase("Top 5 thấp nhất")) {
				sql = sqlStart.replace("DESC", "ASC");
			}else {
				sql = "select Top(10) giaThanh, tenSanPham from SanPham\r\n"
						+ "where MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
						+ "AND YEAR(thoiGianSanXuatDuKien) between ? and ?\r\n"
						+ "order by giaThanh ASC";
			}
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, tuThang);
				stmt.setInt(2, denThang);
				stmt.setInt(3, tuNam);
				stmt.setInt(4, denNam);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					temp = new ArrayList<>();
					temp.add(rs.getString(1));
					temp.add(rs.getString(2));
					dsSanPhamTheoGia.add(temp);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsSanPhamTheoGia;
		}
		
		
		
//		//load dữ liệu giá thành theo sản phẩm
//		public ArrayList<ArrayList<String>> dsGiaThanhTheoSanPham(int tuThang, int tuNam, int denThang, int denNam) {
//			ArrayList<ArrayList<String>> dsGiaThanhSP = new ArrayList<ArrayList<String>>();
//			ArrayList<String> temp;
//			String sql = "  select tenSanPham, giaThanh, thoiGianSanXuatDuKien as thoiGianSanXuat from SanPham\r\n"
//					+ "  where MONTH(thoiGianSanXuatDuKien) between ? and ?\r\n"
//					+ "	AND YEAR(thoiGianSanXuatDuKien) between ? and ?";
//			try (PreparedStatement stmt = con.prepareStatement(sql)){
//				stmt.setInt(1, tuThang);
//				stmt.setInt(2, denThang);
//				stmt.setInt(3, tuNam);
//				stmt.setInt(4, denNam);
//				ResultSet rs = stmt.executeQuery();
//				while(rs.next()) {
//					temp = new ArrayList<>();
//					temp.add(rs.getString("tenSanPham"));
//					temp.add(rs.getString("giaThanh"));
//					temp.add(rs.getString("thoiGianSanXuat"));
//					dsGiaThanhSP.add(temp);
//					
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return dsGiaThanhSP;
//		}
//		//load dữ liệu theo số lượng 
//		public ArrayList<SanPham> dsSoLuong(int tuThang, int tuNam, int denThang, int denNam){
//			ArrayList<SanPham> dsSanPham = sanPham_dao.layDanhSachSanPham();
//			return dsSanPham;
//		}
}
