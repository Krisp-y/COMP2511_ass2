package unsw.dungeon;

public class Enemy extends Moveable implements GoalPublisher, Tickable, Collider {
    
    private GoalSubscriber enemyGoal;
    private EnemyMovementStrategy ms;
    private boolean isAlive;
    
    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.ms = new AttackStrategy(dungeon, this);
        this.enemyGoal = null;
        this.isAlive = true;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) { // If the enemy collides with a player
            Player p = (Player) m;
            p.move(p.getDirection());
            playerCollision(p);
        }
    }
    
    public void playerCollision(Player p) {
        if(p.hasWeapon()) { // If the player has a weapon, kill the enemy and reduce weapon health;
            p.reduceWeaponHealth();
            
            if(p.getWeaponHealth() == 0) {
                p.removeWeapon();
            }
            isAlive = false;
            notifySubscribers();
            dungeon.removeEntity(this);
        } else if (p.isInvincible()) { // If the player is invincible from the potion, kill the enemy.
            notifySubscribers();
            isAlive = false;
            dungeon.removeEntity(this);
        } else { // Otherwise the player is killed by the enemy.
            dungeon.removeEntity(p);
            System.out.println("Game Ending Collision");
            dungeon.endGameLost();
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
        if (enemyGoal == null) {
            return;
        }
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
    
    public boolean isAlive() {
        return isAlive;
    }
}