/*  Calculator.java
    GUI for CS310 Spring 2015 project #3
    Code by Alan Riggins
*/    

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import data_structures.*;

public class Calculator extends JFrame {
    static final long serialVersionUID = 0x0000000000000001;
    
    protected JLabel label;
    protected JButton [] buttons;
    protected JPanel buttonPanel;
    protected ExpressionEvaluator evaluator;
    
    public Calculator() {
        evaluator = new ExpressionEvaluator();
        setWindowAttributes();
        JPanel contentPanel = getContentPanel();
        addComponents(contentPanel);
        setVisible(true);
        setFocusTraversalKeysEnabled(false);
        }
        
    private void setWindowAttributes() {
        setLookAndFeel();
        setTitle("Calculator");
        setSize(350,350);
        setLocation(50,50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        
    private void setLookAndFeel() {

        try {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
            }
        catch(Exception e) {
        System.out.println("Sorry, LookAndFeel not found.");       
            }
        }
        
    private JPanel getContentPanel() {
        JPanel panel = (JPanel) getContentPane();
        BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        panel.setLayout(layout);
        panel.setBorder(new LineBorder(Color.gray,10));
        panel.setBackground(Color.gray);        
        return panel;
        }
        
    private void addComponents(JPanel panel) {
        label = new JLabel(" ", SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(250,40));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(Color.white);      
        panel.add(label, BorderLayout.NORTH);
        GridLayout gridLayout = new GridLayout(4,5);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);
        buttonPanel = new JPanel(gridLayout);
        buttons = new JButton[20];
        buttons[0] = new JButton("7");
        buttons[1] = new JButton("8");
        buttons[2] = new JButton("9");
        buttons[3] = new JButton("+"); 
        buttons[4] = new JButton("^");               
        buttons[5] = new JButton("4"); 
        buttons[6] = new JButton("5");
        buttons[7] = new JButton("6");
        buttons[8] = new JButton("-");
        buttons[9] = new JButton("(");                
        buttons[10] = new JButton("1");
        buttons[11] = new JButton("2");
        buttons[12] = new JButton("3");
        buttons[13] = new JButton("*"); 
        buttons[14] = new JButton(")");               
        buttons[15] = new JButton("0");
        buttons[16] = new JButton(".");
        buttons[17] = new JButton("=");
        buttons[18] = new JButton("/");
        buttons[19] = new JButton("C");                 
        
        for(int i=0; i < 20; i++) {
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ButtonHandler());
            buttons[i].addKeyListener(new KeyProcessor());
            }
            
        panel.add(buttonPanel, BorderLayout.CENTER);                              

    }
        
    public class ButtonHandler implements ActionListener {        
        public void actionPerformed(ActionEvent e) {
            String key = e.getActionCommand();
            if(key.equals("C"))
                label.setText(" ");
            else if(key.equals("+") || key.equals("-")
                  || key.equals("*") || key.equals("/")
                  || key.equals("^") || key.equals("(")
                  || key.equals(")")) {
                  String IOText = label.getText();
                  IOText += " " + key + " ";
                  label.setText(IOText);
                  }
            else if(key.equals("=")) {
                String IOText = label.getText();
                IOText = evaluator.processInput(IOText); 
                label.setText(IOText);
                }
            else {
                String IOText = label.getText();
                IOText += key;
                label.setText(IOText);
                }
            }
        }
            
    public class KeyProcessor implements KeyListener {  
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if(c =='c' || c == KeyEvent.VK_ESCAPE)
                label.setText(" ");
            else if(c == '+' || c == '-'
                  || c == '*' || c == '/'
                  || c == '^' || c == '('
                  || c == ')') {
                  String IOText = label.getText();
                  IOText += " " + c + " ";
                  label.setText(IOText);
                  }
            else if(c == '=' || c == KeyEvent.VK_ENTER) {
                String IOText = label.getText();
                IOText = evaluator.processInput(IOText); 
                label.setText(IOText);
                }
            else {
                if((c < '0' || c > '9') && (c != '.')) return;
                String IOText = label.getText();
                IOText += c;
                label.setText(IOText);
                }                                       
        }
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        }          
       
public static void main(String [] args) {
    new Calculator();
    }
}
