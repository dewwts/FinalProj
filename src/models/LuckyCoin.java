package models;

import javafx.scene.image.Image;

public class LuckyCoin extends BaseItem {

    public LuckyCoin(Image image, double x, double y) {
        super("Lucky Coin", "Increase luck", image, x, y);
    }

    @Override
    public void useItem(Player player) {
        System.out.println("Lucky Coin used: Increase player's luck!");
        // Implement logic to increase player's luck
    }
}
