package unsw.dungeon;

public class Treasure extends Entity implements Collider{
        Dungeon dungeon;

        public Treasure(Dungeon dungeon, int x, int y) {
            super(x, y);
            this.dungeon = dungeon;

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
        dungeon.removeEntity(this);
    }
}