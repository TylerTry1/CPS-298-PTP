package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        homePage(primaryStage);
    }

    private void homePage(Stage primaryStage) {
        Button play = new Button("Begin New Journey");
        Button tutorial = new Button("Tutorial");
        Button unlocks = new Button("Unlocks");
        Button stats = new Button("Stats");
        Button credits = new Button("Credits");
        Button quit = new Button("Exit Game");

        play.setOnAction(e -> playOptions(primaryStage));
        tutorial.setOnAction(e -> textTutorial(primaryStage));
        unlocks.setOnAction(e -> unlocks(primaryStage));
        stats.setOnAction(e -> stats(primaryStage));
        credits.setOnAction(e -> credits(primaryStage));
        quit.setOnAction(e -> Platform.exit());

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(play, tutorial, unlocks, stats, credits, quit);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(20));
        // Align the VBox to the bottom center
        buttonBox.setLayoutX(860); // Adjust this based on your screen width
        buttonBox.setLayoutY(700); // Adjust this based on your screen height
        // Load the background image
        Image image = new Image("/application/images/EndlessMountain16x9.png");
        // Define the background size and position
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

        // Use a Pane to allow free positioning
        Pane root = new Pane();
        root.getChildren().add(buttonBox);
        root.setBackground(new Background(backgroundImage));

        // Set button styles and sizes
        buttonBox.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1)); // Adjust the size as needed
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
        });

        Scene scene = new Scene(root, 1920, 1080); // screen size
        primaryStage.setTitle("Endless Mountain of Monsters");
        primaryStage.setScene(scene);
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
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
        });
        
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, 1920, 1080);
        
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
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 1920, 1080);

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
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 1920, 1080);

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
                ((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
                ((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
            }
        });

        root.setStyle("-fx-background-color: black;"); // Set background color to black
        Scene scene = new Scene(root, 1920, 1080);

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
        Scene scene = new Scene(root, 1920, 1080);

        primaryStage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
