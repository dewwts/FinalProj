package models;

import javafx.scene.image.Image;

public class DandelionSeed {
    private int growthStage;
    private Image[] images;

    public DandelionSeed(Image[] images) {
        this.growthStage = 0;
        this.images = images;
    }

    public int getGrowthStage() { return growthStage; }
    public void setGrowthStage(int growthStage) { this.growthStage = growthStage; }
    public Image getImage() { return images[growthStage]; }

    public void grow() {
        if (growthStage < images.length - 1) {
            growthStage++;
            System.out.println("Dandelion grows to stage " + growthStage);
        }
    }

    public boolean isFullyGrown() {
        return growthStage >= images.length - 1;
    }
}

