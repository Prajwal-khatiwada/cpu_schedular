import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TermsAndConditionsGUI extends JFrame {

    public TermsAndConditionsGUI() {
        initComponents();
        setTitle("Terms and Conditions");
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame
    }

    private void initComponents() {
        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30)); // Dark background
        panel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Terms and Conditions", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Text Area
        JTextArea termsTextArea = new JTextArea();
        termsTextArea.setText("""
                Please read the terms and conditions carefully.

                1. By using this software, you agree to the collection and processing of personal 
                   information provided during registration.
                2. The information collected may be used by the first party or shared with 
                   third-party entities for marketing purposes.
                3. All reasonable measures will be taken to protect the confidentiality of your data. 
                   However, the user accepts the risks of data sharing as outlined here.
                4. Continuing usage implies agreement to these terms.
                
                If you have any concerns, please contact prazwolkhatiwadaGmail.com.
                """);
        termsTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        termsTextArea.setForeground(Color.BLACK);
        termsTextArea.setEditable(false); // Make it non-editable
        termsTextArea.setLineWrap(true);
        termsTextArea.setWrapStyleWord(true);

        // Scroll Pane for Terms
        JScrollPane scrollPane = new JScrollPane(termsTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(50, 150, 200));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to Registration GUI
                new RegistrationGUI().setVisible(true);
                dispose(); // Close the Terms and Conditions window
            }
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add to Frame
        getContentPane().add(panel);
        setSize(600, 400); // Frame size
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TermsAndConditionsGUI().setVisible(true));
    }
}
