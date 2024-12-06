package controllers;

import javafx.stage.Stage;
import models.Ghost;
import models.Player;
import views.BattleView;

public class BattleController {
    private Stage stage;
    private Player player;
    private BattleView battleView;
    private Ghost ghost;
    private int defeatedGhosts;

    public BattleController(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
        this.defeatedGhosts = 0;
    }

    public void startBattle() {
        ghost = generateRandomGhost();
        battleView = new BattleView(player, ghost);
        stage.setScene(battleView.getScene());
        initEventHandlers();
    }

    private Ghost generateRandomGhost() {
        // Implement logic to generate a random ghost
        return new Ghost("Ghost", 20, 5, null, 0, 0);
    }

    private void initEventHandlers() {
        battleView.getAttackButton().setOnAction(e -> {
            // Implement attack logic
            player.getWeapon().useWeapon();
            ghost.setHealth(ghost.getHealth() - player.getWeapon().getAttackPower());
            if (ghost.getHealth() <= 0) {
                defeatedGhosts++;
                if (defeatedGhosts >= 10) {
                    System.out.println("You have defeated 10 ghosts!");
                    // Proceed to next mission
                } else {
                    // Generate next ghost
                    ghost = generateRandomGhost();
                }
            } else {
                ghost.attack(player);
                if (player.getHealth() <= 0) {
                    System.out.println("Game Over!");
                    // Handle game over
                }
            }
        });

        battleView.getDefendButton().setOnAction(e -> {
            // Implement defend logic
            System.out.println("Player defends!");
        });
    }
}

