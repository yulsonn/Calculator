import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 250;

    private double result;
    private String lastCommand;
    private boolean start;

    private CalcPanel panel;

    public Calculator() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);                                //show frame in the center of screen
        setTitle("Simple Calculator");
        setResizable(false);
        panel = new CalcPanel();
        add(panel);
    }

    class CalcPanel extends JPanel {
        JTextField display = new JTextField();                      //field for results

        public CalcPanel() {
            setLayout(new GridBagLayout());

            display.setFont(new Font("calcFont", Font.PLAIN, 20));
            display.setEditable(false);
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setBackground(Color.WHITE);

            result = 0;                                             //auxiliary fields for calculations
            lastCommand = "=";
            start = true;

            // create char-array for the buttons names
            char[] operations = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    '+',    //10
                    'C',    //11
                    '-',    //12
                    '/',    //13
                    '*',    //14
                    '.',    //15
                    '='};   //16

            // create collection of buttons
            ArrayList<JButton> buttons = new ArrayList<JButton>();
            for (int i = 0; i < operations.length; i++) {
                buttons.add(new JButton("" + operations[i]));
                buttons.get(i).addActionListener(new ButtonsActionListener());
                buttons.get(i).setFont(new Font("calcFont", Font.PLAIN, 17));
            }

            //define location of buttons on CalcPanel
            add(display, new GridBagConstraints(0, 0, 4, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(7), new GridBagConstraints(0, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(8), new GridBagConstraints(1, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(9), new GridBagConstraints(2, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(13), new GridBagConstraints(3, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(4), new GridBagConstraints(0, 2, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(5), new GridBagConstraints(1, 2, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(6), new GridBagConstraints(2, 2, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(14), new GridBagConstraints(3, 2, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(1), new GridBagConstraints(0, 4, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(2), new GridBagConstraints(1, 4, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(3), new GridBagConstraints(2, 4, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(12), new GridBagConstraints(3, 4, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(0), new GridBagConstraints(0, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(15), new GridBagConstraints(1, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(11), new GridBagConstraints(2, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(10), new GridBagConstraints(3, 5, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            add(buttons.get(16), new GridBagConstraints(0, 6, 4, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(2, 2, 2, 2), 0, 0));

            setVisible(true);
        }
    }


    class ButtonsActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            boolean isDigit = Character.isDigit(input.charAt(0));

            if (isDigit || input.equals(".")) {
                if (start) {
                    panel.display.setText("");
                    start = false;
                }
                panel.display.setText(panel.display.getText() +input);

            } else if (input.equals("C")) {
                panel.display.setText("");
                result = 0;
                lastCommand = "=";
                start = true;

            } else {
                if (start) {
                    if (input.equals("-")) {            //check for negative number
                        panel.display.setText(input);
                        start = false;
                    } else {
                        lastCommand = input;
                    }
                } else {
                    calculate(Double.parseDouble(panel.display.getText()));
                    lastCommand = input;
                    start = true;
                }
            }
        }


        //calculate and display the result
        public void calculate(double x) {
            if (lastCommand.equals("+")) {
                result += x;
            } else if (lastCommand.equals("-")) {
                result -= x;
            } else if (lastCommand.equals("*")) {
                result *= x;
            } else if (lastCommand.equals("/")) {
                result /= x;
            } else if (lastCommand.equals("=")) {
                result = x;
            }
            panel.display.setText("" + result);
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc. setVisible(true);
    }
}

