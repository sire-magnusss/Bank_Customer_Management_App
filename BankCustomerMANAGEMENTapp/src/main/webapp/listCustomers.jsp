<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bankapp.model.Customer" %>
<%@ page import="bankapp.model.Account" %>
<%@ page import="bankapp.model.Transaction" %>
<%@ page import="bankapp.dao.AccountDAO" %>
<%@ page import="bankapp.dao.TransactionDAO" %>
<%@ page import="bankapp.dao.CustomerDAO" %>
<%
  // Fetch the list of customers from the DAO
  CustomerDAO customerDAO = new CustomerDAO();
  List<Customer> customers = customerDAO.getAllCustomers();

  // Create DAO instances for accounts and transactions
  AccountDAO accountDAO = new AccountDAO();
  TransactionDAO transactionDAO = new TransactionDAO();
%>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Accounts and Transactions</title>
</head>
<body>
<div class="container my-4">
  <h1 class="text-center mb-4">Customer List with Accounts and Transactions</h1>
  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Customer ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Phone</th>
      <th>Accounts</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (customers != null && !customers.isEmpty()) {
        for (Customer customer : customers) {
    %>
    <tr>
      <td><%= customer.getCustomerId() %></td>
      <td><%= customer.getFirstName() + " " + customer.getLastName() %></td>
      <td><%= customer.getEmail() %></td>
      <td><%= customer.getPhone() %></td>
      <td>
        <!-- Fetch accounts for this customer -->
        <%
          List<Account> accounts = accountDAO.getAccountsByCustomerId(customer.getCustomerId());
          if (accounts != null && !accounts.isEmpty()) {
        %>
        <ul>
          <%
            for (Account account : accounts) {
          %>
          <li>
            <b>ID:</b> <%= account.getAccountId() %>,
            <b>Type:</b>
            <%
              switch (account.getAccountType()) {
                case 1: out.print("Current"); break;
                case 2: out.print("Savings"); break;
                case 3: out.print("Call"); break;
                case 4: out.print("Fixed Deposit"); break;
                default: out.print("Unknown");
              }
            %>,
            <b>Balance:</b> $<%= account.getBalance() %>
            <ul>
              <%
                List<Transaction> transactions = transactionDAO.getTransactionsByAccountId(account.getAccountId());
                if (transactions != null && !transactions.isEmpty()) {
              %>
              <li><b>Transactions:</b></li>
              <%
                for (Transaction transaction : transactions) {
              %>
              <li>
                <b>ID:</b> <%= transaction.getTransactionId() %>,
                <b>Type:</b> <%= transaction.getTransactionType() %>,
                <b>Amount:</b> $<%= transaction.getAmount() %>,
                <b>Date:</b> <%= transaction.getTransactionDate() %>
              </li>
              <%
                }
              } else {
              %>
              <li>No transactions found.</li>
              <%
                }
              %>
            </ul>
          </li>
          <%
            }
          %>
        </ul>
        <%
        } else {
        %>
        No accounts found.
        <%
          }
        %>
      </td>
      <td>
        <a href="CustomerControllerServlet?action=viewAccounts&customerId=<%= customer.getCustomerId() %>" class="btn btn-primary btn-sm mb-1">View Accounts</a><br>
        <a href="CustomerControllerServlet?action=editForm&customerId=<%= customer.getCustomerId() %>" class="btn btn-warning btn-sm mb-1">Edit</a><br>
        <a href="addAccount.jsp?customerId=<%= customer.getCustomerId() %>" class="btn btn-success btn-sm mb-1">Add Account</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="6" class="text-center">No customers found.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
