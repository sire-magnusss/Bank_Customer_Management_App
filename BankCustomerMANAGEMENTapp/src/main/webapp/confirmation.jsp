<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Added</title>
</head>
<body>
<div class="container my-4">
    <div class="card">
        <div class="card-header bg-success text-white text-center">
            <h2 class="card-title">Customer Added Successfully</h2>
        </div>
        <div class="card-body">
            <p><strong>First Name:</strong> ${customer.firstName}</p>
            <p><strong>Last Name:</strong> ${customer.lastName}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Phone:</strong> ${customer.phone}</p>
            <div class="d-flex justify-content-between">
                <a href="addCustomer.jsp" class="btn btn-primary">Add Another Customer</a>
                <a href="CustomerControllerServlet?action=list" class="btn btn-secondary">Back to Customer List</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
