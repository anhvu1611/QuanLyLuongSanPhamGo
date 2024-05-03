package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import ConnectDB.Database;
import Enum.LoaiCongDoan;
import entity.CongDoan;
import entity.SanPham;

public class DAO_CongDoan {
    private ArrayList<CongDoan> ds;
    private ArrayList<SanPham> dsSP;
    public DAO_CongDoan() {
        ds = new ArrayList<>();
        layDanhSachCongDoan();
    }

    public ArrayList<CongDoan> getDs() {
        return ds;
    }

    public ArrayList<CongDoan> layDanhSachCongDoan() {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maCongDoan], [soCongNhan], [soLuongThanhPhan], [luongTrenMotCongDoan], [maSanPham], [loaiCongDoan], [ngayChiaCongDoan] FROM [CongDoan] WHERE [trangThai] = 1";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                double luongTren1Sp = rs.getDouble("luongTrenMotCongDoan");
                int soCongNhan = rs.getInt("soCongNhan");
                int soLuongThanhPhan = rs.getInt("soLuongThanhPhan");
                String maSanPham = rs.getString("maSanPham");
                SanPham sanPham = new DAO_SanPham().timSanPhamTheoMa(maSanPham);
                String loaiCongDoanStr = rs.getString("loaiCongDoan");
                java.sql.Date ngayChiaCongDoanSQL = rs.getDate("ngayChiaCongDoan");
                LocalDate ngayChiaCongDoan = ngayChiaCongDoanSQL.toLocalDate();        
                LoaiCongDoan loaiCongDoan = LoaiCongDoan.BEMAT;
                if(loaiCongDoanStr.trim().equalsIgnoreCase("Cắt Gỗ")) {
                	loaiCongDoan = LoaiCongDoan.CATGO;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Chế Tác")) {
                	loaiCongDoan = LoaiCongDoan.CHETAC;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Lắp Ráp")) {
                	loaiCongDoan = LoaiCongDoan.LAPRAP;
                }
              
                boolean daTonTai = false;
                for (CongDoan sp : ds) {
                    if (sp.getMaCongDoan().equalsIgnoreCase(maCongDoan)) {
                        daTonTai = true;
                        break;
                    }
                }

