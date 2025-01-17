package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomListPanel extends JPanel {
    public RoomListPanel(List<String[]> rooms) {
        setLayout(new GridLayout(0, 4, 20, 20)); // Bố cục 4 cột, tự động thêm hàng
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm các phòng vào danh sách
        for (String[] room : rooms) {
            add(createRoomCard("Phòng " + room[0], room[1]));
        }
    }

    private JPanel createRoomCard(String roomName, String roomStatus) {
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

        // Trạng thái phòng
        JLabel statusLabel = new JLabel("Trạng thái: " + roomStatus);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút chi tiết
        JButton detailButton = createDetailButton();

        // Thêm các thành phần vào card
        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách nhỏ
        card.add(statusLabel); // Trạng thái phòng
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(detailButton);

        return card;
    }

    private JButton createDetailButton() {
        JButton button = new JButton("Chi tiết");
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(128, 0, 128)); // Màu tím (#800080)
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Xử lý sự kiện rê chuột qua nút
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(153, 51, 255)); // Màu sáng hơn khi rê chuột qua
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(128, 0, 128)); // Quay lại màu tím ban đầu
            }
        });

        // Xử lý sự kiện nhấn nút
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(button, "Bạn đã bấm nút Chi tiết!");
        });

        return button;
    }
}
