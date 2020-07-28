package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller {

    @FXML
    private GridPane squares;

    @FXML
    private VBox pauseMenu;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Timeline tickerTimeline;
    private Timeline dungeonEnd;

    private boolean isPaused;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.isPaused = false;
        setupTickerTimeline();
        setupDungeonEndTimeline();
        dungeon.subscribeController(this);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        tickerTimeline.play();
    }

    @FXML
    public void resume() {
        isPaused = false;
        pauseMenu.setVisible(false);
        tickerTimeline.play();
        squares.setEffect(null);
        squares.requestFocus();
    }

    public void pause() {
        pauseMenu.setVisible(true);
        tickerTimeline.pause();
        squares.setEffect(new BoxBlur());
        isPaused = true;
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (isPaused) {

            switch (event.getCode()) {
                case ESCAPE:
                    resume();
                    isPaused = false;
                    break;
                default:
                    break;
            }
            return;
        }
        switch (event.getCode()) {
            case UP:
                player.tryMoveUp();
                break;
            case DOWN:
                player.tryMoveDown();
                break;
            case LEFT:
                player.tryMoveLeft();
                break;
            case RIGHT:
                player.tryMoveRight();
                break;
            case ESCAPE:
                pause();
                break;
            default:
                break;
        }
        // tick();
    }

    public void gameWon() {
        dungeonEnd.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            try {
                main.changeToGameWon();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }));
        dungeonEnd.play();
    }
    
    public void gameLost() {
        dungeonEnd.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            try {
                main.changeToGameLost();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }));
        dungeonEnd.play();
    }
    
    
    private void setupTickerTimeline() {
        tickerTimeline = new Timeline();
        tickerTimeline.setCycleCount(Animation.INDEFINITE);
        tickerTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {tick();}));
    }
    
    private void setupDungeonEndTimeline() {
        dungeonEnd = new Timeline();
        dungeonEnd.setCycleCount(1);
    }
    
    public void tick() {
        dungeon.tick();
    }
}

