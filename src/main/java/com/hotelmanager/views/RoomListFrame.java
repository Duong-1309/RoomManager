package com.hotelmanager.views;

import com.hotelmanager.views.Components.RoomListPanel;
import com.hotelmanager.utils.Constants;
import com.hotelmanager.utils.SessionManager;
import com.hotelmanager.views.Components.SideBar;


import javax.swing.*;
import java.awt.*;

public class RoomListFrame extends JFrame {
    private SideBar sideBar;
    private RoomListPanel roomListPanel;

    public RoomListFrame() {
        // Kiểm tra xem đã đăng nhập chưa
        if (SessionManager.getCurrentUser() == null) {
            // Nếu chưa đăng nhập, quay lại màn hình login
            new LoginFrame().setVisible(true);
            this.dispose();
            return;
        }

        setTitle("Thông tin các phòng - Nhà trọ Văn Dương");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        sideBar = new SideBar("Thông tin các phòng");
        roomListPanel = new RoomListPanel();
        add(sideBar, BorderLayout.WEST);
        add(roomListPanel, BorderLayout.CENTER);
    }
}