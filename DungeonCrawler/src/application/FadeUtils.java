package application;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeUtils {

    // Existing fade transition utility method
    public static FadeTransition createFade(Node node, double fromValue, double toValue, int durationMillis) {
        FadeTransition fade = new FadeTransition(Duration.millis(durationMillis), node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        return fade;
    }

    // New method for custom animation (big -> shrink -> hold -> disappear)
    
    
    public static void deathblow(Node node) {
        // Step 1: Scale transition (big -> shrink)
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), node);
        scaleTransition.setFromX(1.5); // Initial size
        scaleTransition.setFromY(1.5);
        scaleTransition.setToX(1.0); // End size
        scaleTransition.setToY(1.0);

        // Step 2: Pause transition (hold)
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(1000)); // Duration visible

        // Step 3: Fade transition (disappear)
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node); // Duration of fade-out
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        // Combine all transitions
        SequentialTransition sequentialTransition = new SequentialTransition(
                scaleTransition,
                pauseTransition,
                fadeTransition
        );

        sequentialTransition.setOnFinished(event -> {
            // Optionally remove the node or perform cleanup here
            node.setVisible(false); // Example: Make the node invisible after animation
        });

        // Play the animation
        sequentialTransition.play();
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
    public static SequentialTransition ddCheck(Node node) {
        // Step 1: Scale and fade-in transition
        ScaleTransition scaleInTransition = new ScaleTransition(Duration.millis(200), node);
        scaleInTransition.setFromX(3.0); // Start at 3x scale
        scaleInTransition.setFromY(3.0);
        scaleInTransition.setToX(.6); // Scale down to 1x
        scaleInTransition.setToY(.6);

        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(200), node);
        fadeInTransition.setFromValue(0.0); // Fade in from invisible
        fadeInTransition.setToValue(1.0); // Fully visible

        ParallelTransition fadeAndScaleIn = new ParallelTransition(scaleInTransition, fadeInTransition);

        // Step 2: Pause transition (hold)
        PauseTransition holdTransition = new PauseTransition(Duration.millis(1500));

        // Step 3: Fade-out transition
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(200), node);
        fadeOutTransition.setFromValue(1.0); // Fully visible
        fadeOutTransition.setToValue(0.0); // Fade out to invisible

        // Combine all transitions
        SequentialTransition sequentialTransition = new SequentialTransition(
                fadeAndScaleIn,
                holdTransition,
                fadeOutTransition
        );

        sequentialTransition.setOnFinished(event -> {
            // Reset node state if needed
            node.setVisible(false); // Example: Hide the node after animation
        });

        // Play the animation
        sequentialTransition.play();

        return sequentialTransition; // Return the animation in case further actions are needed
    }

}
