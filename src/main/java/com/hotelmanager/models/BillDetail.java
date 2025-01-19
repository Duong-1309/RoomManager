package com.hotelmanager.models;

public class BillDetail {
    private int bill_id;
    private String room_id;
    private int month;
    private int year;
    private double rent_amount;
    private int electric_old_index;
    private int electric_new_index;
    private double electric_price;
    private double electric_amount;
    private int water_old_index;
    private int water_new_index;
    private double water_price;
    private double water_amount;
    private double garbage_fee;
    private double wifi_fee;
    private double parking_fee;
    private double total_amount;
    private String status;
    private int electric_usage;
    private int water_usage;

    public BillDetail(int bill_id, String room_id, int month, int year, double rent_amount, int electric_old_index, int electric_new_index, double electric_price, double electric_amount, int water_old_index, int water_new_index, double water_price, double water_amount, double garbage_fee, double wifi_fee, double parking_fee, double total_amount, String status, int electric_usage, int water_usage) {
        this.bill_id = bill_id;
        this.room_id = room_id;
        this.month = month;
        this.year = year;
        this.rent_amount = rent_amount;
        this.electric_old_index = electric_old_index;
        this.electric_new_index = electric_new_index;
        this.electric_price = electric_price;
        this.electric_amount = electric_amount;
        this.water_old_index = water_old_index;
        this.water_new_index = water_new_index;
        this.water_price = water_price;
        this.water_amount = water_amount;
        this.garbage_fee = garbage_fee;
        this.wifi_fee = wifi_fee;
        this.parking_fee = parking_fee;
        this.total_amount = total_amount;
        this.status = status;
        this.electric_usage = electric_usage;
        this.water_usage = water_usage;
    }

    public int getBillId() {
        return bill_id;
    }

    public String getRoomId() {
        return room_id;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getRentAmount() {
        return rent_amount;
    }

    public int getElectricOldIndex() {
        return electric_old_index;
    }

    public int getElectricNewIndex() {
        return electric_new_index;
    }

    public double getElectricPrice() {
        return electric_price;
    }

    public double getElectricAmount() {
        return electric_amount;
    }

    public int getWaterOldIndex() {
        return water_old_index;
    }

    public int getWaterNewIndex() {
        return water_new_index;
    }

    public double getWaterPrice() {
        return water_price;
    }

    public double getWaterAmount() {
        return water_amount;
    }

    public double getGarbageFee() {
        return garbage_fee;
    }

    public double getWifiFee() {
        return wifi_fee;
    }

    public double getParkingFee() {
        return parking_fee;
    }

    public double getTotalAmount() {
        return total_amount;
    }

    public String getStatus() {
        return status;
    }

    public int getElectricUsage() {
        return electric_usage;
    }

    public int getWaterUsage() {
        return water_usage;
    }
}