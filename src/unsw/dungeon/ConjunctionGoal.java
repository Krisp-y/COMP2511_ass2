package unsw.dungeon;

import java.util.ArrayList;

public abstract class ConjunctionGoal implements Goal {
    protected GoalView gvListener;
    protected ArrayList<Goal> subGoals;
    public ConjunctionGoal() {
        this.subGoals = new ArrayList<Goal>();
    }
    
    public void addSubGoal(Goal subGoal) {
        subGoals.add(subGoal);
    }
    
    public ArrayList<Goal> getSubGoals() {
        return subGoals;
    }
    
    public Goal getGoal() {
        return this;
    }
    
    public void addListener(GoalView gv) {
        gvListener = gv;
    }
    
    public void updateListener(boolean result) {
        if (gvListener != null) {
            gvListener.update(result);
        }
        
    }
}