package models;

import javafx.scene.image.Image;

public class LuckyHat extends BaseItem {

    public LuckyHat(Image image, double x, double y) {
        super("Lucky Hat", "Increase critical hit chance", image, x, y);
    }

    @Override
    public void useItem(Player player) {
        System.out.println("Lucky Hat used: Increase critical hit chance!");
        // Implement logic to increase critical hit chance
    }
}
