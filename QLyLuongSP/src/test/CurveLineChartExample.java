package test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class CurveLineChartExample extends JFrame {

    public CurveLineChartExample(String title, ArrayList<Vector<String>> data) {
        super(title);

        XYSeries series = new XYSeries("Dữ liệu");

        // Thêm dữ liệu vào series
        for (Vector<String> point : data) {
            double xValue = Double.parseDouble(point.get(0));
            double yValue = Double.parseDouble(point.get(1));
            series.add(xValue, yValue);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Biều Đồ Đường",  // Tiêu đề biểu đồ
                "X Axis",             // Nhãn trục x
                "Y Axis",             // Nhãn trục y
                dataset               // Dữ liệu
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYSplineRenderer renderer = new XYSplineRenderer();
        plot.setRenderer(renderer);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();

        // Tùy chỉnh đơn vị trục x và y
        TickUnits xTickUnits = new TickUnits();
        xTickUnits.add(new NumberTickUnit(1.0));
        xAxis.setStandardTickUnits(xTickUnits);

        TickUnits yTickUnits = new TickUnits();
        yTickUnits.add(new NumberTickUnit(1.0));
        yAxis.setStandardTickUnits(yTickUnits);

        // Hiển thị biểu đồ trong một JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Vector<String>> data = new ArrayList<>();
            // Thêm dữ liệu mẫu cho biểu đồ
            Vector<String> ds = new Vector<>();
            ds.add("1");
            ds.add("2");
            data.add(ds);
            Vector<String> ds1 = new Vector<>();
            ds1.add("3");
            ds1.add("4");
            data.add(ds1);
            
            Vector<String> ds2 = new Vector<>();
            ds2.add("3");
            ds2.add("4");
            data.add(ds2);
            
            Vector<String> ds3 = new Vector<>();
            ds3.add("2");
            ds3.add("1");
            data.add(ds3);
            

            CurveLineChartExample example = new CurveLineChartExample("Curve Line Chart Example", data);
            example.pack();
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
