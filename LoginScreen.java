import javax.swing.*;
import java.awt.*;

public class LoginScreen extends BaseScreen {
    JTextField emailField;
    JComboBox<String> themeSelector;

    public LoginScreen() {
        super("Personal Energy Tracker - Login", 400, 300);
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Get Started with Personal Energy Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Enter Email:");
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));

        JLabel themeLabel = new JLabel("Select Theme:");
        String[] themes = {"Light", "Dark"};
        themeSelector = new JComboBox<>(themes);
        themeSelector.setMaximumSize(new Dimension(150, 30));

        JButton nextButton = createButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(e -> {
            new UserInfoScreen(emailField.getText(), (String) themeSelector.getSelectedItem());
            dispose();
        });

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(themeLabel);
        mainPanel.add(themeSelector);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(nextButton);

        add(mainPanel);
    }



    public static void main(String[] args) {
        new LoginScreen();
    }
}