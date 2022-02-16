import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayOutputStream; // needed to redirect Standard Out
import java.io.PrintStream; // needed to redirect Standard Out

public class MagicSquareTest {

    private static final byte[] selections = {2, 7, 6, 9, 5, 1, 4, 3, 8};
    private static final short[] cummulativeChoices = {0b0_0000_0010,
            0b0_0100_0010,
            0b0_0110_0010,
            0b1_0110_0010,
            0b1_0111_0010,
            0b1_0111_0011,
            0b1_0111_1011,
            0b1_0111_1111,
            0b1_1111_1111};


    @Test
    public void testConstructor() {
        MagicSquare player1 = new MagicSquare();
        int choices = player1.getChoices();
        assertEquals("constructor doesn't work properly", "0", Integer.toBinaryString(choices));
    }

    @Test
    public void testPrintChoicesEmptySquare() {
        MagicSquare ms = new MagicSquare();


        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        ms.printChoices();

        String expectedConsoleOutput = "_ _ _ "+ System.lineSeparator()+"_ _ _ "+System.lineSeparator()+"_ _ _ "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());

    }

    @Test
    public void testChoose() {
        MagicSquare sq = new MagicSquare();

        // for every selection choice in values array
        for (int i = 0; i < selections.length; i++) {


            // choose num
            sq.choose((byte) selections[i]);

            // verify that getChoices returns proper cummulative selections
            assertEquals("choice was noted incorrectly", cummulativeChoices[i], sq.getChoices());

        }
    }

    @Test
    public void testHasAlreadyChosen() {
        MagicSquare player1 = new MagicSquare();
        player1.choose(4);
        player1.choose(5);
        assertEquals("doesn't work", "true", Boolean.toString(player1.hasAlreadyChosen(4)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidChoice() {
        MagicSquare ms = new MagicSquare();
        ms.choose((byte) -7);
        ms.choose((byte) 10);
    }

    @Test
    public void testPrintChoicesFullSquare() {
        MagicSquare ms = new MagicSquare();

        // choose every selection in the values array
        for (byte selection : selections) {
            ms.choose((byte) selection);
        }

        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        ms.printChoices();

        String expectedConsoleOutput = "2 7 6 "+System.lineSeparator()+"9 5 1 "+System.lineSeparator()+"4 3 8 "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());

    }

}