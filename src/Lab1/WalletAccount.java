package Lab1;
    public class WalletAccount extends Account {
        private String phoneToken;

        public WalletAccount(String accountNumber, double balance, String phoneToken) {
            super(accountNumber, balance); // call cunstruct parent class
            this.phoneToken = phoneToken;
        }

        public String getPhoneToken() {
            return phoneToken;
        }

        public void setPhoneToken(String phoneToken) {
            this.phoneToken = phoneToken;
        }

        @Override
        public String toString() {
            return "WalletAccount [Account Number=" + getAccountNumber() +
                    ", Balance=" + getBalance() + ", Phone Token=" + phoneToken + "]";
        }
    }

