package com.store.admin;

import com.store.admin.model.User;
import com.store.admin.service.AuthService;
import com.store.admin.ui.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Store Administration Console!");
        System.out.println("Please log in to continue.");

        User user = null;
        while (user == null) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            user = authService.login(username, password);

            if (user == null) {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        System.out.println("\nLogin successful. Welcome, " + user.getUsername() + "!");
        Menu menu = new Menu(user);
        menu.display();
    }
}
