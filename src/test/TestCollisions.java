package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

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
import unsw.dungeon.Wall;

@DisplayName("testing collisions")
class TestCollisions {
    Dungeon d;
    Player player_;
    @Nested
    @DisplayName("testing wall collisions")
    class TestWallCollisions {
        @BeforeEach
        void createDungeon() {
            JSONObject simpleDungeon = new JSONObject()
                .put("width", 5)
                .put("height", 5)
                .put("entities", new JSONArray()
                    .put(new JSONObject()
                        .put("x", 2)
                        .put("y", 2)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 3)
                        .put("y", 2)
                        .put("type", "wall")
                    )
                        
                );
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
        }
        
        @Test
        @DisplayName("collides from above")
        void testCollidesFromAbove() {
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            // Player should not be able to move through the wall at (2,2).
            player_.tryMoveDown(); // Player stays at (2,1)
            assertEquals(2, player_.getX());
            assertEquals(1, player_.getY());
        }

        @Test
        @DisplayName("collides from left")
        void testCollidesFromLeft() {
            // Player at (0,0)
            player_.tryMoveDown(); // Player at (0,1)
            player_.tryMoveDown(); // Player at (0,2)
            player_.tryMoveRight(); // Player at (1,2)
            // Player should not be able to move through the wall at (2,2).
            player_.tryMoveRight(); // Player at (1,2)
            assertEquals(1, player_.getX());
            assertEquals(2, player_.getY());
        }

        @Test
        @DisplayName("collides from below")
        void testCollidesFromBelow() {
            // Player at (0,0)
            player_.tryMoveDown(); // Player at (0,1)
            player_.tryMoveDown(); // Player at (0,2)
            player_.tryMoveDown(); // Player at (0,3)
            player_.tryMoveRight(); // Player at (1,3)
            player_.tryMoveRight(); // Player at (2,3)
            
            // Player should not be able to move through the wall at (2,2).
            player_.tryMoveUp(); // Player at (2,3)
            assertEquals(2, player_.getX());
            assertEquals(3, player_.getY());

            player_.tryMoveRight(); //Player at (3,3)
            player_.tryMoveUp(); // Player at (3,3)
            assertEquals(3, player_.getX());
            assertEquals(3, player_.getY());

        }

        @Test
        @DisplayName("collides from right")
        void testCollidesFromRight() {
            // Player at (0,0)
            player_.tryMoveDown(); // Player at (0,1)
            player_.tryMoveDown(); // Player at (0,2)
            player_.tryMoveDown(); // Player at (0,3)
            player_.tryMoveRight(); // Player at (1,3)
            player_.tryMoveRight(); // Player at (2,3)
            player_.tryMoveRight(); // Player at (3,3)
            player_.tryMoveRight(); // Player at (4,3)
            player_.tryMoveUp();    // Player at (4,2)
            // Player should not be able to move through the wall.
            player_.tryMoveLeft(); // Player stays at (4,2)
            assertEquals(4, player_.getX());
            assertEquals(2, player_.getY());
        }
    }
}