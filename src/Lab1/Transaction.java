package Lab1;

import java.time.LocalDateTime;

    public class Transaction {
        private String transactionId;
        private String referenceId;
        private double amount;
        private String transactionType; //
        private LocalDateTime timestamp;

        public Transaction(String transactionId, String referenceId, double amount, String transactionType) {
            this.transactionId = transactionId;
            this.referenceId = referenceId;
            this.amount = amount;
            this.transactionType = transactionType;
            this.timestamp = LocalDateTime.now();
        }

        public Transaction() {

        }

        public String getTransactionId() {
            return transactionId; }
        public String getReferenceId() {
            return referenceId; }
        public double getAmount() {
            return amount; }
        public String getTransactionType() {
            return transactionType; }
        public LocalDateTime getTimestamp() {
            return timestamp; }

        @Override
        public String toString() {
            return "Transaction [ID=" + transactionId + ", RefID=" + referenceId +
                    ", Amount=" + amount + ", Type=" + transactionType + ", Time=" + timestamp + "]";
        }
    }

