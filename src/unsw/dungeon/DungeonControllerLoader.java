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

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        entityImageMap = new HashMap<Entity, EntityView>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        potionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        weaponImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        ghostImage = new Image((new File("images/Enemy_crop.png")).toURI().toString());
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
        System.out.println(ghostImage.getHeight());
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
    private void trackPosition(Entity entity, Node node) {
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
