package Lab3;

import Lab1.Account;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class ReportService {

        // Method to export Account Statement to a CSV file
        public static void exportAccountStatementToCSV(Account account) {
            String filename = "statement_" + account.getAccountNumber() + ".csv";

            try (FileWriter writer = new FileWriter(filename)) {
                // Write CSV Headers
                writer.append("=========================================\n");
                writer.append("           IGIRE PAY STATEMENT           \n");
                writer.append("=========================================\n");
                writer.append("Export Date," + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
                writer.append("Account Number," + account.getAccountNumber() + "\n");
                writer.append("Current Balance ($)," + account.getBalance() + "\n");
                writer.append("-----------------------------------------\n");
                writer.append("Status,SUCCESS\n");

                System.out.println("-> Success: Statement exported successfully to file: " + filename);
            } catch (IOException e) {
                System.out.println("-> Error: Failed to export CSV report - " + e.getMessage());
            }
        }
    }

