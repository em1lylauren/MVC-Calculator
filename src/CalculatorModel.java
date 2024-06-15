/**
 * The model of the calculator.
 */
public class CalculatorModel {
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
        //Case: digit replaces existing text OR number in result field is already 0
        if (isFirst || getCurrentResultField().equals("0")) {
            setCurrentResultField(String.valueOf(n));
            this.isFirst = false;

        } else { //Case: digit is concatenated after existing text

            setCurrentResultField(prevResultField + n);
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
