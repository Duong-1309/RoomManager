package com.hotelmanager.dao;

import com.hotelmanager.config.DatabaseConnection;
import com.hotelmanager.models.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private Connection connection;

    public NotificationDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications order by created_at desc";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Notification notification = new Notification(
                    rs.getInt("id"),
                    rs.getString("room_id"),  // null = 0
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("type"),
                    rs.getBoolean("is_read"),
                    rs.getTimestamp("created_at").toLocalDateTime()
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public boolean markAsRead(int notificationId) {
        String sql = "UPDATE notifications SET is_read = 1 WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, notificationId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // print errors
            e.printStackTrace();
            return false;
        }
    }
}