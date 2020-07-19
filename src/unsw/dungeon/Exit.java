package unsw.dungeon;

public class Exit extends Entity implements Collider, GoalPublisher {
    
    private GoalSubscriber goal;
    public Exit(int x, int y) {
        super(x, y);
        this.goal = null;
    }

    @Override
    public void handleCollision(Moveable m) {
        notifySubscribers();
    }

    @Override
    public void subscribe(GoalSubscriber gs) {
        this.goal = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber s) {
       this.goal = null;
    }

    @Override
    public void notifySubscribers() {
        goal.update();
    }

}