package com.store.admin.service;

import com.store.admin.dao.UserDAO;
import com.store.admin.model.User;
import com.store.admin.util.PasswordUtil;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void createUser(String username, String password, String role) {
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        user.setPasswordSalt(salt);
        user.setRole(role);
        userDAO.addUser(user);
    }

    public void updateUser(User user, String newPassword) {
        // If a new password is provided, re-hash it with the user's existing salt
        if (newPassword != null && !newPassword.isEmpty()) {
            User existingUser = userDAO.getUserById(user.getId());
            String salt = existingUser.getPasswordSalt();
            String hashedPassword = PasswordUtil.hashPassword(newPassword, salt);
            user.setPasswordHash(hashedPassword);
            user.setPasswordSalt(salt);
        } else {
            // Otherwise, keep the existing password hash and salt
            User existingUser = userDAO.getUserById(user.getId());
            user.setPasswordHash(existingUser.getPasswordHash());
            user.setPasswordSalt(existingUser.getPasswordSalt());
        }
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
}
