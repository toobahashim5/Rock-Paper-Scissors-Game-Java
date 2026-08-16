package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {

        setTitle("Rock Paper Scissors - Login");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //---------------- MAIN PANEL ----------------//

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));
        add(mainPanel);

        //---------------- HEADER ----------------//

        JPanel header = new JPanel();
        header.setBackground(new Color(25,118,210));
        header.setPreferredSize(new Dimension(500,80));

        JLabel title = new JLabel("ROCK PAPER SCISSORS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));

        header.add(title);

        //---------------- CENTER ----------------//

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);
        center.setBorder(new EmptyBorder(30,40,30,40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Segoe UI",Font.BOLD,24));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI",Font.PLAIN,16));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI",Font.PLAIN,16));

        loginButton = new JButton("LOGIN");
        registerButton = new JButton("REGISTER");

        styleButton(loginButton);
        styleButton(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        center.add(loginLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        center.add(usernameLabel, gbc);

        gbc.gridx = 1;
        center.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        center.add(passwordLabel, gbc);

        gbc.gridx = 1;
        center.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        center.add(loginButton, gbc);

        gbc.gridx = 1;
        center.add(registerButton, gbc);

        //---------------- ADD ----------------//

        mainPanel.add(header,BorderLayout.NORTH);
        mainPanel.add(center,BorderLayout.CENTER);

        //---------------- EVENTS ----------------//

        loginButton.addActionListener(e->login());

        registerButton.addActionListener(e->{

            dispose();
            new RegisterFrame();

        });

        setVisible(true);

    }

    //======================================

    private void login(){

        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if(username.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;

        }

        UserDAO dao = new UserDAO();

        User user = dao.loginUser(username,password);

        if(user!=null){

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome " + user.getUsername()
            );

            dispose();

            new DashboardFrame(user);

        }

        else{

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );

        }

    }

    //======================================

    private void styleButton(JButton button){

        button.setFont(new Font("Segoe UI",Font.BOLD,15));
        button.setBackground(new Color(25,118,210));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

    }

}