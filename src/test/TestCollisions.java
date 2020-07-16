package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

@DisplayName("testing collisions")
class TestCollisions {
    Dungeon d;
    Player player_;
    

    @Nested
    @DisplayName("testing wall collisions")
    class TestWallCollisions {
        List<Wall> walls;

        @BeforeEach
        void createDungeon() {
            d = new Dungeon(5, 5);
            walls = Arrays.asList( 
                new Wall(2,2), //Walls[0]
                new Wall(3,2) // Walls[1]
            );
            for (Wall wall_ : walls) {
                d.addEntity(wall_);
            }
            // Put player in the top left corner
            player_ = new Player(d, 0, 0);
            d.setPlayer(player_);
        }
        
        @Test
        @DisplayName("collides from above")
        void testCollidesFromAbove() {
            // Player at (0,0)
            player_.moveRight(); // Player at (1,0)
            player_.moveRight(); // Player at (2,0)
            player_.moveDown(); // Player at (2,1)
            // Player should not be able to move through the wall at (2,2).
            player_.moveDown(); // Player stays at (2,1)
            assertEquals(2, player_.getX());
            assertEquals(1, player_.getY());

            // Check that the wall has also not moved.
            assertEquals(2, walls.get(0).getX());
            assertEquals(2, walls.get(0).getY());
        }

        @Test
        @DisplayName("collides from left")
        void testCollidesFromLeft() {
            // Player at (0,0)
            player_.moveDown(); // Player at (0,1)
            player_.moveDown(); // Player at (0,2)
            player_.moveRight(); // Player at (1,2)
            // Player should not be able to move through the wall at (2,2).
            player_.moveRight(); // Player at (1,2)
            assertEquals(1, player_.getX());
            assertEquals(2, player_.getY());

            // Check that the wall has also not moved.
            assertEquals(2, walls.get(0).getX());
            assertEquals(2, walls.get(0).getY());
        }

        @Test
        @DisplayName("collides from below")
        void testCollidesFromBelow() {
            // Player at (0,0)
            player_.moveDown(); // Player at (0,1)
            player_.moveDown(); // Player at (0,2)
            player_.moveDown(); // Player at (0,3)
            player_.moveRight(); // Player at (1,3)
            player_.moveRight(); // Player at (2,3)
            
            // Player should not be able to move through the wall at (2,2).
            player_.moveUp(); // Player at (2,3)
            assertEquals(2, player_.getX());
            assertEquals(3, player_.getY());

            // Check that the wall at (2,2) has also not moved.
            assertEquals(2, walls.get(0).getX());
            assertEquals(2, walls.get(0).getY());

            player_.moveRight(); //Player at (3,3)
            player_.moveUp(); // Player at (3,3)
            assertEquals(3, player_.getX());
            assertEquals(3, player_.getY());

            // Check that the wall at(3,2) has also not moved.
            assertEquals(3, walls.get(0).getX());
            assertEquals(2, walls.get(0).getY());
        }

        @Test
        @DisplayName("collides from right")
        void testCollidesFromRight() {
            // Player at (0,0)
            player_.moveDown(); // Player at (0,1)
            player_.moveDown(); // Player at (0,2)
            player_.moveDown(); // Player at (0,3)
            player_.moveRight(); // Player at (1,3)
            player_.moveRight(); // Player at (2,3)
            player_.moveRight(); // Player at (3,3)
            player_.moveRight(); // Player at (4,3)
            player_.moveUp();    // Player at (4,2)
            // Player should not be able to move through the wall.
            player_.moveLeft(); // Player stays at (4,2)
            assertEquals(3, player_.getX());
            assertEquals(3, player_.getY());

            // Check that the wall at(3,2) has also not moved.
            assertEquals(3, walls.get(0).getX());
            assertEquals(2, walls.get(0).getY());
        }
    }
}