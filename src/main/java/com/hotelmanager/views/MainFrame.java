package com.hotelmanager.views;

import com.hotelmanager.views.Components.DashboardPanel;
import com.hotelmanager.utils.Constants;
import com.hotelmanager.utils.SessionManager;
import com.hotelmanager.views.Components.SideBar;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SideBar sideBar;
    private DashboardPanel dashboardPanel;

    public MainFrame() {
        // Kiểm tra xem đã đăng nhập chưa
        if (SessionManager.getCurrentUser() == null) {
            // Nếu chưa đăng nhập, quay lại màn hình login
            new LoginFrame().setVisible(true);
            this.dispose();
            return;
        }

        setTitle("Nhà trọ Văn Dương");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        sideBar = new SideBar("Trang chủ");
        dashboardPanel = new DashboardPanel();
        add(sideBar, BorderLayout.WEST);
        add(dashboardPanel, BorderLayout.CENTER);
    }
}