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
        for (Entity exitSwitch : exitSwitches) {
            if (dungeon.isPlayerOn(exitSwitch)) {
                return true;
            }
        }
        return false;
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