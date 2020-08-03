package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView extends ImageView {
    public EntityView(Image image) {
        super(image);
    }
    
    public void despawnUpdate() {
        this.setVisible(false);
    }
    
    public void changeDoorOpenImage() {
        Image image = new Image((new File("images/open_door.png")).toURI().toString());
        this.setImage(image);
    }
    
    public void spawnUpdate() {
        this.setVisible(true);
    }
    
}