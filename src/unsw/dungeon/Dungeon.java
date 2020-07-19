/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
     
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }
    
    public void removePortal(int ID) {
        entities.removeIf(e -> e instanceof Portal && ((Portal)e).getID() == ID);
    }
    /**
     * Used by moveable objects to determine if they are colliding with
     * collidable objects. The function returns a reference to the an
     * entity IF the entity is collidable AND the entities coordinates are
     * (x, y). 
     *
     * @param x x-coordinate in the dungeon
     * @param y y-coordinate in the dungeon
     * @return reference to colliding entity.
     */
    public Collider getCollidingEntity(int x, int y) {
        for (Entity e: entities) {
            if (e instanceof Collider && e.getX() == x && e.getY() == y) {
                return (Collider) e;
            }
        }
        return null;
    }
}
