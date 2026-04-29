-- Create a database
CREATE DATABASE IF NOT EXISTS info_db;
USE info_db;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    about TEXT NOT NULL,
    birthday DATE NOT NULL,
    mobile VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    country VARCHAR(50) NOT NULL
    );