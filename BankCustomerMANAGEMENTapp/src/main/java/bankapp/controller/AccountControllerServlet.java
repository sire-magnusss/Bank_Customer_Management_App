package bankapp.controller;

import bankapp.dao.AccountDAO;
import bankapp.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AccountControllerServlet")
public class AccountControllerServlet extends HttpServlet {
    private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        try {
            accountDAO = new AccountDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize AccountDAO", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("addAccount".equals(action)) {
                // Handle Add Account
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                int accountType = Integer.parseInt(request.getParameter("accountType"));
                double balance = Double.parseDouble(request.getParameter("balance"));

                // Create Account object
                Account account = new Account();
                account.setCustomerId(customerId);
                account.setAccountType(accountType);
                account.setBalance(balance);

                // Save account to the database
                accountDAO.addAccount(account);

                // Redirect to a confirmation page
                response.sendRedirect("addAccountConfirmation.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error handling account action", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
