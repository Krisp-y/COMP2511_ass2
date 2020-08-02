package unsw.dungeon;

import java.util.List;

public abstract class BasicGoal implements Goal, GoalSubscriber, GoalPublisher {
    protected GoalView gvListener;
    protected Dungeon dungeon;
    public BasicGoal(Dungeon dungeon, List<Entity> entities) {
        for (Entity entity : entities) {
            // This is a sanity check, any time we create a basic goal we should
            // ensure that the entities it is subscribing too implement the goal
            // subcriber interface
            if (entity instanceof GoalPublisher) {
                GoalPublisher gp = (GoalPublisher) entity;
                gp.subscribe(this);
            }
        }
        subscribe(dungeon);
    }
    
    public void subscribe(GoalSubscriber gs) {
        this.dungeon = (Dungeon) gs;
    }

    public void unsubscribe(GoalSubscriber gs) {
        this.dungeon = null;
    }
    
    @Override
    public void notifySubscribers() {
        dungeon.goalUpdate();
    }
    
    public Goal getGoal() {
        return this;
    }
    
    public void addListener(GoalView gv) {
        gvListener = gv;
    }
    
    public abstract boolean isComplete();

}