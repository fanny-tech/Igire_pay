package Lab2;

import Lab1.Transaction; // Cyangwa aho class ya Transaction iherereye
import java.sql.SQLException;
import java.util.Set;

    public interface TransactionDAO {
        boolean createTable() throws SQLException;
        Transaction save(Transaction transaction) throws SQLException;
        Transaction findById(String id) throws SQLException;
        boolean delete(String id) throws SQLException;
        Set<Transaction> findAll() throws SQLException;
    }

