package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;

@DisplayName("testing goals")
class TestEnemyCollisions {
    Dungeon d;
    Player p;
    Enemy e;
    
    
    @Nested
    @DisplayName("testing cases where an enemy dies from a player collision")
    class TestEnemyDiesFromPlayerCollision {
    
        @Test
        @DisplayName("testing cases where an enemy dies from a player collision when player has a potion")
        void TestEnemyDiesFromPlayerCollisionWithPotion() {
            JSONObject json = new JSONObject()
            .put("width", 10)
            .put("height", 10)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                    .put("type", "potion")
                    .put("x", 3)
                    .put("y", 3)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 5)
                    .put("y", 5)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 5)
                    .put("y", 6)
                )
                .put(new JSONObject()
                    .put("type", "weapon")
                    .put("x", 8)
                    .put("y", 8)
                )
            );
            DungeonLoader dl = new DungeonTestLoader(json);
            d = dl.load();
            p = new Player(d, 0, 0);
            e = new Enemy(d, 9, 9);
            d.setPlayer(p);
            d.addEntity(e);
            d.addEntity(p);
            
            assertTrue(e.isAlive());
            for (int i = 0; i < 3; i++) { // Player collects the potion at (3,3)
                p.tryMoveRight();
                d.tick();
                p.tryMoveDown();
                d.tick();
            }
            assertTrue(p.isInvincible());
            
            for (int i = 0; i < 7; i++) {
                p.tryMoveRight();
                d.tick();
            }
            for (int i = 0; i < 7; i++) { // Player moves towards hiding enemy.
                p.tryMoveDown();
                d.tick();
            }
            // Entity is dead after this collision.
            assertFalse(e.isAlive());
        }
        
        @Test
        @DisplayName("testing cases where an enemy dies from a player collision when player has a weapon")
        void TestEnemyDiesFromPlayerCollisionWithWeapon() {

            JSONObject json = new JSONObject()
            .put("width", 10)
            .put("height", 10)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                    .put("type", "potion")
                    .put("x", 3)
                    .put("y", 3)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 5)
                    .put("y", 5)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 5)
                    .put("y", 6)
                )
                .put(new JSONObject()
                    .put("type", "weapon")
                    .put("x", 1)
                    .put("y", 1)
                )
            );
            DungeonLoader dl = new DungeonTestLoader(json);
            d = dl.load();
            p = new Player(d, 0, 0);
            e = new Enemy(d, 9, 9);
            d.setPlayer(p);
            d.addEntity(e);
            d.addEntity(p);
            
            assertTrue(e.isAlive()); // e is alive
            assertFalse(p.hasWeapon());
            
            p.tryMoveRight();
            d.tick();
            p.tryMoveDown();
            d.tick(); // Player now has the sword
            assertTrue(p.hasWeapon());
            
            for (int i = 0; i < 7; i++) { // After 9 Steps down should collide with enemy
                p.tryMoveDown();
                d.tick();
            }
            
            assertEquals(4, p.getWeaponHealth());
          
            assertFalse(e.isAlive()); 
            
        }
        
        @Test
        @DisplayName("testing cases where multiple enemies die from a player collision")
        void TestMultipleEnemiesDieFromPlayerCollisionWithWeapon() {
            JSONObject json = new JSONObject()
            .put("width", 30)
            .put("height", 30)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                    .put("type", "weapon")
                    .put("x", 1)
                    .put("y", 1)
                )
            );
            DungeonLoader dl = new DungeonTestLoader(json);
            d = dl.load();
            p = new Player(d, 0, 0);
            Enemy[] enemies = {new Enemy(d, 3, 3), new Enemy(d, 4,4), new Enemy(d, 5,5), new Enemy(d, 6,6), new Enemy(d, 7, 7)};
            for (Enemy enemy : enemies) {
                d.addEntity(enemy);
            }
            
            d.setPlayer(p);
            d.addEntity(p);
            
            for (Enemy e : enemies) {
                assertTrue(e != null);
            }
            
            assertFalse(p.hasWeapon());
            p.tryMoveRight();
            d.tick();
            p.tryMoveDown();
            d.tick(); // Player now has the sword
            assertTrue(p.hasWeapon());
            
            
            p.tryMoveRight(); // Player collides with first enemy
            d.tick();
            p.tryMoveDown();
            d.tick();
            assertFalse(enemies[0].isAlive()); 
            
            for (int i = 0; i < 5; i++) {
                p.tryMoveRight(); // Player moves in circles to kill other enemies
                d.tick();
                p.tryMoveDown();
                d.tick();
                p.tryMoveUp();
                d.tick();
                p.tryMoveLeft();
                d.tick();
            }
            
            assertTrue(p.getWeaponHealth() <= 0);
            assertFalse(p.hasWeapon());
            assertFalse(enemies[0].isAlive());
            assertFalse(enemies[1].isAlive()); 
            assertFalse(enemies[2].isAlive()); 
            assertFalse(enemies[3].isAlive()); 
            assertFalse(enemies[4].isAlive()); 
            
    
        }
    
        @Test
        @DisplayName("testing cases where player dies upon enemy collision")
        void TestPlayerDiesWithEnemyCollision() {
            JSONObject json = new JSONObject()
            .put("width", 10)
            .put("height", 10)
            .put("entities", new JSONArray()
               
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 5)
                    .put("y", 6)
                )
                .put(new JSONObject()
                    .put("type", "weapon")
                    .put("x", 8)
                    .put("y", 8)
                )
            );
            DungeonLoader dl = new DungeonTestLoader(json);
            d = dl.load();
            p = new Player(d, 0, 0);
            e = new Enemy(d, 9, 9);
            d.setPlayer(p);
            d.addEntity(e);
            d.addEntity(p);
            
            assertTrue(p.isAlive());
            assertTrue(e.isAlive());
            
            for (int i = 0; i < 5; i++) { // Player collides with enemy
                p.tryMoveRight();
                d.tick();
                p.tryMoveDown();
                d.tick();
            }

            assertTrue(d.isGameEnded()); // Player is dead so game is done
            
        }
    
    } 
}