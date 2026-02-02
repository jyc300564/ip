package shallowseek;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import shallowseek.ui.MainWindow;

public class Main extends Application {
    private ShallowSeek shallowSeek = new ShallowSeek();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Mainwindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            stage.setScene(scene);

            stage.setTitle("ShallowSeek");
            stage.getIcons().add(
                new Image(this.getClass().getResourceAsStream("/images/ShallowSeek.png")) 
            );
            stage.setMinWidth(600);
            stage.setMinHeight(400);

            fxmlLoader.<MainWindow>getController().setShallowSeek(this.shallowSeek);
            stage.show();
            System.out.println(stage.getWidth());
            System.out.println(stage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.shallowSeek.storeTaskList();
    }
}