                if (!daTonTai) {
                	CongDoan congDoan = new CongDoan(maCongDoan, luongTren1Sp, soCongNhan, soLuongThanhPhan, sanPham, loaiCongDoan, ngayChiaCongDoan);
                    ds.add(congDoan);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public void themCongDoan(CongDoan congDoan) {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "INSERT INTO [CongDoan] ([maCongDoan], [soCongNhan], [soLuongThanhPhan], [luongTrenMotCongDoan], [maSanPham], [loaiCongDoan], [ngayChiaCongDoan]) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, congDoan.getMaCongDoan());
            statement.setInt(2, congDoan.getSoCongNhan());
            statement.setInt(3, congDoan.getSoLuongThanhPhan());
            statement.setDouble(4, congDoan.getLuongTren1Sp());
            statement.setString(5, congDoan.getSanPham().getMaSanPham());
            statement.setString(6, congDoan.getLoaiCongDoan().toString());
            statement.setDate(7, java.sql.Date.valueOf(congDoan.getNgayChiaCongDoan()));
            statement.executeUpdate();
            conn.close();
            ds.add(congDoan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<CongDoan> layDanhSachCongDoanTheoSP(String maSanPham) {
        ArrayList<CongDoan> dsCongDoanTheoSP = new ArrayList<>();
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maCongDoan], [soCongNhan], [soLuongThanhPhan], [luongTrenMotCongDoan], [maSanPham], [loaiCongDoan], [ngayChiaCongDoan] FROM [CongDoan] WHERE [maSanPham] = ? AND [trangThai] = 0";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maSanPham);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                double luongTren1Sp = rs.getDouble("luongTrenMotCongDoan");
                int soCongNhan = rs.getInt("soCongNhan");
                int soLuongThanhPhan = rs.getInt("soLuongThanhPhan");
                String maSanPhamDB = rs.getString("maSanPham");
                SanPham sanPham = new DAO_SanPham().timSanPhamTheoMa(maSanPhamDB);
                String loaiCongDoanStr = rs.getString("loaiCongDoan");
                java.sql.Date ngayChiaCongDoanSQL = rs.getDate("ngayChiaCongDoan");
                LocalDate ngayChiaCongDoan = ngayChiaCongDoanSQL.toLocalDate();        
                LoaiCongDoan loaiCongDoan = LoaiCongDoan.BEMAT;
                if(loaiCongDoanStr.trim().equalsIgnoreCase("Cắt Gỗ")) {
                	loaiCongDoan = LoaiCongDoan.CATGO;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Chế Tác")) {
                	loaiCongDoan = LoaiCongDoan.CHETAC;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Lắp Ráp")) {
                	loaiCongDoan = LoaiCongDoan.LAPRAP;
                }
              
                CongDoan congDoan = new CongDoan(maCongDoan, luongTren1Sp, soCongNhan, soLuongThanhPhan, sanPham, loaiCongDoan, ngayChiaCongDoan);
                dsCongDoanTheoSP.add(congDoan);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCongDoanTheoSP;
    }
    public ArrayList<CongDoan> layDanhSachCongDoanTheoSPDaHoanThanh(String maSanPham) {
        ArrayList<CongDoan> dsCongDoanTheoSP = new ArrayList<>();
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maCongDoan], [soCongNhan], [soLuongThanhPhan], [luongTrenMotCongDoan], [maSanPham], [loaiCongDoan], [ngayChiaCongDoan] FROM [CongDoan] WHERE [maSanPham] = ? AND [trangThai] = 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maSanPham);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                double luongTren1Sp = rs.getDouble("luongTrenMotCongDoan");
                int soCongNhan = rs.getInt("soCongNhan");
                int soLuongThanhPhan = rs.getInt("soLuongThanhPhan");
                String maSanPhamDB = rs.getString("maSanPham");
                SanPham sanPham = new DAO_SanPham().timSanPhamTheoMa(maSanPhamDB);
                String loaiCongDoanStr = rs.getString("loaiCongDoan");
                java.sql.Date ngayChiaCongDoanSQL = rs.getDate("ngayChiaCongDoan");
                LocalDate ngayChiaCongDoan = ngayChiaCongDoanSQL.toLocalDate();        
                LoaiCongDoan loaiCongDoan = LoaiCongDoan.BEMAT;
                if(loaiCongDoanStr.trim().equalsIgnoreCase("Cắt Gỗ")) {
                	loaiCongDoan = LoaiCongDoan.CATGO;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Chế Tác")) {
                	loaiCongDoan = LoaiCongDoan.CHETAC;
                }else if(loaiCongDoanStr.trim().equalsIgnoreCase("Lắp Ráp")) {
                	loaiCongDoan = LoaiCongDoan.LAPRAP;
                }
              
                CongDoan congDoan = new CongDoan(maCongDoan, luongTren1Sp, soCongNhan, soLuongThanhPhan, sanPham, loaiCongDoan, ngayChiaCongDoan);
                dsCongDoanTheoSP.add(congDoan);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCongDoanTheoSP;
    }
    public ArrayList<CongDoan> layDanhSachTatCaCongDoanDaHoanThanh() {
        ArrayList<CongDoan> dsCongDoanTheoSP = new ArrayList<>();
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maCongDoan], [soCongNhan], [soLuongThanhPhan], [luongTrenMotCongDoan], [maSanPham], [loaiCongDoan], [ngayChiaCongDoan] FROM [CongDoan] WHERE [trangThai] = 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                double luongTren1Sp = rs.getDouble("luongTrenMotCongDoan");
                int soCongNhan = rs.getInt("soCongNhan");
                int soLuongThanhPhan = rs.getInt("soLuongThanhPhan");
                String maSanPhamDB = rs.getString("maSanPham");
                SanPham sanPham = new DAO_SanPham().timSanPhamTheoMa(maSanPhamDB);
                String loaiCongDoanStr = rs.getString("loaiCongDoan");
                java.sql.Date ngayChiaCongDoanSQL = rs.getDate("ngayChiaCongDoan");
                LocalDate ngayChiaCongDoan = ngayChiaCongDoanSQL.toLocalDate();        
                LoaiCongDoan loaiCongDoan = LoaiCongDoan.BEMAT;
                if(loaiCongDoanStr.trim().equalsIgnoreCase("Cắt Gỗ")) {
                    loaiCongDoan = LoaiCongDoan.CATGO;
                } else if(loaiCongDoanStr.trim().equalsIgnoreCase("Chế Tác")) {
                    loaiCongDoan = LoaiCongDoan.CHETAC;
                } else if(loaiCongDoanStr.trim().equalsIgnoreCase("Lắp Ráp")) {
                    loaiCongDoan = LoaiCongDoan.LAPRAP;
                }
              
