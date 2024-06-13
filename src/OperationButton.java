/**
 * A class representing an operation that can be done on two numbers
 */
public class OperationButton {
    /**
     * A string representing the type of operation this button does.
     */
    private final String opType;

    /**
     * Creates a new instance of an operation button.
     * @param opType
     */
    public OperationButton(String opType) {
        this.opType = opType;
    }

    /**
     * Returns the type of the operation.
     * @return a String representing the type of the operation
     */
    public String getType() {
        return this.opType;
    }

    /**
     * Perform an operation on two numbers, returning the integer result. Does not divide, since that is always a float operation.
     * @param a the first operand
     * @param b the second operand
     * @return the integer result of the operation performed on the operands.
     */
    private int intOperation(int a, int b) throws RuntimeException {
        //Perform operation based on opType
        switch (opType) {
            case "ADD":
                return a + b;

            case "SUBTRACT":
                return a - b;

            case "MULTIPLY":
                return a * b;

            //Should never reach this line
            default:
                throw new RuntimeException("Invalid operation type for integers.");
        }
    }

    /**
     * Perform an operation on two numbers, returning the float result. All attempts at integer division will be sent here.
     * @param a the first operand
     * @param b the second operand
     * @return the float result of the operation performed on the operands.
     */
    private float floatOperation(float a, float b) throws RuntimeException {
        //Perform operation based on opType
        switch (opType) {
            case "ADD":
                return a + b;

            case "SUBTRACT":
                return a - b;

            case "MULTIPLY":
                return a * b;

            case "DIVIDE":
                if (b == 0) throw new ArithmeticException("Divide by zero.");
                else return a / b;

            //Should never reach this line
            default:
                throw new RuntimeException("Invalid operation type for floats.");
        }
    }

    //Testing
    public static void main(String[] args) {

    }
}
