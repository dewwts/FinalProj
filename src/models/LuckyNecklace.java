package models;

import javafx.scene.image.Image;

public class LuckyNecklace extends BaseItem {

    public LuckyNecklace(Image image, double x, double y) {
        super("Lucky Necklace", "Reduce damage taken", image, x, y);
    }

    @Override
    public void useItem(Player player) {
        System.out.println("Lucky Necklace used: Reduce damage taken!");
        // Implement logic to reduce damage taken
    }
}
