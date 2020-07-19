package unsw.dungeon;

public class Door extends Entity implements Collider {
    private int ID;
    Dungeon dungeon;
    int x;
    int y;

    public Door(int x, int y, int ID, Dungeon dungeon) {
        super(x, y);
        this.ID = ID;

    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            doorCollision((Player) m);
        }
    } 
    
    private void doorCollision(Player p) {
        Direction playerMoveDirection = p.getDirection();
        Collider collidingEntity;

        switch (playerMoveDirection) {
            case UP:
                collidingEntity = dungeon.getCollidingEntity(getX(), getY() - 1);
                // If there is no colliding entity above the boulder, move it up
                // and move the player up.
                if (collidingEntity == null) {
                    if(p.hasKey() && p.getID() == this.getDoorID) {
                        p.moveUp();
                    }
                    
                }
                break;
            case DOWN:
                collidingEntity = dungeon.getCollidingEntity(getX(), getY() + 1);
                // If there is no colliding entity below the boulder, move the
                // boulder down and the player down
                if (collidingEntity == null) {
                    if(p.hasKey() && p.getID() == this.getDoorID) {
                        p.moveDown();
                    }
                    
                }
                break;
            case LEFT:
                collidingEntity = dungeon.getCollidingEntity(getX() - 1, getY());
                // If there is no colliding entity left of the boulder, move the
                // boulder left and the player left
                if (collidingEntity == null) {
                    if(p.hasKey() && p.getID() == this.getDoorID) {
                        p.moveLeft();
                    }
                    
                }
                break;
            case RIGHT:
                // If there is no colliding entity right of  the boulder, move the
                // boulder right and the player right
                collidingEntity = dungeon.getCollidingEntity(getX() + 1, getY());
                if (collidingEntity == null) {
                    if(p.hasKey() && p.getID() == this.getDoorID) {
                        p.moveRight();
                    }
                    
                }
                break;
            default:
                return;
        }
        

    }

    public int getDoorID() {
        return this.ID;
    }
    
}