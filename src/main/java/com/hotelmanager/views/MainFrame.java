package com.hotelmanager.views;

import com.hotelmanager.views.Components.BillDetailPanel;
import com.hotelmanager.views.Components.BillPanel;
import com.hotelmanager.views.Components.NotificationPanel;
import com.hotelmanager.views.Components.StatisticDashboardPanel;
import com.hotelmanager.views.Components.DashboardPanel;
import com.hotelmanager.utils.Constants;
import com.hotelmanager.views.Components.SideBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SideBar sideBar;
    private StatisticDashboardPanel statisticDashboardPanel;
    private BillPanel billPanel;
    private NotificationPanel notificationPanel;
    private BillDetailPanel billDetailPanel;
    private DashboardPanel dashboardPanel;

    public MainFrame() {
        setTitle("Nhà trọ Văn Dương");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Constants.BACKGROUND_COLOR);

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        sideBar = new SideBar();
        statisticDashboardPanel = new StatisticDashboardPanel();
        billPanel = new BillPanel();
        notificationPanel = new NotificationPanel();
        billDetailPanel = new BillDetailPanel();
        dashboardPanel = new DashboardPanel();



        add(sideBar, BorderLayout.WEST);
//        add(dashboardPanel, BorderLayout.CENTER);
//        add(billDetailPanel, BorderLayout.CENTER);
//        add(notificationPanel, BorderLayout.CENTER);
//        add(billPanel, BorderLayout.CENTER);
        add(statisticDashboardPanel, BorderLayout.CENTER);
    }
}