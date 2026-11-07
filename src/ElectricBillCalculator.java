import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class ElectricBillCalculator {

    public static void main(String[] args) {

        try {

            File file = new File("Customer_Data.txt");
            Scanner input = new Scanner(file);

            if (!input.hasNext()) {
                input.close();
                JOptionPane.showMessageDialog(null, "Error: Customer ID is missing.");
                return;
            }

            String customerID = input.next();

            if (!input.hasNext()) {
                input.close();
                JOptionPane.showMessageDialog(null, "Error: First name is missing.");
                return;
            }

            String firstName = input.next();

            if (!input.hasNext()) {
                input.close();
                JOptionPane.showMessageDialog(null, "Error: Last name is missing.");
                return;
            }

            String lastName = input.next();

            if (!input.hasNextInt()) {
                input.close();
                JOptionPane.showMessageDialog(null, "Error: Invalid electricity usage.");
                return;
            }

            int usage = input.nextInt();

            if (!input.hasNext()) {
                input.close();
                JOptionPane.showMessageDialog(null, "Error: Payment status is missing.");
                return;
            }

            String paymentStatus = input.next();

            input.close();

            if (usage < 0) {

                JOptionPane.showMessageDialog(null,
                        "Error: Electricity usage cannot be negative.");

            } else if (!paymentStatus.equalsIgnoreCase("Y")
                    && !paymentStatus.equalsIgnoreCase("N")) {

                JOptionPane.showMessageDialog(null,
                        "Error: Payment status must be Y or N.");

            } else {

                double electricityCharge;

                if (usage <= 100) {

                    electricityCharge = usage * 0.10;

                } else if (usage <= 300) {

                    electricityCharge = 10.00
                            + (usage - 100) * 0.15;

                } else {

                    electricityCharge = 10.00 + 30.00
                            + (usage - 300) * 0.20;

                }

                double lateFee;

                if (paymentStatus.equalsIgnoreCase("Y")) {

                    lateFee = 15.00;

                } else {

                    lateFee = 0.00;

                }

                double totalAmount = electricityCharge + lateFee;

                String bill = "MONTHLY UTILITY BILL\n"
                        + "================================\n\n"
                        + "Customer ID: " + customerID + "\n"
                        + "Customer Name: " + firstName + " " + lastName + "\n"
                        + "Electricity Usage: " + usage + " kWh\n\n"
                        + String.format("Electricity Charge: $%.2f%n",
                        electricityCharge)
                        + String.format("Late-Payment Fee: $%.2f%n",
                        lateFee)
                        + "\n"
                        + String.format("TOTAL AMOUNT DUE: $%.2f%n",
                        totalAmount)
                        + "Payment Status: "
                        + (paymentStatus.equalsIgnoreCase("Y")
                        ? "Previous bill overdue"
                        : "Previous bill not overdue");

                JOptionPane.showMessageDialog(null, bill);

                PrintWriter output =
                        new PrintWriter("UtilityBillReport.txt");

                output.println(bill);
                output.close();

                JOptionPane.showMessageDialog(null,
                        "Bill saved successfully!");

            }

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null,
                    "Error: Customer_Data.txt was not found.");

        }

    }

}