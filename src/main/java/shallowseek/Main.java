package shallowseek;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import shallowseek.ui.MainWindow;

/**
 * Entry point of the ShallowSeek application.
 * <p>
 * This class is responsible for:
 * <ul>
 *   <li>Bootstrapping the JavaFX application</li>
 *   <li>Loading the main FXML layout</li>
 *   <li>Initializing and wiring core components such as logic and storage</li>
 *   <li>Handling application-level lifecycle events</li>
 * </ul>
 */
public class Main extends Application {
    private ShallowSeek shallowSeek = new ShallowSeek();

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            stage.setScene(scene);

            stage.setTitle("ShallowSeek");
            stage.getIcons().add(
                new Image(this.getClass().getResourceAsStream("/images/Icon.png")) 
            );
            stage.setMinWidth(615);
            stage.setMinHeight(440);

            fxmlLoader.<MainWindow>getController().setShallowSeek(this.shallowSeek);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the application is about to exit.
     * <p>
     * This method performs final cleanup tasks such as persisting data
     * before the JavaFX runtime shuts down.
     */
    @Override
    public void stop() {
        this.shallowSeek.storeTaskList();
    }
}
