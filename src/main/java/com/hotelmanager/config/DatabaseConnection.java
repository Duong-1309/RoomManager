package com.hotelmanager.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:hotel_manager.db";
    private static Connection connection = null;

    // Lấy kết nối tới cơ sở dữ liệu
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                createTablesIfNotExist(); // Tạo bảng nếu chưa tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return connection;
    }

    // Tạo các bảng nếu chưa tồn tại
    private static void createTablesIfNotExist() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS users (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                username TEXT NOT NULL UNIQUE,
                                password TEXT NOT NULL,
                                role TEXT NOT NULL
                            );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS rooms (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                room_number TEXT NOT NULL UNIQUE,
                                status TEXT NOT NULL CHECK (status IN ('OCCUPIED', 'VACANT')),
                                floor INTEGER,
                                area REAL,
                                rent_price REAL NOT NULL,
                                description TEXT,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                            );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS tenants (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                room_id INTEGER,
                                full_name TEXT NOT NULL,
                                phone TEXT,
                                email TEXT,
                                id_number TEXT,
                                address TEXT,
                                start_date DATE,
                                end_date DATE,
                                status TEXT CHECK (status IN ('ACTIVE', 'INACTIVE')),
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (room_id) REFERENCES rooms(id)
                            );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS bills (
                                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                                  room_id INTEGER,
                                  month INTEGER NOT NULL,
                                  year INTEGER NOT NULL,
                                  rent_amount REAL NOT NULL,
                                  electric_old_index INTEGER,
                                  electric_new_index INTEGER,
                                  electric_price REAL,
                                  electric_amount REAL,
                                  water_old_index INTEGER,
                                  water_new_index INTEGER,
                                  water_price REAL,
                                  water_amount REAL,
                                  garbage_fee REAL,
                                  wifi_fee REAL,
                                  parking_fee REAL,
                                  other_fees REAL,
                                  other_fees_note TEXT,
                                  total_amount REAL,
                                  status TEXT CHECK (status IN ('PENDING', 'PAID')),
                                  payment_date DATE,
                                  note TEXT,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  FOREIGN KEY (room_id) REFERENCES rooms(id)
                            );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS notifications (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                room_id INTEGER,
                                title TEXT NOT NULL,
                                content TEXT NOT NULL,
                                type TEXT CHECK (type IN ('BILL', 'MAINTENANCE', 'OTHER')),
                                is_read BOOLEAN DEFAULT 0,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (room_id) REFERENCES rooms(id)
                            );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS utility_history (
                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                              room_id INTEGER,
                              month INTEGER NOT NULL,
                              year INTEGER NOT NULL,
                              electric_index INTEGER,
                              water_index INTEGER,
                              record_date DATE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (room_id) REFERENCES rooms(id)
                          );
                        """
            );
            statement.execute(
                    """
                            CREATE TABLE IF NOT EXISTS settings (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                key TEXT NOT NULL UNIQUE,
                                value TEXT NOT NULL,
                                description TEXT,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                            );
                        """
            );
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    // Đóng kết nối cơ sở dữ liệu
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
