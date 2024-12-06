package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlantingView {
    private Scene scene;
    private Label wordLabel;
    private TextField inputField;

    public PlantingView() {
        wordLabel = new Label("Type the word:");
        inputField = new TextField();

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(wordLabel, inputField);

        scene = new Scene(layout, 800, 600);
    }

    public Scene getScene() { return scene; }
    public Label getWordLabel() { return wordLabel; }
    public TextField getInputField() { return inputField; }
}

