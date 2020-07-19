package unsw.dungeon;
import javafx.beans.property.IntegerProperty;
import java.util.ArrayList;
import java.util.List;

public class Portal extends Entity implements Collider {
    private int pID;
    Dungeon dungeon;
    int x;
    int y;

    public Portal(Dungeon dungeon, int x, int y, int pID) {
        super(x,y);
        this.dungeon = dungeon;
        this.pID = pID;
}

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            portalCollision((Player) m);
        }
        
    }
    int getID() {
        return pID;
    }

    void setID(int ID) {
        this.pID = ID;
    }
/**
     * Handles the incidence of a player on a portal. When the player
     * collides with the portal, the player is moved to the correseponding portal
     * somewhere else in the maze 
     * @param p
     */

    private void portalCollision(Player p, Portal pl) {
        Direction playerMoveDirection = p.getDirection();
        Collider collidingEntity;

        collidingEntity = dungeon.getCollidingEntity(getX(), getY());

        //if collided with by player, yeet player to portal with matching ID
        //update the x and y of player to match other portal
        List<Entity> entities = dungeon.getEntities();
        for (Entity e: entities) {
            if (e instanceof Portal) {
                Portal port = (Portal)e;
                if(port.getID() == pl.getID()) {
                    p.setX(port.getX());
                    p.setY(port.getY())
                    //move player to port location

                }
            }
        }

    }
}
