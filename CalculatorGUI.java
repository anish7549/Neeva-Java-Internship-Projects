import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField inputField;
    private String operator;
    private double num1, num2, result;

    public CalculatorGUI() {
        setTitle("Simple Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        getContentPane().setBackground(new Color(60, 63, 65));


        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.BOLD, 30));
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        inputField.setBackground(new Color(43, 43, 43));
        inputField.setForeground(Color.WHITE);
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));


        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(new Color(60, 63, 65));


        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setBackground(new Color(77, 77, 77));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);  // Removes the border focus when clicked
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            button.addActionListener(this);
            buttonPanel.add(button);
        }


        setLayout(new BorderLayout(10, 10));
        add(inputField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == 'C') {
            inputField.setText("");
            operator = "";
            num1 = num2 = result = 0;
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        inputField.setText("Cannot divide by 0");
                        return;
                    }
                    break;
            }
            inputField.setText(String.valueOf(result));
            operator = "";
        } else if ("+-*/".contains(command)) {
            num1 = Double.parseDouble(inputField.getText());
            operator = command;
            inputField.setText("");
        } else {
            inputField.setText(inputField.getText() + command);
        }
    }

    public static void main(String[] args) {
        CalculatorGUI calculator = new CalculatorGUI();
        calculator.setVisible(true);
    }
}
