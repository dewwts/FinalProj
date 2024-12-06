package views;

import java.awt.Button;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import models.Player;
import models.BaseItem;
import models.BasePet;
import utils.ImageLoader;

public class GameView {
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private List<BasePet> pets;
    
    private boolean[][] hasTree;
    private boolean[][] hasRock;
    
    private Image grassTile;
    private Image treeSprite;
    private Image rockSprite;
    
    // ขนาดของ Tile และแผนที่
    private static final int TILE_SIZE = 64;
    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 8;

    // แผนที่และภาพ Tile 

    public GameView(Player player, List<BasePet> pets, boolean[][] hasTree, boolean[][] hasRock, int mapWidth, int mapHeight, int tileSize) {
        this.player = player;
        setPets(pets);
        this.hasTree = hasTree;
        this.hasRock = hasRock;

        grassTile = new Image("tile_grass.png");
        treeSprite = new Image("tree.png");
        rockSprite = new Image("rock.png");

        canvas = new Canvas(MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE);
        gc = canvas.getGraphicsContext2D();
        
        //battleButton.setX(10); // ระยะจากขอบซ้าย
        //battleButton.setLayoutY(10); // ระยะจากขอบบน
        Pane root = new Pane();
        root.getChildren().add(canvas);
        //root.getChildren().add(battleButton);

        scene = new Scene(root, MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE);
    }

    public Scene getScene() { return scene; }
    public GraphicsContext getGraphicsContext() { return gc; }

    private void drawMap() {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                // วาดพื้นเป็นหญ้า
                gc.drawImage(grassTile, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // วาดต้นไม้ถ้ามี
                if (hasTree[y][x]) {
                    gc.drawImage(treeSprite, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                // วาดหินถ้ามี
                if (hasRock[y][x]) {
                    gc.drawImage(rockSprite, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void drawPlayer() {
        gc.drawImage(player.getImage(), player.getX(), player.getY(), TILE_SIZE, TILE_SIZE);
    }
    
    public void setPets(List<BasePet> pets) {
    	this.pets = pets;
    }
    
    public void render() {
        gc.clearRect(0, 0, MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE);
        drawMap();
        if (pets != null) {
            drawPets(); // วาดสัตว์เลี้ยง
        }
        drawPlayer();
        drawInventory(gc);
        // วาดองค์ประกอบอื่น ๆ
    }

    private void drawPets() {
        for (BasePet pet : pets) {
            gc.drawImage(pet.getImage(), pet.getX(), pet.getY(), TILE_SIZE, TILE_SIZE);
        }
    }
    
    private void drawInventory(GraphicsContext gc) {
        // ตำแหน่งสำหรับวาดอินเวนทอรีที่มุมซ้ายล่าง
        // สมมติว่า MAP_HEIGHT และ TILE_SIZE ถูกกำหนดไว้ในคลาสแล้ว
        double baseX = 10; // ระยะห่างจากขอบซ้าย
        double baseY = MAP_HEIGHT * TILE_SIZE - 90; // ระยะห่างจากขอบล่าง (ปรับได้ตามต้องการ)
        double itemSize = 60; // ขนาดของไอเท็มที่จะแสดง (ย่อส่วน)
        double spacing = 100;  // ระยะห่างระหว่างแต่ละไอเท็ม

        // วาดพื้นหลังอินเวนทอรี (เป็นสี่เหลี่ยมกึ่งโปร่งใสเพื่อให้ดูรู้ว่าเป็นพื้นที่ Inventory)
        gc.setFill(javafx.scene.paint.Color.color(1, 1, 1, 0.7)); // สีขาวโปร่งใส
        gc.fillRect(baseX - 10, baseY - 20, 3 * spacing + 20, itemSize + 40);

        // ตั้งค่าฟอนต์สำหรับแสดงชื่อไอเท็ม
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.setFont(new javafx.scene.text.Font("Arial", 12));

        // ดึงรายการไอเท็มจาก Player
        List<BaseItem> items = player.getInventory();

        // วาดไอเท็มทีละชิ้น (สูงสุด 3 ชิ้น)
        for (int i = 0; i < items.size() && i < 3; i++) {
            BaseItem item = items.get(i);

            // วาดรูปไอเท็ม
            gc.drawImage(item.getImage(), baseX + i * spacing, baseY, itemSize, itemSize);

            // วาดชื่อไอเท็มด้านล่างรูป
            gc.fillText(item.getName(), baseX + i * spacing, baseY + itemSize + 15);
        }
    }
    
    private void drawButton() {
    	Button goBattle = new Button("Battle !");
    	
    }
    
}

