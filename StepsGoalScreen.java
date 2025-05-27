import javax.swing.*;
import java.awt.*;

public class StepsGoalScreen extends BaseScreen {
    private JComboBox<String> goalBox;
    private double bmr;

    public StepsGoalScreen(double bmr) {
        super("Daily Steps Goal", 400, 200);
        this.bmr = bmr;
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 

        JLabel goalLabel = new JLabel("Select Daily Steps Goal:");
        goalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goalLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] options = {"3000", "5000", "7000", "10000"};
        goalBox = new JComboBox<>(options);
        goalBox.setMaximumSize(new Dimension(200, 30));
        goalBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextButton = createButton("Next");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(e -> {
            int goal = Integer.parseInt((String) goalBox.getSelectedItem());
            new StepEntryScreen(goal, bmr);
            dispose();
        });

        mainPanel.add(goalLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(goalBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(nextButton);

        add(mainPanel);
    }
}