package unsw.dungeon;

import java.util.List;

public class TreasureGoal extends BasicGoal {
    
    private boolean isComplete;
    public TreasureGoal(GoalSubscriber dungeon, List<Entity> treasures) {
        super(dungeon, treasures);
        this.isComplete = false;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    
    @Override
    public String toString() {
        return "treasure";
    }

    @Override
    public void update() {
        
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