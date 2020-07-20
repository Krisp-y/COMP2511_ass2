package test;

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
    class TestTreasure {
        Dungeon d;
        Player player_;
        
        @BeforeEach
        void createDungeon() {
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
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
        }
        
        @Test
        @DisplayName("player should be able to collect treasure")
        void testCollidesFromAbove() {
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            //player should collect treasure here
            player_.tryMoveDown(); // Player at (2,1)
            assertEquals(1,player_.countTreasure());
            player_.tryMoveRight(); // Player at (3,1)
            player_.tryMoveDown(); // Player at (2,1)
            assertEquals(2,player_.countTreasure());
        }
}
