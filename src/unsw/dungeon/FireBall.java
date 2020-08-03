package unsw.dungeon;

public class FireBall extends Moveable implements Collider, Tickable {
    private Direction direction;
    public FireBall(Dungeon dungeon, int x, int y, Direction d) {
        super(dungeon, x, y);
        this.direction = d;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) { // If the fireball hits a player
            System.out.println("Fireball collides with player");
            Player p = (Player) m;
            p.move(p.getDirection());
            FBplayerCollision(p);
        }
    }
    
    public void FBplayerCollision(Player p) {
    
        if (p.isInvincible()) { // If the player is invincible from the potion, absorb the fireball.
            dungeon.removeEntity(this);
        } else { // Otherwise the player is killed by the fireball
            dungeon.removeEntity(p);
            dungeon.endGameLost();
        }
    }

    @Override
    public void tick() {
        int prevx = getX();
        int prevy = getY();
        tryMove(direction);
        if (getX() == prevx && getY() == prevy) {
            dungeon.removeEntity(this);
        }
    }
}
    
