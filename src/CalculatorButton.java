import javax.swing.*;
import java.awt.*;

/**
 * A custom JButton class to change the colour of the button when clicked.
 */
public class CalculatorButton extends JButton {
    private Color backgroundColorClicked;

    public CalculatorButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(backgroundColorClicked);
        } else {
            g.setColor(getBackground());
        }

        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    /**
     * Gets the current background colour when clicked.
     * @return the current Color of the button's background on click
     */
    public Color getBackgroundColorClicked() {
        return backgroundColorClicked;
    }

    /**
     * Sets the background colour when clicked.
     * @param backgroundColorClicked the new Color to be set
     */
    public void setBackgroundColorClicked(Color backgroundColorClicked) {
        this.backgroundColorClicked = backgroundColorClicked;
    }
}
