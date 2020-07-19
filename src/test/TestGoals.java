package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import unsw.dungeon.DungeonTestLoader;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;

@DisplayName("testing goals")
public class TestGoals {
    
    @Nested
    @DisplayName("testing goal creation")
    class TestGoalCreation {
        
        @Test
        @DisplayName("testing simple base goal creation")
        void testBaseGoalSimple() {
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
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
                .put("goal-condition", new JSONObject()
                    .put("goal", "treasure")
                );
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            assertEquals("treasure", mainGoal.toString());
            
            // Test the basic boulder goal works
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
                .put("goal-condition", new JSONObject()
                    .put("goal", "boulders")
                );
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            assertEquals("boulders", mainGoal.toString());
            
            // Test the basic enemy goal works
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
                .put("goal-condition", new JSONObject()
                    .put("goal", "enemies")
                );
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            assertEquals("enemies", mainGoal.toString());
            // Print them out inorder or something and check the order is correct
        }
        
        @Test
        @DisplayName("testing simple cunjunction creations")
        void testConjunctionGoalSimple() {
            JSONObject json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            assertEquals("(enemies OR treasure)", mainGoal.toString());
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            assertEquals("(enemies AND treasure AND exit AND boulders)", mainGoal.toString());
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
        
            mainGoal = d.getMainGoal();
            assertEquals("(enemies OR treasure OR exit OR boulders)", mainGoal.toString());
            
        }
        
        @Test
        @DisplayName("testing one level recusion of conjunctions creation") 
        void testOneLevelRecusionGoalMedium() {
            JSONObject json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
            
            mainGoal = d.getMainGoal();
            // Tests that the same basic goal can be put in multiple compositions.
            assertEquals("((exit AND treasure) OR (enemies AND treasure))", mainGoal.toString());
            
        }
        
        @Test
        @DisplayName("testing deep recursive conjunction creation")
        void testDeepRecursionGoalHard() {
            JSONObject json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
            mainGoal = d.getMainGoal();
            assertEquals("((exit AND (enemies OR boulders)) AND " 
                + "(enemies AND (treasure OR boulders)))", mainGoal.toString());
            
            
            // One with a full binary tree because why not
            json = new JSONObject()
                .put("width", 3)
                .put("height", 3)
                .put("entities", new JSONArray())
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
            dl = new DungeonTestLoader(json);
            d = dl.load();
            mainGoal = d.getMainGoal();
            assertEquals("(((treasure OR exit) AND (enemies AND boulders AND exit)) AND " 
                + "((enemies AND exit) AND (treasure OR boulders OR exit)))", mainGoal.toString());
            
        }
        
    }
    
    @Nested
    @DisplayName("testing goal completion")
    class TestGoalCompletion {
        
        @Test
        @DisplayName("testing simple base goal completion")
        void testSimpleGoalCompletion() {
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
            
            p.tryMoveDown();
            p.tryMoveDown();
            p.tryMoveRight();
            p.tryMoveRight();
            
            
            // When the player reaches the exit goal, all the goals are completed.
            // So the dungeon is completed.
            assertTrue(d.isGameEnded());
            
        }  
    }
}