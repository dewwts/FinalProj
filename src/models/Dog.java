package models;

import javafx.scene.image.Image;

public class Dog extends BasePet {

    public Dog(Image image, double x, double y) {
        super("Dog", "Increase movement speed", image, x, y);
    }

    @Override
    public void useAbility(Player player) {
        System.out.println("Dog's ability activated: Increase movement speed!");
        // Implement logic to increase player's speed
    }
}
