package unsw.dungeon;
import javafx.scene.layout.StackPane;

public abstract class GoalView {
    protected StackPane base;
    protected double width;
    protected double height;
    
    public GoalView(Goal g) {
        g.addListener(this);
    }
    
    public abstract void update(boolean b);
}