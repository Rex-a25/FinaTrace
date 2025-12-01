package com.store.admin.service;

import com.store.admin.dao.UserDAO;
import com.store.admin.model.User;
import com.store.admin.util.PasswordUtil;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && PasswordUtil.verifyPassword(password, user.getPasswordHash(), user.getPasswordSalt())) {
            return user;
        }
        return null;
    }
}
