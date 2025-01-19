package com.hotelmanager.views.Components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.border.*;
import java.text.NumberFormat;
import java.util.Locale;

public class BillDetailPanel extends JPanel {
    private JTable billTable;
    private JTextArea noteArea;
    private final Color PRIMARY_COLOR = new Color(82, 82, 255);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 250);

    public BillDetailPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
    }

    private void initComponents() {
        // Header Panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Table Panel
        add(createTablePanel(), BorderLayout.CENTER);

        // Bottom Panel with buttons and note
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);

        // Title
        JLabel titleLabel = new JLabel("Hoá đơn tiền trọ tháng n");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Room number
        JLabel roomLabel = new JLabel("Phòng 1A");
        roomLabel.setFont(new Font("Arial", Font.BOLD, 20));

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(roomLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(BACKGROUND_COLOR);

        // Create table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add columns
        model.addColumn("STT");
        model.addColumn("Loại tiền cần đóng");
        model.addColumn("Giá");

        // Add sample data
        Object[][] data = {
                {1, "Tiền trọ", 2500000},
                {2, "Tiền điện", 1000000},
                {3, "Tiền nước", 300000},
                {4, "Tiền rác", 50000},
                {5, "Tiền wifi", 50000},
                {6, "Phí gửi xe tháng", 200000}
        };

        for (Object[] row : data) {
            model.addRow(row);
        }

        // Create table
        billTable = new JTable(model);
        customizeTable();

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(billTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private void customizeTable() {
        // Set custom renderer for the price column
        billTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            private final NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Number) {
                    value = formatter.format(value) + " đ";
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        // Customize table appearance
        billTable.setRowHeight(35);
        billTable.setFont(new Font("Arial", Font.PLAIN, 14));
        billTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        billTable.setShowGrid(true);
        billTable.setGridColor(Color.LIGHT_GRAY);

        // Set column widths
        TableColumnModel columnModel = billTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // STT
        columnModel.getColumn(1).setPreferredWidth(200); // Loại tiền
        columnModel.getColumn(2).setPreferredWidth(150); // Giá
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        // Create buttons
        JButton paidButton = createStyledButton("Đã thanh toán", PRIMARY_COLOR);
        JButton deleteButton = createStyledButton("Xoá", PRIMARY_COLOR);
        JButton exportButton = createStyledButton("Xuất file excel", PRIMARY_COLOR);

        buttonsPanel.add(paidButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(exportButton);

        // Note panel
        JPanel notePanel = new JPanel(new BorderLayout(10, 10));
        notePanel.setBackground(BACKGROUND_COLOR);

        noteArea = new JTextArea("Bấm vào sẽ chuyển sang hoá đơn đã thanh toán");
        noteArea.setFont(new Font("Arial", Font.PLAIN, 14));
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        noteArea.setRows(3);
        noteArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        notePanel.add(noteArea, BorderLayout.CENTER);

        // Add components to bottom panel
        bottomPanel.add(buttonsPanel, BorderLayout.NORTH);
        bottomPanel.add(notePanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 35));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }
}