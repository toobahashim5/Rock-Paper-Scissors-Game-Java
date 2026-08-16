package dao;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    // ======================================
    // Save Game
    // ======================================
    public boolean saveGame(
            String username,
            String userChoice,
            String computerChoice,
            String result,
            String date) {

        String sql = """
                INSERT INTO game_history
                (username,user_choice,computer_choice,result,date)
                VALUES(?,?,?,?,?)
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, username);
            pst.setString(2, userChoice);
            pst.setString(3, computerChoice);
            pst.setString(4, result);
            pst.setString(5, date);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // ======================================
    // Total Games
    // ======================================
    public int getTotalGames(String username) {

        String sql = "SELECT COUNT(*) FROM game_history WHERE username=?";

        return getCount(sql, username);

    }

    // ======================================
    // Wins
    // ======================================
    
    public int getWins(String username) {

    String sql = """
            SELECT COUNT(*)
            FROM game_history
            WHERE username=? 
            AND LOWER(result)='win'
            """;

    return getCount(sql, username);

}

    // ======================================
    // Losses
    // ======================================
    public int getLosses(String username) {

        String sql = """
                SELECT COUNT(*)
                FROM game_history
                WHERE username=? AND result='Lose'
                """;

        return getCount(sql, username);

    }

    // ======================================
    // Draws
    // ======================================
    public int getDraws(String username) {

        String sql = """
                SELECT COUNT(*)
                FROM game_history
                WHERE username=? AND result='Draw'
                """;

        return getCount(sql, username);

    }

    // ======================================
    // Winning Percentage
    // ======================================
    public double getWinningPercentage(String username) {

    int total = getTotalGames(username);

    if (total == 0) {
        return 0;
    }

    return (getWins(username) * 100.0) / total;

}

    // ======================================
    // Game History
    // ======================================
    public List<String[]> getGameHistory(String username) {

        List<String[]> history = new ArrayList<>();

        String sql = """
                SELECT user_choice,
                       computer_choice,
                       result,
                       date
                FROM game_history
                WHERE username=?
                ORDER BY id DESC
                """;

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                history.add(new String[]{

                        rs.getString("user_choice"),
                        rs.getString("computer_choice"),
                        rs.getString("result"),
                        rs.getString("date")

                });

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return history;

    }

    // ======================================
    // Generic Count Method
    // ======================================
    private int getCount(String sql, String username) {

        try (
                Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return 0;

    }

}