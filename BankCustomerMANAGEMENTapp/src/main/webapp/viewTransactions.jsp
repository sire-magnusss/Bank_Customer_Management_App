<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bankapp.model.Transaction" %>
<%
  int accountId = (int) request.getAttribute("accountId");
  int customerId = (int) request.getAttribute("customerId"); // Ensure customerId is passed
  List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
  <title>View Transactions</title>
</head>
<body>
<div class="container my-5">
  <h1 class="text-center">Transactions for Account ID: <%= accountId %></h1>
  <table class="table table-striped table-bordered mt-4">
    <thead class="thead-dark">
    <tr>
      <th>Transaction ID</th>
      <th>Transaction Type</th>
      <th>Amount</th>
      <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (transactions != null && !transactions.isEmpty()) {
        for (Transaction transaction : transactions) {
    %>
    <tr>
      <td><%= transaction.getTransactionId() %></td>
      <td><%= transaction.getTransactionType() %></td>
      <td><%= transaction.getAmount() %></td>
      <td><%= transaction.getTransactionDate() %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="4" class="text-center">No transactions found for this account.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <div class="text-center mt-4">
    <a href="addTransaction.jsp?accountId=<%= accountId %>&customerId=<%= customerId %>"
       class="btn btn-primary">Add Transaction</a>
    <a href="CustomerControllerServlet?action=viewAccounts&customerId=<%= customerId %>"
       class="btn btn-secondary">Back to Accounts</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
