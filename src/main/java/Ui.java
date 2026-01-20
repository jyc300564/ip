class Ui {

    private void printSeparateLine() {
        System.out.println("----------------------------------------");
    }

    public void showGreeting() {
        printSeparateLine();
        System.out.println("Hello! I'm ShallowSeek.");
        System.out.println("I look for answers, but not too deep.");
        System.out.println("What can I do for you?");
        printSeparateLine();
    }

    public void showExit(CommandResult result) {
        System.out.println(result.getMessage());
        printSeparateLine();
    }

}
