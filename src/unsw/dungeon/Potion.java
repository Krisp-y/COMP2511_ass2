package unsw.dungeon;

public class Potion extends Entity implements Collider, Tickable {
    
    private int health;
    private Player player;
    private boolean collected;
    public Potion(int x, int y) {
        super(x, y);
        this.health = Dungeon.POTION_HEALTH;
        this.player = null;
        this.collected = false;
    }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            System.out.println("m" + " collided with " + this);
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
            if (health == 0) {
                player.removePotion(this);
                return;
            }
            player.updatePotionHealth(this);
        }
    }
    
    public int getHealth() {
        return health;
    }
}