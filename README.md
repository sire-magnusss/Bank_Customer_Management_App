# Bank Application Management System
This project is a Bank Application Management System developed using Java (JSP/Servlets), MySQL, and Bootstrap. The application allows users to manage customers, accounts, and transactions effectively. It also features a modern interface with consistent styling across pages.

## Screenshots
## Feature	Screenshot

### Customer List

![Screenshot 2024-12-02 182554](https://github.com/user-attachments/assets/21aa0442-8b01-4b0b-be0b-257bd83faf73)

### Add Customer

![Screenshot 2024-12-02 182617](https://github.com/user-attachments/assets/5fa0822d-360c-41d6-8a63-25a115452ae2)

![Screenshot 2024-12-02 182624](https://github.com/user-attachments/assets/a511c5e9-4492-42dc-9103-ca2370e0de61)


### Edit Customer

![Screenshot 2024-12-02 182858](https://github.com/user-attachments/assets/be8df24b-014b-48a4-8fe8-7b774ad091ac)

### View Accounts	

![Screenshot 2024-12-02 182715](https://github.com/user-attachments/assets/b3488a3e-c9cf-4685-b036-afbfe7e0d7ef)


### Add Account	

![Screenshot 2024-12-02 182817](https://github.com/user-attachments/assets/e8726c01-e4c5-46d6-ac56-34247a73e24a)


### View Transactions	

![Screenshot 2024-12-02 182746](https://github.com/user-attachments/assets/c2a359c2-6d9d-4de8-b7a4-25dbea88ff38)


### Add Transaction

![Screenshot 2024-12-02 182759](https://github.com/user-attachments/assets/91666b7c-c262-40c9-80a6-b29b508f47fc)


### Search Customers

![Screenshot 2024-12-02 182921](https://github.com/user-attachments/assets/1db69e07-78d9-4180-9752-5e9726869f10)

### Remove Customer

![Screenshot 2024-12-02 182650](https://github.com/user-attachments/assets/e741f748-ce49-423b-a134-d48215543a35)


## Features
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


## Project Structure

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
│   ├── bankapp.sql   # Database dump






## Installation Instructions

1. Clone the repository to your local machine:

```bash git clone https://github.com/sire-magnusss/Bank_Customer_Management_App.git ```

3. Import the project into your IDE (e.g., IntelliJ, Eclipse).
4. Install MySQL and import the database dump:

  ```bash mysql -u root -p < database/bankapp.sql ```bash

4.Update the database connection settings in META-INF/context.xml
 
 ```bash
<Resource name="jdbc/bank_db" 
          auth="Container" 
          type="javax.sql.DataSource" 
          username="root" 
          password="yourpassword" 
          driverClassName="com.mysql.cj.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/bank_db" />
```

  5. Deploy the project on Apache Tomcat.
Access the application in your browser at:
```bash
http://localhost:8080/bank-app
```



## How It Works
Customer Operations:

Navigate to the customer list to manage customer information.
Use the search functionality to find specific customers.
Account Management:

Select a customer and view their associated accounts.
Add new accounts for a customer, specifying account type and balance.
Transaction Management:

View transactions for an account and record deposits/withdrawals.
## Future Enhancements
Authentication: Add login functionality for admin and staff users.
Reports: Generate downloadable reports for accounts and transactions.
Improved UI: Add animations and real-time updates.
Contributing
Contributions are welcome! Feel free to fork the repository and submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
