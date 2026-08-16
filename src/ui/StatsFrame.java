package ui;

import dao.GameDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatsFrame extends JFrame {

    private JLabel totalGamesLabel;
    private JLabel winsLabel;
    private JLabel lossesLabel;
    private JLabel drawsLabel;
    private JLabel percentageLabel;

    private GameDAO gameDAO;

    public StatsFrame(String username) {

        gameDAO = new GameDAO();

        setTitle("My Statistics");
        setSize(450,450);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));
        add(mainPanel);

        //---------------- HEADER ----------------//

        JPanel header = new JPanel();

        header.setBackground(new Color(25,118,210));
        header.setPreferredSize(new Dimension(450,70));

        JLabel title = new JLabel("MY STATISTICS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));

        header.add(title);

        //---------------- CENTER ----------------//

        JPanel center = new JPanel();

        center.setBackground(Color.WHITE);
        center.setLayout(new GridLayout(5,1,10,10));
        center.setBorder(new EmptyBorder(30,40,30,40));

        totalGamesLabel = createLabel();
        winsLabel = createLabel();
        lossesLabel = createLabel();
        drawsLabel = createLabel();
        percentageLabel = createLabel();

        center.add(totalGamesLabel);
        center.add(winsLabel);
        center.add(lossesLabel);
        center.add(drawsLabel);
        center.add(percentageLabel);

        //---------------- BUTTON ----------------//

        JButton closeButton = new JButton("Close");

        closeButton.setBackground(new Color(25,118,210));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI",Font.BOLD,16));
        closeButton.setFocusPainted(false);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(245,245,245));

        bottom.add(closeButton);

        //---------------- ADD ----------------//

        mainPanel.add(header,BorderLayout.NORTH);
        mainPanel.add(center,BorderLayout.CENTER);
        mainPanel.add(bottom,BorderLayout.SOUTH);

        //---------------- LOAD DATA ----------------//

        loadStatistics(username);

        closeButton.addActionListener(e->dispose());

        setVisible(true);

    }

    //---------------------------------------

    private JLabel createLabel(){

        JLabel label = new JLabel();

        label.setFont(new Font("Segoe UI",Font.BOLD,18));

        return label;

    }

    //---------------------------------------

    private void loadStatistics(String username){

        int total = gameDAO.getTotalGames(username);

        int wins = gameDAO.getWins(username);

        int losses = gameDAO.getLosses(username);

        int draws = gameDAO.getDraws(username);

        double percentage =
                gameDAO.getWinningPercentage(username);

        totalGamesLabel.setText("Total Games : " + total);

        winsLabel.setText("Wins : " + wins);

        lossesLabel.setText("Losses : " + losses);

        drawsLabel.setText("Draws : " + draws);

        percentageLabel.setText(
                String.format(
                        "Winning Percentage : %.2f%%",
                        percentage
                )
        );

    }

}