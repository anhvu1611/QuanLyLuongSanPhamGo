package test;import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import DAO.BangLuongCongNhan_Dao;
import entity.BangLuongCongNhan;

import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BieuDoDuongPanel extends JPanel {
    public BieuDoDuongPanel() {
        // Khởi tạo và hiển thị dữ liệu mẫu
        ArrayList<BangLuongCongNhan> sampleData = BangLuongCongNhan_Dao.layThongKeTungNhanVien(10, 12, 2023, 2023, "CN0001");
        xuatBieuDoDuong(sampleData);
    }

    public void xuatBieuDoDuong(ArrayList<BangLuongCongNhan> data) {
        removeAll();
        setLayout(new BorderLayout());
        XYSeries series = new XYSeries("Dữ liệu");
        chuyenDuLieuBieuDoDuong(data, series);

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

    private void chuyenDuLieuBieuDoDuong(ArrayList<BangLuongCongNhan> data, XYSeries series) {
        for (BangLuongCongNhan point : data) {
            double xValue = Double.parseDouble(new SimpleDateFormat("MM").format(point.getNgayLap()));
            double yValue = point.getTongLuong();
            series.add(xValue, yValue);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu Đồ Đường");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BieuDoDuongPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
