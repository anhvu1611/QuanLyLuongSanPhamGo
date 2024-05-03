/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

/**
 *
 * @author ACER
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import entity.BangLuongCongNhan;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GradientBarPainter;
import org.jfree.chart.renderer.xy.XYSplineRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;

public class Frm_BieuDoCot extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

	/**
     * Creates new form Frm_BieuDoCot
     */
    public Frm_BieuDoCot() {
        initComponents();
        xuatBieuDo();
    }
    
    private void xuatBieuDo(){
        setLayout(new BorderLayout());
        CategoryDataset dataset = createDataset();

        // Tạo biểu đồ từ dữ liệu
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ",  // Tiêu đề biểu đồ
                "Mã Nhân Viên",              // Nhãn trục x
                "Lương",               // Nhãn trục y
                dataset                 // Dữ liệu
        );
        CategoryPlot plot = chart.getCategoryPlot();

        // Lấy renderer cho các cột
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Đặt màu cho từng cột
        renderer.setSeriesPaint(0, Color.BLUE);

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel, BorderLayout.CENTER);
    }
    
    public void xuatBieuDo2Cot(ArrayList<Vector<String>> ds, int lc){
    	removeAll();
        setLayout(new BorderLayout());
        CategoryDataset dataset = taoDataSet2Cot(ds, lc);

        // Tạo biểu đồ từ dữ liệu
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ",  // Tiêu đề biểu đồ
                "Mã Công Nhân",              // Nhãn trục x
                "Giá trị",               // Nhãn trục y
                dataset                 // Dữ liệu
        );
        CategoryPlot plot = chart.getCategoryPlot();

        // Lấy renderer cho các cột
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Đặt màu cho từng cột
        renderer.setSeriesPaint(0, Color.BLUE);

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }
    
    private CategoryDataset taoDataSet2Cot(ArrayList<Vector<String>> ds, int lc) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	if(lc==0) {
    		for (Vector<String> vector : ds) {
    			dataset.addValue(Double.parseDouble(vector.get(1)), "Lương Sản Phẩm", vector.get(0));
    			dataset.addValue(Double.parseDouble(vector.get(2)), "Lương Nhận", vector.get(0));
			}
    	}else if(lc==1) {
    		for (Vector<String> vector : ds) {
    			dataset.addValue(Integer.parseInt(vector.get(3)), "Số Sản Phẩm", vector.get(0));
			}
    	}
    	return dataset;
    }
    
    public void xuatBieuDoDuong(ArrayList<BangLuongCongNhan> data, int lc) {
        removeAll();
        setLayout(new BorderLayout());
        XYSeries series = new XYSeries("Dữ liệu");
        chuyenDuLieuBieuDoDuong(data, series, lc);

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Biểu Đồ Đường",  // Tiêu đề biểu đồ
                "Tháng",             // Nhãn trục x
                "Lương",             // Nhãn trục y
                dataset               // Dữ liệu
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYSplineRenderer renderer = new XYSplineRenderer();
        plot.setRenderer(renderer);

        // Cài đặt nhãn cho mỗi điểm dữ liệu trên cột y
        XYItemLabelGenerator generator = new StandardXYItemLabelGenerator("{2}", new SimpleDateFormat("MM"), NumberFormat.getInstance());
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();

        // Tùy chỉnh đơn vị trục x và y
        TickUnits xTickUnits = new TickUnits();
        xTickUnits.add(new NumberTickUnit(1.0));
        xAxis.setStandardTickUnits(xTickUnits);

        TickUnits yTickUnits = new TickUnits();
        yTickUnits.add(new NumberTickUnit(1000.0)); // Điều chỉnh đơn vị theo giá trị dữ liệu của bạn
        yAxis.setStandardTickUnits(yTickUnits);

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    private void chuyenDuLieuBieuDoDuong(ArrayList<BangLuongCongNhan> data, XYSeries series, int lc) {
        if(lc==0) {
        	for (BangLuongCongNhan point : data) {
                double xValue = Double.parseDouble(new SimpleDateFormat("MM").format(point.getNgayLap()));
                double yValue = point.getTongLuong();
                series.add(xValue, yValue);
            }
        }else {
        	for (BangLuongCongNhan point : data) {
                double xValue = Double.parseDouble(new SimpleDateFormat("MM").format(point.getNgayLap()));
                double yValue = point.getSoLuongSanPham();
                series.add(xValue, yValue);
            }
        }
    }

	public void xuatBieuDo(ArrayList<Vector<String>> ds, int option) {
        removeAll();
        setLayout(new BorderLayout());
        CategoryDataset dataset = createDataset(ds,option);

        // Tạo biểu đồ từ dữ liệu
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ",  // Tiêu đề biểu đồ
                "Mã Nhân Viên",  // Nhãn trục x
                "Lương",  // Nhãn trục y
                dataset,  // Dữ liệu
                PlotOrientation.VERTICAL,  // Hướng biểu đồ
                true, true, false
        );
        CategoryPlot plot = chart.getCategoryPlot();

        // Đặt màu nền là màu đen
        plot.setBackgroundPaint(Color.BLACK);

        // Lấy renderer cho các cột
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Sử dụng GradientBarPainter để tạo gradient cho cột
        renderer.setBarPainter(new GradientBarPainter());

        // Đặt màu sắc gradient cho từng cột
        for (int i = 0; i < ds.size(); i++) {
            // Tạo GradientPaint từ màu đỏ đến màu cam
            Color red = new Color(255, 0, 0); // Màu đỏ
            Color orange = new Color(255, 165, 0); // Màu cam

            // Tính toán màu sắc gradient dựa trên vị trí của cột trong dãy
            float fraction = (float) i / ds.size();
            Color columnColor = new Color(
                    (int) (red.getRed() * (1 - fraction) + orange.getRed() * fraction),
                    (int) (red.getGreen() * (1 - fraction) + orange.getGreen() * fraction),
                    (int) (red.getBlue() * (1 - fraction) + orange.getBlue() * fraction)
            );

            // Tạo GradientPaint từ màu đỏ đến màu cam
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(1, 0);
            GradientPaint gradientPaint = new GradientPaint(start, columnColor, end, Color.WHITE);

            // Đặt màu sắc gradient cho từng cột
            renderer.setSeriesPaint(i, gradientPaint);
        }

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        add(chartPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(12, "Dữ liệu 1", "Thể loại 1");
        dataset.addValue(24, "Dữ liệu 1", "Thể loại 2");
        dataset.addValue(36, "Dữ liệu 1", "Thể loại 3");

        return dataset;
    }
    
    private CategoryDataset createDataset(ArrayList<Vector<String>> ds, int option) {
    	if(option == 0) {
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Vector<String> vector : ds) {
    			dataset.addValue(Double.parseDouble(vector.get(0)), "Lương", vector.get(6));
    		}

            return dataset;
    	}else if(option == 1) {
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Vector<String> vector : ds) {
    			dataset.addValue(Double.parseDouble(vector.get(1)), "Ngày Công", vector.get(6));
    		}

            return dataset;
    	}else {
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Vector<String> vector : ds) {
    			dataset.addValue(Double.parseDouble(vector.get(0)), "Lương", vector.get(6));
    		}

            return dataset;
    	}
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
