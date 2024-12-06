package models;

import javafx.scene.image.Image;

public abstract class BaseItem {
    protected String name;
    protected String effect;
    protected Image image;
    protected double x;
    protected double y;

    public BaseItem(String name, String effect, Image image, double x, double y) {
        this.name = name;
        this.effect = effect;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }
    public String getEffect() { return effect; }
    public Image getImage() { return image; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public abstract void useItem(Player player);
}
