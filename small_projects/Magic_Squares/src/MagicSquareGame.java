import java.util.Scanner;

/**
 * Driver class to drive a magic square game
 * @author Ruslan Bessarab
 * @version 1.0
 * @data 10.31.2020
 */
public class MagicSquareGame {
    /**
     * Main method for setting up and running the game
     * @param args not used
     */
    public static void main(String[] args) {
        printDirections();

        //player 1
        String player_1_name = getName(1);
        MagicSquare player_1 = new MagicSquare();

        //player 2
        String player_2_name = getName(2);
        MagicSquare player_2 = new MagicSquare();

        //game is running until one of the ifs will be true
        while(true) {
            //doing choice for player one
            int choice = choice(player_1, player_2, player_1_name);
            player_1.choose(choice);
            player_1.printChoices();
            //check if game is draw after each choice
            if(isDraw(player_1, player_2)) {
                System.out.println("The game is a draw!");
                break;
            }
            //checking if player wins
            if(isWin(player_1)) {
                System.out.println(player_1_name + " wins!");
                break;
            }

            //choice for player 2
            choice = choice(player_2, player_1, player_2_name);
            player_2.choose(choice);
            player_2.printChoices();
            //same checks for player 2
            if(isDraw(player_1, player_2)) {
                System.out.println("The game is a draw!");
                break;
            }
            if(isWin(player_2)) {
                System.out.println(player_2_name + " wins!");
                break;
            }
        }
    }

    /**
     * Checks is a player wins. Returns true if player has one of the winning conditions; false if not.
     * @param p Player
     * @return true or false depends on conditions.
     */
    public static boolean isWin(MagicSquare p) {
        //winning conditions
        int[] winConditions = {98, 273, 140, 266, 84, 161, 146, 56};
        //The idea is that it counts if player has any winning condition and if count more than 0 then it means
        //there is one of the winning condition
        int count = 0;
        for(int i = 0; i <= 7; i++) {
            short mask = (short) winConditions[i];
            short result = (short) (p.getChoices() & mask);
            if(result == mask)
                count++;
        }
        if (count > 0)
            return true;
        else
            return false;
    }

    /**
     * Checks if game is draw or not.
     * @param p1 player 1
     * @param p2 player 2
     * @return true or false depends on condition.
     */
    public static boolean isDraw(MagicSquare p1, MagicSquare p2) {
        //addition of both players and check if it's less or equal to draw condition
        int result = p1.getChoices() + p2.getChoices();
        int draw = 511;

        if(result < draw)
            return false;
        else
            return true;
    }

    /**
     * Prompts a player for a choice and does few conditions to check
     * if choice is not made before and in the range (1-9).
     * @param p player 1
     * @param p2 player2
     * @param name Name of a player
     * @return choice of a player
     */
    public static int choice(MagicSquare p, MagicSquare p2, String name) {
        Scanner kb = new Scanner(System.in);
        //asks for a number as a choice
        System.out.println(name + " please enter a number: ");
        int choice = kb.nextInt();
        //check if nobody used this choice before
        if(!p.hasAlreadyChosen(choice) || !p2.hasAlreadyChosen(choice))
            System.out.println("A player has already chosen " + choice + "\n");
        //checks if a choice is not out of range
        if(choice > 9 || choice < 1)
            System.out.println(choice + " is not between 1 and 9 " + "\n");

        //doing same things but in loop (in case of player didn't choose a correct number at first).
        while(!p.hasAlreadyChosen(choice) || !p2.hasAlreadyChosen(choice) || choice > 9 || choice < 1) {
            System.out.println(name + " please enter a number: ");
            choice = kb.nextInt();
            if(!p.hasAlreadyChosen(choice) || !p2.hasAlreadyChosen(choice))
                System.out.println("A player has already chosen " + choice + "\n");
            if(choice > 9 || choice < 1)
                System.out.println(choice + " is not between 1 and 9" + "\n");
        }

        return choice;
    }

    /**
     * Method for getting names for each player.
     * @param playerNum number of player - 1st or 2nd
     * @return name of a player as a string
     */
    public static String getName(int playerNum) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a name for player #" + playerNum);
        return kb.nextLine();
    }

    /**
     * Prints direction of the game
     */
    public static void printDirections() {
        System.out.println("Welcome to the game of Magic Squares");
        System.out.println("***********************************");
        System.out.println("Rules:");
        System.out.println("2 players play the game.\n" +
                "Each player takes turns picking a number from 1-9.\n" +
                "No number can be chosen twice\n" +
                "The first player to have 3 numbers that sum to 15 wins!");
        System.out.println("2 7 6 \n" +
                "9 5 1 \n" +
                "4 3 8\n" +
                "***********************************");
    }
}
