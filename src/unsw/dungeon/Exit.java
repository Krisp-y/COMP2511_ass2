package unsw.dungeon;

public class Exit extends Entity implements Collider, GoalPublisher {
    
    private GoalSubscriber exitGoal;
    public Exit(int x, int y) {
        super(x, y);
        this.exitGoal = null;
    }

    @Override
    public void handleCollision(Moveable m) {
        notifySubscribers();
    }

    @Override
    public void notifySubscribers() {
        exitGoal.update();
    }
    
    @Override
    public void subscribe(GoalSubscriber gs) {
        this.exitGoal = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber s) {
       this.exitGoal = null;
    
    }
}