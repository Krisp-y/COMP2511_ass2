package unsw.dungeon;

import java.util.List;

public class TreasureGoal extends BasicGoal {
    
    private int numTreasures;
    private int numCollected;
    public TreasureGoal(Dungeon dungeon, List<Entity> treasures) {
        super(dungeon, treasures);
        this.numTreasures = treasures.size();
        this.numCollected = 0;
    }

    @Override
    public boolean isComplete() {
        boolean result = numCollected >= numTreasures;
        System.out.println(gvListener + "is getting updated by" + this);
        gvListener.update(result);
        return result;
    }
    
    @Override
    public String toString() {
        return "treasure";
    }

    @Override
    public void goalUpdate() {
        numCollected++; //update is called when the player picks up treasure.
        notifySubscribers();
    }
    
    public int getTreasureCount() {
        return numTreasures;
    }
    
    public int getCollectedTreasureCount() {
        return numCollected;
    }
    
}