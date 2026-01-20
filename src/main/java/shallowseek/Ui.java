package shallowseek;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void printSeparateLine() {
        System.out.println("----------------------------------------");
    }

    public String readInput() {
        System.out.print(">> ");
        return this.sc.nextLine().trim().toLowerCase();
    }

    public void showGreeting() {
        printSeparateLine();
        System.out.println("Hello! I'm ShallowSeek.");
        System.out.println("I look for answers, but not too deep.");
        System.out.println("What can I do for you?");
        printSeparateLine();
    }

    public void showResult(CommandResult result) {
        System.out.println();
        System.out.println(result.getMessage());
        printSeparateLine();
    }

}
