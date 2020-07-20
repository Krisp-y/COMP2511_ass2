package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

//import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
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
import unsw.dungeon.Key;
import unsw.dungeon.Entity;


@DisplayName("testing key functionality")

public class TestKey {
    @Nested
    @DisplayName("testing player picking up a key")
    class TestWallCollisions {
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
                        .put("y", 2)
                        .put("type", "key")
                        .put("id", 1)
                    )
                    .put(new JSONObject()
                        .put("x", 2)
                        .put("y", 3)
                        .put("type", "door")
                        .put("id", 1)
                    )
                        
                );
            System.out.println(simpleDungeon);
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
        }

        @Test
        @DisplayName("check player has picked up key")
        void testKeyCollect() {
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            assert(player_.hasKey());

        }

        @Test
        @DisplayName("check players key has the correct ID")
        void testKeyOpen() {
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            assertTrue(player_.hasKey());
            assertEquals("1", String.valueOf(player_.getKeyID()));

        }

        @Test
        @DisplayName("check player can move onto door when correct key held")
        
        void testKeyID() {
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            player_.tryMoveDown(); // Player at (2,3)
            //Player should not be able to move down onto door unless they hold key 1
            assertEquals(2, player_.getX());
            assertEquals(3, player_.getY());
        }
    }

    
    
}