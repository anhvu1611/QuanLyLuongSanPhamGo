/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.DiaChi;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

/**
 *
 * @author ACER
 */
public class DiaChi_Dao {
    private ArrayList<DiaChi> ds;
    private DiaChi dc;

    public DiaChi_Dao() {
        ds = new ArrayList<>();
        layDanhSachDiaChi();
    }

    public ArrayList<DiaChi> getDs() {
        return ds;
    }
    
    public ArrayList<DiaChi> layDanhSachDiaChi(){
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select * from DiaChi";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int maDiaChi = rs.getInt(1);
                String tenDuong = rs.getString(2);
                String tenTinhThanh = rs.getString(3);
                String tenQuanHuyen = rs.getString(4);
                String tenThiXa = rs.getString(5);
                dc = new DiaChi(maDiaChi, tenDuong, tenThiXa, tenQuanHuyen, tenTinhThanh);
                ds.add(dc);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public DiaChi taoDiaChiVaTraDiaChi(String tenDuong, String tenTinhThanh, String tenQuanHuyen, String tenThiXa){
    	Connection con = Database.getConnectTion();
    	DiaChi dc = null;
    	try {
            String storedProcedureCall = "{call chenVaLayDuLieuDiaChi(?,?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = con.prepareCall(storedProcedureCall);
            statement.setString(1, tenDuong);
            statement.setString(2, tenTinhThanh);
            statement.setString(3, tenQuanHuyen);
            statement.setString(4, tenThiXa);
            statement.registerOutParameter(5, Types.INTEGER); // maDiaChi
            statement.registerOutParameter(6, Types.NVARCHAR); // tenDuong
            statement.registerOutParameter(7, Types.NVARCHAR); // tenTinhThanh
            statement.registerOutParameter(8, Types.NVARCHAR); // tenQuanHuyen
            statement.registerOutParameter(9, Types.NVARCHAR); // tenThiXa

            statement.execute();

            // Lấy giá trị từ các tham số đầu ra
            int maDiaChi = statement.getInt(5);
            String tenDuongOutput = statement.getString(6);
            String tenTinhThanhOutput = statement.getString(7);
            String tenQuanHuyenOutput = statement.getString(8);
            String tenThiXaOutput = statement.getString(9);

            // Sử dụng giá trị nhận được
            dc = new DiaChi(maDiaChi, tenDuongOutput, tenThiXaOutput, tenQuanHuyenOutput, tenTinhThanhOutput);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý các lỗi kết nối hoặc lỗi stored procedure ở đây
        }
    	return dc;
    }
    
    public boolean xoaDiaChi(String maDiaChi){
        Connection con = Database.getConnectTion();
        PreparedStatement stmt = null;
        int n=0;
        String sql = "delete from DiaChi where maDiaChi = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maDiaChi);
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
    public boolean suaDiaChi(DiaChi d){
        Connection con = Database.getConnectTion();
        PreparedStatement stmt = null;
        int n=0;
        String sql = "update DiaChi set tenDuong = ?, tenTinhThanh = ?, tenQuanHuyen = ?, tenThiXa = ? where maDiaChi = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, d.getSoNha());
            stmt.setString(2, d.getTenTinhThanh());
            stmt.setString(3, d.getTenQuanHuyen());
            stmt.setString(4, d.getTenThiXa());            
            stmt.setInt(5, d.gettMaDiaChi());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
    public DiaChi layDiaChiTheoMa(int ma){
        for (DiaChi d : ds) {
            if(ma == d.gettMaDiaChi()){
                return d;
            }
        }
        return null;
    }
    
    public String[] layDanhSachTinhThanhPho(){
        String bangTenTinhThanh[] = new String[63];
        int i=0;
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select ten from TinhThanh";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String ten = rs.getString(1);
                bangTenTinhThanh[i]= ten;
                i++;
            }
            conn.close();
            statement.close();
            return bangTenTinhThanh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bangTenTinhThanh;
    }
    
    public ArrayList<String> layDanhSachTinhThanhPhoMangDong(){
        ArrayList<String> bangTenTinhThanh = new ArrayList<String>();
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select ten from TinhThanh";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String ten = rs.getString(1);
                bangTenTinhThanh.add(ten);
            }
            conn.close();
            statement.close();
            return bangTenTinhThanh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bangTenTinhThanh;
    }
    
    public String[] layQuanHuyenDuaTheoTinhThanhPho(String tinhThanhPho){
        Connection con = Database.getConnectTion();
        String storedProcedureCall = "{call layQuanHuyenTheoTinhThanhPho(?)}";
        try {
            CallableStatement statement = con.prepareCall(storedProcedureCall);
            statement.setString(1, tinhThanhPho);
            statement.execute();
            List<String> dsQuanHuyen = new ArrayList<String>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String quanHuyen = resultSet.getString(1);
                dsQuanHuyen.add(quanHuyen);
            }
            resultSet.close();
            statement.close();
            con.close();
            return dsQuanHuyen.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
    }
    
    public String[] layPhuongXaDuaTrenQuanHuyenVaTinhThanhPho(String tinhThanhPho, String quanHuyen){
        Connection con = Database.getConnectTion();
        String storedProcedureCall = "{call layPhuongDuaTrenThanhPhoVaTinh(?, ?)}";
        try {
            CallableStatement statement = con.prepareCall(storedProcedureCall);
            statement.setString(1, quanHuyen);
            statement.setString(2, tinhThanhPho);
            statement.execute();
            List<String> dsThiXa = new ArrayList<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String thiXa = resultSet.getString(1);
                dsThiXa.add(thiXa);
            }
            resultSet.close();
            statement.close();
            con.close();
            return dsThiXa.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
    }

//    public static void main(String[] args) {
//    	DiaChi dctest = new DiaChi_Dao().taoDiaChiVaTraDiaChi("haule", "haule", "haule", "haule");
//    	System.out.println(dctest.toString());
//    }
}
