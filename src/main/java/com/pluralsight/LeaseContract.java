package com.pluralsight;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        double price = vehicleSold.getPrice().doubleValue();
        this.expectedEndingValue = price * 0.50;
        this.leaseFee = price * 0.07;
    }
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }
    public double getLeaseFee() {
        return leaseFee;
    }
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        double interestRate = 0.04;
        int months = 36;
        double monthlyRate = interestRate / 12;
        double total = getTotalPrice();
        return total * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
    }
}

