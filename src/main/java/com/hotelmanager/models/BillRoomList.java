package com.hotelmanager.models;

public class BillRoomList {
    private int id;
    private String roomId;
    private String status;
    private int month;
    private int year;
    private double amount;

    public BillRoomList(int id, String roomId, String status, int month, int year, double amount) {
        this.id = id;
        this.roomId = roomId;
        this.status = status;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return month + "/" + year;
    }

    public String getAmount() {
        // convert amount to currency format
        return String.format("%,.0f", amount);
    }
}