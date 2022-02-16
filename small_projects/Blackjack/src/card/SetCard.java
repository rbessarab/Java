package card;

/**
 * This class is for creating set card objects which extend general Card class.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class SetCard extends Card{
    private String shape;
    private int number;
    private String shading;
    private String color;

    /**
     * Constructs setCard object
     * @param cardText
     * @param shape
     * @param number
     * @param shading
     * @param color
     */
    public SetCard(String cardText, String shape, int number, String shading, String color) {
        super(shape + number + shading + color);
        this.shape = shape;
        this.number = number;
        this.shading = shading;
        this.color = color;
    }

    /**
     * getters which will return shape, number, shading and color
     * @return shape, number, shading, color
     */
    public String getShape() {
        return shape;
    }

    public int getNumber() {
        return number;
    }

    public String getShading() {
        return shading;
    }

    public String getColor() {
        return color;
    }

    /**
     * Returns text representation of the setCard object
     * @return String
     */
    public String toString() {
        return shape + number + shading + color;
    }
}
