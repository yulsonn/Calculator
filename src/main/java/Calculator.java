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
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);                     //задаем размеры фрейма
        setLocationRelativeTo(null);                                //задаем отображение по центру экрана
        setTitle("Simple Calculator");                              //задаем название фрейма
        setResizable(false);                                        //делаем размер фрейма неизменным
        panel = new CalcPanel();                                    //создаем панель CalcPanel
        add(panel);                                                 //добавляем панель на фрейм
    }

    class CalcPanel extends JPanel {
        JTextField display = new JTextField();                      //поле для вывода результата

        public CalcPanel() {
            setLayout(new GridBagLayout());                         //задаем layout для CalcPanel

            display.setFont(new Font("calcFont", Font.PLAIN, 20));  //поле вывода:  увеличиваем шрифт
            display.setEditable(false);                             //              делаем неизменяемым
            display.setHorizontalAlignment(JTextField.RIGHT);       //              выравнивание по правому краю
            display.setBackground(Color.WHITE);                     //              background цвет

            result = 0;                                             //вспомогательные поля для вычислений
            lastCommand = "=";
            start = true;

            // создаем массив char-ов с названиями для кнопок
            char[] operations = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    '+',    //10
                    'C',    //11
                    '-',    //12
                    '/',    //13
                    '*',    //14
                    '.',    //15
                    '='};   //16

            // создаем коллекцию кнопок
            ArrayList<JButton> buttons = new ArrayList<JButton>();
            for (int i = 0; i < operations.length; i++) {
                buttons.add(new JButton("" + operations[i]));                           //добавляем в коллекцию кнопки с названиями из массива operations
                buttons.get(i).addActionListener(new ButtonsActionListener());          //добавляем к кнопкам ActionListener-ов
                buttons.get(i).setFont(new Font("calcFont", Font.PLAIN, 17));           //увеличиваем шрифт
            }

            //задаем отображение кнопок на панели CalcPanel
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

            setVisible(true);                                                       //делаем панель видимой
        }
    }


    class ButtonsActionListener implements ActionListener {

        @Override
        //метод actionPerformed проверяет какая кнопка была нажата, в соответствии с этим выполняет необходимые действия
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
                    if (input.equals("-")) {            //проверка на знак - перед числом(отрицательное число)
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


        //метод calculate производит вычисления и выводит результат
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
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //операция отвечающая за окончание программы после закрытия фрейма
        calc. setVisible(true);                                         //делаем фрейм видимым
    }
}

