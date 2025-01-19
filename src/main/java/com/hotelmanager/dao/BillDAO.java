package com.hotelmanager.dao;

import com.hotelmanager.config.DatabaseConnection;
import com.hotelmanager.models.BillRoomList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private Connection connection;

    public BillDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<BillRoomList> getBillRoomListByStatus(String status) {
        List<BillRoomList> billRoomLists = new ArrayList<>();
        String sql = "SELECT bills.bill_id as id, room_details.room_number as room_number, bills.status as status, bills.month as month, bills.year as year, bills.total_amount as amount FROM room_details join bills on room_details.room_number = bills.room_id WHERE bills.status = ?";;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BillRoomList billRoomList = new BillRoomList(
                    rs.getInt("id"),
                    rs.getString("room_number"),
                    rs.getString("status"),
                    rs.getInt("month"),
                    rs.getInt("year"),
                    // fix amout is read from total_amount
                    rs.getDouble("amount")
                );
                billRoomLists.add(billRoomList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billRoomLists;
    }
}