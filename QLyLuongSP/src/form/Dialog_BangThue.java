/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package form;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import utils.GhiThue;

/**
 *
 * @author ACER
 */
public class Dialog_BangThue extends java.awt.Dialog {

    private static final long serialVersionUID = 3039675595526356362L;

	/**
     * Creates new form Dialog_BangThue
     */
    public Dialog_BangThue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Bảng Thuế Năm 2023");
        initComponents();
        layDuLieuThueVaDayDuLieuLenBang();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	modal = new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    
                },
                new String [] {
                    "Thu Nhập Tính Thuế", "Thuế"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, true
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
        pnNorth = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        pnMain = new javax.swing.JPanel();
        scrBangLuong = new javax.swing.JScrollPane();
        tblBangThue = new javax.swing.JTable();
        pnSouth = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(500, 350));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        pnNorth.setBackground(new java.awt.Color(255, 255, 255));

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Bảng Thuế Năm 2023");
        pnNorth.add(lblTieuDe);

        add(pnNorth, java.awt.BorderLayout.NORTH);

        pnMain.setBackground(new java.awt.Color(255, 255, 255));

        scrBangLuong.setPreferredSize(new java.awt.Dimension(375, 250));

        tblBangThue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblBangThue.setModel(modal);
        tblBangThue.setGridColor(new java.awt.Color(204, 204, 204));
        tblBangThue.setRowHeight(30);
        tblBangThue.setShowGrid(true);
        scrBangLuong.setViewportView(tblBangThue);

        pnMain.add(scrBangLuong);

        add(pnMain, java.awt.BorderLayout.CENTER);

        pnSouth.setBackground(new java.awt.Color(255, 255, 255));

        btnSua.setIcon(new javax.swing.ImageIcon("C:\\Users\\ACER\\OneDrive - Industrial University of HoChiMinh City\\Desktop\\Code\\QuanLySanPham\\QLyLuongSP\\img\\icons\\edit.png")); // NOI18N
        btnSua.setText("Sửa Bảng Thuế");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnSouth.add(btnSua);

        add(pnSouth, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int lc = JOptionPane.showConfirmDialog(null, "Bảng thuế tuân thủ theo bảng thuế quốc gia, chắc chắn thay đổi?", "Lưu Ý", JOptionPane.YES_NO_OPTION);
        if(lc == JOptionPane.YES_OPTION){
            try {
                kiemTraDuLieuDauVao();
                String []mangDuLieu = new String[7];
                for (int i = 0; i < 7; i++) {
					mangDuLieu[i]= (String) tblBangThue.getValueAt(i, 1);
				}
                GhiThue.thayDoiGiaTriThue(mangDuLieu[0], mangDuLieu[1],mangDuLieu[2],mangDuLieu[3],mangDuLieu[4],mangDuLieu[5],mangDuLieu[6]);
                JOptionPane.showMessageDialog(null, "Thay đổi bảng thuế thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialog_BangThue dialog = new Dialog_BangThue(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DefaultTableModel modal;
    private javax.swing.JButton btnSua;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnMain;
    private javax.swing.JPanel pnNorth;
    private javax.swing.JPanel pnSouth;
    private javax.swing.JScrollPane scrBangLuong;
    private javax.swing.JTable tblBangThue;
    // End of variables declaration//GEN-END:variables

    private void kiemTraDuLieuDauVao() throws Exception {
        for(int i=0;i<7;i++){
            String value = (String)tblBangThue.getValueAt(i, 1);
            value = value.trim();
            if(value.isEmpty()){
                throw new Exception("Điền đầy đủ thông tin bảng thuế");
            }else if(!value.matches("[0-9.]{2,}")){
                throw new Exception("Số thuế tuân thủ theo quy tắc 0.**");
            }
        }
    }
    
    private void layDuLieuThueVaDayDuLieuLenBang() {
    	ArrayList<Vector<String>> bangThue = GhiThue.layDuLieuThue();
    	tblBangThue.removeAll();
    	for (Vector<String> vector : bangThue) {
			modal.addRow(vector);
			
		}
    }
}
