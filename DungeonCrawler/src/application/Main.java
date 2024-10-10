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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        homePage(primaryStage);
    }

    private void homePage(Stage primaryStage) {
        // First set of buttons
        Button play = new Button("Begin New Journey");
        Button tutorial = new Button("Tutorial");
        Button unlocks = new Button("Unlocks");
        Button stats = new Button("Stats");
        Button credits = new Button("Credits");
        Button quit = new Button("Exit Game");

        play.setOnAction(e -> battleScene(primaryStage));
        tutorial.setOnAction(e -> textTutorial(primaryStage));
        unlocks.setOnAction(e -> unlocks(primaryStage));
        stats.setOnAction(e -> stats(primaryStage));
        credits.setOnAction(e -> credits(primaryStage));
        quit.setOnAction(e -> Platform.exit());


        // VBox 1 for first set of buttons
        VBox playbutton = new VBox(10);
        playbutton.getChildren().addAll(play);
        playbutton.setSpacing(10);
        playbutton.setPadding(new Insets(20));
        playbutton.setLayoutX(768); // Adjust X for play button
        playbutton.setLayoutY(930); // Adjust Y for play button

        // VBox 2 for second set of buttons
        VBox buttonBox2 = new VBox(10);
        buttonBox2.getChildren().addAll(tutorial, unlocks, stats, credits, quit);
        buttonBox2.setSpacing(10);
        buttonBox2.setPadding(new Insets(20));
        buttonBox2.setLayoutX(7); // Adjust X for second VBox
        buttonBox2.setLayoutY(750); // Adjust Y for second VBox

        // Load the background image
        Image image = new Image("/application/images/EndlessMountain16x9.png");
        // Define the background size and position
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

        // Use a Pane to allow free positioning
        Pane root = new Pane();
        root.getChildren().addAll(playbutton, buttonBox2); // Add both button lists
        root.setBackground(new Background(backgroundImage));

        // Set button styles and sizes for both sets of buttons
        playbutton.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust the size as needed
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.08));
        });

        buttonBox2.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1)); // Adjust the size as needed
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
        });

        Scene scene = new Scene(root, 1920, 1080); // screen size
        primaryStage.setTitle("Endless Mountain of Monsters");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void battleScene(Stage primaryStage) {
        Button back = new Button("Back");

        Button heroPosition4 = new Button("heroPosition4");
        Button heroPosition3 = new Button("heroPosition3");
        Button heroPosition2 = new Button("heroPosition2");
        Button heroPosition1 = new Button("heroPosition1");

        Button enemyPosition1 = new Button("enemyPosition1");
        Button enemyPosition2 = new Button("enemyPosition2");
        Button enemyPosition3 = new Button("enemyPosition3");
        Button enemyPosition4 = new Button("enemyPosition4");

        back.setOnAction(e -> homePage(primaryStage));

        // Create the HBoxes for hero and enemy positions
        HBox heroPositions = new HBox(10);
        heroPositions.getChildren().addAll(heroPosition4, heroPosition3, heroPosition2, heroPosition1);

        HBox enemyPositions = new HBox(10);
        enemyPositions.getChildren().addAll(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);

        // Create a Pane for free positioning
        Pane root = new Pane();

        // Add the HBoxes and back button to the Pane
        root.getChildren().addAll(heroPositions, enemyPositions, back);

        // Manually position the HBoxes and the back button
        heroPositions.setLayoutX(100);  // Position X for hero positions
        heroPositions.setLayoutY(250);  // Position Y for hero positions

        enemyPositions.setLayoutX(1200);  // Position X for enemy positions
        enemyPositions.setLayoutY(250);   // Position Y for enemy positions

        back.setLayoutX(50);  // Position X for back button
        back.setLayoutY(50);  // Position Y for back button

        // Set the button sizes for all buttons in HBoxes
        heroPositions.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
        });

        enemyPositions.getChildren().forEach(button -> {
            ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
            ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
        });
//        root.getChildren().forEach(button -> {
//        	
//        }
        root.setStyle("-fx-background-color: black;");  // Set the background color

        Scene scene = new Scene(root, 1920, 1080);  // Create a scene with the Pane

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // putting this on the sideline, in case we decide we need a play options tab.
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
