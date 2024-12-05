package Pets;

import javafx.scene.image.Image;

public abstract class BasePets {
    private String name;
    private int health;
    private int energy;
    private Image image;
    private double posX; // ตำแหน่ง X
    private double posY; // ตำแหน่ง Y

    // Constructor
    public BasePets(String name, int health, int energy, Image image) {
        this.name = name;
        this.health = health;
        this.energy = energy;
        this.image = image;
        this.posX = 0;
        this.posY = 0;
    }

    // Getter และ Setter สำหรับตำแหน่ง
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    // Getter และ Setter อื่น ๆ
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = Math.max(0, energy);
    }

    public Image getImage() {
        return image;
    }

    // เมธอด Abstract
    public abstract void specialAbility();

    public void showStatus() {
        System.out.println(name + " - Health: " + health + ", Energy: " + energy + ", Position: (" + posX + ", " + posY + ")");
    }
}


