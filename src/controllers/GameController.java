package controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Player;
import models.Unicorn;
import models.BasePet;
import models.BaseWeapon;
import models.Cat;
import models.Dog;
import utils.ImageLoader;
import views.GameView;
import models.Sword;
import models.Moodeng;
import models.MagicWand;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private Stage stage;
    private Player player;
    private GameView gameView;
    private AnimationTimer gameLoop;
    private BasePet selectedPet;
    private BaseWeapon selectedWeapon;
    private List<BasePet> pets;
    private javafx.scene.image.Image playerImage;

    // ขนาด Tile และขนาดแผนที่
    private static final int TILE_SIZE = 64;
    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 8;

    // อาร์เรย์สำหรับเก็บข้อมูลว่าตำแหน่งไหนมีต้นไม้หรือหิน
    private boolean[][] hasTree;
    private boolean[][] hasRock;

    public GameController(Stage stage, BasePet selectedPet, BaseWeapon selectedWeapon) {
        this.stage = stage;
        this.selectedPet = selectedPet;
        this.selectedWeapon = selectedWeapon;
    }
    
    public GameController(Stage stage) {
    	this.stage = stage;
    }

    public void startGame() {
        // สร้างผู้เล่น
    	if(selectedWeapon instanceof Sword) {
    		if(selectedPet instanceof Cat) playerImage = ImageLoader.load("sword_cat.png");
    		if(selectedPet instanceof Dog) playerImage = ImageLoader.load("sword_dog.png");
    		if(selectedPet instanceof Unicorn) playerImage = ImageLoader.load("sword_unicorn.png");
    	}
    	if(selectedWeapon instanceof MagicWand) {
    		if(selectedPet instanceof Cat) playerImage = ImageLoader.load("wand_cat.png");
    		if(selectedPet instanceof Dog) playerImage = ImageLoader.load("wand_dog.png");
    		if(selectedPet instanceof Unicorn) playerImage = ImageLoader.load("wand_unicorn.png");
    	}
    	if(selectedWeapon instanceof Moodeng) {
    		if(selectedPet instanceof Cat) playerImage = ImageLoader.load("moodeng_cat.png");
    		if(selectedPet instanceof Dog) playerImage = ImageLoader.load("moodeng_dog.png");
    		if(selectedPet instanceof Unicorn) playerImage = ImageLoader.load("moodeng_unicorn.png");
    	}
    	    	
    	player = new Player(playerImage, 3 * TILE_SIZE, 3 * TILE_SIZE);
        // สร้างรายการสัตว์เลี้ยง (ถ้าจำเป็น)
        pets = new ArrayList<>();
        pets.add(new Dog(ImageLoader.load("dog.png"), randomX(), randomY()));
        pets.add(new Cat(ImageLoader.load("cat.png"), randomX(), randomY()));
        pets.add(new Unicorn(ImageLoader.load("unicorn.png"), randomX(), randomY()));

        // สร้างข้อมูลต้นไม้และหินบนแผนที่
        generateTreesAndRocks();

        // สร้าง GameView และส่งข้อมูลต้นไม้ หิน ไปด้วย
        gameView = new GameView(player, pets, hasTree, hasRock, MAP_WIDTH, MAP_HEIGHT, TILE_SIZE);
        stage.setScene(gameView.getScene());

        // เริ่มเกมลูป
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                gameView.render();
            }
        };
        gameLoop.start();

        // ตั้งค่า input controls สำหรับการเคลื่อนที่
        initInputControls();
    }

    private void update() {
        // อัปเดตสถานะเกมถ้าจำเป็น
    }

    private void initInputControls() {
        gameView.getScene().setOnKeyPressed(event -> handleMovement(event));
    }

    private void handleMovement(KeyEvent event) {
        double newX = player.getX();
        double newY = player.getY();

        switch (event.getCode()) {
            case W: newY -= TILE_SIZE; break;
            case S: newY += TILE_SIZE; break;
            case A: newX -= TILE_SIZE; break;
            case D: newX += TILE_SIZE; break;
            default: break;
        }

        int tileX = (int) (newX / TILE_SIZE);
        int tileY = (int) (newY / TILE_SIZE);
        
        // ตรวจสอบขอบเขตของแผนที่
        if (tileX >= 0 && tileX < MAP_WIDTH && tileY >= 0 && tileY < MAP_HEIGHT) {
            // ตรวจสอบว่าตำแหน่งใหม่เป็นต้นไม้หรือหินหรือไม่
            if (!hasTree[tileY][tileX] && !hasRock[tileY][tileX]) {
                // ถ้าไม่มีต้นไม้และไม่มีหิน ผู้เล่นสามารถเดินได้
                player.setX(newX);
                player.setY(newY);
            } else {
                // ถ้ามีต้นไม้หรือหิน ผู้เล่นจะไม่ขยับ
                System.out.println("Cannot move there! It's blocked by a tree or a rock.");
            }
        }
        
    }
    
    private void generateTreesAndRocks() {
        hasTree = new boolean[MAP_HEIGHT][MAP_WIDTH];
        hasRock = new boolean[MAP_HEIGHT][MAP_WIDTH];

        Random random = new Random();

        // สร้างต้นไม้จำนวนหนึ่ง เช่น (MAP_WIDTH * MAP_HEIGHT) / 8 ต้น
        int numberOfTrees = (MAP_WIDTH * MAP_HEIGHT) / 8;
        for (int i = 0; i < numberOfTrees; i++) {
            int x, y;
            do {
                x = random.nextInt(MAP_WIDTH);
                y = random.nextInt(MAP_HEIGHT);
            } while (hasTree[y][x] || (x == 3 && y == 3)); // ป้องกันไม่ให้วางบนตำแหน่งผู้เล่นเริ่มต้น
            hasTree[y][x] = true;
        }

        // สร้างหินจำนวนหนึ่ง เช่น (MAP_WIDTH * MAP_HEIGHT) / 10 ก้อน
        int numberOfRocks = (MAP_WIDTH * MAP_HEIGHT) / 10;
        for (int i = 0; i < numberOfRocks; i++) {
            int x, y;
            do {
                x = random.nextInt(MAP_WIDTH);
                y = random.nextInt(MAP_HEIGHT);
            } while (hasRock[y][x] || hasTree[y][x] || (x == 3 && y == 3));
            hasRock[y][x] = true;
        }
    }

    public double randomX() {
        return new Random().nextInt(MAP_WIDTH) * TILE_SIZE;
    }

    public double randomY() {
        return new Random().nextInt(MAP_HEIGHT) * TILE_SIZE;
    }
    
    //Game Battle mission 1
    public void goToBattle() {
    	BattleController battleController = new BattleController(stage, player);
    	battleController.startBattle();
    }
    
}



