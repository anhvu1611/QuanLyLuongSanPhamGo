package ConnectDB;

import java.sql.*;

public class Database {
    static String connectionUrl =
    "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLuongSanPham;user=sa;password=123;integratedSecurity=false;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8";

    public static Connection getConnectTion(){
        Connection conn = null;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);
//            System.out.println("Ket noi thanh cong");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return conn;
    }
    
}
