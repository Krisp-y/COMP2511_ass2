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
        return numDead >= numEnemies;
    }
    
    @Override
    public String toString() {
        return "enemies";
    }

    @Override
    public void update() {
        numDead++; // update is called when an enemy is killed;
        notifySubscribers();
    }
}