package unsw.dungeon;

import java.util.ArrayList;

public class LogicalOrGoal extends ConjunctionGoal {

    public LogicalOrGoal(ArrayList<Goal> subGoals) {
        super(subGoals);
    }

    @Override
    public boolean isComplete() {
        boolean result = false;
        
        for (Goal subGoal : subGoals) {
            result = result || subGoal.isComplete();
        }
        return result;
    }
    
    @Override
    public String toString() {
        String result = "(" + subGoals.get(0).toString();
        for (int i = 1; i < subGoals.size(); i++) {
            result += " OR " + subGoals.get(i);
        }
        result += ")";
        return result;
    }
}