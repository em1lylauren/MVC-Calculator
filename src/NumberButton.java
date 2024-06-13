/** A class representing a number button. */
public class NumberButton {
    /**
     * The value of the button.
     */
    private final int value;

    /**
     * Creates a new instance of a number button.
     * @param value The int value for the button.
     */
    public NumberButton(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the button.
     * @return The integer value of the button.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the value of the button as a float for float calculations.
     * @return The float value of the button.
     */
    public float getValueAsFloat() {
        return (float) this.value;
    }

    //Testing
    public static void main(String[] args) {
        NumberButton numberButton = new NumberButton(1);

        if (numberButton.getValue() != 1) {
            System.out.println("ERROR in getValue(): Returned wrong value for button.");
        }

        if (numberButton.getValueAsFloat() != 1.0) {
            System.out.println("ERROR in getValueAsFloat(): Returned wrong float value for button.");
        }
    }
}
