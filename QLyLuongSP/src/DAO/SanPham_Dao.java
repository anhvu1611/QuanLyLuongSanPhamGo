package DAO;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.crypto.Data;

import ConnectDB.ConnectDB;
import ConnectDB.Database;
import Enum.LoaiGo;
import entity.SanPham;

public class SanPham_Dao {
	private static Connection con = ConnectDB.getConnection();
	public static SanPham timSanPhamTheoMa(String maSanPham) {
		Connection con = Database.getConnectTion();
		String sql = "select * from SanPham where maSanPham = ?";
		SanPham sanPham = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, maSanPham);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maSanPhamSQL = rs.getString("maSanPham");
				int giaThanh = rs.getInt("giaThanh");
				String tenSanPham = rs.getString("tenSanPham");
				String chatGo = rs.getString("chatGo");
				String anh =rs.getString("anh");
				String kichThuoc = rs.getString("kichThuoc");
				LocalDate thoiGian = rs.getDate("thoiGianSanXuatDuKien").toLocalDate();
				
				LoaiGo go = LoaiGo.GOTOT;
				if(chatGo.equalsIgnoreCase("Gỗ Hương")) {
					go = LoaiGo.GOTHUONG;
				}else if(chatGo.equalsIgnoreCase("Gỗ Sao")) {
					go =LoaiGo.GOTRUNG;
				}
				sanPham = new SanPham(maSanPhamSQL, tenSanPham, giaThanh, thoiGian, go, anh, kichThuoc);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sanPham;
	}
	
	public static ArrayList<SanPham> timKiem(String ma, String ten, String khoiLuong, String soLuong, LoaiGo loaiGo, String soCongDoan, String dai, String rong, String cao, Date ngay, String gia){
		ArrayList<SanPham> ds = new ArrayList<>();
		try {
			Connection con = Database.getConnectTion();
			StringBuilder queryBuilder = new StringBuilder
	        		("select sp.maSanPham, giaThanh, tenSanPham, chatGo, anh, kichThuoc, thoiGianSanXuatDuKien, khoiLuong, trangThaiSanPham, soLuongSanPham from SanPham sp join CongDoan cd on cd.maSanPham = sp.maSanPham\r\n"
	        				+ "where 1 = 1 \r\n");

	        if (ma != null && !ma.isEmpty()) {
	            queryBuilder.append(" AND sp.maSanPham = '").append(ma.trim()).append("'");
	        }
	        if (ten != null && !ten.isEmpty()) {
	            queryBuilder.append(" AND tenSanPham = N'").append(ten.trim()).append("'");
	        }
	        if (gia != null && !gia.isEmpty()) {
	            queryBuilder.append(" AND giaThanh = ").append(gia.trim());
	        }
	        if(loaiGo != null) {
	        	queryBuilder.append(" AND chatGo = N'").append(loaiGo.toString()).append("'");
	        }
	        if(ngay != null) {
	            queryBuilder.append(" AND thoiGianSanXuatDuKien = '").
	            append(new java.sql.Date(ngay.getTime())).append("'");
	        }
	        if (khoiLuong != null && !khoiLuong.isEmpty()) {
	            queryBuilder.append(" AND khoiLuong = ").append(khoiLuong.trim());
	        }
	        if (soLuong != null && !soLuong.isEmpty()) {
	            queryBuilder.append(" AND soLuongSanPham = ").append(soLuong.trim());
	        }
	        if(dai!=null && !dai.isEmpty()) {
	        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 3) = ").append(dai.trim());
	        }
	        if(rong!=null && !rong.isEmpty()) {
	        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 2) = ").append(rong.trim());
	        }
	        if(cao!=null && !cao.isEmpty()) {
	        	queryBuilder.append(" and PARSENAME(REPLACE(kichThuoc, 'X', '.'), 1) = ").append(cao.trim());
	        }
	        queryBuilder.append(" group by sp.maSanPham, giaThanh, tenSanPham, chatGo, anh, kichThuoc, thoiGianSanXuatDuKien, khoiLuong, trangThaiSanPham, soLuongSanPham having count(sp.maSanPham)="+soCongDoan);
	        String sql = queryBuilder.toString();
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
//	        System.out.println(sql);
	        while(rs.next()) {
	        	String maSanPham = rs.getString("maSanPham");
                double giaThanh = rs.getDouble("giaThanh");
                String tenSP = rs.getString("tenSanPham");
//                Date thoiGianSanXuatDuKien = rs.getDate("thoiGianSanXuatDuKien");
                String chatGo = rs.getString("chatGo");
                String anh = rs.getString("anh");
                String kichThuoc = rs.getString("kichThuoc");
                double kl = rs.getDouble("khoiLuong");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                LocalDate tg = rs.getDate("thoiGianSanXuatDuKien").toLocalDate();

                int trangThai = rs.getInt("trangThaiSanPham");
                
                LoaiGo loaiG = LoaiGo.GOTOT;
                if(chatGo.trim().equalsIgnoreCase("Gỗ Hương")) {
                	loaiG = LoaiGo.GOTHUONG;
                }else if(chatGo.trim().equalsIgnoreCase("Gỗ Sao")) {
                	loaiG = LoaiGo.GOTRUNG;
                }

                boolean daTonTai = false;
                for (SanPham sp : ds) {
                    if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                        daTonTai = true;
                        break;
                    }
                }

                if (!daTonTai) {
                	SanPham sanPham = new SanPham(maSanPham, tenSP, giaThanh, tg, loaiG, anh, kichThuoc, trangThai, soLuongSanPham, kl);
                    ds.add(sanPham);
                }
            }
	        
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	
}
