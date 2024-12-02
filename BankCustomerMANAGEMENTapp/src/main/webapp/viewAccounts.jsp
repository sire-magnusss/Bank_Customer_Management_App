<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bankapp.model.Account" %>
<%
  List<Account> accounts = (List<Account>) request.getAttribute("accounts");
  int customerId = (int) request.getAttribute("customerId");
%>
<!DOCTYPE html>
<html>
<head>
  <title>View Accounts</title>
</head>
<body>
<div class="container my-5">
  <h1 class="text-center">Accounts for Customer ID: <%= customerId %></h1>
  <table class="table table-striped table-bordered mt-4">
    <thead class="thead-dark">
    <tr>
      <th>Account ID</th>
      <th>Account Type</th>
      <th>Balance</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (accounts != null && !accounts.isEmpty()) {
        for (Account account : accounts) {
    %>
    <tr>
      <td><%= account.getAccountId() %></td>
      <td><%
        switch (account.getAccountType()) {
          case 1: out.print("Current"); break;
          case 2: out.print("Savings"); break;
          case 3: out.print("Call"); break;
          case 4: out.print("Fixed Deposit"); break;
          default: out.print("Unknown");
        }
      %></td>
      <td><%= account.getBalance() %></td>
      <td>
        <a href="CustomerControllerServlet?action=viewTransactions&accountId=<%= account.getAccountId() %>&customerId=<%= customerId %>"
           class="btn btn-info btn-sm">View Transactions</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="4" class="text-center">No accounts found for this customer.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <div class="text-center mt-4">
    <a href="listCustomers.jsp" class="btn btn-secondary">Back to Customer List</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
