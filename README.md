# Store Administration Console Application

This is a console-based application for managing users and products in a store, built with Java and MySQL. It provides a simple command-line interface for administrators and staff to perform their respective duties.

## Features

- User authentication with hashed passwords (SHA-256).
- Role-based access control (ADMIN, STAFF).
- User management (Create, Update, Delete, View all).
- Product management (Add, Edit, Delete, View all).
- Clean, modular architecture with separation of concerns.

## Project Structure

```
.
├── config.properties
├── database.sql
├── README.md
└── src
    └── com
        └── store
            └── admin
                ├── dao
                │   ├── ProductDAO.java
                │   └── UserDAO.java
                ├── model
                │   ├── Product.java
                │   └── User.java
                ├── service
                │   ├── AuthService.java
                │   ├── ProductService.java
                │   └── UserService.java
                ├── ui
                │   └── Menu.java
                ├── util
                │   ├── DatabaseUtil.java
                │   └── PasswordUtil.java
                └── Main.java
```

## Requirements

- Java Development Kit (JDK) 8-17
- Apache NetBeans IDE
- MySQL Server
- MySQL Connector/J (JDBC Driver)

## Setup Instructions

### 1. Database Setup

1.  **Start your MySQL server.**
2.  **Create the database and tables** by executing the `database.sql` script. You can use a MySQL client like MySQL Workbench or the command-line interface.

    ```sh
    mysql -u your_mysql_username -p < database.sql
    ```

    This script will:
    - Create a database named `store_db`.
    - Create the `users` and `products` tables.
    - Insert a default admin user.

    **Default Admin Credentials:**
    - **Username:** `admin`
    - **Password:** `adminpass`

### 2. Configuration

1.  Open the `config.properties` file located in the project's root directory.
2.  Update the database connection details with your MySQL username and password.

    ```properties
    # Database Configuration
    db.url=jdbc:mysql://localhost:3306/store_db
    db.user=your_username
    db.password=your_password
    ```

### 3. NetBeans Project Setup

1.  **Open NetBeans IDE.**
2.  Go to `File` > `Open Project...` and select the project's root folder.
3.  **Add the MySQL JDBC Driver:**
    - In the `Projects` tab, right-click on the `Libraries` folder.
    - Select `Add JAR/Folder...`.
    - Navigate to and select the downloaded MySQL Connector/J `.jar` file.
4.  **Build and Run the Project:**
    - Right-click on the project in the `Projects` tab and select `Clean and Build`.
    - Once the build is complete, right-click on the `Main.java` file and select `Run File` (or press `Shift + F6`).

The application will now start in the NetBeans output console, and you can log in with the default admin credentials.
