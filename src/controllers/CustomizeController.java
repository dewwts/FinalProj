package controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BasePet;
import models.Dog;
import models.Cat;
import models.Unicorn;
import utils.ImageLoader;
import models.BaseWeapon;
import models.Sword;
import models.MagicWand;
import models.Moodeng;

public class CustomizeController {
    private Stage stage;
    private BasePet selectedPet;
    private BaseWeapon selectedWeapon;

    public CustomizeController(Stage stage) {
        this.stage = stage;
    }

    public void showCustomization() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // ส่วนหัว
        Label titleLabel = new Label("Please select Weapons and Pets");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // สัตว์เลี้ยง
        Label petLabel = new Label("เลือกสัตว์เลี้ยง:");
        petLabel.setStyle("-fx-font-size: 18px;");

        HBox petOptions = new HBox(20);
        petOptions.setAlignment(Pos.CENTER);

        Button dogButton = createPetButton(new Dog(ImageLoader.load("dog.png"), 0,0), "dog.png");
        Button catButton = createPetButton(new Cat(ImageLoader.load("cat.png"), 0,0), "cat.png");
        Button unicornButton = createPetButton(new Unicorn(ImageLoader.load("unicorn.png"), 0,0), "unicorn.png");

        petOptions.getChildren().addAll(dogButton, catButton, unicornButton);

        // อาวุธ
        Label weaponLabel = new Label("เลือกอาวุธ:");
        weaponLabel.setStyle("-fx-font-size: 18px;");

        HBox weaponOptions = new HBox(20);
        weaponOptions.setAlignment(Pos.CENTER);

        Button swordButton = createWeaponButton(new Sword(ImageLoader.load("sword.png"), 0,0), "sword.png");
        Button magicWandButton = createWeaponButton(new MagicWand(ImageLoader.load("magic_wand.png"), 0,0), "magic_wand.png");
        Button moodengButton = createWeaponButton(new Moodeng(ImageLoader.load("moodeng.png"), 0,0), "moodeng.png");

        weaponOptions.getChildren().addAll(swordButton, magicWandButton, moodengButton);
        // ปุ่มยืนยัน
        Button confirmButton = new Button("ยืนยัน");
        confirmButton.setStyle("-fx-font-size: 16px;");
        confirmButton.setOnAction(e -> handleConfirmation());
        
        Button startButton = new Button("เริ่มเกม");
        startButton.setStyle("-fx-font-size: 16px;");
        
        startButton.setOnAction(e -> {
            System.out.println("Starting the game...");
            GameController gameController = new GameController(stage, selectedPet, selectedWeapon);
            gameController.startGame();
        });

        // จัด Layout
        root.getChildren().addAll(titleLabel, petLabel, petOptions, weaponLabel, weaponOptions, confirmButton, startButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public Button createPetButton(BasePet pet, String imagePath) {
        Button button = new Button(pet.getName());
        button.setGraphic(createImageView(imagePath, 100, 100));
        button.setOnAction(e -> {
            selectedPet = pet;
            System.out.println("Selected Pet: " + pet.getName());
        });
        return button;
    }

    public Button createWeaponButton(BaseWeapon weapon, String imagePath) {
        Button button = new Button(weapon.getName());
        button.setGraphic(createImageView(imagePath, 100, 100));
        button.setOnAction(e -> {
            selectedWeapon = weapon;
            System.out.println("Selected Weapon: " + weapon.getName());
        });
        return button;
    }

    public ImageView createImageView(String imagePath, int width, int height) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public void handleConfirmation() {
        if (selectedPet == null || selectedWeapon == null) {
            System.out.println("กรุณาเลือกสัตว์เลี้ยงและอาวุธก่อนยืนยัน!");
        } else {
            System.out.println("คุณเลือก:");
            System.out.println("สัตว์เลี้ยง: " + selectedPet.getName());
            System.out.println("อาวุธ: " + selectedWeapon.getName());
            GameController gameController = new GameController(stage, selectedPet, selectedWeapon);
            gameController.startGame();
        }
    }
    
    public BasePet getSelectedPet() {
    	return selectedPet;
    }
    
    public BaseWeapon getSelectedWeapon() {
    	return selectedWeapon;
    }
}
