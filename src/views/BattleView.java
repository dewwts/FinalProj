package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import models.Ghost;
import models.Player;

public class BattleView {
    private Scene scene;
    private Button attackButton;
    private Button defendButton;
    private Player player;
    private Ghost ghost;

    public BattleView(Player player, Ghost ghost) {
        this.player = player;
        this.ghost = ghost;

        attackButton = new Button("Attack");
        defendButton = new Button("Defend");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(attackButton, defendButton);

        scene = new Scene(layout, 800, 600);
    }

    public Scene getScene() { return scene; }
    public Button getAttackButton() { return attackButton; }
    public Button getDefendButton() { return defendButton; }
}

