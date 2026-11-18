
package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public void saveContract(Contract contract) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("contracts.csv", true))) {

            Vehicle vehicle = contract.getVehicleSold();

            String line = contract.getDate() + "|" +
                    contract.getCustomerName() + "|" +
                    contract.getCustomerEmail() + "|" +
                    vehicle.getVin() + "|" +
                    vehicle.getYear() + "|" +
                    vehicle.getMake() + "|" +
                    vehicle.getModel() + "|" +
                    vehicle.getVehicleType() + "|" +
                    vehicle.getColor() + "|" +
                    vehicle.getOdometer() + "|" +
                    vehicle.getPrice();

            if (contract instanceof SalesContract) {

                SalesContract sale = (SalesContract) contract;

                line = "SALE|" + line + "|" +
                        String.format("%.2f", sale.getSalesTaxAmount()) + "|" +
                        String.format("%.2f", sale.getRecordingFee()) + "|" +
                        String.format("%.2f", sale.getProcessingFee()) + "|" +
                        String.format("%.2f", sale.getTotalPrice()) + "|" +
                        (sale.isFinance() ? "YES" : "NO") + "|" +
                        String.format("%.2f", sale.getMonthlyPayment());

            } else if (contract instanceof LeaseContract) {

                LeaseContract lease = (LeaseContract) contract;

                line = "LEASE|" + line + "|" +
                        String.format("%.2f", lease.getExpectedEndingValue()) + "|" +
                        String.format("%.2f", lease.getLeaseFee()) + "|" +
                        String.format("%.2f", lease.getTotalPrice()) + "|" +
                        String.format("%.2f", lease.getMonthlyPayment());
            } else {
                throw new IllegalArgumentException("Unknown contract type");
            }

            writer.write(line);
            writer.newLine();

            System.out.println("Contract saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
