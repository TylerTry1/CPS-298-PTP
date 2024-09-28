package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        homePage(primaryStage);
    }

    private void homePage(Stage primaryStage) {
        Button play = new Button("Play");
        Button tutorial = new Button("Tutorial");
        Button unlocks = new Button("Unlocks");
        Button stats = new Button("Stats");
        Button credits = new Button("Credits");
        Button quit = new Button("Quit");

        play.setOnAction(e -> playOptions(primaryStage));
        tutorial.setOnAction(e -> textTutorial(primaryStage));
        unlocks.setOnAction(e -> unlocks(primaryStage));
        stats.setOnAction(e -> stats(primaryStage));
        credits.setOnAction(e -> credits(primaryStage));
        quit.setOnAction(e -> Platform.exit());

        VBox root = new VBox(10);
        root.getChildren().addAll(play, tutorial, unlocks, stats, credits, quit);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        root.setStyle("-fx-background-color: black;");

        root.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25)); // width of the menu buttons
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1)); // height of the menu buttons
        });

        VBox.setVgrow(play, Priority.ALWAYS);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void playOptions(Stage primaryStage) {
        Button back = new Button("Back");

        back.setOnAction(e -> homePage(primaryStage));

        VBox root = new VBox(10);
        root.getChildren().addAll(back);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Bind button width to 40% of the scene's width and height
        root.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
        });
        
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, 500, 500);
        
//        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
//        primaryStage.requestFocus();
    }

    private void textTutorial(Stage primaryStage) {
        Button back = new Button("Back");
        back.setOnAction(e -> homePage(primaryStage));

        // Create a Label for displaying text
        Label tutorialText = new Label("This is where we will explain how to play the game.");
        tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

        VBox root = new VBox(10);
        root.getChildren().addAll(tutorialText, back);  // Add the Label above the Back button
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Bind button size properties to scale with the window
        root.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setScene(scene);
    }
    
    private void unlocks(Stage primaryStage) {
    	Button back = new Button("Back");
        back.setOnAction(e -> homePage(primaryStage));

        // Create a Label for displaying text
        Label tutorialText = new Label("This is where we list all the unlocks the player has completed.");
        tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

        VBox root = new VBox(10);
        root.getChildren().addAll(tutorialText, back);  // Add the Label above the Back button
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Bind button size properties to scale with the window
        root.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setScene(scene);
    }
    
    private void stats(Stage primaryStage) {
    	Button back = new Button("Back");
        back.setOnAction(e -> homePage(primaryStage));

        // Create a Label for displaying text
        Label tutorialText = new Label("This is where we will keep track of all the player's statistics.");
        tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

        VBox root = new VBox(10);
        root.getChildren().addAll(tutorialText, back);  // Add the Label above the Back button
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Bind button size properties to scale with the window
        root.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setScene(scene);
    }
    
    private void credits(Stage primaryStage) {
    	Button back = new Button("Back");
        back.setOnAction(e -> homePage(primaryStage));

        // Create a Label for displaying text
        Label tutorialText = new Label("CPS 298, Alex, Charlie, Killian, Tyler.");
        tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

        VBox root = new VBox(10);
        root.getChildren().addAll(tutorialText, back);  // Add the Label above the Back button
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Bind button size properties to scale with the window
        root.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.1));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
