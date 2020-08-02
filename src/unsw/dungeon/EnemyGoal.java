package unsw.dungeon;

import java.util.List;

public class EnemyGoal extends BasicGoal {
    
    private int numEnemies;
    private int numDead;
    
    public EnemyGoal(Dungeon dungeon, List<Entity> enemies) {
        super(dungeon, enemies);
        this.numDead = 0;
        this.numEnemies = enemies.size();
    }

    @Override
    public boolean isComplete() {
        boolean result = numDead >= numEnemies;
        gvListener.update(result);
        return result;
    }
    
    @Override
    public String toString() {
        return "enemies";
    }

    @Override
    public void goalUpdate() {
        numDead++; // update is called when an enemy is killed;
        notifySubscribers();
    }
    
    public int getEnemyCount() {
        return numEnemies;
    }
    
    public int getDeadEnemyCount() {
        return numDead;
    }
}