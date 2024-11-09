import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton developerInfoButton;
    private JButton registerButton;

    public LoginGUI() {
        setTitle("User Login");
        setSize(960, 530);  // Match frame size with Scheduling GUI
        setResizable(false);
        setLocationRelativeTo(null);

        // Panel with solid background color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(8, 79, 123)); // Dark blue background
        panel.setLayout(null);

        // Title labels for "CPU Scheduling" and "Simulation"
        JLabel titleLabel = new JLabel("CPU Scheduling", JLabel.CENTER);
        titleLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(280, 10, 400, 60);
        panel.add(titleLabel);

        JLabel subTitleLabel = new JLabel("Simulation", JLabel.CENTER);
        subTitleLabel.setFont(new Font("Bookman Old Style", Font.ITALIC, 18));
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setBounds(410, 80, 120, 30);
        panel.add(subTitleLabel);

        // Input label and text field for email
        JLabel emailLabel = new JLabel("Gmail");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(320, 180, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(450, 180, 200, 30);
        panel.add(emailField);

        // Input label and text field for password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(320, 230, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(450, 230, 200, 30);
        panel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(420, 290, 120, 40);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginUser();
            }
        });
        panel.add(loginButton);

        // "Not Registered?" label and Register button
        JLabel notRegisteredLabel = new JLabel("Not Registered?");
        notRegisteredLabel.setForeground(Color.WHITE);
        notRegisteredLabel.setBounds(380, 350, 100, 30);
        panel.add(notRegisteredLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(490, 350, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openRegisterScreen();
            }
        });
        panel.add(registerButton);

        // Developer Info button
        developerInfoButton = new JButton("Developer Info");
        developerInfoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        developerInfoButton.setBounds(10, 450, 150, 30);
        developerInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Developer:\n\nPrajwal Khatiwada, BCA-VI.");
            }
        });
        panel.add(developerInfoButton);

        // Add the panel to the frame
        add(panel);
    }

    private void loginUser() {
        try {
            String urlStr = "http://localhost/user_auth/login.php";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emailField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(new String(passwordField.getPassword()), "UTF-8");

            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))) {
                out.write(data);
                out.flush();
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                new SchedulingGUI().setVisible(true); // Redirect to Scheduling GUI on successful login
                this.dispose(); // Close the login screen
            } else {
                JOptionPane.showMessageDialog(this, "Login failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void openRegisterScreen() {
        // Assuming you have a class for the register screen
        new RegistrationGUI().setVisible(true);
        this.dispose(); // Close the login screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
