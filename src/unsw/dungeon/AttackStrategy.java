package unsw.dungeon;

public class AttackStrategy implements EnemyMovementStrategy {
    
    private Enemy enemy;
    private Dungeon dungeon;
    public AttackStrategy(Dungeon dungeon, Enemy e) {
        this.enemy = e;
        this.dungeon = dungeon;
    }
    
    @Override
    public void move() {
        int x = dungeon.getPlayerPositionX();
        int y = dungeon.getPlayerPositionY();
        
        Direction direction = calculateNextDirection(x, y);
        enemy.tryMove(direction);
        
    }
    
    public Direction calculateNextDirection(int x, int y) {
        int dx = x - enemy.getX();
        int dy = y - enemy.getY();
        
        Direction d = Direction.STATIONARY;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                d = Direction.RIGHT;
            } else {
                d = Direction.LEFT;
            }
        } else {
            if (dy > 0) {
                d = Direction.DOWN;
            } else {
                d = Direction.UP;
            }
        }
        
        return d;
        
    }
    
}