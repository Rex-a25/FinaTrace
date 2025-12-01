package com.store.admin.ui;

import com.store.admin.model.Product;
import com.store.admin.model.User;
import com.store.admin.service.ProductService;
import com.store.admin.service.UserService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();

    public Menu(User user) {
        this.currentUser = user;
    }

    public void display() {
        if ("ADMIN".equals(currentUser.getRole())) {
            displayAdminMenu();
        } else if ("STAFF".equals(currentUser.getRole())) {
            displayStaffMenu();
        }
    }

    private void displayAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Products");
            System.out.println("3. View All Products");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    manageUsers();
                    break;
                case 2:
                    manageProducts();
                    break;
                case 3:
                    viewAllProducts();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayStaffMenu() {
        while (true) {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. Manage Products");
            System.out.println("2. View All Products");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void manageUsers() {
        while (true) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void manageProducts() {
         while (true) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createUser() {
        System.out.println("\n--- Create New User ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/STAFF): ");
        String role = scanner.nextLine().toUpperCase();

        if (!"ADMIN".equals(role) && !"STAFF".equals(role)) {
            System.out.println("Invalid role. Please enter ADMIN or STAFF.");
            return;
        }

        userService.createUser(username, password, role);
        System.out.println("User created successfully!");
    }

    private void updateUser() {
        System.out.println("\n--- Update User ---");
        viewAllUsers();
        System.out.print("Enter user ID to update: ");
        int userId = getIntInput();

        User userToUpdate = userService.getUserById(userId);
        if (userToUpdate == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter new role (ADMIN/STAFF): ");
        String newRole = scanner.nextLine().toUpperCase();

        userToUpdate.setUsername(newUsername);
        userToUpdate.setRole(newRole);

        userService.updateUser(userToUpdate, newPassword);
        System.out.println("User updated successfully!");
    }

    private void deleteUser() {
        System.out.println("\n--- Delete User ---");
        viewAllUsers();
        System.out.print("Enter user ID to delete: ");
        int userId = getIntInput();

        if (userId == currentUser.getId()) {
            System.out.println("You cannot delete yourself.");
            return;
        }

        userService.deleteUser(userId);
        System.out.println("User deleted successfully!");
    }

    private void viewAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("\n--- All Users ---");
        System.out.printf("%-5s %-20s %-10s %-20s%n", "ID", "Username", "Role", "Created At");
        for (User user : users) {
            System.out.printf("%-5d %-20s %-10s %-20s%n",
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getCreatedAt());
        }
    }

    private void addProduct() {
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price: ");
        BigDecimal price = getBigDecimalInput();
        System.out.print("Enter quantity: ");
        int quantity = getIntInput();

        Product newProduct = new Product(0, name, description, price, quantity, new Timestamp(System.currentTimeMillis()));
        productService.addProduct(newProduct);
        System.out.println("Product added successfully!");
    }

    private void editProduct() {
        System.out.println("\n--- Edit Product ---");
        viewAllProducts();
        System.out.print("Enter product ID to edit: ");
        int productId = getIntInput();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new price: ");
        BigDecimal price = getBigDecimalInput();
        System.out.print("Enter new quantity: ");
        int quantity = getIntInput();

        Product updatedProduct = new Product(productId, name, description, price, quantity, null);
        productService.updateProduct(updatedProduct);
        System.out.println("Product updated successfully!");
    }

    private void deleteProduct() {
        System.out.println("\n--- Delete Product ---");
        viewAllProducts();
        System.out.print("Enter product ID to delete: ");
        int productId = getIntInput();
        productService.deleteProduct(productId);
        System.out.println("Product deleted successfully!");
    }

    private void viewAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("\n--- All Products ---");
        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "Name", "Price", "Quantity");
        for (Product product : products) {
            System.out.printf("%-5d %-20s %-10.2f %-10d%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity());
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Please enter an integer.");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private BigDecimal getBigDecimalInput() {
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("That's not a valid decimal number. Please try again.");
            scanner.next();
        }
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        return value;
    }
}
