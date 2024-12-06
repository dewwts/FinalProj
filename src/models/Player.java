package models;

import javafx.scene.image.Image;
import utils.ImageLoader;

import java.util.ArrayList;

public class Player {
    private int health;
    private BaseWeapon weapon;
    private BasePet pet;
    private ArrayList<BaseItem> inventory;
    private Image image;
    private double x;
    private double y;

    public Player(Image image, double x, double y) {
        this.health = 3;
        this.weapon = null;
        this.pet = null;
        this.inventory = new ArrayList<>(3);
        this.image = image;
        BaseItem lucky_hat = new LuckyHat(ImageLoader.load("lucky_hat.png"), 0, 0);
        BaseItem lucky_coin = new LuckyCoin(ImageLoader.load("lucky_coin.png"), 0, 0);
        BaseItem lucky_necklace = new LuckyNecklace(ImageLoader.load("lucky_necklace.png"), 0, 0);
        this.inventory.add(lucky_hat);
        this.inventory.add(lucky_coin);
        this.inventory.add(lucky_necklace);
        this.x = x;
        this.y = y;
    }

    // Getter and Setter methods
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public BaseWeapon getWeapon() { return weapon; }
    public void setWeapon(BaseWeapon weapon) { this.weapon = weapon; }

    public BasePet getPet() { return pet; }
    public void setPet(BasePet pet) { this.pet = pet; }

    public ArrayList<BaseItem> getInventory() { return inventory; }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    // Other methods
    public void addItem(BaseItem item) {
        if (inventory.size() < 3) {
            inventory.add(item);
        } else {
            System.out.println("Inventory is full!");
        }
    }

    public void removeItem(BaseItem item) {
        inventory.remove(item);
    }

    public void usePetAbility() {
        if (pet != null) {
            pet.useAbility(this);
        }
    }
    
    public boolean isCollidingWith(Ghost ghost) {
        double playerLeft = this.getX();
        double playerRight = this.getX() + TILE_SIZE;
        double playerTop = this.getY();
        double playerBottom = this.getY() + TILE_SIZE;

        double ghostLeft = ghost.getX();
        double ghostRight = ghost.getX() + TILE_SIZE;
        double ghostTop = ghost.getY();
        double ghostBottom = ghost.getY() + TILE_SIZE;

        // ตรวจสอบว่ามีการชนกันหรือไม่
        boolean collisionX = playerRight > ghostLeft && playerLeft < ghostRight;
        boolean collisionY = playerBottom > ghostTop && playerTop < ghostBottom;

        return collisionX && collisionY;
    }

}
