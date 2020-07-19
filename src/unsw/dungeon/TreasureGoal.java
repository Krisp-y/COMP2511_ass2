package unsw.dungeon;

import java.util.ArrayList;

public class TreasureGoal extends BasicGoal {
    
    private boolean isComplete;
    public TreasureGoal(ArrayList<Entity> treasures) {
        super(treasures);
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
        return "treasure";
    }
    
}