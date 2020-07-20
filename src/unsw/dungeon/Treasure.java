package unsw.dungeon;

public class Treasure extends Entity implements Collider, GoalPublisher {
    
    private GoalSubscriber treasureGoal;
    public Treasure(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            treasureCollision((Player) m);
            m.move(m.getDirection());
            notifySubscribers();
        }
    }

    private void treasureCollision(Player p) {
        p.collectItem(this);
    }

    @Override
    public void notifySubscribers() {
        if (treasureGoal == null) {
            return;
        }
        treasureGoal.update();
    }
    
    @Override
    public void subscribe(GoalSubscriber gs) {
        this.treasureGoal = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber s) {
       this.treasureGoal = null;
    }
}