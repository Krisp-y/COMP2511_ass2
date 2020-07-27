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

import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;

@DisplayName("testing goals")
class TestGoals {    
    @Nested
    @DisplayName("testing goal creation")
    class TestGoalCreation {
        
        @Nested
        @DisplayName("testing simple base goal creation")
        class testBaseGoalSimple {
            
            @Test
            @DisplayName("testing exit goal creation")
            void testExitGoalCreation() {
                JSONObject json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray()
                    .put(new JSONObject()
                        .put("type", "exit")
                        .put("x", 2)
                        .put("y", 2)
                    )
                )   
                .put("goal-condition", new JSONObject()
                    .put("goal", "exit")
                );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("exit", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing treasure goal creation")
            void testTreasureGoalCreation() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 1)
                            .put("y", 1)
                        )   
                    
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "treasure")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("treasure", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing boulder goal creation")
            void testBoulderGoalCreation() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 2)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 1)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "boulders")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("boulders", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing enemy goal creation")
            void testEnemyGoalCreation() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                        .put("type", "enemy")
                        .put("x", 1)
                        .put("y", 1)
                        )   
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "enemies")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("enemies", mainGoal.toString());
            }
        }
        
        @Nested
        @DisplayName("testing simple cunjunction creations")
        class testConjunctionGoalSimple {
            
            @Test
            @DisplayName("testing enemies AND treasure goal creation")
            void testEnemiesAndTreasure() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                    
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "enemies")
                            )
                            .put(new JSONObject()
                                .put("goal", "treasure")
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(enemies AND treasure)", mainGoal.toString());  
            }
           
            @Test
            @DisplayName("testing enemies OR treasure goal creation")
            void testEnemiesOrTreasure() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "OR")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "enemies")
                            )
                            .put(new JSONObject()
                                .put("goal", "treasure")
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(enemies OR treasure)", mainGoal.toString());
            }
            @Test
            @DisplayName("testing enemies AND treasure AND exit AND boulders goal creation")
            void testMultipleAndGoalCreation() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "enemies")
                            )
                            .put(new JSONObject()
                                .put("goal", "treasure")
                            )
                            .put(new JSONObject()
                                .put("goal", "exit")
                            )
                            .put(new JSONObject()
                                .put("goal", "boulders")
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(enemies AND treasure AND exit AND boulders)", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing enemies OR treasure OR exit OR boulders goal creation")
            void testMultipleOrGoalCreation() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                    .put("goal", "OR")
                    .put("subgoals", new JSONArray()
                        .put(new JSONObject()
                            .put("goal", "enemies")
                        )
                        .put(new JSONObject()
                            .put("goal", "treasure")
                        )
                        .put(new JSONObject()
                            .put("goal", "exit")
                        )
                        .put(new JSONObject()
                            .put("goal", "boulders")
                        )
                    )
                );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(enemies OR treasure OR exit OR boulders)", mainGoal.toString());
            }
        }
        
        @Nested
        @DisplayName("testing one level recusion of conjunctions creation") 
        class testOneLevelRecusionGoalMedium {
            
            @Test
            @DisplayName("testing (exit AND (enemies OR treasure)) correctly created") 
            void testOneLevelRecursion1() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "exit")
                            )
                            .put(new JSONObject()
                                .put("goal", "OR")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "enemies")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "treasure")
                                    )
                                )
                            )
                        )
                    );
            DungeonLoader dl = new DungeonTestLoader(json);
            Dungeon d = dl.load();
            Goal mainGoal = d.getMainGoal();
            assertEquals("(exit AND (enemies OR treasure))", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing ((exit AND treasure) OR (enemies AND treasure)) correctly created") 
            void testOneLevelRecursion2() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "OR")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "exit")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "treasure")
                                    )
                                )
                            )
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "enemies")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "treasure")
                                    )
                                )
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                // Tests that the same basic goal can be put in multiple compositions.
                assertEquals("((exit AND treasure) OR (enemies AND treasure))", mainGoal.toString());
            }
        }
        
        @Nested
        @DisplayName("testing deep recursive conjunction creation")
        class testDeepRecursionGoalHard {
        
            @Test
            @DisplayName("testing (exit AND (enemies AND (treasure AND boulders))) correctly created") 
            void testDeepRecursion1() {
                
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "exit")
                            )
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "enemies")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "AND")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "treasure")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "boulders")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(exit AND (enemies AND (treasure AND boulders)))", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing ((exit AND (enemies OR boulders)) AND " 
                + "(enemies AND (treasure OR boulders))) correctly created")
            void testDeepRecursion2() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "exit")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "OR")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "enemies")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "boulders")
                                            )
                                        )
                                    )
                                )
                            )
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "enemies")
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "OR")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "treasure")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "boulders")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("((exit AND (enemies OR boulders)) AND " 
                    + "(enemies AND (treasure OR boulders)))", mainGoal.toString());
            }
            
            @Test
            @DisplayName("testing (((treasure OR exit) AND (enemies AND boulders AND exit)) AND " 
            + "((enemies AND exit) AND (treasure OR boulders OR exit))) correctly created")
            // One with a full binary tree because why not
            void testDeepRecursion3() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 0)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "OR")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "treasure")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "exit")
                                            )
                                        )
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "AND")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "enemies")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "boulders")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "exit")
                                            )
                                        )
                                    )
                                )
                            )
                            .put(new JSONObject()
                                .put("goal", "AND")
                                .put("subgoals", new JSONArray()
                                    .put(new JSONObject()
                                        .put("goal", "AND")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "enemies")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "exit")
                                            )
                                        )
                                    )
                                    .put(new JSONObject()
                                        .put("goal", "OR")
                                        .put("subgoals", new JSONArray()
                                            .put(new JSONObject()
                                                .put("goal", "treasure")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "boulders")
                                            )
                                            .put(new JSONObject()
                                                .put("goal", "exit")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Goal mainGoal = d.getMainGoal();
                assertEquals("(((treasure OR exit) AND (enemies AND boulders AND exit)) AND " 
                    + "((enemies AND exit) AND (treasure OR boulders OR exit)))", mainGoal.toString());
            }
        }
    }
    
    @Nested
    @DisplayName("testing goal completion")
    class TestGoalCompletion {
        
        @Nested
        @DisplayName("testing simple base goal completion")
        class testSimpleGoalCompletion {
            
            @Test
            @DisplayName("testing exit goal completion")
            void testExitGoalCompletion() {
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "exit")
                    );
                    
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                // Move the player to the exit goal.
                p.tryMoveDown();
                p.tryMoveDown();
                p.tryMoveRight();
                p.tryMoveRight();
        
                // When the player reaches the exit goal, all the goals are completed.
                // So the dungeon is completed.
                assertTrue(d.isGameEnded());
            }
        
            @Test
            @DisplayName("testing boulder goal completion")
            void testBoulderGoalCompletion() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "boulders")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                // Make the player push the boulder onto the floor switch
                p.tryMoveDown();
                p.tryMoveRight(); // PLayer at (1,1) boulder at (2,1)
                p.tryMoveUp();
                p.tryMoveRight();
                p.tryMoveDown(); // Player at (2,1) boulder at (2,2)
        
                // When the player gets onto the switch the game has ended since the goals are complete.
                assertTrue(d.isGameEnded());
            }
            
            @Test
            @DisplayName("testing boulder goal completion two switches")
            void testBoulderGoalCompletionTwoSwitches() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 4)
                    .put("height", 4)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 2)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "boulders")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                // Make the player push the boulder onto the floor switch
                p.tryMoveRight();
                p.tryMoveDown(); // PLayer at (1,1) boulder at (2,1)
                p.tryMoveUp();
                
                // Player has moved one boulder onto the floor switch but the goal isn't complete yet.
                assertFalse(d.isGameEnded());
                
                p.tryMoveRight();
                p.tryMoveDown(); // Player at (2,1) boulder at (2,2)
        
                // When the player moves the second boulder on the switch the game ends.
                assertTrue(d.isGameEnded());
                
                
            }
        
            @Test
            @DisplayName("testing treasure goal completion")
            void testTreasureGoalCompletion() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 1)
                            .put("y", 1)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "treasure")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                // Make the player push the boulder onto the floor switch
                p.tryMoveDown();
                p.tryMoveRight(); // PLayer at (1,1) collects treasure
        
                // The player has collected the treasure so the game has ended.
                assertTrue(d.isGameEnded());
            }
            
            @Test
            @DisplayName("testing treasure goal completion two treasures")
            void testTreasureGoalCompletionTwoTreasures() {
                JSONObject json = new JSONObject()
                    .put("width", 4)
                    .put("height", 4)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "treasure")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "treasure")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                // Make the player push the boulder onto the floor switch
                p.tryMoveDown();
                p.tryMoveRight(); // PLayer at (1,1) collects treasure
                assertFalse(d.isGameEnded()); // There is still one treasure remaining to be picked up.
                
                p.tryMoveDown();
                p.tryMoveRight(); // PLayer at (2,2) collects treasure
                
                // When the player gets onto the switch the game has ended since the goals are complete.
                assertTrue(d.isGameEnded());
            }
        
            @Test
            @DisplayName("testing enemy goal completion")
            void testEnemyGoalCompletion() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 3)
                    .put("height", 3)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "weapon")
                            .put("x", 1)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "enemies")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                d.addEntity(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                p.tryMoveRight(); // Player collects the sword
                d.tick(); 
                p.tryMoveDown();  // Player then kills the enemy
                d.tick();
                // Enemy goal is complete so the game has ended.
                assertTrue(d.isGameEnded());
            }
            
            @Test
            @DisplayName("testing enemy goal completion multiple enemies")
            void testEnemyGoalCompletionMultipleEnemies() {
                // Test the basic boulder goal works
                JSONObject json = new JSONObject()
                    .put("width", 4)
                    .put("height", 4)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "weapon")
                            .put("x", 1)
                            .put("y", 0)
                        )
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 2)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "enemy")
                            .put("x", 3)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "enemies")
                    );
                DungeonLoader dl = new DungeonTestLoader(json);
                Dungeon d = dl.load();
                Player p = new Player(d, 0, 0);
                d.setPlayer(p);
                d.addEntity(p);
                
                // Ensure the game hasn't ended yet.
                assertFalse(d.isGameEnded());
                
                p.tryMoveRight(); // Player collects the sword
                d.tick(); 
                p.tryMoveRight();  // Player then kills the first enemy
                d.tick();
                // Enemy goal is incomplete since an enemy remains
                assertFalse(d.isGameEnded());
                
                p.tryMoveRight(); // Player kills second enemy.
                d.tick();
                // Enemy goal is complete since an enemy remains
                assertTrue(d.isGameEnded());
            }
        
        }
        
        @Nested
        @DisplayName("testing multiple goal completion")
        class testMultipleGoalCompletion {
            
            @Nested
            @DisplayName("testing boulders OR exit completion")
            class  testBouldersOrExit {
                Dungeon d;
                Player p;
                
                @BeforeEach
                void setupDungeon() {
                    JSONObject json = new JSONObject()
                    .put("width", 4)
                    .put("height", 4)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "OR")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "boulders")
                            )
                            .put(new JSONObject()
                                .put("goal", "exit")
                            )
                        )
                    );
                    DungeonLoader dl = new DungeonTestLoader(json);
                    d = dl.load();
                    p = new Player(d, 0, 0);
                    d.setPlayer(p);
                }
                
                
                // Made a class since there are two ways of completing.
                @Test
                @DisplayName("testing boulders OR exit completed by satisfying boulders")
                void testBoulderOrExitWithBouldersFirst() {
                    // Ensure the game hasn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    // Make the player push the boulder onto the floor switch
                    p.tryMoveRight(); 
                    p.tryMoveDown(); // PLayer at (1,1) boulder at (1,2) on the switch
            
                    // When the player gets onto the switch the game has ended since the goals are complete.
                    assertTrue(d.isGameEnded());
                }
                
                @Test
                @DisplayName("testing boulders OR exit completed by satisfying exit")
                void testBoulderOrExitWithExitFirst() {
                    // Ensure the game hasn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    // Make the player push the boulder onto the floor switch
                    p.tryMoveRight(); 
                    p.tryMoveRight();
                    p.tryMoveDown();
                    p.tryMoveDown(); // Player at (2,2) on the exit tile.
            
                    // When the player gets onto the switch the game has ended since the goals are complete.
                    assertTrue(d.isGameEnded());
                }
            }
            
            @Nested
            @DisplayName("testing boulders AND exit completion")
            class testBouldersAndExit {
                // Made class to check completion in the case maze isn't stepped on last.
                Dungeon d;
                Player p;
                
                @BeforeEach
                void setupDungeon() {
                    JSONObject json = new JSONObject()
                    .put("width", 4)
                    .put("height", 5)
                    .put("entities", new JSONArray()
                        .put(new JSONObject()
                            .put("type", "boulder")
                            .put("x", 1)
                            .put("y", 1)
                        )
                        .put(new JSONObject()
                            .put("type", "switch")
                            .put("x", 1)
                            .put("y", 2)
                        )
                        .put(new JSONObject()
                            .put("type", "exit")
                            .put("x", 2)
                            .put("y", 2)
                        )
                    )
                    .put("goal-condition", new JSONObject()
                        .put("goal", "AND")
                        .put("subgoals", new JSONArray()
                            .put(new JSONObject()
                                .put("goal", "boulders")
                            )
                            .put(new JSONObject()
                                .put("goal", "exit")
                            )
                        )
                    );
                    DungeonLoader dl = new DungeonTestLoader(json);
                    d = dl.load();
                    p = new Player(d, 0, 0);
                    d.setPlayer(p);
                }
                
                
                // Made a class since there are two ways of completing.
                @Test
                @DisplayName("testing boulders AND exit completed by satisfying boulders first")
                void testBoulderAndExitWithBouldersFirst() {
                    // Ensure the game hasn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    // Make the player push the boulder onto the floor switch
                    p.tryMoveRight(); 
                    p.tryMoveDown(); // PLayer at (1,1) boulder at (1,2) on the switch
                    
                    // When the player puts boulder onto the switch the game has not ended since
                    // the exit goal is not complete.
                    assertFalse(d.isGameEnded());
                    
                    p.tryMoveRight();
                    p.tryMoveDown(); // Player on the exit tile, the game has now ended
                    
                    assertTrue(d.isGameEnded());
                }
                
                @Test
                @DisplayName("testing boulders AND exit NOT completed by satisfying exit first, must satisfy exit last")
                void testBoulderAndExitWithExitFirst() {
                    // Ensure the game hasn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    // Make the player step on the exit tile.
                    p.tryMoveRight(); 
                    p.tryMoveRight(); // Player at (1,1) boulder at (1,2) on the switch
                    p.tryMoveDown();
                    p.tryMoveDown();
                    
                    // When the player steps on the exit tile the game isn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    p.tryMoveUp();
                    p.tryMoveUp(); 
                    p.tryMoveLeft();
                    p.tryMoveDown(); // Player has moved boulder onto switch, game still isn't ended yet.
                    
                    assertFalse(d.isGameEnded());
                }
                
                @Test
                @DisplayName("testing boulders AND exit NOT completed by satisfying boulders first then moving boulder off switch")
                void testBoulderAndExitWithBouldersFirstThenMovingBoulderAway() {
                    // Ensure the game hasn't ended yet.
                    assertFalse(d.isGameEnded());
                    
                    // Make the player push the boulder onto the floor switch
                    p.tryMoveRight(); 
                    p.tryMoveDown(); // PLayer at (1,1) boulder at (1,2) on the switch
                    
                    // When the player puts boulder onto the switch the game has not ended since
                    // the exit goal is not complete.
                    assertFalse(d.isGameEnded());
                    
                    p.tryMoveDown(); // Player moves boulder off switch (boulder goal incomplete).
                    p.tryMoveRight();// Player on the exit tile, the game has not ended since both goals aren't satisfied.
                    
                    assertFalse(d.isGameEnded());
                    
                    p.tryMoveDown(); // PLayer pushes boulder back onto the switch
                    p.tryMoveDown();
                    p.tryMoveLeft();
                    p.tryMoveUp();  // Player at (3,1)
                    
                    p.tryMoveRight();
                    p.tryMoveUp(); // Game should end here, boulder is on the switch and player is on exit tile.
                    
                    assertTrue(d.isGameEnded());
                }
            }
        }
    }
}