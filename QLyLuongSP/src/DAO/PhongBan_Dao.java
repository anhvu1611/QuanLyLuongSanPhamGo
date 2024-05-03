/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.PhongBan;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER
 */
public class PhongBan_Dao {
    private ArrayList<PhongBan> ds;
    private PhongBan phongBan = null;
    public PhongBan_Dao() {
        ds = new ArrayList<PhongBan>();
        layDanhSachPhongBan();
    }

    public ArrayList<PhongBan> getDs() {
        return ds;
    }
    
    public ArrayList<PhongBan> layDanhSachPhongBan(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Connection conn = Database.getConnectTion();
            String sql = "select * from PhongBan";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maPhongBan = rs.getString(1);
                String tenPhongBan = rs.getString(2);
                int soLuongNhanVien = Integer.parseInt(rs.getString(3));
                LocalDate ngayThanhLap = LocalDate.parse(rs.getString(4));
                String soDienThoaiLienLac = rs.getString(5);
                String moTa = rs.getString(6);
                phongBan = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, ngayThanhLap, soDienThoaiLienLac, moTa);
                ds.add(phongBan);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
//    public PhongBan[] layDanhSachTenPhongBan(){
//        return (PhongBan[]) ds.toArray();
//    }
    
//    public boolean tangSoLuongNhanVien(String ma){
//        Connection con = Database.getConnectTion();
//        PreparedStatement stmt = null;
//        int n=0;
//        String sql = "update PhongBan set soLuongNhanVien = soLuongNhanVien+1 where maPhongBan = ?";
//        try {
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, ma);
//            n = stmt.executeUpdate();
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n>0;
//    }
    
    public int laySoLuongNhanVienCuaPhongBan(String ma){
        int soLuong = 0;
        try {            
            Connection conn = Database.getConnectTion();
            String sql = "select count(*) from NhanVien n "
                    + "join PhongBan b on b.maPhongBan = n.maPhongBan" +
                            "where b.maPhongBan = ?" +
                            "group by b.maPhongBan";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ma);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                soLuong = Integer.parseInt(rs.getString(1));          
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    
    public boolean kiemTraSoLuongPhongBanDaDayHayChua(PhongBan pb){
        int soLuongNhanVienHienTai = laySoLuongNhanVienCuaPhongBan(pb.getMaPhongBan());
        int soLuongNhanVienToiDa = pb.getSoLuongNhanVien();
        if(soLuongNhanVienHienTai == soLuongNhanVienToiDa){
            return false;
        }
        return true;
    }
    
    public PhongBan layPhongBanTheoMa(String ma){
        for (PhongBan d : ds) {
            if(d.getMaPhongBan().equals(ma)){
                return d;
            }
        }
        return null;
    }
    
    
}
