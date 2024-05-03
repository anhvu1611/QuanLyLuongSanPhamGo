/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import DAO.BangChamCongCongNhan_Dao;
import DAO.BangPhanCongCongDoan_Dao;
import DAO.CongDoan_Dao;
import DAO.NhanVien_Dao;
import DAO.SanPham_Dao;
import Enum.CaLamCongNhan;
import Enum.CaLamNhanVien;
import Enum.TrangThai;
import entity.BangChamCongCongNhan;
import entity.BangChamCongNhanVien;
import entity.BangPhanCongCongDoan;
import entity.NhanVien;

/**
 *
 * @author Vu
 */
public class Dialog_DanhSachPhanCong extends javax.swing.JDialog {
	private DefaultTableModel modelDSPC;
	private BangPhanCongCongDoan_Dao bangPCCD_dao;
	private BangChamCongCongNhan_Dao bangCCCN_dao;
	private NhanVien_Dao nhanVien_dao;
	private CongDoan_Dao congDoan_Dao;
	private SanPham_Dao sanPham_dao;
	private static final String[] HEADERS = { "STT","Mã phân công đoạn", "Mã công nhân", "Tên công nhân", "Có mặt",
			"Vắng mặt","Ca làm", "Số lượng phân công","Số lượng hoàn thành"};

	// Lấy ngày hôm nay
	LocalDate ngayHomNay = LocalDate.now();

	// Định dạng ngày theo yyyy-MM-dd
	DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Chuyển đổi và in ra màn hình
	String homNay = ngayHomNay.format(dinhDang);
	/**
	 * Creates new form DanhSachPhanCong
	 * @param nv 
	 */
	public Dialog_DanhSachPhanCong(java.awt.Frame parent, boolean modal, NhanVien nv) {
		super(parent, modal);
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
		congDoan_Dao = new CongDoan_Dao();
		bangPCCD_dao = new BangPhanCongCongDoan_Dao();
		bangCCCN_dao = new BangChamCongCongNhan_Dao();
		nhanVien_dao = new NhanVien_Dao();
		//System.out.println("Ket noi thanh cong");
	}
	// đọc dữ liệu vào table bảng phân công
	private void loadDanhSachPhanCong() {
		ArrayList<BangPhanCongCongDoan> listPCCD = (ArrayList<BangPhanCongCongDoan>) bangPCCD_dao.layDanhSachPhanCongCN();
		for (BangPhanCongCongDoan bpc : listPCCD) {
			System.out.println(bpc.getSoLuongConLai());
			modelDSPC.addRow(new Object[] {listPCCD.indexOf(bpc)+1,
					bpc.getCongDoan().getMaCongDoan(),
					bpc.getCongNhanDamNhan().getMaNhanSu(), 
					bpc.getCongNhanDamNhan().getTen(), 
					true, 
					false,
					cmbCaLam.getSelectedItem(),
					bpc.getSoLuongConLai(), 
					0
			});	
		}
	}

