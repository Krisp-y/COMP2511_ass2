package unsw.dungeon;

public interface Collider {   
    
    /**
     * Classes implement collider which means that they have some event trigger
     * when moveable entities interact with them. Classes implementing collider
     * must implement "handleCollision" which governs how they interact
     * with a moveable object m.
     * @param m
     */
    public void handleCollision(Moveable m);

}