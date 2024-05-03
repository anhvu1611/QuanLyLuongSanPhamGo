/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.DAO_SanPham;
import DAO.DAO_ThongKeSanPham;
import entity.SanPham;

/**
 *
 * @author Vu
 */
public class frm_ThongKe_SanPham extends javax.swing.JPanel {
	private DAO_ThongKeSanPham thongKeSP_dao;
	private DAO_SanPham sanPham_dao;
	ChartPanel chartPanel;
    /**
     * Creates new form ThongKeSanPham
     */
    public frm_ThongKe_SanPham() {
    	KetNoiCSDL();
        initComponents();
    }
    public void KetNoiCSDL() {
  		try {
 			ConnectDB.ConnectDB.getInstance().connect();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
  		thongKeSP_dao = new DAO_ThongKeSanPham();
  		sanPham_dao = new DAO_SanPham();
  	}
    private CategoryDataset createDataset(String chonLoaiThongKe, String tieuChi1, String tieuChi2) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	if(chonLoaiThongKe.equalsIgnoreCase("Số lượng")&& tieuChi1.equalsIgnoreCase("Thống kê theo tháng")) {
    		ArrayList<ArrayList<String>> dsSoLuongSanPham = 
        			thongKeSP_dao.soLuongSanPham(Integer.parseInt((String) cmbTuThang.getSelectedItem()), 
        					Integer.parseInt(cmbTuNam.getSelectedItem().toString()), 
        					Integer.parseInt(cmbDenThang.getSelectedItem().toString()), 
        					Integer.parseInt(cmbDenNam.getSelectedItem().toString()), 
        					cmbTieuChi1.getSelectedItem().toString(), cmbTieuChi2.getSelectedItem().toString());
        	dataset = new DefaultCategoryDataset();
        	for(ArrayList<String> x : dsSoLuongSanPham) {
        		dataset.addValue(Integer.parseInt(x.get(2)), "Số lượng sản phẩm", x.get(1)+"/"+x.get(0));
        	}
    	}else if(chonLoaiThongKe.equalsIgnoreCase("Số lượng")&& tieuChi1.equalsIgnoreCase("Thống kê theo năm")){
    		ArrayList<ArrayList<String>> dsSoLuongSanPham = 
        			thongKeSP_dao.soLuongSanPham(Integer.parseInt((String) cmbTuThang.getSelectedItem()), 
        					Integer.parseInt(cmbTuNam.getSelectedItem().toString()), 
        					Integer.parseInt(cmbDenThang.getSelectedItem().toString()), 
        					Integer.parseInt(cmbDenNam.getSelectedItem().toString()), 
        					cmbTieuChi1.getSelectedItem().toString(), cmbTieuChi2.getSelectedItem().toString());
        	 dataset = new DefaultCategoryDataset();
        	for(ArrayList<String> x : dsSoLuongSanPham) {
        		dataset.addValue(Integer.parseInt(x.get(2)), "Số lượng sản phẩm", x.get(1));
        	}
    	}else if(chonLoaiThongKe.equalsIgnoreCase("Chất gỗ")){
    		ArrayList<ArrayList<String>> dsSanPhamTheoChatGo = thongKeSP_dao.soLuongSanPhamTheoChatGo(Integer.parseInt((String) cmbTuThang.getSelectedItem()), 
					Integer.parseInt(cmbTuNam.getSelectedItem().toString()), 
					Integer.parseInt(cmbDenThang.getSelectedItem().toString()), 
					Integer.parseInt(cmbDenNam.getSelectedItem().toString()), 
					cmbTieuChi1.getSelectedItem().toString(), cmbTieuChi2.getSelectedItem().toString());
    		dataset = new DefaultCategoryDataset();
    		for(ArrayList<String> x : dsSanPhamTheoChatGo) {
    			dataset.addValue(Integer.parseInt(x.get(1)), "Số sản phẩm", x.get(0));
    		}
    		
    	}else {
    		ArrayList<ArrayList<String>> dsSanPhamTheoChatGo = thongKeSP_dao.sanPhamTheoGiaThanh(Integer.parseInt((String) cmbTuThang.getSelectedItem()), 
					Integer.parseInt(cmbTuNam.getSelectedItem().toString()), 
					Integer.parseInt(cmbDenThang.getSelectedItem().toString()), 
					Integer.parseInt(cmbDenNam.getSelectedItem().toString()), 
					cmbTieuChi1.getSelectedItem().toString(), cmbTieuChi2.getSelectedItem().toString());
    		dataset = new DefaultCategoryDataset();
    		for(ArrayList<String> x : dsSanPhamTheoChatGo) {
    			dataset.addValue(Double.parseDouble(x.get(0).replace(",", ".")), "Số sản phẩm", x.get(1));
    		}
    	}
        return dataset;
    }
    
    
    
