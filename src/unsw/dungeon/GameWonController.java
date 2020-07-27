package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameWonController extends GameEndController {

    @FXML
    Text gameOverText;
    
    @FXML
    Text subText;
    
    public GameWonController() {
        super();
    }
    
    @FXML
    public void initialize() {
        gameOverText.setText("You Won!");
    }
}