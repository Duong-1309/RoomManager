package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hotelmanager.config.DatabaseConnection;

public class RoomListPanel extends JPanel {
    public RoomListPanel(List<String[]> rooms) {
        setLayout(new GridLayout(0, 4, 20, 20)); // Bố cục 4 cột, tự động thêm hàng
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm các phòng vào danh sách
        for (String[] room : rooms) {
            add(createRoomCard("Phòng " + room[0], room[1], room[0]));
        }
    }

    private JPanel createRoomCard(String roomName, String roomStatus, String roomNumber) {
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
        JButton detailButton = createDetailButton(roomNumber);

        // Thêm các thành phần vào card
        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách nhỏ
        card.add(statusLabel); // Trạng thái phòng
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(detailButton);

        return card;
    }

    private JButton createDetailButton(String roomNumber) {
        JButton button = new JButton("Chi tiết");
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(128, 0, 128));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(153, 51, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(128, 0, 128));
            }
        });

        button.addActionListener(e -> {
            List<String[]> tenants = getTenantsByRoom(roomNumber);
            if (tenants.isEmpty()) {
                JOptionPane.showMessageDialog(button, "Không có người thuê trong phòng " + roomNumber);
            } else {
                showTenantsDialog(roomNumber, tenants);
            }
        });

        return button;
    }

    private List<String[]> getTenantsByRoom(String roomNumber) {
        List<String[]> tenants = new ArrayList<>();
        String sql = """
            SELECT full_name, citizen_id, dob, temporary_registration, phone_number, start_date
            FROM rooms
            WHERE room = ?;
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tenants.add(new String[]{
                            rs.getString("full_name"), // Họ và Tên
                            rs.getString("citizen_id"), // Số CCCD
                            rs.getString("dob"), // Ngày sinh
                            rs.getString("temporary_registration"), // Đăng ký tạm trú
                            rs.getString("phone_number"), // Số điện thoại
                            rs.getString("start_date") // Ngày bắt đầu thuê
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    private void showTenantsDialog(String roomNumber, List<String[]> tenants) {
        JDialog dialog = new JDialog((Frame) null, "Danh sách người thuê - Phòng " + roomNumber, true);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Phòng " + roomNumber + " - Danh sách người thuê", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dialog.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"STT", "Họ và Tên", "Số CCCD", "Ngày sinh", "Đăng ký tạm trú", "Số điện thoại", "Ngày bắt đầu thuê"};
        Object[][] data = new Object[tenants.size()][columnNames.length];
        for (int i = 0; i < tenants.size(); i++) {
            String[] tenant = tenants.get(i);
            data[i][0] = i + 1;
            data[i][1] = tenant[0];
            data[i][2] = tenant[1];
            data[i][3] = tenant[2];
            data[i][4] = tenant[3];
            data[i][5] = tenant[4];
            data[i][6] = tenant[5];
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
