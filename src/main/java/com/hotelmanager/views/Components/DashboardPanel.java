 package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.border.*;

import com.hotelmanager.controllers.DashboardController;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

public class DashboardPanel extends JPanel {
    private final Color PRIMARY_COLOR = new Color(82, 82, 255);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 250);
    private final Color PINK_COLOR = new Color(255, 102, 153);

    DashboardController dashboardController;

    public DashboardPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        dashboardController = new DashboardController();

        initComponents();
    }

    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Tổng quát");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Main content panel with grid layout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // load data
        List<Integer> summaryRoom = dashboardController.getSummaryRoom();

        // Stats cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statsPanel.setBackground(BACKGROUND_COLOR);
        statsPanel.add(createStatsCard("Số phòng ", + summaryRoom.get(0) + " Phòng", true));
        statsPanel.add(createStatsCard("Số phòng được thuê ", + summaryRoom.get(1) + " phòng", false));

        statsPanel.add(createStatsCard("Số người đang thuê", + dashboardController.countTenants() + " Người", false));

        // Add components to grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        contentPanel.add(statsPanel, gbc);

        // Pie chart
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        contentPanel.add(createChartPanel(summaryRoom.get(1), summaryRoom.get(2)), gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createStatsCard(String title, String value, boolean isOccupied) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Text panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));

        textPanel.add(titleLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(valueLabel);

        card.add(textPanel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createChartPanel(Integer cBooked, Integer cAvailable) {
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        Integer total = cBooked + cAvailable;
        Integer bookedPercent = Math.round((float)cBooked / total * 100);
        Integer availablePercent = Math.round((float)cAvailable / total * 100);

        dataset.setValue("Phòng đã ở", bookedPercent);
        dataset.setValue("Phòng trống", availablePercent);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                null,  // No title
                dataset,
                true,  // Include legend
                true,
                false
        );

        // Customize chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Phòng đã ở", PRIMARY_COLOR);
        plot.setSectionPaint("Phòng trống", PINK_COLOR);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelGenerator(null);  // Remove labels

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return chartPanel;
    }
}