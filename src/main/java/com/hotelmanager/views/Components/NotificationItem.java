package com.hotelmanager.views.Components;

import com.hotelmanager.controllers.NotificationController;
import com.hotelmanager.models.Notification;
import com.hotelmanager.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class NotificationItem extends JPanel {
    private final Notification notification;
    private final NotificationController controller;

    public NotificationItem(Notification notification) {
        this.notification = notification;
        this.controller = new NotificationController();

        setLayout(new BorderLayout(15, 0));
        setBackground(notification.isRead() ?
                Constants.DEACTIVE_COLOR : Constants.PRIMARY_COLOR);
        setBorder(new EmptyBorder(15, 20, 15, 20));

        initComponents();
        setupListeners();
    }

    private void initComponents() {
        // Icon based on type
        JLabel iconLabel = new JLabel(getIconForType(notification.getType()));
        iconLabel.setForeground(notification.isRead() ? Color.BLACK : Color.WHITE);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        add(iconLabel, BorderLayout.WEST);

        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        contentPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(notification.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(notification.isRead() ? Color.BLACK : Color.WHITE);

        JLabel contentLabel = new JLabel(notification.getContent());
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contentLabel.setForeground(notification.isRead() ? Color.GRAY : Color.WHITE);

        contentPanel.add(titleLabel);
        contentPanel.add(contentLabel);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        if (!notification.isRead()) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleNotificationClick();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    setBackground(Constants.PRIMARY_COLOR.brighter());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(Constants.PRIMARY_COLOR);
                }
            });
        }
    }

    private void handleNotificationClick() {
        if (controller.markAsRead(notification.getId())) {
            // Tìm NotificationPanel cha và reload
            Container parent = getParent();
            while (parent != null && !(parent instanceof NotificationPanel)) {
                parent = parent.getParent();
            }
            if (parent instanceof NotificationPanel) {
                ((NotificationPanel) parent).loadNotifications();
            }
        }
    }

    private String getIconForType(String type) {
        return switch (type) {
            case "BILL" -> "💰";
            case "MAINTENANCE" -> "🔧";
            default -> "📢";
        };
    }
}