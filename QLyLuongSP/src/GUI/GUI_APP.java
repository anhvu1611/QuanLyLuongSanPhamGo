/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import form.Dialog_DoiMatKhau;
import form.Frm_ThongKe_CongNhan;
import form.Frm_ThongKe_NhanVien;
import form.Frm_TrangChu;
import form.frm_CongNhan_Luong;
//import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;
import form.MenuItem;
import form.frm_CongNhan_ChamCong;
import form.frm_NhanVien_ChamCong;
import form.frm_SanPham_PhanCong;
import form.frm_CongNhan_CapNhat;
import form.frm_CongNhan_TimKiem;
import form.frm_CongNhan_TinhLuong;
import form.frm_NhanVien_CapNhat;
import form.frm_NhanVien_TimKiem;
import form.frm_NhanVien_TinhLuong;
import form.frm_SanPham_CapNhat;
import form.frm_SanPham_ChiaCongDoan;
import form.frm_SanPham_TimKiem;
import form.frm_ThongKe_SanPham;

/**
 *
 * @author Vu
 */
public class GUI_APP extends javax.swing.JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2836698090758608258L;
	private MenuItem menuHomePage,
            menuSanPham, menuCapNhatSP, menuPhanCong,menuTimKiemSP,menuTaoCongDoan,
            menuNhanVien,menuCapNhatNV, menuChamCongNV,menuLuongNV,menuTimKiemNV,
            menuCongNhan, menuCapNhatCN, menuChamCongCN, menuLuongCN, menuTimKiemCN,
            menuThongKe,menuThongKeLuongNV, menuThongKeLuongCN, menuThongKeSP;

    /**
     * Creates new form DanhSachPhong
     */
    public GUI_APP(NhanVien nv) {
        initComponents(nv);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute(nv);
    }
    
	private void execute(NhanVien nv){
          //icon
          ImageIcon iconHomePage = new ImageIcon(getClass().getResource("/item/home-48.png"));
          ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/item/chart-48.png"));
          ImageIcon iconSP = new ImageIcon(getClass().getResource("/item/wood-48.png"));
          ImageIcon iconCongNhan = new ImageIcon(getClass().getResource("/item/worker-48.png"));
          ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/item/employee-48.png"));
          ImageIcon iconBill1 = new ImageIcon(getClass().getResource("/item/dot.png"));
          ImageIcon iconBill2 = new ImageIcon(getClass().getResource("/item/dot.png"));
          
          if(nv.getChucVu().getMaChucVu().equalsIgnoreCase("CV0005")) {
        	  menuHomePage = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new Frm_TrangChu());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              //Sản phẩm

              menuTimKiemSP = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuSanPham = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                
                  }
              }, menuTimKiemSP
//            		  menuCapNhatSP, menuPhanCong,menuTimKiemSP, menuTaoCongDoan
            		  );
            
              menuLuongNV = new MenuItem(iconBill2, "Lương", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TinhLuong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemNV = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              },menuLuongNV,menuTimKiemNV
