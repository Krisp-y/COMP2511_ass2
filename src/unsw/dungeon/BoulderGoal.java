package unsw.dungeon;

import java.util.ArrayList;

public class BoulderGoal extends BasicGoal {
    
    private boolean isComplete;
    public BoulderGoal(ArrayList<Entity> floorSwitches) {
        super(floorSwitches);
        this.isComplete = false;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void tryComplete() {
        isComplete = true;
    }
    
    @Override
    public String toString() {
        return "boulder";
    }
}