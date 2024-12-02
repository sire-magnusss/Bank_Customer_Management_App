<%@ include file="header.jsp" %>
<%@ page import="bankapp.model.Customer" %>
<%
  // Retrieve the customer object passed from the servlet
  Customer customer = (Customer) request.getAttribute("customer");
  if (customer == null) {
%>
<div class="container my-4">
  <div class="alert alert-danger" role="alert">
    <h4 class="alert-heading">Error</h4>
    <p>Customer not found. Please try again.</p>
    <hr>
    <a href="CustomerControllerServlet?action=list" class="btn btn-danger">Back to Customer List</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
<%
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Customer</title>
</head>
<body>
<div class="container my-4">
  <div class="card">
    <div class="card-header bg-warning text-white">
      <h2 class="card-title">Edit Customer</h2>
    </div>
    <div class="card-body">
      <form action="CustomerControllerServlet" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">

        <div class="mb-3">
          <label for="firstName" class="form-label">First Name:</label>
          <input type="text" id="firstName" name="firstName" class="form-control" value="<%= customer.getFirstName() %>" required>
        </div>

        <div class="mb-3">
          <label for="lastName" class="form-label">Last Name:</label>
          <input type="text" id="lastName" name="lastName" class="form-control" value="<%= customer.getLastName() %>" required>
        </div>

        <div class="mb-3">
          <label for="email" class="form-label">Email:</label>
          <input type="email" id="email" name="email" class="form-control" value="<%= customer.getEmail() %>" required>
        </div>

        <div class="mb-3">
          <label for="phone" class="form-label">Phone:</label>
          <input type="text" id="phone" name="phone" class="form-control" value="<%= customer.getPhone() %>" required>
        </div>

        <button type="submit" class="btn btn-success">Save Changes</button>
        <a href="CustomerControllerServlet?action=list" class="btn btn-secondary">Cancel</a>
      </form>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
