package com.hotelmanager;

import com.hotelmanager.views.LoginFrame;
import com.hotelmanager.views.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
//            new LoginFrame().setVisible(true);
        });
    }
}
