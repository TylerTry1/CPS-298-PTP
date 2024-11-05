package application;
import javafx.animation.FadeTransition; // Import the JavaFX FadeTransition
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeUtils { // Renamed to avoid conflict

    public static FadeTransition createFade(Node node, double fromValue, double toValue, int durationMillis) {
        FadeTransition fade = new FadeTransition(Duration.millis(durationMillis), node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        return fade;
    }
}
