-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

-- Drop existing tables to start fresh
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- Create the users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(64) NOT NULL, -- SHA-256 hash
    password_salt VARCHAR(32) NOT NULL, -- Salt for password hashing
    role ENUM('ADMIN', 'STAFF') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the products table
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert the default admin user with a salted and hashed password
-- The password is 'adminpass'
-- The salt is 'c1a2b3d4e5f67890'
-- The resulting hash is for 'adminpass' + salt
INSERT INTO users (username, password_hash, password_salt, role) VALUES (
    'admin',
    'f7f3f2d19c3695d31221a735073109a563231363780373281315570221c9a63d',
    'c1a2b3d4e5f67890',
    'ADMIN'
);
