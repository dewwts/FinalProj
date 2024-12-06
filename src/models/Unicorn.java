package models;

import javafx.scene.image.Image;

public class Unicorn extends BasePet {

    public Unicorn(Image image, double x, double y) {
        super("Unicorn", "Heal player", image, x, y);
    }

    @Override
    public void useAbility(Player player) {
        System.out.println("Unicorn's ability activated: Heal player!");
        player.setHealth(player.getHealth() + 1);
    }
}
