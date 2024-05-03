/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.BangChamCongNhanVien_Dao;
import DAO.NhanVien_Dao;
import DAO.PhongBan_Dao;
import DAO.SanPham_Dao;
import Enum.CaLamNhanVien;
import Enum.TrangThai;
import entity.BangChamCongNhanVien;
import entity.NhanVien;
import entity.PhongBan;

/**
 *
 * @author Vu
 */
public class frm_NhanVien_ChamCong extends javax.swing.JPanel {
	private DefaultTableModel modelBCCNV;
	private DefaultTableModel modelDSCC;
	private BangChamCongNhanVien_Dao bangCCNV_Dao;
	private SanPham_Dao sanPham_Dao;
	private NhanVien_Dao nhanVien_Dao;
	private PhongBan_Dao phongBan_dao;
	private ArrayList<BangChamCongNhanVien> listCCNV;
	private ArrayList<PhongBan> listPhongBan;
	
	// Lấy ngày hôm nay
    LocalDate ngayHomNay = LocalDate.now();
    // Định dạng ngày theo yyyy-MM-dd
    DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Chuyển đổi và in ra màn hình
    String homNay = ngayHomNay.format(dinhDang);
    /**
     * Creates new form PanelChamCongNV
     */
    public frm_NhanVien_ChamCong(NhanVien nv) {
    	KetNoiCSDL();
        initComponents(nv);
        
        
    }
    // khởi tạo kết nối đến CSDL
  	public void KetNoiCSDL() {
  		try {
 			ConnectDB.ConnectDB.getInstance().connect();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
  		bangCCNV_Dao = new BangChamCongNhanVien_Dao();
  		nhanVien_Dao = new NhanVien_Dao();
  		phongBan_dao = new PhongBan_Dao();
  	}
  	// đọc dữ liệu vào table bảng chấm công
  	private void loadBangChamCongNV() {
  		String caLam;
  		ArrayList<BangChamCongNhanVien> listCCNV = bangCCNV_Dao.layDanhSachChamCongHomNay(homNay);
  		for (BangChamCongNhanVien bcc : listCCNV) {
  			modelBCCNV.addRow(new Object[] {listCCNV.indexOf(bcc)+1, 
  					bcc.getNhanVienDuocChamCong().getMaNhanSu(),
  					bcc.getNhanVienDuocChamCong().getHo()+" "+bcc.getNhanVienDuocChamCong().getTen(),
  					bcc.getCaLam(),
  					bcc.getNhanVienDuocChamCong().getBan().getTenPhongBan(),
  					bcc.getNhanVienDuocChamCong().getChucVu(),
  					bcc.getNhanVienDuocChamCong().getSoDienThoai(),
  					bcc.getTrangThai(),
  					bcc.getNgayChamCong(),
  					bcc.getDoiTuongChamCong().getTen(),
  					bcc.getMaChamCong(),
  					bcc.getSoGioTangCa()
  			});	
  		}	
  	}
  	//đọc dữ liệu vào table bảng chấm công theo phòng ban
   	private void loadBangChamCongTheoPhongBan(String phongBan) {
   		ArrayList<BangChamCongNhanVien> listCCCNHomNay = bangCCNV_Dao.layDanhSachChamCongTheoPhongBanHomNay(phongBan);
   		for (BangChamCongNhanVien bcc : listCCCNHomNay) {
   			modelBCCNV.addRow(new Object[] {listCCCNHomNay.indexOf(bcc)+1, 
   					bcc.getNhanVienDuocChamCong().getMaNhanSu(),
  					bcc.getNhanVienDuocChamCong().getHo()+" "+bcc.getNhanVienDuocChamCong().getTen(),
  					bcc.getCaLam(),
  					bcc.getNhanVienDuocChamCong().getBan().getTenPhongBan(),
  					bcc.getNhanVienDuocChamCong().getChucVu(),
  					bcc.getNhanVienDuocChamCong().getSoDienThoai(),
  					bcc.getTrangThai(),
  					bcc.getNgayChamCong(),
  					bcc.getDoiTuongChamCong().getTen(),
  					bcc.getMaChamCong(),
  					bcc.getSoGioTangCa()
   			});
   			
   		}	
   	}
  	//lấy danh sách chấm công
  	private void loadDanhSachChamCong() {
  		
  		ArrayList<NhanVien> listNVCC = bangCCNV_Dao.layDanhSachChuaChamTheoPhongBan(cmbPhongBan.getSelectedItem().toString());
  		//ArrayList<NhanVien> listNVCC = bangCCNV_Dao.layDanhSachChuaCham();
  		for (NhanVien nv : listNVCC) {
  			modelDSCC.addRow(new Object[] {listNVCC.indexOf(nv)+1, 
  					nv.getMaNhanSu(),
  					nv.getHo()+" "+nv.getTen(),
  					nv.getSoDienThoai(),
  					nv.getBan().getTenPhongBan(),
  					nv.getChucVu()
  			});
  		}	
  	}
  	
  	//Lấy dữ liệu theo dòng đã chọn để chấm công
  	private BangChamCongNhanVien layDuLieuDongDaChonDeChamCong(String maNhanVienDangNhap) {
  		if(tblDanhSachChamCong.getSelectedRow() != -1) {
  			//String maNhanVien = (String) tblDanhSachChamCong.getModel().getValueAt(tblDanhSachChamCong.getSelectedRow(), 1);
  			int row = tblDanhSachChamCong.getSelectedRow();
  	    	String maChamCong = maChamCongMoi(bangCCNV_Dao.layMaChamCongCaoNhat());
  	    	Date ngayCham = new Date();
  	    	NhanVien nguoiCham = nhanVien_Dao.timNhanVienTheoMa(maNhanVienDangNhap);
  	    	TrangThai trangThai = null;
  	    	String trangThaiDiLam = (String) cmbTrangThai.getSelectedItem();
  	    	if(trangThaiDiLam.equalsIgnoreCase("Đi làm")) {
  	    		trangThai = TrangThai.COLAM;
  	    	}else if(trangThaiDiLam.equalsIgnoreCase("Đi trễ")) {
  	    		trangThai = TrangThai.DITRE;
  	    	}else if(trangThaiDiLam.equalsIgnoreCase("Nghỉ có phép")) {
  	    		trangThai = TrangThai.NGHICOPHEP;
  	    	}else {
  	    		trangThai = TrangThai.NGHIKHONGPHEP;
  	    	}
  	    	
  	    	String ghiChu = "";
  	    	
  	    	CaLamNhanVien caLam = null;
  	    	String caLamNV = (String) cmbCaLam.getSelectedItem();
  	    	if(caLamNV.equalsIgnoreCase("Full-time")) {
  	    		caLam = CaLamNhanVien.FULLTIME;
  	    	}else if(caLamNV.equalsIgnoreCase("Part-time")) {
  	    		caLam = CaLamNhanVien.PARTTIME;
  	    	}else {
  	    		caLam = CaLamNhanVien.TANGCA;
  	    	}
  	    	
  	    	String maNhanVien = (String) tblDanhSachChamCong.getValueAt(row, 1);
  	    	NhanVien nvDuocCham = nhanVien_Dao.timNhanVienTheoMa(maNhanVien);
  	    	String soGioTangCa = cmbSoGioTangCa.getSelectedItem().toString();
  	    	int soGio = Integer.parseInt(soGioTangCa);
  	    	BangChamCongNhanVien ccnvMoi = new BangChamCongNhanVien(maChamCong, ngayCham, nguoiCham, trangThai, ghiChu, caLam, nvDuocCham,soGio);
			return ccnvMoi;  	
  		}
  		
  		return null;
  	}
  	//Lấy dữ liệu dòng đầu tiên để chấm
  	private BangChamCongNhanVien layDuLieuDongDauTienDeChamCong(String maNhanVienDangNhap) {
  		tblDanhSachChamCong.setRowSelectionInterval(0, 0);
        
  		if(tblDanhSachChamCong.getSelectedRow() != -1) {
  			//String maNhanVien = (String) tblDanhSachChamCong.getModel().getValueAt(tblDanhSachChamCong.getSelectedRow(), 1);
  			int row = tblDanhSachChamCong.getSelectedRow();
  	    	String maChamCong = maChamCongMoi(bangCCNV_Dao.layMaChamCongCaoNhat());
  	    	Date ngayCham = new Date();
  	    	
  	    	//Sửa lại thành mã người đăng nhập
//  	    	String maNhanVien111 = (String) tblDanhSachChamCong.getValueAt(row, 1);
  	    	NhanVien nguoiCham = nhanVien_Dao.timNhanVienTheoMa(maNhanVienDangNhap);
  	    	TrangThai trangThai = null;
  	    	String trangThaiDiLam = (String) cmbTrangThai.getSelectedItem();
  	    	if(trangThaiDiLam.equalsIgnoreCase("Đi làm")) {
  	    		trangThai = TrangThai.COLAM;
  	    	}else if(trangThaiDiLam.equalsIgnoreCase("Đi trễ")) {
  	    		trangThai = TrangThai.DITRE;
  	    	}else if(trangThaiDiLam.equalsIgnoreCase("Nghỉ có phép")) {
  	    		trangThai = TrangThai.NGHICOPHEP;
  	    	}else {
  	    		trangThai = TrangThai.NGHIKHONGPHEP;
  	    	}
  	    	String ghiChu = "";
  	    	CaLamNhanVien caLam = null;
  	    	String caLamNV = (String) cmbCaLam.getSelectedItem();
  	    	if(caLamNV.equalsIgnoreCase("Full-time")) {
  	    		caLam = CaLamNhanVien.FULLTIME;
  	    	}else if(caLamNV.equalsIgnoreCase("Part-time")) {
  	    		caLam = CaLamNhanVien.PARTTIME;
  	    	}else {
  	    		caLam = CaLamNhanVien.TANGCA;
  	    	}
  	    	String maNhanVien = (String) tblDanhSachChamCong.getValueAt(row, 1);
  	    	NhanVien nvDuocCham = nhanVien_Dao.timNhanVienTheoMa(maNhanVien);
  	    	String soGioTangCa = cmbSoGioTangCa.getSelectedItem().toString();
  	    	int soGio = Integer.parseInt(soGioTangCa);
  	    	BangChamCongNhanVien ccnvMoi = new BangChamCongNhanVien(maChamCong, ngayCham, nguoiCham, trangThai, ghiChu, caLam, nvDuocCham, soGio);
			return ccnvMoi;
  	    	
  		}else {
  			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để chấm công");
  		}
  		
  		return null;
  	}
  	

  	//Phát sinh mã chấm công nhân viên mới
  	private String maChamCongMoi(String maChamCong) {
		String maChamCongMoi = null;
		if(maChamCong == null){
			maChamCongMoi = "CCNV0001";
		}
		else{
			int number = Integer.parseInt(maChamCong.substring(4));
			number++;
			maChamCongMoi = String.format("CCNV%04d", number);
		}

		return maChamCongMoi;
	}
    
  	//Placehoder cho ô tìm kiếm
  	private static void addPlaceholder(JTextField textField, String placeholder) {
        // Sự kiện khi component focus được bật
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            // Sự kiện khi component focus mất đi
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        // Thiết lập màu sắc và placeholder ban đầu
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
    }
  //lấy dữ liệu cập nhật
    private BangChamCongNhanVien layDuLieuCapNhat() {
    	if(tblBangChamCong.getSelectedRow() != -1) {
    		int row = tblBangChamCong.getSelectedRow();
  			String maChamCong = (String) tblBangChamCong.getModel().getValueAt(row, 10);
  			Date ngayCham = (Date) tblBangChamCong.getModel().getValueAt(row, 8);
  			
  			//Sau này sửa thành mã người đăng nhập
  	    	NhanVien nguoiCham = nhanVien_Dao.timNhanVienTheoMa((String)tblBangChamCong.getModel().getValueAt(row, 1));
  	    	
  	    	TrangThai trangThai = null;
  	    	String  trangThaiChon = (String) cmbTrangThai.getSelectedItem();
  	    	if(trangThaiChon.equalsIgnoreCase("Đi làm")) {
  	    		trangThai = TrangThai.COLAM;
  	    	}else if(trangThaiChon.equalsIgnoreCase("Đi trễ")) {
  	    		trangThai = TrangThai.DITRE;
  	    	}else if(trangThaiChon.equalsIgnoreCase("Nghỉ có phép")) {
  	    		trangThai = TrangThai.NGHICOPHEP;
  	    	}else {
  	    		trangThai = TrangThai.NGHIKHONGPHEP;
  	    	}
  	    	String ghiChu = null;
  	    	CaLamNhanVien caLam = null;
  	    	String caLamNV = (String) cmbCaLam.getSelectedItem();
  	    	if(caLamNV.equalsIgnoreCase("Full-time")) {
  	    		caLam = CaLamNhanVien.FULLTIME;
  	    	}else if(caLamNV.equalsIgnoreCase("Part-time")) {
  	    		caLam = CaLamNhanVien.PARTTIME;
  	    	}else {
  	    		caLam = CaLamNhanVien.TANGCA;
  	    	}
  	    	String maNhanVienDuocCham = (String) tblBangChamCong.getValueAt(row, 1);
  	    	NhanVien nvDuocCham = nhanVien_Dao.timNhanVienTheoMa(maNhanVienDuocCham);
  	    	
  	    	BangChamCongNhanVien ccnvMoi = new BangChamCongNhanVien(maChamCong, ngayCham, nguoiCham, trangThai, ghiChu, caLam, nvDuocCham);
  	    	
			return ccnvMoi;
  	    	
  		}else {
  			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên ở Bảng chấm công để cập nhật");
  		}
  		
  		return null;
    }
  	/**
     * Creates new form PanelChamCongNV
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(NhanVien nv) {
    	
    	
    	
        jLabel1 = new javax.swing.JLabel();
        dchNgayCham = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cmbPhongBan = new javax.swing.JComboBox<>();
        btnLayDanhSach = new javax.swing.JButton();
        txtTimKiemDanhSachChamCong = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachChamCong = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangChamCong = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JLabel();
        cmbTrangThai = new javax.swing.JComboBox<>();
        txtTimKiemBangChamCong = new javax.swing.JTextField();
        btnChamCongTatCa = new javax.swing.JButton();
        btnChamCong = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbCaLam = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cmbLocPhongBan = new javax.swing.JComboBox<>();
        cmbSoGioTangCa = new javax.swing.JComboBox<>();
        
        btnChamCongTatCa.setEnabled(false);
        btnChamCong.setEnabled(false);
        btnCapNhat.setEnabled(false);
        //Placeholder
        addPlaceholder(txtTimKiemBangChamCong, "Nhập để tìm kiếm...");
        addPlaceholder(txtTimKiemDanhSachChamCong, "Nhập để tìm kiếm...");

        setPreferredSize(new java.awt.Dimension(1295, 634));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ngày chấm công:");
        
        dchNgayCham.setPreferredSize(new java.awt.Dimension(128, 22));
        dchNgayCham.setDateFormatString("yyyy-MM-dd");
        dchNgayCham.setEnabled(false);
        dchNgayCham.setDate(new Date());
        dchNgayCham.setPreferredSize(new java.awt.Dimension(128, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Phòng ban:");

        cmbPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listPhongBan = phongBan_dao.layDanhSachPhongBan();
        for(PhongBan pb : listPhongBan) {
        	cmbPhongBan.addItem(pb.getTenPhongBan());
        }
        cmbPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPhongBanActionPerformed(evt);
            }
        });

        btnLayDanhSach.setBackground(new java.awt.Color(89, 193, 99));
        btnLayDanhSach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLayDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-add-24.png"))); // NOI18N
        btnLayDanhSach.setText("Lấy danh sách");
        btnLayDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDanhSachActionPerformed(evt);
            }
        });

        //Tìm kiếm trong danh sách chấm công
        txtTimKiemDanhSachChamCong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtTimKiemDanhSachChamCong.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelDSCC);
				tblDanhSachChamCong.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(text));
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					modelDSCC.setRowCount(0);
					loadDanhSachChamCong();
				}
			}
		});
        txtTimKiemDanhSachChamCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemDanhSachChamCongActionPerformed(evt);
            }
        });

        tblDanhSachChamCong.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Phòng ban", "Chức vụ"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            });
            
            
            String[] colsDSCC = { "STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Phòng ban", "Chức vụ"};
    		modelDSCC = new DefaultTableModel(colsDSCC, 0);
    		tblDanhSachChamCong = new JTable(modelDSCC);
        jScrollPane2.setViewportView(tblDanhSachChamCong);


        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng chấm công nhân viên hôm nay", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        String[] colsBCCNV = { "STT", "Mã nhân viên", "Tên nhân viên", "Ca làm", "Phòng ban", "Chức vụ", "Sđt", "Trạng thái", "Ngày chấm công", "Người chấm", "Mã chấm công", "Số giờ tăng ca"};
		tblBangChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            colsBCCNV
        ));

        
        tblBangChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Ca làm", "Phòng ban", "Chức vụ", "Sđt", "Trạng thái", "Giờ đi làm", "Ngày chấm công", "Người chấm"
            }
        ));
        modelBCCNV = new DefaultTableModel(colsBCCNV, 0);
		tblBangChamCong = new JTable(modelBCCNV);
		loadBangChamCongNV();
        tblBangChamCong.setFillsViewportHeight(true);
        tblBangChamCong.setName(""); // NOI18N
        jScrollPane1.setViewportView(tblBangChamCong);
        tblBangChamCong.getAccessibleContext().setAccessibleName("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Họ và tên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số giờ tăng ca:");

        txtHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N


        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N


        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Đi trễ", "Nghỉ có phép", "Nghỉ không phép" }));
        
        cmbSoGioTangCa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2","3","4" }));

        
        cmbTrangThai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbTrangThai.getSelectedItem().toString().equalsIgnoreCase("Đi trễ") || cmbTrangThai.getSelectedItem().toString().equalsIgnoreCase("Đi làm")) {
					cmbSoGioTangCa.setEnabled(true);
		        }else {
		        	cmbSoGioTangCa.setEnabled(false);
		        }
				
			}
		});
        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Đi trễ", "Nghỉ có phép", "Nghỉ không phép" }));
        cmbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTrangThaiActionPerformed(evt);
            }
        });
      //Tìm kiếm bảng chấm công nhân viên
        txtTimKiemBangChamCong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtTimKiemBangChamCong.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelBCCNV);
				tblBangChamCong.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(text));
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					modelBCCNV.setRowCount(0);
					loadBangChamCongNV();
				}
			}
		});
        btnChamCongTatCa.setText("Chấm công tất cả");
        btnChamCongTatCa.setPreferredSize(new java.awt.Dimension(106, 40));
        btnChamCongTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongTatCaActionPerformed(evt, nv.getMaNhanSu());
            }
        });

        btnChamCong.setText("Chấm công");
        btnChamCong.setPreferredSize(new java.awt.Dimension(106, 31));
        btnChamCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongActionPerformed(evt, nv.getMaNhanSu());
            }
        });

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/update-24.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        
        tblDanhSachChamCong.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {}		
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int pos = tblDanhSachChamCong.getSelectedRow();
				txtMaNhanVien.setText(modelDSCC.getValueAt(pos, 1).toString());
				txtHoVaTen.setText(modelDSCC.getValueAt(pos, 2).toString());
			}
		});
        tblBangChamCong.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int pos = tblBangChamCong.getSelectedRow();
				txtMaNhanVien.setText(modelBCCNV.getValueAt(pos, 1).toString());
				txtHoVaTen.setText(modelBCCNV.getValueAt(pos, 2).toString());
				
			}
		});
        
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("giờ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ca làm:");

        cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full-time", "Part-time" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Lọc theo phòng ban:");

        cmbLocPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbLocPhongBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả","Kế toán", "Quản lý nhân sự", "Quản lý kho bãi", "Tuyển dụng", "Tài chính", "Kiểm toán", "Kinh doanh" }));
        cmbLocPhongBan.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String locPhongBan = cmbLocPhongBan.getSelectedItem().toString();
				modelBCCNV.setRowCount(0);
				if(locPhongBan.equalsIgnoreCase("Tất cả")) {
					loadBangChamCongNV();
				}else {
					loadBangChamCongTheoPhongBan(locPhongBan);
				}
				
				
			}
		});
        
        
        cmbSoGioTangCa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3","4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(txtTimKiemDanhSachChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(1, 1, 1)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoVaTen)
                    .addComponent(txtMaNhanVien)
                    .addComponent(cmbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbSoGioTangCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(btnChamCongTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(btnChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(btnCapNhat)
                .addGap(0, 541, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTimKiemBangChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cmbLocPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dchNgayCham, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cmbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(btnLayDanhSach)
                .addGap(48, 461, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmbPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLayDanhSach))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dchNgayCham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(4, 4, 4)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimKiemDanhSachChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChamCongTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cmbLocPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiemBangChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaNhanVien))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHoVaTen))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(cmbSoGioTangCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPhongBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPhongBanActionPerformed

    private void btnLayDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayDanhSachActionPerformed
        // TODO add your handling code here:
    	modelDSCC.setRowCount(0);
    	loadDanhSachChamCong();
    	if(modelDSCC.getRowCount()>0) {
    		btnChamCongTatCa.setEnabled(true);
        	btnChamCong.setEnabled(true);
        	btnCapNhat.setEnabled(true);
    	}else {
    		btnChamCongTatCa.setEnabled(false);
        	btnChamCong.setEnabled(false);
        	btnCapNhat.setEnabled(false);
        	JOptionPane.showMessageDialog(null, "Phòng ban này đã chấm hết");
    	}
    }//GEN-LAST:event_btnLayDanhSachActionPerformed

    private void txtTimKiemDanhSachChamCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemDanhSachChamCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemDanhSachChamCongActionPerformed

    private void btnChamCongTatCaActionPerformed(java.awt.event.ActionEvent evt, String ma) {//GEN-FIRST:event_btnChamCongTatCaActionPerformed
        // TODO add your handling code here:
    	int totalRowCount = modelDSCC.getRowCount();
    	for(int i=0;i<totalRowCount;i++) {
    		BangChamCongNhanVien ccnvMoi = layDuLieuDongDauTienDeChamCong(ma);
    		if(bangCCNV_Dao.themChamCongNhanVien(ccnvMoi)==true) {
        		//reset lại bảng danh sách chấm
        		modelDSCC.setRowCount(0);
            	loadDanhSachChamCong();
            	//reset lại bảng chấm công nhân viên
        		modelBCCNV.setRowCount(0);
        		loadBangChamCongNV();
        		//chkHomNay.setSelected(false);
        	}	
    	}
    	JOptionPane.showMessageDialog(null, "Chấm công thành công");
    }//GEN-LAST:event_btnChamCongTatCaActionPerformed

    private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt, String maNhanVienChamCong) {//GEN-FIRST:event_btnChamCongActionPerformed
        // TODO add your handling code here:
    	if(tblDanhSachChamCong.getSelectedRow() == -1) {
    		JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên để chấm cống");
    		return;
    	}
    	BangChamCongNhanVien ccnvMoi = layDuLieuDongDaChonDeChamCong(maNhanVienChamCong);
    	if(bangCCNV_Dao.themChamCongNhanVien(ccnvMoi)==true) {
    		//reset lại bảng danh sách chấm
    		modelDSCC.setRowCount(0);
        	loadDanhSachChamCong();
        	//reset lại bảng chấm công nhân viên
        	modelBCCNV.setRowCount(0);
    		loadBangChamCongNV();
    		//chkHomNay.setSelected(false);
    		JOptionPane.showMessageDialog(null, "Chấm công thành công");
    	}else {
    		JOptionPane.showMessageDialog(null, "Chấm công thất bại");
    	}
    }//GEN-LAST:event_btnChamCongActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    	BangChamCongNhanVien ccnvMoi = layDuLieuCapNhat();
		if(ccnvMoi != null ) {
			int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật không ?");
			if (choice == JOptionPane.YES_OPTION) {
				if (bangCCNV_Dao.capNhatChamCongNV(ccnvMoi)) {
					modelBCCNV.setRowCount(0);
		    		loadBangChamCongNV();
					JOptionPane.showMessageDialog(null, "Cập nhật hoàn tất!");
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
				}
			}
		}
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cmbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChamCong;
    private javax.swing.JButton btnChamCongTatCa;
    private javax.swing.JButton btnLayDanhSach;
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbLocPhongBan;
    private javax.swing.JComboBox<String> cmbPhongBan;
    private javax.swing.JComboBox<String> cmbSoGioTangCa;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private com.toedter.calendar.JDateChooser dchNgayCham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBangChamCong;
    private javax.swing.JTable tblDanhSachChamCong;
    private javax.swing.JLabel txtHoVaTen;
    private javax.swing.JLabel txtMaNhanVien;
    private javax.swing.JTextField txtTimKiemBangChamCong;
    private javax.swing.JTextField txtTimKiemDanhSachChamCong;
    // End of variables declaration//GEN-END:variables
}
