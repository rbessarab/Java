package deck;

import card.StandardCard;

/**
 * This class is for creating StandardDeck objects which extends Deck class.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class StandardDeck extends Deck{

    /**
     * Default constructor;
     * Constructs StandardDeck object with 52 card objects in it.
     */
    public StandardDeck() {
        String[] suits = new String[] {"hearts", "spades", "clubs", "diamonds"};
        String[] ranks = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                StandardCard card = new StandardCard(ranks[j], suits[i]);
                addCard(card);
            }
        }
    }

    /**
     * Hides the Deck.dealTopCard() method. It retrieves the card from the parent class by
     * calling dealTopCard() on the parent class and then casts the Card object to a
     * StandardCard object for ease of use.
     * @return StandardCard
     */
    public StandardCard dealTopCard() {
        return (StandardCard) super.dealTopCard();
    }
}
