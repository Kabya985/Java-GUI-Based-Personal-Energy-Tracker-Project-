import javax.swing.*;
import java.awt.*;

public class MoodSettingsScreen extends BaseScreen {
    JComboBox<String> moodBox;
    int steps;
    double bmr;

    public MoodSettingsScreen(int steps, double bmr) {
        super("Mood Settings", 400, 250);
        this.steps = steps;
        this.bmr = bmr;
        setupUI();
        setVisible(true);
    }

    @Override
    void setupUI() {
        JLabel moodLabel = new JLabel("How is your mood today?");
        moodBox = new JComboBox<>(new String[]{"Happy", "Sad", "Tired", "Stressed", "Neutral"});

        JLabel tipLabel = new JLabel("Select a mood to see your energy tip!");
        tipLabel.setForeground(Color.BLUE);
        tipLabel.setVerticalAlignment(SwingConstants.CENTER);

        moodBox.addActionListener(e -> {
            String mood = (String) moodBox.getSelectedItem();
            String tip = getMoodTip(mood);
            tipLabel.setText(" " + tip);
        });

        JButton nextButton = createButton("Next");
        nextButton.addActionListener(e -> {
            String selectedMood = (String) moodBox.getSelectedItem();
            new WaterSleepScreen(steps, bmr, selectedMood); 
            dispose(); 
        });

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(moodLabel);
        centerPanel.add(moodBox);
        centerPanel.add(tipLabel);
        centerPanel.add(nextButton);

        add(centerPanel, BorderLayout.CENTER);
        add(createFooter("Stay positive!"), BorderLayout.SOUTH);
    }

    private String getMoodTip(String mood) {
        return switch (mood) {
            case "Happy" -> "Great! Keep that energy up with a light walk or some deep breaths!";
            case "Sad" -> "Take it easy today. Listen to music or journal your thoughts.";
            case "Tired" -> "Try a short nap or hydrate well!";
            case "Stressed" -> "Take 5 minutes to breathe deeply or try a short stretch break.";
            case "Neutral" -> "Maintain balance with hydration and light activity!";
            default -> "Stay mindful and take care of yourself.";
        };
    }
}