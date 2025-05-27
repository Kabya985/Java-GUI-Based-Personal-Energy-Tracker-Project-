import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class WellnessReport extends BaseScreen {

    private int steps, water, screenTime;
    private double bmr, sleep;
    private String mood;

    WellnessReport(int steps, double bmr, String mood, int water, double sleep, int screenTime) {
        super("Daily Wellness Report", 500, 450);
        this.steps = steps;
        this.bmr = bmr;
        this.mood = mood;
        this.water = water;
        this.sleep = sleep;
        this.screenTime = screenTime;

        setupUI();  
        setVisible(true);
    }

    @Override
    void setupUI() {
        JLabel title = new JLabel("Your Wellness Summary", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        dataPanel.add(new JLabel("Steps Taken:"));    dataPanel.add(new JLabel(steps + " steps"));
        dataPanel.add(new JLabel("BMR:"));            dataPanel.add(new JLabel(String.format("%.2f kcal", bmr)));
        dataPanel.add(new JLabel("Mood:"));           dataPanel.add(new JLabel(mood));
        dataPanel.add(new JLabel("Water Intake:"));   dataPanel.add(new JLabel(water + " glasses"));
        dataPanel.add(new JLabel("Sleep Duration:")); dataPanel.add(new JLabel(sleep + " hours"));
        dataPanel.add(new JLabel("Screen Time:"));    dataPanel.add(new JLabel(screenTime + " hours"));
        add(dataPanel, BorderLayout.CENTER);

        int score = calculateEnergyScore();
        String tips = generateTips();

        JTextArea result = new JTextArea("Energy Score: " + score + "/100\n\n" + tips);
        result.setWrapStyleWord(true);
        result.setLineWrap(true);
        result.setEditable(false);
        result.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(result);
        scroll.setPreferredSize(new Dimension(480, 120));

        JButton finishBtn = createButton("Finish");
        finishBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        finishBtn.addActionListener(e -> {
            dispose();
            new FinalScreen();  
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scroll, BorderLayout.CENTER);
        bottomPanel.add(finishBtn, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private int calculateEnergyScore() {
        int score = 100;
        if (water < 6) score -= 10;
        if (sleep < 6 || sleep > 9) score -= 10;
        if (screenTime > 3) score -= 10;
        if (steps < 5000) score -= 10;
        return Math.max(score, 0);
    }

    private String generateTips() {


        StringBuilder tips = new StringBuilder("Wellness Tips:\n\n");
        if (water < 6) tips.append("- Drink more water! Aim for 6-8 glasses.\n");
        else tips.append("- Great job staying hydrated!\n");
        if (sleep < 6) tips.append("- Try to get at least 7 hours of sleep.\n");
        else if (sleep > 9) tips.append("- Oversleeping? Keep it in balance!\n");
        else tips.append("- Perfect sleep range! Keep it up.\n");
        if (screenTime > 3) tips.append("- Reduce screen time to avoid eye strain.\n");
Hide quoted text
        else tips.append("- Good screen management!\n");
        return tips.toString();
    }
}