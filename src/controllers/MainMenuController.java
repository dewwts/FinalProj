package controllers;

import javafx.stage.Stage;
import views.GameView;
import views.MainMenuView;

public class MainMenuController {
    private MainMenuView view;
    private Stage stage;

    public MainMenuController(MainMenuView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        initEventHandlers();
    }

    public void initEventHandlers() {
        view.getStartButton().setOnAction(e -> {
            System.out.println("Starting the game...");
            GameController gameController = new GameController(stage);
            gameController.startGame();
        });

        view.getSelectButton().setOnAction(e -> {
            System.out.println("Opening customization menu...");
            CustomizeController customizeController = new CustomizeController(stage);
            customizeController.showCustomization();
        });

        view.getExitButton().setOnAction(e -> {
            System.out.println("Exiting the game...");
            stage.close();
        });
    }
}
