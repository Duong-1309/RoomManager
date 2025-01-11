package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationPanel extends JPanel {
    private List<NotificationItem> notificationItems;
    private JPanel listPanel;

    public NotificationPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
    }

    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Thông báo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Notifications list
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(245, 245, 250));

        // Add sample notifications
        addNotifications();

        // Scroll pane for notifications
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(245, 245, 250));
        scrollPane.getViewport().setBackground(new Color(245, 245, 250));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void addNotifications() {
        String[] rooms = {"1A", "1B", "2C", "3F", "2D"};
        for (String room : rooms) {
            NotificationItem item = new NotificationItem("Phòng " + room + " tới tháng đóng tiền nhà tháng 12 năm 2024");
            listPanel.add(item);
            listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between items
        }
    }
}