package Lab1;


    public class SavingsAccount extends Account {
        private double interestRate;

        public SavingsAccount(String accountNumber, double balance, double interestRate) {
            super(accountNumber, balance);
            this.interestRate = interestRate;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(double interestRate) {
            this.interestRate = interestRate;
        }

        //  Polymorphism
        @Override
        public void withdraw(double amount) {
            double fee = 50;
            double totalAmount = amount + fee;

            if (amount > 0 && getBalance() >= totalAmount) {
                setBalance(getBalance() - totalAmount);
                System.out.println("-> withdraw Savings: " + amount + " : " + fee + ")");
            } else {
                System.out.println("-> Error: withdraw not working, insufficient amount!");
            }
        }

        @Override
        public String toString() {
            return "SavingsAccount [Account Number=" + getAccountNumber() +
                    ", Balance=" + getBalance() + ", Interest Rate=" + interestRate + "]";
        }
    }

