package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String FILE_NAME = "contracts.csv";

    // Method to save contract by appending to the contracts file
    public void saveContract(Contract contract) {
        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));

            StringBuilder stringBuilder = new StringBuilder();

            if (contract instanceof SalesContract) {

                SalesContract salesContract = (SalesContract) contract;

                stringBuilder.append("SALE|")
                        .append(salesContract.getDate()).append("|")
                        .append(salesContract.getCustomerName()).append("|")
                        .append(salesContract.getCustomerEmail()).append("|")
                        .append(vehicleToString(salesContract.getVehicleSold())).append("|")
                        .append(salesContract.getSalesTaxAmount()).append("|")
                        .append(salesContract.getRecordingFee()).append("|")
                        .append(salesContract.getProcessingFee()).append("|")
                        .append(salesContract.getTotalPrice()).append("|")
                        .append(salesContract.isFinanceOption() ? "YES" : "NO").append("|")
                        .append(salesContract.getMonthlyPayment());

            } else if (contract instanceof LeaseContract) {

                LeaseContract leaseContract = (LeaseContract) contract;

                stringBuilder.append("LEASE|")
                        .append(leaseContract.getDate()).append("|")
                        .append(leaseContract.getCustomerName()).append("|")
                        .append(leaseContract.getCustomerEmail()).append("|")
                        .append(vehicleToString(leaseContract.getVehicleSold())).append("|")
                        .append(leaseContract.getExpectedEndingValue()).append("|")
                        .append(leaseContract.getLeaseFee()).append("|")
                        .append(leaseContract.getTotalPrice()).append("|")
                        .append(leaseContract.getMonthlyPayment());

            }

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to convert vehicle information to string
    private String vehicleToString(Vehicle vehicle) {
        return vehicle.getVin() + "|" +
                vehicle.getYear() + "|" +
                vehicle.getMake() + "|" +
                vehicle.getModel() + "|" +
                vehicle.getVehicleType() + "|" +
                vehicle.getColor() + "|" +
                vehicle.getOdometer() + "|" +
                vehicle.getPrice();
    }

}
