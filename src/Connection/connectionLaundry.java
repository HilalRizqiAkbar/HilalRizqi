package Connection;

import java.sql.*;

public class connectionLaundry {
    private Connection connection;

    public connectionLaundry() {
        // Mendapatkan koneksi ke database
        String url = "jdbc:mysql://localhost:3306/laundry_db";

        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkRegistration(String username) {
        boolean exists = false;

        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = true;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

public boolean checkLogin(String username, String password, String userType) {
    boolean loggedIn = false;

    try {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND userType = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, userType);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            loggedIn = true;
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return loggedIn;
}

    public boolean saveRegistration(String username, String password, String address, String telepon, String userType) {
        boolean success = false;

        try {
            String sql = "INSERT INTO users (username, password, address, telepon, userType) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, address);
            statement.setString(4, telepon);
            statement.setString(5, userType);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                success = true;
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

}