    private JTable dataTable;
    private JScrollPane tableScrollPane = new JScrollPane();
    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the data for the bar chart (replace this with your actual data)
        CategoryDataset dataset = createDataset(cmbChonLoaiThongKe.getSelectedItem().toString(), cmbTieuChi1.getSelectedItem().toString(), cmbTieuChi2.getSelectedItem().toString());

        // Create the bar chart
       
        JFreeChart barChart;
        if(cmbChonLoaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Số lượng")&&cmbTieuChi1.getSelectedItem().toString().equalsIgnoreCase("Thống kê theo tháng")) {
        	  barChart = ChartFactory.createBarChart(
        	            "Biểu đồ số lượng sản phẩm hoàn thành theo tháng",
        	            "Tháng/năm",
        	            "Số lượng sản phẩm",
        	            dataset,
        	            PlotOrientation.VERTICAL,
        	            true, true, false
        	        );
        }else if(cmbChonLoaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Số lượng")&&cmbTieuChi1.getSelectedItem().toString().equalsIgnoreCase("Thống kê theo năm")){
        	  barChart = ChartFactory.createBarChart(
        	            "Biểu đồ số lượng sản phẩm hoàn thành theo năm",
        	            "Năm",
        	            "Số lượng sản phẩm",
        	            dataset,
        	            PlotOrientation.VERTICAL,
        	            true, true, false
        	        );
        }else if(cmbChonLoaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Chất gỗ")) {
        	 barChart = ChartFactory.createBarChart(
     	            "Biểu đồ thể hiện số sản phẩm theo chất gỗ",
     	            "Chất gỗ",
     	            "Số sản phẩm",
     	            dataset,
     	            PlotOrientation.VERTICAL,
     	            true, true, false
     	        );
        }else {
        	barChart = ChartFactory.createBarChart(
     	            "Biểu đồ thể hiện giá thành theo sản phẩm",
     	            "Tên sản phẩm",
     	            "Giá thành",
     	            dataset,
     	            PlotOrientation.VERTICAL,
     	            true, true, false
     	        );
        }
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel3.removeAll();
        jPanel3.add(chartPanel);
        jPanel3.revalidate();
        jPanel3.repaint();
        
        showTableData();

//     // Tạo JScrollPane để chứa JTable
//        tableScrollPane = new JScrollPane();

        // Thêm JScrollPane vào JPanel
        
        jPanel4.removeAll();
        jPanel4.add(tableScrollPane, BorderLayout.CENTER);
        jPanel4.revalidate();
        jPanel4.repaint();
       
        
    }
    
    
    private void showTableData() {
    	DefaultTableModel tableModel = new DefaultTableModel();
    	if(cmbChonLoaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Giá thành")) {
    		ArrayList<SanPham> dsSoLuong = sanPham_dao.layDanhSachSanPham();
    		tableModel.setColumnIdentifiers(new Object[]{"Tên sản phẩm", "Giá thành", "Ngày sản xuất"});
    		for(SanPham sp : dsSoLuong) {
    			tableModel.addRow(new Object[] {
    					sp.getTenSP(),
    					sp.getGiaThanh(),
    					sp.getThoiGianSanXuatDuKien()
    					
    			});
    			
    		}
    	}else if(cmbChonLoaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Số lượng")) {
    		ArrayList<SanPham> dsSoLuong = sanPham_dao.layDanhSachSanPham();
    		//DefaultTableModel tableModel = new DefaultTableModel();
    		tableModel.setColumnIdentifiers(new Object[]{"Tên sản phẩm", "Số lượng", "Ngày sản xuất"});
    		for(SanPham sp : dsSoLuong) {
    			tableModel.addRow(new Object[] {
    					sp.getTenSP(),
    					sp.getSoLuong(),
    					sp.getThoiGianSanXuatDuKien()
    			});
    		}
    	}else {
    		ArrayList<SanPham> dsSoLuong = sanPham_dao.layDanhSachSanPham();
    		//DefaultTableModel tableModel = new DefaultTableModel();
    		tableModel.setColumnIdentifiers(new Object[]{"Tên sản phẩm", "Chất gỗ", "Ngày sản xuất"});
    		for(SanPham sp : dsSoLuong) {
    			tableModel.addRow(new Object[] {
    					sp.getTenSP(),
    					sp.getChatGo(),
    					sp.getThoiGianSanXuatDuKien()
    			});
    		}
    	}

        dataTable = new JTable(tableModel);

        // Tạo JScrollPane để chứa JTable
        tableScrollPane = new JScrollPane(dataTable);
        
        tableScrollPane.setViewportView(dataTable);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	// Lấy tháng hiện tại từ Calendar
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
     // Lấy năm hiện tại từ Calendar
        int currentYear = calendar.get(Calendar.YEAR);
        jPanel1 = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTuThang = new javax.swing.JLabel();
        lblTuNam = new javax.swing.JLabel();
        lblDenThang = new javax.swing.JLabel();
        lblDenNam = new javax.swing.JLabel();
        cmbTuThang = new javax.swing.JComboBox<>();
        cmbTuNam = new javax.swing.JComboBox<>();
        cmbDenNam = new javax.swing.JComboBox<>();
        cmbDenThang = new javax.swing.JComboBox<>();
        lblChonLoaiThongKe = new javax.swing.JLabel();
        lblTieuChi1 = new javax.swing.JLabel();
        cmbChonLoaiThongKe = new javax.swing.JComboBox<>();
        cmbTieuChi1 = new javax.swing.JComboBox<>();
        btnThongKe = new javax.swing.JButton();
        lblTieuChi2 = new javax.swing.JLabel();
        cmbTieuChi2 = new javax.swing.JComboBox<>();
        btnInThongKe = new javax.swing.JButton();
        btnHuongDan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1295, 634));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 102, 0)));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(255, 102, 0));
        lblTieuDe.setText("Thống kê sản phẩm");

        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(lblTieuDe)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lblTieuDe)
                .addGap(12, 12, 12))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        lblTuThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTuThang.setText("Tháng:");

        lblTuNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTuNam.setText("Năm:");

        lblDenThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDenThang.setText("Đến tháng:");

        lblDenNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDenNam.setText("Năm:");

        cmbTuThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTuThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbTuThang.setSelectedIndex(currentMonth);
        
        cmbTuNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTuNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024" }));
        cmbTuNam.setSelectedItem(String.valueOf(currentYear));
        
        cmbDenNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDenNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024" }));
        cmbDenNam.setSelectedItem(String.valueOf(currentYear));
        
        cmbDenThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDenThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbDenThang.setSelectedIndex(currentMonth);
        
        lblChonLoaiThongKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblChonLoaiThongKe.setText("Chọn loại thống kê:");

        lblTieuChi1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTieuChi1.setText("Tiêu chí 1:");

        cmbChonLoaiThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbChonLoaiThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng", "Giá thành", "Chất gỗ" }));
        cmbChonLoaiThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbChonLoaiThongKeActionPerformed(evt);
            }
        });

        cmbTieuChi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTieuChi1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê theo tháng", "Thống kê theo năm" }));
        cmbTieuChi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTieuChi1ActionPerformed(evt);
            }
        });

        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-chart-24.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        
        lblTieuChi2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTieuChi2.setText("Tiêu chí 2:");

        cmbTieuChi2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTieuChi2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Top 5 cao nhất", "Top 5 thấp nhất", "Top 10 cao nhất", "Top 10 thấp nhất" }));
        cmbTieuChi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTieuChi2ActionPerformed(evt);
            }
        });

