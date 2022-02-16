package card;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The purpose for this class is to test StandardCard class.
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class StandardCardTest {
    @Test
    public void testStandardCard() {
        StandardCard card = new StandardCard("Jack", "hearts");
        assertEquals("Card text is not correct", "Jack of hearts", card.getCardText());
    }

    @Test
    public void testToString() {
        StandardCard card = new StandardCard("5", "diamond");
        assertEquals("After toString should be 5 of diamond", "5 of diamond", card.toString());
    }
}