package unsw.dungeon;

import java.util.ArrayList;

public abstract class BasicGoal implements Goal {
    
    protected ArrayList<Entity> entities;
    public BasicGoal(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public abstract void tryComplete();
}