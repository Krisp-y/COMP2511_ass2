package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
    
    private Stage primaryStage;
    private String selectedLevel;
    
    
    public void setLevel(String newLevel) {
        selectedLevel = newLevel;
    }
    
    private void changeScene(FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void changeToMainMenu() throws IOException {
        changeScene(createMainMenuLoader());
    }
    
    public void changeToDungeon() throws IOException {
        changeScene(createDungeonLoader(selectedLevel));
    }
    
    public void changeToGameWon() throws IOException {
        changeScene(createGameEndLoader("won"));
    }
    
    public void changeToGameLost() throws IOException {
        changeScene(createGameEndLoader("lost"));
    }
    
    private FXMLLoader createGameEndLoader(String type) throws IOException {
        
        GameEndController gameOverController;
        switch (type) {
            case "won":
                gameOverController = new GameWonController();
                break;
            case "lost":
                gameOverController = new GameLostController();
                break;
            default:
                gameOverController = null;
        }
        
        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        gameOverLoader.setController(gameOverController);
        gameOverController.subscribe(this);
        return gameOverLoader;
    }
    
    private FXMLLoader createMainMenuLoader() throws IOException {
        MainMenuController menuController = new MainMenuController(selectedLevel);
        FXMLLoader menuloader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        menuloader.setController(menuController);
        menuController.subscribe(this);
        return menuloader;
    }
    
    private FXMLLoader createDungeonLoader(String selectedLevel) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(selectedLevel);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        controller.subscribe(this);
        return loader;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.selectedLevel = "advanced.json";
        changeScene(createMainMenuLoader());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
