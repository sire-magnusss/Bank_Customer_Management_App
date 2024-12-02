Bank Application Management System
This project is a Bank Application Management System developed using Java (JSP/Servlets), MySQL, and Bootstrap. The application allows users to manage customers, accounts, and transactions effectively. It also features a modern interface with consistent styling across pages.

Features
Customer Management

Add, edit, view, and remove customers.
Search for customers by name or email.
View a detailed list of all customers with associated accounts and transactions.
Account Management

Add new accounts for existing customers.
View accounts by customer, including account type and balance.
Transaction Management

Record deposits and withdrawals for accounts.
View transactions for a specific account.
Responsive Design

Integrated Bootstrap for a clean, responsive UI.
Consistent header and footer across all pages.
Database-Driven Application

MySQL database integration with support for creating, reading, updating, and deleting records.
Technologies Used
Backend: Java (JSP/Servlets)
Frontend: HTML, CSS, Bootstrap
Database: MySQL
Server: Apache Tomcat
Version Control: Git & GitHub


Project Structure

BankApp/
├── src/
│   ├── bankapp/
│   │   ├── controller/
│   │   │   ├── CustomerControllerServlet.java
│   │   │   ├── AccountControllerServlet.java
│   │   │   ├── TransactionControllerServlet.java
│   │   ├── dao/
│   │   │   ├── CustomerDAO.java
│   │   │   ├── AccountDAO.java
│   │   │   ├── TransactionDAO.java
│   │   ├── model/
│   │   │   ├── Customer.java
│   │   │   ├── Account.java
│   │   │   ├── Transaction.java
├── web/
│   ├── jsp/
│   │   ├── addCustomer.jsp
│   │   ├── editCustomer.jsp
│   │   ├── listCustomers.jsp
│   │   ├── addAccount.jsp
│   │   ├── addTransaction.jsp
│   │   ├── viewAccounts.jsp
│   │   ├── viewTransactions.jsp
│   │   ├── searchCustomer.jsp
│   │   ├── confirmation.jsp
│   │   ├── header.jsp
│   │   ├── footer.jsp
├── database/
│   ├── bankapp.sql (Database dump)

[Uploading Dump20241118 (1).sql…]()



Installation Instructions

1. Clone the repository to your local machine:

git clone https://github.com/sire-magnusss/Bank_Customer_Management_App.git

2. Import the project into your IDE (e.g., IntelliJ, Eclipse).
3. Install MySQL and import the database dump:

   mysql -u root -p < database/bankapp.sql

4.Update the database connection settings in META-INF/context.xml
 
  <Resource name="jdbc/bank_db" 
          auth="Container" 
          type="javax.sql.DataSource" 
          username="root" 
          password="yourpassword" 
          driverClassName="com.mysql.cj.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/bank_db" />

  5. Deploy the project on Apache Tomcat.
Access the application in your browser at:

http://localhost:8080/bank-app


Screenshots
Feature	Screenshot
Customer List	
Add Customer	
Edit Customer	
View Accounts	
Add Account	
View Transactions	
Add Transaction	
Search Customers



How It Works
Customer Operations:

Navigate to the customer list to manage customer information.
Use the search functionality to find specific customers.
Account Management:

Select a customer and view their associated accounts.
Add new accounts for a customer, specifying account type and balance.
Transaction Management:

View transactions for an account and record deposits/withdrawals.
Future Enhancements
Authentication: Add login functionality for admin and staff users.
Reports: Generate downloadable reports for accounts and transactions.
Improved UI: Add animations and real-time updates.
Contributing
Contributions are welcome! Feel free to fork the repository and submit a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.
