package unsw.dungeon;

import java.util.List;

public class BoulderGoal extends BasicGoal {
    
    private boolean isComplete;
    public BoulderGoal(GoalSubscriber dungeon, List<Entity> floorSwitches, List<Entity> boulders) {
        super(dungeon, floorSwitches);
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
        return "boulders";
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