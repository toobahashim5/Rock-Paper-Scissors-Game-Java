package ui;

import dao.GameDAO;
import logic.GameLogic;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class DashboardFrame extends JFrame {

    private User user;

    private GameLogic gameLogic;
    private GameDAO gameDAO;

    // Header
    private JLabel welcomeLabel;

    // Game Labels
    private JLabel computerChoiceLabel;
    private JLabel resultLabel;

    // Score Labels
    private JLabel totalGamesLabel;
    private JLabel winsLabel;
    private JLabel lossesLabel;
    private JLabel drawsLabel;

    // Buttons
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;

    private JButton statsButton;
    private JButton historyButton;
    private JButton logoutButton;

    // Counters
    private int totalGames;
    private int wins;
    private int losses;
    private int draws;

    public DashboardFrame(User user) {

        this.user = user;

        gameLogic = new GameLogic();
        gameDAO = new GameDAO();

        setTitle("Rock Paper Scissors Dashboard");
        setSize(950,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //----------------------------------------
        // Main Panel
        //----------------------------------------

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));

        add(mainPanel);

        //----------------------------------------
        // HEADER
        //----------------------------------------

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(new Color(25,118,210));
        header.setPreferredSize(new Dimension(950,70));

        JLabel title = new JLabel(" ROCK PAPER SCISSORS");

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));

        welcomeLabel = new JLabel(
                "Welcome, " + user.getUsername() + "   "
        );

        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Segoe UI",Font.BOLD,18));

        header.add(title,BorderLayout.WEST);
        header.add(welcomeLabel,BorderLayout.EAST);

        //----------------------------------------
        // SIDEBAR
        //----------------------------------------

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(new Dimension(200,500));

        sidebar.setBackground(new Color(38,50,56));

        sidebar.setLayout(new GridLayout(4,1,10,10));

        sidebar.setBorder(new EmptyBorder(20,15,20,15));

        statsButton = new JButton("My Stats");

        historyButton = new JButton("History");

        logoutButton = new JButton("Logout");

        JButton dashboardButton = new JButton("Dashboard");

        styleSideButton(dashboardButton);
        styleSideButton(statsButton);
        styleSideButton(historyButton);
        styleSideButton(logoutButton);

        sidebar.add(dashboardButton);
        sidebar.add(statsButton);
        sidebar.add(historyButton);
        sidebar.add(logoutButton);

        //----------------------------------------
        // CENTER PANEL
        //----------------------------------------

        JPanel center = new JPanel();

        center.setBackground(Color.WHITE);

        center.setLayout(new BorderLayout());

        //----------------------------------------
        // GAME PANEL
        //----------------------------------------

        JPanel gamePanel = new JPanel();

        gamePanel.setBackground(Color.WHITE);

        gamePanel.setLayout(new FlowLayout(
                FlowLayout.CENTER,
                40,
                30
        ));

        rockButton = new JButton("🪨 Rock");

        paperButton = new JButton("📄 Paper");

        scissorsButton = new JButton("✂ Scissors");

        styleGameButton(rockButton);
        styleGameButton(paperButton);
        styleGameButton(scissorsButton);

        gamePanel.add(rockButton);
        gamePanel.add(paperButton);
        gamePanel.add(scissorsButton);

        //----------------------------------------
        // RESULT PANEL
        //----------------------------------------

        JPanel resultPanel = new JPanel();

        resultPanel.setBackground(Color.WHITE);

        resultPanel.setLayout(new GridLayout(6,1,10,10));

        resultPanel.setBorder(
                new EmptyBorder(20,40,20,40)
        );

        computerChoiceLabel = new JLabel(
                "Computer Choice : -"
        );

        resultLabel = new JLabel(
                "Result : -"
        );

        totalGamesLabel = new JLabel();

        winsLabel = new JLabel();

        lossesLabel = new JLabel();

        drawsLabel = new JLabel();

        Font font =
                new Font("Segoe UI",Font.BOLD,18);

        computerChoiceLabel.setFont(font);
        resultLabel.setFont(font);
        totalGamesLabel.setFont(font);
        winsLabel.setFont(font);
        lossesLabel.setFont(font);
        drawsLabel.setFont(font);

        resultPanel.add(computerChoiceLabel);
        resultPanel.add(resultLabel);
        resultPanel.add(totalGamesLabel);
        resultPanel.add(winsLabel);
        resultPanel.add(lossesLabel);
        resultPanel.add(drawsLabel);

        center.add(gamePanel,BorderLayout.NORTH);
        center.add(resultPanel,BorderLayout.CENTER);

        //----------------------------------------

        mainPanel.add(header,BorderLayout.NORTH);
        mainPanel.add(sidebar,BorderLayout.WEST);
        mainPanel.add(center,BorderLayout.CENTER);

        //----------------------------------------
        // EVENTS
        //----------------------------------------

        rockButton.addActionListener(e ->
                playGame("Rock"));

        paperButton.addActionListener(e ->
                playGame("Paper"));

        scissorsButton.addActionListener(e ->
                playGame("Scissors"));

        statsButton.addActionListener(e ->
                new StatsFrame(user.getUsername()));

        historyButton.addActionListener(e ->
                new HistoryFrame(user.getUsername()));

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        loadStatistics();

        setVisible(true);

    }

    // =====================================================
    // PLAY GAME
    // =====================================================

    private void playGame(String userChoice) {

        String computerChoice = gameLogic.getComputerChoice();

        String result = gameLogic.checkWinner(
                userChoice,
                computerChoice
        );

        computerChoiceLabel.setText(
                "Computer Choice : " + computerChoice
        );

        resultLabel.setText(
                "Result : " + result
        );

        boolean saved = gameDAO.saveGame(
                user.getUsername(),
                userChoice,
                computerChoice,
                result,
                LocalDateTime.now().toString()
        );

        if (!saved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save game!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        loadStatistics();

    }

    // =====================================================
    // LOAD STATISTICS FROM DATABASE
    // =====================================================

    private void loadStatistics() {

        totalGames = gameDAO.getTotalGames(
                user.getUsername()
        );

        wins = gameDAO.getWins(
                user.getUsername()
        );

        losses = gameDAO.getLosses(
                user.getUsername()
        );

        draws = gameDAO.getDraws(
                user.getUsername()
        );

        totalGamesLabel.setText(
                "Total Games : " + totalGames
        );

        winsLabel.setText(
                "Wins : " + wins
        );

        lossesLabel.setText(
                "Losses : " + losses
        );

        drawsLabel.setText(
                "Draws : " + draws
        );

        if (wins > losses) {

            resultLabel.setForeground(
                    new Color(46,125,50)
            );

        }

        else if (losses > wins) {

            resultLabel.setForeground(
                    Color.RED
            );

        }

        else {

            resultLabel.setForeground(
                    new Color(255,140,0)
            );

        }

    }
    // =====================================================
    // STYLE GAME BUTTONS
    // =====================================================

    private void styleGameButton(JButton button) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(new Color(25,118,210));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(170,60));

    }

    // =====================================================
    // STYLE SIDEBAR BUTTONS
    // =====================================================

    private void styleSideButton(JButton button) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(38,50,56));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}