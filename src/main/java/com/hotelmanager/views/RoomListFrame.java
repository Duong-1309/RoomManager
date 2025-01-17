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
        setLayout(new BorderLayout());
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Thêm thanh navigation (SideBar)
        SideBar sideBar = new SideBar("Thông tin các phòng");
        add(sideBar, BorderLayout.WEST);

        // Lấy danh sách các phòng từ database
        List<String> rentedRooms = getRentedRoomsFromDatabase();

        // Panel danh sách các phòng
        RoomListPanel roomListPanel = new RoomListPanel(rentedRooms);

        // Thêm thanh cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(roomListPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<String> getRentedRoomsFromDatabase() {
        List<String> rooms = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT room_number FROM room_details WHERE status = 'Được thuê'")) {

            while (rs.next()) {
                rooms.add(rs.getString("room_number"));
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
