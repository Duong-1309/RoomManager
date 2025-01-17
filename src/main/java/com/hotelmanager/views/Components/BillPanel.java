package com.hotelmanager.views.Components;

import com.hotelmanager.config.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private RoomListPanel pendingBillsPanel;
    private RoomListPanel paidBillsPanel;

    public BillPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initComponents();
    }

    private void initComponents() {
        // Search panel (optional, can be expanded later)
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));

        // Fetch room data and create panels
        List<String> pendingRooms = getPendingBillsRoomsFromDatabase();
        List<String> paidRooms = getPaidBillsRoomsFromDatabase();

        pendingBillsPanel = new RoomListPanel(pendingRooms);
        paidBillsPanel = new RoomListPanel(paidRooms);

        tabbedPane.addTab("Cần thu", pendingBillsPanel);
        tabbedPane.addTab("Đã thu", paidBillsPanel);

        // Customize tab appearance
        customizeTabbedPane();

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(245, 245, 250));

        // Search field (optional functionality)
        JTextField searchField = new JTextField("Tìm kiếm hóa đơn...");
        searchField.setPreferredSize(new Dimension(300, 30));
        searchPanel.add(searchField);

        return searchPanel;
    }

    private void customizeTabbedPane() {
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(82, 82, 255));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
    }

    // Fetch pending bills data from the database
    private List<String> getPendingBillsRoomsFromDatabase() {
        List<String> rooms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT room_number FROM room_details WHERE bill_status = 'pending'")) {

            while (rs.next()) {
                rooms.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Fetch paid bills data from the database
    private List<String> getPaidBillsRoomsFromDatabase() {
        List<String> rooms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT room_number FROM room_details WHERE bill_status = 'paid'")) {

            while (rs.next()) {
                rooms.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