                CongDoan congDoan = new CongDoan(maCongDoan, luongTren1Sp, soCongNhan, soLuongThanhPhan, sanPham, loaiCongDoan, ngayChiaCongDoan);
                dsCongDoanTheoSP.add(congDoan);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCongDoanTheoSP;
    }

    public void capNhatCongDoan(CongDoan congDoan) {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "UPDATE [CongDoan] " +
                    "SET [soCongNhan] = ?, [soLuongThanhPhan] = ?, [luongTrenMotCongDoan] = ?, [maSanPham] = ?, [loaiCongDoan] = ?, [ngayChiaCongDoan] = ? " +
                    "WHERE [maCongDoan] = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, congDoan.getSoCongNhan());
            statement.setInt(2, congDoan.getSoLuongThanhPhan());
            statement.setDouble(3, congDoan.getLuongTren1Sp());
            statement.setString(4, congDoan.getSanPham().getMaSanPham());
            statement.setString(5, congDoan.getLoaiCongDoan().toString());
            statement.setDate(6, java.sql.Date.valueOf(congDoan.getNgayChiaCongDoan()));
            statement.setString(7, congDoan.getMaCongDoan());
            statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaCongDoan(String maCongDoan) {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "DELETE FROM [CongDoan] WHERE [maCongDoan] = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maCongDoan);
            statement.executeUpdate();
            conn.close();
            CongDoan congDoan = timCongDoanTheoMa(maCongDoan);
            if (congDoan != null) {
                ds.remove(congDoan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CongDoan timCongDoanTheoMa(String maCongDoan) {
        for (CongDoan congDoan : ds) {       	
            if (congDoan.getMaCongDoan().equals(maCongDoan)) {
                return congDoan;
            }
        }
        return null;
    }
    public String layMaCongDoanTiepTheo(String ma) {
        String maCongDoan = null;
        Connection connection = Database.getConnectTion(); // Corrected method name to getConnection

        try {
            String sql = "{ ? = call dbo.layMaCongDoanTiepTheo(?) }";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.NVARCHAR);
            callableStatement.setString(2, ma); // Use setString with parameter index 2
            callableStatement.execute();
            maCongDoan = callableStatement.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close(); // Close the connection in the finally block
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return maCongDoan;
    }

    public int laySoLuongThanhPhanTheoMaCongDoan(String maCongDoan) {
        int soLuongThanhPhan = 0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [soLuongThanhPhan] FROM [CongDoan] WHERE [maCongDoan] = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maCongDoan);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                soLuongThanhPhan = rs.getInt("soLuongThanhPhan");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongThanhPhan;
    }
    public static void main(String[] args) {
        try {          
            DAO_CongDoan daoCongDoan = new DAO_CongDoan();
            ArrayList<CongDoan> dsCongDoanHoanThanh = daoCongDoan.layDanhSachTatCaCongDoanDaHoanThanh();     
            for (CongDoan congDoan : dsCongDoanHoanThanh) {
                System.out.println("Mã Công Đoạn: " + congDoan.getMaCongDoan());
                System.out.println("Số Công Nhân: " + congDoan.getSoCongNhan());
                System.out.println("Loại Công Đoạn: " + congDoan.getLoaiCongDoan());
                System.out.println("Ngày Chia Công Đoạn: " + congDoan.getNgayChiaCongDoan());
                // In thêm các thông tin khác nếu cần
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void setDs(ArrayList<CongDoan> ds) {
		this.ds = ds;
	}
}
