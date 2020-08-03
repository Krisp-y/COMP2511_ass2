package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
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
    @DisplayName("testing player collision with a single portal")
    class TestSinglePortalCollisions {
        Dungeon d;
        Player player_;
        JSONObject simpleDungeon = new JSONObject()
                .put("width", 8)
                .put("height", 8)
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
        @Test
        @DisplayName("check location of player after teleport in map with one portal")
        void testPortalTelportationSinglePortal() {
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with portal at (2,2) and teleported to (4,4)
            assertEquals(4, player_.getX());
            assertEquals(4, player_.getY());
        }
        
        @Test
        @DisplayName("portals can be used more than once")
        void testPortalTelportationSinglePortalReuse() {
            
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
            // Player at (0,0)
            player_.tryMoveRight(); // Player at (1,0)
            player_.tryMoveRight(); // Player at (2,0)
            player_.tryMoveDown(); // Player at (2,1)
            player_.tryMoveDown(); // Player at (2,2)
            // Player should have collided with portal at (2,2) and teleported to (4,4)
            player_.tryMoveRight();
            player_.tryMoveLeft(); // Move off the portal and back onto the portal at (4,4)
            assertEquals(2, player_.getX());
            assertEquals(2, player_.getY()); // Player teleports back
        }
    }
    
    @Nested
    @DisplayName("testing player collision with a multiple portals")
    class TestMultiplePortalCollisions {
        Dungeon d;
        Player player;
        JSONObject simpleDungeon = new JSONObject()
                .put("width", 8)
                .put("height", 8)
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
                    .put(new JSONObject()
                        .put("x", 1)
                        .put("y", 1)
                        .put("type", "portal")
                        .put("id", 1)
                    )
                    .put(new JSONObject()
                        .put("x", 5)
                        .put("y", 5)
                        .put("type", "portal")
                        .put("id", 1)
                    )  
                );
  
        
        @Test
        @DisplayName("test players teleported correctly with two portals")
        void testPortalTeleportationTwoPortals() {
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player = new Player(d, 0, 0);
            d.setPlayer(player);
            
            // Player at (0,0)
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveDown(); // Player at (1,1)
            // Player should have collided with portal at (1,1) and teleported to (5,5);
            assertEquals(5, player.getX());
            assertEquals(5, player.getY());
            
        }
        
        @Test
        @DisplayName("test players can use multiple portals more than once")
        void testPortalTeleportationTwoPortalsMultipleUse() {
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d = dl.load();
            // Put player in the top left corner
            player = new Player(d, 0, 0);
            d.setPlayer(player);
            
            // Player at (0,0)
            player.tryMoveRight(); // Player at (1,0)
            player.tryMoveDown(); // Player at (1,1)
            // Player should have collided with portal at (1,1) and teleported to (5,5);
            player.tryMoveLeft(); // Player should have collided with (4,4) 
            player.tryMoveUp(); // and teleported back to (2,2)
            
            assertEquals(2, player.getX());
            assertEquals(2, player.getY());
            
        }
    }
}