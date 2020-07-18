package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;

@DisplayName("testing collisions")
class TestCollisions {
    
    @Nested
    @DisplayName("testing player wall collisions")
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
        @DisplayName("player can't move through walls from above")
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
        @DisplayName("player can't move through walls from left")
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
        @DisplayName("player can't move through walls from below")
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
        @DisplayName("player can't move through walls from right")
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
    
    @Nested
    @DisplayName("testing player boulder collisions")
    class TestBoulderCollisions {
        Dungeon d_;
        Player player_;
        @BeforeEach
        void createDungeon() {
            JSONObject simpleDungeon = new JSONObject()
                .put("width", 7)
                .put("height", 7)
                .put("entities", new JSONArray()
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 1)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 2)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 3)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 4)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 5)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 0)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 1)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 2)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 3)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 4)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 5)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 0)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 1)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 2)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 3)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 4)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 5)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 6)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 5)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 4)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 3)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 2)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                    .put(new JSONObject()
                        .put("x", 1)
                        .put("y", 6)
                        .put("type", "wall")
                    )
                );
            DungeonLoader dl = new DungeonTestLoader(simpleDungeon);
            d_ = dl.load();
        }
        
        @Nested
        @DisplayName("testing boulder collisions into free space")
        class TestBoulderCollisionIntoFreeSpace {
            
            Boulder boulder;
            
            @BeforeEach
            void addBoulders() {
                // Manually add the middle boulder so we can track it's position
                boulder = new Boulder(d_, 2, 2);
                d_.addEntity(boulder);
            }
            
            @Test
            @DisplayName("can move boulders from below into free space")
            void testBouldersCollideFromBelowIntoFreeSpace() {
                player_ = new Player(d_, 2, 3);
                d_.setPlayer(player_);
                player_.tryMoveUp();
                
                assertEquals(2, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(2, boulder.getX());
                assertEquals(1, boulder.getY());
            }
    
            @Test
            @DisplayName("can move boulders from above into free space")
            void testBouldersCollideFromAboveIntoFreeSpace() {
                player_ = new Player(d_, 2, 1);
                d_.setPlayer(player_);
                player_.tryMoveDown();
                
                assertEquals(2, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(2, boulder.getX());
                assertEquals(3, boulder.getY());
            }
            
            @Test
            @DisplayName("can move boulders from left into free space")
            void testBouldersCollideFromLeftIntoFreeSpace() {
                player_ = new Player(d_, 1, 2);
                d_.setPlayer(player_);
                player_.tryMoveRight();
                
                assertEquals(2, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(2, boulder.getY());
            }
            
            @Test
            @DisplayName("can move boulders from above into free space")
            void testBouldersCollideFromRightIntoFreeSpace() {
                player_ = new Player(d_, 3, 2);
                d_.setPlayer(player_);
                player_.tryMoveLeft();
                
                assertEquals(2, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(1, boulder.getX());
                assertEquals(2, boulder.getY());
            }
        }
        
        @Nested
        @DisplayName("testing boulder collisions into walls")
        class TestBoulderCollisionIntoWalls {            
            Boulder boulder;
            @BeforeEach
            void addBoulders() {
                // Manually add the middle boulder so we can track it's position
                boulder = new Boulder(d_, 3, 3);
                d_.addEntity(boulder);
                
            }
            
            @Test
            @DisplayName("can't move boulders from below into a wall")
            void testBouldersCollideFromBelowIntoWalls() {
                player_ = new Player(d_, 3, 4);
                d_.setPlayer(player_);
                
                // Move boulder and player up one square
                player_.tryMoveUp();
                assertEquals(3, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(2, boulder.getY());
                
                // Move boulder and player up one more square
                player_.tryMoveUp();
                assertEquals(3, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(1, boulder.getY());
                
                // Try Move boulder and player up one more square into wall.
                // Should be no change in position.
                player_.tryMoveUp();
                assertEquals(3, player_.getX());
                assertEquals(2, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(1, boulder.getY());
                
            }
    
            @Test
            @DisplayName("can't move boulders from above into a wall")
            void testBouldersCollideFromAboveIntoWalls() {
                player_ = new Player(d_, 3, 2);
                d_.setPlayer(player_);
                
                // Move boulder and player up one square
                player_.tryMoveDown();
                assertEquals(3, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(4, boulder.getY());
                
                // Move boulder and player up one more square
                player_.tryMoveDown();
                assertEquals(3, player_.getX());
                assertEquals(4, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(5, boulder.getY());
                
                // Try Move boulder and player up one more square into wall.
                // Should be no change in position.
                player_.tryMoveDown();
                assertEquals(3, player_.getX());
                assertEquals(4, player_.getY());
                assertEquals(3, boulder.getX());
                assertEquals(5, boulder.getY());
            }
            
            @Test
            @DisplayName("can't move boulders from left into a wall")
            void testBouldersCollideFromLeftIntoWalls() {
                player_ = new Player(d_, 2, 3);
                d_.setPlayer(player_);
                
                // Move boulder and player up one square
                player_.tryMoveRight();
                assertEquals(3, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(4, boulder.getX());
                assertEquals(3, boulder.getY());
                
                // Move boulder and player up one more square
                player_.tryMoveRight();
                assertEquals(4, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(5, boulder.getX());
                assertEquals(3, boulder.getY());
                
                // Try Move boulder and player up one more square into wall.
                // Should be no change in position.
                player_.tryMoveRight();
                assertEquals(4, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(5, boulder.getX());
                assertEquals(3, boulder.getY());
            }
            
            @Test
            @DisplayName("can't move boulders from right into a wall")
            void testBouldersCollideFromRightIntoWalls() {
                player_ = new Player(d_, 4, 3);
                d_.setPlayer(player_);
                
                // Move boulder and player left one square
                player_.tryMoveLeft();
                assertEquals(3, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(2, boulder.getX());
                assertEquals(3, boulder.getY());
                
                // Move boulder and player left one more square
                player_.tryMoveLeft();
                assertEquals(2, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(1, boulder.getX());
                assertEquals(3, boulder.getY());
                
                // Try Move boulder and player left one more square into wall.
                // Should be no change in position.
                player_.tryMoveLeft();
                assertEquals(2, player_.getX());
                assertEquals(3, player_.getY());
                assertEquals(1, boulder.getX());
                assertEquals(3, boulder.getY());
            }
        }
        
        @Nested
        @DisplayName("testing boulder collisions with each other other")
        class TestBoulderCollisionsIntoBoulders {
            
            Boulder middle_boulder;
            Boulder left_boulder;
            Boulder right_boulder;
            Boulder up_boulder;
            Boulder down_boulder;
            
            @BeforeEach
            void addBoulders() {
                // Manually add all the boulders so we can track their position
                middle_boulder = new Boulder(d_, 3, 3);
                left_boulder = new Boulder(d_, 2, 3);
                right_boulder = new Boulder(d_, 4, 3);
                down_boulder = new Boulder(d_, 3, 4);
                up_boulder = new Boulder(d_, 3, 2);
                d_.addEntity(middle_boulder);
                d_.addEntity(left_boulder);
                d_.addEntity(right_boulder);
                d_.addEntity(up_boulder);
                d_.addEntity(down_boulder);
            }
            
            @Test
            @DisplayName("can't move boulders from below into other boulders")
            void testBouldersCollideFromBelowIntoBoulder() {
                player_ = new Player(d_, 3, 5);
                d_.setPlayer(player_);
                
                // Try to move player and boulder 
                player_.tryMoveUp();
                
                // Player doesn't move.
                assertEquals(3, player_.getX());
                assertEquals(5, player_.getY());
                
                // All the boulders don't move.
                assertEquals(3, down_boulder.getX());
                assertEquals(4, down_boulder.getY());
                assertEquals(3, middle_boulder.getX());
                assertEquals(3, middle_boulder.getY());
                assertEquals(3, up_boulder.getX());
                assertEquals(2, up_boulder.getY());
                assertEquals(4, right_boulder.getX());
                assertEquals(3, right_boulder.getY());
                assertEquals(2, left_boulder.getX());
                assertEquals(3, right_boulder.getY());
            }
    
            @Test
            @DisplayName("can't move boulders from above into other boulders")
            void testBouldersCollideFromAboveIntoBoulder() {
                player_ = new Player(d_, 3, 1);
                d_.setPlayer(player_);
                
                // Try to move player and boulder 
                player_.tryMoveDown();
                
                // Player doesn't move
                assertEquals(3, player_.getX());
                assertEquals(1, player_.getY());
                
                // All the boulders don't move.
                assertEquals(3, down_boulder.getX());
                assertEquals(4, down_boulder.getY());
                assertEquals(3, middle_boulder.getX());
                assertEquals(3, middle_boulder.getY());
                assertEquals(3, up_boulder.getX());
                assertEquals(2, up_boulder.getY());
                assertEquals(4, right_boulder.getX());
                assertEquals(3, right_boulder.getY());
                assertEquals(2, left_boulder.getX());
                assertEquals(3, right_boulder.getY());
            }
            
            @Test
            @DisplayName("can't move boulders from left into other boulders")
            void testBouldersCollideFromLeftIntoBoulder() {
                player_ = new Player(d_, 1, 3);
                d_.setPlayer(player_);
                
                // Try to move player and boulder 
                player_.tryMoveRight();
                
                // Player doesn't move
                assertEquals(1, player_.getX());
                assertEquals(3, player_.getY());
                
                // All the boulders don't move.
                assertEquals(3, down_boulder.getX());
                assertEquals(4, down_boulder.getY());
                assertEquals(3, middle_boulder.getX());
                assertEquals(3, middle_boulder.getY());
                assertEquals(3, up_boulder.getX());
                assertEquals(2, up_boulder.getY());
                assertEquals(4, right_boulder.getX());
                assertEquals(3, right_boulder.getY());
                assertEquals(2, left_boulder.getX());
                assertEquals(3, right_boulder.getY());
            }
            
            @Test
            @DisplayName("can't move boulders from right into other boulders")
            void testBouldersCollideFromRightIntoBoulder() {
                player_ = new Player(d_, 5, 3);
                d_.setPlayer(player_);
                
                // Try to move player and boulder 
                player_.tryMoveLeft();
                
                // Player doesn't move
                assertEquals(5, player_.getX());
                assertEquals(3, player_.getY());
                
                // All the boulders don't move.
                assertEquals(3, down_boulder.getX());
                assertEquals(4, down_boulder.getY());
                assertEquals(3, middle_boulder.getX());
                assertEquals(3, middle_boulder.getY());
                assertEquals(3, up_boulder.getX());
                assertEquals(2, up_boulder.getY());
                assertEquals(4, right_boulder.getX());
                assertEquals(3, right_boulder.getY());
                assertEquals(2, left_boulder.getX());
                assertEquals(3, right_boulder.getY());
            }
        }
        
        @Nested
        @DisplayName("boulder movement integration test")
        class TestBoulderCollisionIntegrationTest {
            
            Boulder boulder_1;
            Boulder boulder_2;
            
            @BeforeEach
            void addBoulders() {
                // Manually add the middle boulder so we can track it's position
                boulder_1 = new Boulder(d_, 3, 3);
                boulder_2 = new Boulder(d_, 2, 5);
                d_.addEntity(boulder_1);
                d_.addEntity(boulder_2);
            }
            
            @Test
            @DisplayName("test can move boulders multiple times")
            void testBouldersCanCollideMoreThanOnce() {
                player_ = new Player(d_, 3, 4);
                d_.setPlayer(player_);
                player_.tryMoveUp(); 
                
                assertEquals(3, player_.getX());// Player move to (3,3)
                assertEquals(3, player_.getY());
                assertEquals(3, boulder_1.getX()); // Boulder move to (3,2)
                assertEquals(2, boulder_1.getY());
                
                player_.tryMoveRight(); // Player moves to (4,3)
                player_.tryMoveUp(); // Player moves to (4, 2)
                player_.tryMoveLeft();
                
                assertEquals(3, player_.getX()); // Player moves to (3,2)
                assertEquals(2, player_.getY());
                assertEquals(2, boulder_1.getX()); // boulder moves to (2,2)
                assertEquals(2, boulder_1.getY()); 
                
                player_.tryMoveUp();  // Player moves to (3, 1)
                player_.tryMoveLeft(); // Player moves to (2, 1)
                player_.tryMoveDown();
                
                assertEquals(2, player_.getX()); // Player moves to (2,2)
                assertEquals(2, player_.getY());
                assertEquals(2, boulder_1.getX()); // boulder moves to (2,3)
                assertEquals(3, boulder_1.getY()); 
                
                player_.tryMoveLeft();  // Player moves to (1, 2)
                player_.tryMoveDown(); // Player moves to (1, 3)
                player_.tryMoveRight();
                
                assertEquals(2, player_.getX()); // Player moves to (2,3)
                assertEquals(3, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder moves to (3,3)
                assertEquals(3, boulder_1.getY()); 
            }
        
            
            @Test
            @DisplayName("test can collide two boulders multiple times")
            void testTwoBouldersCanCollideMoreThanOnce() {
                player_ = new Player(d_, 3, 2); // Player at (3, 2)
                d_.setPlayer(player_);
                player_.tryMoveDown(); // Player at (3, 3)
                player_.tryMoveDown(); // Player at (3, 4)
                
                // Collision of boulder with wall.
                player_.tryMoveDown(); // Player at (3, 4)
                assertEquals(3, player_.getX()); // Player moves to (3,4)
                assertEquals(4, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_1.getY());
                
                player_.tryMoveRight(); // Player at (4, 4)
                player_.tryMoveDown();  // Player at (4, 5)
                
                // Collision of boulder_1 with boulder_2.
                player_.tryMoveLeft(); // Player at (4, 5)
                assertEquals(4, player_.getX()); // Player stays at (4,5)
                assertEquals(5, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_1.getY());
                assertEquals(2, boulder_2.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_2.getY());
                
                // System is stuck, test player can't move the boulders.
                player_.tryMoveUp(); // Player at (4, 4)
                player_.tryMoveLeft(); // Player at (3, 4)
                
                // No effect, player collides with boulder_1, boulder_1 behind wall
                player_.tryMoveDown();
                assertEquals(3, player_.getX()); // Player stays at (3,4)
                assertEquals(4, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_1.getY());
                assertEquals(2, boulder_2.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_2.getY());
                
                player_.tryMoveLeft(); // Player at (2, 4)
                
                // No effect, player collides with boulder_2, boulder_2 behind wall
                player_.tryMoveDown();
                assertEquals(2, player_.getX()); // Player stays at (2,4)
                assertEquals(4, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_1.getY());
                assertEquals(2, boulder_2.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_2.getY());
                
                player_.tryMoveLeft(); // Player at (1, 4)
                player_.tryMoveDown(); // Player at (1, 5)
                
                // No effect, player collides with boulder_2, boulder_2 behind boulder_1
                player_.tryMoveRight();
                assertEquals(1, player_.getX()); // Player stays at (1, 5)
                assertEquals(5, player_.getY());
                assertEquals(3, boulder_1.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_1.getY());
                assertEquals(2, boulder_2.getX()); // boulder stays at (3,5)
                assertEquals(5, boulder_2.getY());
                      
            }
        
        }
        
    }

}