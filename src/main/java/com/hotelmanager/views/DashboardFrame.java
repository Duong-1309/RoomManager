package com.hotelmanager.views;

import com.hotelmanager.views.Components.StatisticDashboardPanel;
import com.hotelmanager.utils.Constants;
import com.hotelmanager.utils.SessionManager;
import com.hotelmanager.views.Components.SideBar;


import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private SideBar sideBar;
    private StatisticDashboardPanel statisticDashboardPanel;

    public DashboardFrame() {
        // Kiểm tra xem đã đăng nhập chưa
        if (SessionManager.getCurrentUser() == null) {
            // Nếu chưa đăng nhập, quay lại màn hình login
            new LoginFrame().setVisible(true);
            this.dispose();
            return;
        }

        setTitle("Trang chủ - Nhà trọ Văn Dương");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        sideBar = new SideBar("Trang chủ");
        statisticDashboardPanel = new StatisticDashboardPanel();
        add(sideBar, BorderLayout.WEST);
        add(statisticDashboardPanel, BorderLayout.CENTER);
    }
}