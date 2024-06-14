/**
 * The model of the calculator.
 */
public class CalculatorModel {
    /**
     * The current number and first operand for any operation.
     */
    private Number currentNumber;
    /**
     * The second number and second operand for any operation.
     */
    private Number nextNumber;
    /**
     * The current operation selected.
     */
    private String operation;

    /**
     * An array of operations in the calculator.
     */
    private final OperationButton[] operations = {new OperationButton("ADD"),
                                                  new OperationButton("SUB"),
                                                  new OperationButton("MUL"),
                                                  new OperationButton("DIV"),};

    /**
     * An array of numbers (digits) on the calculator.
     */
    private final NumberButton[] numbers = {new NumberButton(0), new NumberButton(1), new NumberButton(2),
                                            new NumberButton(3), new NumberButton(4), new NumberButton(5),
                                            new NumberButton(6), new NumberButton(7), new NumberButton(8),
                                            new NumberButton(9)};

    /**
     * Creates a new instance of the Calculator model.
     */
    public CalculatorModel() {
        this.currentNumber = 0;
        this.nextNumber = 0;
        this.operation = "NONE";
    }


}
