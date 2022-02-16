package game;

import card.Card;
import card.StandardCard;
import deck.*;


/**
 * This class tests the StandardCard (inherits from Card) and the StandardDeck (inherits from Deck) by
 * creating a standard deck of 52 cards, shuffling the deck, dealing DEALT number of cards, and reshuffling the
 * deck so all the dealt cards are returned to the dealer pile.
*/
public class DeckClient {

    public static final int DEALT=4;

    public static void main(String[] args) {
        // construct a 52 card deck

        System.out.println("Creating Deck...\n");
        StandardDeck myDeck = new StandardDeck();
        displayDeck(myDeck);

        System.out.println("Shuffled Deck...\n");
        shuffleDeck(myDeck);

        System.out.println("Dealing a Few Cards...\n");
        dealCards(DEALT,myDeck);


        System.out.println("Shuffled deck again...\n");
        shuffleDeck(myDeck);
    }

    private static void shuffleDeck(Deck myDeck){
        myDeck.shuffle();
        displayDeck(myDeck);
    }

    private static void displayDeck(Deck myDeck) {
        System.out.println(myDeck);
        System.out.println();
    }

    private static void dealCards(int numCards, StandardDeck myDeck) {

        for (int i = 0; i < numCards ; i++) {
            System.out.println("Dealt a "+ myDeck.dealTopCard().getCardText());
        }
        System.out.println();
        displayDeck(myDeck);
    }

}
