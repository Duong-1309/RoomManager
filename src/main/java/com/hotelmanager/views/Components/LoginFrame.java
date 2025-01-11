package com.hotelmanager.views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final Color PRIMARY_COLOR = new Color(82, 82, 255);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 250);

    public LoginFrame() {
        setTitle("Đăng nhập - Quản lý nhà trọ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        initComponents();
    }

    private void initComponents() {
        // Left panel with image
        JPanel leftPanel = createLeftPanel();
        add(leftPanel);

        // Right panel with login form
        JPanel rightPanel = createRightPanel();
        add(rightPanel);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_COLOR);

        // Add logo or welcome text
        JLabel welcomeLabel = new JLabel("Nhà trọ Văn Dương", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(60, 50, 60, 50));

        // Login title
        JLabel titleLabel = new JLabel("Đăng nhập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create text fields first
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Username field
        JPanel usernamePanel = createInputPanel("Tên đăng nhập", usernameField);

        // Password field
        JPanel passwordPanel = createInputPanel("Mật khẩu", passwordField);

        // Login button
        JButton loginButton = new JButton("Đăng nhập");
        styleButton(loginButton);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> handleLogin());

        // Add components with spacing
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(usernamePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(passwordPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(loginButton);

        return panel;
    }

    private JPanel createInputPanel(String labelText, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        // Configure text field
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(new Dimension(300, 40));
        field.setFont(new Font("Arial", Font.PLAIN, 14));

        // Custom border
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(300, 40));
        button.setMaximumSize(new Dimension(300, 40));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(102, 102, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // TODO: Add authentication logic here
        // For now, just show success and open main window
        if (!username.isEmpty() && !password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Đăng nhập thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);

            // Open main window
            openMainWindow();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openMainWindow() {
        // Hide login window
        this.dispose();

        // Open main window
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}