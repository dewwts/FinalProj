package main;

import controllers.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.MainMenuView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainMenuView mainMenuView = new MainMenuView();
        new MainMenuController(mainMenuView, primaryStage);

        primaryStage.setTitle("HealJai");
        primaryStage.setScene(mainMenuView.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
