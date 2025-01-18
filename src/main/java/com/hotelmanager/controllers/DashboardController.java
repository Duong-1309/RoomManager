package com.hotelmanager.controllers;

import com.hotelmanager.dao.DashboardDAO;

import java.util.List;

public class DashboardController {
    private DashboardDAO dashboardDAO;

    public DashboardController() {
        dashboardDAO = new DashboardDAO();
    }

    public List<Integer> getSummaryRoom() {
        return dashboardDAO.getSummaryRoom();
    }

    public Integer countTenants() {
        return dashboardDAO.countTenants();
    }

    public List<Object[]> getElectricAndWaterUsage() {
        return dashboardDAO.getElectricAndWaterUsage();
    }
}
