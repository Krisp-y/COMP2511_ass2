package unsw.dungeon;

public class Potion extends Entity implements Collider, Tickable {
    
    private int health;
    public Potion(int x, int y) {
        super(x, y);
        this.health = 15;
    }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            potionCollision((Player) m);
            m.move(m.getDirection());
        }
    }


    private void potionCollision(Player p) {
        //not a collision if they already have key
        p.collectItem(this);
    }

    @Override
    public void tick() {
        
    }
}