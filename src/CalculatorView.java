import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A class that represents the view part of the MVC calculator.
 */
public class CalculatorView extends JFrame {
    /**
     * An array of JButtons representing the digits 0-9.
     */
    private JButton[] digitButtons;
    /**
     * An array of JButtons representing operations.
     */
    private JButton[] operatorButtons;
    /**
     * An array of JButtons not considered operations or digits
     */
    private JButton[] miscButtons;

    private JTextField resultArea;
    private JTextField operatorArea;
    private JScrollPane resultOperatorScrollPane;

    public CalculatorView(){
        createUIComponents();
        organizeUIComponents();
        setVisible(true);
    }

    private void createUIComponents() {
        resultArea = new JTextField();
        resultArea.setEditable(false);

        operatorArea = new JTextField();
        operatorArea.setEditable(false);

        resultOperatorScrollPane = new JScrollPane(resultArea);

        digitButtons = new JButton[]{new JButton("0"), new JButton("1"), new JButton("2"),
                                     new JButton("3"), new JButton("4"), new JButton("5"),
                                     new JButton("6"), new JButton("7"), new JButton("8"),
                                     new JButton("9")};

        operatorButtons = new JButton[]{new JButton("+"), new JButton("-"), new JButton("*"),
                                        new JButton("/")};

        //Clear, sign, percent, dot, equals buttons
        miscButtons = new JButton[]{new JButton("C"), new JButton("+/-"), new JButton("%"),
                                    new JButton("."), new JButton("=")};

        setLocationByPlatform(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void organizeUIComponents() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        pack();
    }

    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
    }
}
