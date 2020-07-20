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
            System.out.println("here!");
            player.addPotion(this);
            m.move(m.getDirection());
        }
    }

    @Override
    public void tick() {
        if (collected) {
            health--;
            if (health == 0) {
                player.removePotion(this);
            }
        }
    }
}