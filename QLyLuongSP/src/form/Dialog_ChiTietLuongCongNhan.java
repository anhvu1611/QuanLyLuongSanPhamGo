    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
    */
    package form;

    import java.awt.FlowLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.SQLException;
    import java.text.NumberFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.Locale;
    import java.util.Vector;

    import javax.swing.JDialog;
    import javax.swing.JFileChooser;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JTable;
    import javax.swing.table.DefaultTableCellRenderer;
    import javax.swing.table.DefaultTableModel;

    import DAO.BangChamCongCongNhan_Dao;
    import DAO.BangPhanCongCongDoan_Dao;
    import DAO.CongNhan_Dao;
    import DAO.NhanVien_Dao;
    import utils.*;
    import entity.BangChamCongCongNhan;
    import entity.ChuyenMon;
    import entity.CongNhan;

    /**
     *
     * @author Vu
     */
    public class Dialog_ChiTietLuongCongNhan extends javax.swing.JDialog {
        private BangChamCongCongNhan_Dao bangCCCN_dao;
        private CongNhan_Dao congNhan_dao;
        private DefaultTableModel modelChiTietLuong;
        private static final String[] HEADERS = { "STT", "Ngày làm", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Ca làm", "Số lượng", "Thành tiền"};
        private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        /**
         * Creates new form ChiTietLuongCongNhan
         */
//        public ChiTietLuongCongNhan(java.awt.Frame parent, Vector rowData, String month) {
//            super(parent);
//            KetNoiCSDL();
//            initComponents();
//            txtMaCongNhan.setText(rowData.get(2).toString());
//            CongNhan cn = congNhan_dao.timCongNhanBangMa(txtMaCongNhan.getText());
//            txtTenCongNhan.setText(cn.getHo()+" "+cn.getTen());
//            Date thangCham = (Date) rowData.get(9);
//            int thang = thangCham.getMonth();
//            
//            int nam = thangCham.getYear();
//            loadBangChamCongCongNhan(Integer.parseInt(month), txtMaCongNhan.getText());
//            
//            
//            // Định dạng giá trị double thành chuỗi VNĐ
//            String formattedAmount = currencyFormat.format(tinhTongTien());
//            txtTongTien.setText(formattedAmount);
//            
//            btnInChiTiet.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    Date ngayIn = new java.sql.Date(new java.util.Date().getTime());
//                    SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
//                    String formatDate = dt.format(ngayIn);
//                    
//                    
//                    String outputFilePath = null;
//                    
//                    
//                    try {
//
//                        JFileChooser luuFile = new JFileChooser();
//                        luuFile.setDialogTitle("Chọn thư mục lưu");
//
//                        int luaChon = luuFile.showSaveDialog(null);
//
//                        if (luaChon == JFileChooser.APPROVE_OPTION) {
//                            String filePath = luuFile.getSelectedFile().getAbsolutePath();
//                            
//                            if (filePath != null) {
//                                outputFilePath = filePath + ".pdf";
//                            }
//
//
//                            outputFilePath = filePath + ".pdf";
//
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//
//                    LuuFile luuFile = new LuuFile();
//                    
//                    boolean ketQua = luuFile.InBangLuong(outputFilePath, thang, nam, tblChiTiet);
//                    
//                    if (ketQua){
//                        JOptionPane.showMessageDialog(null, "In thành công");
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null, "In thất bại");
//
//                    }
//
//                }
//
//            });
//        }
        
        //load table chi tiết lương
        private void loadBangChamCongCongNhan(int thang, String ma) {
            
            CongNhan cn = congNhan_dao.timCongNhanBangMa(ma);
            ChuyenMon chuyenMon = null;
            String chuyenMonCN = cn.getChuyenMon().getTenChuyenMon();
            double heSoLuongTheoChuyenMon;
            if(chuyenMonCN.equalsIgnoreCase("Cắt gỗ")) {
                heSoLuongTheoChuyenMon = 1.02;
            }else if(chuyenMonCN.equalsIgnoreCase("Chế tác")) {
                heSoLuongTheoChuyenMon = 1.04;
            }else if(chuyenMonCN.equalsIgnoreCase("Lắp ráp")) {
                heSoLuongTheoChuyenMon = 1;
            }else {
                heSoLuongTheoChuyenMon = 1.01;
            }
            ArrayList<BangChamCongCongNhan> bcccn = bangCCCN_dao.timChamCongTheoThangVaTheoMa(ma,thang);
            for (BangChamCongCongNhan bcc : bcccn) {
                System.out.println(bcc.getChamCongDoan().getCongDoan().getMaCongDoan());
                modelChiTietLuong.addRow(new Object[] {bcccn.indexOf(bcc)+1, 
                        bcc.getNgayChamCong(),
                        bcc.getChamCongDoan().getCongDoan().getSanPham().getMaSanPham(),				
                        bcc.getChamCongDoan().getCongDoan().getSanPham().getTenSP(),
                        bcc.getChamCongDoan().getCongDoan().getMaCongDoan(),
                        bcc.getChamCongDoan().getCongDoan().getLoaiCongDoan(),
                        bcc.getCaLam(),
                        bcc.getSoLuongSanPhamHoanThanh(), 
                        bcc.getSoLuongSanPhamHoanThanh() 
                        * (bcc.getCaLam().toString().equals("Ca Ba") ? 1.5 : 1) 
                        * bcc.getChamCongDoan().getCongDoan().getLuongTren1Sp() 
                        * heSoLuongTheoChuyenMon 
                        
                });
            }	
            tblChiTiet.getColumnModel().getColumn(8).setCellRenderer(new CurrencyRenderer());
        }
        private static class CurrencyRenderer extends DefaultTableCellRenderer {
            private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

            @Override
            protected void setValue(Object value) {
                if (value != null) {
                    value = currencyFormat.format(value);
                }
                super.setValue(value);
            }
        }
        
    // khởi tạo kết nối đến CSDL
        public void KetNoiCSDL() {
            try {
                ConnectDB.ConnectDB.getInstance().connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bangCCCN_dao = new BangChamCongCongNhan_Dao();
            congNhan_dao = new CongNhan_Dao();
        }
        //Tính tổng tiền
        public double tinhTongTien() {
            double sum = 0;
            // Lặp qua tất cả các dòng và tính tổng cột mong muốn
            for (int row = 0; row < tblChiTiet.getRowCount(); row++) {
                Object value = tblChiTiet.getValueAt(row, 8);
                // Kiểm tra giá trị có phải là kiểu double không trước khi tính tổng
                if (value instanceof Double) {
                    sum += (Double) value;
                } else if (value instanceof String) {
                    // Nếu giá trị là String, thử chuyển đổi thành double
                    try {
                        sum += Double.parseDouble((String) value);
                    } catch (NumberFormatException ignored) {
                        // Nếu không thể chuyển đổi, bỏ qua giá trị không hợp lệ
                        System.out.println("Chuyen khong duoc");
                    }
                }
            }
            return sum;
            
        }
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {
            
            jPanel1 = new javax.swing.JPanel();
            jPanel2 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            txtMaCongNhan = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtTenCongNhan = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            txtTongTien = new javax.swing.JLabel();
            btnInChiTiet = new javax.swing.JButton();
            jScrollPane1 = new javax.swing.JScrollPane();
            tblChiTiet = new javax.swing.JTable();
            jLabel3 = new javax.swing.JLabel();
            txtLuuY = new JLabel();
            setTitle("Chi tiết lương công nhân trong tháng");
            
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new java.awt.Dimension(1200, 710));

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
            jLabel1.setText("Chi tiết lương công nhân trong tháng");

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel2.setText("Mã công nhân:");

            txtMaCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            txtMaCongNhan.setText("CN0003");

            
            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel4.setText("Tên công nhân:");

            txtTenCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            txtTenCongNhan.setText("Hoàng Trình");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel6.setText("Tổng tiền:");

            txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            btnInChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnInChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/print-24.png"))); // NOI18N
            btnInChiTiet.setText("In chi tiết");
            

            jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết lương của công nhân "));

            tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "STT", "Ngày làm", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Ca làm", "Số lượng", "Thành tiền(Chưa tính hệ số lương chuyên môn)"
                }
            ));
            modelChiTietLuong = new DefaultTableModel(HEADERS, 0);
            tblChiTiet = new JTable(modelChiTietLuong);
            jScrollPane1.setViewportView(tblChiTiet);

            

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(0,0,0));
            jLabel1.setText("Chi tiết lương công nhân trong tháng");

            jPanel1.setBackground(new java.awt.Color(255, 153, 51));
            jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)));

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
            jLabel1.setText("Chi tiết lương công nhân trong tháng");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(jLabel1)
                    .addContainerGap(89, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel1)
                    .addContainerGap(30, Short.MAX_VALUE))
            );
            
            jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 102)));
            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtTenCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addGap(18, 18, 18)
                    .addComponent(txtTongTien)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMaCongNhan)
                        .addComponent(jLabel4)
                        .addComponent(txtTenCongNhan)
                        .addComponent(jLabel6)
                        .addComponent(txtTongTien)
                        .addComponent(jLabel3))
                    .addContainerGap(39, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 38, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(btnInChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(255, 255, 255)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
                .addGroup(layout.createSequentialGroup()
                    .addGap(176, 176, 176)
                    .addComponent(btnInChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
            */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Dialog_ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Dialog_ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Dialog_ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Dialog_ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

        }
        
        
        public Dialog_ChiTietLuongCongNhan(java.awt.Frame parent, String ma,  Date thangCham) {
            super(parent);
            KetNoiCSDL();
            initComponents();
            txtMaCongNhan.setText(ma);
            CongNhan cn = congNhan_dao.timCongNhanBangMa(ma);
            txtTenCongNhan.setText(cn.getHo()+" "+cn.getTen());
//            Date thangCham = (Date) rowData.get(9);
            int thang = thangCham.getMonth();
            System.out.println(thang);
            int nam = thangCham.getYear();
            System.out.println(nam);
            loadBangChamCongCongNhan(ma, thangCham);
            
            
            // Định dạng giá trị double thành chuỗi VNĐ
            String formattedAmount = currencyFormat.format(tinhTongTien());
            txtTongTien.setText(formattedAmount);
            
            btnInChiTiet.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    Date ngayIn = new java.sql.Date(new java.util.Date().getTime());
                    SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
                    String formatDate = dt.format(ngayIn);
                    
                    
                    String outputFilePath = null;
                    
                    
                    try {

                        JFileChooser luuFile = new JFileChooser();
                        luuFile.setDialogTitle("Chọn thư mục lưu");

                        int luaChon = luuFile.showSaveDialog(null);

                        if (luaChon == JFileChooser.APPROVE_OPTION) {
                            String filePath = luuFile.getSelectedFile().getAbsolutePath();
                            
                            if (filePath != null) {
                                outputFilePath = filePath + ".pdf";
                            }


                            outputFilePath = filePath + ".pdf";

                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }

                    LuuFile luuFile = new LuuFile();
                    
                    boolean ketQua = luuFile.InBangLuong(outputFilePath, thang, 2023, tblChiTiet);
                    
                    if (ketQua){
                        JOptionPane.showMessageDialog(null, "In thành công");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "In thất bại");

                    }

                }

            });
        }
        
        private void loadBangChamCongCongNhan(String maCongNhan, Date ngayCham) {
        	CongNhan cn = CongNhan_Dao.timCongNhanBangMa(maCongNhan);
        	ChuyenMon chuyenMon = null;
            String chuyenMonCN = cn.getChuyenMon().getTenChuyenMon();
            double heSoLuongTheoChuyenMon;
            if(chuyenMonCN.equalsIgnoreCase("Cắt gỗ")) {
                heSoLuongTheoChuyenMon = 1.02;
            }else if(chuyenMonCN.equalsIgnoreCase("Chế tác")) {
                heSoLuongTheoChuyenMon = 1.04;
            }else if(chuyenMonCN.equalsIgnoreCase("Lắp ráp")) {
                heSoLuongTheoChuyenMon = 1;
            }else {
                heSoLuongTheoChuyenMon = 1.01;
            }
            ArrayList<Vector<String>> bcccn = bangCCCN_dao.layDanhSachChamCongTheoMaCongNhanVaThang(maCongNhan, ngayCham.getMonth()+1);
            int z=0;
            modelChiTietLuong.setRowCount(0);
            for (Vector<String> bcc : bcccn) {
                	bcc.add(0,++z+"");
                	bcc.add(1,new Date().toString());
                	modelChiTietLuong.addRow(bcc);
                }

//            tblChiTiet.getColumnModel().getColumn(8).setCellRenderer(new CurrencyRenderer());            
            }	
        

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnInChiTiet;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tblChiTiet;
        private javax.swing.JLabel txtMaCongNhan;
        private javax.swing.JLabel txtTenCongNhan;
        private javax.swing.JLabel txtTongTien;
        private javax.swing.JLabel txtLuuY;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        // End of variables declaration//GEN-END:variables
    }
