<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Transaction Successful</title>
</head>
<body>
<div class="container my-5">
  <div class="alert alert-success text-center" role="alert">
    <h1>Transaction Recorded Successfully</h1>
  </div>
  <div class="text-center mt-4">
    <a href="CustomerControllerServlet?action=list" class="btn btn-primary">Back to Customer List</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
