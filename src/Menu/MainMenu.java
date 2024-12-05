package Menu;
import GameController.MapGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private Button newGameButton; // ประกาศตัวแปรระดับคลาส

    @Override
    public void start(Stage primaryStage) {
        // สร้างปุ่มต่าง ๆ
        newGameButton = new Button("เริ่มเกมใหม่"); // กำหนดค่าที่นี่
        Button loadGameButton = new Button("โหลดเกม");
        Button settingsButton = new Button("ตั้งค่า");
        Button exitButton = new Button("ออกจากเกม");

        // กำหนดการทำงานของปุ่ม
        newGameButton.setOnAction(e -> startNewGame());
        loadGameButton.setOnAction(e -> loadGame());
        settingsButton.setOnAction(e -> openSettings());
        exitButton.setOnAction(e -> primaryStage.close());

        // จัดเรียงปุ่มใน VBox
        VBox vbox = new VBox(10); // ระยะห่างระหว่างปุ่ม 10 พิกเซล
        vbox.getChildren().addAll(newGameButton, loadGameButton, settingsButton, exitButton);
        vbox.setStyle("-fx-padding: 50; -fx-alignment: center;");

        // สร้างฉากและตั้งค่า
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // เปิด fullscreen
        primaryStage.setFullScreenExitHint("กด ESC เพื่อออกจากโหมดเต็มหน้าจอ"); // ข้อความแจ้ง
        primaryStage.setTitle("เกมผจญภัยตามหาสัตว์เลี้ยง");
        primaryStage.show();
    }

    // เมธอดตัวอย่างสำหรับการทำงานของปุ่ม
    private void startNewGame() {
        // เปลี่ยน Scene ไปที่ MapGame
        GameController.MapGame mapGame = new GameController.MapGame();
        Stage currentStage = (Stage) newGameButton.getScene().getWindow();
        try {
            mapGame.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGame() {
        // โค้ดสำหรับโหลดเกม
        System.out.println("โหลดเกม");
    }

    private void openSettings() {
        // โค้ดสำหรับเปิดหน้าตั้งค่า
        System.out.println("เปิดหน้าตั้งค่า");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

