package bankapp.dao;

import bankapp.model.Transaction;

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

public class TransactionDAO {
    private DataSource dataSource;

    public TransactionDAO() throws NamingException {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/bank_db");
    }

    // Add a new transaction to the database
    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (account_id, transaction_type, amount) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getAccountId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setBigDecimal(3, transaction.getAmount());

            stmt.executeUpdate();
        }
    }

    // Retrieve all transactions for a specific account
    public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE account_id = ? ORDER BY transaction_date DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setAccountId(rs.getInt("account_id"));
                    transaction.setTransactionType(rs.getString("transaction_type"));
                    transaction.setAmount(rs.getBigDecimal("amount"));
                    transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }
}
