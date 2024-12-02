<%@ page import="bankapp.model.Customer" %>
<%
  // Retrieve the customer ID from the request
  int customerId = Integer.parseInt(request.getParameter("customerId"));
%>
<%@ include file="header.jsp" %>
<div class="container my-4">
  <h1 class="text-center">Add Account</h1>
  <p class="text-muted text-center">For Customer ID: <strong><%= customerId %></strong></p>
  <form action="AccountControllerServlet" method="post" class="mt-4">
    <input type="hidden" name="action" value="addAccount">
    <input type="hidden" name="customerId" value="<%= customerId %>">

    <div class="mb-3">
      <label for="accountType" class="form-label">Account Type:</label>
      <select id="accountType" name="accountType" class="form-select" required>
        <option value="1">Current</option>
        <option value="2">Savings</option>
        <option value="3">Call</option>
        <option value="4">Fixed Deposit</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="balance" class="form-label">Initial Balance:</label>
      <input type="number" id="balance" name="balance" class="form-control" step="0.01" required>
    </div>

    <div class="text-center">
      <button type="submit" class="btn btn-primary">Add Account</button>
      <a href="CustomerControllerServlet?action=list" class="btn btn-secondary">Back to Customer List</a>
    </div>
  </form>
</div>
<%@ include file="footer.jsp" %>
