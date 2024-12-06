package controllers;

import javafx.stage.Stage;
import models.DandelionSeed;
import utils.Randomizer;
import views.PlantingView;

public class PlantingController {
    private Stage stage;
    private PlantingView plantingView;
    private DandelionSeed dandelionSeed;
    private String currentWord;

    public PlantingController(Stage stage) {
        this.stage = stage;
        this.dandelionSeed = new DandelionSeed(loadDandelionImages());
    }

    public void startPlanting() {
        plantingView = new PlantingView();
        stage.setScene(plantingView.getScene());
        generateNewWord();
        initEventHandlers();
    }

    private void generateNewWord() {
        currentWord = Randomizer.getRandomWord();
        plantingView.getWordLabel().setText("Type the word: " + currentWord);
    }

    private void initEventHandlers() {
        plantingView.getInputField().setOnAction(e -> {
            String input = plantingView.getInputField().getText();
            if (input.equals(currentWord)) {
                dandelionSeed.grow();
                if (dandelionSeed.isFullyGrown()) {
                    System.out.println("Dandelion is fully grown!");
                    // Proceed to ending scene
                } else {
                    generateNewWord();
                }
            } else {
                System.out.println("Incorrect! Try again.");
            }
            plantingView.getInputField().clear();
        });
    }

    private javafx.scene.image.Image[] loadDandelionImages() {
        // Implement logic to load images for each growth stage
        return new javafx.scene.image.Image[10];
    }
}
