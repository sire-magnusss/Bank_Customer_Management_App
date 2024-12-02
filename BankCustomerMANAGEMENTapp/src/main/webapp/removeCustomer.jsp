<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Remove Customer</title>
</head>
<body>
<div class="container my-4">
  <h1 class="text-danger text-center">Remove Customer</h1>
  <form action="CustomerControllerServlet" method="post" class="mt-4">
    <div class="mb-3">
      <label for="customerId" class="form-label">Customer ID:</label>
      <input type="number" id="customerId" name="customerId" class="form-control" required>
    </div>
    <input type="hidden" name="action" value="remove">
    <div class="text-center">
      <button type="submit" class="btn btn-danger btn-lg">Remove Customer</button>
    </div>
  </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
