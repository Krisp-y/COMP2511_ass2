package unsw.dungeon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BasicGoalView extends GoalView {
    private ImageView image;
    private String type;
    private StackPane imageTile;
    private StackPane parent;
    
    public BasicGoalView(BasicGoal bg, StackPane parent) {
        super(bg);
        this.parent = parent;
        if (bg instanceof EnemyGoal) {
            image = DungeonController.getImageFromPath("src/images/deep_elf_master_archer.png");
            type = "EnemyGoalView";
        } else if (bg instanceof TreasureGoal) {
            image = DungeonController.getImageFromPath("src/images/gold_pile.png");
            type = "TreasureGoalView";
        } else if (bg instanceof BoulderGoal) {
            image = DungeonController.getImageFromPath("src/images/boulder.png");
            type = "BoulderGoalView";
        } else if (bg instanceof ExitGoal) {
            image = DungeonController.getImageFromPath("src/images/exit.png");
            type = "ExitGoalView";
        }
        setupView();
    }
    
    public StackPane getNode() {
        return imageTile;
    }

    private void setupView() {
        imageTile = new StackPane();
        imageTile.setMinSize(DungeonController.BASIC_GOAL_VIEW_SIZE, DungeonController.BASIC_GOAL_VIEW_SIZE);
        imageTile.setMaxSize(DungeonController.BASIC_GOAL_VIEW_SIZE, DungeonController.BASIC_GOAL_VIEW_SIZE);
        imageTile.setPrefSize(DungeonController.BASIC_GOAL_VIEW_SIZE, DungeonController.BASIC_GOAL_VIEW_SIZE);
        parent.getChildren().add(imageTile);
        imageTile.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        imageTile.getChildren().add(image);
    }

    public void update(boolean b) {
        if (b) {
            imageTile.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            imageTile.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
    
    @Override
    public String toString() {
        return type;
    }

}