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
        for (int i = 0; i<9; i++) {
            int digit = i;
            view.getDigitButton(i).addActionListener(_ -> digitButtonClicked(digit));
        }

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
     * Updates the view using the model's current data.
     */
    public void updateView() {
        view.getResultArea().setText(model.getCurrentResultField());
        view.getOperatorArea().setText(model.getOperationField());
    }

}
