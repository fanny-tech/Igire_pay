package Lab3;

import Lab1.Customer;
import Lab1.Account;
import Lab2.CustomerDAOImpl;
import Lab2.AccountDAOImpl;

import java.util.Scanner;

public class IgirePayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        AccountDAOImpl accountDAO = new AccountDAOImpl();

        try {
            // Create tables automatically if they don't exist
            customerDAO.createTable();
            accountDAO.createTable();
            System.out.println("Database tables initialized successfully!");
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }

        int choice = 0;
        do {
            System.out.println("\n=================================");
            System.out.println("    IGIRE PAY PAYMENT SYSTEM     ");
            System.out.println("=================================");
            System.out.println("1. Register Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. Create Account");
            System.out.println("4. View Account Balance");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. Export Account Statement (CSV)");
            System.out.println("8. Exit");
            System.out.print("Choose an option (1-7): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1: // Register Customer
                        System.out.print("Enter Customer ID: ");
                        String custId = scanner.nextLine();
                        System.out.print("Enter Full Name: ");
                        String fullName = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine();

                        try {
                            Customer customer = new Customer(custId, fullName, email, phone);
                            customerDAO.save(customer);
                            System.out.println("-> Success: Customer registered successfully!");
                        } catch (Exception e) {
                            System.out.println("-> Error: " + e.getMessage());
                        }
                        break;

                    case 2: // View Customer Details
                        System.out.print("Enter Customer ID to search: ");
                        String searchId = scanner.nextLine();
                        try {
                            Customer found = customerDAO.findById(searchId);
                            if (found != null) {
                                System.out.println("-> Found: " + found);
                            } else {
                                System.out.println("-> Notice: Customer not found!");
                            }
                        } catch (Exception e) {
                            System.out.println("-> Error: " + e.getMessage());
                        }
                        break;

                    case 3: // Create Account
                        System.out.print("Enter Account Number: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double initialBalance = scanner.nextDouble();
                        scanner.nextLine();

                        try {
                            if (initialBalance < 0) {
                                throw new InvalidAmountException("Initial balance cannot be negative!");
                            }
                            Account account = new Account(accNum, initialBalance);
                            accountDAO.save(account);
                            System.out.println("-> Success: Account created successfully!");
                        } catch (IgirePayException e) {
                            System.out.println("-> [" + e.getErrorCode() + "] " + e.getMessage() + " (Time: " + e.getTimestamp() + ")");
                        } catch (Exception e) {
                            System.out.println("-> Error: " + e.getMessage());
                        }
                        break;

                    case 4: // View Account Balance
                        System.out.print("Enter Account Number to check balance: ");
                        String checkAccNum = scanner.nextLine();
                        try {
                            Account acc = accountDAO.findByAccountNumber(checkAccNum);
                            if (acc != null) {
                                System.out.println("-> Account Number: " + acc.getAccountNumber());
                                System.out.println("-> Current Balance: $" + acc.getBalance());
                            } else {
                                throw new AccountNotFoundException("Account number " + checkAccNum + " does not exist!");
                            }
                        } catch (IgirePayException e) {
                            System.out.println("-> [" + e.getErrorCode() + "] " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("-> Error: " + e.getMessage());
                        }
                        break;

                    case 5: // Deposit Money with Exception Handling
                        System.out.print("Enter Account Number for deposit: ");
                        String depAccNum = scanner.nextLine();
                        System.out.print("Enter amount to deposit: ");
                        double depAmount = scanner.nextDouble();
                        scanner.nextLine();

                        try {
                            if (depAmount <= 0) {
                                throw new InvalidAmountException("Deposit amount must be greater than zero!");
                            }

                            Account acc = accountDAO.findByAccountNumber(depAccNum);
                            if (acc == null) {
                                throw new AccountNotFoundException("Account number " + depAccNum + " does not exist!");
                            }

                            acc.deposit(depAmount);
                            accountDAO.update(acc);
                            System.out.println("-> Success: Deposit completed. New Balance: $" + acc.getBalance());

                        } catch (IgirePayException e) {
                            System.out.println("-> [" + e.getErrorCode() + "] " + e.getMessage() + " (Time: " + e.getTimestamp() + ")");
                        } catch (Exception e) {
                            System.out.println("-> Database/SQL Error: " + e.getMessage());
                        }
                        break;

                    case 6: // Withdraw Money with Exception Handling
                        System.out.print("Enter Account Number for withdrawal: ");
                        String witAccNum = scanner.nextLine();
                        System.out.print("Enter amount to withdraw: ");
                        double witAmount = scanner.nextDouble();
                        scanner.nextLine();

                        try {
                            if (witAmount <= 0) {
                                throw new InvalidAmountException("Withdrawal amount must be greater than zero!");
                            }

                            Account acc = accountDAO.findByAccountNumber(witAccNum);
                            if (acc == null) {
                                throw new AccountNotFoundException("Account number " + witAccNum + " does not exist!");
                            }

                            if (acc.getBalance() < witAmount) {
                                throw new InsufficientBalanceException("Insufficient balance! Current balance is $" + acc.getBalance());
                            }

                            acc.withdraw(witAmount);
                            accountDAO.update(acc);
                            System.out.println("-> Success: Withdrawal completed. New Balance: $" + acc.getBalance());

                        } catch (IgirePayException e) {
                            System.out.println("-> [" + e.getErrorCode() + "] " + e.getMessage() + " (Time: " + e.getTimestamp() + ")");
                        } catch (Exception e) {
                            System.out.println("-> Database/SQL Error: " + e.getMessage());
                        }
                        break;

                    case 7: // Export Account Statement to CSV
                        System.out.print("Enter Account Number to export statement: ");
                        String csvAccNum = scanner.nextLine();
                        try {
                            Account acc = accountDAO.findByAccountNumber(csvAccNum);
                            if (acc != null) {
                                ReportService.exportAccountStatementToCSV(acc);
                            } else {
                                throw new AccountNotFoundException("Account number " + csvAccNum + " does not exist!");
                            }
                        } catch (IgirePayException e) {
                            System.out.println("-> [" + e.getErrorCode() + "] " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("-> Error: " + e.getMessage());
                        }
                        break;

                    case 8: // Exit (was 7)
                        System.out.println("Thank you for using IgirePay. Goodbye!");
                        break;

                    default:
                        System.out.println("-> Invalid choice! Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("-> Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }

        } while (choice != 7);

        scanner.close();
    }
}