package unsw.dungeon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController extends Controller {

    @FXML
    private Button selectLevel;

    @FXML
    private StackPane selectLevelPane;

    @FXML
    private StackPane mainMenuPane;

    @FXML
    private StackPane backgroundPane;

    private String selectedLevel;

    public MainMenuController(String selectedLevel) {
        selectedLevel = "level1";
    }

    @FXML
    public void selectLevel() {
        selectLevelPane.toFront();
        selectLevelPane.setVisible(true);
        mainMenuPane.setVisible(false);
        backgroundPane.setVisible(false);
    }

    @FXML
    public void changeToLevel1() {
        main.setLevel("level1.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToLevel2() {
        main.setLevel("level2.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToLevel3() {
        main.setLevel("level3.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToLevel4() {
        main.setLevel("level4.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToLevel5() {
        main.setLevel("level5.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToLevel6() {
        main.setLevel("level6.json");
        try {
            main.changeToDungeon();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}