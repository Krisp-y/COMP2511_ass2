package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.Enemy;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;

@DisplayName("testing goals")
class TestEnemyMovement {
    Dungeon d;
    Player p;
    Enemy e;
    
    private int getDistance(Player p, Enemy e) {
        return Math.abs(p.getX() - e.getX()) + Math.abs(p.getY() - e.getY());
    }
    
    @Test
    @DisplayName("testing enemy moves towards player in simple cases")
    void TestEnemyMovesTowardsPlayerSimple() {
    
        JSONObject json = new JSONObject()
            .put("width", 4)
            .put("height", 4)
            .put("entities", new JSONArray()
            
            );
        DungeonLoader dl = new DungeonTestLoader(json);
        d = dl.load();
        p = new Player(d, 0, 0);
        e = new Enemy(d, 2, 2);
        d.setPlayer(p);
        d.addEntity(e);
        
        int originalDistance = getDistance(p, e);
        assertEquals(4, originalDistance); // Make sure the getdistance function is working.
        
        p.tryMoveDown(); //Player moves down to (0,1)
        d.tick();
        // When the player moves down the distance should shrink between enemy and player
        assertTrue(originalDistance >= getDistance(p, e)); // Enemy should be at (2,1)
        originalDistance = getDistance(p, e);
        
        p.tryMoveRight();
        d.tick();
        assertTrue(originalDistance >= getDistance(p, e)); // Player and enemy at (1,1)
        assertEquals(0, getDistance(p, e));
    }
    
    @Test
    @DisplayName("testing enemy moves towards player in simple cases")
    void TestEnemyMovesTowardsPlayerWithWalls() {
        JSONObject json = new JSONObject()
            .put("width", 4)
            .put("height", 4)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 2)
                    .put("y", 2)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 1)
                    .put("y", 2)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 2)
                    .put("y", 1)
                )
            );
        System.out.println(json);
        DungeonLoader dl = new DungeonTestLoader(json);
        d = dl.load();
        p = new Player(d, 0, 0);
        e = new Enemy(d, 3, 3);
        d.setPlayer(p);
        d.addEntity(e);
        
        int originalDistance = getDistance(p, e);
        assertEquals(6, originalDistance);
        
        // When not blocked by walls, the distance between the player and the enemy should never grow.
        
        p.tryMoveDown(); //Player moves down to (0,1)
        d.tick();
        assertTrue(originalDistance >= getDistance(p, e)); // Enemy should be at (2,1)
        originalDistance = getDistance(p, e);
        
        p.tryMoveRight();
        d.tick();
        assertTrue(originalDistance >= getDistance(p, e));
        originalDistance = getDistance(p, e);
        
        // When the enemy is blocked by a wall, the distance should grow by a factor no greater than one,
        // In this case the enemy is blocked and the player moves, but the enemy does not turn around or go the
        // wrong direction.
        p.tryMoveRight();
        d.tick();
        assertTrue(originalDistance + 1 >= getDistance(p, e));
        originalDistance = getDistance(p, e);
             
        p.tryMoveUp();
        d.tick();
        assertTrue(originalDistance >= getDistance(p, e));
        originalDistance = getDistance(p, e);
        
        p.tryMoveRight();
        d.tick();
        assertTrue(originalDistance >= getDistance(p, e));
        originalDistance = getDistance(p, e);
    }
}