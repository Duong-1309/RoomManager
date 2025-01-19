package com.hotelmanager.views.Components;

import com.hotelmanager.utils.Constants;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class StatisticDashboardPanel extends JPanel {
    private JTextField searchField;
    private UtilityChartsPanel chartsPanel;

    public StatisticDashboardPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Constants.BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
    }

    private void initComponents() {
        addSearchBar();
        addCharts();
    }

    private void addSearchBar() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Constants.BACKGROUND_COLOR);

        searchField = new JTextField("Search order ID...");
        searchField.setPreferredSize(new Dimension(300, 30));
        searchPanel.add(searchField);

        add(searchPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void addCharts() {
        chartsPanel = new UtilityChartsPanel();
        add(chartsPanel);
    }
}