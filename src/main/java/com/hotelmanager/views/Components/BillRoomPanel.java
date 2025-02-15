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
import com.hotelmanager.controllers.BillController;
import com.hotelmanager.models.BillDetail;
import com.hotelmanager.models.BillRoomList;


public class BillRoomPanel extends JPanel {
    private List<BillRoomList> rooms;
    private BillController billController;

    public BillRoomPanel(List<BillRoomList> rooms) {
        setLayout(new GridLayout(0, 4, 20, 20)); // Bố cục 4 cột, tự động thêm hàng
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        billController = new BillController();
        this.rooms = rooms;

        // Thêm các phòng vào danh sách
        for (BillRoomList room : rooms) {
            add(createRoomCard(room));
        }
        // Force revalidate và repaint
        revalidate();
        repaint();
    }

    private JPanel createRoomCard(BillRoomList room) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Tên phòng
        JLabel nameLabel = new JLabel("Phòng " + room.getRoomId());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Trạng thái phòng
        JLabel statusLabel = new JLabel("Trạng thái: " + room.getStatus());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Số tiền cần thu
        // format totalAmount to currency

        JLabel amountLabel = new JLabel("Số tiền: " + room.getAmount() + " VNĐ");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel date = new JLabel("Tháng: " + room.getDate());
        date.setFont(new Font("Arial", Font.PLAIN, 14));
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút chi tiết
        JButton detailButton = createDetailButton(room.getId());

        // Thêm các thành phần vào card
        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0,  5))); // Khoảng cách nhỏ
        card.add(statusLabel); // Trạng thái phòng
        card.add(Box.createRigidArea(new Dimension(0, 7)));
        card.add(amountLabel); // Số tiền cần thu
        card.add(Box.createRigidArea(new Dimension(0, 7)));
        card.add(date);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(detailButton);

        return card;
    }

    private JButton createDetailButton(int bill_id) {
        JButton button = new JButton("Chi tiết hoá đơn");
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
            BillDetail billDetail = billController.getBillDetailById(bill_id);

            if (billDetail == null) {
                JOptionPane.showMessageDialog(button, "Không tìm thấy hoá đơn " + bill_id);
            } else {
                showBillDetailDialog(billDetail);
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

    private void showBillDetailDialog(BillDetail billDetail) {
        JDialog dialog = new JDialog((Frame) null, "Chi tiết hoá đơn - Phòng " + billDetail.getRoomId() + " tháng " + billDetail.getMonth() + "/" + billDetail.getYear(), true);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Chi tiết hoá đơn - Phòng " + billDetail.getRoomId() + " tháng " + billDetail.getMonth() + "/" + billDetail.getYear());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dialog.add(titleLabel, BorderLayout.NORTH);


        String[] columnNames = {"STT", "Chi tiết", "Số lượng", "Giá", "Số tiền"};
        Object[][] data = new Object[][] {
            {"1", "Tiền Phòng", "1", formatCurrency(billDetail.getRentAmount()), formatCurrency(billDetail.getRentAmount())},
            {"2", "Tiền điện", billDetail.getElectricNewIndex() - billDetail.getElectricOldIndex(), formatCurrency(billDetail.getElectricPrice()),formatCurrency(billDetail.getElectricAmount())},
            {"3", "Tiền nước", billDetail.getWaterNewIndex() - billDetail.getWaterOldIndex(), formatCurrency(billDetail.getWaterPrice()), formatCurrency(billDetail.getWaterAmount())},
            {"4", "Tiền rác", "1", formatCurrency(billDetail.getGarbageFee()), formatCurrency(billDetail.getGarbageFee())},
            {"5", "Tiền wifi", "1", formatCurrency(billDetail.getWifiFee()), formatCurrency(billDetail.getWifiFee())},
            {"6", "Tiền gửi xe", "1", formatCurrency(billDetail.getParkingFee()), formatCurrency(billDetail.getParkingFee())},
            {"", "", "", "Tổng cộng", formatCurrency(billDetail.getTotalAmount())}
        };

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

    private String formatCurrency(double amount) {
        return String.format("%,.0f", amount);
    }
}
