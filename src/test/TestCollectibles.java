package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;

@Nested
@DisplayName("testing player collects treasure")
class TestCollectibles {
    Dungeon d;
    Player player;
    
    @Test
    @DisplayName("player should be able to collect treasure")
    void testMultipleTreasureCollection() {
        
        JSONObject simpleDungeon = new JSONObject()
            .put("width", 5)
            .put("height", 5)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                .put("x", 2)
                .put("y", 1)
                .put("type", "treasure")
            )
            .put(new JSONObject()
                .put("x", 3)
                .put("y", 2)
                .put("type", "treasure")
            )
        );
        DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
        d = dl.load();
        // Put player in the top left corner
        player = new Player(d, 0, 0);
        d.setPlayer(player);
        
        // Player at (0,0)
        player.tryMoveRight(); // Player at (1,0)
        player.tryMoveRight(); // Player at (2,0)
        // player should collect treasure here
        player.tryMoveDown(); // Player at (2,1)
        assertEquals(1, player.countTreasure());
        player.tryMoveRight(); // Player at (3,1)
        player.tryMoveDown(); // Player at (2,1)
        assertEquals(2, player.countTreasure());
    }
    
    @Test
    @DisplayName("player should be able to collect potion")
    void testPotionCollection() {
        JSONObject json = new JSONObject()
            .put("width", 10)
            .put("height", 10)
            .put("entities", new JSONArray()
                .put(new JSONObject()
                    .put("type", "potion")
                    .put("x", 2)
                    .put("y", 2)
                )
                .put(new JSONObject()
                    .put("type", "wall")
                    .put("x", 9)
                    .put("y", 2)
                )
            );
        DungeonLoader dl = new DungeonTestLoader(json);
        d = dl.load();
        player = new Player(d, 0, 0);
        d.setPlayer(player);
        player.tryMoveDown(); //Player moves down to (0,1)
        d.tick();
        player.tryMoveDown();
        d.tick();
        player.tryMoveRight();
        d.tick();
        player.tryMoveRight();
        d.tick();
        
        // Player has picked up the potion
        assertTrue(player.isInvincible());
        for (int i = 0; i < 15; i++) {
            player.tryMoveRight(); // Player will try and move right 15 times, wearing off the potion.
        }
        // Make sure the player is no longer invincible.
        assertFalse(player.isInvincible());
        for (int i = 0; i < 9; i++) {
            player.tryMoveLeft(); // Player will try and move left 9 times, back over the potion.
        }
        
        assertFalse(player.isInvincible());
    }
}
