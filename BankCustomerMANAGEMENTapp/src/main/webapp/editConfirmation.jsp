<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Updated</title>
</head>
<body>
<div class="container my-4">
  <div class="card">
    <div class="card-header bg-info text-white text-center">
      <h2 class="card-title">Customer Updated Successfully</h2>
    </div>
    <div class="card-body text-center">
      <p class="text-success">The customer's information has been successfully updated!</p>
      <a href="CustomerControllerServlet?action=list" class="btn btn-primary">View All Customers</a>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
