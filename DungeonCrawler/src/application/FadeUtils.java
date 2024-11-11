package application;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeUtils {

    public static FadeTransition createFade(Node node, double fromValue, double toValue, int durationMillis) {
        FadeTransition fade = new FadeTransition(Duration.millis(durationMillis), node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        return fade;
    }

    // Method to handle transitions between multiple Stages with a loading effect
    public static void transitionBetweenStages(Stage currentStage, Stage loadingStage, Runnable nextStageMethod) {
        // Step 1: Fade out the current stage
        Scene currentScene = currentStage.getScene();
        FadeTransition fadeOutCurrent = createFade(currentScene.getRoot(), 1.0, 0.0, 1200);

        fadeOutCurrent.setOnFinished(event -> {
            currentStage.hide(); // Hide the current stage after fading out

            // Step 2: Show the loading stage with fade-in
            loadingStage.show();
            Scene loadingScene = loadingStage.getScene();
            FadeTransition fadeInLoading = createFade(loadingScene.getRoot(), 0.0, 1.0, 1200);

            fadeInLoading.setOnFinished(event2 -> {
                // Pause for a moment on the loading screen
                pauseAndSwitchToNextStage(loadingStage, nextStageMethod);
            });

            fadeInLoading.play();
        });

        fadeOutCurrent.play();
    }

    // Helper method to pause and then switch to the new stage
    private static void pauseAndSwitchToNextStage(Stage loadingStage, Runnable nextStageMethod) {
        // Create a fade-out effect for the loading screen
        Scene scene = loadingStage.getScene();
        FadeTransition fadeOutLoading = createFade(scene.getRoot(), 1.0, 0.0, 1200);

        fadeOutLoading.setOnFinished(event -> {
            loadingStage.hide(); // Hide the loading stage
            Platform.runLater(nextStageMethod); // Execute the next stage method
        });

        fadeOutLoading.play();
    }
}
