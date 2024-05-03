/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.Database;
import entity.ChucVu;
import entity.DiaChi;
import Enum.GioiTinh;
import entity.NhanVien;
import entity.PhongBan;
import Enum.PhuCap;
import entity.TaiKhoan;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class NhanVien_Dao {
    private ArrayList<NhanVien> ds;

    public NhanVien_Dao() {
        ds = new ArrayList<>();
        layDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDs() {
        return ds;
    }
    
//    public static NhanVien timNhanVienBangMa_Login(String ma) {
//        	try {
//                Connection conn = Database.getConnectTion();
//                String sql = "select * from NhanVien where maNhanVien = ?";
//                PreparedStatement statement = conn.prepareStatement(sql);
//                statement.setString(1, ma);
//                ResultSet rs = statement.executeQuery();          
//                while (rs.next()) {
//                	String maNhanVien = rs.getString(1);
//                    String ho = rs.getString("ho");
//                    String ten = rs.getString("ten");
//                    String soDienThoai = rs.getString(4);
//                    String email = rs.getString(5);
//                    Date ngayVaoLam = rs.getDate(6);
//                    String CCCD = rs.getString(7);
//                    float thamNien = Float.parseFloat(rs.getString(8));
//                    float heSoLuong = Float.parseFloat(rs.getString(9));
//                    double phuCap = Double.parseDouble(rs.getString(10));
//                    String gioiTinh = rs.getString(11);
//                    String maChucVu = rs.getString(12);
//                    String maTaiKhoan = rs.getString(13);
//                    String BHYT = rs.getString(14);
//                    String BHXH = rs.getString(15);
//                    String maPhongBan = rs.getString(16);
//                    String anh = rs.getString(17);
//                    int maDiaChi = rs.getInt(18);
//                    Date ngaySinh = rs.getDate(19);
//                    boolean bhyt = false;
//                    boolean bhxh = false;
//                    if(BHYT.equalsIgnoreCase("Tham Gia")){
//                        bhyt = true;
//                    }        
//                    if(BHXH.equalsIgnoreCase("Tham Gia")){
//                        bhxh = true;
//                    }
//                    phongBan = qlPhongBan_Dao.layPhongBanTheoMa(maPhongBan);
//                    chucVu = qlChucVu.layChucVuTheoMa(maChucVu);
//                    diaChi =  qlDiaChi.layDiaChiTheoMa(maDiaChi);
//                    taiKhoan = qlTaiKhoan.layTaiKhoanTheoMa(maTaiKhoan);
//                    if(gioiTinh.equals("Nam")){
//                        gt = GioiTinh.NAM;
//                    }else if(gioiTinh.equals("Nữ")){
//                        gt = GioiTinh.NU;
//                    }else{
//                        gt = GioiTinh.KHAC;
//                    }
//                    
//                    if(phuCap == 500000){
//                        pc = PhuCap.MUC_1;
//                    }else if(phuCap == 750){
//                        pc = PhuCap.MUC_2;
//                    }else if(phuCap == 1000000){
//                        pc = PhuCap.MUC_3;
//                    }else{
//                        pc = PhuCap.MUC_4;
//                    }
//                    
//                    nv = new NhanVien(chucVu, bhyt, bhxh, phongBan, email, heSoLuong, thamNien, maNhanVien, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, pc, gt, anh);
//                }
//                conn.close();
//                statement.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        	return email;
//        
//    }
    
    public ArrayList<NhanVien> layDanhSachNhanVien(){
        try {
            TaiKhoan_Dao qlTaiKhoan = new TaiKhoan_Dao();
            DiaChi_Dao qlDiaChi = new DiaChi_Dao();
            PhongBan_Dao qlPhongBan_Dao = new PhongBan_Dao();
            ChucVu_Dao qlChucVu = new ChucVu_Dao();
            PhongBan phongBan = null;
            TaiKhoan taiKhoan = null;
            DiaChi diaChi = null;
            ChucVu chucVu = null;
            GioiTinh gt;
            NhanVien nv = null;
            PhuCap pc;
            
            Connection conn = Database.getConnectTion();
            String sql = "select * from NhanVien";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String ho = rs.getString("ho");
                String ten = rs.getString("ten");
                String soDienThoai = rs.getString(4);
                String email = rs.getString(5);
                Date ngayVaoLam = rs.getDate(6);
                String CCCD = rs.getString(7);
                float thamNien = Float.parseFloat(rs.getString(8));
                float heSoLuong = Float.parseFloat(rs.getString(9));
                double phuCap = Double.parseDouble(rs.getString(10));
                String gioiTinh = rs.getString(11);
                String maChucVu = rs.getString(12);
                String maTaiKhoan = rs.getString(13);
                String BHYT = rs.getString(14);
                String BHXH = rs.getString(15);
                String maPhongBan = rs.getString(16);
                String anh = rs.getString(17);
                int maDiaChi = rs.getInt(18);
                Date ngaySinh = rs.getDate(19);
                boolean bhyt = false;
                boolean bhxh = false;
                if(BHYT.equalsIgnoreCase("Tham Gia")){
                    bhyt = true;
                }        
                if(BHXH.equalsIgnoreCase("Tham Gia")){
                    bhxh = true;
                }
                phongBan = qlPhongBan_Dao.layPhongBanTheoMa(maPhongBan);
                chucVu = qlChucVu.layChucVuTheoMa(maChucVu);
                diaChi =  qlDiaChi.layDiaChiTheoMa(maDiaChi);
                taiKhoan = qlTaiKhoan.layTaiKhoanTheoMa(maTaiKhoan);
                if(gioiTinh.equals("Nam")){
                    gt = GioiTinh.NAM;
                }else if(gioiTinh.equals("Nữ")){
                    gt = GioiTinh.NU;
                }else{
                    gt = GioiTinh.KHAC;
                }
                
                if(phuCap == 500000){
                    pc = PhuCap.MUC_1;
                }else if(phuCap == 750){
                    pc = PhuCap.MUC_2;
                }else if(phuCap == 1000000){
                    pc = PhuCap.MUC_3;
                }else{
                    pc = PhuCap.MUC_4;
                }
                
                nv = new NhanVien(maNhanVien, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, pc, gt, anh, chucVu, taiKhoan, bhyt, bhxh, phongBan, email, heSoLuong, thamNien);
                ds.add(nv);              
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public static String layMaNhanVienTiepTheo() {
        String maNhanVien = null;
        Connection connection = Database.getConnectTion();
        try {
            // Gọi hàm từ cơ sở dữ liệu
            String sql = "{ ? = call dbo.layMaNhanVienTiepTheo() }";
            CallableStatement callableStatement = connection.prepareCall(sql);

            // Định dạng kiểu dữ liệu cho kết quả trả về
            callableStatement.registerOutParameter(1, Types.NVARCHAR);

            // Thực thi hàm
            callableStatement.execute();

            // Lấy kết quả từ hàm
            maNhanVien = callableStatement.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }

        return maNhanVien;
    }

    
    public boolean xoaNhanVien(String maNhanVien){
        Connection con = Database.getConnectTion();
        PreparedStatement stmt = null;
        int n=0;
        String sql = "delete from NhanVien where maNhanVien = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maNhanVien);
            n = stmt.executeUpdate();               
            ds.remove(timNhanVienTheoMa(maNhanVien));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
    public NhanVien timNhanVienTheoMa(String ma){
        for (NhanVien d : ds) {
            if(d.getMaNhanSu().equalsIgnoreCase(ma)){
                return d;
            }
        }
        return null;
    }
    
    private void capNhatLaiDanhSach(NhanVien nvBanDau) {
    	for (NhanVien nhanVien : ds) {
			if(nhanVien.getMaNhanSu().equalsIgnoreCase(nvBanDau.getMaNhanSu())) {
				int viTri = ds.indexOf(nhanVien);
				ds.remove(viTri);
				ds.add(viTri, nvBanDau);
				return;
			}
		}
    }
    
    public boolean suaNhanVien(NhanVien nvBanDau) {
        try (Connection conn = Database.getConnectTion()) {
            String storedProcedure = "{call capNhatThongTinNhanVien(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = conn.prepareCall(storedProcedure)) {
                statement.setString(1, nvBanDau.getMaNhanSu());
                statement.setString(2, nvBanDau.getHo());
                statement.setString(3, nvBanDau.getTen());
                statement.setString(4, nvBanDau.getSoDienThoai());
                statement.setString(5, nvBanDau.getEmail());
                statement.setDate(6, new Date(nvBanDau.getNgayVaoLam().getTime()));
                statement.setString(7, nvBanDau.getCCCD());
                statement.setFloat(8, nvBanDau.getThamNiem());
                statement.setFloat(9, nvBanDau.getHeSoLuong());
                statement.setInt(10, nvBanDau.getPhuCap().getGiaTri());
                statement.setString(11, nvBanDau.getGioiTinh().getGioiTinh());
                statement.setString(12, nvBanDau.getChucVu().getMaChucVu());
                statement.setString(13, nvBanDau.isBHYT()? "Tham Gia":"Không");
                statement.setString(14, nvBanDau.isBHXH()? "Tham Gia":"Không");
                statement.setString(15, nvBanDau.getBan().getMaPhongBan());
                statement.setString(16, nvBanDau.getAnh());
                statement.setInt(17, nvBanDau.getDiaChi().gettMaDiaChi());
                statement.setString(18, nvBanDau.getDiaChi().getSoNha());
                statement.setString(19, nvBanDau.getDiaChi().getTenTinhThanh());
                statement.setString(20, nvBanDau.getDiaChi().getTenQuanHuyen());
                statement.setString(21, nvBanDau.getDiaChi().getTenThiXa());
                statement.setDate(22, new Date(nvBanDau.getNgaySinh().getTime()));
                int rowsAffected = statement.executeUpdate();
                capNhatLaiDanhSach(nvBanDau);
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your SQL exception here
        }
        return false;
    }
    
    public boolean themNhanVien(NhanVien nv){
        Connection con = Database.getConnectTion();
        PreparedStatement stmt = null;
        int n=0;
        String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nv.getMaNhanSu());
            stmt.setString(2, nv.getHo());
            stmt.setString(3, nv.getTen());
            stmt.setString(4, nv.getSoDienThoai());            
            stmt.setString(5, nv.getEmail());
            stmt.setDate(6, new Date(nv.getNgayVaoLam().getTime()));
            stmt.setString(7, nv.getCCCD());
            stmt.setFloat(8, nv.getThamNiem());
            stmt.setFloat(9, nv.getHeSoLuong());
            stmt.setInt(10, nv.getPhuCap().getGiaTri());
            stmt.setString(11, nv.getGioiTinh().getGioiTinh());
            stmt.setString(12,nv.getChucVu().getMaChucVu());
            stmt.setString(13, null);
            stmt.setString(14, (nv.isBHYT())?"Tham Gia": "Không");     
            stmt.setString(15, (nv.isBHXH())?"Tham Gia": "Không");
            stmt.setString(16, nv.getBan().getMaPhongBan());
            stmt.setString(17, nv.getAnh());
            stmt.setInt(18, nv.getDiaChi().gettMaDiaChi());
            stmt.setDate(19, new Date(nv.getNgaySinh().getTime()));
            n = stmt.executeUpdate();
            ds.add(nv);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n>0;
    }
    
    public NhanVien[] tieuChiTimKiemNhanVien(
            String ma, String ten, String ho, String cccd,
            String sdt, float thamNien, float heSo, ChucVu chucVu,
            GioiTinh gioiTinh, PhongBan ban, String tinh, String quanHuyen,
            String thiXa, java.util.Date ngaySinh, java.util.Date ngayVaoLam, 
            String email, boolean bhyt, boolean bhxh, int lc) {
    	ArrayList<NhanVien> ketQua = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder
        		("SELECT maNhanVien FROM NhanVien nv"
        				+ " join DiaChi dc on dc.maDiaChi = nv.maDiaChi"
        				+ " join ChucVu cv on cv.maChucVu = nv.maChucVu"
        				+ " join PhongBan pb on pb.maPhongBan = nv.maPhongBan WHERE 1=1");

        if (ma != null && !ma.isEmpty()) {
            queryBuilder.append(" AND maNhanVien = '").append(ma.trim()).append("'");
        }

        if (ten != null &&!ten.equalsIgnoreCase("Nhập Tên") &&  !ten.isEmpty()) {
            queryBuilder.append(" AND ten like N'%").append(ten.trim()).append("%'");
        }

        if (ho != null && !ho.equalsIgnoreCase("Nhập Họ") &&  !ho.isEmpty()) {
            queryBuilder.append(" AND ho like N'%").append(ho.trim()).append("%'");
        }

        if (cccd != null && !cccd.equalsIgnoreCase("Căn cước") && !cccd.isEmpty()) {
        	queryBuilder.append(" AND CCCD = '").append(cccd.trim()).append("'");
        }

        if (sdt != null && sdt.equalsIgnoreCase("Số điện thoại") &&  !sdt.isEmpty()) {
        	queryBuilder.append(" AND soDienThoai = '").append(sdt.trim()).append("'");
        }
        
        if (thamNien!=0 ) {
        	queryBuilder.append(" AND thamNien = ").append(thamNien);
        }
        
        if (heSo!=0) {
        	queryBuilder.append(" AND heSo = ").append(heSo);
        }
        
        if(chucVu!= null) {
        	queryBuilder.append(" AND cv.maChucVu = '")
        	.append(chucVu.getMaChucVu().trim()).append("'");
        }
        
        if(gioiTinh != null) {
        	queryBuilder.append(" AND gioiTinh = N'")
        	.append(gioiTinh.getGioiTinh()).append("'");
        }
        
        if(ban != null) {
        	queryBuilder.append(" AND pb.maPhongBan = '")
        	.append(ban.getMaPhongBan().trim()).append("'");
        }
        
        if (tinh != null && !tinh.isEmpty()) {
            queryBuilder.append(" AND tenTinhThanh = N'")
            .append(tinh.trim()).append("'");
        }
        
        if (quanHuyen != null && !quanHuyen.isEmpty()) {
            queryBuilder.append(" AND tenQuanHuyen =N'")
            .append(quanHuyen.trim()).append("'");
        }    
        
        if (thiXa != null && !thiXa.isEmpty()) {
            queryBuilder.append(" AND tenThiXa = N'").
            append(thiXa.trim()).append("'");
        } 
        
        if(ngaySinh != null) {
        	queryBuilder.append(" AND ngaySinh = '").
        	append(new Date(ngaySinh.getTime())).append("'");
        }
        
        if(ngayVaoLam != null) {
        	queryBuilder.append(" AND ngayVaoLam = '").
        	append(new Date(ngayVaoLam.getTime())).append("'");
        }
        
        if (email != null && !email.equalsIgnoreCase("Ngập email") && !email.isEmpty()) {
            queryBuilder.append(" AND email = N'").append(email.trim()).append("'");
        }
        
        if(lc==1) {
        	if(bhxh == true) {
            	queryBuilder.append(" AND BHXH = N'").append("Tham Gia").append("'");
            }else {
            	queryBuilder.append(" AND BHXH = N'").append("Không").append("'");
            }
        	if(bhyt == true) {
            	queryBuilder.append(" AND BHYT = N'").append("Tham Gia").append("'");
            }else {
            	queryBuilder.append(" AND BHYT = N'").append("Không").append("'");
            }
        }
       
        try {
        	Connection conn = Database.getConnectTion();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryBuilder.toString());
            while(rs.next()){
            	String maNhanVien = rs.getString("maNhanVien");
            	ketQua.add(timNhanVienTheoMa(maNhanVien));
            }
            statement.close();
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ketQua.toArray(new NhanVien[0]);
    }

	public NhanVien timNhanVienTheoMaTaiKhoan(String maTaiKhoan) {
		for (NhanVien d : ds) {
            if(d.getTaiKhoan().getMaTaiKhoan().equalsIgnoreCase(maTaiKhoan)){
                return d;
            }
        }
        return null;
	}
	
	 public static NhanVien timNhanVienBangMaNhanVien(String ma){
		 NhanVien nv = null;
	        try {
	            TaiKhoan_Dao qlTaiKhoan = new TaiKhoan_Dao();
	            DiaChi_Dao qlDiaChi = new DiaChi_Dao();
	            PhongBan_Dao qlPhongBan_Dao = new PhongBan_Dao();
	            ChucVu_Dao qlChucVu = new ChucVu_Dao();
	            PhongBan phongBan = null;
	            TaiKhoan taiKhoan = null;
	            DiaChi diaChi = null;
	            ChucVu chucVu = null;
	            GioiTinh gt;
	            
	            PhuCap pc;
	            
	            Connection conn = Database.getConnectTion();
	            String sql = "select * from NhanVien where maNhanVien = ?";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, ma);
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()){
	                String maNhanVien = rs.getString(1);
	                String ho = rs.getString("ho");
	                String ten = rs.getString("ten");
	                String soDienThoai = rs.getString(4);
	                String email = rs.getString(5);
	                Date ngayVaoLam = rs.getDate(6);
	                String CCCD = rs.getString(7);
	                float thamNien = Float.parseFloat(rs.getString(8));
	                float heSoLuong = Float.parseFloat(rs.getString(9));
	                double phuCap = Double.parseDouble(rs.getString(10));
	                String gioiTinh = rs.getString(11);
	                String maChucVu = rs.getString(12);
	                String maTaiKhoan = rs.getString(13);
	                String BHYT = rs.getString(14);
	                String BHXH = rs.getString(15);
	                String maPhongBan = rs.getString(16);
	                String anh = rs.getString(17);
	                int maDiaChi = rs.getInt(18);
	                Date ngaySinh = rs.getDate(19);
	                boolean bhyt = false;
	                boolean bhxh = false;
	                if(BHYT.equalsIgnoreCase("Tham Gia")){
	                    bhyt = true;
	                }        
	                if(BHXH.equalsIgnoreCase("Tham Gia")){
	                    bhxh = true;
	                }
	                phongBan = qlPhongBan_Dao.layPhongBanTheoMa(maPhongBan);
	                chucVu = qlChucVu.layChucVuTheoMa(maChucVu);
	                diaChi =  qlDiaChi.layDiaChiTheoMa(maDiaChi);
	                taiKhoan = qlTaiKhoan.layTaiKhoanTheoMa(maTaiKhoan);
	                if(gioiTinh.equals("Nam")){
	                    gt = GioiTinh.NAM;
	                }else if(gioiTinh.equals("Nữ")){
	                    gt = GioiTinh.NU;
	                }else{
	                    gt = GioiTinh.KHAC;
	                }
	                
	                if(phuCap == 500000){
	                    pc = PhuCap.MUC_1;
	                }else if(phuCap == 750){
	                    pc = PhuCap.MUC_2;
	                }else if(phuCap == 1000000){
	                    pc = PhuCap.MUC_3;
	                }else{
	                    pc = PhuCap.MUC_4;
	                }
	                
	                nv = new NhanVien(maNhanVien, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, pc, gt, anh, chucVu, taiKhoan, bhyt, bhxh, phongBan, email, heSoLuong, thamNien);             
	            }
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return nv;
	    }

    
//    public static void main(String[] args) {
//        DAO_NhanVien daonv = new DAO_NhanVien();
//        for (NhanVien nv : daonv.getDs()) {
//            System.out.println(nv.toString());
//        }
//    }
}
