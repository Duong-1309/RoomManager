package com.hotelmanager.views.Components;

import com.hotelmanager.utils.Constants;
import com.hotelmanager.views.DashboardFrame;
import com.hotelmanager.views.NotificationFrame;
import com.hotelmanager.views.RoomListFrame;
import com.hotelmanager.views.BillFrame;
import com.hotelmanager.views.UtilitiesFrame;
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
    // add current path active
    private final String currentPath; // Lưu path hiện tại

    public SideBar(String currentPath) {
        this.currentPath = currentPath;
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

        if (text.equals(currentPath)) {
            menuItem.setBackground(Constants.PRIMARY_COLOR);
            label.setForeground(Color.WHITE);
        }

        menuItem.add(label);
        menuItem.add(Box.createHorizontalGlue());
        if (!text.equals(currentPath)) {
            addHoverEffectAndClick(menuItem, text);
        }

        return menuItem;
    }

    private void addHoverEffectAndClick(JPanel menuItem, String path) {
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(Constants.BACKGROUND_COLOR);
                menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToPath(path);
            }
        });
    }

    private void navigateToPath(String path) {
        // Đóng frame hiện tại
        Window currentWindow = SwingUtilities.getWindowAncestor(this);
        currentWindow.dispose();

        // Mở frame mới theo path
        SwingUtilities.invokeLater(() -> {
            JFrame frame = switch (path) {
                case "Trang chủ" -> new DashboardFrame();
                case "Thông báo" -> new NotificationFrame();
                case "Thông tin các phòng" -> new RoomListFrame();
                case "Hoá đơn" -> new BillFrame();
                case "Thống kê điện/nước" -> new UtilitiesFrame();
                default -> new DashboardFrame();
            };
            frame.setVisible(true);
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