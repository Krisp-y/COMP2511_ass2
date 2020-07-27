package unsw.dungeon;

public class RetreatStrategy implements EnemyMovementStrategy {

    private Enemy enemy;
    private Dungeon dungeon;
    public RetreatStrategy(Dungeon dungeon, Enemy e) {
        this.enemy = e;
        this.dungeon = dungeon;
    }
    
    @Override
    public void move() {
        int x = dungeon.getPlayerPositionX();
        int y = dungeon.getPlayerPositionY();
        
        int prevx = enemy.getX();
        int prevy = enemy.getY();
        
        Direction[] bestDirections = calculateNextDirection(x, y);
        enemy.tryMove(bestDirections[0]);
        
        // If the enemy can't move, try and move in the second best direction.
        if (enemy != null && prevx == enemy.getX() && prevy == enemy.getY()) {
            enemy.tryMove(bestDirections[1]);
        }
        
    }
    
    /**
     * Super advanced algorithm that determines the next best directions to retreat in for an enemy given the players position.
     * @param x players x position
     * @param y players y position
     * @return Directoin array where the first entry is the best direction and the second entry is second best.
     */
    public Direction[] calculateNextDirection(int x, int y) {
        int dx = x - enemy.getX();
        int dy = y - enemy.getY();
        Direction[] d = {Direction.STATIONARY, Direction.STATIONARY};

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                d[0] = Direction.LEFT;
                if (dy > 0) {
                    d[1] = Direction.UP;
                } else {
                    d[1] = Direction.DOWN;
                }
            } else {
                d[0] = Direction.RIGHT;
                if (dy > 0) {
                    d[1] = Direction.UP;
                } else {
                    d[1] = Direction.DOWN;
                }
            }
        } else {
            if (dy > 0) {
                d[0] = Direction.UP;
                if (dx > 0) {
                    d[1] = Direction.LEFT;
                } else {
                    d[1] = Direction.RIGHT;
                }
            } else {
                d[0] = Direction.DOWN;
                if (dx > 0) {
                    d[1] = Direction.LEFT;
                } else {
                    d[1] = Direction.RIGHT;
                }
            }
        }
        
        return d;
        
    }

}
