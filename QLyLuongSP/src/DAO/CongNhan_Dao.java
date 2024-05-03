package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ConnectDB.Database;
import Enum.GioiTinh;
import Enum.PhuCap;
import entity.ChuyenMon;
import entity.CongNhan;
import entity.DiaChi;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.Date;

public class CongNhan_Dao {
	private ArrayList<CongNhan> ds;
	private static Connection con;
	public CongNhan_Dao() {
		ds = new ArrayList<>();
//		con = ConnectDB.getInstance().getConnection();		
		layDanhSachCongNhan();
	}
	
    public ArrayList<CongNhan> getDs() {
		return ds;
	}



	public void setDs(ArrayList<CongNhan> ds) {
		this.ds = ds;
	}

	public ArrayList<CongNhan> layDanhSachCongNhan(){
        try {
        	con = Database.getConnectTion();
            DiaChi_Dao qlDiaChi = new DiaChi_Dao();
            DiaChi diaChi = null;
            GioiTinh gt;
            PhuCap pc = PhuCap.MUC_2;
            ChuyenMon_Dao qlChuyenMon = new ChuyenMon_Dao();
            ChuyenMon chuyenMon = null; 
            boolean trangThai = false;
            String sql = "select * from CongNhan";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String maCongNhan = rs.getString("maCongNhan");
                String ho = rs.getString("ho");
                String ten = rs.getString("ten");
                String soDienThoai = rs.getString("soDienThoai");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String CCCD = rs.getString("CCCD");
                String gioiTinh = rs.getString("gioiTinh");
                String anh = rs.getString("anh");
                int maDiaChi = rs.getInt("maDiaChi");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maChuyenMon = rs.getString("maChuyenMon");
                int trangThaiInt = rs.getInt("trangThai");
                chuyenMon = qlChuyenMon.timChuyenMonTheoMa(maChuyenMon);
                if(trangThaiInt == 0) {
                	trangThai = true;
                }
                diaChi =  qlDiaChi.layDiaChiTheoMa(maDiaChi);               
                if(gioiTinh.equals("Nam")){
                    gt = GioiTinh.NAM;
                }else if(gioiTinh.equals("Nữ")){
                    gt = GioiTinh.NU;
                }else{
                    gt = GioiTinh.KHAC;
                }
                
                CongNhan cn = new CongNhan(maCongNhan, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, pc, gt, anh, trangThai, chuyenMon);
                ds.add(cn);                             
            }
            con.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

	public static String layMaCongNhanTiepTheo() {
        String maCongNhan = null;
//        Connection connection = Database.getConnectTion();
        con = Database.getConnectTion();
        String sql = "{ ? = call dbo.layMaCongNhanTiepTheo() }";
        try(CallableStatement callableStatement = con.prepareCall(sql);) {
            callableStatement.registerOutParameter(1, Types.NVARCHAR);
            callableStatement.execute();
            maCongNhan = callableStatement.getString(1);
            con.close();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return maCongNhan;
    }
        
//	public static CongNhan timCongNhanBangMa(String maCongNhan) {
////	public CongNhan timCongNhanBangMa(String maCongNhan) {
////		String sql = "select * from CongNhan where maCongNhan = ?";
////		CongNhan congNhan = null;
////		con = Database.getConnectTion();
////		try (PreparedStatement stmt = con.prepareStatement(sql)) {
////			stmt.setString(1, maCongNhan);
////			ResultSet rs = stmt.executeQuery();
////			while (rs.next()) {
////				congNhan = new CongNhan(rs.getString(1), rs.getString(2), rs.getString(3));
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return congNhan;
////	}

	public static CongNhan timCongNhanBangMa(String maCongNhan) {
		String sql = "select * from CongNhan where maCongNhan = ?";
		CongNhan congNhan = null;
		con = Database.getConnectTion();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maCongNhan);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String ho = rs.getString("ho");
				String ten = rs.getString("ten");
				String sdt = rs.getString("soDienThoai");
				Date ngaySinh = rs.getDate("ngaySinh");
				Date ngayVaoLam = rs.getDate("ngayVaoLam");
				String CCCD = rs.getString("CCCD");
				int maDiaChi = rs.getInt("maDiaChi");
				DiaChi diaChi = new DiaChi_Dao().layDiaChiTheoMa(maDiaChi);
				int phuCapCN = rs.getInt("phuCap");
				PhuCap phuCap = null;
				if(phuCapCN == 500000) {
					phuCap = PhuCap.MUC_1;
				}else if(phuCapCN == 750000) {
					phuCap = PhuCap.MUC_2;
				}else if(phuCapCN == 1000000) {
					phuCap = PhuCap.MUC_3;
				}else {
					phuCap = PhuCap.MUC_4;
				}
				String gioiTinh = rs.getString("gioiTinh");
				GioiTinh gt = null;
				if(gioiTinh.equals("Nam")){
                    gt = GioiTinh.NAM;
                }else if(gioiTinh.equals("Nữ")){
                    gt = GioiTinh.NU;
                }else{
                    gt = GioiTinh.KHAC;
                }
				String anh = rs.getString("anh");
				int trangThaiCN = rs.getInt("trangThai");
				boolean trangThai = false;
				if(trangThaiCN==0) {
					trangThai = true;
				}
				String maChuyenMon = rs.getString("maChuyenMon");
				ChuyenMon chuyenMon = new ChuyenMon_Dao().timChuyenMonTheoMa(maChuyenMon);
				congNhan = new CongNhan(maCongNhan, ho, ten, sdt, ngaySinh, ngayVaoLam, CCCD, diaChi, phuCap, gt, anh, trangThai, chuyenMon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congNhan;
	}
	
	//Tìm nhân viên theo nhiều tiêu chí
		public CongNhan[] tieuChiTimKiemCongNhan(String maCongNhan, ChuyenMon chuyenMon, GioiTinh gioiTinh,
				String ho, String ten, String cccd, String sdt,
				String tinhThanhPho, String quanHuyen, String xa,
				java.util.Date ngaySinh, java.util.Date ngayVaoLam) {
		    ArrayList<CongNhan> ketQua = new ArrayList<>();
		    StringBuilder queryBuilder = new StringBuilder
		            ("SELECT maCongNhan FROM CongNhan cn"
		                    + " join DiaChi dc on dc.maDiaChi = cn.maDiaChi"
		                    + " join ChuyenMon cm on cm.maChuyenMon = cn.maChuyenMon WHERE 1=1");

		    if (maCongNhan != null && !maCongNhan.isEmpty()) {
		        queryBuilder.append(" AND maCongNhan = '").append(maCongNhan.trim()).append("'");
		    }
		    
		    if (chuyenMon != null) {
		        queryBuilder.append(" AND cm.maChuyenMon = '").append(chuyenMon.getMaChuyenMon().trim()).append("'");
		    }

		    if (gioiTinh != null) {
		        queryBuilder.append(" AND gioiTinh = '").append(gioiTinh.getGioiTinh().trim()).append("'");
		    }

		    if (ho != null && !ho.equalsIgnoreCase("Nhập Họ") && !ho.isEmpty()) {
		        queryBuilder.append(" AND ho like N'%").append(ho.trim()).append("%'");
		    }

		    if (ten != null && !ten.equalsIgnoreCase("Nhập Tên") && !ten.isEmpty()) {
		        queryBuilder.append(" AND ten like N'%").append(ten.trim()).append("%'");
		    }

		    if (cccd != null && !cccd.equalsIgnoreCase("Căn cước") && !cccd.isEmpty()) {
		        queryBuilder.append(" AND CCCD = '").append(cccd.trim()).append("'");
		    }

		    if (sdt != null && !sdt.equalsIgnoreCase("Số điện thoại") && !sdt.isEmpty()) {
		        queryBuilder.append(" AND soDienThoai = '").append(sdt.trim()).append("'");
		    }

		    if (tinhThanhPho != null && !tinhThanhPho.isEmpty() && !tinhThanhPho.equalsIgnoreCase("Bất Kì")) {
		        queryBuilder.append(" AND dc.tenTinhThanh = N'").append(tinhThanhPho.trim()).append("'");
		    }

		    if (quanHuyen != null && !quanHuyen.isEmpty() && !quanHuyen.equalsIgnoreCase("Bất Kì")) {
		        queryBuilder.append(" AND dc.tenQuanHuyen = N'").append(quanHuyen.trim()).append("'");
		    }

		    if (xa != null && !xa.isEmpty() && !xa.equalsIgnoreCase("Bất Kì")) {
		        queryBuilder.append(" AND dc.tenThiXa = N'").append(xa.trim()).append("'");
		    }

		    if (ngaySinh != null) {
		        queryBuilder.append(" AND ngaySinh = '").append(new Date(ngaySinh.getTime())).append("'");
		    }

		    if (ngayVaoLam != null) {
		        queryBuilder.append(" AND ngayVaoLam = '").append(new Date(ngayVaoLam.getTime())).append("'");
		    }


		    // Thêm các điều kiện tìm kiếm khác vào queryBuilder tương tự như trên

		    try {
		        Connection conn = Database.getConnectTion();
		        Statement statement = conn.createStatement();
		        ResultSet rs = statement.executeQuery(queryBuilder.toString());
		        while (rs.next()) {
		            String maCongNhanResult = rs.getString("maCongNhan");
		            CongNhan congNhan = timCongNhanTheoMa(maCongNhanResult);
		            ketQua.add(congNhan);
		        }
		        statement.close();
		        conn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return ketQua.toArray(new CongNhan[0]);
		}
		
		public CongNhan timCongNhanTheoMa(String maCongNhanResult) {
			for (CongNhan congNhan : ds) {
				if(congNhan.getMaNhanSu().equalsIgnoreCase(maCongNhanResult)) {
					return congNhan;
				}
			}
			return null;
		}

//		public CongNhan timChuyenMonPhuCapCongNhanBangMa(String maCongNhan) {
//			String sql = "select * from CongNhan where maCongNhan = ?";
//			CongNhan congNhan = null;
//			con = Database.getConnectTion();
//			try (PreparedStatement stmt = con.prepareStatement(sql)) {
//				stmt.setString(1, maCongNhan);
//				ResultSet rs = stmt.executeQuery();
//				while (rs.next()) {
//					PhuCap phuCap = null;
//					int phuCapCN = rs.getInt(8);
//					if(phuCapCN == 5000000) {
//						phuCap = PhuCap.MUC_1;
//					}else if(phuCapCN == 750000) {
//						phuCap = PhuCap.MUC_2;
//					}else if(phuCapCN == 1000000) {
//						phuCap = PhuCap.MUC_3;
//					}else {
//						phuCap = PhuCap.MUC_4;
//					}
//					ChuyenMon chuyenMon = new ChuyenMon_Dao().timChuyenMonTheoMa(rs.getString(10));
//					congNhan = new CongNhan(rs.getString(1), rs.getString(2), rs.getString(3), phuCap, chuyenMon);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return congNhan;
//		}

		public boolean themCongNhan(CongNhan nv) {
			Connection con = Database.getConnectTion();
	        PreparedStatement stmt = null;
	        int n=0;
	        String sql = "insert into CongNhan values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        try {
	            stmt = con.prepareStatement(sql);
	            stmt.setString(1, nv.getMaNhanSu());
	            stmt.setString(2, nv.getHo());
	            stmt.setString(3, nv.getTen());
	            stmt.setString(4, nv.getSoDienThoai());            
	            stmt.setDate(5, new Date(nv.getNgaySinh().getTime()));
	            stmt.setDate(6, new Date(nv.getNgayVaoLam().getTime()));
	            stmt.setString(7, nv.getCCCD());
	            stmt.setInt(8, nv.getPhuCap().getGiaTri());
	            stmt.setString(9, nv.getGioiTinh().getGioiTinh());
	            stmt.setString(10, nv.getChuyenMon().getMaChuyenMon());
	            stmt.setInt(11, (nv.isTrangThai())?0:1);
	            stmt.setInt(12, nv.getDiaChi().gettMaDiaChi());
	            stmt.setString(13, nv.getAnh());
	            n = stmt.executeUpdate();
	            ds.add(nv);
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n>0;
		}

		public boolean suaCongNhan(CongNhan cnBanDau) {
	        try (Connection conn = Database.getConnectTion()) {
	            String storedProcedure = "{call capNhatThongTinCongNhan(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	            try (PreparedStatement statement = conn.prepareCall(storedProcedure)) {
	                statement.setString(1, cnBanDau.getMaNhanSu());
	                statement.setString(2, cnBanDau.getHo());
	                statement.setString(3, cnBanDau.getTen());
	                statement.setString(4, cnBanDau.getSoDienThoai());
	                statement.setDate(5, new Date(cnBanDau.getNgaySinh().getTime()));
	                statement.setDate(6, new Date(cnBanDau.getNgayVaoLam().getTime()));
	                statement.setString(7, cnBanDau.getCCCD());
	                statement.setInt(8, cnBanDau.getPhuCap().getGiaTri());
	                statement.setString(9, cnBanDau.getGioiTinh().getGioiTinh());
	                statement.setString(10, cnBanDau.getChuyenMon().getMaChuyenMon());
	                statement.setInt(11, (cnBanDau.isTrangThai())?0:1);
	                statement.setInt(12, cnBanDau.getDiaChi().gettMaDiaChi());
	                statement.setString(13, cnBanDau.getDiaChi().getSoNha());
	                statement.setString(14, cnBanDau.getDiaChi().getTenTinhThanh());
	                statement.setString(15, cnBanDau.getDiaChi().getTenQuanHuyen());
	                statement.setString(16, cnBanDau.getDiaChi().getTenThiXa());
	                statement.setString(17, cnBanDau.getAnh());
	                int rowsAffected = statement.executeUpdate();
	                capNhatLaiDanhSach(cnBanDau);
	                return rowsAffected > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle your SQL exception here
	        }
	        return false;
	    }

		private void capNhatLaiDanhSach(CongNhan cnBanDau) {
		    	for (CongNhan congNhan : ds) {
					if(congNhan.getMaNhanSu().equalsIgnoreCase(cnBanDau.getMaNhanSu())) {
						int viTri = ds.indexOf(congNhan);
						ds.remove(viTri);
						ds.add(viTri, cnBanDau);
						return;
					}
				}
    }
		
	
		public ArrayList<CongNhan> timCongNhanTheoChuyenMon(String chuyenMon) {
			ArrayList<CongNhan> data = new ArrayList<>();
			for (CongNhan congNhan : ds) {
				if(congNhan.getChuyenMon().getTenChuyenMon().equalsIgnoreCase(chuyenMon)) {
					data.add(congNhan);
				}
			}
			return data;
		}
		
		public ArrayList<CongNhan> layDanhSachCongNhanTheoMaChuyenMon(String maChuyenMon) {
		    ArrayList<CongNhan> dsCongNhanTheoMaCM = new ArrayList<>();
		    try {
		        con = Database.getConnectTion();
		        DiaChi_Dao qlDiaChi = new DiaChi_Dao();
		        DiaChi diaChi = null;
		        GioiTinh gt;
		        PhuCap pc = PhuCap.MUC_2;
		        ChuyenMon_Dao qlChuyenMon = new ChuyenMon_Dao();
		        ChuyenMon chuyenMon = qlChuyenMon.timChuyenMonTheoMa(maChuyenMon);
		        boolean trangThai;

		        String sql = "SELECT * FROM CongNhan WHERE maChuyenMon = ?";
		        try (PreparedStatement statement = con.prepareStatement(sql)) {
		            statement.setString(1, maChuyenMon);
		            ResultSet rs = statement.executeQuery();
		            while (rs.next()) {
		                String maCongNhan = rs.getString("maCongNhan");
		                String ho = rs.getString("ho");
		                String ten = rs.getString("ten");
		                String soDienThoai = rs.getString("soDienThoai");
		                Date ngayVaoLam = rs.getDate("ngayVaoLam");
		                String CCCD = rs.getString("CCCD");
		                String gioiTinh = rs.getString("gioiTinh");
		                String anh = rs.getString("anh");
		                int maDiaChi = rs.getInt("maDiaChi");
		                Date ngaySinh = rs.getDate("ngaySinh");
		                int trangThaiInt = rs.getInt("trangThai");

		                trangThai = (trangThaiInt == 0);
		                diaChi = qlDiaChi.layDiaChiTheoMa(maDiaChi);
		                if (gioiTinh.equals("Nam")) {
		                    gt = GioiTinh.NAM;
		                } else if (gioiTinh.equals("Nữ")) {
		                    gt = GioiTinh.NU;
		                } else {
		                    gt = GioiTinh.KHAC;
		                }

		                CongNhan cn = new CongNhan(maCongNhan, ho, ten, soDienThoai, ngaySinh, ngayVaoLam, CCCD, diaChi, pc, gt, anh, trangThai, chuyenMon);
		                dsCongNhanTheoMaCM.add(cn);
		            }
		        } finally {
		            con.close();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return dsCongNhanTheoMaCM;
		}
}