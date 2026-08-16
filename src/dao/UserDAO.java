package dao;

import database.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // ===============================
    // Register New User
    // ===============================
    public boolean registerUser(User user) {

        if (user == null) {
            return false;
        }

        if (usernameExists(user.getUsername())) {
            return false;
        }

        String sql = "INSERT INTO users(username,password,created_at) VALUES(?,?,?)";

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, user.getUsername().trim());
            pst.setString(2, user.getPassword().trim());
            pst.setString(3, user.getCreatedAt());

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // ===============================
    // Login User
    // ===============================
    public User loginUser(String username, String password) {

        String sql = """
                SELECT id,
                       username,
                       password,
                       created_at
                FROM users
                WHERE username=? AND password=?
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, username.trim());
            pst.setString(2, password.trim());

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    return new User(

                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("created_at")

                    );

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    // ===============================
    // Check Username Exists
    // ===============================
    public boolean usernameExists(String username) {

        String sql = "SELECT 1 FROM users WHERE username=?";

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, username.trim());

            try (ResultSet rs = pst.executeQuery()) {

                return rs.next();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

}