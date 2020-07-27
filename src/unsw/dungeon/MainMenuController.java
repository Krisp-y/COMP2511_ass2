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


public class MainMenuController {
    
    @FXML
    private Text selectedLevelText;
    
    @FXML
    private Button playGame;
    
    @FXML
    private Button selectLevel;
    
    @FXML
    private Button changeSettings;
    
    @FXML
    private VBox rightPanel;
    
    private String selectedLevel;
    private ArrayList<Button> buttons;
    private Stage primaryStage;
    
    public MainMenuController(Stage primaryStage) {
        selectedLevel = "advanced.json";
        buttons = new ArrayList<Button>();
        this.primaryStage = primaryStage;
    }
    
    @FXML 
    public void startGame() throws IOException {
        System.out.println("Starting Game!");
        primaryStage.setTitle("Dungeon");
        
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(selectedLevel);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene game_scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(game_scene);
        primaryStage.show();
    }
    
    @FXML 
    public void changeSettings() {
        System.out.println("Changing Settings!");
    }
    
    @FXML
    public void selectLevel() {
        if (!buttons.isEmpty()) {
            return;
        }
        File f = new File("dungeons");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        
        
        for (File file : files) {
            Button button = new Button();
            String prettified = prettify(file.getName());
            button.setText(prettified);
            button.setOnAction(e -> {
                selectedLevel = file.getName(); 
                selectedLevelText.setText(prettified);
                rightPanel.getChildren().removeAll(buttons);
                buttons.clear();
            });
            buttons.add(button);
            rightPanel.getChildren().add(button);
        }
    }
    
    private String prettify(String filename) {
        String[] nofiletype = filename.split("\\.");
        String[] parts = nofiletype[0].split("_");
        String result = "";
        for (String part : parts) {
            String lowercase = part.toLowerCase();
            result += lowercase.substring(0, 1).toUpperCase() + lowercase.substring(1) + " ";
        }
        result += "Maze";
        return result;
    }
    
}