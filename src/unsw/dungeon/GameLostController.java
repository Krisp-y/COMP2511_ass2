package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameLostController extends GameEndController {
    @FXML
    Text gameOverText;
    
    @FXML
    Text subText;
    
    public GameLostController() {
        super();
    }
    
    @FXML
    public void initialize() {
        gameOverText.setText("You Lost!");
    }
}