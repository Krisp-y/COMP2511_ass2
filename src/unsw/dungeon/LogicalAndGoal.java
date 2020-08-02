package unsw.dungeon;
public class LogicalAndGoal extends ConjunctionGoal {

    public LogicalAndGoal() {
        super();
    }

    @Override
    public boolean isComplete() {
        
        ExitGoal eg = null;
        boolean result = true;
        for (Goal subGoal : subGoals) {
            result = subGoal.isComplete() && result;
            if (subGoal instanceof ExitGoal) {
                eg = (ExitGoal) subGoal;
            }
        }
        
        // Weird edge case we get with exit tile, if the AND fails we should tell it to
        // switch off the completion view.
        if (eg != null && !result) {
            eg.setComplete(false);
        }
       
        gvListener.update(result);
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