package skins;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

public class SelectLevelButtonSkin extends ButtonSkin{

    public SelectLevelButtonSkin(Button control) {
        super(control);

        final FadeTransition fadeIn = new FadeTransition(Duration.millis(300));
        fadeIn.setNode(control);
        fadeIn.setToValue(1);
        
        final ScaleTransition scaleout = new ScaleTransition(Duration.millis(300));
        scaleout.setToX(1.2f);
        scaleout.setToY(1.2f);
        control.setOnMouseEntered(e -> {
            fadeIn.playFromStart();
            scaleout.playFromStart();
        });

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(300));
        fadeOut.setNode(control);
        fadeOut.setToValue(0.5);
        
        final ScaleTransition scalein = new ScaleTransition(Duration.millis(300));
        scalein.setToX(0.8f);
        scalein.setToY(0.8f);
        
        control.setOnMouseExited(e -> {
            fadeOut.playFromStart();
            scalein.playFromStart();
        });
        
        control.setOpacity(0.5);   
    }   
}