import javax.swing.*;
import java.awt.*;

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
    /**
     * Holds the result text.
     */
    private JTextField resultArea;
    /**
     * Holds the operator text.
     */
    private JTextField operatorArea;
    /**
     * Holds the result area text field (for overflow).
     */
    private JScrollPane resultOperatorScrollPane;

    /**
     * Returns the instance of a specified digit button.
     * @param digit the digit button index (corresponds to the actual digit itself)
     * @return the specified digit button.
     */
    public JButton getDigitButton(int digit) {
        return digitButtons[digit];
    }

    /**
     * Returns the instance of the operator text field.
     * @return the operator text field
     */
    public JTextField getOperatorArea() {
        return operatorArea;
    }

    /**
     * Returns the instance of the result text field.
     * @return the result text field
     */
    public JTextField getResultArea() {
        return resultArea;
    }

    /**
     * Returns instance of the result scroll pane.
     * @return the result scroll pane
     */
    public JScrollPane getResultOperatorScrollPane() {
        return resultOperatorScrollPane;
    }

    /**
     * Returns the instance of a specified operator button.
     * @param operator the operator button index
     * @return the specified operator button.
     */
    public JButton getOperatorButton(int operator) {
        return operatorButtons[operator];
    }

    /**
     * Returns the instance of a specified miscellaneous button (not digit nor operator)
     * @param misc the misc button index
     * @return the specified misc button.
     */
    public JButton getMiscButton(int misc) {
        return miscButtons[misc];
    }

    /**
     * Initializes the calculator GUI and makes it visible to the user.
     */
    public CalculatorView(){
        createUIComponents();
        organizeUIComponents();
        setVisible(true);
    }

    /**
     * Initializes all the JComponents and alters the colour/font scheme.
     */
    private void createUIComponents() {
        resultArea = new JTextField();
        resultArea.setEditable(false);

        operatorArea = new JTextField();
        operatorArea.setEditable(false);

        resultOperatorScrollPane = new JScrollPane(resultArea);

        digitButtons = new JButton[]{new JButton("      0"), new JButton("1"), new JButton("2"),
                                     new JButton("3"), new JButton("4"), new JButton("5"),
                                     new JButton("6"), new JButton("7"), new JButton("8"),
                                     new JButton("9")};

        operatorButtons = new JButton[]{new JButton("+"), new JButton("-"), new JButton("×"),
                                        new JButton("÷")};

        //Clear, sign, percent, dot, equals buttons
        miscButtons = new JButton[]{new JButton("C"), new JButton("+/-"), new JButton("%"),
                                    new JButton("."), new JButton("=")};

        //Fonts and colour changes
        Font textFont = new Font("Roboto", Font.PLAIN, 20);
        Color textColorPrimary = new Color(255, 255, 255);
        Color textColorSecondary = new Color(0, 0, 0);

        Color bgColor = new Color(0, 0, 0);
        Color textAreaColor = new Color(255, 255, 255);

        Color digitColor = new Color(60, 60, 60);
        Color digitColorHover = new Color(89, 89, 89);

        Color operationColor = new Color(200, 95, 38);
        Color operationColorHover = new Color(228, 125, 72);

        Color miscColor = new Color(172, 172, 172);
        Color miscColorHover = new Color(209, 209, 209);

        getContentPane().setBackground(bgColor);

        resultArea.setFont(textFont);
        resultArea.setBackground(textAreaColor);
        resultArea.setCaretColor(textAreaColor);
        resultArea.setForeground(textColorSecondary);
        resultOperatorScrollPane.setBorder(BorderFactory.createEmptyBorder());

        operatorArea.setFont(textFont);
        operatorArea.setBackground(textAreaColor);
        operatorArea.setCaretColor(textAreaColor);
        operatorArea.setForeground(textColorSecondary);
        operatorArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (JButton button : digitButtons) {
            button.setFont(textFont);
            button.setBackground(digitColor);
            button.setForeground(textColorPrimary);
            button.setBorder(null);
        }
        digitButtons[0].setHorizontalAlignment(SwingConstants.LEFT); //Special tweaks for alignment of 0 text

        for (JButton button : operatorButtons) {
            button.setFont(textFont);
            button.setBackground(operationColor);
            button.setForeground(textColorPrimary);
            button.setBorder(null);
        }

        for (JButton button : miscButtons) {
            button.setFont(textFont);
            button.setBackground(miscColor);
            button.setForeground(textColorSecondary);
            button.setBorder(null);
        }

        //Special tweaks for the decimal and equals buttons
        miscButtons[3].setBackground(digitColor); miscButtons[3].setForeground(textColorPrimary);
        miscButtons[4].setBackground(operationColor); miscButtons[4].setForeground(textColorPrimary);

        setLocationByPlatform(true);
        setResizable(false);

        setTitle("Calculator");
        setIconImage(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Organizes the layout of all GUI components.
     */
    private void organizeUIComponents() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        //Variables for spacing
        int scrollPaneSize = 270;
        int verticalSize = 60;
        int horizontalSize = 80;
        int gapSize = 15;

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        //Combines the below 3 into a horizontal group
                                        .addGroup(layout.createSequentialGroup()
                                                //Digit buttons 1 and 2 (together horizontally), then digit button 0 in a vertical group
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(digitButtons[1], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(gapSize)
                                                                .addComponent(digitButtons[2], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(digitButtons[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(gapSize)
                                                //Decimal (dot) button and digit button 3 in a vertical group
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(miscButtons[3], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(digitButtons[3], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                .addGap(gapSize)
                                                //Equals button and addition button in a vertical group
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(miscButtons[4], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(operatorButtons[0], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)))
                                        //Combines the 3 vertically
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                //Result area and operator area in a horizontal group
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(resultOperatorScrollPane, GroupLayout.PREFERRED_SIZE, scrollPaneSize, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(gapSize)
                                                        .addComponent(operatorArea, GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                //Combines the below 2 into a horizontal group
                                                .addGroup(layout.createSequentialGroup()
                                                        //Combines the below 2 into a vertical group
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                //Digit buttons 4 and 5 in a horizontal group
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(digitButtons[4], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(gapSize)
                                                                        .addComponent(digitButtons[5], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                                //Combines the below 2 into a horizontal group
                                                                .addGroup(layout.createSequentialGroup()
                                                                        //Digit button 7 and clear button in a vertical group
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(digitButtons[7], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(miscButtons[0], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                                        //Digit button 8 and sign button in a vertical group
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addGap(gapSize)//needed?
                                                                                        .addComponent(digitButtons[8], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)//needed?
                                                                                        .addComponent(miscButtons[1], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)))))
                                                        .addGap(gapSize)
                                                        //Combines the below 2 into a vertical group
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                //Combines the below 2 into a horizontal group
                                                                .addGroup(layout.createSequentialGroup()
                                                                        //Digit button 9 and the percent button in a vertical group
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(digitButtons[9], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(miscButtons[2], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(gapSize)
                                                                        //Division button and multiplication button in a vertical group
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(operatorButtons[3], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(operatorButtons[2], GroupLayout.PREFERRED_SIZE, horizontalSize,GroupLayout.PREFERRED_SIZE)))
                                                                //Digit button 6 and subtraction button in horizontal group
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(digitButtons[6], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(gapSize)
                                                                        .addComponent(operatorButtons[1], GroupLayout.PREFERRED_SIZE, horizontalSize, GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                //Result text field and operator text field
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(resultOperatorScrollPane)
                                        .addComponent(operatorArea, GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)

                                //Clear button, sign button, percent button and division button
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(miscButtons[0], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(miscButtons[1], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(miscButtons[2], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(operatorButtons[3], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)

                                //Digit buttons 7, 8, 9, and the multiplication button
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(digitButtons[7], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[8], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[9], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(operatorButtons[2], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)

                                //Digit buttons 4, 5, 6, and the subtraction button
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(digitButtons[4], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[5], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[6], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(operatorButtons[1], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)

                                //Digit buttons 1, 2, 3, and the addition button
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(digitButtons[1], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[2], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(digitButtons[3], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(operatorButtons[0], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)

                                //Digit button 0, the decimal (dot) button, and the equals button
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(digitButtons[0], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(miscButtons[3], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(miscButtons[4], GroupLayout.PREFERRED_SIZE, verticalSize, GroupLayout.PREFERRED_SIZE))
                                .addGap(gapSize)));

        pack();
    }
}
