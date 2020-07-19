package unsw.dungeon;

import java.util.ArrayList;

public abstract class ConjunctionGoal implements Goal {
    
    protected ArrayList<Goal> subGoals;
    public ConjunctionGoal(ArrayList<Goal> subGoals) {
        this.subGoals = subGoals;
    }
}