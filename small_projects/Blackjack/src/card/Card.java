package card;

/**
 * This class is for general purposes.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class Card {
    private String cardText;

    /**
     * Constructor to construct a card object; takes card text as the parameter
     * @param cardText
     */
    public Card(String cardText) {
        this.cardText = cardText;
    }

    /**
     * Returns card text; getter
     * @return cardText
     */
    public String getCardText() {
        return cardText;
    }

    /**
     * Returns card's text representation
     * @return cardText
     */
    @Override
    public String toString() {
        return cardText;
    }
}
