package ui;

import dao.GameDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public HistoryFrame(String username) {

        setTitle("Game History");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));
        add(mainPanel);

        // ================= HEADER =================

        JPanel header = new JPanel();

        header.setBackground(new Color(25,118,210));
        header.setPreferredSize(new Dimension(750,70));

        JLabel title = new JLabel("GAME HISTORY");

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));

        header.add(title);

        // ================= TABLE =================

        String[] columns = {
                "User Choice",
                "Computer Choice",
                "Result",
                "Date"
        };

        model = new DefaultTableModel(columns,0){

            @Override
            public boolean isCellEditable(int row,int column){

                return false;

            }

        };

        table = new JTable(model);

        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(15,15,15,15));

        // ================= BUTTON =================

        JButton closeButton = new JButton("Close");

        closeButton.setBackground(new Color(25,118,210));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI",Font.BOLD,15));
        closeButton.setFocusPainted(false);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(245,245,245));

        bottom.add(closeButton);

        // ================= ADD =================

        mainPanel.add(header,BorderLayout.NORTH);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(bottom,BorderLayout.SOUTH);

        loadHistory(username);

        closeButton.addActionListener(e -> dispose());

        setVisible(true);

    }

    // =====================================

    private void loadHistory(String username){

        GameDAO dao = new GameDAO();

        List<String[]> history = dao.getGameHistory(username);

        model.setRowCount(0);

        for(String[] row : history){

            model.addRow(row);

        }

    }

}