	//Kiểm tra số lượng nhập vào
	private boolean kiemTraThongTinChamCong(String soLuongLam) {
		int soLuongPhanCong = (int) modelDSPC.getValueAt(tblDanhSachPhanCong.getSelectedRow(), 7);
		if(soLuongLam.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,"Chưa nhập số lượng");
			return false;
		}else if(Integer.parseInt(soLuongLam) > soLuongPhanCong) {
			JOptionPane.showMessageDialog(null, "Số lượng hoàn thành phải nhỏ hơn số lượng phân công");
			return false;
		}else if(!soLuongLam.matches("[\\d+\\s]*")){
			JOptionPane.showMessageDialog(null, "Chỉ được nhập số dương");
			return false;
		}
		return true;
	}
	//Phát sinh mã chấm công công nhân mới 
	private String maChamCongMoi(String maChamCong) {
		int number = Integer.parseInt(maChamCong.substring(4));
		number++;
		String maChamCongMoi = String.format("CCCN%04d", number);
		// System.out.println(maChamCongMoi);
		return maChamCongMoi;
	}
	//Lấy dữ liệu theo dòng đã chọn để chấm công
	private BangChamCongCongNhan layDuLieuDongDaChonDeChamCong() {
		if(tblDanhSachPhanCong.getSelectedRow() != -1) {

			int row = tblDanhSachPhanCong.getSelectedRow();
			String maChamCong = maChamCongMoi(bangCCCN_dao.layMaChamCongCaoNhat());
			Date ngayCham = new Date();

			Boolean trangThai = null;
			if((boolean) tblDanhSachPhanCong.getValueAt(row, 4)) {
				trangThai = true;
			}else {
				trangThai = false;
			}
			String soLuongHoanThanh = tblDanhSachPhanCong.getValueAt(row, 8).toString() ;

			try {
				int parsedNumber = Integer.parseInt(soLuongHoanThanh);
				//Sửa thành mã người đăng nhập
				String maNguoiCham = (String) tblDanhSachPhanCong.getValueAt(row, 2);
				NhanVien nguoiCham = nhanVien_dao.timNhanVienTheoMa(maNguoiCham);

				CaLamCongNhan caLam = null;
				String caLamCongNhan = (String) tblDanhSachPhanCong.getValueAt(row, 6);
				if(caLamCongNhan.equalsIgnoreCase("Ca Một")) {
					caLam = CaLamCongNhan.CAMOT;
				}else if(caLamCongNhan.equalsIgnoreCase("Ca Hai")) {
					caLam = CaLamCongNhan.CAHAI;
				}else {
					caLam = CaLamCongNhan.CABA;
				}
				String maCongNhan = (String) tblDanhSachPhanCong.getValueAt(row, 2);
				//BangPhanCongCongDoan pccd = bangPCCD_dao.timPhanCongCongDoanTheoMaCongNhan(maCongNhan);
				BangPhanCongCongDoan pccd = bangPCCD_dao.timTheoMaCongNhanVaMaCongDoan(maCongNhan, tblDanhSachPhanCong.getValueAt(row, 1).toString());
				BangChamCongCongNhan cccnMoi = new BangChamCongCongNhan(maChamCong, ngayCham, trangThai, parsedNumber, nguoiCham, caLam, pccd );
				return cccnMoi;  	
			}catch (NumberFormatException e) {
				System.err.println("Invalid number format");
			}

		}

		return null;
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents(NhanVien nv) {

		jScrollPane1 = new javax.swing.JScrollPane();
		tblDanhSachPhanCong = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		cmbCaLam = new javax.swing.JComboBox<>();
		dateChNgayCham = new com.toedter.calendar.JDateChooser();
		btnChamCong = new javax.swing.JButton();
		btnTroVe = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(1200, 700));
		setResizable(false);

		dateChNgayCham.setDateFormatString("yyyy-MM-dd");
		dateChNgayCham.setEnabled(false);
		dateChNgayCham.setDate(new Date());


		tblDanhSachPhanCong.setModel(new javax.swing.table.DefaultTableModel(
				new Object [][] {

				},
				HEADERS

				));


		modelDSPC = new DefaultTableModel(HEADERS, 0);       

		modelDSPC = new DefaultTableModel(HEADERS, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 4 || columnIndex == 5) { // Cột "Có mặt" và "Vắng mặt (Có phép)"
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};


		tblDanhSachPhanCong = new JTable(modelDSPC) {
			@Override
			public TableCellEditor getCellEditor(int row, int column) {
				if (column == 4 || column == 5) { // Cột "Có mặt" và "Vắng mặt (Có phép)"
					return new DefaultCellEditor(new JCheckBox());
				}
				return super.getCellEditor(row, column);
			}
		};

		tblDanhSachPhanCong = new JTable(modelDSPC);
		TableColumn column = tblDanhSachPhanCong.getColumnModel().getColumn(4);
		column.setCellEditor((TableCellEditor) new DefaultCellEditor(new JCheckBox()));
		column.setCellEditor(new DefaultCellEditor(new JCheckBox()));


		tblDanhSachPhanCong = new JTable(modelDSPC) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column==0||column==1||column==2 || column==3||column==6||column==7 || column ==8) {
					return false; // Không cho phép chỉnh sửa cột thứ 1,2,3,4,7,8
				}
				return true; // Cho phép chỉnh sửa các cột khác
			}
		};
		// Định nghĩa Renderer tùy chỉnh cho cột thứ 8 (index 8)
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 8) { // Chỉnh màu cho cột thứ 
					cell.setForeground(Color.BLUE); // Màu chữ 
					//cell.setBackground(Color.LIGHT_GRAY); // Màu nền 
				}
				return cell;
			}
		};
		tblDanhSachPhanCong.getColumnModel().getColumn(8).setCellRenderer(renderer);

		tblDanhSachPhanCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblDanhSachPhanCong.rowAtPoint(e.getPoint());
				int column = tblDanhSachPhanCong.columnAtPoint(e.getPoint());
				// Xử lý sự kiện chuột ở đây
				if (column == 5) {
					modelDSPC.setValueAt(false, row, 4);
					modelDSPC.setValueAt(0, row, 8);
				}else if(column == 4) {
					if (column == 4) {
						modelDSPC.setValueAt(false, row, 5);
					}
				}

			}
		});

		tblDanhSachPhanCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tblDanhSachPhanCong.columnAtPoint(e.getPoint());
				int row = tblDanhSachPhanCong.rowAtPoint(e.getPoint());
				if(tblDanhSachPhanCong.getModel().getRowCount()!=-1) {
					int selectedRow = tblDanhSachPhanCong.getSelectedRow();
					if((boolean) modelDSPC.getValueAt(selectedRow, 4) == true && (boolean) modelDSPC.getValueAt(selectedRow, 5)==false) {
						while (true) {
							String soLuongLam = JOptionPane.showInputDialog("Nhập số lượng:");
							if(soLuongLam == null) {
								break; //nhấn cancel hoặc đóng hộp thoại
							}
							if(kiemTraThongTinChamCong(soLuongLam)) {
								modelDSPC.setValueAt(soLuongLam, selectedRow, 8);
								break;
							}

						}
					}else if((boolean) modelDSPC.getValueAt(selectedRow, 4) == false){
						modelDSPC.setValueAt(0, selectedRow, 8);
					}
				}



			}
		});





		jScrollPane1.setViewportView(tblDanhSachPhanCong);

		jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		jLabel1.setText("Ngày chấm công:");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		jLabel2.setText("Ca làm:");

		cmbCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca Một", "Ca Hai", "Ca Ba" }));
		//Chọn ca làm
		cmbCaLam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int row = 0; row < modelDSPC.getRowCount(); row++) {
					modelDSPC.setValueAt(cmbCaLam.getSelectedItem(), row, 6);
				}
			}
		});
		btnChamCong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnChamCong.setText("Chấm công");
		btnChamCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-mark-22.png")));
		btnChamCong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChamCongActionPerformed(evt, nv);
			}
		});

		btnTroVe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		btnTroVe.setText("Trở về");
		btnTroVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-back-30.png")));
		btnTroVe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTroVeActionPerformed(evt);
			}
		});
		// Tạo TableRowSorter và kết nối với JTable
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelDSPC);
		tblDanhSachPhanCong.setRowSorter(sorter);

		// Sắp xếp theo cột có chỉ số 0 (First Name) khi dữ liệu thay đổi
		modelDSPC.addTableModelListener(e -> {
			sorter.sort();
		});

		loadDanhSachPhanCong();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1)
				.addGroup(layout.createSequentialGroup()
						.addGap(96, 96, 96)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel1)
								.addComponent(jLabel2))
						.addGap(36, 36, 36)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(dateChNgayCham, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
						.addComponent(btnChamCong)
						.addGap(50, 50, 50)
						.addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(116, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addComponent(dateChNgayCham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(42, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addGap(41, 41, 41)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnChamCong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE))))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents


	private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt, NhanVien nv) {//GEN-FIRST:event_btnTroVeActionPerformed

		tblDanhSachPhanCong.clearSelection();
		int totalRowCount = modelDSPC.getRowCount();
		for(int i=0;i<totalRowCount;i++) {
			tblDanhSachPhanCong.setRowSelectionInterval(i, i);

			int row = tblDanhSachPhanCong.getSelectedRow();
			int soLuongPhanCong =(int) tblDanhSachPhanCong.getValueAt(row, 7) ;
			String soLuongHoanThanh = tblDanhSachPhanCong.getValueAt(row, 8).toString() ;

			try {
				int parsedNumber = Integer.parseInt(soLuongHoanThanh);
				int soLuongConLai = soLuongPhanCong - parsedNumber;
				String maCongDoan = (String) tblDanhSachPhanCong.getValueAt(row, 1);
				String maCongNhan = (String) tblDanhSachPhanCong.getValueAt(row, 2);
				if(bangPCCD_dao.capNhatSoLuongBangCongDoan(soLuongConLai, maCongDoan, maCongNhan)==false) {
					JOptionPane.showMessageDialog(null, "Chấm công thất bại");
				}
			}catch (NumberFormatException e) {
				System.err.println("Invalid number format");
			}
			BangChamCongCongNhan cccnMoi = layDuLieuDongDaChonDeChamCong();
			if(!bangCCCN_dao.themChamcongCongNhan(cccnMoi,nv.getMaNhanSu() )) {
				JOptionPane.showMessageDialog(null, "Lỗi rồi");
			}
		}
		if(congDoan_Dao.updateTrangThaiCongDoan()==true) {
			System.out.println("Đã update trạng thái công đoạn");
		}else {
			System.out.println("Update trạng thái công đoạn thất bại");
		}
		for(int i=0;i<modelDSPC.getRowCount();i++) {
			if(congDoan_Dao.updateTrangThaiSanPham(modelDSPC.getValueAt(i, 1).toString().substring(modelDSPC.getValueAt(i, 1).toString().length() - 6))==true) {
				System.out.println("Update trạng thái sản phẩm thành công");
			}else {
				System.out.println("Update trạng thái sản phẩm thất bại");
			}
			modelDSPC.setRowCount(0);
			loadDanhSachPhanCong();
			JOptionPane.showMessageDialog(null, "Chấm công thành công");
		}
	}//GEN-LAST:event_btnTroVeActionPerformed

	private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
		// TODO add your handling code here:
		Window window = SwingUtilities.getWindowAncestor(btnTroVe);
		window.dispose();
	}//GEN-LAST:event_btnTroVeActionPerformed




	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnChamCong;
	private javax.swing.JButton btnTroVe;
	private javax.swing.JComboBox<String> cmbCaLam;
	private com.toedter.calendar.JDateChooser dateChNgayCham;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblDanhSachPhanCong;
	// End of variables declaration//GEN-END:variables
}
