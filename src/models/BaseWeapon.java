package models;

import javafx.scene.image.Image;

public abstract class BaseWeapon {
    protected String name;
    protected int attackPower;
    protected Image image;
    protected double x;
    protected double y;

    public BaseWeapon(String name, int attackPower, Image image, double x, double y) {
        this.name = name;
        this.attackPower = attackPower;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public String getName() { return name; }
    public int getAttackPower() { return attackPower; }
    public Image getImage() { return image; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public abstract void useWeapon();
}
