package card;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * THe purpose of this class is to test Card object
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class CardTest {

    @Test
    public void testCard() {
        Card card = new Card("8 of hearts");
        assertEquals("Card text is not correct", "8 of hearts", card.getCardText());
    }

    @Test
    public void testToString() {
        Card card = new Card("6 of clubs");
        assertEquals("After toString should be 6 of clubs", "6 of clubs", card.toString());
    }
}