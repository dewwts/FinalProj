package models;

import javafx.scene.image.Image;

public class Cat extends BasePet {

    public Cat(Image image, double x, double y) {
        super("Cat", "Increase dodge chance", image, x, y);
    }

    @Override
    public void useAbility(Player player) {
        System.out.println("Cat's ability activated: Increase dodge chance!");
        // Implement logic to increase player's dodge chance
    }
}
