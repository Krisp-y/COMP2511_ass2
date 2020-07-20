package unsw.dungeon;

public class Weapon extends Entity implements Collider {
    
    private int health;

    public Weapon(int x, int y) {
        super(x, y);
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
        p.addWeapon(this);
    }
}
    

