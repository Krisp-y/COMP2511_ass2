package unsw.dungeon;

import java.util.List;

public class FloorSwitch extends Entity implements Collider, GoalPublisher {
    
    private GoalSubscriber floorSwitchGoal;
    public FloorSwitch(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void subscribe(GoalSubscriber gs) {
        
    }

    @Override
    public void unsubscribe(GoalSubscriber gs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifySubscribers() {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleCollision(Moveable m) {
        // TODO Auto-generated method stub
    }
    
}