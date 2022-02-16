import javax.crypto.spec.PSource;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Ruslan Bessarab
 * Date: 01/17/2020
 * Version 1.0
 * This class creates a story based on user's choice. First, user can choose which story he wants to go through
 * and then read the story into array. Next, it creates a story and lastly write all of the choices into a new text file.
 */
public class ChooseYourOwnAdventure {
    /**
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        welcome();

        //creating new file object to show the incomplete directory content
        File directory = new File("./stories/incomplete");
        File[] contents = directory.listFiles();
        directoryContent(contents);

        //asking user to enter a story
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Name: ");
        String userChoiceStory = keyboard.nextLine();

        //creating 'path' string for the storyExist() method
        String path = "./stories/incomplete/" + userChoiceStory + ".txt";

        tellingStory(path, userChoiceStory);
    }

    /**
     * welcoming the user
     */
    public static void welcome() {
        System.out.println("Welcome to my Choose Your Own Adventure program!");
        System.out.println("Please choose a story:");
    }

    /**
     * directoryContent() method that loops through files in incomplete folder
     * @param contents
     */
    public static void directoryContent(File[] contents) {
        for (int i = 0; i < contents.length; i++) {
            File content = contents[i];
            System.out.println(content.getName());
        }
    }


    /**
     * storyExists() method that checks if the story exist
     * @param path
     * @return true or false based on existing of a file
     */
    public static boolean storyExists(String path) {
        File file = new File(path);
        if (file.exists())
            return true;
        else
            return false;
    }

    /**
     * tellingStory() method that telling the story if it exists
     * @param path
     * @param userChoiceStory
     */
    public static void tellingStory(String path, String userChoiceStory) {
        //telling the story or exit the program if story doesn't exist
        if(storyExists(path)) {
            Scanner userChoice = null;
            Scanner reader = null;
            PrintWriter writer = null;

            try {
                reader = new Scanner(new FileInputStream("./stories/incomplete/" + userChoiceStory + ".txt"));
                writer = new PrintWriter(new FileOutputStream("./stories/complete/" + userChoiceStory + ".txt"));

                //while loop that will count lines in file
                int counter = 0;
                while(reader.hasNextLine()) {
                    String line = reader.nextLine();
                    counter++;
                }

                //for loop that will put each line of a file into array
                String[] steps = new String[counter];
                Scanner storingStory = new Scanner(new File("./stories/incomplete/" + userChoiceStory + ".txt"));
                for(int i = 0; i < counter; i++) {
                    steps[i] = storingStory.nextLine();
                }

                //telling the story
                boolean userAlive = true;
                userChoice = new Scanner(System.in);
                int choice = 0;
                String[] parts = steps[choice].split(" : ");
                System.out.println(parts[0]);
                writer.println(parts[0]);
                String[] choices = parts[1].split(" \\| ");

                for(int i = 0; i < choices.length; i++) {
                    String[] value = choices[i].split(" = ");
                    System.out.println(value[1] + ": " + value[0]);
                    writer.println(value[1] + ": " + value[0]);
                }

                while(userAlive) {
                    choice = userChoice.nextInt();
                    writer.println("Your choice: " + choice);
                    writer.println();
                    parts = steps[choice - 1].split(" : ");
                    System.out.println(parts[0]);
                    writer.println(parts[0]);
                    choices = parts[1].split(" \\| ");


                    for(int i = 0; i < choices.length; i++) {
                        String[] value = choices[i].split(" = ");
                        System.out.println(value[1] + ": " + value[0]);
                        writer.println(value[1] + ": " + value[0]);
                        if(value[1].equals("-1") || value[1].equals("-2")) {
                            userAlive = false;
                        }
                    }

                }
            }
            catch(FileNotFoundException ex) {
                System.out.println("Error reading from file");
            }
            finally {
                if(reader != null)
                    reader.close();
                if(writer != null)
                    writer.close();
            }
        }
        else
            System.out.println("Sorry, story file doesn't exist.");
    }
}