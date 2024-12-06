package models;

import javafx.scene.image.Image;

public class MagicWand extends BaseWeapon {

    public MagicWand(Image image, double x, double y) {
        super("Magic Wand", 8, image, x, y);
    }

    @Override
    public void useWeapon() {
        System.out.println("Cast a magic spell!");
        // Implement logic for magic attack
    }
}
