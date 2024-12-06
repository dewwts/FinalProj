package models;

import javafx.scene.image.Image;

public abstract class BasePet {
    protected String name;
    protected String specialAbility;
    protected Image image;
    protected double x;
    protected double y;

    public BasePet(String name, String specialAbility, Image image, double x, double y) {
        this.name = name;
        this.specialAbility = specialAbility;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }
    public String getSpecialAbility() { return specialAbility; }
    public Image getImage() { return image; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public abstract void useAbility(Player player);
}
