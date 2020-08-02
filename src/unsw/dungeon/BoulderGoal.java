package unsw.dungeon;

import java.util.List;

public class BoulderGoal extends BasicGoal {
    
    private List<Entity> floorSwitches;
    private List<Entity> boulders;
    private int switchesTriggered;
    
    public BoulderGoal(Dungeon dungeon, List<Entity> floorSwitches, List<Entity> boulders) {
        super(dungeon, floorSwitches);
        this.floorSwitches = floorSwitches;
        this.boulders = boulders;
        this.switchesTriggered = 0;
        countTriggeredSwitches();
    }

    @Override
    public boolean isComplete() {
        return goalSatisfied();
    }
    
    @Override
    public String toString() {
        return "boulders";
    }
    
    private void countTriggeredSwitches() {
        
        switchesTriggered = 0;
        for (Entity floorSwitch : floorSwitches) {
            for (Entity boulder : boulders) {
                if (Dungeon.inSamePosition(floorSwitch, boulder)) {
                    switchesTriggered++;
                }
            }
        }
    }
    
    private boolean goalSatisfied() {
        countTriggeredSwitches();
        boolean result = switchesTriggered >= floorSwitches.size();
        gvListener.update(result);
        return result;
    }

    @Override
    public void goalUpdate() {
        // Notify the dungeon that the state has changed.
        notifySubscribers();
    }
    
    public int getNumFloorSwitches() {
        return floorSwitches.size();
    }
    
    public int getSwitchesTriggered() {
        return switchesTriggered;
    }

}