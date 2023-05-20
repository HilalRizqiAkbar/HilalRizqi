
-- Membuat database "laundry_db"
CREATE DATABASE IF NOT EXISTS laundry_db;

-- Menggunakan database "laundry_db"
USE laundry_db;

-- Membuat tabel "users" untuk menyimpan data pengguna
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  address VARCHAR(100) NOT NULL,
  telepon VARCHAR(50) NOT NULL,
  userType ENUM('User', 'Admin') NOT NULL
);
