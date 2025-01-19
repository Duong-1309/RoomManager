package com.hotelmanager.controllers;

import com.hotelmanager.dao.BillDAO;
import com.hotelmanager.dao.DashboardDAO;
import com.hotelmanager.models.BillDetail;
import com.hotelmanager.models.BillRoomList;

import java.util.List;

public class BillController {
    private BillDAO billDAO;

    public BillController() {
        billDAO = new BillDAO();
    }

    public List<BillRoomList> getBillRoomListByStatus(String status) {
        return billDAO.getBillRoomListByStatus(status);
    }

    public BillDetail getBillDetailById(int billId) {
        return billDAO.getBillDetailById(billId);
    }
}
