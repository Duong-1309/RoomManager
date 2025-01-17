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
        calendar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

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
        DefaultTableModel model = new DefaultTableModel();

        // Add columns for days of week
        for (String day : new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}) {
            model.addColumn(day);
        }

        // Get first day of month
        LocalDate firstDay = currentDate.withDayOfMonth(1);
        int daysInMonth = currentDate.lengthOfMonth();
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        // Create calendar data
        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> week = new Vector<>();
        int day = 1;

        // Add empty cells for days before the first day of month
        for (int i = 1; i < firstDayOfWeek; i++) {
            week.add("");
        }

        // Add days of the month
        while (day <= daysInMonth) {
            week.add(day);
            if ((firstDayOfWeek + day - 1) % 7 == 0 || day == daysInMonth) {
                data.add(new Vector<>(week));
                week.clear();
            }
            day++;
        }

        // Fill remaining cells in the last week
        while (week.size() < 7 && !week.isEmpty()) {
            week.add("");
        }
        if (!week.isEmpty()) {
            data.add(week);
        }

        model.setDataVector(data, new Vector<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")));
        calendar.setModel(model);

        // Adjust column widths
        TableColumnModel columnModel = calendar.getColumnModel();
        for (int i = 0; i < calendar.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(50);
        }
    }

    private class CalendarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 14));

            // Reset background and foreground
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);

            if (value != null) {
                String text = value.toString();
                if (!text.isEmpty()) {
                    try {
                        int day = Integer.parseInt(text);
                        // Highlight today's date
                        if (currentDate.getYear() == LocalDate.now().getYear() &&
                                currentDate.getMonth() == LocalDate.now().getMonth() &&
                                day == LocalDate.now().getDayOfMonth()) {
                            label.setBackground(new Color(82, 82, 255));
                            label.setForeground(Color.WHITE);
                        }
                    } catch (NumberFormatException e) {
                        // This is a header
                        label.setFont(new Font("Arial", Font.BOLD, 14));
                        label.setForeground(new Color(82, 82, 255));
                    }
                }
            }

            return label;
        }
    }
}