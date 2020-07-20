package unsw.dungeon;

import java.util.List;

public class BoulderGoal extends BasicGoal {
    
    private List<Entity> floorSwitches;
    private List<Entity> boulders;
    
    public BoulderGoal(Dungeon dungeon, List<Entity> floorSwitches, List<Entity> boulders) {
        super(dungeon, floorSwitches);
        this.floorSwitches = floorSwitches;
        this.boulders = boulders;
    }

    @Override
    public boolean isComplete() {
        return goalSatisfied();
    }
    
    @Override
    public String toString() {
        return "boulders";
    }
    
    private boolean goalSatisfied() {
        boolean foundBoulder = true;
        
        for (Entity floorSwitch : floorSwitches) {
            foundBoulder = false;
            for (Entity boulder : boulders) {
                if (Dungeon.inSamePosition(floorSwitch, boulder)) {
                    foundBoulder = true;
                    break;
                }
            }
            if (!foundBoulder) {
                return false;
            }
        }
        return foundBoulder;
    }

    @Override
    public void update() {
        // Notify the dungeon that the state has changed.
        notifySubscribers();
    }

}