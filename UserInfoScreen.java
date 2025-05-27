import javax.swing.*;
import java.awt.*;

public class UserInfoScreen extends BaseScreen {
    private JTextField ageField, weightField, heightField;
    private JComboBox<String> genderBox;
    private String email, theme;

    public UserInfoScreen(String email, String theme) {
        super("User Info", 400, 300);
        this.email = email;
        this.theme = theme;
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        setLayout(new GridLayout(6, 2, 5, 5)); 

        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        ageField = new JTextField();
        weightField = new JTextField();
        heightField = new JTextField();

        add(new JLabel("Gender:")); add(genderBox);
        add(new JLabel("Age:")); add(ageField);
        add(new JLabel("Weight (kg):")); add(weightField);
        add(new JLabel("Height (cm):")); add(heightField);

        JButton nextButton = createButton("Next");
        nextButton.addActionListener(e -> goToBMRScreen());
        add(new JLabel()); 
        add(nextButton);
    }

    private void goToBMRScreen() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());
            String gender = (String) genderBox.getSelectedItem();

            new BMRCalculateScreen(weight, height, age, gender);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numerical inputs.");
        }
    }
}