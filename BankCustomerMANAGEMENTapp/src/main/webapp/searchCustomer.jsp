<%@ page import="java.util.List" %>
<%@ page import="bankapp.model.Customer" %>
<%@ page import="bankapp.model.Account" %>
<%@ page import="bankapp.dao.CustomerDAO" %>
<%@ page import="bankapp.dao.AccountDAO" %>
<%
  String searchTerm = request.getParameter("search");
  List<Customer> customers = null;
  if (searchTerm != null) {
    CustomerDAO customerDAO = new CustomerDAO();
    customers = customerDAO.searchCustomers(searchTerm);
  }

  AccountDAO accountDAO = new AccountDAO(); // For fetching accounts
%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Search Customers</title>
</head>
<body>
<div class="container my-4">
  <h1 class="text-primary text-center">Search Customers</h1>
  <form method="get" action="searchCustomer.jsp" class="d-flex my-4">
    <input type="text" name="search" placeholder="Search by name or email" class="form-control me-2" required>
    <button type="submit" class="btn btn-primary">Search</button>
  </form>
  <%
    if (customers != null) {
  %>
  <h2 class="text-secondary mt-4">Search Results</h2>
  <ul class="list-group mt-3">
    <%
      for (Customer customer : customers) {
    %>
    <li class="list-group-item">
      <strong>Name:</strong> <%= customer.getFirstName() + " " + customer.getLastName() %><br>
      <strong>Email:</strong> <%= customer.getEmail() %><br>
      <strong>Phone:</strong> <%= customer.getPhone() %><br>
      <!-- Fetch and display associated accounts -->
      <strong>Accounts:</strong>
      <%
        List<Account> accounts = accountDAO.getAccountsByCustomerId(customer.getCustomerId());
        if (accounts != null && !accounts.isEmpty()) {
      %>
      <ul>
        <%
          for (Account account : accounts) {
        %>
        <li>
          <b>Account ID:</b> <%= account.getAccountId() %>,
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
          <b>Balance:</b> <%= account.getBalance() %>
        </li>
        <%
          }
        %>
      </ul>
      <%
      } else {
      %>
      <span>No accounts found.</span>
      <%
        }
      %>
    </li>
    <%
      }
    %>
  </ul>
  <%
  } else if (searchTerm != null) {
  %>
  <p class="text-danger mt-4">No customers found for the search term "<%= searchTerm %>".</p>
  <%
    }
  %>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
