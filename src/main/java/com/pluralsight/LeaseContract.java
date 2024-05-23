package com.pluralsight;

public class LeaseContract extends Contract{

    private static final double LEASE_RATE = 0.04;
    private static final int LEASE_TERM = 36;
    private static final double VALUE_PERCENTAGE = 0.50;
    private static final double LEASE_FEE_PERCENTAGE = 0.07;

    private double expectedEndingValue;
    private double leaseFee;

    // Constructor
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * VALUE_PERCENTAGE;
        this.leaseFee = vehicleSold.getPrice() * LEASE_FEE_PERCENTAGE;
    }

    // Getters for additional fields
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        // Total price is calculated as the initial price minus the expected ending value plus the lease fee
        return (getVehicleSold().getPrice() - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = LEASE_TERM;
        double interestRate = LEASE_RATE / 12; // Monthly interest rate
        double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
        return Math.round(monthlyPayment * 100) / 100.0;
    }

}
