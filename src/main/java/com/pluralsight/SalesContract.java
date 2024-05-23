package com.pluralsight;

public class SalesContract extends Contract {

    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private static final double PROCESSING_FEE_UNDER_10000 = 295.00;
    private static final double PROCESSING_FEE_10000_AND_ABOVE = 495.00;

    private boolean financeOption;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;

    // Constructor
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {

        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;
        this.salesTaxAmount = vehicleSold.getPrice() * SALES_TAX_RATE;
        this.recordingFee = RECORDING_FEE;
        this.processingFee = vehicleSold.getPrice() < 10000 ? PROCESSING_FEE_UNDER_10000 : PROCESSING_FEE_10000_AND_ABOVE;

    }

    // Getters and setters
    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            int numberOfPayments;
            double interestRate;
            if (getVehicleSold().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            return Math.round(monthlyPayment * 100) / 100.0;
        } else {
            return 0.0;
        }
    }

}
