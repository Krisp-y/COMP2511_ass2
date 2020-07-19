package unsw.dungeon;

import java.util.List;

public abstract class BasicGoal implements Goal, GoalSubscriber, GoalPublisher {
    
    public BasicGoal(GoalSubscriber dungeon, List<Entity> entities) {
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

    public abstract void setComplete(boolean isComplete);
}