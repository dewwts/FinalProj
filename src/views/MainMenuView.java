package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuView {
    private Scene scene;
    private Button startButton;
    private Button exitButton;
    private Button Select;

    public MainMenuView() {
        startButton = new Button("Start Game");
        exitButton = new Button("Exit");
        Select = new Button("Customize");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(startButton, Select, exitButton);

        scene = new Scene(layout, 800, 600);
    }

    public Scene getScene() { return scene; }
    public Button getStartButton() { return startButton; }
    public Button getExitButton() { return exitButton; }
    public Button getSelectButton() {return Select;}
}

