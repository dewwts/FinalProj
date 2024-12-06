package models;

import javafx.scene.image.Image;

public class MisfortuneGod {
    private String dialogue;
    private Image image;

    public MisfortuneGod(String dialogue, Image image) {
        this.dialogue = dialogue;
        this.image = image;
    }

    public String getDialogue() { return dialogue; }
    public Image getImage() { return image; }

    public void speak() {
        System.out.println("Misfortune God says: " + dialogue);
        // Implement logic to display dialogue on screen
    }
}
