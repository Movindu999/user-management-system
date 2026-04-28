--Create info_db database
CREATE DATABASE info_db;

USE info_db;

--Create the users table
CREATE TABLE users(
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      about TEXT NOT NULL,
                      birthday DATE NOT NULL,
                      mobile VARCHAR(15) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      country VARCHAR(50) NOT NULL
);