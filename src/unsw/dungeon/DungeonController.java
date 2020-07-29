package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller {

    @FXML
    private GridPane squares;

    @FXML
    private VBox pauseMenu;
    
    @FXML
    private HBox inventoryHbox;
    
    @FXML
    private VBox itemStatusVbox;
    
    @FXML
    private VBox basicGoalVbox;
    
    private HBox weaponStatus;
    private HBox potionStatus;

    private List<ImageView> initialEntities;
    
    // I am so unbelievably sorry for this mess but deadlines make you do dumb things
    private Map<CollectibleEnum, EntityView> entityImageMap;
    private Map<CollectibleEnum, Integer> inventoryCountMap;
    private Map<CollectibleEnum, Pane> inventoryViewMap;
    private List<Pair<BasicGoal, HBox>> basicGoalViews;

    private Player player;
    private Goal mainGoal;
    private Dungeon dungeon;

    private Timeline tickerTimeline;
    private Timeline dungeonEnd;
    
    private boolean isPaused;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, 
        Map<Entity, EntityView> entityImageMap) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.entityImageMap = new HashMap<CollectibleEnum, EntityView>();
        this.inventoryCountMap = new HashMap<CollectibleEnum, Integer>();
        this.inventoryViewMap = new HashMap<CollectibleEnum, Pane>();
        this.basicGoalViews = new ArrayList<Pair<BasicGoal, HBox>>();
        this.isPaused = false;
        this.tickerTimeline = new Timeline();
        this.dungeonEnd = new Timeline();
        
        setupEntityImageMap(entityImageMap);
        setupInventoryCountMap();
        setupTickerTimeline();
        setupDungeonEndTimeline();
        dungeon.subscribeController(this);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        tickerTimeline.play();
        
        setupBasicGoalView();
        setupMainGoalView();
    }

    @FXML
    public void resume() {
        isPaused = false;
        pauseMenu.setVisible(false);
        tickerTimeline.play();
        squares.setEffect(null);
        squares.requestFocus();
    }

    public void pause() {
        pauseMenu.setVisible(true);
        tickerTimeline.pause();
        squares.setEffect(new BoxBlur());
        isPaused = true;
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (isPaused) {

            switch (event.getCode()) {
                case ESCAPE:
                    resume();
                    isPaused = false;
                    break;
                default:
                    break;
            }
            return;
        }
        switch (event.getCode()) {
            case UP:
                player.tryMoveUp();
                break;
            case DOWN:
                player.tryMoveDown();
                break;
            case LEFT:
                player.tryMoveLeft();
                break;
            case RIGHT:
                player.tryMoveRight();
                break;
            case ESCAPE:
                pause();
                break;
            default:
                break;
        }
        // tick();
    }

    public void gameWon() {
        dungeonEnd.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            try {
                main.changeToGameWon();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }));
        dungeonEnd.play();
    }
    
    public void gameLost() {
        dungeonEnd.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            try {
                main.changeToGameLost();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }));
        dungeonEnd.play();
    }
        
    public void tick() {
        dungeon.tick();
    }

	public void reduceWeaponHealth() {
	    int endidx = weaponStatus.getChildren().size() - 1;
	    weaponStatus.getChildren().remove(endidx);
	}
	

	public void showWeaponStatus(boolean b) {
	    if (b) {
	        assert(weaponStatus == null);
	        weaponStatus = new HBox();
	        weaponStatus.getChildren().add(
	            new ImageView(entityImageMap.get(CollectibleEnum.WEAPON).getImage()));
            // Add all the health dots.
            for (int i = 0; i < Dungeon.WEAPON_HEALTH; i++) {
                weaponStatus.getChildren().add(getImageFromPath("images/dot.png"));
            }
	        itemStatusVbox.getChildren().add(weaponStatus);
	    } else {
	        weaponStatus.getChildren().clear();
	        itemStatusVbox.getChildren().remove(weaponStatus);
	    }
	}

	public void showInvincibleStatus(boolean b) {
	    if (b) {
	        if (potionStatus != null) {
	            // WE already have a potion, so just update the counter to 15.
	            updatePotionHealth(Dungeon.POTION_HEALTH);
	            return;
	        }
	        
	        potionStatus = new HBox();
	        potionStatus.getChildren().add(
	            new ImageView(entityImageMap.get(CollectibleEnum.POTION).getImage()));
            // Add all the health dots.
            potionStatus.getChildren().add(new Text(": " + Dungeon.POTION_HEALTH));
	        itemStatusVbox.getChildren().add(potionStatus);
	    } else {
	        potionStatus.getChildren().clear();
	        itemStatusVbox.getChildren().remove(potionStatus);
	    }
	}
	
	public void updatePotionHealth(int health) {
	    Text t = (Text) potionStatus.getChildren().get(1);
	    t.setText(": " + health);
	}

	public void addToInventoryView(Entity e) {
	    Integer oldcount = inventoryCountMap.get(Dungeon.getEntityEnum(e));
	    inventoryCountMap.put(Dungeon.getEntityEnum(e), oldcount + 1);
	    
	    if (oldcount == 0) {
	        Pane inventoryItem = createInventoryItem(Dungeon.getEntityEnum(e));
	        inventoryHbox.getChildren().add(inventoryItem);
	    } else {
	        updateInventoryItem(Dungeon.getEntityEnum(e), oldcount + 1);
	    }
	}

	public void removeFromInventoryView(Entity e) {
	    Integer oldcount = inventoryCountMap.get(Dungeon.getEntityEnum(e));
	    inventoryCountMap.put(Dungeon.getEntityEnum(e), oldcount - 1);
	    
	    if (oldcount - 1 ==  0) { // Remove the pane from the inventory view
	        Pane p = inventoryViewMap.get(Dungeon.getEntityEnum(e));
	        inventoryHbox.getChildren().remove(p);
	    } else {
	        updateInventoryItem(Dungeon.getEntityEnum(e), oldcount - 1);
	    }
	}
	
	private Pane createInventoryItem(CollectibleEnum e) {
	    StackPane p = new StackPane();
	    p.setAlignment(Pos.BOTTOM_RIGHT);
	    p.getChildren().add(new ImageView(entityImageMap.get(e).getImage()));
	    p.getChildren().add(new Text(""));
	    inventoryViewMap.put(e, p);
	    return p;
	}
	
	private void updateInventoryItem(CollectibleEnum e, Integer count) {
	    Pane p = inventoryViewMap.get(e); // get the pane.
	    int idx = inventoryHbox.getChildren().indexOf(p);
	    Text t = (Text) p.getChildren().get(1); // This is always the text object.
	    t.setText(count.toString());
	    inventoryHbox.getChildren().set(idx, p); // replace with the new pane.
    }

    private void setupTickerTimeline() {
        tickerTimeline.setCycleCount(Animation.INDEFINITE);
        tickerTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            tick();
        }));
    }

    private void setupDungeonEndTimeline() {
        dungeonEnd.setCycleCount(1);
    }

    private void setupInventoryCountMap() {
        for (CollectibleEnum e : entityImageMap.keySet()) {
            inventoryCountMap.put(e, 0);
        }
    }
	
	private ImageView getImageFromPath(String path) {
	    return new ImageView(new Image((new File(path)).toURI().toString()));
	}

    private void setupEntityImageMap(Map<Entity, EntityView> entityImageMap_) {
        for (Map.Entry<Entity, EntityView> entry : entityImageMap_.entrySet()) {
            this.entityImageMap.put(Dungeon.getEntityEnum(entry.getKey()), entry.getValue());
        }
    }
    
    private void setupBasicGoalView() {
        List<BasicGoal> basicGoals = dungeon.getBasicGoals();
        
        for (BasicGoal g : basicGoals) {
            HBox goalView = new HBox();
            if (g instanceof EnemyGoal) {
                EnemyGoal eg = (EnemyGoal) g;
                goalView.getChildren().add(getImageFromPath("images/deep_elf_master_archer.png"));
                goalView.getChildren().add(new Text(eg.getDeadEnemyCount() + "/" + eg.getEnemyCount()));
            } else if (g instanceof TreasureGoal) {
                TreasureGoal tg = (TreasureGoal) g;
                goalView.getChildren().add(getImageFromPath("images/gold_pile.png"));
                goalView.getChildren().add(new Text(tg.getCollectedTreasureCount() + "/" + tg.getTreasureCount()));
            } else if (g instanceof BoulderGoal) {
                BoulderGoal bg = (BoulderGoal) g;
                goalView.getChildren().add(getImageFromPath("images/boulder.png"));
                goalView.getChildren().add(new Text(bg.getSwitchesTriggered() + "/" + bg.getNumFloorSwitches()));
            } else if (g instanceof ExitGoal) {
                goalView.getChildren().add(getImageFromPath("images/exit.png"));
                goalView.getChildren().add(new Text("Incomplete"));
            }
            basicGoalVbox.getChildren().add(goalView);
            basicGoalViews.add(new Pair<BasicGoal, HBox>(g, goalView));
        }
        
        
    }
    
    private void setupMainGoalView() {
        return;
    }
    
    public void updateBasicGoals() {
        for (Pair<BasicGoal, HBox> bgHBox : basicGoalViews) {
            Text t = (Text) bgHBox.getValue().getChildren().get(1);
            if (bgHBox.getKey() instanceof EnemyGoal) {
                EnemyGoal eg = (EnemyGoal) bgHBox.getKey();
                t.setText(eg.getDeadEnemyCount() + "/" + eg.getEnemyCount());
            } else if (bgHBox.getKey() instanceof TreasureGoal) {
                TreasureGoal tg = (TreasureGoal) bgHBox.getKey();
                t.setText(tg.getCollectedTreasureCount() + "/" + tg.getTreasureCount());
            } else if (bgHBox.getKey() instanceof BoulderGoal) {
                BoulderGoal bg = (BoulderGoal) bgHBox.getKey();
                t.setText(bg.getSwitchesTriggered() + "/" + bg.getNumFloorSwitches());
            }
        }
    }
}

