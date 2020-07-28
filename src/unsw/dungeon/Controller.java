package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;

public abstract class Controller {
    protected DungeonApplication main;
    
    public void subscribe(DungeonApplication main) {
        this.main = main;
    }
    
    @FXML 
    public void startGame() throws IOException {
        main.changeToDungeon();
    }
    
    @FXML 
    public void quitGame() {
        main.quitGame();
    }

    @FXML
    public void restartLevel() throws IOException {
        main.changeToDungeon();
    }

    @FXML
    public void mainMenu() throws IOException {
        main.changeToMainMenu();
    }

    @FXML 
    public void changeSettings() {
        System.out.println("Changing Settings!");
    }
}
