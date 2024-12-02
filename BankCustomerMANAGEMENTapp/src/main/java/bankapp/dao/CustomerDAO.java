package bankapp.dao;

import bankapp.model.Customer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private DataSource dataSource;

    // Constructor to initialize the DataSource via JNDI lookup
    public CustomerDAO() throws NamingException {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/bank_db");
    }

    // Add a new customer to the database
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the PreparedStatement
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());

            // Execute the query
            stmt.executeUpdate();
        }
    }

    // Retrieve customer by ID
    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    return customer;
                }
            }
        }
        return null;
    }

    // Update customer details
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE customer_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getCustomerId());

            stmt.executeUpdate();
        }
    }

    // Remove a customer and their related accounts and transactions by ID
    public void removeCustomerById(int customerId) throws SQLException {
        String deleteTransactions = "DELETE FROM transaction WHERE account_id IN (SELECT account_id FROM account WHERE customer_id = ?)";
        String deleteAccounts = "DELETE FROM account WHERE customer_id = ?";
        String deleteCustomer = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement stmt1 = conn.prepareStatement(deleteTransactions);
                 PreparedStatement stmt2 = conn.prepareStatement(deleteAccounts);
                 PreparedStatement stmt3 = conn.prepareStatement(deleteCustomer)) {

                // Delete transactions
                stmt1.setInt(1, customerId);
                stmt1.executeUpdate();

                // Delete accounts
                stmt2.setInt(1, customerId);
                stmt2.executeUpdate();

                // Delete customer
                stmt3.setInt(1, customerId);
                stmt3.executeUpdate();

                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                conn.rollback(); // Rollback if any error occurs
                throw e;
            }
        }
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Search for customers by name or email
    public List<Customer> searchCustomers(String searchTerm) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String query = "%" + searchTerm + "%";
            stmt.setString(1, query);
            stmt.setString(2, query);
            stmt.setString(3, query);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setFirstName(rs.getString("first_name"));
                    customer.setLastName(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    customers.add(customer);
                }
            }
        }
        return customers;
    }
}
