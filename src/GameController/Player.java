package GameController;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    private double x; // ตำแหน่ง X ของตัวละคร
    private double y; // ตำแหน่ง Y ของตัวละคร
    private int hp; // จำนวนหัวใจ (HP)
    private Image sprite; // รูปภาพของตัวละคร
    private Image heartSprite; // รูปภาพของหัวใจ

    public Player(double startX, double startY, Image sprite, Image heartSprite) {
        this.x = startX;
        this.y = startY;
        this.hp = 3; // เริ่มต้นด้วยหัวใจ 3 ดวง
        this.sprite = sprite;
        this.heartSprite = heartSprite;
    }

    // Getter และ Setter สำหรับตำแหน่ง
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Getter และ Setter สำหรับ HP
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, 3)); // จำกัดค่า HP ระหว่าง 0-3
    }

    // วาดตัวละคร
    public void draw(GraphicsContext gc) {
        gc.drawImage(sprite, x, y, 64, 64); // วาดตัวละครขนาด 64x64
    }

    // วาดหัวใจ (HP)
    public void drawHearts(GraphicsContext gc) {
        for (int i = 0; i < hp; i++) {
            gc.drawImage(heartSprite, 10 + i * 40, 10, 32, 32); // วาดหัวใจที่มุมบนซ้าย
        }
    }
}