//            		  menuCapNhatNV, menuChamCongNV,menuLuongNV,menuTimKiemNV
            		  );

              menuLuongCN = new MenuItem(iconBill2, "Lương", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_Luong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemCN = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuCongNhan = new MenuItem(iconCongNhan, "Công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              },menuLuongCN,menuTimKiemCN
//            		  menuCapNhatCN, menuChamCongCN,menuLuongCN,menuTimKiemCN
            		  );


              //Thống kê
              menuThongKeLuongNV = new MenuItem(iconBill1, "Lương nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new Frm_ThongKe_NhanVien());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

              menuThongKeLuongCN = new MenuItem(iconBill2, "Lương công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new Frm_ThongKe_CongNhan());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuThongKeSP = new MenuItem(iconBill2, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_ThongKe_SanPham());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuThongKeLuongNV, menuThongKeLuongCN,menuThongKeSP);


              menuHomePage.setBackground(menus.getBackground());
              menuCongNhan.setBackground(menus.getBackground());
              menuNhanVien.setBackground(menus.getBackground());
              menuSanPham.setBackground(menus.getBackground());
              menuThongKe.setBackground(menus.getBackground());

              addMenu(menuHomePage,menuSanPham,menuNhanVien,menuCongNhan,menuThongKe);
          }else if(nv.getChucVu().getMaChucVu().equalsIgnoreCase("CV0006")) {
        	//menu trang chủ
              menuHomePage = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new Frm_TrangChu());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              //Sản phẩm
              
            	  
              menuTimKiemSP = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              
              menuSanPham = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                
                  }
              },menuTimKiemSP);
            
              
              menuCapNhatNV = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_CapNhat());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              
              

              
              menuTimKiemNV = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuCapNhatNV,menuTimKiemNV);

              //Công nhân
              menuCapNhatCN = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_CapNhat());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

             
              menuTimKiemCN = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuCongNhan = new MenuItem(iconCongNhan, "Công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuCapNhatCN,menuTimKiemCN);


             
              menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              });


              menuHomePage.setBackground(menus.getBackground());
              menuCongNhan.setBackground(menus.getBackground());
              menuNhanVien.setBackground(menus.getBackground());
              menuSanPham.setBackground(menus.getBackground());
              menuThongKe.setBackground(menus.getBackground());

              addMenu(menuHomePage,menuSanPham,menuNhanVien,menuCongNhan,menuThongKe);
          }else if(nv.getChucVu().getMaChucVu().equalsIgnoreCase("CV0002")) {
        	  menuHomePage = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new Frm_TrangChu());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              //Sản phẩm
              
            	  menuCapNhatSP = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
          			@Override
          			public void actionPerformed(ActionEvent e) {
          				// TODO Auto-generated method stub
          				panelBody.removeAll();
                          panelBody.add(new frm_SanPham_CapNhat());
                          panelBody.repaint();
                          panelBody.revalidate();
          			}
                    });

              menuPhanCong = new MenuItem(iconBill2, "Phân công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_PhanCong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemSP = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTaoCongDoan = new MenuItem(iconBill2, "Tạo công đoạn", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_ChiaCongDoan());   
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuSanPham = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                
                  }
              }, menuCapNhatSP, menuPhanCong,menuTimKiemSP, menuTaoCongDoan);
            
              

                        

              menuChamCongNV = new MenuItem(iconBill2, "Chấm công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_ChamCong(nv));
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemNV = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuChamCongNV,menuTimKiemNV);

              menuChamCongCN = new MenuItem(iconBill2, "Chấm công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_ChamCong(nv));
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

              menuTimKiemCN = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuCongNhan = new MenuItem(iconCongNhan, "Công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuChamCongCN,menuTimKiemCN);


              //Thống kê
              
              menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              });


              menuHomePage.setBackground(menus.getBackground());
              menuCongNhan.setBackground(menus.getBackground());
              menuNhanVien.setBackground(menus.getBackground());
              menuSanPham.setBackground(menus.getBackground());
              menuThongKe.setBackground(menus.getBackground());

              addMenu(menuHomePage,menuSanPham,menuNhanVien,menuCongNhan,menuThongKe);
          
          }else if(nv.getChucVu().getMaChucVu().equals("CV0000")){
        	  menuHomePage = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();

                      panelBody.repaint();
                      panelBody.revalidate();
                      
                      //menuHomePage.setBackground(Color.CYAN);
                  }
              });
              //Sản phẩm
              menuCapNhatSP = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_CapNhat());
                      panelBody.repaint();
                      panelBody.revalidate();
                      
