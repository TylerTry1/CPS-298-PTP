package application;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	// music tracks
	String combatMusic = getClass().getResource("/Music/DD1RuinsTheme.mp3").toExternalForm();
	Media mediaPath = new Media(combatMusic);
	String menuMusic = getClass().getResource("/Music/MainMenu.mp3").toExternalForm();
	Media mediaPath2 = new Media(menuMusic);
	String shopMusic = getClass().getResource("/Music/ShopTheme.mp3").toExternalForm();
	Media mediaPath3 = new Media(shopMusic);
	
	Font KingArthurLegend = Font.loadFont(getClass().getResourceAsStream("/fonts/KingArthurLegend.ttf"), 40);
	Font Ubuntu = Font.loadFont(getClass().getResourceAsStream("/fonts/UbuntuRegular.ttf"), 40);
	@Override
	public void start(Stage primaryStage) {
		homePage(primaryStage);

	}

	private void homePage(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());

		Media media = new Media(menuMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// First set of buttons

		Button play = new Button("Begin New Journey");
		Button tutorial = new Button("Tutorial");
		Button unlocks = new Button("Unlocks");
		Button stats = new Button("Stats");
		Button credits = new Button("Credits");
		Button quit = new Button("Exit Game");
		Button shop = new Button("Shop");
		
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
		shop.setOnAction(e -> {
			shop(primaryStage);
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

		VBox shopTesting = new VBox(10);
		shopTesting.getChildren().addAll(shop);
		shopTesting.setSpacing(10);
		shopTesting.setPadding(new Insets(20));
		shopTesting.setLayoutX(10); // Adjust X for second VBox
		shopTesting.setLayoutY(10); // Adjust Y for second VBox
		
		// Load the background image
		Image image = new Image("applicationImagesBackgrounds/EndlessMountain16x9.png");
		// Define the background size and position
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);

		// Use a Pane to allow free positioning
		Pane root = new Pane();
		root.getChildren().addAll(playbutton, buttonBox2, shopTesting); // Add both button lists
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
		Media media = new Media(combatMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button back = new Button("Back");
		Button heroPosition4 = new Button("heroPosition4");
		Button heroPosition3 = new Button("heroPosition3");
		Button heroPosition2 = new Button("heroPosition2");
		Button heroPosition1 = new Button("heroPosition1");
		Button enemyPosition1 = new Button("enemyPosition1");
		Button enemyPosition2 = new Button("enemyPosition2");
		Button enemyPosition3 = new Button("enemyPosition3");
		Button enemyPosition4 = new Button("enemyPosition4");

		Button skillbutton1 = new Button("skill 1");
		Button skillbutton2 = new Button("skill 2");
		Button skillbutton3 = new Button("skill 3");
		Button skillbutton4 = new Button("skill 4");
		Button movebutton = new Button("move");
		Button passTurnButton = new Button("pass turn");

		Text heroName = new Text("Hero Name");
		Text enemyName = new Text("Enemy Name");
		Text enemyBleedResistanceNumberText = new Text("BLD");
		Text enemyBlightResistanceNumberText = new Text("BLGT");
		Text enemyBurnResistanceNumberText = new Text("BRN");
		Text enemyStunResistanceNumberText = new Text("STN");
		Text enemyMoveResistanceNumberText = new Text("MOV");
		Text enemyDebuffResistanceNumberText = new Text("DBFF");
		Text enemyDeathResistanceNumberText = new Text("DTH");
		Text enemyBleedResistanceNumber = new Text("0"); // replace later with variables
		Text enemyBlightResistanceNumber= new Text("0");
		Text enemyBurnResistanceNumber= new Text("0");
		Text enemyStunResistanceNumber= new Text("0");
		Text enemyMoveResistanceNumber= new Text("0");
		Text enemyDebuffResistanceNumber= new Text("0");
		Text enemyDeathResistanceNumber= new Text("0");
		Text heroHPPos4 = new Text ("heroHPPos4");
		Text heroHPPos3 = new Text ("heroHPPos3");
		Text heroHPPos2 = new Text ("heroHPPos2");
		Text heroHPPos1 = new Text ("heroHPPos1");
		Text enemyHPPos1 = new Text ("enemyHPPos1");
		Text enemyHPPos2 = new Text ("enemyHPPos2");
		Text enemyHPPos3 = new Text ("enemyHPPos3");
		Text enemyHPPos4 = new Text ("enemyHPPos4");
		Text roundNumberText = new Text ("Round #");
		
		heroName.setFont(KingArthurLegend);
		heroName.setFill(Color.web("#4c4c4c"));
		enemyName.setFont(KingArthurLegend);
		enemyName.setFill(Color.web("#4c4c4c"));
		enemyBleedResistanceNumberText.setFont(Ubuntu);
		enemyBleedResistanceNumberText.setFill(Color.web("#bc1313"));
		enemyBlightResistanceNumberText.setFont(Ubuntu);
		enemyBlightResistanceNumberText.setFill(Color.web("#437c36"));
		enemyBurnResistanceNumberText.setFont(Ubuntu);
		enemyBurnResistanceNumberText.setFill(Color.web("#ca7430"));
		enemyStunResistanceNumberText.setFont(Ubuntu);
		enemyStunResistanceNumberText.setFill(Color.web("#cfc257"));
		enemyMoveResistanceNumberText.setFont(Ubuntu);
		enemyMoveResistanceNumberText.setFill(Color.web("#3f87b7"));
		enemyDebuffResistanceNumberText.setFont(Ubuntu);
		enemyDebuffResistanceNumberText.setFill(Color.web("#d5661b"));
		enemyDeathResistanceNumberText.setFont(Ubuntu);
		enemyDeathResistanceNumberText.setFill(Color.web("#8e0000"));
		
		enemyBleedResistanceNumber.setFont(Ubuntu);
		enemyBleedResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyBlightResistanceNumber.setFont(Ubuntu);
		enemyBlightResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyBurnResistanceNumber.setFont(Ubuntu);
		enemyBurnResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyStunResistanceNumber.setFont(Ubuntu);
		enemyStunResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyMoveResistanceNumber.setFont(Ubuntu);
		enemyMoveResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyDebuffResistanceNumber.setFont(Ubuntu);
		enemyDebuffResistanceNumber.setFill(Color.web("#4c4c4c"));
		enemyDeathResistanceNumber.setFont(Ubuntu);
		enemyDeathResistanceNumber.setFill(Color.web("#4c4c4c"));
		heroHPPos4.setFont(Ubuntu);
		heroHPPos4.setFill(Color.web("#bc1313"));
		heroHPPos3.setFont(Ubuntu);
		heroHPPos3.setFill(Color.web("#bc1313"));
		heroHPPos2.setFont(Ubuntu);
		heroHPPos2.setFill(Color.web("#bc1313"));
		heroHPPos1.setFont(Ubuntu);
		heroHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos1.setFont(Ubuntu);
		enemyHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos2.setFont(Ubuntu);
		enemyHPPos2.setFill(Color.web("#bc1313"));
		enemyHPPos3.setFont(Ubuntu);
		enemyHPPos3.setFill(Color.web("#bc1313"));
		enemyHPPos4.setFont(Ubuntu);
		enemyHPPos4.setFill(Color.web("#bc1313"));
		
		roundNumberText.setFont(KingArthurLegend);
		roundNumberText.setFill(Color.web("#4c4c4c"));
		// -------------------------------------------------------------

		Paint redToBlackGradient = new LinearGradient(
                0, 0, // Start at the top
                0, 1, // End at the bottom
                true, // proportional
                CycleMethod.NO_CYCLE, // Cycle method
                new Stop(0, Color.RED), // Start color (top)
                new Stop(1, Color.BLACK) // End color (bottom)
        );
		Paint whiteToBlackGradient = new LinearGradient(
				 0, 0, // Start at the top
	                0, 1, // End at the bottom
	                true, // proportional
	                CycleMethod.NO_CYCLE, // Cycle method
	                new Stop(0, Color.BLACK), // Start color (top)
	                new Stop(1, Color.rgb(50, 50, 50)) // End color (bottom, darker gray)
	        );
		Rectangle heroHealthBarRedRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color
		Rectangle heroHealthBarRedRectangle3 = new Rectangle(100, 15);
		Rectangle heroHealthBarRedRectangle2 = new Rectangle(100, 15);
		Rectangle heroHealthBarRedRectangle1 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color
		Rectangle heroHealthBarBlackRectangle3 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle2 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle2 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle3 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle4 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle2 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle3 = new Rectangle(100, 15);
		Rectangle enemyHealthBarBlackRectangle4 = new Rectangle(100, 15);
		heroHealthBarRedRectangle4.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle4.setArcWidth(5);
	    heroHealthBarRedRectangle4.setArcHeight(5);
		heroHealthBarRedRectangle3.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle3.setArcWidth(5);
	    heroHealthBarRedRectangle3.setArcHeight(5);
		heroHealthBarRedRectangle2.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle2.setArcWidth(5);
	    heroHealthBarRedRectangle2.setArcHeight(5);
		heroHealthBarRedRectangle1.setFill(redToBlackGradient);
	    heroHealthBarRedRectangle1.setArcWidth(5);
	    heroHealthBarRedRectangle1.setArcHeight(5);
	    heroHealthBarBlackRectangle4.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle4.setArcWidth(5);
	    heroHealthBarBlackRectangle4.setArcHeight(5);
	    heroHealthBarBlackRectangle3.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle3.setArcWidth(5);
	    heroHealthBarBlackRectangle3.setArcHeight(5);
	    heroHealthBarBlackRectangle2.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle2.setArcWidth(5);
	    heroHealthBarBlackRectangle2.setArcHeight(5);
	    heroHealthBarBlackRectangle1.setFill(whiteToBlackGradient);
	    heroHealthBarBlackRectangle1.setArcWidth(5);
	    heroHealthBarBlackRectangle1.setArcHeight(5);
	    enemyHealthBarRedRectangle1.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle1.setArcWidth(5);
	    enemyHealthBarRedRectangle1.setArcHeight(5);
	    enemyHealthBarRedRectangle2.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle2.setArcWidth(5);
	    enemyHealthBarRedRectangle2.setArcHeight(5);
	    enemyHealthBarRedRectangle3.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle3.setArcWidth(5);
	    enemyHealthBarRedRectangle3.setArcHeight(5);
	    enemyHealthBarRedRectangle4.setFill(redToBlackGradient);
	    enemyHealthBarRedRectangle4.setArcWidth(5);
	    enemyHealthBarRedRectangle4.setArcHeight(5);
	    enemyHealthBarBlackRectangle1.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle1.setArcWidth(5);
	    enemyHealthBarBlackRectangle1.setArcHeight(5);
	    enemyHealthBarBlackRectangle2.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle2.setArcWidth(5);
	    enemyHealthBarBlackRectangle2.setArcHeight(5);
	    enemyHealthBarBlackRectangle3.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle3.setArcWidth(5);
	    enemyHealthBarBlackRectangle3.setArcHeight(5);
	    enemyHealthBarBlackRectangle4.setFill(whiteToBlackGradient);
	    enemyHealthBarBlackRectangle4.setArcWidth(5);
	    enemyHealthBarBlackRectangle4.setArcHeight(5);
		// -------------------------------------------------------------
		// Images for everything
		ImageView enemyInPosition1 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png"));
		ImageView enemyInPosition2 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png"));
		ImageView enemyInPosition3 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Archer/Goblin_Archer_1_Idle.png"));
		ImageView enemyInPosition4 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Shaman/Goblin_Shaman_1_Idle.png"));
		
		ImageView heroInPosition1 = new ImageView(new Image("applicationImagesPlayerSprites/Paladin_Idle.png"));
		ImageView heroInPosition2 = new ImageView(new Image("applicationImagesPlayerSprites/Assassin_Idle.png"));
		ImageView heroInPosition3 = new ImageView(new Image("applicationImagesPlayerSprites/Wizard_Idle.png"));
		ImageView heroInPosition4 = new ImageView(new Image("applicationImagesPlayerSprites/Alchemist_Idle.png"));
		
		ImageView skillbuttonimage1 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png"));
		ImageView skillbuttonimage2 = new ImageView(new Image("abilityIconsPaladin/divineSmite.png"));
		ImageView skillbuttonimage3 = new ImageView(new Image("abilityIconsPaladin/auraOfCourage.png"));
		ImageView skillbuttonimage4 = new ImageView(new Image("abilityIconsPaladin/shieldOfFaith.png"));
		ImageView skillbuttonimagemove = new ImageView(new Image("GUIAssets/skillbuttonimagemove.png"));
		ImageView skillbuttonimagepass = new ImageView(new Image("GUIAssets/skillbuttonimagepass.png"));
		ImageView heroNamePlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView enemyNamePlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView turnOrderBarLeftAndRight = new ImageView(new Image("GUIAssets/turnOrderBarLeftAndRight.png"));
		ImageView heroSelectionIndicator4 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator3 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator2 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator1 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator1 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator2 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator3 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator4 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		
		ImageView heroTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView heroTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView heroTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView heroTurnTicker4 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView enemyTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView enemyTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView enemyTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png"));
		ImageView enemyTurnTicker4= new ImageView(new Image("GUIAssets/turnticker.png"));
		
		ImageView enemyBLDResistanceIcon = new ImageView(new Image("GUIAssets/BLDResistance.png"));
		ImageView enemyBLGTResistanceIcon = new ImageView(new Image("GUIAssets/BLGTResistance.png"));
		ImageView enemyBURNResistanceIcon = new ImageView(new Image("GUIAssets/BURNResistance.png"));
		ImageView enemySTNResistanceIcon = new ImageView(new Image("GUIAssets/STNResistance.png"));
		ImageView enemyMOVResistanceIcon = new ImageView(new Image("GUIAssets/MOVResistance.png"));
		ImageView enemyDBFFResistanceIcon = new ImageView(new Image("GUIAssets/DBFFResistance.png"));
		ImageView enemyDTHResistanceIcon = new ImageView(new Image("GUIAssets/DTHResistance.png"));
		
		
		
		Image backgroundImagesetup = new Image("combatBackgrounds/cryptsRoomWallDrain.png");
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background
																														// image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top
																											// left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);

		HBox enemyResistances = new HBox(50);
		enemyResistances.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon );
		HBox heroPositions = new HBox(50);
		heroPositions.getChildren().addAll(heroPosition4, heroPosition3, heroPosition2, heroPosition1);
		HBox enemyPositions = new HBox(50);
		enemyPositions.getChildren().addAll(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);
		HBox skillButtons = new HBox(10);
		skillButtons.getChildren().addAll(skillbutton1, skillbutton2, skillbutton3, skillbutton4, movebutton);
		HBox heroHealthBarRedRectangles = new HBox(105);
		heroHealthBarRedRectangles.getChildren().addAll(heroHealthBarRedRectangle4,heroHealthBarRedRectangle3,heroHealthBarRedRectangle2,heroHealthBarRedRectangle1);
		HBox heroHealthBarBlackRectangles = new HBox(105);
		heroHealthBarBlackRectangles.getChildren().addAll(heroHealthBarBlackRectangle4,heroHealthBarBlackRectangle3,heroHealthBarBlackRectangle2,heroHealthBarBlackRectangle1);
		HBox enemyHealthBarRedRectangles = new HBox(105);
		enemyHealthBarRedRectangles.getChildren().addAll(enemyHealthBarRedRectangle1,enemyHealthBarRedRectangle2,enemyHealthBarRedRectangle3,enemyHealthBarRedRectangle4);
		HBox enemyHealthBarBlackRectangles = new HBox(105);
		enemyHealthBarBlackRectangles.getChildren().addAll(enemyHealthBarBlackRectangle1,enemyHealthBarBlackRectangle2,enemyHealthBarBlackRectangle3,enemyHealthBarBlackRectangle4);
		// -------------------------------------------------------------
		// Set the size of the images to match the button size
		enemyInPosition1.setScaleX(.75);
		enemyInPosition1.setScaleY(.85); 
		enemyInPosition2.setScaleX(.75);
		enemyInPosition2.setScaleY(.85);
		enemyInPosition3.setScaleX(.75);
		enemyInPosition3.setScaleY(.85);
		enemyInPosition4.setScaleX(1);
		enemyInPosition4.setScaleY(1.1);
		heroInPosition1.setScaleX(.55);
		heroInPosition1.setScaleY(.65); 
		heroInPosition2.setScaleX(.45);
		heroInPosition2.setScaleY(.45);
		heroInPosition3.setScaleX(.65);
		heroInPosition3.setScaleY(.65);
		heroInPosition4.setScaleX(.55);
		heroInPosition4.setScaleY(.55);
		skillbuttonimage1.setFitWidth(0.06 * 1920); // placeholder 16:9 aspect ratio
		skillbuttonimage1.setFitHeight(0.09 * 1080);
		skillbuttonimage2.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage2.setFitHeight(0.09 * 1080);
		skillbuttonimage3.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage3.setFitHeight(0.09 * 1080);
		skillbuttonimage4.setFitWidth(0.06 * 1920); // placeholder
		skillbuttonimage4.setFitHeight(0.09 * 1080);
		skillbuttonimagemove.setFitWidth(0.06 * 1920);
		skillbuttonimagemove.setFitHeight(0.051 * 1920);
		// -------------------------------------------------------------
		enemyBLDResistanceIcon.setFitWidth(0.03 * 1920); 
		enemyBLDResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyBLGTResistanceIcon.setFitWidth(0.03 * 1920);
		enemyBLGTResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyBURNResistanceIcon.setFitWidth(0.03 * 1920);
		enemyBURNResistanceIcon.setFitHeight(0.03 * 1080); 
		enemySTNResistanceIcon.setFitWidth(0.03 * 1920);
		enemySTNResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyMOVResistanceIcon.setFitWidth(0.03 * 1920);
		enemyMOVResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyDBFFResistanceIcon.setFitWidth(0.03 * 1920);
		enemyDBFFResistanceIcon.setFitHeight(0.03 * 1080); 
		enemyDTHResistanceIcon.setFitWidth(0.03 * 1920);
		enemyDTHResistanceIcon.setFitHeight(0.03 * 1080); 
		// -------------------------------------------------------------
		enemyInPosition1.setMouseTransparent(true);
		enemyInPosition2.setMouseTransparent(true);
		enemyInPosition3.setMouseTransparent(true);
		enemyInPosition4.setMouseTransparent(true);
		heroInPosition1.setMouseTransparent(true);
		heroInPosition2.setMouseTransparent(true);
		heroInPosition3.setMouseTransparent(true);
		heroInPosition4.setMouseTransparent(true);
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
		skillbuttonimagemove.setMouseTransparent(true);
		skillbuttonimagepass.setMouseTransparent(true);
		
		heroPosition4.setVisible(false);
		heroPosition3.setVisible(false);
		heroPosition2.setVisible(false);
		heroPosition1.setVisible(false);
		enemyPosition1.setVisible(false);
		enemyPosition2.setVisible(false);
		enemyPosition3.setVisible(false);
		enemyPosition4.setVisible(false);
		
		skillbutton1.setVisible(false);
		skillbutton2.setVisible(false);
		skillbutton3.setVisible(false);
		skillbutton4.setVisible(false);
		movebutton.setVisible(false);
		passTurnButton.setVisible(false);
		
		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		heroPosition1.setOnMouseEntered(e -> {heroSelectionIndicator1.setVisible(true);});
		heroPosition1.setOnMouseExited(e -> {heroSelectionIndicator1.setVisible(false);});
		heroSelectionIndicator1.setVisible(false);
		
		heroPosition2.setOnMouseEntered(e -> {heroSelectionIndicator2.setVisible(true);});
		heroPosition2.setOnMouseExited(e -> {heroSelectionIndicator2.setVisible(false); });
		heroSelectionIndicator2.setVisible(false);

		heroPosition3.setOnMouseEntered(e -> {heroSelectionIndicator3.setVisible(true);});
		heroPosition3.setOnMouseExited(e -> {heroSelectionIndicator3.setVisible(false);});
		heroSelectionIndicator3.setVisible(false);

		heroPosition4.setOnMouseEntered(e -> {heroSelectionIndicator4.setVisible(true);});
		heroPosition4.setOnMouseExited(e -> {heroSelectionIndicator4.setVisible(false);});
		heroSelectionIndicator4.setVisible(false);
		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		enemyPosition1.setOnMouseEntered(e -> {enemySelectionIndicator1.setVisible(true);});
		enemyPosition1.setOnMouseExited(e -> {enemySelectionIndicator1.setVisible(false);});
		enemySelectionIndicator1.setVisible(false);

		enemyPosition2.setOnMouseEntered(e -> {enemySelectionIndicator2.setVisible(true);});
		enemyPosition2.setOnMouseExited(e -> {enemySelectionIndicator2.setVisible(false);});
		enemySelectionIndicator2.setVisible(false);

		enemyPosition3.setOnMouseEntered(e -> {enemySelectionIndicator3.setVisible(true);});
		enemyPosition3.setOnMouseExited(e -> {enemySelectionIndicator3.setVisible(false);});
		enemySelectionIndicator3.setVisible(false);

		enemyPosition4.setOnMouseEntered(e -> {enemySelectionIndicator4.setVisible(true);});
		enemyPosition4.setOnMouseExited(e -> {enemySelectionIndicator4.setVisible(false); });
		enemySelectionIndicator4.setVisible(false);
		// -------------------------------------------------------------
		
		heroHealthBarRedRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);});
		heroHealthBarRedRectangle4.setOnMouseExited(e -> {heroHPPos4.setVisible(false); });
		heroHPPos4.setVisible(false);
		heroHealthBarBlackRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);});
		heroHealthBarBlackRectangle4.setOnMouseExited(e -> {heroHPPos4.setVisible(false); });
		heroHPPos4.setVisible(false);
		 
		heroHealthBarRedRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);});
		heroHealthBarRedRectangle3.setOnMouseExited(e -> {heroHPPos3.setVisible(false); });
		heroHPPos3.setVisible(false);
		heroHealthBarBlackRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);});
		heroHealthBarBlackRectangle3.setOnMouseExited(e -> {heroHPPos3.setVisible(false); });
		heroHPPos3.setVisible(false);
		
		heroHealthBarRedRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);});
		heroHealthBarRedRectangle2.setOnMouseExited(e -> {heroHPPos2.setVisible(false); });
		heroHPPos2.setVisible(false);
		heroHealthBarBlackRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);});
		heroHealthBarBlackRectangle2.setOnMouseExited(e -> {heroHPPos2.setVisible(false); });
		heroHPPos2.setVisible(false);
		
		heroHealthBarRedRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);});
		heroHealthBarRedRectangle1.setOnMouseExited(e -> {heroHPPos1.setVisible(false); });
		heroHPPos1.setVisible(false);
		heroHealthBarBlackRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);});
		heroHealthBarBlackRectangle1.setOnMouseExited(e -> {heroHPPos1.setVisible(false); });
		heroHPPos1.setVisible(false);
		
		
		enemyHealthBarRedRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);});
		enemyHealthBarRedRectangle4.setOnMouseExited(e -> {enemyHPPos4.setVisible(false); });
		enemyHPPos4.setVisible(false);
		enemyHealthBarBlackRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);});
		enemyHealthBarBlackRectangle4.setOnMouseExited(e -> {enemyHPPos4.setVisible(false); });
		enemyHPPos4.setVisible(false);
		
		enemyHealthBarRedRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);});
		enemyHealthBarRedRectangle3.setOnMouseExited(e -> {enemyHPPos3.setVisible(false); });
		enemyHPPos3.setVisible(false);
		enemyHealthBarBlackRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);});
		enemyHealthBarBlackRectangle3.setOnMouseExited(e -> {enemyHPPos3.setVisible(false); });
		enemyHPPos3.setVisible(false);
		
		enemyHealthBarRedRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);});
		enemyHealthBarRedRectangle2.setOnMouseExited(e -> {enemyHPPos2.setVisible(false); });
		enemyHPPos2.setVisible(false);
		enemyHealthBarBlackRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);});
		enemyHealthBarBlackRectangle2.setOnMouseExited(e -> {enemyHPPos2.setVisible(false); });
		enemyHPPos2.setVisible(false);
		
		enemyHealthBarRedRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);});
		enemyHealthBarRedRectangle1.setOnMouseExited(e -> {enemyHPPos1.setVisible(false); });
		enemyHPPos1.setVisible(false);
		enemyHealthBarBlackRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);});
		enemyHealthBarBlackRectangle1.setOnMouseExited(e -> {enemyHPPos1.setVisible(false); });
		enemyHPPos1.setVisible(false);
		// -------------------------------------------------------------

		// Create a Pane for free positioning
		Pane root = new Pane();

		// Add buttons, images, and back button to the root Pane
		root.getChildren().addAll(heroPositions, enemyPositions, back);
		root.getChildren().addAll(enemyInPosition1, enemyInPosition2, enemyInPosition3, enemyInPosition4);
		root.getChildren().addAll(heroInPosition1, heroInPosition2, heroInPosition3, heroInPosition4);
		root.getChildren().addAll(heroTurnTicker1, heroTurnTicker2, heroTurnTicker3, heroTurnTicker4);
		root.getChildren().addAll(enemyTurnTicker1, enemyTurnTicker2, enemyTurnTicker3, enemyTurnTicker4);
		root.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon);
		root.getChildren().addAll(enemyBleedResistanceNumberText, enemyBlightResistanceNumberText, enemyBurnResistanceNumberText, enemyStunResistanceNumberText, enemyMoveResistanceNumberText, enemyDebuffResistanceNumberText, enemyDeathResistanceNumberText);
		root.getChildren().addAll(enemyBleedResistanceNumber,enemyBlightResistanceNumber,enemyBurnResistanceNumber,enemyStunResistanceNumber,enemyMoveResistanceNumber,enemyDebuffResistanceNumber, enemyDeathResistanceNumber);
		root.getChildren().add(skillButtons); 
		root.getChildren().add(passTurnButton); 
		root.getChildren().addAll(skillbuttonimage1, skillbuttonimage2, skillbuttonimage3, skillbuttonimage4, skillbuttonimagemove, skillbuttonimagepass); 																						
		root.getChildren().addAll(heroNamePlate, enemyNamePlate);
		root.getChildren().add(turnOrderBarLeftAndRight);
		root.getChildren().addAll(heroName, enemyName);// keep name after name plate to avoid layering issues
		root.getChildren().add(roundNumberText);
		root.getChildren().addAll(heroSelectionIndicator1, heroSelectionIndicator2, heroSelectionIndicator3,heroSelectionIndicator4); 
		root.getChildren().addAll(enemySelectionIndicator1, enemySelectionIndicator2, enemySelectionIndicator3, enemySelectionIndicator4); // might need 4 of these.
		root.getChildren().add(heroHealthBarBlackRectangles);
		root.getChildren().add(heroHealthBarRedRectangles);
		root.getChildren().add(enemyHealthBarBlackRectangles);
		root.getChildren().add(enemyHealthBarRedRectangles);
		root.getChildren().addAll(heroHPPos4,heroHPPos3,heroHPPos2,heroHPPos1);
		root.getChildren().addAll(enemyHPPos4,enemyHPPos3,enemyHPPos2,enemyHPPos1);
		
		root.setBackground(new Background(backgroundImagePayoff)); // set background image

		// -------------------------------------------------------------
		// Manually position the HBoxes and back button
		heroPositions.setLayoutX(125); // Position X for hero positions
		heroPositions.setLayoutY(250); // Position Y for hero positions

		enemyPositions.setLayoutX(1025); // Position X for enemy positions
		enemyPositions.setLayoutY(250); // Position Y for enemy positions

		skillButtons.setLayoutX(585); // 585 sweet spot
		skillButtons.setLayoutY(775);
		
		passTurnButton.setLayoutX(1222);
		passTurnButton.setLayoutY(775);
		
		heroName.setLayoutX(180); // hero's name
		heroName.setLayoutY(750);
		heroName.setScaleX(.65);
		heroName.setScaleY(.65);
		
		roundNumberText.setLayoutX(1340);
		roundNumberText.setLayoutY(45);
		roundNumberText.setScaleX(.95);
		roundNumberText.setScaleY(.95);
		
		enemyName.setLayoutX(1460);
		enemyName.setLayoutY(750);
		enemyName.setScaleX(.65);
		enemyName.setScaleY(.65);
		
		heroNamePlate.setLayoutX(-5);
		heroNamePlate.setLayoutY(710);

		enemyNamePlate.setLayoutX(1415);
		enemyNamePlate.setLayoutY(710);
		enemyNamePlate.setScaleX(-1);
		
		turnOrderBarLeftAndRight.setLayoutX(0);
		turnOrderBarLeftAndRight.setLayoutY(0);
		// -------------------------------------------------------------
		//health bars
		heroHealthBarRedRectangles.setLayoutX(155);
		heroHealthBarRedRectangles.setLayoutY(600);
		
		heroHealthBarBlackRectangles.setLayoutX(155);
		heroHealthBarBlackRectangles.setLayoutY(600);

		enemyHealthBarRedRectangles.setLayoutX(1055);
		enemyHealthBarRedRectangles.setLayoutY(600);
	
		enemyHealthBarBlackRectangles.setLayoutX(1055);
		enemyHealthBarBlackRectangles.setLayoutY(600);
		// -------------------------------------------------------------
		heroTurnTicker1.setLayoutX(255);// 205 spacing
		heroTurnTicker1.setLayoutY(595);
		heroTurnTicker2.setLayoutX(460); 
		heroTurnTicker2.setLayoutY(595);
		heroTurnTicker3.setLayoutX(665);
		heroTurnTicker3.setLayoutY(595);
		heroTurnTicker4.setLayoutX(870);
		heroTurnTicker4.setLayoutY(595);
		// -------------------------------------------------------------
		enemyTurnTicker1.setLayoutX(1155); // 205 spacing
		enemyTurnTicker1.setLayoutY(595); 
		enemyTurnTicker2.setLayoutX(1360);
		enemyTurnTicker2.setLayoutY(595);
		enemyTurnTicker3.setLayoutX(1565);
		enemyTurnTicker3.setLayoutY(595);
		enemyTurnTicker4.setLayoutX(1770);
		enemyTurnTicker4.setLayoutY(595);
		// -------------------------------------------------------------
		enemyBLDResistanceIcon.setLayoutX(1412); // these 3 assets are weird, so need eyeball'd placements.
		enemyBLDResistanceIcon.setLayoutY(952); 
		enemyBLGTResistanceIcon.setLayoutX(1480); 
		enemyBLGTResistanceIcon.setLayoutY(953); 
		enemyBURNResistanceIcon.setLayoutX(1564);
		enemyBURNResistanceIcon.setLayoutY(950); 
		
		enemySTNResistanceIcon.setLayoutX(1621);//75 spacing
		enemySTNResistanceIcon.setLayoutY(950); 
		enemyMOVResistanceIcon.setLayoutX(1695);
		enemyMOVResistanceIcon.setLayoutY(950); 
		enemyDBFFResistanceIcon.setLayoutX(1776);
		enemyDBFFResistanceIcon.setLayoutY(950); 
		enemyDTHResistanceIcon.setLayoutX(1852); 
		enemyDTHResistanceIcon.setLayoutY(950); 
		
		enemyBleedResistanceNumberText.setLayoutX(1390);
		enemyBleedResistanceNumberText.setLayoutY(940); 
		enemyBlightResistanceNumberText.setLayoutX(1456);
		enemyBlightResistanceNumberText.setLayoutY(940); 
		enemyBurnResistanceNumberText.setLayoutX(1541);
		enemyBurnResistanceNumberText.setLayoutY(940); 
		enemyStunResistanceNumberText.setLayoutX(1616);
		enemyStunResistanceNumberText.setLayoutY(940); 
		enemyMoveResistanceNumberText.setLayoutX(1678);
		enemyMoveResistanceNumberText.setLayoutY(940); 
		enemyDebuffResistanceNumberText.setLayoutX(1753);
		enemyDebuffResistanceNumberText.setLayoutY(940); 
		enemyDeathResistanceNumberText.setLayoutX(1842);
		enemyDeathResistanceNumberText.setLayoutY(940); 
		
		enemyBleedResistanceNumberText.setScaleX(.65);
		enemyBleedResistanceNumberText.setScaleY(.65); 
		enemyBlightResistanceNumberText.setScaleX(.65);
		enemyBlightResistanceNumberText.setScaleY(.65); 
		enemyBurnResistanceNumberText.setScaleX(.65);
		enemyBurnResistanceNumberText.setScaleY(.65); 
		enemyStunResistanceNumberText.setScaleX(.65);
		enemyStunResistanceNumberText.setScaleY(.65); 
		enemyMoveResistanceNumberText.setScaleX(.65);
		enemyMoveResistanceNumberText.setScaleY(.65); 
		enemyDebuffResistanceNumberText.setScaleX(.65);
		enemyDebuffResistanceNumberText.setScaleY(.65); 
		enemyDeathResistanceNumberText.setScaleX(.65);
		enemyDeathResistanceNumberText.setScaleY(.65); 
		
		enemyBleedResistanceNumber.setLayoutX(1415); // 77 spacing
		enemyBleedResistanceNumber.setLayoutY(1020);
		enemyBleedResistanceNumber.setScaleX(.75);
		enemyBleedResistanceNumber.setScaleY(.75); 
		enemyBlightResistanceNumber.setLayoutX(1492);
		enemyBlightResistanceNumber.setLayoutY(1020); 
		enemyBlightResistanceNumber.setScaleX(.75);
		enemyBlightResistanceNumber.setScaleY(.75); 
		enemyBurnResistanceNumber.setLayoutX(1569);
		enemyBurnResistanceNumber.setLayoutY(1020); 
		enemyBurnResistanceNumber.setScaleX(.75);
		enemyBurnResistanceNumber.setScaleY(.75); 
		enemyStunResistanceNumber.setLayoutX(1639);
		enemyStunResistanceNumber.setLayoutY(1020); 
		enemyStunResistanceNumber.setScaleX(.75);
		enemyStunResistanceNumber.setScaleY(.75); 
		enemyMoveResistanceNumber.setLayoutX(1716);
		enemyMoveResistanceNumber.setLayoutY(1020); 
		enemyMoveResistanceNumber.setScaleX(.75);
		enemyMoveResistanceNumber.setScaleY(.75); 
		enemyDebuffResistanceNumber.setLayoutX(1793);
		enemyDebuffResistanceNumber.setLayoutY(1020); 
		enemyDebuffResistanceNumber.setScaleX(.75);
		enemyDebuffResistanceNumber.setScaleY(.75); 
		enemyDeathResistanceNumber.setLayoutX(1870);
		enemyDeathResistanceNumber.setLayoutY(1020); 
		enemyDeathResistanceNumber.setScaleX(.75);
		enemyDeathResistanceNumber.setScaleY(.75); 
		
		
		heroHPPos4.setLayoutX(92); 
		heroHPPos4.setLayoutY(650); 
		heroHPPos4.setScaleX(.5); 
		heroHPPos4.setScaleY(.5); 
		
		heroHPPos3.setLayoutX(297); // space 205
		heroHPPos3.setLayoutY(650);
		heroHPPos3.setScaleX(.5); 
		heroHPPos3.setScaleY(.5); 
		
		heroHPPos2.setLayoutX(503); 
		heroHPPos2.setLayoutY(650); 
		heroHPPos2.setScaleX(.5); 
		heroHPPos2.setScaleY(.5); 
		
		heroHPPos1.setLayoutX(708); 
		heroHPPos1.setLayoutY(650);  
		heroHPPos1.setScaleX(.5); 
		heroHPPos1.setScaleY(.5); 
		
		enemyHPPos1.setLayoutX(975); 
		enemyHPPos1.setLayoutY(650);   
		enemyHPPos1.setScaleX(.5); 
		enemyHPPos1.setScaleY(.5); 
		enemyHPPos2.setLayoutX(1180); 
		enemyHPPos2.setLayoutY(650);  
		enemyHPPos2.setScaleX(.5); 
		enemyHPPos2.setScaleY(.5); 
		enemyHPPos3.setLayoutX(1385); 
		enemyHPPos3.setLayoutY(650);  
		enemyHPPos3.setScaleX(.5); 
		enemyHPPos3.setScaleY(.5); 
		enemyHPPos4.setLayoutX(1590); 
		enemyHPPos4.setLayoutY(650); 
		enemyHPPos4.setScaleX(.5); 
		enemyHPPos4.setScaleY(.5); 
		// -------------------------------------------------------------
		heroSelectionIndicator1.setLayoutX(733);
		heroSelectionIndicator1.setLayoutY(420);
		heroSelectionIndicator2.setLayoutX(528);
		heroSelectionIndicator2.setLayoutY(420);
		heroSelectionIndicator3.setLayoutX(323);
		heroSelectionIndicator3.setLayoutY(420);
		heroSelectionIndicator4.setLayoutX(118);
		heroSelectionIndicator4.setLayoutY(420);
		// -------------------------------------------------------------
		enemySelectionIndicator1.setLayoutX(1019); // spacing of 205 between each
		enemySelectionIndicator1.setLayoutY(423);
		enemySelectionIndicator2.setLayoutX(1224);
		enemySelectionIndicator2.setLayoutY(423);
		enemySelectionIndicator3.setLayoutX(1429);
		enemySelectionIndicator3.setLayoutY(423);
		enemySelectionIndicator4.setLayoutX(1634);
		enemySelectionIndicator4.setLayoutY(423);
		// -------------------------------------------------------------
		back.setLayoutX(50); // Position X for back button
		back.setLayoutY(50); // Position Y for back button
		// -------------------------------------------------------------
		// Manually position the images on top of the buttons
		enemyInPosition1.setLayoutX(875); // spacing of 205 in between each.
		enemyInPosition1.setLayoutY(190);
		enemyInPosition2.setLayoutX(1080);
		enemyInPosition2.setLayoutY(190);
		enemyInPosition3.setLayoutX(1235);
		enemyInPosition3.setLayoutY(190);
		enemyInPosition4.setLayoutX(1460);
		enemyInPosition4.setLayoutY(155);
		// -------------------------------------------------------------
		heroInPosition1.setLayoutX(585); // spacing of 205 in between each.
		heroInPosition1.setLayoutY(190);
		heroInPosition2.setLayoutX(380);
		heroInPosition2.setLayoutY(260);
		heroInPosition3.setLayoutX(145);
		heroInPosition3.setLayoutY(200);
		heroInPosition4.setLayoutX(-50);
		heroInPosition4.setLayoutY(210);
		// -------------------------------------------------------------
		skillbuttonimage1.setLayoutX(586);
		skillbuttonimage1.setLayoutY(778);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2.setLayoutX(713);
		skillbuttonimage2.setLayoutY(778);
		skillbuttonimage3.setLayoutX(840);
		skillbuttonimage3.setLayoutY(778);
		skillbuttonimage4.setLayoutX(967);
		skillbuttonimage4.setLayoutY(778);
		skillbuttonimagemove.setLayoutX(1094);
		skillbuttonimagemove.setLayoutY(778);
		skillbuttonimagepass.setLayoutX(1227);
		skillbuttonimagepass.setLayoutY(791);
		skillbuttonimagepass.setScaleX(1.35);
		skillbuttonimagepass.setScaleY(1.35);
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
		passTurnButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.015));
		passTurnButton.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));

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
		
		
		enemyTeam enemyTeam1 = new enemyTeam(4,4);
		Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams(4,4,15);
		enemyTeam1 = enemyTeamsArray.createTeams(6);
		enemyTeamsArray = enemyTeamsArray.createSelection(); // This Function is the current problem...
		
		
		//Random rand = new Random();
		//int teamSelect = rand.nextInt(15);
		
		combatFlow flow = new combatFlow();
		flow.determineTurnOrder();
		flow.runCombat();

	}
	private void shop(Stage primaryStage) {
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		
		// music player
		Media media = new Media(shopMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0), use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button back = new Button("Back");
		Text itemDescriptions = new Text("Item Descriptions will go at the top here");
		Text itemImages = new Text("item images should go here, item descriptions should show up when hovered over.");
		itemDescriptions.setFont(KingArthurLegend);
		itemImages.setFont(KingArthurLegend);
		
		
		ImageView shopKeeper = new ImageView(new Image("shopAssets/shopKeeper.png"));
		itemDescriptions.setFill(Color.web("#4c4c4c"));
		itemImages.setFill(Color.web("#4c4c4c"));
		Image backgroundImagesetup = new Image("shopAssets/shopBackground.png");
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background																									// image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top																					// left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);
		
		shopKeeper.setFitWidth(0.55 * 1920);
		shopKeeper.setFitHeight(0.7 * 1080);
		// Create a Pane for free positioning
		Pane root = new Pane();

		// Add buttons, images, and back button to the root Pane
		root.getChildren().add(back);
		root.getChildren().add(shopKeeper); 	
		root.getChildren().addAll(itemDescriptions, itemImages);

		root.setBackground(new Background(backgroundImagePayoff)); // set background image

		// -------------------------------------------------------------
	

		back.setLayoutX(50); // Position X for back button
		back.setLayoutY(50); // Position Y for back button
		itemDescriptions.setLayoutX(1080);
		itemDescriptions.setLayoutY(50);
		itemImages.setLayoutX(1080);
		itemImages.setLayoutY(1000);
		// -------------------------------------------------------------
		shopKeeper.setLayoutX(20);
		shopKeeper.setLayoutY(200);

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
