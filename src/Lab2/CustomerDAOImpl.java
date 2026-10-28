package Lab2;

import Lab1.Customer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CustomerDAOImpl {

    private static final String URL = "jdbc:mysql://localhost:3306/igire-pay";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // 1. Create table in the database
    public boolean createTable() throws SQLException {
        String sql = """
                create table if not exists customer (
                    customer_id varchar(50) primary key,
                    customer_name varchar(50) not null,
                    email varchar(50) unique,
                    phone_number varchar(20) not null
                )
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        return true;
    }

    // 2. Save a Customer (Create / Register)
    public Customer save(Customer customer) throws SQLException {
        String sql = "insert into customer(customer_id, customer_name, email, phone_number) values(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getCustomerId());
            pstmt.setString(2, customer.getFullName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.executeUpdate();
        }
        return customer;
    }

    // 3. Find Customer by ID (Read / FindById)
    public Customer findById(String id) throws SQLException {
        String sql = "select customer_id, customer_name, email, phone_number from customer where customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getString("customer_id"));
                    customer.setFullName(rs.getString("customer_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    return customer;
                }
            }
        }
        return null;
    }

    // 4. Update Customer information (Update)
    public boolean update(Customer customer) throws SQLException {
        String sql = "update customer set customer_name = ?, email = ?, phone_number = ? where customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFullName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getCustomerId());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }

    // 5. Delete Customer (Delete)
    public boolean delete(String id) throws SQLException {
        String sql = "delete from customer where customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        return true;
    }

    // 6. Get all customers (Custom Query / FindAll)
    public Set<Customer> findAll() throws SQLException {
        Set<Customer> customers = new HashSet<>();
        String sql = "select customer_id, customer_name, email, phone_number from customer";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setFullName(rs.getString("customer_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customers.add(customer);
            }
        }
        return customers;
    }

    public static void main(String[] args) {
        CustomerDAOImpl dao = new CustomerDAOImpl();

        try {
            // 1. Try to create the table
            dao.createTable();
            System.out.println("Table created successfully!");

            // 2. Try to register a new Customer
            Customer customer = new Customer("C001", "Ange Umuhire", "ange@gmail.com", "0790000000");
            dao.save(customer);
            System.out.println("Customer saved successfully!");

            // 3. Try again to search customer by ID
            Customer found = dao.findById("C001");
            System.out.println("Customer found: " + found);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}