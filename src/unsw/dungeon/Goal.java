package unsw.dungeon;

public interface Goal {
    public boolean isComplete();
    
    public void addListener(GoalView gv);
}