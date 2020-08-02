package unsw.dungeon;

import java.util.List;

public class ExitGoal extends BasicGoal {
    
    private List<Entity> exitSwitches;
    public ExitGoal(Dungeon dungeon, List<Entity> exitSwitches) {
        super(dungeon, exitSwitches);
        this.exitSwitches = exitSwitches;
    }

    @Override
    public boolean isComplete() {
        boolean result = false;
        for (Entity exitSwitch : exitSwitches) {
            if (dungeon.isPlayerOn(exitSwitch)) {
                result = true;
            }
        }
        gvListener.update(result);
        return result;
    }
    
    @Override
    public String toString() {
        return "exit";
    }

    @Override
    public void goalUpdate() {
        notifySubscribers();
    }

}