package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        MainMenuController menuController = new MainMenuController(primaryStage);
        FXMLLoader menuloader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        menuloader.setController(menuController);
        Parent menu_root = menuloader.load();
        Scene main_menu_scene = new Scene(menu_root);
        
        menu_root.requestFocus();
        primaryStage.setScene(main_menu_scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
