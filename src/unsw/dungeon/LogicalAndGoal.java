package unsw.dungeon;

import java.util.ArrayList;

public class LogicalAndGoal extends ConjunctionGoal {

    public LogicalAndGoal() {
        super();
    }

    @Override
    public boolean isComplete() {
        boolean result = true;
        
        for (Goal subGoal : subGoals) {
            result = result && subGoal.isComplete();
        }
        return result;
    }
    
    @Override
    public String toString() {
        String result = "(" + subGoals.get(0).toString();
        for (int i = 1; i < subGoals.size(); i++) {
            result += " AND " + subGoals.get(i);
        }
        result += ")";
        return result;
    }

}