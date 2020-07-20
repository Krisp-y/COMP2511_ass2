package unsw.dungeon;

import java.util.List;

public class Key extends Entity implements Collider {
    Dungeon dungeon;
    int kID;

    public Key(Dungeon dungeon, int x, int y, int kID) {
        super(x, y);
        this.dungeon = dungeon;
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
        dungeon.removeKey(this.getKeyID());
        
    }
}