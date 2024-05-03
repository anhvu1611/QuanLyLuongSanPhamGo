package test;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class df extends JFrame {

    public df(String title) {
        super(title);

        // Tạo dataset với 3 cột và 3 cột con cho mỗi cột
        CategoryDataset dataset = createDataset();

        // Tạo biểu đồ cột
        JFreeChart chart = createChart(dataset);

        // Thêm biểu đồ vào Panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, "Category1", "Subcategory1");
        dataset.addValue(4.0, "Category1", "Subcategory2");
        dataset.addValue(3.0, "Category1", "Subcategory3");

        dataset.addValue(5.0, "Category2", "Subcategory1");
        dataset.addValue(7.0, "Category2", "Subcategory2");
        dataset.addValue(2.0, "Category2", "Subcategory3");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Example",      // Tiêu đề biểu đồ
                "Category",               // Nhãn trục x
                "Value",                  // Nhãn trục y
                dataset,                  // Dataset
                PlotOrientation.VERTICAL, // Hướng biểu đồ
                true,                     // Có hiển thị legend hay không
                true,
                false
        );

        // Thêm hiệu ứng màu sắc cho cột
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.RED);

        // Cấu hình khoảng cách giữa các cột con
        renderer.setItemMargin(0.05); // Đặt giá trị này để giữa các cột con nằm gần nhau hơn

        // Cấu hình trục x
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.25);

        // Cấu hình trục y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            df example = new df("Bar Chart Example");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
