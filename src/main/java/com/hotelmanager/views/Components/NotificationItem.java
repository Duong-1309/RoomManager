package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class NotificationItem extends JPanel {
    private Color defaultBackground = new Color(82, 82, 255);
    private Color hoverBackground = new Color(102, 102, 255);

    public NotificationItem(String message) {
        setLayout(new BorderLayout(15, 0));
        setBackground(defaultBackground);
        setBorder(new EmptyBorder(15, 20, 15, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        initComponents(message);
        setupHoverEffect();
    }

    private void initComponents(String message) {
        // Left side - Bell icon
        JLabel iconLabel = new JLabel("🔔");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        iconLabel.setForeground(Color.WHITE);
        add(iconLabel, BorderLayout.WEST);

        // Center - Message
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        add(messageLabel, BorderLayout.CENTER);

        // Right side - Arrow icon
        JLabel arrowLabel = new JLabel(">");
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 20));
        arrowLabel.setForeground(Color.WHITE);
        add(arrowLabel, BorderLayout.EAST);

        // Make the panel clickable
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void setupHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle click event
                System.out.println("Notification clicked");
            }
        });
    }
}