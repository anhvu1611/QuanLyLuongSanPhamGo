/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.ChuyenMon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class ChuyenMon_Dao {
    private ArrayList<ChuyenMon> ds;
    private ChuyenMon chuyenMon = null;

    public ChuyenMon_Dao() {
        ds = new ArrayList<>();
        layDanhSachChuyenMon();
    }
    
    public ArrayList<ChuyenMon> getDs() {
		return ds;
	}
    
    public ArrayList<ChuyenMon> layDanhSachChuyenMon(){
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select * from ChuyenMon";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maChuyenMon = rs.getString(1);
                String tenChuyenMon = rs.getString(2);
                float heSoLuong = rs.getFloat(3);
                chuyenMon = new ChuyenMon(maChuyenMon, tenChuyenMon, heSoLuong);
                ds.add(chuyenMon);
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ChuyenMon timChuyenMonTheoMa(String maChuyenMon) {
    	for (ChuyenMon chuyenMon : ds) {
			if(chuyenMon.getMaChuyenMon().equalsIgnoreCase(maChuyenMon)) {
				return chuyenMon;
			}
		}
    	return null;
    }
    
}
