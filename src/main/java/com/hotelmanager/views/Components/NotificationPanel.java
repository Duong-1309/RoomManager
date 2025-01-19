package com.hotelmanager.views.Components;

import com.hotelmanager.controllers.NotificationController;
import com.hotelmanager.models.Notification;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationPanel extends JPanel {
    private NotificationController controller;
    private JPanel listPanel;

    public NotificationPanel() {
        this.controller = new NotificationController();
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
        listPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));

        // Add sample notifications
        loadNotifications();

        // Scroll pane for notifications
        JScrollPane scrollPane = new JScrollPane(
            listPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(245, 245, 250));
        scrollPane.getViewport().setBackground(new Color(245, 245, 250));

        scrollPane.setPreferredSize(new Dimension(800, 600));

        add(scrollPane, BorderLayout.CENTER);
    }

    void loadNotifications() {
        listPanel.removeAll();
        for (Notification notification : controller.getNotifications()) {
            NotificationItem item = new NotificationItem(notification);
            listPanel.add(item);
            listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between items
        }
    }
}