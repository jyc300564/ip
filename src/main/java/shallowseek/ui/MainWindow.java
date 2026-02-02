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

    public void setShallowSeek(ShallowSeek shallowSeek) {
        this.shallowSeek = shallowSeek;
    }

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
