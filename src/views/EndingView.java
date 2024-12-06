package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EndingView {
    private Scene scene;
    private Label endingLabel;

    public EndingView() {
        endingLabel = new Label("Congratulations! You have completed the game.");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(endingLabel);

        scene = new Scene(layout, 800, 600);
    }

    public Scene getScene() { return scene; }
    public Label getEndingLabel() { return endingLabel; }
}

