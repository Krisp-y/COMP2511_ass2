package unsw.dungeon;

import java.util.List;

public class ExitGoal extends BasicGoal {
    private boolean complete;
    private List<Entity> exitSwitches;
    public ExitGoal(Dungeon dungeon, List<Entity> exitSwitches) {
        super(dungeon, exitSwitches);
        this.exitSwitches = exitSwitches;
        this.complete = false;
    }
    
    public void checkComplete() {
        complete = false;
        for (Entity exitSwitch : exitSwitches) {
            if (dungeon.isPlayerOn(exitSwitch)) {
                setComplete(true);
            }
        }
    }
    
    @Override
    public boolean isComplete() {
        checkComplete();
        return complete;
    }
    
    
    
    @Override
    public String toString() {
        return "exit";
    }

    @Override
    public void goalUpdate() {
        notifySubscribers();
    }
    
    public void setComplete(boolean b) {
        complete = b;
        gvListener.update(complete);
    }

}