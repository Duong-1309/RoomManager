package com.hotelmanager.views;

import com.hotelmanager.views.Components.RoomListPanel;
import com.hotelmanager.views.Components.SideBar;
import com.hotelmanager.config.DatabaseConnection;
import com.hotelmanager.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomListFrame extends JFrame {
    public RoomListFrame() {
        setTitle("Thông tin các phòng");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Thanh navigation
        SideBar sideBar = new SideBar("Thông tin các phòng");
        add(sideBar, BorderLayout.WEST);

        // Panel chính (chứa tiêu đề và danh sách phòng)
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Constants.BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin các phòng");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Danh sách phòng
        List<String[]> rooms = getRoomsFromDatabase();
        RoomListPanel roomListPanel = new RoomListPanel(rooms);

        // Thêm danh sách phòng vào ScrollPane
        JScrollPane scrollPane = new JScrollPane(roomListPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm mainPanel vào Center
        add(mainPanel, BorderLayout.CENTER);
    }

    private List<String[]> getRoomsFromDatabase() {
        List<String[]> rooms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT room_number, status FROM room_details")) {

            while (rs.next()) {
                rooms.add(new String[]{rs.getString("room_number"), rs.getString("status")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomListFrame().setVisible(true));
    }
}
