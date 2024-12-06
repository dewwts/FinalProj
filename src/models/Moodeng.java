package models;

import javafx.scene.image.Image;

public class Moodeng extends BaseWeapon {

    public Moodeng(Image image, double x, double y) {
        super("Moodeng", 12, image, x, y);
    }

    @Override
    public void useWeapon() {
        System.out.println("Strike with Moodeng!");
        // Implement logic for Moodeng attack
    }
}
