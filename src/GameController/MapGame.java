package GameController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

import Pets.BasePets;
import Pets.Cat;
import Pets.Dog;
import Pets.Unicorn;

public class MapGame extends Application {
    private Player player;
    private BasePets activePet;
    private double petX, petY;

    private static final int TILE_SIZE = 64; // ขนาด Tile
    private static final int MAP_WIDTH = 10; // จำนวน Tile ในแนวนอน
    private static final int MAP_HEIGHT = 8; // จำนวน Tile ในแนวตั้ง

    private Image mapTile;
    private Image treeSprite;

    private boolean[][] hasTree;
    private BasePets[] pets;
    private double[][] petsPositions;

    @Override
    public void start(Stage stage) {
        // โหลดรูปภาพ
        mapTile = new Image("tiles.png");
        Image playerSprite = new Image("sprites.png");
        treeSprite = new Image("bush.png");
        Image heartSprite = new Image("heart.png");

        // สร้าง Player
        player = new Player(3 * TILE_SIZE, 3 * TILE_SIZE, playerSprite, heartSprite);

        // สร้างสัตว์เลี้ยง
        pets = new BasePets[] {
            new Dog("Buddy"),
            new Cat("Whiskers"),
            new Unicorn("Twinkle")
        };

        // สุ่มตำแหน่งสัตว์เลี้ยง
        petsPositions = new double[pets.length][2];
        Random random = new Random();
        for (int i = 0; i < pets.length; i++) {
            int x, y;
            do {
                x = random.nextInt(MAP_WIDTH);
                y = random.nextInt(MAP_HEIGHT);
            } while (hasTree != null && hasTree[y][x]);
            petsPositions[i][0] = x * TILE_SIZE;
            petsPositions[i][1] = y * TILE_SIZE;
        }

        generateTrees();

        // สร้าง Canvas
        Canvas canvas = new Canvas(MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // วาดแผนที่และตัวละคร
        drawMap(gc);
        drawPets(gc);
        player.draw(gc);
        player.drawHearts(gc);

        // จัดการการเคลื่อนที่ของตัวละคร
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            handleMovement(event, gc, stage);
        });

        // จัดการ Layout
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Map Game");
        stage.show();
    }

    private void generateTrees() {
        hasTree = new boolean[MAP_HEIGHT][MAP_WIDTH];
        Random random = new Random();

        int numberOfTrees = (MAP_WIDTH * MAP_HEIGHT) / 5;
        for (int i = 0; i < numberOfTrees; i++) {
            int x, y;
            do {
                x = random.nextInt(MAP_WIDTH);
                y = random.nextInt(MAP_HEIGHT);
            } while (hasTree[y][x]);
            hasTree[y][x] = true;
        }
    }

    private void drawMap(GraphicsContext gc) {
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                gc.drawImage(mapTile, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (hasTree[y][x]) {
                    gc.drawImage(treeSprite, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void drawPets(GraphicsContext gc) {
        for (int i = 0; i < pets.length; i++) {
            gc.drawImage(pets[i].getImage(), petsPositions[i][0], petsPositions[i][1], TILE_SIZE, TILE_SIZE);
        }
    }

    private void handleMovement(KeyEvent event, GraphicsContext gc, Stage stage) {
        double newX = player.getX();
        double newY = player.getY();

        // คำนวณตำแหน่งใหม่ของผู้เล่น
        switch (event.getCode()) {
            case W: newY -= TILE_SIZE; break;
            case S: newY += TILE_SIZE; break;
            case A: newX -= TILE_SIZE; break;
            case D: newX += TILE_SIZE; break;
        }

        // ตรวจสอบการชนกับสัตว์เลี้ยง
        for (int i = 0; i < pets.length; i++) {
            if (Math.abs(newX - petsPositions[i][0]) < TILE_SIZE && Math.abs(newY - petsPositions[i][1]) < TILE_SIZE) {
                showPetDialog(stage, pets[i], gc);
                return;
            }
        }

        // แปลงตำแหน่งใหม่เป็น Tile และตรวจสอบขอบเขต
        int tileX = (int) (newX / TILE_SIZE);
        int tileY = (int) (newY / TILE_SIZE);

        if (tileX >= 0 && tileX < MAP_WIDTH && tileY >= 0 && tileY < MAP_HEIGHT && !hasTree[tileY][tileX]) {
            player.setX(newX);
            player.setY(newY);

            // การเคลื่อนที่ของสัตว์เลี้ยง
            if (activePet != null) {
                double dx = newX - petX;
                double dy = newY - petY;

                int newPetTileX = (int) (petX / TILE_SIZE);
                int newPetTileY = (int) (petY / TILE_SIZE);

                if (Math.abs(dx) > Math.abs(dy)) {
                    // เคลื่อนที่ในแนวนอน
                    newPetTileX += (dx > 0) ? 1 : -1;
                } else {
                    // เคลื่อนที่ในแนวตั้ง
                    newPetTileY += (dy > 0) ? 1 : -1;
                }

                // ตรวจสอบว่าตำแหน่งใหม่ของสัตว์เลี้ยงไม่ชนต้นไม้
                if (newPetTileX >= 0 && newPetTileX < MAP_WIDTH &&
                    newPetTileY >= 0 && newPetTileY < MAP_HEIGHT &&
                    !hasTree[newPetTileY][newPetTileX]) {
                    petX = newPetTileX * TILE_SIZE;
                    petY = newPetTileY * TILE_SIZE;
                    
                }
            }
        }

        // วาดใหม่ทั้งหมด
        drawMap(gc);
        if (activePet != null) {
            gc.drawImage(activePet.getImage(), petX, petY, TILE_SIZE, TILE_SIZE);
        }
        player.draw(gc);
        player.drawHearts(gc);
    }


    private void showPetDialog(Stage stage, BasePets pet, GraphicsContext gc) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("พบสัตว์เลี้ยง!");
        alert.setHeaderText("คุณพบ " + pet.getName() + "!");
        alert.setContentText("คุณต้องการให้ " + pet.getName() + " เข้าร่วมการผจญภัยไหม?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                pets = new BasePets[] { pet };
                activePet = pet;
                drawMap(gc);
                drawPets(gc);
                player.draw(gc);
                player.drawHearts(gc);
                startFirstQuest(stage);
            }
        });
    }

    private void startFirstQuest(Stage stage) {
        Alert questAlert = new Alert(Alert.AlertType.INFORMATION);
        questAlert.setTitle("เริ่มเควสแรก!");
        questAlert.setHeaderText("คุณพร้อมแล้ว!");
        questAlert.setContentText("ภารกิจแรก: จัดการซอมบี้ในป่า!");
        questAlert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

