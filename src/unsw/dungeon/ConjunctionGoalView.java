package unsw.dungeon;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConjunctionGoalView extends GoalView {
    private String title;
    private List<GoalView> subgoals;
    private ConjunctionGoal cg;
    private StackPane textStackPane;
    private StackPane subgoalStackPane;
    private StackPane parent;
    
    public ConjunctionGoalView(ConjunctionGoal cg, StackPane parent) {
        super(cg);
        this.cg = cg;
        this.parent = parent;
        if (cg instanceof LogicalAndGoal) {
            title = "AND";
        } else if (cg instanceof LogicalOrGoal) {
            title = "OR";
        }
        setupView();
    }
    
    private void setupView() {
        HBox subViews = new HBox();
        
        subViews.setPrefHeight(parent.getPrefHeight());
        subViews.setPrefWidth(parent.getPrefWidth());
        
        parent.getChildren().add(subViews);
        subViews.setAlignment(Pos.CENTER);
        
        subgoalStackPane = new StackPane();
        textStackPane = new StackPane();
        subViews.getChildren().add(subgoalStackPane);
        subViews.getChildren().add(textStackPane);
        
        subgoalStackPane.setPrefWidth(0.8*subViews.getPrefWidth());
        textStackPane.setPrefWidth(0.2*subViews.getPrefWidth());
        subgoalStackPane.setPrefHeight(subViews.getPrefHeight());
        textStackPane.setPrefHeight(subViews.getPrefHeight());
        
        VBox subGoalVbox = new VBox();
        subgoalStackPane.getChildren().add(subGoalVbox);
        subGoalVbox.setPrefHeight(subgoalStackPane.getPrefHeight());
        subGoalVbox.setPrefWidth(subgoalStackPane.getPrefWidth());
        
        int numSubgoals = cg.getSubGoals().size();
        
        for (Goal subgoal : cg.getSubGoals()) {
            StackPane sp = new StackPane();
            sp.setPrefHeight(subGoalVbox.getPrefHeight() / numSubgoals);
            sp.setPrefWidth(subGoalVbox.getPrefWidth());
            
            subGoalVbox.getChildren().add(sp);
            if (subgoal instanceof BasicGoal) {
                new BasicGoalView((BasicGoal) subgoal, sp);
            } else if (subgoal instanceof ConjunctionGoal) {
                new ConjunctionGoalView((ConjunctionGoal) subgoal, sp);
            }
        }
        
        // Set the text look
        textStackPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        Text t = new Text(title);
        t.setRotate(90);
        t.setFont(Font.loadFont("../../fonts/CompassPro.ttf", 20));
        textStackPane.getChildren().add(t);
    }
    
    public void update(boolean b) {
        if (b) {
            textStackPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            textStackPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }   
    }
        
    @Override
    public String toString() {
        return title;
    }
}