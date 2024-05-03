/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.BangLuongNhanVien_Dao;

/**
 *
 * @author ACER
 */
public class Frm_ThongKe_NhanVien extends javax.swing.JPanel {


	private static final long serialVersionUID = 1L;
	private DefaultTableModel modalBangDuLieu;
	private ActionListener suKienTuThang;
	/**
     * Creates new form Frm_ThongKe_NhanVien
     */
    public Frm_ThongKe_NhanVien() {
        initComponents();
    }
    
    public static String[] getRecentYears(int count) {
        int currentYear = Year.now().getValue();
        String[] recentYears = new String[count];
        for (int i = 0; i < count; i++) {
            recentYears[i] = currentYear - i + "";
        }

        return recentYears;
    }
    
    private void duLieuCuaBieuDoCot() {
    	int tuThang = Integer.parseInt((String) cmbTuThang.getSelectedItem());
    	int tuNam = Integer.parseInt((String) cmbTuNam.getSelectedItem());
    	int toiThang = Integer.parseInt((String) cmbToiThanh.getSelectedItem());
    	int toiNam = Integer.parseInt((String) cmbToiNam.getSelectedItem());
    	int tieuChiMot = cmbTCMot.getSelectedIndex();
    	int tieuChiHai = cmbTCHai.getSelectedIndex();
    	int loaiThongKe = cmbLoai.getSelectedIndex();
    	if(loaiThongKe == 0) {
    		if(tieuChiMot == 0) {
        		if(tieuChiHai == 0){
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "desc", "LuongThucNhan");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}else if(tieuChiHai == 1) {
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "asc", "LuongThucNhan");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}
        	}else if(tieuChiMot == 1) {
        		if(tieuChiHai == 0){
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "desc", "NgayLam");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}else if(tieuChiHai == 1) {
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "asc", "NgayLam");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}
        	}
    	}else if(loaiThongKe == 1) {
    		if(tuThang==2) {
    			tuThang = 3;
    		}else if(tuThang == 3) {
    			tuThang = 6;
    		}else if(tuThang == 4) {
    			tuThang = 9;
    		}else if(tuThang == 1) {
    			tuThang = 1;
    		}
    		
    		if(toiThang==2) {
    			toiThang = 6;
    		}else if(toiThang == 3) {
    			toiThang = 9;
    		}else if(toiThang == 4) {
    			toiThang = 12;
    		}else if(toiThang == 1) {
    			toiThang = 3;
    		}
    		if(tieuChiMot == 0) {
        		if(tieuChiHai == 0){
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "desc", "LuongThucNhan");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}else if(tieuChiHai == 1) {
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "asc", "LuongThucNhan");
                	pnBieuDoCot.xuatBieuDo(ds, 0);
                	dayDuLieuVaoBang(ds);
            	}
        	}else if(tieuChiMot == 1) {
        		if(tieuChiHai == 0){
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "desc", "NgayLam");
                	pnBieuDoCot.xuatBieuDo(ds, 1);
                	dayDuLieuVaoBang(ds);
            	}else if(tieuChiHai == 1) {
            		ArrayList<Vector<String>> ds=  BangLuongNhanVien_Dao.layDanhSachThongKeTopNNhanVien(tuThang, tuNam, toiThang, toiNam, 5, "asc", "NgayLam");
                	pnBieuDoCot.xuatBieuDo(ds, 1);
                	dayDuLieuVaoBang(ds);
            	}
        	}
    	}
    	
    }

    private void dayDuLieuVaoBang(ArrayList<Vector<String>> ds) {
		modalBangDuLieu.setRowCount(0);
		for (Vector<String> vector : ds) {
			modalBangDuLieu.addRow(new String[] {vector.get(6), vector.get(1), vector.get(0)});
		}
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblTieuDe = new javax.swing.JLabel();
        pnThoiGian = new javax.swing.JPanel();
        lblTuThang = new javax.swing.JLabel();
        cmbTuThang = new javax.swing.JComboBox<>("1;2;3;4;5;6;7;8;9;10;11;12".split(";"));
        lblToiThanh = new javax.swing.JLabel();
        cmbToiThanh = new javax.swing.JComboBox<>("1;2;3;4;5;6;7;8;9;10;11;12".split(";"));
        lblTuNam = new javax.swing.JLabel();
        cmbTuNam = new javax.swing.JComboBox<>(getRecentYears(4));
        lblToiNam = new javax.swing.JLabel();
        cmbToiNam = new javax.swing.JComboBox<>(getRecentYears(4));
        jPanel1 = new javax.swing.JPanel();
        lblChonLoaiThongKe = new javax.swing.JLabel();
        cmbLoai = new javax.swing.JComboBox<>();
        lblTieuChi1 = new javax.swing.JLabel();
        cmbTCMot = new javax.swing.JComboBox<>();
        lblTieuChi2 = new javax.swing.JLabel();
        cmbTCHai = new javax.swing.JComboBox<>();
        btnThongKe = new javax.swing.JButton();
        btnXuatDanhSach = new javax.swing.JButton();
        pnBieuDoCot = new form.Frm_BieuDoCot();
        pnBangDuLieu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrBangDuLieu = new javax.swing.JScrollPane();
        tblBangDuLieu = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setDoubleBuffered(false);
        setPreferredSize(new java.awt.Dimension(1250, 680));

        lblTieuDe.setBackground(new java.awt.Color(255, 255, 255));
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(255, 153, 0));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Thống Kê Nhân Viên");

        pnThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        pnThoiGian.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thời Gian", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblTuThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTuThang.setText("Tháng");

        cmbTuThang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbTuThang.setMaximumRowCount(12);
        suKienTuThang =  new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTuThangActionPerformed(evt);
            }
        };
        cmbTuThang.addActionListener(suKienTuThang);

        lblToiThanh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblToiThanh.setText("Đến tháng");

        cmbToiThanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbToiThanh.setMaximumRowCount(12);
        cmbToiThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbToiThanhActionPerformed(evt);
            }
        });

        lblTuNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTuNam.setText("Năm");

        cmbTuNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbTuNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTuNamActionPerformed(evt);
            }
        });

        lblToiNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblToiNam.setText("Năm");

        cmbToiNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbToiNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbToiNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnThoiGianLayout = new javax.swing.GroupLayout(pnThoiGian);
        pnThoiGian.setLayout(pnThoiGianLayout);
        pnThoiGianLayout.setHorizontalGroup(
            pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThoiGianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTuThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblToiThanh, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThoiGianLayout.createSequentialGroup()
                        .addComponent(cmbToiThanh, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnThoiGianLayout.createSequentialGroup()
                        .addComponent(cmbTuThang, 0, 87, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTuNam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblToiNam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbToiNam, 0, 84, Short.MAX_VALUE)
                    .addComponent(cmbTuNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        pnThoiGianLayout.setVerticalGroup(
            pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoiGianLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTuThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbTuThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTuNam)
                        .addComponent(cmbTuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblToiThanh)
                    .addGroup(pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbToiNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblToiNam)
                        .addComponent(cmbToiThanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tiêu Chí Thống Kê", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblChonLoaiThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChonLoaiThongKe.setText("Chọn loại thống kê");
        lblChonLoaiThongKe.setPreferredSize(new java.awt.Dimension(37, 19));

        cmbLoai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Tháng", "Theo Quý" }));
        cmbLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLoaiActionPerformed(evt);
            }
        });

        lblTieuChi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTieuChi1.setText("Tiêu Chí 1");
        lblTieuChi1.setPreferredSize(new java.awt.Dimension(37, 19));

        cmbTCMot.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbTCMot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lương", "Ngày Công" }));
        cmbTCMot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTCMotActionPerformed(evt);
            }
        });

        lblTieuChi2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTieuChi2.setText("Tiêu Chí 2");
        lblTieuChi2.setPreferredSize(new java.awt.Dimension(37, 19));

        cmbTCHai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbTCHai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Top 5 Nhân Viên Cao Nhất", "Top 5 Nhân Viên Thấp Nhất" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTieuChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTCMot, 0, 176, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblChonLoaiThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTieuChi2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTCHai, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTieuChi2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTCHai)
                    .addComponent(cmbTCMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTieuChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChonLoaiThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/analysis.png"))); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnXuatDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/export (1).png"))); // NOI18N
        btnXuatDanhSach.setText("Xuất Danh Sách Thống Kê");
        btnXuatDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachActionPerformed(evt);
            }
        });

        pnBieuDoCot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        pnBangDuLieu.setBackground(new java.awt.Color(255, 255, 255));
        pnBangDuLieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        pnBangDuLieu.setAutoscrolls(true);
        pnBangDuLieu.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bảng Dữ Liệu");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        pnBangDuLieu.add(jLabel1, java.awt.BorderLayout.NORTH);

        tblBangDuLieu.setAutoCreateRowSorter(true);
        modalBangDuLieu = new DefaultTableModel(new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Mã Nhân Viên", "Số Ngày Công", "Lương Nhận"
        });
        tblBangDuLieu.setModel(modalBangDuLieu);
        tblBangDuLieu.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(modalBangDuLieu);
        tblBangDuLieu.setRowSorter(rowSorter);

        // Thiết lập kích thước cho dòng ở vị trí row (ví dụ: dòng đầu tiên có index là 0)
        int rowHeight = 30; // Đặt kích thước mong muốn
        tblBangDuLieu.setRowHeight(rowHeight);
        tblBangDuLieu.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrBangDuLieu.setViewportView(tblBangDuLieu);

        pnBangDuLieu.add(scrBangDuLieu, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(486, 486, 486)
                                .addComponent(btnThongKe)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnBieuDoCot, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXuatDanhSach)
                            .addComponent(pnBangDuLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(pnThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnBieuDoCot, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(pnBangDuLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cmbToiNam.setEnabled(false);
    }// </editor-fold>                        


	private void cmbTuThangActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int tuThang = Integer.parseInt((String) cmbTuThang.getSelectedItem());
        int tuNam = Integer.parseInt((String) cmbTuNam.getSelectedItem());
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        int thangHienTai = currentMonth.getValue();
        int namHienTai = currentDate.getYear();
        int toiNam = Integer.parseInt((String)cmbToiNam.getSelectedItem());
        if(tuNam == toiNam) {
        	layThangConLai(tuThang);
        }
    }                                          

    private void layThangConLai(int tuThang) {
		duLieuThang();
		for (int i = cmbToiThanh.getItemCount() - 1; i >= 0; i--) {
    		int currentToiNam = Integer.parseInt((String) cmbToiThanh.getItemAt(i));
            if (currentToiNam < tuThang) {
                cmbToiThanh.removeItemAt(i);
            }
        }
	}

    private void duLieuThang() {
		cmbToiThanh.removeAllItems();
		if(lblTuThang.getText().equalsIgnoreCase("Quý")) {
			for(int i=1;i<=4;i++) {
				cmbToiThanh.addItem(i+"");
			}
		}else {
			for(int i=1;i<=12;i++) {
				cmbToiThanh.addItem(i+"");
			}
		}
		
	}

	private void cmbToiThanhActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void cmbTuNamActionPerformed(java.awt.event.ActionEvent evt) {
    	duLieuThang();
    	duLieuNam();
    	LocalDate currentDate = LocalDate.now();
    	int nam = currentDate.getYear();
    	int tuNam = Integer.parseInt((String) cmbTuNam.getSelectedItem());
    	if(tuNam == nam) {
    		cmbToiNam.setSelectedItem(nam+"");
    		cmbToiNam.setEnabled(false);
    	}else {
    		layRaNamPhuHop(tuNam);
    		cmbToiNam.setEnabled(true);
    	}
    }                                        

    private void layRaNamPhuHop(int tuNam) {
    	duLieuNam();
    	for (int i = cmbToiNam.getItemCount() - 1; i >= 0; i--) {
    		int currentToiNam = Integer.parseInt((String) cmbToiNam.getItemAt(i));
            if (currentToiNam < tuNam) {
                cmbToiNam.removeItemAt(i);
            }
        }
	}

	private void duLieuNam() {
		cmbToiNam.removeAllItems();
		String[] nam = getRecentYears(4);
		for(int i=0;i<nam.length;i++) {
			cmbToiNam.addItem(nam[i]);
		}
	}

	private void cmbToiNamActionPerformed(java.awt.event.ActionEvent evt) {                                          
	    String selectedToiNam = (String) cmbToiNam.getSelectedItem();
	    
	    if (selectedToiNam != null) {
	        int toiNam = Integer.parseInt(selectedToiNam);

	        if (toiNam == Integer.parseInt((String) cmbTuNam.getSelectedItem())) {
	            int tuThang = Integer.parseInt((String) cmbTuThang.getSelectedItem());
	            int toiThang = Integer.parseInt((String) cmbToiThanh.getSelectedItem());

	            if (toiThang < tuThang) {
	                cmbToiThanh.setSelectedItem(String.valueOf(tuThang));
	            }
	        }
	    }
	}


    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	duLieuCuaBieuDoCot();
    }                                          

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void cmbTCMotActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
    }                                        

    private void cmbLoaiActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    	int loaiTieuChi = cmbLoai.getSelectedIndex();
    	cmbTuThang.removeActionListener(suKienTuThang);
    	if (loaiTieuChi == 0) {
    		
    	    lblTuThang.setText("Tháng");
    	    lblToiThanh.setText("Đến tháng");
    	    cmbTuThang.removeAllItems();
    	    cmbToiThanh.removeAllItems();
    	    for (int i = 1; i <= 12; i++) {
    	        cmbTuThang.addItem(String.valueOf(i));
    	        cmbToiThanh.addItem(String.valueOf(i));
    	    }
    	    
    	} else if (loaiTieuChi == 1) {
    	    lblTuThang.setText("Quý");
    	    lblToiThanh.setText("Đến Quý");
    	    cmbTuThang.removeAllItems();
    	    cmbToiThanh.removeAllItems();
    	    for (int i = 1; i <= 4; i++) {
    	        cmbTuThang.addItem(String.valueOf(i));
    	        cmbToiThanh.addItem(String.valueOf(i));
    	    }
    	}
    	cmbTuThang.addActionListener(suKienTuThang);
    }                                       


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnXuatDanhSach;
    private javax.swing.JComboBox<String> cmbLoai;
    private javax.swing.JComboBox<String> cmbTCHai;
    private javax.swing.JComboBox<String> cmbTCMot;
    private javax.swing.JComboBox<String> cmbToiNam;
    private javax.swing.JComboBox<String> cmbToiThanh;
    private javax.swing.JComboBox<String> cmbTuNam;
    private javax.swing.JComboBox<String> cmbTuThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChonLoaiThongKe;
    private javax.swing.JLabel lblTieuChi1;
    private javax.swing.JLabel lblTieuChi2;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblToiNam;
    private javax.swing.JLabel lblToiThanh;
    private javax.swing.JLabel lblTuNam;
    private javax.swing.JLabel lblTuThang;
    private javax.swing.JPanel pnBangDuLieu;
    private form.Frm_BieuDoCot pnBieuDoCot;
    private javax.swing.JPanel pnThoiGian;
    private javax.swing.JScrollPane scrBangDuLieu;
    private javax.swing.JTable tblBangDuLieu;
    // End of variables declaration                   
}
