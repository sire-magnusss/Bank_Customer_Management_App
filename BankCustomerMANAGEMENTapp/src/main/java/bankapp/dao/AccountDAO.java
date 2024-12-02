package bankapp.dao;

import bankapp.model.Account;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private DataSource dataSource;

    public AccountDAO() throws NamingException {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/bank_db");
    }

    // Add a new account to the database
    public void addAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account (account_type, balance, customer_id) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, account.getAccountType());
            stmt.setBigDecimal(2, BigDecimal.valueOf(account.getBalance())); // Convert double to BigDecimal
            stmt.setInt(3, account.getCustomerId());

            stmt.executeUpdate();
        }
    }


    // Retrieve all accounts for a specific customer
    public List<Account> getAccountsByCustomerId(int customerId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE customer_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setAccountId(rs.getInt("account_id"));
                    account.setAccountType(rs.getInt("account_type"));
                    account.setBalance(rs.getBigDecimal("balance").doubleValue()); // Convert BigDecimal to double
                    account.setCustomerId(rs.getInt("customer_id"));
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }
}