//                      menuCapNhatSP.setBackground(Color.cyan);
//                      menuHomePage.setBackground(menus.getBackground());
                  }
              });

              menuPhanCong = new MenuItem(iconBill2, "Phân công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_PhanCong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemSP = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTaoCongDoan = new MenuItem(iconBill2, "Tạo công đoạn", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_SanPham_ChiaCongDoan());   
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuSanPham = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                
                  }
              }, menuCapNhatSP, menuPhanCong,menuTimKiemSP, menuTaoCongDoan);
            
              //Nhân viên
              menuCapNhatNV = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_CapNhat());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

              menuChamCongNV = new MenuItem(iconBill2, "Chấm công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_ChamCong(nv));
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuLuongNV = new MenuItem(iconBill2, "Lương", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TinhLuong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemNV = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_NhanVien_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuCapNhatNV, menuChamCongNV,menuLuongNV,menuTimKiemNV);

              //Công nhân
              menuCapNhatCN = new MenuItem(iconBill1, "Cập nhật", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_CapNhat());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

              menuChamCongCN = new MenuItem(iconBill2, "Chấm công", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_ChamCong(nv));
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuLuongCN = new MenuItem(iconBill2, "Lương", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_TinhLuong());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuTimKiemCN = new MenuItem(iconBill2, "Tìm kiếm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();
                      panelBody.add(new frm_CongNhan_TimKiem());
                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuCongNhan = new MenuItem(iconCongNhan, "Công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuCapNhatCN, menuChamCongCN,menuLuongCN,menuTimKiemCN);


              //Thống kê
              menuThongKeLuongNV = new MenuItem(iconBill1, "Lương nhân viên", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                      panelBody.removeAll();

                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });

              menuThongKeLuongCN = new MenuItem(iconBill2, "Lương công nhân", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();

                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuThongKeSP = new MenuItem(iconBill2, "Sản phẩm", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      panelBody.removeAll();

                      panelBody.repaint();
                      panelBody.revalidate();
                  }
              });
              menuThongKe = new MenuItem(iconThongKe, "Thống kê", new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
              }, menuThongKeLuongNV, menuThongKeLuongCN,menuThongKeSP);


              menuHomePage.setBackground(menus.getBackground());
              menuCongNhan.setBackground(menus.getBackground());
              menuNhanVien.setBackground(menus.getBackground());
              menuSanPham.setBackground(menus.getBackground());
              menuThongKe.setBackground(menus.getBackground());

              addMenu(menuHomePage,menuSanPham,menuNhanVien,menuCongNhan,menuThongKe);
          }
          
          

    }

    //add tất cả menu
    private void addMenu(MenuItem...menu){
        for(int i=0;i<menu.length;i++){
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for(MenuItem m : subMenu){
            		addMenu(m);
            }
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="colln apsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(NhanVien nv) {
//    	System.out.println(nv);
//    	qlTaiKhoan = new TaiKhoan_Dao();
        panelHeader = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnChangePass = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(1352, 800));

        panelHeader.setBackground(new java.awt.Color(241, 153, 82));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Chương trình quản lý lương sản phẩm");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/wood-80.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("WorkWood");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(146, 146, 146)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 600));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menus.setBackground(new java.awt.Color(255, 240, 197));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        jPanel1.setBackground(new java.awt.Color(255, 240, 197));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/nhanVien.png"))); // NOI18N

        jLabel2.setText(nv.getHo()+" "+nv.getTen());
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnChangePass.setBackground(new java.awt.Color(35, 142, 241));
        btnChangePass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChangePass.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/key.png"))); // NOI18N
        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	TaiKhoan tk = nv.getTaiKhoan();
            	System.out.println(tk);
                btnChangePassActionPerformed(evt, tk);
            }
        });

        btnLogout.setBackground(new java.awt.Color(35, 142, 241));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/item/doiPass.png"))); // NOI18N
        btnLogout.setText("Đăng xuất");
        btnLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thoatKhoiChuongTrinh();
			}
		});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnChangePass)
                .addGap(12, 12, 12)
                .addComponent(btnLogout)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBodyMousePressed(evt);
            }
        });
        panelBody.setLayout(new java.awt.BorderLayout());
        panelBody.add(new Frm_TrangChu(), java.awt.BorderLayout.CENTER);

        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        
        
        setBounds(0, 0, 1460, 815);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }// </editor-fold>//GEN-END:initComponents

    protected void thoatKhoiChuongTrinh() {
    	int lc = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát khỏi chương trình", "Chú ý", JOptionPane.YES_NO_OPTION);
		if(lc==JOptionPane.YES_OPTION) {
			this.dispose();
			frm_DangNhap frm_DangNhap = new frm_DangNhap();
			frm_DangNhap.setLocationRelativeTo(null);
			frm_DangNhap.setVisible(true);
		}
	}
	private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt, TaiKhoan tk) {//GEN-FIRST:event_btnChangePassActionPerformed
       Dialog_DoiMatKhau dialog_DoiMatKhau = new Dialog_DoiMatKhau(new Frame(), true, tk);
       dialog_DoiMatKhau.setLocationRelativeTo(null);
       dialog_DoiMatKhau.setVisible(true);
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void panelBodyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBodyMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBodyMousePressed


    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI_APP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI_APP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI_APP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI_APP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUI_APP().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
//    private TaiKhoan_Dao qlTaiKhoan;
    
    // End of variables declaration//GEN-END:variables


}
