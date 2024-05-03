package test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChartExample extends JFrame {

    public BarChartExample(String title) {
        super(title);

        // Tạo dữ liệu cho biểu đồ
        CategoryDataset dataset = createDataset();

        // Tạo biểu đồ từ dữ liệu
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ cột đơn giản",  // Tiêu đề biểu đồ
                "Thể loại",              // Nhãn trục x
                "Giá trị",               // Nhãn trục y
                dataset                 // Dữ liệu
        );

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Thêm dữ liệu vào biểu đồ
        dataset.addValue(12, "Dữ liệu 1", "Thể loại 1");
        dataset.addValue(24, "Dữ liệu 1", "Thể loại 2");
        dataset.addValue(36, "Dữ liệu 1", "Thể loại 3");

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BarChartExample example = new BarChartExample("Biểu đồ cột Java Swing");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
