package deck;
import card.Card;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is for general purposes.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class Deck {
    private ArrayList<Card> dealerPile = new ArrayList<>();
    private ArrayList<Card> discardPile = new ArrayList<>();

    /**
     * Default constructor
     */
    public Deck() {

    }

    /**
     * Adds Card object to the dealerPile Array list.
     * @param card
     */
    public void addCard(Card card) {
        dealerPile.add(card);
    }

    /**
     * Moves all cards from the discard pile to the dealer pile.
     * Then randomizes the position of all cards in the dealer pile.
     */
    public void shuffle() {
        dealerPile.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(dealerPile);
    }

    /**
     * Removes the "top" card from the dealer pile and places it in the discard pile.
     * Returns the removed card.
     * @return Card object.
     */
    public Card dealTopCard() {
        Card card = dealerPile.get(dealerPile.size() - 1);
        dealerPile.remove(dealerPile.size() - 1);
        discardPile.add(card);
        return card;
    }

    /**
     * Counts and returns the number of cards in the dealer pile.
     * @return counter.
     */
    public int cardCount() {
        int counter = 0;
        for (int i = 0; i < dealerPile.size(); i++) {
            counter++;
        }
        return counter;
    }

    /**
     * Text representation of the Deck object.
     * @return String
     */
    @Override
    public String toString() {
        String dealerPileCard = "";
        for(Card card : dealerPile) {
            dealerPileCard += card + " \n";
        }

        String discardPileCard = "";
        for(Card card : discardPile) {
            discardPileCard += card + "\n";
        }

        return dealerPile.size() + " cards in deck \n"
                + "*****************\n" + dealerPileCard + "\n" + discardPile.size() +
                " cards in discard\n" + "*******************\n" + discardPileCard;
    }
}
