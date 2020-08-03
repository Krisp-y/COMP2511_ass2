package unsw.dungeon;

public class FireBall extends Moveable implements Collider {
    public FireBall(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void handleCollision(Moveable m) {
        
        //Move fireball in direction dragon is moving
        if(m instanceof Dragon) {
            m.getDirection();
            if (m.getDirection() == Direction.UP) {
                this.moveUp();
            } else if (m.getDirection() == Direction.DOWN) {
                moveDown();
            } else if (m.getDirection() == Direction.LEFT) {
                moveLeft();
            } else if (m.getDirection() == Direction.RIGHT) {
                moveRight();
            }
            //End dragon collision
            return;
        }

        if (m instanceof Player) { // If the fireball hits a player
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

}
    
