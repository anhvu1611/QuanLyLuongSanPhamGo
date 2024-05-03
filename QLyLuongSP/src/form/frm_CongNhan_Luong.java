/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.BangLuongCongNhan_Dao;
import GUI.GUI_APP;

/**
 *
 * @author Vu
 */
public class frm_CongNhan_Luong extends javax.swing.JPanel {
	private BangLuongCongNhan_Dao bangLuongCongNhan_dao;
	private DefaultTableModel modelBLCN;
	public GUI_APP app;
	/**
	 * Creates new form LuongCongNhan
	 */
	public frm_CongNhan_Luong() {
		KetNoiCSDL();
		initComponents();
	}
	// khởi tạo kết nối đến CSDL
	public void KetNoiCSDL() {
		try {
			ConnectDB.ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bangLuongCongNhan_dao = new BangLuongCongNhan_Dao();
	}
	//load bảng lương
	private void loadBangLuong() {
		// Tạo một đối tượng NumberFormat với Locale là Vietnamese
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		//  		String nam = cmbNam.getSelectedItem().toString();
		String thang = cmbThang.getSelectedItem().toString();
		//  		ArrayList<BangLuongCongNhan> list = bangLuongCongNhan_dao.layBangLuongTheoThangNam(Integer.parseInt(nam), Integer.parseInt(thang));
		//  		if(list.isEmpty()) {
		//  			JOptionPane.showMessageDialog(null, "Tháng/năm đã chọn không có dữ liệu để tính lương");
		//  		}else {
		//  			for(BangLuongCongNhan luong : list) {
		//  	  			modelBLCN.addRow(new Object[] {list.indexOf(luong)+1, 
		//  	  					luong.getMaBangLuongCongNhan(),
		//  	  					luong.getCongNhan().getMaNhanSu(),
		//  	  					luong.getCongNhan().getHo() + " " + luong.getCongNhan().getTen(),
		//  	  					luong.getCongNhan().getChuyenMon().getTenChuyenMon(),
		//  	  					luong.getSoLuongSanPham(),
		//  	  					currencyFormat.format((Double)luong.getLuongTheoSanPham()),
		//  	  					currencyFormat.format(Double.parseDouble((luong.getCongNhan().getPhuCap().toString()))),
		//
		////  	  					luong.getCongNhan().getPhuCap(),
		//  	  					currencyFormat.format((Double)luong.getTongLuong()),
		//  	  					luong.getNgayLap()
		//  	  					
		//  	  			});
		//  	  		}
		//  			btnXemChiTiet.setEnabled(true);
		//  			btnInBangLuong.setEnabled(true);
		//  		}
		ArrayList<Vector<String>> list = bangLuongCongNhan_dao.layBangLuongTheoMaCongNhanVaThang(Integer.parseInt(thang));
		if(list.size()<1) {
			JOptionPane.showMessageDialog(null, "Tháng/năm đã chọn không có dữ liệu để tính lương");
		}else {
			int i=0;
			for(Vector<String> luong : list) {
				luong.add(0, ++i + "");
				modelBLCN.addRow(luong);
			}
			btnXemChiTiet.setEnabled(true);
			btnInBangLuong.setEnabled(true);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		// Lấy tháng hiện tại từ Calendar
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH);
		// Lấy năm hiện tại từ Calendar
		int currentYear = calendar.get(Calendar.YEAR);
		// Tạo một mảng chứa số tháng từ 1 đến 12
		String[] monthNames = new String[12];
		for (int i = 0; i < 12; i++) {
			monthNames[i] = String.valueOf(i + 1);
		}
		jScrollPane1 = new javax.swing.JScrollPane();
		tblBangLuongCongNhan = new javax.swing.JTable();
		btnInBangLuong = new javax.swing.JButton();
		btnXemChiTiet = new javax.swing.JButton();
		btnTinhLuong = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		cmbThang = new javax.swing.JComboBox<>();
		jLabel19 = new javax.swing.JLabel();
		cmbNam = new javax.swing.JComboBox<>();
		jPanel1 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		btnHDSD = new javax.swing.JButton();

		setBackground(new java.awt.Color(255, 255, 255));
		setPreferredSize(new java.awt.Dimension(1295, 634));

		jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng lương công nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

		tblBangLuongCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		tblBangLuongCongNhan.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null}
				},
				new String [] {
						"Mã công nhân", "Tên công nhân", "Chuyên môn", "Hệ số lương chuyên môn", "Tên sản phẩm", "Tên công đoạn", "Lương theo công đoạn", "Số lượng hoàn thành", "Phụ cấp", "Tổng lương (VNĐ)"
				}
				));
		String[] colsBLCN = {"STT","Mã bảng lương","Mã công nhân", "Tên công nhân","Chuyên môn", "Số lượng hoàn thành" ,"Lương theo sản phẩm", "Phụ cấp", "Tổng lương (VNĐ)", "Ngày lập"};
		modelBLCN = new DefaultTableModel(colsBLCN, 0);
		tblBangLuongCongNhan = new JTable(modelBLCN);
		jScrollPane1.setViewportView(tblBangLuongCongNhan);

		btnInBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnInBangLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-print-24.png"))); // NOI18N
		btnInBangLuong.setText("In bảng lương");
		btnInBangLuong.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.MatteBorder(null)));
		btnInBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnInBangLuong.setText("In bảng lương");
		btnInBangLuong.setEnabled(false);
		btnInBangLuong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog_InBangLuongCongNhan inBangLuongCN = new Dialog_InBangLuongCongNhan(app, true, tblBangLuongCongNhan);
				inBangLuongCN.setLocationRelativeTo(null);
				inBangLuongCN.setTitle("In bảng lương công nhân");
				inBangLuongCN.setVisible(true);
			}
		});

		btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnXemChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-view-details-24.png"))); // NOI18N
		btnXemChiTiet.setText("Xem chi tiết");
		btnXemChiTiet.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.MatteBorder(null)));

		btnTinhLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnTinhLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-payroll-24.png"))); // NOI18N
		btnTinhLuong.setText("Tính lương");
		btnTinhLuong.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.MatteBorder(null)));
		//Xem Chi Tiết
		btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnXemChiTiet.setText("Xem chi tiết");
		btnXemChiTiet.setEnabled(false);

		btnXemChiTiet.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblBangLuongCongNhan.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần xem chi tiết");
				}else {
					int selectedRow = tblBangLuongCongNhan.getSelectedRow();
					String maCongNhan = (String) tblBangLuongCongNhan.getValueAt(selectedRow, 2);
					//					SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
					Dialog_ChiTietLuongCongNhan chiTietLuongCN = null;
					chiTietLuongCN = new Dialog_ChiTietLuongCongNhan(app, maCongNhan, new Date());
					chiTietLuongCN.setLocationRelativeTo(null);
					chiTietLuongCN.setVisible(true);
				}

			}
		});



		btnTinhLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		btnTinhLuong.setText("Tính lương");
		btnTinhLuong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modelBLCN.setRowCount(0);
				loadBangLuong();

			}
		});

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hệ số lương theo chuyên môn"));

		jLabel10.setText("Cắt gỗ:");

		jLabel11.setText("1.02");

		jLabel12.setText("Chế tác:");

		jLabel13.setText("1.04");

		jLabel14.setText("Lắp ráp:");

		jLabel15.setText("Bề mặt:");

		jLabel16.setText("1.0");

		jLabel17.setText("1.01");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap(57, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel12)
								.addComponent(jLabel10)
								.addComponent(jLabel14)
								.addComponent(jLabel15))
						.addGap(37, 37, 37)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel17)
								.addComponent(jLabel16)
								.addComponent(jLabel13)
								.addComponent(jLabel11))
						.addGap(34, 34, 34))
				);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel10)
								.addComponent(jLabel11))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel12)
								.addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel14)
								.addComponent(jLabel16))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel15)
								.addComponent(jLabel17))
						.addContainerGap(12, Short.MAX_VALUE))
				);

		jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		jLabel18.setText("Tháng:");

		cmbThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		cmbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

		jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		jLabel19.setText("Năm:");

		cmbNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026" }));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));

		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
		jLabel9.setForeground(new java.awt.Color(255, 153, 0));
		jLabel9.setText("Quản lý lương công nhân");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(86, 86, 86)
						.addComponent(jLabel9)
						.addContainerGap(107, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(24, 24, 24)
						.addComponent(jLabel9)
						.addContainerGap(21, Short.MAX_VALUE))
				);
		cmbThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		cmbThang.setModel(new javax.swing.DefaultComboBoxModel<>(monthNames));
		cmbThang.setSelectedIndex(currentMonth);

		// Tạo một mảng chứa số năm từ (currentYear - 10) đến (currentYear + 10)
		String[] yearNames = new String[16];
		for (int i = 0; i < 16; i++) {
			yearNames[i] = String.valueOf(currentYear - 5 + i);
		}
		cmbNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(yearNames));
		cmbNam.setSelectedItem(String.valueOf(currentYear));



		btnHDSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-question-24.png"))); // NOI18N
		btnHDSD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnHuongDanActionPerformed(e);

				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			private void btnHuongDanActionPerformed(ActionEvent evt) throws Exception {

				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/huongDan/huongDanLuongCongNhan.png"));
				Image image = imageIcon.getImage().getScaledInstance(1180, 680, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(image);
				JPanel imagePanel = new JPanel();
				imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

				JLabel imageLabel1 = new JLabel(imageIcon);
				imagePanel.add(imageLabel1);

				JScrollPane scrollPane = new JScrollPane(imagePanel);
				scrollPane.setPreferredSize(new Dimension(1200, 700));

				scrollPane.getVerticalScrollBar().setUnitIncrement(20);
				scrollPane.getVerticalScrollBar().setBlockIncrement(100);

				JOptionPane.showMessageDialog(null, scrollPane, "Hướng dẫn thống kê sản phẩm", JOptionPane.PLAIN_MESSAGE);
			}
		});


		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(195, 195, 195)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnTinhLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(82, 82, 82)
										.addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(92, 92, 92)
										.addComponent(btnInBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel18)
										.addGap(18, 18, 18)
										.addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(84, 84, 84)
										.addComponent(jLabel19)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(278, 278, 278)))
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(193, 193, 193))
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1)
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(249, 249, 249)
						.addComponent(btnHDSD)
						.addGap(46, 46, 46))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(14, 14, 14)
										.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGap(30, 30, 30)
										.addComponent(btnHDSD)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(12, 12, 12)
										.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addGap(29, 29, 29)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel19)
												.addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel18))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnTinhLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnInBangLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(28, 28, 28)))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
	}// </editor-fold>//GEN-END:initComponents

	private void btnHDSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDSDActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_btnHDSDActionPerformed


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnHDSD;
	private javax.swing.JButton btnInBangLuong;
	private javax.swing.JButton btnTinhLuong;
	private javax.swing.JButton btnXemChiTiet;
	private javax.swing.JComboBox<String> cmbNam;
	private javax.swing.JComboBox<String> cmbThang;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblBangLuongCongNhan;
	// End of variables declaration//GEN-END:variables
}

//frm_CongNhan_TinhLuong