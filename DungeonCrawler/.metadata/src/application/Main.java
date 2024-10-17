package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	// music tracks
	String path1 = getClass().getResource("/Music/DD1RuinsTheme.mp3").toExternalForm();
	Media mediaPath = new Media(path1);
	String path2 = getClass().getResource("/Music/MainMenu.mp3").toExternalForm();
	Media mediaPath2 = new Media(path2);

	@Override
	public void start(Stage primaryStage) {
		homePage(primaryStage);

	}

	private void homePage(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());

		Media media = new Media(path2);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.05); // Volume level (0.0 to 1.0)
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// First set of buttons
		Button play = new Button("Begin New Journey");
		Button tutorial = new Button("Tutorial");
		Button unlocks = new Button("Unlocks");
		Button stats = new Button("Stats");
		Button credits = new Button("Credits");
		Button quit = new Button("Exit Game");
		play.setOnAction(e -> {
			battleScene(primaryStage);
			mediaPlayer.stop();
		});

		tutorial.setOnAction(e -> {
			textTutorial(primaryStage);
			mediaPlayer.stop();
		});
		unlocks.setOnAction(e -> {
			unlocks(primaryStage);
			mediaPlayer.stop();
		});
		stats.setOnAction(e -> {
			stats(primaryStage);
			mediaPlayer.stop();
		});
		credits.setOnAction(e -> {
			credits(primaryStage);
			mediaPlayer.stop();

		});
		quit.setOnAction(e -> {
			Platform.exit();
			mediaPlayer.stop();
		});

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
		Image image = new Image("applicationImagesBackgrounds/EndlessMountain16x9.png");
		// Define the background size and position
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

		// Use a Pane to allow free positioning
		Pane root = new Pane();
		root.getChildren().addAll(playbutton, buttonBox2); // Add both button lists
		root.setBackground(new Background(backgroundImage));

		// Set button styles and sizes for both sets of buttons
		playbutton.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust the size
																									// as needed
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.08));
		});

		buttonBox2.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1)); // Adjust the size
																									// as needed
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
		});

		Scene scene = new Scene(root, 1920, 1080); // screen size
		scene.setCursor(customCursor);
		primaryStage.setTitle("Endless Mountain of Monsters");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
			mediaPlayer.stop(); // Stop the music when the stage is closed
		});
	}

	private void battleScene(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());

		// music player
		Media media = new Media(path1);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.05); // Volume level (0.0 to 1.0)
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button back = new Button("Back");
		Button heroPosition4 = new Button("heroPosition4");
		Button heroPosition3 = new Button("heroPosition3");
		Button heroPosition2 = new Button("heroPosition2");
		Button heroPosition1 = new Button("heroPosition1");
		ProgressBar heroHealthBar1 = new ProgressBar(1.0); // Initial progress value 0-1.0
		ProgressBar heroHealthBar2 = new ProgressBar(1.0);
		ProgressBar heroHealthBar3 = new ProgressBar(1.0);
		ProgressBar heroHealthBar4 = new ProgressBar(1.0);
		heroHealthBar1.setStyle("-fx-accent: red;");
		heroHealthBar2.setStyle("-fx-accent: red;");
		heroHealthBar3.setStyle("-fx-accent: red;");
		heroHealthBar4.setStyle("-fx-accent: red;");
		Button enemyPosition1 = new Button("enemyPosition1");
		Button enemyPosition2 = new Button("enemyPosition2");
		Button enemyPosition3 = new Button("enemyPosition3");
		Button enemyPosition4 = new Button("enemyPosition4");
		ProgressBar enemyHealthBar1 = new ProgressBar(1.0); // Initial progress value 0-1.0
		ProgressBar enemyHealthBar2 = new ProgressBar(1.0);
		ProgressBar enemyHealthBar3 = new ProgressBar(1.0);
		ProgressBar enemyHealthBar4 = new ProgressBar(1.0);
		Button skillbutton1 = new Button("skill 1");
		Button skillbutton2 = new Button("skill 2");
		Button skillbutton3 = new Button("skill 3");
		Button skillbutton4 = new Button("skill 4");
		Button movebutton = new Button("move");
		Button passTurnbutton = new Button("pass turn");

		Text heroName = new Text("Hero Name");
		Text enemyName = new Text("Enemy Name");

		// -------------------------------------------------------------

		// Create the HBoxes for hero and enemy positions
		HBox heroPositions = new HBox(50);
		heroPositions.getChildren().addAll(heroPosition4, heroPosition3, heroPosition2, heroPosition1);

		HBox heroHealthBars = new HBox(105);
		heroHealthBars.getChildren().addAll(heroHealthBar1, heroHealthBar2, heroHealthBar3, heroHealthBar4);

		HBox enemyPositions = new HBox(50);
		enemyPositions.getChildren().addAll(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);

		HBox enemyHealthBars = new HBox(105); // 105 is the sweet spot
		enemyHealthBars.getChildren().addAll(enemyHealthBar1, enemyHealthBar2, enemyHealthBar3, enemyHealthBar4);

		HBox skillButtons = new HBox(10);
		skillButtons.getChildren().addAll(skillbutton1, skillbutton2, skillbutton3, skillbutton4, movebutton,
				passTurnbutton);
		// -------------------------------------------------------------
		// Images for everything
		ImageView enemyInPosition1 = new ImageView(
				new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png"));
		ImageView enemyInPosition2 = new ImageView(
				new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png"));
		ImageView enemyInPosition3 = new ImageView(
				new Image("applicationImagesEnemySprites/Goblin Archer/Goblin_Archer_1_Idle.png"));
		ImageView enemyInPosition4 = new ImageView(
				new Image("applicationImagesEnemySprites/Goblin Shaman/Goblin_Shaman_1_Idle.png"));
		ImageView skillbuttonimage1 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png"));
		ImageView skillbuttonimage2 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png"));
		ImageView skillbuttonimage3 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png"));
		ImageView skillbuttonimage4 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png"));
		ImageView heroNamePlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView enemyNamePlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView heroSelectionIndicator4 = new ImageView(new Image("GUIAssets/characterSelectionIndicator.png"));
		ImageView heroSelectionIndicator3 = new ImageView(new Image("GUIAssets/characterSelectionIndicator.png"));
		ImageView heroSelectionIndicator2 = new ImageView(new Image("GUIAssets/characterSelectionIndicator.png"));
		ImageView heroSelectionIndicator1 = new ImageView(new Image("GUIAssets/characterSelectionIndicator.png"));
		ImageView enemySelectionIndicator1 = new ImageView(new Image("GUIAssets/enemySelectionIndicator.png"));
		ImageView enemySelectionIndicator2 = new ImageView(new Image("GUIAssets/enemySelectionIndicator.png"));
		ImageView enemySelectionIndicator3 = new ImageView(new Image("GUIAssets/enemySelectionIndicator.png"));
		ImageView enemySelectionIndicator4 = new ImageView(new Image("GUIAssets/enemySelectionIndicator.png"));

		Image backgroundImagesetup = new Image("combatBackgrounds/cryptsRoomWallDrain.png");
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background
																														// image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top
																											// left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);

		// -------------------------------------------------------------
		// Set the size of the images to match the button size
		enemyInPosition1.setFitWidth(0.08 * 1920); // Width relative to the screen size (1920x1080)
		enemyInPosition1.setFitHeight(0.3 * 1080); // Height relative to the screen size
		enemyInPosition2.setFitWidth(0.08 * 1920); // 153.6 pixels wide.
		enemyInPosition2.setFitHeight(0.3 * 1080);
		enemyInPosition3.setFitWidth(0.08 * 1920);
		enemyInPosition3.setFitHeight(0.3 * 1080);
		enemyInPosition4.setFitWidth(0.08 * 1920);
		enemyInPosition4.setFitHeight(0.3 * 1080);

		enemyHealthBar1.setStyle("-fx-accent: red;");
		enemyHealthBar2.setStyle("-fx-accent: red;");
		enemyHealthBar3.setStyle("-fx-accent: red;");
		enemyHealthBar4.setStyle("-fx-accent: red;");
		enemyInPosition1.setMouseTransparent(true);
		enemyInPosition2.setMouseTransparent(true);
		enemyInPosition3.setMouseTransparent(true);
		enemyInPosition4.setMouseTransparent(true);
		skillbuttonimage1.setMouseTransparent(true);
		skillbuttonimage2.setMouseTransparent(true);
		skillbuttonimage3.setMouseTransparent(true);
		skillbuttonimage4.setMouseTransparent(true);
		heroSelectionIndicator4.setMouseTransparent(true);
		heroSelectionIndicator3.setMouseTransparent(true);
		heroSelectionIndicator2.setMouseTransparent(true);
		heroSelectionIndicator1.setMouseTransparent(true);
		enemySelectionIndicator1.setMouseTransparent(true);
		enemySelectionIndicator2.setMouseTransparent(true);
		enemySelectionIndicator3.setMouseTransparent(true);
		enemySelectionIndicator4.setMouseTransparent(true);
		// -------------------------------------------------------------
		heroPosition1.setOnMouseEntered(e -> {
			heroSelectionIndicator1.setVisible(true); // Make the image appear on hover
		});
		heroPosition1.setOnMouseExited(e -> {
			heroSelectionIndicator1.setVisible(false); // Make the image appear on hover
		});
		heroSelectionIndicator1.setVisible(false);

		heroPosition2.setOnMouseEntered(e -> {
			heroSelectionIndicator2.setVisible(true); // Make the image appear on hover
		});
		heroPosition2.setOnMouseExited(e -> {
			heroSelectionIndicator2.setVisible(false); // Make the image appear on hover
		});
		heroSelectionIndicator2.setVisible(false);

		heroPosition3.setOnMouseEntered(e -> {
			heroSelectionIndicator3.setVisible(true); // Make the image appear on hover
		});
		heroPosition3.setOnMouseExited(e -> {
			heroSelectionIndicator3.setVisible(false); // Make the image appear on hover
		});
		heroSelectionIndicator3.setVisible(false);

		heroPosition4.setOnMouseEntered(e -> {
			heroSelectionIndicator4.setVisible(true); // Make the image appear on hover
		});
		heroPosition4.setOnMouseExited(e -> {
			heroSelectionIndicator4.setVisible(false); // Make the image appear on hover
		});
		heroSelectionIndicator4.setVisible(false);

		enemyPosition1.setOnMouseEntered(e -> {
			enemySelectionIndicator1.setVisible(true); // Make the image appear on hover
		});
		enemyPosition1.setOnMouseExited(e -> {
			enemySelectionIndicator1.setVisible(false); // Make the image appear on hover
		});
		enemySelectionIndicator1.setVisible(false);

		enemyPosition2.setOnMouseEntered(e -> {
			enemySelectionIndicator2.setVisible(true); // Make the image appear on hover
		});
		enemyPosition2.setOnMouseExited(e -> {
			enemySelectionIndicator2.setVisible(false); // Make the image appear on hover
		});
		enemySelectionIndicator2.setVisible(false);

		enemyPosition3.setOnMouseEntered(e -> {
			enemySelectionIndicator3.setVisible(true); // Make the image appear on hover
		});
		enemyPosition3.setOnMouseExited(e -> {
			enemySelectionIndicator3.setVisible(false); // Make the image appear on hover
		});
		enemySelectionIndicator3.setVisible(false);

		enemyPosition4.setOnMouseEntered(e -> {
			enemySelectionIndicator4.setVisible(true); // Make the image appear on hover
		});
		enemyPosition4.setOnMouseExited(e -> {
			enemySelectionIndicator4.setVisible(false); // Make the image appear on hover
		});
		enemySelectionIndicator4.setVisible(false);
		heroName.setFill(Color.WHITE);
		enemyName.setFill(Color.WHITE);
		// -------------------------------------------------------------

		skillbuttonimage1.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1.setFitHeight(0.09 * 1080);
		skillbuttonimage2.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2.setFitHeight(0.09 * 1080);
		skillbuttonimage3.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3.setFitHeight(0.09 * 1080);
		skillbuttonimage4.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4.setFitHeight(0.09 * 1080);
		// Create a Pane for free positioning
		Pane root = new Pane();

		// Add buttons, images, and back button to the root Pane
		root.getChildren().addAll(heroPositions, enemyPositions, back);
		root.getChildren().addAll(enemyInPosition1, enemyInPosition2, enemyInPosition3, enemyInPosition4);
		root.getChildren().add(heroHealthBars); // Add hero health bars to the root Pane
		root.getChildren().add(enemyHealthBars); // Add enemy health bars to the root Pane
		root.getChildren().add(skillButtons); // skill buttons
		root.getChildren().addAll(skillbuttonimage1, skillbuttonimage2, skillbuttonimage3, skillbuttonimage4); // these
																												// are
																												// the
																												// images
																												// for
																												// the
																												// skill
																												// buttons
		root.getChildren().addAll(heroNamePlate, enemyNamePlate);
		root.getChildren().addAll(heroName, enemyName);// keep name after name plate to avoid layering issues
		root.getChildren().addAll(heroSelectionIndicator1, heroSelectionIndicator2, heroSelectionIndicator3,
				heroSelectionIndicator4); // might need 4 of these.
		root.getChildren().addAll(enemySelectionIndicator1, enemySelectionIndicator2, enemySelectionIndicator3,
				enemySelectionIndicator4); // might need 4 of these.

		root.setBackground(new Background(backgroundImagePayoff));

		// -------------------------------------------------------------
		// Manually position the HBoxes and back button
		heroPositions.setLayoutX(125); // Position X for hero positions
		heroPositions.setLayoutY(250); // Position Y for hero positions

		enemyPositions.setLayoutX(1025); // Position X for enemy positions
		enemyPositions.setLayoutY(250); // Position Y for enemy positions

		enemyHealthBars.setLayoutX(1055); // Position health bar relative to enemies
		enemyHealthBars.setLayoutY(600); // Position health bar slightly below enemies

		heroHealthBars.setLayoutX(155); // Position health bar relative to heroes
		heroHealthBars.setLayoutY(600); // Position health bar slightly below heroes

		skillButtons.setLayoutX(585); // 585 sweet spot
		skillButtons.setLayoutY(775);

		heroName.setLayoutX(200); // hero's name
		heroName.setLayoutY(740);

		enemyName.setLayoutX(1660);
		enemyName.setLayoutY(740);

		heroNamePlate.setLayoutX(-5);
		heroNamePlate.setLayoutY(710);

		enemyNamePlate.setLayoutX(1415);
		enemyNamePlate.setLayoutY(710);
		enemyNamePlate.setScaleX(-1);
		// -------------------------------------------------------------

		heroSelectionIndicator1.setLayoutX(750);
		heroSelectionIndicator1.setLayoutY(560);
		heroSelectionIndicator2.setLayoutX(545);
		heroSelectionIndicator2.setLayoutY(560);
		heroSelectionIndicator3.setLayoutX(340);
		heroSelectionIndicator3.setLayoutY(560);
		heroSelectionIndicator4.setLayoutX(135);
		heroSelectionIndicator4.setLayoutY(560);
		// -------------------------------------------------------------

		enemySelectionIndicator1.setLayoutX(1047); // spacing of 205 between each
		enemySelectionIndicator1.setLayoutY(513);
		enemySelectionIndicator2.setLayoutX(1252);
		enemySelectionIndicator2.setLayoutY(513);
		enemySelectionIndicator3.setLayoutX(1457);
		enemySelectionIndicator3.setLayoutY(513);
		enemySelectionIndicator4.setLayoutX(1662);
		enemySelectionIndicator4.setLayoutY(513);

		back.setLayoutX(50); // Position X for back button
		back.setLayoutY(50); // Position Y for back button
		// -------------------------------------------------------------
		// Manually position the images on top of the buttons
		enemyInPosition1.setLayoutX(1025); // spacing of 205 in between each.
		enemyInPosition1.setLayoutY(250);
		enemyInPosition2.setLayoutX(1230);
		enemyInPosition2.setLayoutY(250);
		enemyInPosition3.setLayoutX(1435);
		enemyInPosition3.setLayoutY(250);
		enemyInPosition4.setLayoutX(1640);
		enemyInPosition4.setLayoutY(250);
		// -------------------------------------------------------------
		skillbuttonimage1.setLayoutX(586);
		skillbuttonimage1.setLayoutY(778);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2.setLayoutX(713);
		skillbuttonimage2.setLayoutY(778);
		skillbuttonimage3.setLayoutX(840);
		skillbuttonimage3.setLayoutY(778);
		skillbuttonimage4.setLayoutX(967);
		skillbuttonimage4.setLayoutY(778);
		// -------------------------------------------------------------
		// Set the button sizes for all buttons in HBoxes
		heroPositions.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
		});

		enemyPositions.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.08));
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.3));
		});
		skillButtons.getChildren().forEach(button -> {
			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});

		back.setOnAction(e -> {
			mediaPlayer.stop(); // Stop the music when the back button is pressed
			homePage(primaryStage);
		});

		Scene scene = new Scene(root, 1920, 1080); // Create a scene with the Pane
		scene.setCursor(customCursor);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(event -> {
			mediaPlayer.stop(); // Stop the music when the stage is closed
		});

	}

	// putting this on the sideline, in case we decide we need a play options tab.
	private void playOptions(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
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
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("This is where we will explain how to play the game.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
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
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}

	private void unlocks(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("This is where we list all the unlocks the player has completed.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
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
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}

	private void stats(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("This is where we will keep track of all the player's statistics.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
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
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}

	private void credits(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		Button back = new Button("Back");
		back.setOnAction(e -> homePage(primaryStage));

		// Create a Label for displaying text
		Label tutorialText = new Label("CPS 298, Alex, Charlie, Killian, Tyler.");
		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size

		VBox root = new VBox(10);
		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
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
		scene.setCursor(customCursor);

		primaryStage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
