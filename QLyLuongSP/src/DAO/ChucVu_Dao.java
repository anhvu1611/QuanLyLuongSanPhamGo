/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.ChucVu;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class ChucVu_Dao {
    private ArrayList<ChucVu> ds;
    private ChucVu chucVu;
    public ChucVu_Dao() {
        ds= new ArrayList<>();
        layDanhSachChucVu();
    }
    
    public ArrayList<ChucVu> layDanhSachChucVu(){
        try {
            Connection conn = Database.getConnectTion();
            String sql = "select * from ChucVu";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maChucVu = rs.getString(1);
                String tenChucVu = rs.getString(2);
                float luongCungTheoChucVu = Float.parseFloat(rs.getString(3));
                chucVu = new ChucVu(maChucVu, tenChucVu, luongCungTheoChucVu);
                ds.add(chucVu);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ChucVu layChucVuTheoMa(String ma){
        for (ChucVu d : ds) {
            if(d.getMaChucVu().equals(ma)){
                return d;
            }
        }
        return null;
    }

	public ArrayList<ChucVu> getDs() {
		return ds;
	}

	public void setDs(ArrayList<ChucVu> ds) {
		this.ds = ds;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}
    
    
}
