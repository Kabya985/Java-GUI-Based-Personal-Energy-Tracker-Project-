import javax.swing.*;
import java.awt.*;

public class StepEntryScreen extends BaseScreen {
    private JTextField stepsField;
    private int stepGoal;
    private double bmr;

    public StepEntryScreen(int stepGoal, double bmr) {
        super("Steps Entry", 400, 220);
        this.stepGoal = stepGoal;
        this.bmr = bmr;
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel stepLabel = new JLabel("Enter Today's Steps:");
        stepLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stepLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        stepsField = new JTextField();
        stepsField.setMaximumSize(new Dimension(200, 30));
        stepsField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextButton = createButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(e -> processSteps());

        panel.add(stepLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(stepsField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(nextButton);

        add(panel);
    }


    private void processSteps() {
        try {
            int steps = Integer.parseInt(stepsField.getText());
            double caloriesBurned = steps * 0.04;
            String message = steps >= stepGoal
                    ? "Congratulations! You met your goal."
                    : "Try to walk more tomorrow.";
            JOptionPane.showMessageDialog(this, message + "\nCalories burned: " + String.format("%.2f", caloriesBurned));
            new MealInputScreen(steps, bmr);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number of steps.");
        }
    }
}


