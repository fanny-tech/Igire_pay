package Lab2;

import Lab1.Account;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDAOImpl {

    private static final String URL = "jdbc:mysql://localhost:3306/igire-pay";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // 1. Create table in the database with PIN support
    public boolean createTable() throws SQLException {
        String sql = """
                create table if not exists account (
                    account_number varchar(50) primary key,
                    balance double not null,
                    pin varchar(10) default '0000'
                )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        return true;
    }

    // 2. Save a new Account with default PIN
    public Account save(Account account) throws SQLException {
        String sql = "insert into account(account_number, balance, pin) values(?, ?, '0000')";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getAccountNumber());
            pstmt.setDouble(2, account.getBalance());
            pstmt.executeUpdate();
        }
        return account;
    }

    // Overloaded save method to include custom PIN (Exercise 3.4)
    public Account saveWithPin(Account account, String pin) throws SQLException {
        String sql = "insert into account(account_number, balance, pin) values(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getAccountNumber());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setString(3, pin);
            pstmt.executeUpdate();
        }
        return account;
    }

    // 3. Find account by account number (Read / FindByAccountNumber)
    public Account findByAccountNumber(String accountNumber) throws SQLException {
        String sql = "select account_number, balance from account where account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    return account;
                }
            }
        }
        return null;
    }

    // 4. Update Account balance (Update)
    public boolean update(Account account) throws SQLException {
        String sql = "update account set balance = ? where account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getAccountNumber());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }

    // 5. Delete Account (Delete)
    public boolean delete(String accountNumber) throws SQLException {
        String sql = "delete from account where account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();
        }
        return true;
    }

    // 6. Get all accounts (Custom Query / FindAll)
    public Set<Account> findAll() throws SQLException {
        Set<Account> accounts = new HashSet<>();
        String sql = "select account_number, balance from account";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Account account = new Account();
                account.setAccountNumber(rs.getString("account_number"));
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    // 7. Verify Account PIN (Exercise 3.4 Authentication)
    public boolean verifyPin(String accountNumber, String enteredPin) throws SQLException {
        String sql = "select pin from account where account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String dbPin = rs.getString("pin");
                    return dbPin != null && dbPin.equals(enteredPin);
                }
            }
        }
        return false;
    }

    // 8. Update Account PIN (Exercise 3.4 Authentication)
    public boolean updatePin(String accountNumber, String newPin) throws SQLException {
        String sql = "update account set pin = ? where account_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPin);
            pstmt.setString(2, accountNumber);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }
}