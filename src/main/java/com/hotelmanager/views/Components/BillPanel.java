package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class BillPanel extends JPanel {
    private JTextField searchField;
    private JTabbedPane tabbedPane;
    private RoomListPanel pendingBillsPanel;
    private RoomListPanel paidBillsPanel;

    public BillPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
    }

    private void initComponents() {
        // Search panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create tabs
        pendingBillsPanel = new RoomListPanel();
        paidBillsPanel = new RoomListPanel();

        tabbedPane.addTab("Cần thu", pendingBillsPanel);
        tabbedPane.addTab("Đã thu", paidBillsPanel);

        // Customize tab appearance
        customizeTabbedPane();

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(245, 245, 250));

        // Search icon and text field
        JPanel searchBox = new JPanel();
        searchBox.setLayout(new BoxLayout(searchBox, BoxLayout.X_AXIS));
        searchBox.setBackground(Color.WHITE);
        searchBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Search icon
        JLabel searchIcon = new JLabel("🔍");
        searchBox.add(searchIcon);
        searchBox.add(Box.createRigidArea(new Dimension(5, 0)));

        // Search field
        searchField = new JTextField("Search order ID...");
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(250, 25));
        searchBox.add(searchField);

        searchPanel.add(searchBox);
        return searchPanel;
    }

    private void customizeTabbedPane() {
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(82, 82, 255));

        // Add custom styling
        UIManager.put("TabbedPane.selected", Color.WHITE);
        UIManager.put("TabbedPane.contentAreaColor", Color.WHITE);
        UIManager.put("TabbedPane.shadow", Color.WHITE);

        // Set custom border for tabs
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
    }
}