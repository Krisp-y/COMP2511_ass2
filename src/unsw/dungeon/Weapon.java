package unsw.dungeon;

public class Weapon extends Entity implements Collider {
    Dungeon dungeon;
    int health;

    public Weapon(Dungeon dungeon, int x, int y, int health) {
        super(x, y);
        this.dungeon = dungeon;
        this.health = 5;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            weaponCollision((Player) m);
            m.move(m.getDirection());
        }
    }

    public int getHealth() {
        return health;
    }

    public void decrementHealth() {
        health--;
    }
    private void weaponCollision(Player p) {
        if(p.hasWeapon()) {
            return;
        }
        p.collectItem(this);
        dungeon.removeEntity(this);
        
    }
}
    

