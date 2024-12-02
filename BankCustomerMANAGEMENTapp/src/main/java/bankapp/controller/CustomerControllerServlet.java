package bankapp.controller;

import bankapp.dao.AccountDAO;
import bankapp.dao.CustomerDAO;
import bankapp.dao.TransactionDAO;
import bankapp.model.Account;
import bankapp.model.Customer;
import bankapp.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/CustomerControllerServlet")
public class CustomerControllerServlet extends HttpServlet {
    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize the DAOs
            customerDAO = new CustomerDAO();
            accountDAO = new AccountDAO();
            transactionDAO = new TransactionDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DAOs", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                // Handle Add Customer
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                // Populate Customer object
                Customer customer = new Customer();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setPhone(phone);

                // Save the customer to the database
                customerDAO.addCustomer(customer);

                // Set customer as a request attribute for confirmation page
                request.setAttribute("customer", customer);

                // Forward to confirmation.jsp
                request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
            } else if ("remove".equals(action)) {
                // Handle Remove Customer
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                customerDAO.removeCustomerById(customerId);

                // Redirect to a success page or display a success message
                response.sendRedirect("removeConfirmation.jsp");
            } else if ("edit".equals(action)) {
                // Handle Edit Customer
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                // Update Customer
                Customer customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setPhone(phone);
                customerDAO.updateCustomer(customer);

                // Redirect to confirmation page
                response.sendRedirect("editConfirmation.jsp");
            } else if ("editForm".equals(action)) {
                // Retrieve Customer and forward to editCustomer.jsp
                String customerIdParam = request.getParameter("customerId");
                if (customerIdParam == null || customerIdParam.isEmpty()) {
                    throw new ServletException("Customer ID is missing.");
                }

                int customerId = Integer.parseInt(customerIdParam);
                Customer customer = customerDAO.getCustomerById(customerId);

                if (customer == null) {
                    throw new ServletException("Customer with ID " + customerId + " not found.");
                }

                // Set the customer in request scope
                request.setAttribute("customer", customer);

                // Forward to editCustomer.jsp
                request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
            } else if ("list".equals(action)) {
                // Handle List All Customers
                List<Customer> customers = customerDAO.getAllCustomers();
                request.setAttribute("customers", customers);

                // Forward to listCustomers.jsp
                request.getRequestDispatcher("/listCustomers.jsp").forward(request, response);
            } else if ("viewAccounts".equals(action)) {
                // Handle View Accounts
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                List<Account> accounts = accountDAO.getAccountsByCustomerId(customerId);

                // Set attributes for the JSP
                request.setAttribute("customerId", customerId);
                request.setAttribute("accounts", accounts);

                // Forward to viewAccounts.jsp
                request.getRequestDispatcher("/viewAccounts.jsp").forward(request, response);
            } else if ("viewTransactions".equals(action)) {
                // Handle View Transactions
                String accountIdParam = request.getParameter("accountId");
                String customerIdParam = request.getParameter("customerId");

                // Check for missing parameters
                if (accountIdParam == null || accountIdParam.isEmpty() || customerIdParam == null || customerIdParam.isEmpty()) {
                    throw new ServletException("Required parameters are missing.");
                }

                int accountId = Integer.parseInt(accountIdParam);
                int customerId = Integer.parseInt(customerIdParam);
                List<Transaction> transactions = transactionDAO.getTransactionsByAccountId(accountId);

                // Set attributes for the JSP
                request.setAttribute("accountId", accountId);
                request.setAttribute("customerId", customerId); // Pass customerId to JSP
                request.setAttribute("transactions", transactions);

                // Forward to viewTransactions.jsp
                request.getRequestDispatcher("/viewTransactions.jsp").forward(request, response);
            } else if ("listWithAccountsAndTransactions".equals(action)) {
                // Handle Listing Customers with Accounts and Transactions
                List<Customer> customers = customerDAO.getAllCustomers();

                // For each customer, fetch accounts and their transactions
                for (Customer customer : customers) {
                    List<Account> accounts = accountDAO.getAccountsByCustomerId(customer.getCustomerId());
                    for (Account account : accounts) {
                        List<Transaction> transactions = transactionDAO.getTransactionsByAccountId(account.getAccountId());
                        account.setTransactions(transactions); // Assume Account model has a `setTransactions` method
                    }
                    customer.setAccounts(accounts); // Assume Customer model has a `setAccounts` method
                }

                request.setAttribute("customers", customers);

                // Forward to the JSP for displaying customers with accounts and transactions
                request.getRequestDispatcher("/listCustomers.jsp").forward(request, response);
            } else if ("search".equals(action)) {
                // Handle Search Customers
                String searchTerm = request.getParameter("searchTerm");
                List<Customer> customers = customerDAO.searchCustomers(searchTerm);

                // Set search results as a request attribute
                request.setAttribute("customers", customers);

                // Forward to searchResults.jsp
                request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            throw new ServletException("Error handling customer action", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to doPost
        doPost(request, response);
    }
}
