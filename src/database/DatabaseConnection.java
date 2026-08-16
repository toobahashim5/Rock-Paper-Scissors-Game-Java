package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String DB_FOLDER = "database";
    private static final String DB_NAME = "game.db";
    private static final String URL = "jdbc:sqlite:" + DB_FOLDER + "/" + DB_NAME;

    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection connect() {

        try {

            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            // Create database folder automatically
            File folder = new File(DB_FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            connection = DriverManager.getConnection(URL);

            createTables();

            System.out.println("==================================");
            System.out.println("SQLite Connected Successfully");
            System.out.println("Database Path : " + URL);
            System.out.println("==================================");

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return connection;
    }

    private static void createTables() {

        String usersTable = """
                CREATE TABLE IF NOT EXISTS users(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    created_at TEXT NOT NULL
                );
                """;

        String historyTable = """
                CREATE TABLE IF NOT EXISTS game_history(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    user_choice TEXT NOT NULL,
                    computer_choice TEXT NOT NULL,
                    result TEXT NOT NULL,
                    date TEXT NOT NULL
                );
                """;

        try (Statement stmt = connection.createStatement()) {

            stmt.execute(usersTable);
            stmt.execute(historyTable);

            System.out.println("Database Tables Ready.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}