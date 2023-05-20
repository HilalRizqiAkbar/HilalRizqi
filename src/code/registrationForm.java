package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Connection.connectionLaundry;
public class registrationForm extends JFrame {
    private JPanel panel;
    private JLabel registrationLabel;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton registerButton;
    private JButton exitButton;
    private JButton haveAccountButton;
    private JTextField addressField;
    private JLabel addressLabel;
    private JTextField teleponField;
    private JLabel teleponLabel;
    private JLabel passwordLabel;
    private JComboBox chooseBox;
    private JLabel chooseLabel;
    private JLabel authImage;

    // Membuat objek koneksi ke database
    private connectionLaundry connectionLaundry;

    public registrationForm() {
        connectionLaundry = new connectionLaundry();

        setTitle("Registration");
        setContentPane(panel);
        setMinimumSize(new Dimension(900, 550));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon authIcon = new ImageIcon("src/image/auth-logo.png"); // Replace with the path to your icon
        authImage.setIcon(authIcon);
        Image image = authIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledAuthIcon = new ImageIcon(image);
        authImage.setIcon(scaledAuthIcon);

        // Menambahkan icon pada frame
        ImageIcon icon = new ImageIcon("src/image/laundry.png");
        setIconImage(icon.getImage());

        // Menambahkan item pada JComboBox
        chooseBox.addItem("User");
        chooseBox.addItem("Admin");

        // ActionListener untuk tombol registerButton
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Mengambil nilai dari input fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String address = addressField.getText();
                String telepon = teleponField.getText();
                String userType = chooseBox.getSelectedItem().toString();

                // Memeriksa keberadaan huruf kapital pada username
                boolean hasUppercase = false;
                for (char c : username.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        hasUppercase = true;
                        break;
                    }
                }

                // Memeriksa apakah telepon hanya berisi angka
                boolean isNumeric = telepon.matches("\\d+");

                // Validasi input fields
                if (username.isEmpty() || password.isEmpty() || address.isEmpty() || telepon.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill in all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isNumeric) {
                    JOptionPane.showMessageDialog(panel, "Nomor Telepon must contain only numeric characters!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.length() < 8) {
                    JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!hasUppercase) {
                    JOptionPane.showMessageDialog(panel, "Username must include at least one uppercase letter!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Memeriksa keberadaan username di database
                        boolean exists = connectionLaundry.checkRegistration(username);

                        if (exists) {
                            JOptionPane.showMessageDialog(panel, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                        // Simpan data ke database
                        boolean success = connectionLaundry.saveRegistration(username, password, address, telepon, userType);

                        if (success) {
                            // Menampilkan form login dan menyembunyikan form registrasi
                            JOptionPane.showMessageDialog(panel, "Congratulations, you have successfully registered your account!");
                            loginForm loginForm = new loginForm();
                            loginForm.setVisible(true);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(panel, "Failed to save registration data!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        // ActionListener untuk tombol haveAccountButton
        haveAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm loginForm = new loginForm();
                loginForm.setVisible(true);
                setVisible(false);
            }
        });

        setVisible(true);
    }
}
