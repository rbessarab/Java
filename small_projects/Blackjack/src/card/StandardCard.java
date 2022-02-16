package card;

/**
 * This class is for creating standard card class which extends general Card class.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class StandardCard extends Card {
    private String rank;
    private String suit;

    /**
     * Constructs StandardCard object
     * @param rank
     * @param suit
     */
    public StandardCard(String rank, String suit) {
        super(rank + " of " + suit);
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Getters which return rank and suit.
     * @return rank, suit
     */
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    /**
     * Text representation of the StandardCard object
     * @return rank of suit
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
