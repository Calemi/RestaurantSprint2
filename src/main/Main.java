package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static Main instance;
    private Stage stage;

    public Main() {
        instance = this;
    }

    /**
     * Used to get the single instance of Main.
     */
    public static Main getInstance() {
        return instance;
    }

    /**
     * Used to get the single instance of the stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Called by the launch method.
     * Used to setup the window information.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("Restaurant Application");
        primaryStage.setResizable(false);

        Scenes.loadScene(Scenes.getLoginScene());
    }

    /**
     * Calls the launch method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}