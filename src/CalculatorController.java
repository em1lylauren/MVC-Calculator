/**
 * The controller class for the MVC calculator.
 */
public class CalculatorController  {
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

        //Plus minus mul div

        view.getMiscButton(0).addActionListener(_ -> clearButtonClicked());
        //view.getMiscButton(1).addActionListener(_ -> ); Sign
        //view.getMiscButton(2).addActionListener(_ -> ); Percentage
        view.getMiscButton(3).addActionListener(_ -> decimalButtonClicked());
        //view.getMiscButton(4).addActionListener(_ -> ); Equals

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
     * Updates the view when the clear button is clicked.
     */
    public void clearButtonClicked() {
        model.clearResultField();
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
     * Updates the view using the model's current data.
     */
    public void updateView() {
        view.getResultArea().setText(model.getCurrentResultField());
        view.getOperatorArea().setText(model.getOperationField());
    }

}
