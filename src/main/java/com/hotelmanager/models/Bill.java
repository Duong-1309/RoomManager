package hotelmanager.models;

public class Bill {
    private String month;
    private double electricityAmount;
    private double waterAmount;

    public Bill(String month, double electricityAmount, double waterAmount) {
        this.month = month;
        this.electricityAmount = electricityAmount;
        this.waterAmount = waterAmount;
    }

    // Getters and setters
    public String getMonth() { return month; }
    public double getElectricityAmount() { return electricityAmount; }
    public double getWaterAmount() { return waterAmount; }
}