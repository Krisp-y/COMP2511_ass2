package unsw.dungeon;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Moveable implements Tickable, Collider {
    private List<Entity> inventory;
    private boolean alive;
    private boolean isTeleporting;
    private DungeonController dc;
    private 
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.inventory = new CopyOnWriteArrayList<>();
        this.alive = true;
    }   

    public void teleport(int x, int y) {
        x().set(x);
        y().set(y);
    }

    public List<Entity> getInventory() {
        return this.inventory;
    }
    
    /** Moves an item from the dungeon entity list to the player inventory. */
    public void collectItem(Entity e) {
        inventory.add(e);
        if (dc != null) {
            dc.addToInventoryView(e);
        }
        dungeon.removeEntity(e);
    }

    public void removeCollectable(Entity e) {
        inventory.remove(e);
        if (dc != null) {
            dc.removeFromInventoryView(e);
        }
    }

    public boolean hasKey() {
        for (Entity e: inventory) {
            if (e instanceof Key) {
                return true;
            }
        }
        return false;
    }
    
    public int getKeyID() {
        for (Entity e: inventory) {
            if (e instanceof Key) {
                Key key = (Key)e;
                return key.getKeyID();
            }
        }
        return -1;
    }

    public void useKey() {
        for (Entity e : inventory) {
            if (e instanceof Key) {
                inventory.remove(e);
                if (dc != null) {
                    dc.removeFromInventoryView(e);
                }
            }
        }
    }

    public int countTreasure() {
        int count = 0;
        for(Entity e: inventory ) {
            if (e instanceof Treasure) {
                count++;
            }
        }
        return count;
    }
    
    public void addPotion(Potion potion) {
        
        // If we have just added a potion, make the player invincible and make enemies retreat
        if (!isInvincible()) {
            dungeon.setEnemiesToRetreat();
        }
        // System.out.println("trying to add potion");
        inventory.add(potion);
        if (dc != null) {
            dc.addToInventoryView(potion);
            dc.showInvincibleStatus(true);
        }
        dungeon.removeEntity(potion);
    }   
    
    public void removePotion(Potion potion) {
        
        inventory.remove(potion);
        if (dc != null) {
            dc.removeFromInventoryView(potion);
        }
        
        // If we have removed a potion and we have none left, make the enemies attack
        if (!isInvincible()) {
            if (dc != null) { // Only show
                dc.showInvincibleStatus(false);
            }
            dungeon.setEnemiesToAttack();
        }
    }
    
    public void updatePotionHealth(Potion potion) {
        if (dc != null) {
            dc.updatePotionHealth(potion.getHealth());
        }
    }
    
    public int getPotionHealth(Potion potion) {
        return potion.getHealth();
    }
    
    public boolean isInvincible() {
        for (Entity e: inventory) {
            if (e instanceof Potion) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasWeapon() {
        for (Entity e: inventory) {
            if (e instanceof Weapon) {
                return true;
            }
        }
        return false;
    }
    
    public void addWeapon(Weapon w) {
        inventory.add(w);
        if (dc != null) {
            dc.showWeaponStatus(true);
            dc.addToInventoryView(w);
        }
        dungeon.removeEntity(w);
    }
    
    public void removeWeapon() {
        for (Entity e : inventory) {
            if (e instanceof Weapon) {
                inventory.remove(e);
                if (dc != null) {
                    dc.showWeaponStatus(false);
                    dc.removeFromInventoryView(e);
                }
            }
        }
    }
    
    public void reduceWeaponHealth() {
        
        for (Entity e: inventory) {
            if (e instanceof Weapon) {
                Weapon w = (Weapon) e;
                w.decrementHealth();
                if (dc != null) {
                    dc.reduceWeaponHealth();
                }
            }
        }
    }
    
    public int getWeaponHealth() {
        for (Entity e: inventory) {
            if (e instanceof Weapon) {
                return ((Weapon) e).getHealth();
            }
        }
        return -1;
    }

    @Override
    public void tick() {
        for (Entity e: inventory) {
            if (e instanceof Tickable) {
                ((Tickable) e).tick();
            }
        }
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Enemy) {
            Enemy enemy = (Enemy) m;
            enemy.move(enemy.getDirection());
            enemy.playerCollision(this); // handle the player collision on the enemy side.
        }

        if (m instanceof FireBall) {
            FireBall fireBall = (FireBall) m;
            fireBall.move(fireBall.getDirection());
            fireBall.FBplayerCollision(this); // handle the player collision on the enemy side.
        }
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public boolean isTeleporting() {
        return isTeleporting;
    }
    
    public void setTeleporting(boolean isTeleporting) {
        this.isTeleporting = isTeleporting;
    }
    
    public void subscribeController(DungeonController dc) {
        this.dc = dc;
    }

    public void dropMine() {

    }
}
