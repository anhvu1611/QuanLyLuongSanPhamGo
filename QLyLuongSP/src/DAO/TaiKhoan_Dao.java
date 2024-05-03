/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class TaiKhoan_Dao {
    private ArrayList<TaiKhoan> ds;
    private TaiKhoan tk = null;

    public TaiKhoan_Dao() {
        ds= new ArrayList<TaiKhoan>();
    }

    public ArrayList<TaiKhoan> getDs() {
        return ds;
    }
    
    public ArrayList<TaiKhoan> layDanhSachTaiKhoan(){
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select * from TaiKhoan";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maTaiKhoan = rs.getString(1);
                String tenTaiKhoan = rs.getString(2);
                String matKhau = rs.getString(3);
                tk = new TaiKhoan(maTaiKhoan, tenTaiKhoan, matKhau);
                ds.add(tk);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
//    public static void main(String[] args) {
//        ArrayList<TaiKhoan> ds = new DAO_TaiKhoan().layDanhSachTaiKhoan();
//        for (TaiKhoan d : ds) {
//            System.out.println(d.toString());
//        }
//    }
    
    public TaiKhoan DangNhap(String tenTaiKhoan, String matKhau){
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select * from TaiKhoan where tenTaiKhoan = ? and matKhau = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tenTaiKhoan);
            statement.setString(2, matKhau);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maTaiKhoan = rs.getString(1);
                String tenTaiKhoanDB = rs.getString(2);
                String matKhauDB = rs.getString(3);
                TaiKhoan tk = new TaiKhoan(maTaiKhoan, tenTaiKhoanDB, matKhauDB);
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean quenMatKhau(String email){
        return false;
    }
    
    public static boolean doiMatKhau(String maTaiKhoan, String matKhauMoi){
        Connection con = null;
        PreparedStatement stmt = null;
        int n=0;
        String sql = "update taiKhoan set matKhau = ? where maTaiKhoan = ?";
        try {
        	con = Database.getConnectTion();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, matKhauMoi);
            stmt.setString(2, maTaiKhoan);          
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
    public TaiKhoan layTaiKhoanTheoMa(String ma){
        for (TaiKhoan d : layDanhSachTaiKhoan()) {
            if(d.getMaTaiKhoan().equals(ma)){
                return d;
            }
        }
        return null;
    }
    
    public static String layEmailTheoMa(String ma) {
    	String email = "";
    	try {
            Connection conn = Database.getConnectTion();
            String sql = "select email from taiKhoan tk join NhanVien nv on nv.maTaiKhoan=tk.maTaiKhoan where maNhanVien = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ma);
            ResultSet rs = statement.executeQuery();          
            while (rs.next()) {
                email = rs.getString(1);
            }
            conn.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return email;
    }

    
}
