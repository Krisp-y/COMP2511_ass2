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
        return numTreasures == numCollected;
    }
    
    @Override
    public String toString() {
        return "treasure";
    }

    @Override
    public void update() {
        numCollected++; //update is called when the player picks up treasure.
        notifySubscribers();
    }
    
}