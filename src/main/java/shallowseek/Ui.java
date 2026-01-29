package shallowseek;
import java.util.Scanner;

/**
 * Handles all user interface interactions for the application.
 * This class manages input reading from the console and displaying 
 * formatted output to the user.
 */
public class Ui {
    /** The scanner used to read user input from the standard input stream. */
    private Scanner sc;

    /**
     * Initializes a new Ui instance and sets up the input scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal separator line to the console for visual clarity.
     */
    private void printSeparateLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Displays an input prompt and reads a line of text from the user.
     * The input is trimmed and converted to lowercase for consistent parsing.
     * @return The processed input string from the user.
     */
    public String readInput() {
        System.out.print(">> ");
        return this.sc.nextLine().trim().toLowerCase();
    }

    /**
     * Displays the initial greeting message and application introduction.
     */
    public void showGreeting() {
        printSeparateLine();
        System.out.println("Hello! I'm ShallowSeek.");
        System.out.println("I look for answers, but not too deep.");
        System.out.println("What can I do for you?");
        printSeparateLine();
    }

    /**
     * Displays the result of a command execution to the user.
     * @param result The CommandResult object containing the message to be shown.
     */
    public void showResult(CommandResult result) {
        System.out.println();
        System.out.println(result.getMessage());
        printSeparateLine();
    }

}
