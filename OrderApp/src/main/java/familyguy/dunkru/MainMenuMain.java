/**
 * runs the program.
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuMain extends Application {

    /**
     * Starts the main stage of the RU Cafe application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuMain.class.getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 718, 467);
        stage.setTitle("RU Cafe");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * The main method for launching the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}