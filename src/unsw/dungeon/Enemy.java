package unsw.dungeon;

public class Enemy extends Moveable implements GoalPublisher, Tickable, Collider {
    
    private GoalSubscriber enemyGoal;
    private EnemyMovementStrategy ms;
    
    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.ms = new AttackStrategy(dungeon, this);
        this.enemyGoal = null;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            dungeon.endGame();
        }
    }

    @Override
    public void tick() {
        ms.move();
    }
    
    public void setStrategy(EnemyMovementStrategy ms) {
        this.ms = ms;
    }
    
    @Override
    public void notifySubscribers() {
        enemyGoal.update();
    }
    
    @Override
    public void subscribe(GoalSubscriber gs) {
        this.enemyGoal = gs;
    }

    @Override
    public void unsubscribe(GoalSubscriber s) {
       this.enemyGoal = null;
    }
    
}