package unsw.dungeon;

public class Key extends Entity implements Collider {
    int kID;

    public Key(int x, int y, int kID) {
        super(x, y);
        this.kID = kID;
    }
    
    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            keyCollision((Player) m);
            m.move(m.getDirection());
        }
    }
    public int getKeyID() {
        return kID;
    }

    void setID(int ID) {
        this.kID = ID;
    }

    private void keyCollision(Player p) {
        //not a collision if they already have key
        if(p.hasKey()) {
            return;
        }
        p.collectItem(this);
    }
}