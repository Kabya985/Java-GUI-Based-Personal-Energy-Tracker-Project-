import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class WaterSleepScreen extends BaseScreen {
    JTextField waterField, sleepField, screenTimeField;
    int steps;
    double bmr;
    String mood;

    public WaterSleepScreen(int steps, double bmr, String mood) {
        super("Hydration & Sleep", 400, 300);
        this.steps = steps;
        this.bmr = bmr;
        this.mood = mood;
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        waterField = new JTextField();
        sleepField = new JTextField();
        screenTimeField = new JTextField();

        centerPanel.add(new JLabel("Water Intake (glasses):"));
        centerPanel.add(waterField);
        centerPanel.add(new JLabel("Sleep Hours:"));
        centerPanel.add(sleepField);
        centerPanel.add(new JLabel("Screen Time (hours):"));
        centerPanel.add(screenTimeField);

        JButton nextButton = createButton("Next");
        nextButton.addActionListener(e -> {
            try {
                int water = Integer.parseInt(waterField.getText());
                double sleep = Double.parseDouble(sleepField.getText());
                double screenTime = Double.parseDouble(screenTimeField.getText());
                String tip = generateTips(water, sleep, screenTime);

                JOptionPane.showMessageDialog(this, tip, "Energy Tips", JOptionPane.INFORMATION_MESSAGE);
                new WellnessReport(steps, bmr, mood, water, sleep, (int) screenTime);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields!");
            }
        });

        centerPanel.add(new JLabel());
        centerPanel.add(nextButton);

        add(centerPanel, BorderLayout.CENTER);
        add(createFooter("Stay Healthy & Energized!"), BorderLayout.SOUTH);
    }

    private String generateTips(int water, double sleep, double screenTime) {
        StringBuilder tips = new StringBuilder("Today's Energy Tips:\n\n");
        if (water < 6) {
            tips.append("- Drink more water! Aim for 6–8 glasses a day.\n");
        } else {
            tips.append("- Great job staying hydrated!\n");
        }

        if (sleep < 6) {
            tips.append("- Try to get at least 7 hours of sleep.\n");
        } else if (sleep > 9) {
            tips.append("- You slept well, but oversleeping can reduce energy.\n");
        } else {
            tips.append("- Perfect sleep range! Keep it up.\n");
        }

        if (screenTime > 3) {
            tips.append("- Reduce screen time to avoid eye strain and fatigue.\n");
        } else {
            tips.append("- Good screen management!\n");
        }

        return tips.toString();
    }
}