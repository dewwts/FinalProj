package controllers;

import javafx.stage.Stage;
import views.EndingView;

public class EndingController {
    private Stage stage;
    private EndingView endingView;

    public EndingController(Stage stage) {
        this.stage = stage;
    }

    public void showEnding() {
        endingView = new EndingView();
        stage.setScene(endingView.getScene());
    }
}
