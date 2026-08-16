package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton registerButton;
    private JButton loginButton;

    public RegisterFrame() {

        setTitle("Rock Paper Scissors - Register");
        setSize(500, 520);
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

        JLabel title = new JLabel("CREATE ACCOUNT");
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

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Segoe UI",Font.BOLD,24));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI",Font.PLAIN,16));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI",Font.PLAIN,16));

        registerButton = new JButton("REGISTER");
        loginButton = new JButton("BACK TO LOGIN");

        styleButton(registerButton);
        styleButton(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        center.add(registerLabel, gbc);

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
        center.add(registerButton, gbc);

        gbc.gridx = 1;
        center.add(loginButton, gbc);

        //---------------- ADD ----------------//

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(center, BorderLayout.CENTER);

        //---------------- EVENTS ----------------//

        registerButton.addActionListener(e -> register());

        loginButton.addActionListener(e -> {

            dispose();
            new LoginFrame();

        });

        setVisible(true);

    }

    //========================================

    private void register() {

        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if(username.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        if(password.length() < 4){

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 4 characters."
            );

            return;
        }

        UserDAO dao = new UserDAO();

        if(dao.usernameExists(username)){

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists."
            );

            return;
        }

        User user = new User(

                username,
                password,
                LocalDateTime.now().toString()

        );

        boolean success = dao.registerUser(user);

        if(success){

            JOptionPane.showMessageDialog(
                    this,
                    "Registration Successful!"
            );

            dispose();

            new LoginFrame();

        }

        else{

            JOptionPane.showMessageDialog(
                    this,
                    "Registration Failed."
            );

        }

    }

    //========================================

    private void styleButton(JButton button){

        button.setFont(new Font("Segoe UI",Font.BOLD,15));
        button.setBackground(new Color(25,118,210));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

    }

}