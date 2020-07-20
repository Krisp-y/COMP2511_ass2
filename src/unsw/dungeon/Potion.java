package unsw.dungeon;

public class Potion extends Entity implements Collider, Tickable {
    
    private int health;
    Player player;
    private boolean collected;
    public Potion(int x, int y) {
        super(x, y);
        this.health = 15;
        this.player = null;
        this.collected = false;
    }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            player = (Player) m;
            player.addPotion(this);
            collected = true;
            m.move(m.getDirection());
        }
    }

    @Override
    public void tick() {
        if (collected) {
            health--;
        }
    }
    
    public int getHealth() {
        return health;
    }
}