//        btnInThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        btnInThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-chart-24.png"))); // NOI18N
//        btnInThongKe.setText("In thống kê");
//        btnInThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
//        btnInThongKe.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnInThongKeActionPerformed(evt);
//            }
//        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTuThang)
                            .addComponent(lblDenThang))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTuThang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDenThang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDenNam)
                            .addComponent(lblTuNam))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDenNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblChonLoaiThongKe)
                            .addComponent(lblTieuChi1)))
                    .addComponent(lblTieuChi2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTieuChi1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbChonLoaiThongKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTieuChi2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                .addGap(69, 69, 69))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTuThang)
                    .addComponent(lblTuNam)
                    .addComponent(cmbTuThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChonLoaiThongKe)
                    .addComponent(cmbChonLoaiThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDenThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDenNam)
                            .addComponent(cmbDenNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTieuChi1)
                            .addComponent(cmbTieuChi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblDenThang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTieuChi2)
                    .addComponent(cmbTieuChi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                .addGap(24, 24, 24))
        );

        btnHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/icons8-question-24.png"))); // NOI18N
        btnHuongDan.addActionListener(new ActionListener() {
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
			        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/huongDan/huongDanThongKeSP.png"));
			        
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
        
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng dữ liệu"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addComponent(btnHuongDan)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnHuongDan)))
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3.setLayout(new BorderLayout());
        jPanel3.setPreferredSize(new java.awt.Dimension(784, 400));
        jPanel4.setLayout(new BorderLayout());
    }// </editor-fold>                        

    private void cmbChonLoaiThongKeActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void cmbTieuChi1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void cmbTieuChi2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnInThongKeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnHuongDan;
    private javax.swing.JButton btnInThongKe;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cmbChonLoaiThongKe;
    private javax.swing.JComboBox<String> cmbDenNam;
    private javax.swing.JComboBox<String> cmbDenThang;
    private javax.swing.JComboBox<String> cmbTieuChi1;
    private javax.swing.JComboBox<String> cmbTieuChi2;
    private javax.swing.JComboBox<String> cmbTuNam;
    private javax.swing.JComboBox<String> cmbTuThang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblChonLoaiThongKe;
    private javax.swing.JLabel lblDenNam;
    private javax.swing.JLabel lblDenThang;
    private javax.swing.JLabel lblTieuChi1;
    private javax.swing.JLabel lblTieuChi2;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTuNam;
    private javax.swing.JLabel lblTuThang;
    // End of variables declaration                   
}
