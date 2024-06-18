import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The controller class for the MVC calculator.
 */
public class CalculatorController implements KeyListener {
    /**
     * The model in the MVC pattern.
     */
    private final CalculatorModel model;
    /**
     * The view in the MVC pattern.
     */
    private final CalculatorView view;

    /**
     * Creates an instance of the CalculatorController
     * @param model the calculator's model
     * @param view the calculator's view
     */
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        //Adding ActionListeners to the buttons
        for (int i = 0; i<10; i++) {
            int digit = i;
            view.getDigitButton(i).addActionListener(_ -> digitButtonClicked(digit));
        }

        for (int i = 0; i<4; i++) {
            int operation = i;
            view.getOperatorButton(operation).addActionListener(_ -> operationButtonClicked(view.getOperatorButton(operation).getText()));
        }

        view.getMiscButton(0).addActionListener(_ -> clearButtonClicked());
        view.getMiscButton(1).addActionListener(_ -> signButtonClicked());
        view.getMiscButton(2).addActionListener(_ -> percentageButtonClicked());
        view.getMiscButton(3).addActionListener(_ -> decimalButtonClicked());
        view.getMiscButton(4).addActionListener(_ -> equalsButtonClicked());
        view.addKeyListener(this);

        updateView();
    }

    /**
     * Updates the view when any of the digit buttons are clicked.
     * @param digit the digit value itself
     */
    public void digitButtonClicked(int digit) {
        model.changeResultField(digit);
        updateView();
    }

    /**
     * Updates the view when any of the operator buttons are clicked.
     * @param operation the operator
     */
    public void operationButtonClicked(String operation) {
        model.changeOperationField(operation);
        updateView();
    }

    /**
     * Updates the view when the clear button is clicked.
     */
    public void clearButtonClicked() {
        model.clearResultField();
        updateView();
    }

    /**
     * Updates the view when the sign button is clicked.
     */
    public void signButtonClicked() {
        model.changeSign();
        updateView();
    }

    /**
     * Updates the view when the percent button is clicked.
     */
    public void percentageButtonClicked() {
        model.changeToPercent();
        updateView();
    }

    /**
     * Updates the view when the decimal button is clicked.
     */
    public void decimalButtonClicked() {
        model.addDecimalPoint();
        updateView();
    }

    /**
     * Updates the view when the equals button is clicked.
     */
    public void equalsButtonClicked() {
        model.calculate();
        updateView();
    }

    /**
     * Updates the view using the model's current data.
     */
    public void updateView() {
        view.getResultArea().setText(model.getCurrentResultField());
        view.getOperatorArea().setText(model.getOperationField());
    }

    //Keyboard support
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            //Digits
            case '0':
                digitButtonClicked(0);
                break;
            case '1':
                digitButtonClicked(1);
                break;
            case '2':
                digitButtonClicked(2);
                break;
            case '3':
                digitButtonClicked(3);
                break;
            case '4':
                digitButtonClicked(4);
                break;
            case '5':
                digitButtonClicked(5);
                break;
            case '6':
                digitButtonClicked(6);
                break;
            case '7':
                digitButtonClicked(7);
                break;
            case '8':
                digitButtonClicked(8);
                break;
            case '9':
                digitButtonClicked(9);
                break;

            //Operations
            case '+':
                operationButtonClicked("+");
                break;
            case '-':
                operationButtonClicked("-");
                break;
            case '*':
                operationButtonClicked("×");
                break;
            case '/':
                operationButtonClicked("÷");
                break;

            //Misc
            case 'C':
            case 'c':
                clearButtonClicked();
                break;
            case '%':
                percentageButtonClicked();
                break;
            case '.':
                decimalButtonClicked();
                break;
            case '=':
                equalsButtonClicked();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Misc
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                equalsButtonClicked();
                break;
            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_DELETE:
                clearButtonClicked();
                break;
            case KeyEvent.VK_SHIFT:
                signButtonClicked();
                break;
        }
    }
}
