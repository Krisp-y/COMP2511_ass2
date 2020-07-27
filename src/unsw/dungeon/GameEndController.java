package unsw.dungeon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class GameEndController extends Controller {
    @FXML 
    public void restartLevel() {
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    public void mainMenu() {
        try {
            main.changeToMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}