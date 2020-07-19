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

    @Override
    public void update() {
        if (mainGoal.isComplete()) {
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
}
