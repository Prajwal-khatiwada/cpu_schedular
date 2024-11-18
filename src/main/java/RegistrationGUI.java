import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegistrationGUI extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, institutionField, facultyField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, loginButton, developerInfoButton;

    public RegistrationGUI() {
        setTitle("User Registration");
        setResizable(false);
        setLayout(null);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(8, 79, 123));

        // Heading Texts
        JLabel cpuSchedulingLabel = new JLabel("CPU Scheduling", SwingConstants.CENTER);
        cpuSchedulingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        cpuSchedulingLabel.setForeground(Color.WHITE);
        cpuSchedulingLabel.setBounds(0, 10, 1000, 50);
        add(cpuSchedulingLabel);

        JLabel simulationLabel = new JLabel("Simulation", SwingConstants.CENTER);
        simulationLabel.setFont(new Font("Arial", Font.BOLD, 30));
        simulationLabel.setForeground(Color.WHITE);
        simulationLabel.setBounds(0, 50, 1000, 50);
        add(simulationLabel);

        // Panel for the input fields in two columns
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 20, 20));
        panel.setBounds(200, 150, 600, 200);
        panel.setBackground(new Color(8, 79, 123));

        // Add labels and text fields in the specified two-column layout
        panel.add(addLabel("First Name"));
        firstNameField = addTextField();
        panel.add(firstNameField);

        panel.add(addLabel("Last Name"));
        lastNameField = addTextField();
        panel.add(lastNameField);

        panel.add(addLabel("Gmail"));
        emailField = addTextField();
        panel.add(emailField);

        panel.add(addLabel("Institution"));
        institutionField = addTextField();
        panel.add(institutionField);

        panel.add(addLabel("Faculty/Course"));
        facultyField = addTextField();
        panel.add(facultyField);

        panel.add(addLabel("Password"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(addLabel("Confirm Password"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        // Add panel to the frame
        add(panel);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(450, 400, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerUser();
            }
        });
        add(registerButton);

        // Terms and Conditions Link
        // Terms and Conditions Link
        JLabel termsLabel = new JLabel(
                "<html><u><span style='color:white;'>I agree with <a href='#' style='color:white;'>Terms and Conditions</a></span></u></html>");
        termsLabel.setForeground(Color.WHITE);
        termsLabel.setBounds(360, 440, 280, 30);
        termsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        termsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openTermsAndConditionsScreen();
            }
        });
        add(termsLabel);


        // "Already have an Account?" and Login Button
        JLabel alreadyHaveAccountLabel = new JLabel("Already have an Account?", SwingConstants.CENTER);
        alreadyHaveAccountLabel.setForeground(Color.WHITE);
        alreadyHaveAccountLabel.setBounds(0, 490, 1000, 30);
        add(alreadyHaveAccountLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(450, 530, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openLoginScreen();
            }
        });
        add(loginButton);

        // Developer Info Button at the bottom left of the screen
        developerInfoButton = new JButton("Developer Info");
        developerInfoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        developerInfoButton.setBounds(10, 620, 150, 30); // Positioned at the bottom left
        developerInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Developer:\n\nPrajwal Khatiwada, BCA-VI.");
            }
        });
        add(developerInfoButton);
    }

    private JLabel addLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField addTextField() {
        return new JTextField();
    }

    private void registerUser() {
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String urlStr = "http://localhost/user_auth/register.php";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Form the data to be sent
            String data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstNameField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastNameField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emailField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("institution", "UTF-8") + "=" + URLEncoder.encode(institutionField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("faculty", "UTF-8") + "=" + URLEncoder.encode(facultyField.getText(), "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            // Send the data
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))) {
                out.write(data);
                out.flush();
            }

            // Check response from server
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void openLoginScreen() {
        // Open the login screen when the button is clicked
        new LoginGUI().setVisible(true);
        this.setVisible(false); // Close registration screen
    }

    private void openTermsAndConditionsScreen() {
        // Open the terms and conditions screen
        new TermsAndConditionsGUI().setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationGUI().setVisible(true);
    }
}
