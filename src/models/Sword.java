package models;

import javafx.scene.image.Image;

public class Sword extends BaseWeapon {

    public Sword(Image image, double x, double y) {
        super("Sword", 10, image, x, y);
    }

    @Override
    public void useWeapon() {
        System.out.println("Swing the sword!");
        // Implement logic for sword attack
    }
}
