package com.hotelmanager.controllers;

import com.hotelmanager.dao.NotificationDAO;
import com.hotelmanager.models.Notification;

import java.util.List;

public class NotificationController {
    private NotificationDAO notificationDAO;
    private List<Notification> notifications;

    public NotificationController() {
        notificationDAO = new NotificationDAO();
        this.loadNotifications();
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void loadNotifications() {
        this.notifications = notificationDAO.getAllNotifications();
    }

    public boolean markAsRead(int notificationId) {
        if (notificationDAO.markAsRead(notificationId)) {
            loadNotifications();  // Refresh data
        }
        return false;
    }
}