package com.hotelmanager.views.Components;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.*;
import javax.swing.table.*;

public class CustomCalendar extends JPanel {
    private JLabel monthLabel;
    private JTable calendar;
    private JButton prevButton;
    private JButton nextButton;
    private LocalDate currentDate;

    public CustomCalendar() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        currentDate = LocalDate.now();
        initComponents();
    }

    private void initComponents() {
        // Header panel with month and navigation
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(Color.WHITE);

        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Navigation buttons
        prevButton = new JButton("<");
        nextButton = new JButton(">");
        styleButton(prevButton);
        styleButton(nextButton);

        JPanel navPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        navPanel.setBackground(Color.WHITE);
        navPanel.add(prevButton);
        navPanel.add(nextButton);

        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(navPanel, BorderLayout.EAST);

        // Calendar table
        calendar = new JTable();
        calendar.setRowHeight(30);
        calendar.setShowGrid(true);
        calendar.setGridColor(Color.LIGHT_GRAY);
        calendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        calendar.setDefaultRenderer(Object.class, new CalendarCellRenderer());

        // Add components
        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(calendar), BorderLayout.CENTER);

        // Update calendar
        updateCalendar();

        // Add listeners
        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
        });
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(new Color(82, 82, 255));
        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    private void updateCalendar() {
        // Update month label
        monthLabel.setText(currentDate.getMonth().toString() + " " + currentDate.getYear());

        // Create calendar model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add day of week headers
        model.addRow(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"});

        // Get first day of month
        LocalDate firstDay = currentDate.withDayOfMonth(1);
        int daysInMonth = currentDate.lengthOfMonth();
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        // Create calendar grid
        Object[][] calendarData = new Object[6][7];
        int day = 1;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < firstDayOfWeek - 1) {
                    calendarData[i][j] = "";
                } else if (day > daysInMonth) {
                    calendarData[i][j] = "";
                } else {
                    calendarData[i][j] = day++;
                }
            }
        }

        // Add data to model
        for (Object[] row : calendarData) {
            model.addRow(row);
        }

        calendar.setModel(model);
    }

    private class CalendarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            Component cell = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            cell.setFont(new Font("Arial", Font.PLAIN, 14));
            ((JLabel) cell).setHorizontalAlignment(SwingConstants.CENTER);

            // Style header row
            if (row == 0) {
                cell.setFont(new Font("Arial", Font.BOLD, 14));
                cell.setForeground(new Color(82, 82, 255));
            }

            // Style today's date
            if (value != null && !value.toString().isEmpty()) {
                int day = Integer.parseInt(value.toString());
                if (currentDate.getYear() == LocalDate.now().getYear() &&
                        currentDate.getMonth() == LocalDate.now().getMonth() &&
                        day == LocalDate.now().getDayOfMonth()) {
                    cell.setBackground(new Color(82, 82, 255));
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }
            }

            return cell;
        }
    }
}