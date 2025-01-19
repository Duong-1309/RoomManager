package com.hotelmanager.views.Components;


import com.hotelmanager.controllers.BillController;
import com.hotelmanager.models.BillRoomList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BillPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private BillRoomPanel pendingBillsPanel;
    private BillRoomPanel paidBillsPanel;
    private BillController billController;

    public BillPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        billController = new BillController();
        initComponents();
    }

    private void initComponents() {
        // Tạo panel tìm kiếm (có thể thêm chức năng sau này)
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Tạo tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));

        // Lấy dữ liệu từ cơ sở dữ liệu
        List<BillRoomList> pendingRooms = billController.getBillRoomListByStatus("PENDING");
        List<BillRoomList> paidRooms = billController.getBillRoomListByStatus("PAID");

        pendingBillsPanel = new BillRoomPanel(pendingRooms);
        paidBillsPanel = new BillRoomPanel(paidRooms);

        // Revalidate và repaint trước khi thêm vào tab
        pendingBillsPanel.revalidate();
        pendingBillsPanel.repaint();
        paidBillsPanel.revalidate();
        paidBillsPanel.repaint();

        tabbedPane.addTab("Cần thu", pendingBillsPanel);
        tabbedPane.addTab("Đã thu", paidBillsPanel);

        customizeTabbedPane();

        add(tabbedPane, BorderLayout.CENTER);

        // Revalidate và repaint cả tabbedPane
        tabbedPane.revalidate();
        tabbedPane.repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(245, 245, 250));

        // Trường tìm kiếm (chỉ để hiển thị, chưa có chức năng)
        JTextField searchField = new JTextField("Tìm kiếm hóa đơn...");
        searchField.setPreferredSize(new Dimension(300, 30));
        searchPanel.add(searchField);

        return searchPanel;
    }

    private void customizeTabbedPane() {
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(82, 82, 255));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
    }
}
