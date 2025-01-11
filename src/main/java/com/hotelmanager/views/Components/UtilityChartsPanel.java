package com.hotelmanager.views.Components;

// Thêm các import cần thiết
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import com.hotelmanager.utils.Constants;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.awt.*;

public class UtilityChartsPanel extends JPanel {

    public UtilityChartsPanel() {
        setLayout(new GridLayout(2, 1, 0, 20));
        setBackground(Constants.BACKGROUND_COLOR);

        initCharts();
    }

    private void initCharts() {
        // Electricity Chart
        ChartPanel electricityChartPanel = createChartPanel(
                "Tiền điện các tháng",
                Constants.PRIMARY_COLOR,
                "Tiền điện"
        );

        // Water Chart
        ChartPanel waterChartPanel = createChartPanel(
                "Tiền nước các tháng",
                Constants.DANGER_COLOR,
                "Tiền nước"
        );

        add(electricityChartPanel);
        add(waterChartPanel);
    }

    private ChartPanel createChartPanel(String title, Color color, String series) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add sample data
        for (int i = 1; i <= 10; i++) {
            dataset.addValue(Math.random() * 100, series, "Tháng " + i);
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