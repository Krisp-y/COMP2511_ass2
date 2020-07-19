package unsw.dungeon;

import java.util.ArrayList;

public class ExitGoal extends BasicGoal {
    
    private boolean isComplete;
    public ExitGoal(ArrayList<Entity> exitSwitch) {
        super(exitSwitch);
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
        return "exit";
    }
}