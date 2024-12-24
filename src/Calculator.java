import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame frame;
    private JTextField display;
    private StringBuilder currentInput;

    public Calculator() {
        frame = new JFrame("Calculator");
        display = new JTextField();
        currentInput = new StringBuilder();

        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);
        display.setEditable(false);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 4));
        frame.add(buttonsPanel, BorderLayout.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            buttonsPanel.add(button);
        }

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("C")) {
                currentInput.setLength(0);
                display.setText("");
            } else if (command.equals("=")) {
                try {
                    String result = evaluateExpression(currentInput.toString());
                    display.setText(result);
                    currentInput.setLength(0);
                    currentInput.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            } else {
                currentInput.append(command);
                display.setText(currentInput.toString());
            }
        }
    }

    private String evaluateExpression(String expression) {
        // This simple evaluation uses Java's built-in scripting engine (Java 6 and above)
        try {
            return String.valueOf(new javax.script.ScriptEngineManager()
                    .getEngineByName("JavaScript")
                    .eval(expression));
        } catch (Exception e) {
            return "Error";
        }
    }
}
