package bankapp.controller;

import bankapp.dao.TransactionDAO;
import bankapp.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {
    private TransactionDAO transactionDAO;

    @Override
    public void init() throws ServletException {
        try {
            transactionDAO = new TransactionDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize TransactionDAO", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("addTransaction".equals(action)) {
                // Handle Add Transaction
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                String transactionType = request.getParameter("transactionType");
                double amount = Double.parseDouble(request.getParameter("amount"));

                // Populate Transaction object
                Transaction transaction = new Transaction();
                transaction.setAccountId(accountId);
                transaction.setTransactionType(transactionType);
                transaction.setAmount(BigDecimal.valueOf(amount));

                // Save transaction to the database
                transactionDAO.addTransaction(transaction);

                // Redirect to a confirmation page
                response.sendRedirect("transactionConfirmation.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error handling transaction action", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
