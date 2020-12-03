package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static Main instance;

    private Stage stage;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("Restaurant Application");
        primaryStage.setResizable(false);

        Scenes.loadScene(Scenes.getLoginScene());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }
}