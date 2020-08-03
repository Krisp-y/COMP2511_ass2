package unsw.dungeon;

public class Wall extends Entity implements Collider {

    public Wall(int x, int y) {
        super(x, y);
    }

    /**
    * Wall collisions do nothing
    */
    @Override
    public void handleCollision(Moveable m) {
        return;
    }    
}
