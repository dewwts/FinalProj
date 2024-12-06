package models;

import javafx.scene.image.Image;

public class Ghost {
    private Image image;
    private double x, y;

    public Ghost(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

