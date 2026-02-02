package shallowseek.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import shallowseek.CommandResult;
import shallowseek.ShallowSeek;

public class MainWindow extends BorderPane {
    @FXML
    private ListView<DialogBox> messageList;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ShallowSeek shallowSeek;

    private Image userImage = new Image(
        this.getClass().getResourceAsStream("/images/User.png"));
    private Image shallowSeekImage = new Image(
        this.getClass().getResourceAsStream("/images/ShallowSeek.png"));

    /**
     * Inject the ShallowSeek instance.
     * @param a ShallowSeek instance.
     */
    public void setShallowSeek(ShallowSeek shallowSeek) {
        this.shallowSeek = shallowSeek;
        String welcomeMsg = "Hello! I'm ShallowSeek.\n"
            + "I look for answers, but not too deep.\n"
            + "What can I do for you?";
        this.messageList.getItems().add(
            DialogBox.getShallowSeekDialog(welcomeMsg, this.shallowSeekImage));
    }

    /**
     * Act depend on the command's result from the user.
     * Exit the application if the user triggered a ExitCommand.
     * Otherwise, add the result of the command into the message list area.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        CommandResult result = this.shallowSeek.getResponse(input);
        
        if (result.shouldExit()) {
            Platform.exit();
        }

        this.messageList.getItems().add(
            DialogBox.getUserDialog(input, this.userImage));
        this.messageList.getItems().add(
            DialogBox.getShallowSeekDialog(result.getMessage(), this.shallowSeekImage));
        this.messageList.scrollTo(this.messageList.getItems().size() - 1);
        this.userInput.clear();
    }
}
