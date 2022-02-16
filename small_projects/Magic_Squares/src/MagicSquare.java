/**
 * This class used for creating magic squares objects
 * @author Ruslan Bessarab
 * @version 1.0
 * @date 10.30.2020
 */
public class MagicSquare {
    private short choices;

    /**
     * Default Constructor
     */
    public MagicSquare() {
    }

    /**
     * Accepts a byte value ranging between 1-9. This will set the bit at the index selection - 1 to "on."
     * This method then returns true if the bit was changed, false if the selection has already been chosen,
     * and throws an IllegalArgumentException if selection is less than 1 or greater than 9.
     *
     * @param selection player's choice
     * @return true or false depends on if it's possible value
     */
    public boolean choose(int selection) {
        if(selection < 1 || selection > 9) {
            throw new IllegalArgumentException(selection + " is out of range.");
        }

        else if(hasAlreadyChosen(selection)) {
            //creating mask
            short mask = createMask(selection - 1);

            //use OR operator to set a specific bit to "on"
            choices = (short) (choices | mask);
            return true;
        }

        else {
            return false;
        }
    }

    /**
     * Returns true if the bit at index selection - 1 is set to the "on" position,
     * or false if the bit is in the "off" position.
     * @param selection player's choice
     * @return
     */
    public boolean hasAlreadyChosen(int selection) {
        short mask = createMask(selection - 1);
        short result = (short) (choices | mask);

        if(result == choices)
            return false;
        else
            return true;
    }

    /**
     * Getter for the choices field. This number can then be examined
     * to determine if the player has won the match or not.
     * @return choices
     */
    public short getChoices() {
        return choices;
    }

    /**
     * Prints a string representation of the magic square. Each number is printed in the
     * position located in the first image at the top of this document. Any number not
     * chosen will have an underscore in that position.
     */
    public void printChoices() {
        int[][] list = {{2, 7, 6}, {9, 5, 1}, {4, 3, 8} };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(hasAlreadyChosen(list[i][j]))
                    System.out.print("_ ");
                else
                    System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }

    private short createMask(int index) {
        short mask = 1; //mask = 0000_0000_0000_0001
        mask = (short) (mask << index);
        return mask;
    }
}
