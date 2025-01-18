package com.hotelmanager.views.Components;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import com.hotelmanager.controllers.DashboardController;
import com.hotelmanager.utils.Constants;

import java.util.List;

public class UtilityChartsPanel extends JPanel {
    private DashboardController dashboardController;

    public UtilityChartsPanel() {
        setLayout(new GridLayout(2, 1, 0, 20));
        setBackground(Constants.BACKGROUND_COLOR);

        dashboardController = new DashboardController();
        initCharts();
    }

    private void initCharts() {
        // Electricity Chart
        ChartPanel electricityChartPanel = createChartPanel(
                "Số điện các tháng (kWh)",
                Constants.PRIMARY_COLOR,
                "Số điện",
                true // true để lấy dữ liệu số điện
        );

        // Water Chart
        ChartPanel waterChartPanel = createChartPanel(
                "Số nước các tháng (m³)",
                Constants.DANGER_COLOR,
                "Số nước",
                false // false để lấy dữ liệu số nước
        );

        add(electricityChartPanel);
        add(waterChartPanel);
    }

    private ChartPanel createChartPanel(String title, Color color, String series, boolean isElectricity) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> usageData = dashboardController.getElectricAndWaterUsage();

        // Thêm dữ liệu thực từ bảng bills
        for (Object[] data : usageData) {
            int month = (int) data[0];
            int value = isElectricity ? (int) data[1] : (int) data[2]; // Điện hoặc Nước
            dataset.addValue(value, series, "Tháng " + month);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Tháng",
                "Giá trị",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizeChart(chart, color);
        return new ChartPanel(chart);
    }

    private void customizeChart(JFreeChart chart, Color color) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, color);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(false);

        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);

        chart.setBackgroundPaint(Color.WHITE);
    }
}
