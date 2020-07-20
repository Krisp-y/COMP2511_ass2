package unsw.dungeon;

import java.util.List;

public class EnemyGoal extends BasicGoal {
    
    private boolean isComplete;
    public EnemyGoal(Dungeon dungeon, List<Entity> enemies) {
        super(dungeon, enemies);
        this.isComplete = false;
    }
    
    public void setComplete() {
        this.isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    @Override
    public String toString() {
        return "enemies";
    }

    @Override
    public void update() {
        notifySubscribers();
    }
}