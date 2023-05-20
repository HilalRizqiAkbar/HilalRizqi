package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Connection.connectionLaundry;

public class loginForm extends JFrame {
    private JPanel panels;
    private JLabel loginBanner;
    private JTextField usernameFields;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton cancelButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton backButton;
    private JComboBox<String> chooseLabel;
    private JLabel chooserLabel;
    private JTextField usernameField;
    private JLabel authImage;

    private connectionLaundry connectionLaundry;

    public loginForm() {
        connectionLaundry = new connectionLaundry();

        setTitle("Login");
        setContentPane(panels);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 550));
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon authIcon = new ImageIcon("src/image/auth-logo.png"); // Replace with the path to your icon
        authImage.setIcon(authIcon);
        Image image = authIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledAuthIcon = new ImageIcon(image);
        authImage.setIcon(scaledAuthIcon);

        ImageIcon icon = new ImageIcon("src/image/laundry.png");
        setIconImage(icon.getImage());

        chooseLabel.addItem("User");
        chooseLabel.addItem("Admin");

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = chooseLabel.getSelectedItem().toString();

                boolean loggedIn = connectionLaundry.checkLogin(username, password, userType);

                if (loggedIn) {
                    JOptionPane.showMessageDialog(panels, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    menuForm menuForm = new menuForm();
                    menuForm.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(panels, "Invalid! Please Check It Again!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationForm registrationForms = new registrationForm();
                registrationForms.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        loginForm form = new loginForm();
    }
}
