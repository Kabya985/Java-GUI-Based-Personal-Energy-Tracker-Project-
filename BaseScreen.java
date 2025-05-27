import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


abstract class BaseScreen extends JFrame {

public BaseScreen(String title, int width, int height) {  
    setTitle(title);  
    setSize(width, height);  
    setLocationRelativeTo(null); 
    setDefaultCloseOperation(EXIT_ON_CLOSE);  
    setLayout(new BorderLayout());  
}  

protected JPanel createFooter(String text) {  
    JPanel footer = new JPanel();  
    footer.setBackground(Color.LIGHT_GRAY);  
    footer.add(new JLabel(text));  
    return footer;  
}  

protected JButton createButton(String text) {  
    JButton button = new JButton(text);  
    button.setFont(new Font("Arial", Font.PLAIN, 14));  
    return button;  
}  

abstract void setupUI();  
}