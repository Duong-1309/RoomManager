package com.hotelmanager.views.Components;

import com.hotelmanager.utils.Constants;
import com.hotelmanager.utils.SessionManager;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class SideBar extends JPanel {
    private String[] menuItems = {
            "Trang chủ",
            "Thông báo",
            "Thông tin các phòng",
            "Hoá đơn",
            "Thống kê điện/nước"
    };

    public SideBar() {
        setPreferredSize(new Dimension(Constants.SIDEBAR_WIDTH, 0));
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        initComponents();
    }

    private void initComponents() {
        addLogo();
        addMenuItems();
        addUserInfo();
    }

    private void addLogo() {
        JLabel logoLabel = new JLabel("Nhà trọ " + SessionManager.getCurrentUser().getUsername());
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(logoLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void addMenuItems() {
        for (String menuItem : menuItems) {
            JPanel menuItemPanel = createMenuItem(menuItem);
            add(menuItemPanel);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }

    private JPanel createMenuItem(String text) {
        JPanel menuItem = new JPanel();
        menuItem.setLayout(new BoxLayout(menuItem, BoxLayout.X_AXIS));
        menuItem.setBackground(Color.WHITE);
        menuItem.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        menuItem.add(label);
        menuItem.add(Box.createHorizontalGlue());

        if (text.equals("Thống kê điện/nước")) {
            menuItem.setBackground(Constants.PRIMARY_COLOR);
            label.setForeground(Color.WHITE);
        }

        addMenuItemHoverEffect(menuItem, text);
        return menuItem;
    }

    private void addMenuItemHoverEffect(JPanel menuItem, String text) {
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!text.equals("Thống kê điện/nước")) {
                    menuItem.setBackground(Constants.BACKGROUND_COLOR);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!text.equals("Thống kê điện/nước")) {
                    menuItem.setBackground(Color.WHITE);
                }
            }
        });
    }

    private void addUserInfo() {
        add(Box.createVerticalGlue());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel userIcon = new JLabel("👤");
        JLabel userName = new JLabel(SessionManager.getCurrentUser().getUsername());
        JLabel userEmail = new JLabel("hotelmanager@chunha");

        userPanel.add(userIcon);
        userPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        userPanel.add(userName);

        add(userPanel);
    }
}