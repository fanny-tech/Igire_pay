package Lab2;

import Lab1.Transaction;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TransactionDAOImpl implements TransactionDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/igire-pay";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public boolean createTable() throws SQLException {
        String sql = """
                create table if not exists transactions (
                    transaction_id varchar(50) primary key,
                    reference_id varchar(50),
                    amount double not null,
                    transaction_type varchar(50),
                    timestamp datetime
                )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        return true;
    }

    @Override
    public Transaction save(Transaction transaction) throws SQLException {
        String sql = "insert into transactions(transaction_id, reference_id, amount, transaction_type, timestamp) values(?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setString(2, transaction.getReferenceId());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getTransactionType());
            // Guhindura LocalDateTime ya Java ikajya muri SQL Timestamp
            pstmt.setTimestamp(5, Timestamp.valueOf(transaction.getTimestamp()));

            pstmt.executeUpdate();
        }
        return transaction;
    }

    @Override
    public Transaction findById(String id) throws SQLException {
        String sql = "select transaction_id, reference_id, amount, transaction_type, timestamp from transactions where transaction_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Transaction tx = new Transaction();

                    /*
                    tx.setTransactionId(rs.getString("transaction_id"));
                    tx.setReferenceId(rs.getString("reference_id"));
                    tx.setAmount(rs.getDouble("amount"));
                    tx.setTransactionType(rs.getString("transaction_type"));
                    tx.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                    */
                    return tx;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from transactions where transaction_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        return true;
    }

    @Override
    public Set<Transaction> findAll() throws SQLException {
        Set<Transaction> transactions = new HashSet<>();
        String sql = "select transaction_id, reference_id, amount, transaction_type, timestamp from transactions";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

            }
        }
        return transactions;
    }
}