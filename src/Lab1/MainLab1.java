package Lab1;
    public class MainLab1 {
        public static void main(String[] args) {
            IgireManager manager = new IgireManager();

            Customer c1 = new Customer("C001", "Uwimana Francoise", "fanny@gmail.com", "0780000000");
            manager.addCustomer(c1);

            WalletAccount myWallet = new WalletAccount("ACC999", 50000, "0780000000");
            System.out.println(myWallet);


            Transaction tx1 = new Transaction("T01", "REF-100", 5000, "DEPOSIT");
            Transaction tx2 = new Transaction("T02", "REF-100", 5000, "DEPOSIT");
            manager.processTransaction(tx1);
            manager.processTransaction(tx2);


            manager.viewAllTransactions();
        }
    }

