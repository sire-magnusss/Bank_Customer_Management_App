<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bankapp.model.Account" %>
<%
  // Retrieve customerId and accountId from query string
  String customerIdParam = request.getParameter("customerId");
  String accountIdParam = request.getParameter("accountId");

  if (customerIdParam == null || accountIdParam == null) {
    throw new IllegalArgumentException("Customer ID or Account ID is missing.");
  }

  int customerId = Integer.parseInt(customerIdParam);
  int accountId = Integer.parseInt(accountIdParam);

  // Set customerId and accountId as attributes
  request.setAttribute("customerId", customerId);
  request.setAttribute("accountId", accountId);
%>
<div class="container my-4">
  <div class="card">
    <div class="card-header bg-primary text-white">
      <h2 class="card-title text-center">Add Transaction for Account ID: <%= accountId %></h2>
    </div>
    <div class="card-body">
      <form action="TransactionControllerServlet" method="post">
        <input type="hidden" name="action" value="addTransaction">
        <input type="hidden" name="accountId" value="<%= accountId %>">
        <input type="hidden" name="customerId" value="<%= customerId %>">

        <div class="mb-3">
          <label for="transactionType" class="form-label">Transaction Type:</label>
          <select id="transactionType" name="transactionType" class="form-select" required>
            <option value="deposit">Deposit</option>
            <option value="withdrawal">Withdrawal</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="amount" class="form-label">Amount:</label>
          <input type="number" id="amount" name="amount" class="form-control" step="0.01" required>
        </div>

        <button type="submit" class="btn btn-success w-100">Submit Transaction</button>
      </form>
      <div class="mt-3 text-center">
        <a href="CustomerControllerServlet?action=viewTransactions&accountId=<%= accountId %>&customerId=<%= customerId %>" class="btn btn-secondary">Back to Transactions</a>
      </div>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
