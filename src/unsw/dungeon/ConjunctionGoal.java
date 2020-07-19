package unsw.dungeon;

import java.util.ArrayList;

public abstract class ConjunctionGoal implements Goal {
    
    protected ArrayList<Goal> subGoals;
    public ConjunctionGoal() {
        this.subGoals = new ArrayList<Goal>();
    }
    
    public void addSubGoal(Goal subGoal) {
        subGoals.add(subGoal);
    }
}