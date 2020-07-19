package unsw.dungeon;

import java.util.List;

public class ExitGoal extends BasicGoal {
    
    private GoalSubscriber dungeon;
    private boolean isComplete;
    public ExitGoal(GoalSubscriber dungeon, List<Entity> exitSwitches) {
        super(dungeon, exitSwitches);
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
        return "exit";
    }

    @Override
    public void update() {
        setComplete(true);
        notifySubscribers();
    }

    @Override
    public void subscribe(GoalSubscriber gs) {
        dungeon = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber gs) {
       dungeon = null;
    }

    @Override
    public void notifySubscribers() {
        dungeon.update();
    }
}