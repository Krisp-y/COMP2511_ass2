package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import unsw.dungeon.Key;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Moveable {
    private List<Entity> inventory;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.inventory = new ArrayList<>();
    }   

    public void teleport(int x, int y) {
        x().set(x);
        y().set(y);
    }

    public List<Entity> getInventory() {
        return this.inventory;
    }

    public void collectItem(Entity e) {
        inventory.add(e);
    }

    public void removeCollectable(Entity e) {
        inventory.remove(e);
    }

    public boolean hasKey() {
        for (Entity e: inventory) {
            if (e instanceof Key) {
                return true;
            }
        }
        return false;
    }

    public int getKeyID() {
        for (Entity e: inventory) {
            if (e instanceof Key) {
                Key key = (Key)e;
                return key.getKeyID();
            }
        }
        return -1;
    }

    public void useKey() {
        inventory.removeIf(e -> e instanceof Key);
    }

    public int countTreasure() {
        int count = 0;
        for(Entity e: inventory ) {
            if (e instanceof Treasure) {
                count++;
            }
        }
        return count;
    }
}
