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
            Player p = (Player)m;
            if(p.hasWeapon()) {
                p.reduceWeaponHealth();
                if(p.checkWeaponHealth() == 0) {
                    p.removeWeapon();;
                }
                //kill enemy
                dungeon.removeEntity(this);
                notifySubscribers();
                //Zac please edit base on potion implementation
            } else if (p.hasPotion()) {
                dungeon.removeEntity(this);
                notifySubscribers();
            } else {
            dungeon.endGame();
        }
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