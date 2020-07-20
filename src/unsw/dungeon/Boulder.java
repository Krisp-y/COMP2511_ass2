package unsw.dungeon;

public class Boulder extends Moveable implements Collider {
    
    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public void handleCollision(Moveable m) {
        // If the moveable object is a player then we have to handle movement
        // of the boulders (if necessary)
        if (m instanceof Player) {
            handlePlayerCollision((Player) m);
        }
    }
    
    /**
     * Handles collision between a boulder and a player. When the player
     * collides with a boulder, the boulder will move to the adjacent square
     * in the direction of the player. If the square above them is empty,
     * or is a floor switch, the boulder will move onto that square and the
     * player moves to the adjacent square in it's direction. If the square
     * next to the boulder is empty, the boulder and player will not move.
     * @param p
     */
    private void handlePlayerCollision(Player p) {
        Direction playerMoveDirection = p.getDirection();
        Collider collidingEntity;

        switch (playerMoveDirection) {
            case UP:
                collidingEntity = dungeon.getCollidingEntity(getX(), getY() - 1);
                // If there is no colliding entity above the boulder, move it up
                // and move the player up.
                if (canMoveInto(collidingEntity)) {
                    moveUp();
                    p.moveUp();
                }
                
                if (collidingEntity instanceof FloorSwitch) {
                    collidingEntity.handleCollision(this);
                }
                
                break;
            case DOWN:
                collidingEntity = dungeon.getCollidingEntity(getX(), getY() + 1);
                // If there is no colliding entity below the boulder, or the colliding entity is a switch
                // move the boulder down and the player down
                if (canMoveInto(collidingEntity)) {
                    moveDown();
                    p.moveDown();
                } 
                
                if (collidingEntity instanceof FloorSwitch) {
                    collidingEntity.handleCollision(this);
                }
                
                break;
            case LEFT:
                collidingEntity = dungeon.getCollidingEntity(getX() - 1, getY());
                // If there is no colliding entity left of the boulder, move the
                // boulder left and the player left
                if (canMoveInto(collidingEntity)) {
                    moveLeft();
                    p.moveLeft();
                } 
                
                if (collidingEntity instanceof FloorSwitch) {
                    collidingEntity.handleCollision(this);
                }
                
                break;
            case RIGHT:
                // If there is no colliding entity right of  the boulder, move the
                // boulder right and the player right
                collidingEntity = dungeon.getCollidingEntity(getX() + 1, getY());
                if (canMoveInto(collidingEntity)) {
                    moveRight();
                    p.moveRight();
                }
                
                if (collidingEntity instanceof FloorSwitch) {
                    collidingEntity.handleCollision(this);
                }
                
                break;
            default:
                return;
        }
    }
    
    private boolean canMoveInto(Collider collidingEntity) {
        return collidingEntity == null || collidingEntity instanceof FloorSwitch;
    }
}