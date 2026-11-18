package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
        this.recordingFee = 100;
        double price = vehicleSold.getPrice().doubleValue();
        this.salesTaxAmount = price * 0.05;
        if (price < 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice().doubleValue();
        return price + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0;
        }

        double price = getVehicleSold().getPrice().doubleValue();
        double interestRate;
        int months;

        if (price >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }

        double monthlyRate = interestRate / 12;
        double total = getTotalPrice();

        return total * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
    }
}
