package Lab1;

import java.util.*;

    public class IgireManager {
        private List<Customer> customers = new ArrayList<>();
        private List<Transaction> transactions = new ArrayList<>();
        private Set<String> processedReferenceIds = new HashSet<>(); // Iyi ni yo ibuza duplicate!

        // add new list
        public void addCustomer(Customer customer) {
            customers.add(customer);
            System.out.println("add customer successfully: " + customer.getFullName());
        }

        // Transaction void Duplicate (Idempotency)
        public boolean processTransaction(Transaction tx) {

            if (processedReferenceIds.contains(tx.getReferenceId())) {
                System.out.println(" ERROR: Duplicate transaction! RefID: " + tx.getReferenceId());
                return false;
            }

            // if Transaction is new
            processedReferenceIds.add(tx.getReferenceId());
            transactions.add(tx);
            System.out.println(" SUCCESS: Transaction! RefID: " + tx.getReferenceId());
            return true;
        }

        // display all Transaction
        public void viewAllTransactions() {
            System.out.println("============= (TRANSACTIONS) ===");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

