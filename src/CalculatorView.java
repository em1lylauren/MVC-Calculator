import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A class that represents the view part of the MVC calculator.
 */
public class CalculatorView extends JFrame {
    /**
     * An array of JButtons representing the digits 0-9.
     */
    private CalculatorButton[] digitButtons;
    /**
     * An array of JButtons representing operations.
     */
    private CalculatorButton[] operatorButtons;
    /**
     * An array of JButtons not considered operations or digits
     */
    private CalculatorButton[] miscButtons;
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
    public CalculatorButton getDigitButton(int digit) {
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
    public CalculatorButton getOperatorButton(int operator) {
        return operatorButtons[operator];
    }

    /**
     * Returns the instance of a specified miscellaneous button (not digit nor operator)
     * @param misc the misc button index
     * @return the specified misc button.
     */
    public CalculatorButton getMiscButton(int misc) {
        return miscButtons[misc];
    }

    /**
     * Initializes the calculator GUI and makes it visible to the user.
     */
    public CalculatorView(){
        createUIComponents();
        organizeUIComponents();
        setVisible(true);
        setFocusable(true);
    }

    /**
     * Initializes all the JComponents and alters the colour/font scheme.
     */
    private void createUIComponents() {
        resultArea = new JTextField();
        resultArea.setHorizontalAlignment(SwingConstants.RIGHT); //Right-aligned text
        resultArea.setEditable(false);
        resultArea.setFocusable(false);

        operatorArea = new JTextField();
        operatorArea.setHorizontalAlignment(SwingConstants.CENTER);
        operatorArea.setEditable(false);
        operatorArea.setFocusable(false);

        resultOperatorScrollPane = new JScrollPane(resultArea);

        digitButtons = new CalculatorButton[]{new CalculatorButton("             0"), new CalculatorButton("1"), new CalculatorButton("2"),
                                              new CalculatorButton("3"), new CalculatorButton("4"), new CalculatorButton("5"),
                                              new CalculatorButton("6"), new CalculatorButton("7"), new CalculatorButton("8"),
                                              new CalculatorButton("9")};

        operatorButtons = new CalculatorButton[]{new CalculatorButton("+"), new CalculatorButton("-"), new CalculatorButton("×"),
                                                 new CalculatorButton("÷")};

        //Clear, sign, percent, dot, equals buttons
        miscButtons = new CalculatorButton[]{new CalculatorButton("C"), new CalculatorButton("+/-"), new CalculatorButton("%"),
                                             new CalculatorButton("."), new CalculatorButton("=")};

        //Fonts and colour changes
        Font textFont = new Font("Microsoft JhengHei UI Light", Font.PLAIN, 21);
        Color textColorPrimary = new Color(255, 255, 255);
        Color textColorSecondary = new Color(0, 0, 0);

        Color bgColor = new Color(21, 21, 21);
        Color textAreaColor = new Color(255, 255, 255);

        Color digitColor = new Color(60, 60, 60);
        Color digitColorClicked = new Color(89, 89, 89);

        Color operationColor = new Color(200, 95, 38);
        Color operationColorClicked = new Color(228, 125, 72);

        Color miscColor = new Color(172, 172, 172);
        Color miscColorClicked = new Color(209, 209, 209);

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

        for (CalculatorButton button : digitButtons) {
            button.setFont(textFont);
            button.setBackground(digitColor);
            button.setBackgroundColorClicked(digitColorClicked);
            button.setForeground(textColorPrimary);
            button.setBorder(null);
            button.setFocusable(false);
        }
        digitButtons[0].setHorizontalAlignment(SwingConstants.LEFT); //Special tweaks for alignment of 0 text

        for (CalculatorButton button : operatorButtons) {
            button.setFont(textFont);
            button.setBackground(operationColor);
            button.setBackgroundColorClicked(operationColorClicked);
            button.setForeground(textColorPrimary);
            button.setBorder(null);
            button.setFocusable(false);
        }

        for (CalculatorButton button : miscButtons) {
            button.setFont(textFont);
            button.setBackground(miscColor);
            button.setBackgroundColorClicked(miscColorClicked);
            button.setForeground(textColorSecondary);
            button.setBorder(null);
            button.setFocusable(false);
        }

        //Special tweaks for the decimal and equals buttons
        miscButtons[3].setBackground(digitColor); miscButtons[3].setBackgroundColorClicked(digitColorClicked); miscButtons[3].setForeground(textColorPrimary);
        miscButtons[4].setBackground(operationColor); miscButtons[4].setBackgroundColorClicked(operationColorClicked); miscButtons[4].setForeground(textColorPrimary);

        setLocationByPlatform(true);
        setResizable(false);

        setTitle("Calculator");
        try {
            setIconImage(ImageIO.read(new File("icon/calculator.png")));
        } catch (IOException e) {
            //Don't do anything if icon doesn't load
        }

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
