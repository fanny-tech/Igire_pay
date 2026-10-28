package Lab2;

import Lab1.Account;
import java.sql.SQLException;
import java.util.Set;

    public interface AccountDAO {
        boolean createTable() throws SQLException;
        Account save(Account account) throws SQLException;
        Account findById(String id) throws SQLException;
        boolean update(Account account) throws SQLException;
        boolean delete(String id) throws SQLException;
        Set<Account> findAll() throws SQLException;
    }

