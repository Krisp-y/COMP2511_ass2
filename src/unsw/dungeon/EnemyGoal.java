package unsw.dungeon;

import java.util.List;

public class EnemyGoal extends BasicGoal {
    
    private boolean isComplete;
    public EnemyGoal(Dungeon dungeon, List<Entity> enemies) {
        super(dungeon, enemies);
        this.isComplete = false;
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
        // TODO Auto-generated method stub

    }

    @Override
    public void subscribe(GoalSubscriber gs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsubscribe(GoalSubscriber gs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifySubscribers() {
        // TODO Auto-generated method stub

    }
}