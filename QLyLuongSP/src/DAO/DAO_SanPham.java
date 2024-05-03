package DAO;

import ConnectDB.Database;
import entity.SanPham;
import Enum.LoaiGo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAO_SanPham {
    private ArrayList<SanPham> ds;

    public DAO_SanPham() {
        ds = new ArrayList<>();
//        layDanhSachSanPhamChuaHoanThanh();
    }

    public ArrayList<SanPham> getDs() {
        return ds;
    }
    
    public ArrayList<SanPham> layDanhSachSanPham() {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT * FROM [SanPham]";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                double giaThanh = rs.getDouble("giaThanh");
                String tenSP = rs.getString("tenSanPham");
                Date thoiGianSanXuatDuKien = rs.getDate("thoiGianSanXuatDuKien");
                String chatGo = rs.getString("chatGo");
                String anh = rs.getString("anh");
                String kichThuoc = rs.getString("kichThuoc");
                double khoiLuong = rs.getDouble("khoiLuong");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                LocalDate tg = rs.getDate("thoiGianSanXuatDuKien").toLocalDate();

                int trangThai = rs.getInt("trangThaiSanPham");
                
                LoaiGo loaiGo = LoaiGo.GOTOT;
                if(chatGo.trim().equalsIgnoreCase("Gỗ Hương")) {
                	loaiGo = LoaiGo.GOTHUONG;
                }else if(chatGo.trim().equalsIgnoreCase("Gỗ Sao")) {
                	loaiGo = LoaiGo.GOTRUNG;
                }

                boolean daTonTai = false;
                for (SanPham sp : ds) {
                    if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                        daTonTai = true;
                        break;
                    }
                }

                if (!daTonTai) {
                	SanPham sanPham = new SanPham(maSanPham, tenSP, giaThanh, tg, loaiGo, anh, kichThuoc, trangThai, soLuongSanPham, khoiLuong);
                    ds.add(sanPham);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<SanPham> layDanhSachSanPhamChuaHoanThanh() {
        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maSanPham], [giaThanh], [tenSanPham], [thoiGianSanXuatDuKien], [chatGo], [anh], [kichThuoc] FROM [SanPham] WHERE [trangThaiSanPham] = 0";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                double giaThanh = rs.getDouble("giaThanh");
                String tenSP = rs.getString("tenSanPham");
                Date thoiGianSanXuatDuKien = rs.getDate("thoiGianSanXuatDuKien");
                String chatGo = rs.getString("chatGo");
                String anh = rs.getString("anh");
                String kichThuoc = rs.getString("kichThuoc");
                
                LoaiGo loaiGo = LoaiGo.GOTOT;
                if(chatGo.trim().equalsIgnoreCase("Gỗ Hương")) {
                	loaiGo = LoaiGo.GOTHUONG;
                }else if(chatGo.trim().equalsIgnoreCase("Gỗ Sao")) {
                	loaiGo = LoaiGo.GOTRUNG;
                }
//                String kichThuoc = rs.getString("kichThuoc");               
//                LoaiGo loaiGo = LoaiGo.valueOf(chatGo);
                // Kiểm tra xem sản phẩm đã tồn tại trong danh sách hay chưa
                boolean daTonTai = false;
                for (SanPham sp : ds) {
                    if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                        daTonTai = true;
                        break;
                    }
                }

                if (!daTonTai) {
                    SanPham sanPham = new SanPham(maSanPham, tenSP, giaThanh, thoiGianSanXuatDuKien.toLocalDate(), loaiGo, anh, kichThuoc);
                    ds.add(sanPham);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    public ArrayList<SanPham> layDanhSachSanPhamDaHoanThanh() {
        ArrayList<SanPham> dsSanPhamDaHoanThanh = new ArrayList<>();

        try {
            Connection conn = Database.getConnectTion();
            String sql = "SELECT [maSanPham], [giaThanh], [tenSanPham], [thoiGianSanXuatDuKien], [chatGo], [anh], [kichThuoc] FROM [SanPham] WHERE [trangThaiSanPham] = 1";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                double giaThanh = rs.getDouble("giaThanh");
                String tenSP = rs.getString("tenSanPham");
                Date thoiGianSanXuatDuKien = rs.getDate("thoiGianSanXuatDuKien");
                String chatGo = rs.getString("chatGo");
                String anh = rs.getString("anh");
                String kichThuoc = rs.getString("kichThuoc");

                LoaiGo loaiGo = LoaiGo.GOTOT;
                if (chatGo.trim().equalsIgnoreCase("Gỗ Hương")) {
                    loaiGo = LoaiGo.GOTHUONG;
                } else if (chatGo.trim().equalsIgnoreCase("Gỗ Sao")) {
                    loaiGo = LoaiGo.GOTRUNG;
                }

                // Kiểm tra xem sản phẩm đã tồn tại trong danh sách hay chưa
                boolean daTonTai = false;
                for (SanPham sp : dsSanPhamDaHoanThanh) {
                    if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                        daTonTai = true;
                        break;
                    }
                }

                if (!daTonTai) {
                    SanPham sanPham = new SanPham(maSanPham, tenSP, giaThanh, thoiGianSanXuatDuKien.toLocalDate(), loaiGo, anh, kichThuoc);
                    dsSanPhamDaHoanThanh.add(sanPham);
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsSanPhamDaHoanThanh;
    }
    public static String layMaSanPhamTiepTheo() {
        String maSanPham = null;
        Connection connection = Database.getConnectTion();
        try {
            String sql = "{ ? = call dbo.layMaSanPhamTiepTheo() }";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.NVARCHAR);
            callableStatement.execute();
            maSanPham = callableStatement.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maSanPham;
    }

    public SanPham timSanPhamTheoMa(String maSanPham) {
        for (SanPham sp : ds) {
            if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                return sp;
            }
        }
        return null;
    }

//    public void themSanPham(SanPham sanPham) {
//        try {
//            Connection conn = Database.getConnectTion();
//            String sql = "INSERT INTO [SanPham] ([maSanPham], [giaThanh], [tenSanPham], [thoiGianSanXuatDuKien], [chatGo], [anh], [kichThuoc]) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, sanPham.getMaSanPham());
//            statement.setDouble(2, sanPham.getGiaThanh());
//            statement.setString(3, sanPham.getTenSP());
//            statement.setDate(4, java.sql.Date.valueOf(sanPham.getThoiGianSanXuatDuKien()));
//            statement.setString(5, sanPham.getChatGo().toString());
//            statement.setString(6, sanPham.getAnh());
//            statement.setString(7, sanPham.getKichThuoc());
//            statement.executeUpdate();
//            conn.close();
//            ds.add(sanPham);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public boolean themSanPham(SanPham sanPham) {
    	int n=0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "INSERT INTO [SanPham] " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, sanPham.getMaSanPham());
            statement.setDouble(2, sanPham.getGiaThanh());
            statement.setString(3, sanPham.getTenSP());
            statement.setDate(7, java.sql.Date.valueOf(sanPham.getThoiGianSanXuatDuKien()));
            statement.setString(4, sanPham.getChatGo().toString());
            statement.setString(5, sanPham.getAnh());
            statement.setString(6, sanPham.getKichThuoc());
            statement.setInt(8, sanPham.getSoLuong());
            statement.setInt(9, sanPham.getTrangThai());
            statement.setDouble(10, sanPham.getKhoiLuong());    
            n =  statement.executeUpdate();
            conn.close();
            ds.add(sanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }

    public boolean capNhatSanPham(SanPham sanPham) {
    	int n = 0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "UPDATE [SanPham] " +
                    "SET [giaThanh] = ?, [tenSanPham] = ?, [thoiGianSanXuatDuKien] = ?, [chatGo] = ?, [anh] = ?, [kichThuoc] = ? " +
                    "WHERE [maSanPham] = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, sanPham.getGiaThanh());
            statement.setString(2, sanPham.getTenSP());
            statement.setDate(3, java.sql.Date.valueOf(sanPham.getThoiGianSanXuatDuKien()));
            statement.setString(4, sanPham.getChatGo().toString());
            statement.setString(5, sanPham.getAnh());
            statement.setString(6, sanPham.getKichThuoc());
            statement.setString(7, sanPham.getMaSanPham());
            n = statement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }

    public boolean xoaSanPham(String maSanPham) {
    	int n = 0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "DELETE FROM [SanPham] WHERE [maSanPham] = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maSanPham);
            n = statement.executeUpdate();
            conn.close();
            SanPham sanPham = timSanPhamTheoMa(maSanPham);
            if (sanPham != null) {
                ds.remove(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
}
