/**
 * The model of the calculator.
 */
public class CalculatorModel {
    /**
     * The max length of the string in the result text area.
     */
    private final int maxDigits = 22;
    /**
     * Text representing the current number in the result text area.
     */
    private String currentResultField;
    /**
     * Text representing the temporary variable to hold the first operand value entered.
     */
    private String prevResultField;
    /**
     * Text representing the current operation selected.
     */
    private String operationField;
    /**
     * A boolean value marking whether currentResultField is to be replaced with or concatenated with the next digit entered.
     */
    private boolean isFirst;

    /**
     * Creates a new instance of the Calculator model.
     */
    public CalculatorModel() {
        this.currentResultField = "0";
        this.prevResultField = "";
        this.operationField = "";
        this.isFirst = true;
    }

    /**
     * Changes the number displayed in the result text field.
     * @param n the digit to replace/concatenate onto the existing number
     */
    public void changeResultField(int n) {
        //Only allow addition of more digits if it's under the max limit
        if (getCurrentResultField().length() < maxDigits) {
            //Case: digit replaces existing text OR number in result field is already 0
            if (isFirst || getCurrentResultField().equals("0")) {
                setCurrentResultField(String.valueOf(n));
                this.isFirst = false;

            } else { //Case: digit is concatenated after existing text
                setCurrentResultField(getCurrentResultField() + n);
            }
        }
    }

    /**
     * Changes the operation displayed in the operation text field.
     * @param operation the mathematical operation to be put in the operation text field
     */
    public void changeOperationField(String operation) {
        //Hold the current value in another variable for later
        setPrevResultField(getCurrentResultField());

        setOperationField(operation);
        this.isFirst = true; //In preparation for the second number, so the current one disappears from the display
    }

    /**
     * Performs the calculation.
     */
    public void calculate() {
        //Make sure there is an operation to complete
        if (!getOperationField().isEmpty()) {
            System.out.println("First operand: " + getPrevResultField());
            System.out.println("Second operand: " + getCurrentResultField());

            Double firstOperand = Double.parseDouble(getPrevResultField());
            Double secondOperand = Double.parseDouble(getCurrentResultField());
            char operation = getOperationField().charAt(0);

            Double result = calculateResult(firstOperand, secondOperand, operation);

            setCurrentResultField(String.valueOf(result)); //Show new result
            setOperationField("");

            this.isFirst = true; //To not add on to result
        }
    }

    /**
     * Performs the actual calculation of two numbers and an operator.
     * @param firstOperand the first operand
     * @param secondOperand the second operand
     * @param operation the operation to be performed
     * @return the result of the operation
     * @throws ArithmeticException when attempting to divide by zero
     */
    public Double calculateResult(Double firstOperand, Double secondOperand, char operation) throws ArithmeticException {
        //Calculate based on given operand
        switch (operation) {
            case '+':
                return firstOperand + secondOperand;

            case '-':
                return firstOperand - secondOperand;

            case '×':
                return firstOperand * secondOperand;

            case '÷':
                if (secondOperand == 0) {
                    throw new ArithmeticException("Can't divide by zero.");
                } else {
                    return firstOperand / secondOperand;
                }

            default:
                throw new ArithmeticException("Invalid operation.");
        }
    }

    /**
     * Clears the result text field.
     */
    public void clearResultField() {
        //Add in rotating clear and all clear later?
        setCurrentResultField("0");
        setOperationField("");
    }

    /**
     * Adds a decimal point dot (.) to the text field. Does not add one if a dot already has been added.
     */
    public void addDecimalPoint() {
        //If we are within the allowed digit length and it doesn't already have a dot
        if (getCurrentResultField().length() < maxDigits &&! getCurrentResultField().contains(".")) {
            setCurrentResultField(getCurrentResultField() + '.');
        }
    }

    /**
     * Changes the result to a percentage of 100.
     */
    public void changeToPercent() {
        //If it equals 0, it's still 0
        if (getCurrentResultField().equals("0")) {
            setCurrentResultField("0");

        } else {
            Double newResult = Double.parseDouble(getCurrentResultField()) / 100;
            setCurrentResultField(String.valueOf(newResult));
        }

        //Mimic the behaviour of the iPhone calculator
        this.isFirst = true;
    }

    /**
     * Changes the sign of the number in the result field.
     */
    public void changeSign() {
        //If the number is positive add a - sign
        if (!getCurrentResultField().contains("-")) {
            setCurrentResultField("-" + getCurrentResultField());

        //If the number is negative remove the - sign
        } else {
            setCurrentResultField(getCurrentResultField().substring(1));
        }
    }

    /**
     * Gets the current value of the result string.
     * @return the String representing the current number in the result text field
     */
    public String getCurrentResultField() {
        return currentResultField;
    }

    /**
     * Sets the value of currentResultField.
     * @param currentResultField the new text to set the value to
     */
    public void setCurrentResultField(String currentResultField) {
        this.currentResultField = currentResultField;
    }

    /**
     * Gets the current value of the previous (temp) result field.
     * @return the String representing the current number in the previous result variable
     */
    public String getPrevResultField() {
        return prevResultField;
    }

    /**
     * Sets the value of prevResultField.
     * @param prevResultField the new text to set the value to
     */
    public void setPrevResultField(String prevResultField) {
        this.prevResultField = prevResultField;
    }

    /**
     * Gets the current value of the operation string.
     * @return the String representing the current operation
     */
    public String getOperationField() {
        return operationField;
    }

    /**
     * Sets the value of operationField.
     * @param operationField the new text to set the value to
     */
    public void setOperationField(String operationField) {
        this.operationField = operationField;
    }
}
