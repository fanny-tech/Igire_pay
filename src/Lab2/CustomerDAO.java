package Lab2;

import Lab1.Customer;
import java.sql.SQLException;
import java.util.Set;

public interface CustomerDAO {
    boolean createTable() throws SQLException;
    Customer save(Customer customer) throws SQLException;
    Customer findById(String id) throws SQLException;
    boolean update(Customer customer) throws SQLException;
    boolean delete(String id) throws SQLException;
    Set<Customer> findAll() throws SQLException;
}
