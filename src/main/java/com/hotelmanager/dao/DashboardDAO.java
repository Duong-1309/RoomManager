package com.hotelmanager.dao;

import com.hotelmanager.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {
    private Connection connection;

    public DashboardDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public ArrayList<Integer> getSummaryRoom() {
        ArrayList<Integer> summary = new ArrayList<>();
        String sql = "SELECT COUNT(*) as total, SUM(CASE WHEN status = 'Được thuê' THEN 1 ELSE 0 END) as booked, SUM(CASE WHEN status = 'Trống' THEN 1 ELSE 0 END) as available FROM room_details;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                summary.add(rs.getInt("total"));
                summary.add(rs.getInt("booked"));
                summary.add(rs.getInt("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return summary;
    }

    public Integer countTenants() {
        Integer cTenants = 0;
        String sql = "SELECT COUNT(*) as total FROM rooms;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                cTenants = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cTenants;
    }

    public List<Object[]> getElectricAndWaterUsage() {
        List<Object[]> usageData = new ArrayList<>();
        String sql = """
            SELECT 
                month, 
                SUM(electric_new_index - electric_old_index) AS total_electric_usage,
                SUM(water_new_index - water_old_index) AS total_water_usage
            FROM bills
            GROUP BY month
            ORDER BY month;
        """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usageData.add(new Object[]{
                        rs.getInt("month"),
                        rs.getInt("total_electric_usage"),
                        rs.getInt("total_water_usage")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usageData;
    }
}
