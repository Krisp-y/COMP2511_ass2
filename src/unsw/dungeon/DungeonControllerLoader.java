package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    private Map<Entity, EntityView> entityImageMap;
    
    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image exitImage;
    private Image portalImage;
    private Image floorSwitchImage;
    private Image enemyImage;
    private Image keyImage;
    private Image doorImage;
    private Image treasureImage;
    private Image potionImage;
    private Image weaponImage;
    private Image ghostImage;
    private Image landMineImage;
    private Image fireBallImage;
    private Image dragonImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        entityImageMap = new HashMap<Entity, EntityView>();
        playerImage = createImage("src/images/human_new.png");
        wallImage = createImage("src/images/brick_blue.png");
        boulderImage = createImage("src/images/boulder.png");
        exitImage = createImage("src/images/exit.png");
        portalImage = createImage("src/images/portal.png");
        floorSwitchImage = createImage("src/images/pressure_plate.png");
        enemyImage = createImage("src/images/deep_elf_master_archer.png");
        keyImage = createImage("src/images/key.png");
        doorImage = new Image((new File("images/closed_door2.png")).toURI().toString());
        treasureImage = createImage("src/images/gold_pile.png");
        potionImage = createImage("src/images/bubbly.png");
        weaponImage = createImage("src/images/greatsword_1_new.png");
        ghostImage = createImage("src/images/ghost2.png");
        landMineImage = createImage("src/images/mine.png");
        fireBallImage = createImage("src/images/fireball.png");
        dragonImage = createImage("src/images/Dragon.png");
            }
    
    private Image createImage(String path) {
        return new Image((new File(path)).toURI().toString());
    }
    
    @Override
    public void onLoad(Player player) {
        EntityView view = new EntityView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        EntityView view = new EntityView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        EntityView view = new EntityView(boulderImage);
        addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        EntityView view = new EntityView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Portal portal) {
        EntityView view = new EntityView(portalImage);
        addEntity(portal, view);
    }
    
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        EntityView view = new EntityView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        EntityView view = new EntityView(enemyImage);
        addEntity(enemy, view);
    }
   
    @Override
    public void onLoad(Ghost ghost) {
        EntityView view = new EntityView(ghostImage);
        addEntity(ghost, view);
    }

    public void onLoad(Treasure treasure) {
        EntityView view = new EntityView(treasureImage);
        addEntity(treasure, view);
    }
    
    public void onLoad(Key key) {
        EntityView view = new EntityView(keyImage);
        addEntity(key, view);
    }
        
    public void onLoad(Door door) {
        EntityView view = new EntityView(doorImage);
        addEntity(door, view);
    }
    
    public void onLoad(Potion potion) {
        EntityView view = new EntityView(potionImage);
        addEntity(potion, view);
    }
    
    public void onLoad(Weapon weapon) {
        EntityView view = new EntityView(weaponImage);
        addEntity(weapon, view);
    }

    public void onLoad(FireBall fireBall) {
        EntityView view = new EntityView(fireBallImage);
        addEntity(fireBall, view);
    }

    public void onLoad(Dragon dragon) {
        EntityView view = new EntityView(dragonImage);
        addEntity(dragon, view);
    }
    
    public void onLoad(LandMine landMine) {
        EntityView view = new EntityView(landMineImage);
        addEntity(landMine, view);
    }
    private void addEntity(Entity entity, EntityView view) {
        track(entity, view);
        entities.add(view);
        entityImageMap.put(entity, view);
    }
    
    

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, final Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
    
    private void track(Entity entity, EntityView view) {
        entity.subsribeView(view);
        trackPosition(entity, view);
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities, entityImageMap);
    }

    
}
