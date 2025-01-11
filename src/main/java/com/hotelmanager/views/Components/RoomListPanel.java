package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class RoomListPanel extends JPanel {
    public RoomListPanel() {
        setLayout(new GridLayout(0, 4, 20, 20)); // 4 columns, dynamic rows
        setBackground(new Color(245, 245, 250));
        setBorder(new EmptyBorder(20, 0, 20, 0));

        // Add sample rooms
        addRoom("Phòng 1A");
        addRoom("Phòng 2C");
        addRoom("Phòng 4D");
        addRoom("Phòng 5A");
        addRoom("Phòng 6A");
        addRoom("Phòng 7A");
        addRoom("Phòng 8A");
    }

    private void addRoom(String roomName) {
        JPanel roomCard = createRoomCard(roomName);
        add(roomCard);
    }

    private JPanel createRoomCard(String roomName) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Room name
        JLabel nameLabel = new JLabel(roomName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Detail button
        JButton detailButton = createDetailButton();

        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(detailButton);

        return card;
    }

    private JButton createDetailButton() {
        JButton button = new JButton("Chi tiết");
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(82, 82, 255));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(100, 30));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(102, 102, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(82, 82, 255));
            }
        });

        return button;
    }
}