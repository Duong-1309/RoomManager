package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomListPanel extends JPanel {
    public RoomListPanel(List<String> roomNumbers) {
        setLayout(new GridLayout(0, 4, 20, 20)); // 4 cột, tự động thêm hàng
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm các phòng
        for (String roomNumber : roomNumbers) {
            add(createRoomCard("Phòng " + roomNumber)); // Chỉ thêm "Phòng" tại đây
        }
    }

    private JPanel createRoomCard(String roomName) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Tên phòng
        JLabel nameLabel = new JLabel(roomName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút chi tiết
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

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(102, 102, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(82, 82, 255));
            }
        });

        return button;
    }
}
