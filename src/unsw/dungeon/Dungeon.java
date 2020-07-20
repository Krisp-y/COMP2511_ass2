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
public class Dungeon implements GoalSubscriber {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goal mainGoal;
    private boolean isGameEnded;
     
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.isGameEnded = false;
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
    
    public void setMainGoal(Goal mainGoal) {
        this.mainGoal = mainGoal;
    }
    
    public Goal getMainGoal() {
        return mainGoal;
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
    

    public void removeKey(int ID) {
        entities.removeIf(e -> e instanceof Key && ((Key)e).getKeyID() == ID);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
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
    
     /**
     * Used by floor switches to determine if there is a boulder on top of them.
     *
     * @param x x-coordinate in the dungeon
     * @param y y-coordinate in the dungeon
     * @return reference to colliding boulder.
     */
    public Boulder getCollidingBoulder(int x, int y) {
        for (Entity e: entities) {
            if (e instanceof Boulder && e.getX() == x && e.getY() == y) {
                return (Boulder) e;
            }
        }
        return null;
    }

    @Override
    public void update() {
        // mainGoal is null iff it has not been specified in the json.
        if (mainGoal != null && mainGoal.isComplete()) {
            endGame();
        }
    }
    
    // TODO: do stuff to correctly handle the ending of the game.
    public void endGame() {
        this.isGameEnded = true;
    }
    
    public boolean isGameEnded() {
        return isGameEnded;
    }
    
    public static boolean inSamePosition(Entity a, Entity b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    
    public boolean isPlayerOn(Entity e) {
        return inSamePosition(player, e);
    }
    
    public void tick() {
        for (Entity e: entities) {
            if (e instanceof Tickable) {
                ((Tickable) e).tick();
            }
        }
    }
    
    public int getPlayerPositionX() {
        return player.getX();
    }
    
    public int getPlayerPositionY() {
        return player.getY();
    }
    
}
