package unsw.dungeon;

public class FloorSwitch extends Entity implements Collider, GoalPublisher {
    
    private GoalSubscriber boulderGoal;
    private Dungeon dungeon;
    
    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.boulderGoal = null;
        this.dungeon = dungeon;
    }

    @Override
    public void subscribe(GoalSubscriber gs) {
        boulderGoal = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber gs) {
        boulderGoal = null;
    }

    @Override
    public void notifySubscribers() {
        boulderGoal.goalUpdate();
    }

    @Override
    public void handleCollision(Moveable m) {
        // If there is a boulder collision, notify the floor switch goal that a boulder moved
        // on top of the switch.
        if (m instanceof Boulder) {
            notifySubscribers();
        }
        // If the collision is with a player, let them move through if there is no boulder on top. If there is a boulder
        // on top, then handle the collision through the boulder.
        if (m instanceof Player) {
            Boulder b = dungeon.getCollidingBoulder(getX(), getY());
            if (b == null) {
                m.move(m.getDirection());
            } else {
                b.handleCollision(m);
                // By the boulder off the floorswitch, we may have incompleted a goal.
                notifySubscribers();
            }
        }
        
        // Enemies can move on floor swithces if there is no boulder
        if (m instanceof Enemy) {
            Boulder b = dungeon.getCollidingBoulder(getX(), getY());
            if (b == null) {
                m.move(m.getDirection());
            }
        }
    }
}