package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.Player;


@DisplayName("testing key functionality")

public class TestKey {
    @Nested
    @DisplayName("testing player picking up a key")
    class TestWallCollisions {
        Dungeon d;
        Player player;
        
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
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player = new Player(d, 0, 0);
            d.setPlayer(player);
        }

        @Test
        @DisplayName("check player has picked up key")
        void testKeyCollect() {
            // Player at (0,0)
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveRight(); // Player at (2,0)
            player.tryMoveDown(); // Player at (2,1)
            player.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            assert(player.hasKey());

        }
        
        @Test
        @DisplayName("check player cannot move onto door with no key held")
        void testCannotMoveThroughDoorWithNoKey() {
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveDown(); // Player at (1,1)
            player.tryMoveDown(); // Player at (1,2)
            player.tryMoveDown(); // Player at (1,3)
            player.tryMoveRight(); // Player at (2,3)
            
            //Player should not be able to move down onto door unless they hold key 1
            assertEquals(1, player.getX());
            assertEquals(3, player.getY());
        }

        @Test
        @DisplayName("check player can move onto door when correct key held")
        void testCanMoveThroughDoorWithNoKey() {
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveRight(); // Player at (2,0)
            player.tryMoveDown(); // Player at (2,1)
            player.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            player.tryMoveDown(); // Player at (2,3)
            //Player should not be able to move down onto door unless they hold key 1
            assertEquals(2, player.getX());
            assertEquals(3, player.getY());
        
        }
        
        @Test
        @DisplayName("check player can move onto door after it is unlocked")
        void testCanMoveThroughDoorAfterUnlocked() {
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveRight(); // Player at (2,0)
            player.tryMoveDown(); // Player at (2,1)
            player.tryMoveDown(); // Player at (2,2)
            // Player should have collided with key at (2,2)
            player.tryMoveDown(); // Player at (2,3) (through the door)
            player.tryMoveLeft(); // Player at (2,2) again
            player.tryMoveRight(); // Player back at (2,3) on the door
            assertEquals(2, player.getX());
            assertEquals(3, player.getY());
            player.tryMoveUp();
            player.tryMoveDown();
            assertEquals(2, player.getX());
            assertEquals(3, player.getY());
        }
    }
}