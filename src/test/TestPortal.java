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


@DisplayName("testing portal functionality")

public class TestPortal {
    @Nested
    @DisplayName("testing player collision with a portal")
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
                        .put("type", "portal")
                        .put("id", 0)
                    )
                    .put(new JSONObject()
                        .put("x", 4)
                        .put("y", 4)
                        .put("type", "portal")
                        .put("id", 0)
                    )
                        
                );
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
        }

        @Test
        @DisplayName("check location of player after teleport")
        void testPortalreloc() {
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with portal at (2,2)
            assertEquals(4, player_.getX());
            assertEquals(4, player_.getY());
        }
    
    }
}