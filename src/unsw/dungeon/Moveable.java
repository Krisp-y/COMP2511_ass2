package unsw.dungeon;

public abstract class Moveable extends Entity {
    protected Dungeon dungeon;
    
    // Moveable is trying to move in direction d.
    // Necessary for resolving collisions with objects whose collision
    // event needs to know the direction of the thing which is colliding
    // with it. For example, players colliding with boulders. The boulder
    // needs to know which direction the player is moving.
    private Direction direction;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Moveable(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.direction = Direction.STATIONARY;
    }
    
    /**
     * Function for moveable objects to attempt moving to the next adjacent tile
     * In the directino of d. Moveable object checks 
     * if there is a collision with a collidable object, and calls the 
     * handleCollision function of that object to resolve any state changes as 
     * a result of the collision. In the case where there is no collision, 
     * the moveable object simply moves in the direction d
     *
     * @param d Direction enum, the direction the moveable wants to move.
     */
    public void tryMove(Direction d) {
        if (d == Direction.UP) {
            tryMoveUp();
        } else if (d == Direction.DOWN) {
            tryMoveDown();
        } else if (d == Direction.LEFT) {
            tryMoveLeft();
        } else if (d == Direction.RIGHT) {
            tryMoveRight();
        }
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    /** Attempts to move Moveable up, changes state on collision. */
    public void tryMoveUp() {        
        direction = Direction.UP;
        Collider c = dungeon.getCollidingEntity(getX(), getY() - 1);
        if (c == null) {
            moveUp();
        } else { // There is a collision
            c.handleCollision(this);
        }
    }
    
    /** Attempts to move Moveable down, changes state on collision. */
    public void tryMoveDown() {
        direction = Direction.DOWN;
        Collider c = dungeon.getCollidingEntity(getX(), getY() + 1);
        if (c == null) {
            moveDown();
        } else { // There is a collision
            c.handleCollision(this);

        }
    }
    
    /** Attempts to move Moveable left, changes state on collision. */
    public void tryMoveLeft() {
        direction = Direction.LEFT;
        Collider c = dungeon.getCollidingEntity(getX() - 1, getY());
        if (c == null) {
            moveLeft();
        } else { // There is a collision
            c.handleCollision(this);
        }
    }
    
    /** Attempts to move Moveable right, changes state on collision. */
    public void tryMoveRight() {
        direction = Direction.RIGHT;
        Collider c = dungeon.getCollidingEntity(getX() + 1, getY());
        if (c == null) {
            moveRight();
        } else { // There is a collision
            c.handleCollision(this);
        }
    }
    
    /**
     * Forces a move in the direction d - used when trying to override collision events. E.g players moving over switches.
     * @param d Direction of movement.
     */
    public void move(Direction d) {
        if (d == Direction.UP) {
            moveUp();
        } else if (d == Direction.DOWN) {
            moveDown();
        } else if (d == Direction.LEFT) {
            moveLeft();
        } else if (d == Direction.RIGHT) {
            moveRight();
        }
    }
    
    /** Moves moveable upwards */
    public void moveUp() {    
        if (getY() > 0)
            y().set(getY() - 1);
    }
    
    /** Moves moveable downwards */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }
    
    /** Moves moveable left */
    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }
    
    /** Moves moveable downwards */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
}