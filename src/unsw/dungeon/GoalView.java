package unsw.dungeon;
import javafx.scene.layout.StackPane;

public abstract class GoalView {
    protected boolean isComplete;
    protected StackPane base;
    protected double width;
    protected double height;
    
    public GoalView(Goal g) {
        this.isComplete = false;
        g.addListener(this);
    }
    
    public abstract void update(boolean b);
}