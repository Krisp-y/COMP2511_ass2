package unsw.dungeon;

public class Treasure extends Entity implements Collider{

        public Treasure(int x, int y) {
            super(x, y);
        }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            treasureCollision((Player) m);
            m.move(m.getDirection());
        }
    }

    private void treasureCollision(Player p) {
        p.collectItem(this);
    }
}