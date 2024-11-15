package application;

import java.util.Timer;
import java.util.TimerTask;

import application.combatFlow.combatControl;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	private Stage loadingStage;
	// music tracks
	
	String combatMusic = getClass().getResource("/Music/DD1RuinsTheme.mp3").toExternalForm();
	Media mediaPath = new Media(combatMusic);
	String menuMusic = getClass().getResource("/Music/MainMenu.mp3").toExternalForm();
	Media mediaPath2 = new Media(menuMusic);
	String shopMusic = getClass().getResource("/Music/ShopTheme.mp3").toExternalForm();
	Media mediaPath3 = new Media(shopMusic);
	String gameOver =  getClass().getResource("/Music/gameOver.mp3").toExternalForm();
	Media mediaPath4 = new Media(gameOver);
	int count = 0; // For use in combat
	int fightsWon = 0;
	
	Font KingArthurLegend = Font.loadFont(getClass().getResourceAsStream("/fonts/KingArthurLegend.ttf"), 40);
	Font Ubuntu = Font.loadFont(getClass().getResourceAsStream("/fonts/UbuntuRegular.ttf"), 40);
	Font DwarvenAxe = Font.loadFont(getClass().getResourceAsStream("/fonts/DwarvenAxe.ttf"), 40);
	@Override
	public void start(Stage primaryStage) {
//		setupLoadingStage(primaryStage);
		initialization(primaryStage);
//		shop(primaryStage);
//		gameOver(primaryStage);
	}

	private void initialization(Stage primaryStage) { // this scene is used specifically so that we can make our application full screen.
	    Image cursorImage = new Image("GUIAssets/cursor.png");

	    Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
	    ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
	    Button play = new Button("Enter Game");
	    Text enterGameText = new Text ("Enter Game");
	    enterGameText.setFont(DwarvenAxe);
	    enterGameText.setFill(Color.web("#d5d5d5"));
	    play.setOnAction(e -> {homePage(primaryStage);});
//        FadeUtils.transitionBetweenStages(primaryStage, loadingStage, () -> homePage(primaryStage));});
	    // VBox 1 for first set of buttons
	    VBox playbutton = new VBox(10);
	    playbutton.getChildren().addAll(play);
	    
	    enterGame.setLayoutX(310);
	    enterGame.setLayoutY(635); 
	    enterGame.setScaleX(.5);
	    enterGame.setScaleY(.8);
	    enterGame.setRotate(90);
	    enterGame.setMouseTransparent(true);
	    
	    enterGameText.setLayoutX(910);
	    enterGameText.setLayoutY(975); 
	    enterGameText.setScaleX(2);
	    enterGameText.setScaleY(2);
	    enterGameText.setMouseTransparent(true);
	    
	    playbutton.setSpacing(10);
	    playbutton.setPadding(new Insets(20));
	    playbutton.setLayoutX(768); // Adjust X for play button
	    playbutton.setLayoutY(880); // Adjust Y for play button
	    playbutton.setOpacity(0);
	    
	    playbutton.setOnMouseEntered(e -> {enterGameText.setFill(Color.web("#d10000"));});
	    playbutton.setOnMouseExited(e -> {enterGameText.setFill(Color.web("#d5d5d5"));});

	    Image image = new Image("applicationImagesBackgrounds/EndlessMountain16x9.png");
	    BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
	    
	    Pane root = new Pane();
	    root.getChildren().addAll(enterGame);
	    root.getChildren().addAll(playbutton); // Add both button lists
	    root.getChildren().addAll(enterGameText);
	    
	    root.setBackground(new Background(backgroundImage));
	    // Set button styles and sizes for both sets of buttons
	    playbutton.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.12));});
	    Scene scene = new Scene(root, 1920, 1080); // Screen size
	    // Ensure the stage stays in fullscreen mode when the scene changes
	    primaryStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
	    primaryStage.setMaximized(true);});
	    scene.setCursor(customCursor);
	    primaryStage.setTitle("Endless Mountain of Monsters");
	    primaryStage.initStyle(StageStyle.UNDECORATED); // Set only once, before showing the stage
	    primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	private void homePage(Stage primaryStage) {
	    Image cursorImage = new Image("GUIAssets/cursor.png");
	    ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
	    Cursor customCursor = Cursor.cursor(cursorImage.getUrl());

	    Media media = new Media(menuMusic);
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
	    mediaPlayer.play(); // music player

	    // First set of buttons
	    Button play = new Button("Begin New Journey");
	    Button tutorial = new Button("Tutorial");
//	    Button unlocks = new Button("Unlocks");
//	    Button stats = new Button("Stats");
	    Button credits = new Button("Credits");
	    Button quit = new Button("Exit Game");
	    Button shop = new Button("Shop");
	    Button gameOver = new Button ("Game Over Sample");

	    Text enterGameText = new Text ("Begin New Journey");
	    
	    enterGameText.setFont(DwarvenAxe);
	    enterGameText.setFill(Color.web("#d5d5d5"));
	    
	    enterGame.setLayoutX(310);
	    enterGame.setLayoutY(635); 
	    enterGame.setScaleX(.5);
	    enterGame.setScaleY(.8);
	    enterGame.setRotate(90);
	    enterGame.setMouseTransparent(true);
//	    enterGame.setVisible(false);
	    
	    enterGameText.setLayoutX(855);
	    enterGameText.setLayoutY(975); 
	    enterGameText.setScaleX(1.4);
	    enterGameText.setScaleY(2);
	    enterGameText.setMouseTransparent(true);
	    
	    
	    
	    play.setOnMouseEntered(e -> {enterGameText.setFill(Color.web("#d10000"));});
	    play.setOnMouseExited(e -> {enterGameText.setFill(Color.web("#d5d5d5"));});
	    play.setOnAction(e -> {
	        battleScene(primaryStage);
	        mediaPlayer.stop();
	    });

	    tutorial.setOnAction(e -> {
	        textTutorial(primaryStage);
	        mediaPlayer.stop();
	    });
//	    unlocks.setOnAction(e -> {
//	        unlocks(primaryStage);
//	        mediaPlayer.stop();
//	    });
//	    stats.setOnAction(e -> {
//	        stats(primaryStage);
//	        mediaPlayer.stop();
//	    });
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
	    gameOver.setOnAction(e -> {
	        gameOver(primaryStage);
	        mediaPlayer.stop();
	    });

	    // VBox 1 for first set of buttons
	    VBox playbutton = new VBox(10);
	    playbutton.getChildren().addAll(play);
	    playbutton.setSpacing(10);
	    playbutton.setPadding(new Insets(20));
	    playbutton.setLayoutX(768); // Adjust X for play button
	    playbutton.setLayoutY(900); // Adjust Y for play button
	    playbutton.setScaleX(1.05);
	    playbutton.setScaleY(1.6);
	    playbutton.setOpacity(0);

	    // VBox 2 for second set of buttons
	    VBox buttonBox2 = new VBox(10);
	    buttonBox2.getChildren().addAll(tutorial, credits, quit); // unlocks, stats,
	    buttonBox2.setSpacing(10);
	    buttonBox2.setPadding(new Insets(20));
	    buttonBox2.setLayoutX(7); // Adjust X for second VBox
	    buttonBox2.setLayoutY(880); // Adjust Y for second VBox

	    VBox shopTesting = new VBox(10);
	    shopTesting.getChildren().addAll(shop, gameOver);
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
	 // Add both button lists
	    root.getChildren().addAll(enterGame);
	    root.getChildren().addAll(enterGameText);
	    root.getChildren().addAll(playbutton, buttonBox2, shopTesting, gameOver);
	    
	    
	    root.setBackground(new Background(backgroundImage));
	    

	    // Set button styles and sizes for both sets of buttons
	    playbutton.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.2)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.08));
	    });

	    buttonBox2.getChildren().forEach(button -> {
	        ((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1)); // Adjust size
	        ((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
	    });

	    Scene scene = new Scene(root, 1920, 1080); // Screen size

	    // Ensure the stage stays in fullscreen mode when the scene changes
	    primaryStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
	    });

	    scene.setCursor(customCursor);
	    primaryStage.setTitle("Endless Mountain of Monsters");
	    primaryStage.setMaximized(true);
	    primaryStage.setScene(scene);
	    primaryStage.show();

	    // Stop the music when the stage is closed
	    primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());
	}


	private void battleScene(Stage primaryStage) {
		
		
		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		
		String heroName = "hero name Variable"; // this will be used for our hero's name to change. // var
		String enemyName = "enemy name Variable"; // var
		String moveName = "moveName";
		
		// music player
		Media media = new Media(combatMusic);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.00); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.play(); // music player

		// Create buttons for everything
		Button heroPosition4 = new Button("heroPosition4");
		Button heroPosition3 = new Button("heroPosition3");
		Button heroPosition2 = new Button("heroPosition2");
		Button heroPosition1 = new Button("heroPosition1");
		Button enemyPosition1 = new Button("enemyPosition1");
		Button enemyPosition2 = new Button("enemyPosition2");
		Button enemyPosition3 = new Button("enemyPosition3");
		Button enemyPosition4 = new Button("enemyPosition4");

		ToggleGroup skillButtonGroup = new ToggleGroup();
		RadioButton skillbutton1 = new RadioButton("skill 1");
		skillbutton1.setToggleGroup(skillButtonGroup);
		RadioButton skillbutton2 = new RadioButton("skill 2");
		skillbutton2.setToggleGroup(skillButtonGroup);
		RadioButton skillbutton3 = new RadioButton("skill 3");
		skillbutton3.setToggleGroup(skillButtonGroup);
		RadioButton skillbutton4 = new RadioButton("skill 4");
		skillbutton4.setToggleGroup(skillButtonGroup);
//		RadioButton movebutton = new RadioButton("move");
//		movebutton.setToggleGroup(skillButtonGroup);
		RadioButton passTurnButton = new RadioButton("pass turn");
		passTurnButton.setToggleGroup(skillButtonGroup);
		 
		Button menuBackButton = new Button ("Back");
		Button menuQuitButton = new Button ("Quit Game");
		
		Text heroNameText = new Text(heroName);
		Text enemyNameText = new Text(enemyName);
		Text enemyBleedResistanceNumberText = new Text("BLD");
		Text enemyBlightResistanceNumberText = new Text("BLGT");
		Text enemyBurnResistanceNumberText = new Text("BRN");
		Text enemyStunResistanceNumberText = new Text("STN");
		Text enemyMoveResistanceNumberText = new Text("MOV");
		Text enemyDebuffResistanceNumberText = new Text("DBFF");
		Text enemyDeathResistanceNumberText = new Text("DTH");
		Text enemyBleedResistanceNumber = new Text("0"); // var
		Text enemyBlightResistanceNumber= new Text("0"); // var
		Text enemyBurnResistanceNumber= new Text("0"); // var
		Text enemyStunResistanceNumber= new Text("0"); // var
		Text enemyMoveResistanceNumber= new Text("0"); // var
		Text enemyDebuffResistanceNumber= new Text("0"); // var
		Text enemyDeathResistanceNumber= new Text("0"); // var
		Text heroHPPos4 = new Text ("heroHPPos4"); // var
		Text heroHPPos3 = new Text ("heroHPPos3"); // var
		Text heroHPPos2 = new Text ("heroHPPos2"); // var
		Text heroHPPos1 = new Text ("heroHPPos1"); // var
		Text enemyHPPos1 = new Text ("enemyHPPos1"); // var
		Text enemyHPPos2 = new Text ("enemyHPPos2"); // var
		Text enemyHPPos3 = new Text ("enemyHPPos3"); // var
		Text enemyHPPos4 = new Text ("enemyHPPos4"); // var
		Text roundNumberText = new Text ("Round" ); // var + roundCounter
		
		Text moveDescriptionText = new Text ("DMG:  CRIT:(critpercent)"); // var
		Text moveNameText = new Text (moveName); // var
		
		Text menuQuitGameText = new Text ("Quit Game");
		
		heroNameText.setFont(KingArthurLegend);
		heroNameText.setFill(Color.web("#FFEB80"));
		enemyNameText.setFont(KingArthurLegend);
		enemyNameText.setFill(Color.web("d10000"));
		enemyBleedResistanceNumberText.setFont(DwarvenAxe);
		enemyBleedResistanceNumberText.setFill(Color.web("#bc1313"));
		enemyBlightResistanceNumberText.setFont(DwarvenAxe);
		enemyBlightResistanceNumberText.setFill(Color.web("#437c36"));
		enemyBurnResistanceNumberText.setFont(DwarvenAxe);
		enemyBurnResistanceNumberText.setFill(Color.web("#ca7430"));
		enemyStunResistanceNumberText.setFont(DwarvenAxe);
		enemyStunResistanceNumberText.setFill(Color.web("#cfc257"));
		enemyMoveResistanceNumberText.setFont(DwarvenAxe);
		enemyMoveResistanceNumberText.setFill(Color.web("#3f87b7"));
		enemyDebuffResistanceNumberText.setFont(DwarvenAxe);
		enemyDebuffResistanceNumberText.setFill(Color.web("#d5661b"));
		enemyDeathResistanceNumberText.setFont(DwarvenAxe);
		enemyDeathResistanceNumberText.setFill(Color.web("#8e0000"));
		roundNumberText.setFont(KingArthurLegend);
		roundNumberText.setFill(Color.web("#d5d5d5"));
		enemyBleedResistanceNumber.setFont(DwarvenAxe);
		enemyBleedResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyBlightResistanceNumber.setFont(DwarvenAxe);
		enemyBlightResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyBurnResistanceNumber.setFont(DwarvenAxe);
		enemyBurnResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyStunResistanceNumber.setFont(DwarvenAxe);
		enemyStunResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyMoveResistanceNumber.setFont(DwarvenAxe);
		enemyMoveResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyDebuffResistanceNumber.setFont(DwarvenAxe);
		enemyDebuffResistanceNumber.setFill(Color.web("#d5d5d5"));
		enemyDeathResistanceNumber.setFont(DwarvenAxe);
		enemyDeathResistanceNumber.setFill(Color.web("#d5d5d5"));
		heroHPPos4.setFont(DwarvenAxe);
		heroHPPos4.setFill(Color.web("#bc1313"));
		heroHPPos3.setFont(DwarvenAxe);
		heroHPPos3.setFill(Color.web("#bc1313"));
		heroHPPos2.setFont(DwarvenAxe);
		heroHPPos2.setFill(Color.web("#bc1313"));
		heroHPPos1.setFont(DwarvenAxe);
		heroHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos1.setFont(DwarvenAxe);
		enemyHPPos1.setFill(Color.web("#bc1313"));
		enemyHPPos2.setFont(DwarvenAxe);
		enemyHPPos2.setFill(Color.web("#bc1313"));
		enemyHPPos3.setFont(DwarvenAxe);
		enemyHPPos3.setFill(Color.web("#bc1313"));
		enemyHPPos4.setFont(DwarvenAxe);
		enemyHPPos4.setFill(Color.web("#bc1313"));
		moveDescriptionText.setFont(DwarvenAxe); 
		moveDescriptionText.setFill(Color.web("#d5d5d5"));
		moveNameText.setFont(DwarvenAxe); 
		moveNameText.setFill(Color.web("#d5d5d5"));
		moveNameText.setTextAlignment(TextAlignment.CENTER);
		
		menuQuitGameText.setFont(KingArthurLegend);
		menuQuitGameText.setFill(Color.web("#cfc257"));
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
		BoxBlur blur = new BoxBlur(10, 10, 3);
		Rectangle heroHealthBarRedRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color // var
		Rectangle heroHealthBarRedRectangle3 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarRedRectangle2 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarRedRectangle1 = new Rectangle(100, 15); // var
		Rectangle heroHealthBarBlackRectangle4 = new Rectangle(100, 15); // Width, Height, Fill Color
		Rectangle heroHealthBarBlackRectangle3 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle2 = new Rectangle(100, 15);
		Rectangle heroHealthBarBlackRectangle1 = new Rectangle(100, 15);
		Rectangle enemyHealthBarRedRectangle1 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle2 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle3 = new Rectangle(100, 15); // var
		Rectangle enemyHealthBarRedRectangle4 = new Rectangle(100, 15); // var
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
		ImageView enemyInPosition1 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png")); // var
		ImageView enemyInPosition2 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Axeman/Goblin_axeman_1_Idle.png")); // var
		ImageView enemyInPosition3 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Archer/Goblin_Archer_1_Idle.png")); // var
		ImageView enemyInPosition4 = new ImageView(new Image("applicationImagesEnemySprites/Goblin Shaman/Goblin_Shaman_1_Idle.png")); // var
		
		ImageView heroInPosition1 = new ImageView(new Image("applicationImagesPlayerSprites/Paladin_Idle.png"));
		ImageView heroInPosition2 = new ImageView(new Image("applicationImagesPlayerSprites/Assassin_Idle.png"));
		ImageView heroInPosition3 = new ImageView(new Image("applicationImagesPlayerSprites/Wizard_Idle.png"));
		ImageView heroInPosition4 = new ImageView(new Image("applicationImagesPlayerSprites/Alchemist_Idle.png"));
		
		ImageView skillbuttonimage1 = new ImageView(new Image("abilityIconsPaladin/holy_rampart.png")); // var
		ImageView skillbuttonimage2 = new ImageView(new Image("abilityIconsPaladin/divineSmite.png")); // var
		ImageView skillbuttonimage3 = new ImageView(new Image("abilityIconsPaladin/auraOfCourage.png")); // var
		ImageView skillbuttonimage4 = new ImageView(new Image("abilityIconsPaladin/shieldOfFaith.png")); // var
//		ImageView skillbuttonimagemove = new ImageView(new Image("GUIAssets/skillbuttonimagemove.png"));
		ImageView skillbuttonimagepass = new ImageView(new Image("GUIAssets/skillbuttonimagepass.png"));
		ImageView heroNameTextPlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView enemyNameTextPlate = new ImageView(new Image("GUIAssets/nameplate.png"));
		ImageView turnOrderBarLeftAndRight = new ImageView(new Image("GUIAssets/turnOrderBarLeftAndRight.png"));
		ImageView heroSelectionIndicator4 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator3 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator2 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView heroSelectionIndicator1 = new ImageView(new Image("GUIAssets/CharacterSelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator1 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator2 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator3 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		ImageView enemySelectionIndicator4 = new ImageView(new Image("GUIAssets/enemySelectionIndicatorSize1.png"));
		
		ImageView heroTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView heroTurnTicker4 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker1 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker2 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker3 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView enemyTurnTicker4 = new ImageView(new Image("GUIAssets/turnticker.png")); // var
		ImageView skillButtonSelectedFrame1 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame2 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame3 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView skillButtonSelectedFrame4 = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
//		ImageView skillButtonMoveSelectedFrame = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		
		ImageView enemyBLDResistanceIcon = new ImageView(new Image("GUIAssets/BLDResistance.png"));
		ImageView enemyBLGTResistanceIcon = new ImageView(new Image("GUIAssets/BLGTResistance.png"));
		ImageView enemyBURNResistanceIcon = new ImageView(new Image("GUIAssets/BURNResistance.png"));
		ImageView enemySTNResistanceIcon = new ImageView(new Image("GUIAssets/STNResistance.png"));
		ImageView enemyMOVResistanceIcon = new ImageView(new Image("GUIAssets/MOVResistance.png"));
		ImageView enemyDBFFResistanceIcon = new ImageView(new Image("GUIAssets/DBFFResistance.png"));
		ImageView enemyDTHResistanceIcon = new ImageView(new Image("GUIAssets/DTHResistance.png"));
		
		ImageView menuBackground = new ImageView(new Image("GUIAssets/menuBackground.png"));
		ImageView menuBackButtonImage = new ImageView(new Image("GUIAssets/menuBackButtonImage.png"));
		ImageView menuButtonFrame1 = new ImageView(new Image("GUIAssets/menuButtonFrame.png"));
		
		ImageView enemyAttackingBloodStain  = new ImageView(new Image("GUIAssets/enemyAttackingBloodStain.png"));
		ImageView heroAttackingBloodStain  = new ImageView(new Image("GUIAssets/heroAttackingBloodStain.png"));
		
		Image backgroundImagesetup = new Image("combatBackgrounds/cryptsRoomWallDrain.png");  // var
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);
		
		FadeTransition fadeInHeroHPPos1 = FadeUtils.createFade(heroHPPos1, 0, 1, 200);
		FadeTransition fadeInHeroHPPos2 = FadeUtils.createFade(heroHPPos2, 0, 1, 200);
		FadeTransition fadeInHeroHPPos3 = FadeUtils.createFade(heroHPPos3, 0, 1, 200);
		FadeTransition fadeInHeroHPPos4 = FadeUtils.createFade(heroHPPos4, 0, 1, 200);
		FadeTransition fadeOutHeroHPPos1 = FadeUtils.createFade(heroHPPos1, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos2 = FadeUtils.createFade(heroHPPos2, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos3 = FadeUtils.createFade(heroHPPos3, 1, 0, 200);
		FadeTransition fadeOutHeroHPPos4 = FadeUtils.createFade(heroHPPos4, 1, 0, 200);
		
		FadeTransition fadeInEnemyHPPos1 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos2 = FadeUtils.createFade(enemyHPPos2, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos3 = FadeUtils.createFade(enemyHPPos3, 0, 1, 200);
		FadeTransition fadeInEnemyHPPos4 = FadeUtils.createFade(enemyHPPos4, 0, 1, 200);
		FadeTransition fadeOutEnemyHPPos1 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos2 = FadeUtils.createFade(enemyHPPos2, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos3 = FadeUtils.createFade(enemyHPPos3, 1, 0, 200);
		FadeTransition fadeOutEnemyHPPos4 = FadeUtils.createFade(enemyHPPos4, 1, 0, 200);
		
		FadeTransition fadeInSkillButtonSelectedFrame1 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInSkillButtonSelectedFrame2 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInSkillButtonSelectedFrame3 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInSkillButtonSelectedFrame4 = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeInSkillButtonMoveSelectedFrame = FadeUtils.createFade(enemyHPPos1, 0, 1, 200);
		FadeTransition fadeOutSkillButtonSelectedFrame1 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutSkillButtonSelectedFrame2 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutSkillButtonSelectedFrame3 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutSkillButtonSelectedFrame4 = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		FadeTransition fadeOutSkillButtonMoveSelectedFrame = FadeUtils.createFade(enemyHPPos1, 1, 0, 200);
		
	    FadeTransition fadeInHeroSelectionIndicator1 = FadeUtils.createFade(heroSelectionIndicator1, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator2 = FadeUtils.createFade(heroSelectionIndicator2, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator3 = FadeUtils.createFade(heroSelectionIndicator3, 0, 1, 200);
	    FadeTransition fadeInHeroSelectionIndicator4 = FadeUtils.createFade(heroSelectionIndicator4, 0, 1, 200);
	    FadeTransition fadeOutHeroSelectionIndicator1 = FadeUtils.createFade(heroSelectionIndicator1, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator2 = FadeUtils.createFade(heroSelectionIndicator2, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator3 = FadeUtils.createFade(heroSelectionIndicator3, 1, 0, 200);
	    FadeTransition fadeOutHeroSelectionIndicator4 = FadeUtils.createFade(heroSelectionIndicator4, 1, 0, 200);
	    
        FadeTransition fadeInEnemySelectionIndicator1 = FadeUtils.createFade(enemySelectionIndicator1, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator2 = FadeUtils.createFade(enemySelectionIndicator2, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator3 = FadeUtils.createFade(enemySelectionIndicator3, 0, 1, 200);
        FadeTransition fadeInEnemySelectionIndicator4 = FadeUtils.createFade(enemySelectionIndicator4, 0, 1, 200);
        FadeTransition fadeOutEnemySelectionIndicator1 = FadeUtils.createFade(enemySelectionIndicator1, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator2 = FadeUtils.createFade(enemySelectionIndicator2, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator3 = FadeUtils.createFade(enemySelectionIndicator3, 1, 0, 200);
        FadeTransition fadeOutEnemySelectionIndicator4 = FadeUtils.createFade(enemySelectionIndicator4, 1, 0, 200);
        
        PauseTransition twoSecDelay = new PauseTransition(Duration.seconds(2));
        
		HBox enemyResistances = new HBox(50);
		enemyResistances.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon );
		HBox heroPositions = new HBox(50);
		heroPositions.getChildren().addAll(heroPosition4, heroPosition3, heroPosition2, heroPosition1);
		HBox enemyPositions = new HBox(50);
		enemyPositions.getChildren().addAll(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);
		HBox skillButtons = new HBox(10);
		skillButtons.getChildren().addAll(skillbutton1, skillbutton2, skillbutton3, skillbutton4);// ,movebutton
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
//		skillbuttonimagemove.setFitWidth(0.06 * 1920);
//		skillbuttonimagemove.setFitHeight(0.051 * 1920);
		
		skillButtonSelectedFrame1.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame1.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame2.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame2.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame3.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame3.setFitHeight(0.09 * 1080);
		skillButtonSelectedFrame4.setFitWidth(0.06 * 1920);
		skillButtonSelectedFrame4.setFitHeight(0.09 * 1080);
//		skillButtonMoveSelectedFrame.setFitWidth(0.06 * 1920);
//		skillButtonMoveSelectedFrame.setFitHeight(0.09 * 1080);
	

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
//		skillbuttonimagemove.setMouseTransparent(true);
		skillbuttonimagepass.setMouseTransparent(true);
		menuBackButtonImage.setMouseTransparent(true);
		menuQuitGameText.setMouseTransparent(true);
		skillButtonSelectedFrame1.setMouseTransparent(true);
		skillButtonSelectedFrame2.setMouseTransparent(true);
		skillButtonSelectedFrame3.setMouseTransparent(true);
		skillButtonSelectedFrame4.setMouseTransparent(true);
//		skillButtonMoveSelectedFrame.setMouseTransparent(true);
		skillButtonSelectedFrame1.setVisible(false);
		skillButtonSelectedFrame2.setVisible(false);
		skillButtonSelectedFrame3.setVisible(false);
		skillButtonSelectedFrame4.setVisible(false);
//		skillButtonMoveSelectedFrame.setVisible(false);
		
		enemyBLDResistanceIcon.setVisible(false);
		enemyBLGTResistanceIcon.setVisible(false);
		enemyBURNResistanceIcon.setVisible(false);
		enemySTNResistanceIcon.setVisible(false);
		enemyMOVResistanceIcon.setVisible(false);
		enemyDBFFResistanceIcon.setVisible(false);
		enemyDTHResistanceIcon.setVisible(false);
		enemyBleedResistanceNumberText.setVisible(false);
		enemyBlightResistanceNumberText.setVisible(false);
		enemyBurnResistanceNumberText.setVisible(false);
		enemyStunResistanceNumberText.setVisible(false);
		enemyMoveResistanceNumberText.setVisible(false);
		enemyDebuffResistanceNumberText.setVisible(false);
		enemyDeathResistanceNumberText.setVisible(false);
		enemyBleedResistanceNumber.setVisible(false);
		enemyBlightResistanceNumber.setVisible(false);
		enemyBurnResistanceNumber.setVisible(false);
		enemyStunResistanceNumber.setVisible(false);
		enemyMoveResistanceNumber.setVisible(false);
		enemyDebuffResistanceNumber.setVisible(false);
		enemyDeathResistanceNumber.setVisible(false);
		
		enemyNameText.setVisible(false);
		
		heroPosition4.setOpacity(0);
		heroPosition3.setOpacity(0);
		heroPosition2.setOpacity(0);
		heroPosition1.setOpacity(0);
		enemyPosition1.setOpacity(0);
		enemyPosition2.setOpacity(0);
		enemyPosition3.setOpacity(0);
		enemyPosition4.setOpacity(0);

		skillbutton1.setOpacity(0);
		skillbutton2.setOpacity(0);
		skillbutton3.setOpacity(0);
		skillbutton4.setOpacity(0);
//		movebutton.setOpacity(0);
		passTurnButton.setOpacity(0);
		
		menuBackButton.setOpacity(0);
		menuQuitButton.setOpacity(0);
		// -------------------------------------------------------------
		skillbutton1.setOnAction(e -> { // clicking on skill buttons makes the indicator visible, and disables visibility of all others.
			// i was going to do fade transistions here, but it seems like it doesn't work on button clicks, but only button hovers?
			skillButtonSelectedFrame1.setVisible(true);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton2.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(true);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton3.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(true);
			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
		skillbutton4.setOnAction(e -> {
			skillButtonSelectedFrame1.setVisible(false);
			skillButtonSelectedFrame2.setVisible(false);
			skillButtonSelectedFrame3.setVisible(false);
			skillButtonSelectedFrame4.setVisible(true);
//			skillButtonMoveSelectedFrame.setVisible(false);
		});
//		movebutton.setOnAction(e -> {
//			skillButtonSelectedFrame1.setVisible(false);
//			skillButtonSelectedFrame2.setVisible(false);
//			skillButtonSelectedFrame3.setVisible(false);
//			skillButtonSelectedFrame4.setVisible(false);
//			skillButtonMoveSelectedFrame.setVisible(true);
//		});
		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		// none of this is correct. these need to be changed so that when a move is selected, it only shows visible on which enemies can be attacked.  // var
		heroPosition1.setOnMouseEntered(e -> {heroSelectionIndicator1.setVisible(true);
		fadeInHeroSelectionIndicator1.play();
		});
		heroPosition1.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator1.play();
		});
		heroSelectionIndicator1.setVisible(false);
		
		heroPosition2.setOnMouseEntered(e -> {heroSelectionIndicator2.setVisible(true);
		fadeInHeroSelectionIndicator2.play();
		});
		heroPosition2.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator2.play();
		});
		heroSelectionIndicator2.setVisible(false);

		heroPosition3.setOnMouseEntered(e -> {heroSelectionIndicator3.setVisible(true);
		fadeInHeroSelectionIndicator3.play();
		});
		heroPosition3.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator3.play();
		});
		heroSelectionIndicator3.setVisible(false);

		heroPosition4.setOnMouseEntered(e -> {heroSelectionIndicator4.setVisible(true);
		fadeInHeroSelectionIndicator4.play();
		});
		heroPosition4.setOnMouseExited(e -> {
			fadeOutHeroSelectionIndicator4.play();
		});
		heroSelectionIndicator4.setVisible(false);
		
		
		enemyPosition1.setOnMouseEntered(e -> {enemySelectionIndicator1.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator1.play();
		});
		enemyPosition1.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator1.play();
		});
		
		enemySelectionIndicator1.setVisible(false);
		
		enemyPosition2.setOnMouseEntered(e -> {enemySelectionIndicator2.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator2.play();
		});
		enemyPosition2.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator2.play();
		});
		
		enemySelectionIndicator2.setVisible(false);
		
		enemyPosition3.setOnMouseEntered(e -> {enemySelectionIndicator3.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator3.play();
		});
		enemyPosition3.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator3.play();
		});
		
		enemySelectionIndicator3.setVisible(false);
		
		enemyPosition4.setOnMouseEntered(e -> {enemySelectionIndicator4.setVisible(true);
		enemyNameText.setVisible(true);
		fadeInEnemySelectionIndicator4.play();
		});
		enemyPosition4.setOnMouseExited(e -> {
			enemyNameText.setVisible(false);
			fadeOutEnemySelectionIndicator4.play();
		});
		
		enemySelectionIndicator4.setVisible(false);

		// -------------------------------------------------------------
		//make images dissapear or appear on button hover.
		enemyPosition1.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
			enemyNameText.setVisible(true);
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemyBleedResistanceNumber.setVisible(true);
		    enemyBlightResistanceNumber.setVisible(true);
		    enemyBurnResistanceNumber.setVisible(true);
		    enemyStunResistanceNumber.setVisible(true);
		    enemyMoveResistanceNumber.setVisible(true);
		    enemyDebuffResistanceNumber.setVisible(true);
		    enemyDeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator1.setVisible(true);
		    fadeInEnemySelectionIndicator1.play();
		});

		enemyPosition1.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator1.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemyBleedResistanceNumber.setVisible(false);
	        enemyBlightResistanceNumber.setVisible(false);
	        enemyBurnResistanceNumber.setVisible(false);
	        enemyStunResistanceNumber.setVisible(false);
	        enemyMoveResistanceNumber.setVisible(false);
	        enemyDebuffResistanceNumber.setVisible(false);
	        enemyDeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator1.setOnFinished(event -> {
		        enemySelectionIndicator1.setVisible(false);
		       
		    });
		});


		enemyPosition2.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemyBleedResistanceNumber.setVisible(true);
		    enemyBlightResistanceNumber.setVisible(true);
		    enemyBurnResistanceNumber.setVisible(true);
		    enemyStunResistanceNumber.setVisible(true);
		    enemyMoveResistanceNumber.setVisible(true);
		    enemyDebuffResistanceNumber.setVisible(true);
		    enemyDeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator2.setVisible(true);
		    fadeInEnemySelectionIndicator2.play();
		});

		enemyPosition2.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator2.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemyBleedResistanceNumber.setVisible(false);
	        enemyBlightResistanceNumber.setVisible(false);
	        enemyBurnResistanceNumber.setVisible(false);
	        enemyStunResistanceNumber.setVisible(false);
	        enemyMoveResistanceNumber.setVisible(false);
	        enemyDebuffResistanceNumber.setVisible(false);
	        enemyDeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator2.setOnFinished(event -> {
		        enemySelectionIndicator2.setVisible(false);
		     
		    });
		});

		enemyPosition3.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemyBleedResistanceNumber.setVisible(true);
		    enemyBlightResistanceNumber.setVisible(true);
		    enemyBurnResistanceNumber.setVisible(true);
		    enemyStunResistanceNumber.setVisible(true);
		    enemyMoveResistanceNumber.setVisible(true);
		    enemyDebuffResistanceNumber.setVisible(true);
		    enemyDeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator3.setVisible(true);
		    fadeInEnemySelectionIndicator3.play();
		});

		enemyPosition3.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator3.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemyBleedResistanceNumber.setVisible(false);
	        enemyBlightResistanceNumber.setVisible(false);
	        enemyBurnResistanceNumber.setVisible(false);
	        enemyStunResistanceNumber.setVisible(false);
	        enemyMoveResistanceNumber.setVisible(false);
	        enemyDebuffResistanceNumber.setVisible(false);
	        enemyDeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator3.setOnFinished(event -> {
		        enemySelectionIndicator3.setVisible(false);
		       
		    });
		});

		enemyPosition4.setOnMouseEntered(e -> {
		    // Show all elements and play fade-in animation
		    enemyBLDResistanceIcon.setVisible(true);
		    enemyBLGTResistanceIcon.setVisible(true);
		    enemyBURNResistanceIcon.setVisible(true);
		    enemySTNResistanceIcon.setVisible(true);
		    enemyMOVResistanceIcon.setVisible(true);
		    enemyDBFFResistanceIcon.setVisible(true);
		    enemyDTHResistanceIcon.setVisible(true);
		    enemyBleedResistanceNumberText.setVisible(true);
		    enemyBlightResistanceNumberText.setVisible(true);
		    enemyBurnResistanceNumberText.setVisible(true);
		    enemyStunResistanceNumberText.setVisible(true);
		    enemyMoveResistanceNumberText.setVisible(true);
		    enemyDebuffResistanceNumberText.setVisible(true);
		    enemyDeathResistanceNumberText.setVisible(true);
		    enemyBleedResistanceNumber.setVisible(true);
		    enemyBlightResistanceNumber.setVisible(true);
		    enemyBurnResistanceNumber.setVisible(true);
		    enemyStunResistanceNumber.setVisible(true);
		    enemyMoveResistanceNumber.setVisible(true);
		    enemyDebuffResistanceNumber.setVisible(true);
		    enemyDeathResistanceNumber.setVisible(true);
		    enemySelectionIndicator4.setVisible(true);
		    fadeInEnemySelectionIndicator4.play();
		});

		enemyPosition4.setOnMouseExited(e -> {
		    // Play fade-out animation
		    fadeOutEnemySelectionIndicator4.play();
		    enemyBLDResistanceIcon.setVisible(false);
	        enemyBLGTResistanceIcon.setVisible(false);
	        enemyBURNResistanceIcon.setVisible(false);
	        enemySTNResistanceIcon.setVisible(false);
	        enemyMOVResistanceIcon.setVisible(false);
	        enemyDBFFResistanceIcon.setVisible(false);
	        enemyDTHResistanceIcon.setVisible(false);
	        enemyBleedResistanceNumberText.setVisible(false);
	        enemyBlightResistanceNumberText.setVisible(false);
	        enemyBurnResistanceNumberText.setVisible(false);
	        enemyStunResistanceNumberText.setVisible(false);
	        enemyMoveResistanceNumberText.setVisible(false);
	        enemyDebuffResistanceNumberText.setVisible(false);
	        enemyDeathResistanceNumberText.setVisible(false);
	        enemyBleedResistanceNumber.setVisible(false);
	        enemyBlightResistanceNumber.setVisible(false);
	        enemyBurnResistanceNumber.setVisible(false);
	        enemyStunResistanceNumber.setVisible(false);
	        enemyMoveResistanceNumber.setVisible(false);
	        enemyDebuffResistanceNumber.setVisible(false);
	        enemyDeathResistanceNumber.setVisible(false);
		    // After fade-out completes, set elements to invisible
		    fadeOutEnemySelectionIndicator4.setOnFinished(event -> {
		        enemySelectionIndicator4.setVisible(false);
		       
		    });
		});
		// -------------------------------------------------------------

		
		
		heroHealthBarRedRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);
		fadeInHeroHPPos4.play();});
		heroHealthBarRedRectangle4.setOnMouseExited(e -> {fadeOutHeroHPPos4.play();});
		heroHPPos4.setVisible(false);
		heroHealthBarBlackRectangle4.setOnMouseEntered(e -> {heroHPPos4.setVisible(true);
		fadeInHeroHPPos4.play();});
		heroHealthBarBlackRectangle4.setOnMouseExited(e -> {fadeOutHeroHPPos4.play();});
		heroHPPos4.setVisible(false);
		 
		heroHealthBarRedRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);
		fadeInHeroHPPos3.play();});
		heroHealthBarRedRectangle3.setOnMouseExited(e -> {fadeOutHeroHPPos3.play();});
		heroHPPos3.setVisible(false);
		heroHealthBarBlackRectangle3.setOnMouseEntered(e -> {heroHPPos3.setVisible(true);
		fadeInHeroHPPos3.play();});
		heroHealthBarBlackRectangle3.setOnMouseExited(e -> {fadeOutHeroHPPos3.play();});
		heroHPPos3.setVisible(false);
		
		heroHealthBarRedRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);
		fadeInHeroHPPos2.play();});
		heroHealthBarRedRectangle2.setOnMouseExited(e -> {fadeOutHeroHPPos2.play();});
		heroHPPos2.setVisible(false);
		heroHealthBarBlackRectangle2.setOnMouseEntered(e -> {heroHPPos2.setVisible(true);
		fadeInHeroHPPos2.play();});
		heroHealthBarBlackRectangle2.setOnMouseExited(e -> {fadeOutHeroHPPos2.play();});
		heroHPPos2.setVisible(false);
		
		heroHealthBarRedRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);
		fadeInHeroHPPos1.play();});
		heroHealthBarRedRectangle1.setOnMouseExited(e -> {fadeOutHeroHPPos1.play();});
		heroHPPos1.setVisible(false);
		heroHealthBarBlackRectangle1.setOnMouseEntered(e -> {heroHPPos1.setVisible(true);
		fadeInHeroHPPos1.play();});
		heroHealthBarBlackRectangle1.setOnMouseExited(e -> {fadeOutHeroHPPos1.play();});
		heroHPPos1.setVisible(false);
		
		
		enemyHealthBarRedRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);
		fadeInEnemyHPPos4.play();});
		enemyHealthBarRedRectangle4.setOnMouseExited(e -> {fadeOutEnemyHPPos4.play();});
		enemyHPPos4.setVisible(false);
		enemyHealthBarBlackRectangle4.setOnMouseEntered(e -> {enemyHPPos4.setVisible(true);
		fadeInEnemyHPPos4.play();});
		enemyHealthBarBlackRectangle4.setOnMouseExited(e -> {fadeOutEnemyHPPos4.play();});
		enemyHPPos4.setVisible(false);
		
		enemyHealthBarRedRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);
		fadeInEnemyHPPos3.play();});
		enemyHealthBarRedRectangle3.setOnMouseExited(e -> {fadeOutEnemyHPPos3.play();});
		enemyHPPos3.setVisible(false);
		enemyHealthBarBlackRectangle3.setOnMouseEntered(e -> {enemyHPPos3.setVisible(true);
		fadeInEnemyHPPos3.play();});
		enemyHealthBarBlackRectangle3.setOnMouseExited(e -> {fadeOutEnemyHPPos3.play();});
		enemyHPPos3.setVisible(false);
		
		enemyHealthBarRedRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);
		fadeInEnemyHPPos2.play();});
		enemyHealthBarRedRectangle2.setOnMouseExited(e -> {fadeOutEnemyHPPos2.play();});
		enemyHPPos2.setVisible(false);
		enemyHealthBarBlackRectangle2.setOnMouseEntered(e -> {enemyHPPos2.setVisible(true);
		fadeInEnemyHPPos2.play();});
		enemyHealthBarBlackRectangle2.setOnMouseExited(e -> {fadeOutEnemyHPPos2.play();});
		enemyHPPos2.setVisible(false);
		
		enemyHealthBarRedRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);
		fadeInEnemyHPPos1.play();});
		enemyHealthBarRedRectangle1.setOnMouseExited(e -> {fadeOutEnemyHPPos1.play();});
		enemyHPPos1.setVisible(false);
		enemyHealthBarBlackRectangle1.setOnMouseEntered(e -> {enemyHPPos1.setVisible(true);
		fadeInEnemyHPPos1.play();});
		enemyHealthBarBlackRectangle1.setOnMouseExited(e -> {fadeOutEnemyHPPos1.play();});
		enemyHPPos1.setVisible(false);
		// -------------------------------------------------------------

		// Create a Pane for free positioning
		Pane root = new Pane();
		Pane heroAttackSplashScreen = new Pane();
		Pane enemyAttackSplashScreen = new Pane();
		
		Pane combatMenu = new Pane();
		
		
		heroAttackSplashScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); // semi-transparent black
		heroAttackSplashScreen.setVisible(false); // initially hidden
		FadeTransition fadeInheroAttackSplashScreen = new FadeTransition(Duration.millis(100), heroAttackSplashScreen);
		fadeInheroAttackSplashScreen.setFromValue(0);
		fadeInheroAttackSplashScreen.setToValue(1);
		twoSecDelay.play();
		FadeTransition fadeOutheroAttackSplashScreen = new FadeTransition(Duration.millis(100), heroAttackSplashScreen);
		fadeOutheroAttackSplashScreen.setFromValue(1);
		fadeOutheroAttackSplashScreen.setToValue(0);
		fadeOutheroAttackSplashScreen.setOnFinished(e -> heroAttackSplashScreen.setVisible(false)); // Hide after fading out
		
		enemyAttackSplashScreen.getChildren().addAll(heroAttackingBloodStain);
//		enemyAttackSplashScreen.getChildren().addAll(charAttacking, charDefending); // var

		// -------------------------------------------------------------

		
		enemyAttackSplashScreen.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); // semi-transparent black
		enemyAttackSplashScreen.setVisible(false); // initially hidden
		FadeTransition fadeInenemyAttackSplashScreen = new FadeTransition(Duration.millis(100), enemyAttackSplashScreen);
		fadeInenemyAttackSplashScreen.setFromValue(0);
		fadeInenemyAttackSplashScreen.setToValue(1);
		twoSecDelay.play();
		FadeTransition fadeOutenemyAttackSplashScreen = new FadeTransition(Duration.millis(100), enemyAttackSplashScreen);
		fadeOutenemyAttackSplashScreen.setFromValue(1);
		fadeOutenemyAttackSplashScreen.setToValue(0);
		fadeOutenemyAttackSplashScreen.setOnFinished(e -> enemyAttackSplashScreen.setVisible(false)); // Hide after fading out
		
		enemyAttackSplashScreen.getChildren().addAll(enemyAttackingBloodStain);
//		enemyAttackSplashScreen.getChildren().addAll(charAttacking, charDefending); // var
		
		// -------------------------------------------------------------
		
		combatMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"); // semi-transparent black
		combatMenu.setVisible(false); // initially hidden
        FadeTransition fadeInCombatMenu = new FadeTransition(Duration.millis(300), combatMenu);
        fadeInCombatMenu.setFromValue(0);
        fadeInCombatMenu.setToValue(1);
        FadeTransition fadeOutCombatMenu = new FadeTransition(Duration.millis(300), combatMenu);
        fadeOutCombatMenu.setFromValue(1);
        fadeOutCombatMenu.setToValue(0);
        fadeOutCombatMenu.setOnFinished(e -> combatMenu.setVisible(false)); // Hide after fading out
        

        
		// Add buttons, images
		root.getChildren().addAll(heroPositions, enemyPositions);
		root.getChildren().addAll(enemyInPosition1, enemyInPosition2, enemyInPosition3, enemyInPosition4);
		root.getChildren().addAll(heroInPosition1, heroInPosition2, heroInPosition3, heroInPosition4);
		root.getChildren().addAll(heroTurnTicker1, heroTurnTicker2, heroTurnTicker3, heroTurnTicker4);
		root.getChildren().addAll(enemyTurnTicker1, enemyTurnTicker2, enemyTurnTicker3, enemyTurnTicker4);
		root.getChildren().addAll(enemyBLDResistanceIcon, enemyBLGTResistanceIcon, enemyBURNResistanceIcon, enemySTNResistanceIcon, enemyMOVResistanceIcon, enemyDBFFResistanceIcon, enemyDTHResistanceIcon);
		root.getChildren().addAll(enemyBleedResistanceNumberText, enemyBlightResistanceNumberText, enemyBurnResistanceNumberText, enemyStunResistanceNumberText, enemyMoveResistanceNumberText, enemyDebuffResistanceNumberText, enemyDeathResistanceNumberText);
		root.getChildren().addAll(enemyBleedResistanceNumber,enemyBlightResistanceNumber,enemyBurnResistanceNumber,enemyStunResistanceNumber,enemyMoveResistanceNumber,enemyDebuffResistanceNumber, enemyDeathResistanceNumber);
		root.getChildren().add(skillButtons); 
		root.getChildren().add(passTurnButton); 
		root.getChildren().addAll(skillbuttonimage1, skillbuttonimage2, skillbuttonimage3, skillbuttonimage4, skillbuttonimagepass); // , skillbuttonimagemove																				
		root.getChildren().addAll(skillButtonSelectedFrame1,skillButtonSelectedFrame2,skillButtonSelectedFrame3,skillButtonSelectedFrame4); // ,skillButtonMoveSelectedFrame
		root.getChildren().addAll(heroNameTextPlate, enemyNameTextPlate);
		root.getChildren().add(turnOrderBarLeftAndRight);
		root.getChildren().addAll(heroNameText, enemyNameText);// keep name after name plate to avoid layering issues
		root.getChildren().add(roundNumberText);
		root.getChildren().addAll(heroSelectionIndicator1, heroSelectionIndicator2, heroSelectionIndicator3,heroSelectionIndicator4); 
		root.getChildren().addAll(enemySelectionIndicator1, enemySelectionIndicator2, enemySelectionIndicator3, enemySelectionIndicator4); // might need 4 of these.
		root.getChildren().add(heroHealthBarBlackRectangles);
		root.getChildren().add(heroHealthBarRedRectangles);
		root.getChildren().add(enemyHealthBarBlackRectangles);
		root.getChildren().add(enemyHealthBarRedRectangles);
		root.getChildren().addAll(heroHPPos4,heroHPPos3,heroHPPos2,heroHPPos1);
		root.getChildren().addAll(enemyHPPos4,enemyHPPos3,enemyHPPos2,enemyHPPos1);
		root.getChildren().addAll(moveDescriptionText,moveNameText);
		
		root.setBackground(new Background(backgroundImagePayoff)); // set background image
		
		combatMenu.getChildren().addAll(menuBackground);
		combatMenu.getChildren().addAll(menuButtonFrame1);
		combatMenu.getChildren().addAll(menuBackButton,menuQuitButton);
		combatMenu.getChildren().add(menuQuitGameText);
		combatMenu.getChildren().add(menuBackButtonImage);
		// -------------------------------------------------------------
		// Manually position the HBoxes and back button
		heroPositions.setLayoutX(125); // Position X for hero positions
		heroPositions.setLayoutY(250); // Position Y for hero positions

		enemyPositions.setLayoutX(1025); // Position X for enemy positions
		enemyPositions.setLayoutY(250); // Position Y for enemy positions
		
		heroNameText.setLayoutX(-50); // hero's name
		heroNameText.setLayoutY(750);
		heroNameText.setScaleX(.65);
		heroNameText.setScaleY(.65);
		
		roundNumberText.setLayoutX(1340);
		roundNumberText.setLayoutY(45);
		roundNumberText.setScaleX(.95);
		roundNumberText.setScaleY(.95);
		
		enemyNameText.setLayoutX(1460);
		enemyNameText.setLayoutY(750);
		enemyNameText.setScaleX(.65);
		enemyNameText.setScaleY(.65);
		
		heroNameTextPlate.setLayoutX(-5);
		heroNameTextPlate.setLayoutY(710);

		enemyNameTextPlate.setLayoutX(1415);
		enemyNameTextPlate.setLayoutY(710);
		enemyNameTextPlate.setScaleX(-1);
		
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
		moveDescriptionText.setLayoutX(25);
		moveDescriptionText.setLayoutY(800);
		
		moveNameText.setLayoutX(900);
		moveNameText.setLayoutY(800);
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
		
		enemyBleedResistanceNumberText.setLayoutX(1403);
		enemyBleedResistanceNumberText.setLayoutY(940); 
		enemyBlightResistanceNumberText.setLayoutX(1469);
		enemyBlightResistanceNumberText.setLayoutY(940); 
		enemyBurnResistanceNumberText.setLayoutX(1554);
		enemyBurnResistanceNumberText.setLayoutY(940); 
		enemyStunResistanceNumberText.setLayoutX(1629);
		enemyStunResistanceNumberText.setLayoutY(940); 
		enemyMoveResistanceNumberText.setLayoutX(1696);
		enemyMoveResistanceNumberText.setLayoutY(940); 
		enemyDebuffResistanceNumberText.setLayoutX(1771);
		enemyDebuffResistanceNumberText.setLayoutY(940); 
		enemyDeathResistanceNumberText.setLayoutX(1855);
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
		
		enemyBleedResistanceNumber.setLayoutX(1418); // 77 spacing
		enemyBleedResistanceNumber.setLayoutY(1020);
		enemyBleedResistanceNumber.setScaleX(.75);
		enemyBleedResistanceNumber.setScaleY(.75); 
		enemyBlightResistanceNumber.setLayoutX(1495);
		enemyBlightResistanceNumber.setLayoutY(1020); 
		enemyBlightResistanceNumber.setScaleX(.75);
		enemyBlightResistanceNumber.setScaleY(.75); 
		enemyBurnResistanceNumber.setLayoutX(1572);
		enemyBurnResistanceNumber.setLayoutY(1020); 
		enemyBurnResistanceNumber.setScaleX(.75);
		enemyBurnResistanceNumber.setScaleY(.75); 
		enemyStunResistanceNumber.setLayoutX(1642);
		enemyStunResistanceNumber.setLayoutY(1020); 
		enemyStunResistanceNumber.setScaleX(.75);
		enemyStunResistanceNumber.setScaleY(.75); 
		enemyMoveResistanceNumber.setLayoutX(1719);
		enemyMoveResistanceNumber.setLayoutY(1020); 
		enemyMoveResistanceNumber.setScaleX(.75);
		enemyMoveResistanceNumber.setScaleY(.75); 
		enemyDebuffResistanceNumber.setLayoutX(1796);
		enemyDebuffResistanceNumber.setLayoutY(1020); 
		enemyDebuffResistanceNumber.setScaleX(.75);
		enemyDebuffResistanceNumber.setScaleY(.75); 
		enemyDeathResistanceNumber.setLayoutX(1873);
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
		
		skillButtons.setLayoutX(696); // 585 sweet spot
		skillButtons.setLayoutY(828);
		
		passTurnButton.setLayoutX(1077);
		passTurnButton.setLayoutY(828);
		
		skillbuttonimage1.setLayoutX(696);
		skillbuttonimage1.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillbuttonimage2.setLayoutX(823);
		skillbuttonimage2.setLayoutY(828);
		skillbuttonimage3.setLayoutX(950);
		skillbuttonimage3.setLayoutY(828);
		skillbuttonimage4.setLayoutX(1077);
		skillbuttonimage4.setLayoutY(828);
//		skillbuttonimagemove.setLayoutX(1094);
//		skillbuttonimagemove.setLayoutY(778);
		skillbuttonimagepass.setLayoutX(1207); // 1227
		skillbuttonimagepass.setLayoutY(841); // 791
		skillbuttonimagepass.setScaleX(1.35);
		skillbuttonimagepass.setScaleY(1.35);
		
		skillButtonSelectedFrame1.setLayoutX(696);
		skillButtonSelectedFrame1.setLayoutY(828);// 778 SWEET SPOT // 127 x multiple
		skillButtonSelectedFrame2.setLayoutX(823);
		skillButtonSelectedFrame2.setLayoutY(828);
		skillButtonSelectedFrame3.setLayoutX(950);
		skillButtonSelectedFrame3.setLayoutY(828);
		skillButtonSelectedFrame4.setLayoutX(1077);
		skillButtonSelectedFrame4.setLayoutY(828);
//		skillButtonMoveSelectedFrame.setLayoutX(1094);
//		skillButtonMoveSelectedFrame.setLayoutY(778);
		
		skillButtonSelectedFrame1.setScaleX(1.6);
		skillButtonSelectedFrame1.setScaleY(1.6);// 778 SWEET SPOT // 127 x multiple
		skillButtonSelectedFrame2.setScaleX(1.6);
		skillButtonSelectedFrame2.setScaleY(1.6);
		skillButtonSelectedFrame3.setScaleX(1.6);
		skillButtonSelectedFrame3.setScaleY(1.6);
		skillButtonSelectedFrame4.setScaleX(1.6);
		skillButtonSelectedFrame4.setScaleY(1.6);
//		skillButtonMoveSelectedFrame.setScaleX(1.6);
//		skillButtonMoveSelectedFrame.setScaleY(1.6);
		// -------------------------------------------------------------
		menuBackground.setLayoutX(450);
		menuBackground.setLayoutY(0);
		menuBackground.setScaleX(1);
		menuBackground.setScaleY(1);
		
		menuQuitButton.setLayoutX(925);
		menuQuitButton.setLayoutY(633);
		menuQuitButton.setScaleX(7.5);
		menuQuitButton.setScaleY(2.5);
		
		menuBackButtonImage.setLayoutX(1310);
		menuBackButtonImage.setLayoutY(103);
		menuBackButtonImage.setScaleX(1);
		menuBackButtonImage.setScaleY(1);
		
		menuBackButton.setLayoutX(1310);
		menuBackButton.setLayoutY(110);
		menuBackButton.setScaleX(1);
		menuBackButton.setScaleY(2);
		
		menuButtonFrame1.setLayoutX(650);
		menuButtonFrame1.setLayoutY(580);
		menuButtonFrame1.setScaleX(1);
		menuButtonFrame1.setScaleY(1);

		menuQuitGameText.setLayoutX(835);
		menuQuitGameText.setLayoutY(660);
		menuQuitGameText.setScaleX(1);
		menuQuitGameText.setScaleY(1);
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
		    ((RadioButton) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.06));
		    ((RadioButton) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));
		});

		passTurnButton.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.015));
		passTurnButton.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.09));

		menuBackButton.setOnAction(e -> {
			fadeOutCombatMenu.playFromStart(); 
			root.setEffect(null); // remove blur
		});
		menuQuitButton.setOnAction(e -> {
			mediaPlayer.stop(); // Stop the music when the back button is pressed
			
			homePage(primaryStage);
		});
		primaryStage.setMaximized(true);
		
		Scene scene = new Scene(new StackPane(root, combatMenu), 1920, 1080);
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				 if (combatMenu.isVisible()) {
					 fadeOutCombatMenu.playFromStart(); // Start fade out transition
			            root.setEffect(null);    // Remove blur
			        } else {
			            combatMenu.setVisible(true); // Show the menu first
			            root.setEffect(blur);       // Apply blur to root
			            fadeInCombatMenu.playFromStart();     // Fade in the menu
			        }
			    }
		});

		scene.setCursor(customCursor);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(event -> {
			mediaPlayer.stop(); // Stop the music when the stage is closed
		});
		
		
		combatControl flow = new combatControl(enemyPosition1, enemyPosition2, enemyPosition3, enemyPosition4);
		flow.createEnemyTeam();
		flow.adjustSpeeds();
		flow.determineTurnOrder();
		entities[] tempTurnOrder = flow.getTurnOrder();
		playerTeamArray tempPTeamArray = flow.getPlayerTeamArray();
		Characters[] tempPTeam = tempPTeamArray.getTeam();
		enemyTeam tempETeamArray = flow.getEnemyTeam();
		Enemies[] tempETeam = tempETeamArray.getTeam();
		count = 0;
		Timer timer = new Timer();
		
		// This task checks if either team is dead, and if not
		// runs the primary combat control method.
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (tempPTeamArray.checkGameOver() || tempETeamArray.checkGameOver()) {
					if(tempPTeamArray.checkGameOver()) {
						System.out.println("Player Team Defeated. Game Over.");
						timer.cancel();
						timer.purge();
						// Game over Screen?
					}
					else if (tempETeamArray.checkGameOver()) {
						System.out.println("Enemy Team Defeated. Round Over.");
						fightsWon++;
						timer.cancel();
						timer.purge();
						// Shop Scene, continue to next round?
					}
				}
					
				else {
						
					// Reset turn counter if order has been run through
					if (count > 7)
						count = 0;
					
					count = flow.runCombat(count);
				}
			}
		};
		
		System.out.println("Starting Combat:");
		timer.schedule(task, 2000, 5000);
		
		
		
		// This will be what we implement and add animation stuff to 
		// when we get to that stage: 
		/*
		Timeline fiveSecondsCombat = new Timeline(
                			new KeyFrame(Duration.seconds(5), 
                			new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("this is called every 5 seconds on UI thread");
				if (tempPTeamArray.checkGameOver() || tempETeamArray.checkGameOver()) {
					System.out.println("Game Over.");
				}
					
				else {
						
					// Reset turn counter if order has been run through
					if (count > 7)
						count = 0;
					
					count = flow.runCombat(count);
				}
            }
        }));
		fiveSecondsCombat.setCycleCount(Timeline.INDEFINITE); // Probably won't be indefinite, have to look into this
		fiveSecondsCombat.play();
		*/


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
		Text itemDescriptions = new Text("Item Descriptions will go here");
		Text item1Price = new Text("1");
		Text item2Price = new Text("2");
		Text item3Price = new Text("3");
		Text playerGold = new Text("playerGold");
		Text purchaseConfirmationText = new Text("Buy (item) for (char) (-X Gold)");
		Text exitShopText = new Text("exit shop");

		ToggleGroup itemSelection = new ToggleGroup();
		RadioButton item1Button = new RadioButton("item1");
		item1Button.setToggleGroup(itemSelection);
		RadioButton item2Button = new RadioButton("item2");
		item2Button.setToggleGroup(itemSelection);
		RadioButton item3Button = new RadioButton("item3");
		item3Button.setToggleGroup(itemSelection);
		Button purchaseConfirmationButton = new Button("purchaseConfirmationButton");

		Button charChoiceAlchemistButton= new Button("charChoiceAlchemist");
		Button charChoiceWizardButton= new Button("charChoiceWizard");
		Button charChoiceAssassinButton= new Button("charChoiceAssassin");
		Button charChoicePaladinButton= new Button("charChoicePaladin");


		itemDescriptions.setFont(DwarvenAxe);
		item1Price.setFont(DwarvenAxe);
		item2Price.setFont(DwarvenAxe);
		item3Price.setFont(DwarvenAxe);
		playerGold.setFont(DwarvenAxe);
		purchaseConfirmationText.setFont(DwarvenAxe);
		exitShopText.setFont(DwarvenAxe);
		
		itemDescriptions.setFill(Color.web("#d5d5d5"));
		item1Price.setFill(Color.web("#d5d5d5"));
		item2Price.setFill(Color.web("#d5d5d5"));
		item3Price.setFill(Color.web("#d5d5d5"));
		playerGold.setFill(Color.web("#FFEB80"));
		purchaseConfirmationText.setFill(Color.web("#d5d5d5"));
		exitShopText.setFill(Color.web("#d5d5d5"));
		
		ImageView shopKeeper = new ImageView(new Image("shopAssets/shopKeeper.png"));
		ImageView itemForSaleFrame1 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView itemForSaleFrame2 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView itemForSaleFrame3 = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		
		ImageView itemForSaleFrameArrow1 = new ImageView(new Image("shopAssets/selectionArrow.png"));
		ImageView itemForSaleFrameArrow2 = new ImageView(new Image("shopAssets/selectionArrow.png"));
		ImageView itemForSaleFrameArrow3 = new ImageView(new Image("shopAssets/selectionArrow.png"));

		
		
		ImageView exitShopBanner = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView shopSeparatonBar1 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView shopSeparatonBar2 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView shopSeparatonBar3 = new ImageView(new Image("shopAssets/shopSeparatonBar.png"));
		ImageView purchaseConfirmationImage = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		
		ImageView goldIcon = new ImageView(new Image("shopAssets/goldIcon.png"));
		ImageView paladinPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Paladin/paladinPlayerIcon.png"));
		ImageView assassinPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Assassin/assassinPlayerIcon.png"));
		ImageView wizardPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Wizard/wizardPlayerIcon.png"));
		ImageView alchemistPlayerIcon= new ImageView(new Image("applicationImagesHeroSprites/Alchemist/alchemistPlayerIcon.png"));
		ImageView charSelectedAlchemist = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedWizard = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedAssassin = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		ImageView charSelectedPaladin = new ImageView(new Image("GUIAssets/skillButtonSelectedFrame.png"));
		
		Image backgroundImagesetup = new Image("shopAssets/shopBackground.png");
		BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false); // background																									// image
		BackgroundPosition customPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.TOP, 0, true); // fit to top																					// left
		BackgroundImage backgroundImagePayoff = new BackgroundImage(backgroundImagesetup, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, customPosition, size);
		
		Pane root = new Pane();
		
		purchaseConfirmationText.setMouseTransparent(true);
		alchemistPlayerIcon.setMouseTransparent(true);
		wizardPlayerIcon.setMouseTransparent(true);
		assassinPlayerIcon.setMouseTransparent(true);
		paladinPlayerIcon.setMouseTransparent(true);
		exitShopText.setMouseTransparent(true);
		
		charSelectedAlchemist.setVisible(false);
		charSelectedWizard.setVisible(false);
		charSelectedAssassin.setVisible(false);
		charSelectedPaladin.setVisible(false);
		
		itemForSaleFrameArrow1.setVisible(false);
		itemForSaleFrameArrow2.setVisible(false);
		itemForSaleFrameArrow3.setVisible(false);
		
		charChoiceAlchemistButton.setOpacity(0);
		charChoiceWizardButton.setOpacity(0);
		charChoiceAssassinButton.setOpacity(0);
		charChoicePaladinButton.setOpacity(0);
		purchaseConfirmationButton.setOpacity(0);
		item1Button.setOpacity(0);
		item2Button.setOpacity(0);
		item3Button.setOpacity(0);
		
		back.setOpacity(0);
		
		
		charChoiceAlchemistButton.setOnAction(e -> { // clicking on skill buttons makes the indicator visible, and disables visibility of all others.
			// i was going to do fade transistions here, but it seems like it doesn't work on button clicks, but only button hovers?
			charSelectedAlchemist.setVisible(true);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(false);
		});
		charChoiceWizardButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(true);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(false);
		});
		charChoiceAssassinButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(true);
			charSelectedPaladin.setVisible(false);
		});
		charChoicePaladinButton.setOnAction(e -> {
			charSelectedAlchemist.setVisible(false);
			charSelectedWizard.setVisible(false);
			charSelectedAssassin.setVisible(false);
			charSelectedPaladin.setVisible(true);
		});
//--------------------------------------------------------------------------------

			item1Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(true);
				itemForSaleFrameArrow2.setVisible(false);
				itemForSaleFrameArrow3.setVisible(false);
			});
			item2Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(false);
				itemForSaleFrameArrow2.setVisible(true);
				itemForSaleFrameArrow3.setVisible(false);
			});
			item3Button.setOnAction(e -> {
				itemForSaleFrameArrow1.setVisible(false);
				itemForSaleFrameArrow2.setVisible(false);
				itemForSaleFrameArrow3.setVisible(true);
			});
			
//--------------------------------------------------------------------------------

		back.setOnMouseEntered(e -> {
			exitShopText.setFill(Color.web("#d10000"));
		});
		back.setOnMouseExited(e -> {
			exitShopText.setFill(Color.web("#d5d5d5"));
		});
		purchaseConfirmationButton.setOnMouseEntered(e -> {
			purchaseConfirmationText.setFill(Color.web("#d10000"));
		});
		purchaseConfirmationButton.setOnMouseExited(e -> {
			purchaseConfirmationText.setFill(Color.web("#d5d5d5"));
		});
		
//		charChoiceAlchemistButton.setVisible(false);

		// Add buttons, images, and back button to the root Pane

		root.getChildren().addAll(shopKeeper, purchaseConfirmationImage); 	
		root.getChildren().addAll(itemForSaleFrame1, itemForSaleFrame2, itemForSaleFrame3, shopSeparatonBar1, shopSeparatonBar2, shopSeparatonBar3, goldIcon);
		root.getChildren().addAll(itemForSaleFrameArrow1,itemForSaleFrameArrow2,itemForSaleFrameArrow3);
		root.getChildren().addAll(item1Button, item2Button, item3Button, purchaseConfirmationButton);
		root.getChildren().addAll(charChoiceAlchemistButton, charChoiceWizardButton, charChoiceAssassinButton, charChoicePaladinButton);
		root.getChildren().addAll(paladinPlayerIcon,assassinPlayerIcon,wizardPlayerIcon,alchemistPlayerIcon);
		root.getChildren().addAll(charSelectedAlchemist,charSelectedWizard,charSelectedAssassin,charSelectedPaladin);
		root.getChildren().addAll(playerGold, purchaseConfirmationText);
		root.getChildren().addAll(itemDescriptions,item1Price,item2Price,item3Price);
		root.getChildren().addAll(exitShopBanner);
		root.getChildren().addAll(back);
		root.getChildren().addAll(exitShopText);

		root.setBackground(new Background(backgroundImagePayoff)); // set background image

		// -------------------------------------------------------------
		
		// -------------------------------------------------------------
		item1Button.setLayoutX(1231);
		item1Button.setLayoutY(148);
		item2Button.setLayoutX(1481);
		item2Button.setLayoutY(148);
		item3Button.setLayoutX(1731);
		item3Button.setLayoutY(148);
		
		itemForSaleFrameArrow1.setLayoutX(1244);
		itemForSaleFrameArrow1.setLayoutY(5);
		itemForSaleFrameArrow1.setScaleX(1);
		itemForSaleFrameArrow1.setScaleY(3);
		itemForSaleFrameArrow1.setRotate(90);
		
		itemForSaleFrameArrow2.setLayoutX(1494);
		itemForSaleFrameArrow2.setLayoutY(5);
		itemForSaleFrameArrow2.setScaleX(1);
		itemForSaleFrameArrow2.setScaleY(3);
		itemForSaleFrameArrow2.setRotate(90);
		
		itemForSaleFrameArrow3.setLayoutX(1744);
		itemForSaleFrameArrow3.setLayoutY(5);
		itemForSaleFrameArrow3.setScaleX(1);
		itemForSaleFrameArrow3.setScaleY(3);
		itemForSaleFrameArrow3.setRotate(90);
		
		purchaseConfirmationButton.setLayoutX(1420);
		purchaseConfirmationButton.setLayoutY(990);
		purchaseConfirmationButton.setScaleX(4.9);
		purchaseConfirmationButton.setScaleY(3);
		
		purchaseConfirmationImage.setLayoutX(659);
		purchaseConfirmationImage.setLayoutY(607);
		purchaseConfirmationImage.setScaleX(.3);
		purchaseConfirmationImage.setScaleY(1.65);
		purchaseConfirmationImage.setRotate(90);
		
		
		item1Button.setScaleX(2.5);
		item1Button.setScaleY(13.75);
		item2Button.setScaleX(2.5);
		item2Button.setScaleY(13.75);
		item3Button.setScaleX(2.5);
		item3Button.setScaleY(13.75);

		// -------------------------------------------------------------
		// frames around character images that appear if the char is selected
		charSelectedAlchemist.setLayoutX(1157);
		charSelectedAlchemist.setLayoutY(772);
		charSelectedWizard.setLayoutX(1357);
		charSelectedWizard.setLayoutY(772);
		charSelectedAssassin.setLayoutX(1557);
		charSelectedAssassin.setLayoutY(772);
		charSelectedPaladin.setLayoutX(1757);
		charSelectedPaladin.setLayoutY(772);
		
		charSelectedAlchemist.setScaleX(1.7);
		charSelectedAlchemist.setScaleY(1.7);
		charSelectedWizard.setScaleX(1.7);
		charSelectedWizard.setScaleY(1.7);
		charSelectedAssassin.setScaleX(1.7);
		charSelectedAssassin.setScaleY(1.7);
		charSelectedPaladin.setScaleX(1.7);
		charSelectedPaladin.setScaleY(1.7);
		// -------------------------------------------------------------

		charChoiceAlchemistButton.setLayoutX(1148);
		charChoiceAlchemistButton.setLayoutY(812);
		charChoiceWizardButton.setLayoutX(1357);
		charChoiceWizardButton.setLayoutY(812);
		charChoiceAssassinButton.setLayoutX(1555);
		charChoiceAssassinButton.setLayoutY(812);
		charChoicePaladinButton.setLayoutX(1755);
		charChoicePaladinButton.setLayoutY(812);
		
		charChoiceAlchemistButton.setScaleX(1);
		charChoiceAlchemistButton.setScaleY(4.95);
		charChoiceWizardButton.setScaleX(1.1);
		charChoiceWizardButton.setScaleY(4.95);
		charChoiceAssassinButton.setScaleX(1.05);
		charChoiceAssassinButton.setScaleY(4.95);
		charChoicePaladinButton.setScaleX(1.1);
		charChoicePaladinButton.setScaleY(4.95);
		// -------------------------------------------------------------
		paladinPlayerIcon.setLayoutX(1785);
		paladinPlayerIcon.setLayoutY(800);
		assassinPlayerIcon.setLayoutX(1585);
		assassinPlayerIcon.setLayoutY(800);
		wizardPlayerIcon.setLayoutX(1385);
		wizardPlayerIcon.setLayoutY(800);
		alchemistPlayerIcon.setLayoutX(1185);
		alchemistPlayerIcon.setLayoutY(800);
		
		paladinPlayerIcon.setScaleX(1.75);
		paladinPlayerIcon.setScaleY(1.75);
		assassinPlayerIcon.setScaleX(1.75);
		assassinPlayerIcon.setScaleY(1.75);
		wizardPlayerIcon.setScaleX(1.75);
		wizardPlayerIcon.setScaleY(1.75);
		alchemistPlayerIcon.setScaleX(1.75);
		alchemistPlayerIcon.setScaleY(1.75);
		
		
		exitShopBanner.setLayoutX(-440);
		exitShopBanner.setLayoutY(-330);
		exitShopBanner.setScaleX(.3);
		exitShopBanner.setScaleY(.5);
		exitShopBanner.setRotate(90);
		exitShopText.setLayoutX(105);
		exitShopText.setLayoutY(75);
		exitShopText.setScaleX(1.8);
		exitShopText.setScaleY(1.8);
		back.setLayoutX(145); // Position X for back button
		back.setLayoutY(55); // Position Y for back button
		back.setScaleX(6);
		back.setScaleY(3.1);
		itemDescriptions.setLayoutX(1180);
		itemDescriptions.setLayoutY(600);
		
		purchaseConfirmationText.setLayoutX(1300);
		purchaseConfirmationText.setLayoutY(1010);
		purchaseConfirmationText.setScaleX(1.9);
		purchaseConfirmationText.setScaleY(1.9);
		// -------------------------------------------------------------
		shopSeparatonBar1.setLayoutX(1100);
		shopSeparatonBar1.setLayoutY(690);
		shopSeparatonBar1.setScaleX(1);
		shopSeparatonBar1.setScaleY(1.5);
		shopSeparatonBar2.setLayoutX(1100);
		shopSeparatonBar2.setLayoutY(950);
		shopSeparatonBar2.setScaleX(1);
		shopSeparatonBar2.setScaleY(1.5);
		shopSeparatonBar3.setLayoutX(1100);
		shopSeparatonBar3.setLayoutY(1175);
		shopSeparatonBar3.setScaleX(1);
		shopSeparatonBar3.setScaleY(1.5);
		
		item1Price.setLayoutX(1247);
		item1Price.setLayoutY(350);
		item2Price.setLayoutX(1497);
		item2Price.setLayoutY(350);
		item3Price.setLayoutX(1747);
		item3Price.setLayoutY(350);
		
		itemForSaleFrame1.setLayoutX(925);
		itemForSaleFrame1.setLayoutY(-230);
		itemForSaleFrame2.setLayoutX(1175);
		itemForSaleFrame2.setLayoutY(-230);
		itemForSaleFrame3.setLayoutX(1425);
		itemForSaleFrame3.setLayoutY(-230);
		
		itemForSaleFrame1.setScaleX(.5);
		itemForSaleFrame1.setScaleY(.5);
		itemForSaleFrame2.setScaleX(.5);
		itemForSaleFrame2.setScaleY(.5);
		itemForSaleFrame3.setScaleX(.5);
		itemForSaleFrame3.setScaleY(.5);
		
		goldIcon.setLayoutX(160);
		goldIcon.setLayoutY(900);
		goldIcon.setScaleX(1.5);
		goldIcon.setScaleY(1.5);
		playerGold.setLayoutX(375);
		playerGold.setLayoutY(965);
		playerGold.setScaleX(2.25);
		playerGold.setScaleY(2.25);
		// -------------------------------------------------------------
		shopKeeper.setLayoutX(20);
		shopKeeper.setLayoutY(200);
		shopKeeper.setFitWidth(0.55 * 1920);
		shopKeeper.setFitHeight(0.7 * 1080);
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
	
	private void gameOver(Stage primaryStage) {
		Media media = new Media(gameOver);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.05); // Volume level (0.0 to 1.0) use 0.05
		mediaPlayer.play(); // music player

		Image cursorImage = new Image("GUIAssets/cursor.png");
		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
		ImageView enterGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView exitGame = new ImageView(new Image("shopAssets/itemForSaleFrame.png"));
		ImageView gameOverFrame = new ImageView(new Image("applicationImagesBackgrounds/gameOverFrame.png"));
		ImageView gameOverSkeletonLeft = new ImageView(new Image("GUIAssets/gameOverSkeleton.png"));
		ImageView gameOverSkeletonRight = new ImageView(new Image("GUIAssets/gameOverSkeleton.png"));

		Button play = new Button("New Journey");
		Button exit = new Button("Exit Game");
		Text exitGameText = new Text("Exit Game");
		Text newRunText = new Text("New Journey");
		Text gameOver = new Text("Game Over");
		
		play.setOnAction(e -> battleScene(primaryStage));
		exit.setOnAction(e -> homePage(primaryStage));

		gameOver.setFill(Color.web("#d10000"));
		gameOver.setFont(DwarvenAxe);
		newRunText.setFill(Color.web("#d5d5d5"));
		newRunText.setFont(DwarvenAxe);
		newRunText.setFill(Color.web("#d5d5d5"));
		exitGameText.setFont(DwarvenAxe);
		exitGameText.setFill(Color.web("#d5d5d5"));

		gameOverSkeletonLeft.setLayoutX(0);
		gameOverSkeletonLeft.setLayoutY(720);
		gameOverSkeletonLeft.setScaleX(1);
		gameOverSkeletonLeft.setScaleY(1);
		gameOverSkeletonLeft.setScaleX(-1);
		
		gameOverSkeletonRight.setLayoutX(1670);
		gameOverSkeletonRight.setLayoutY(720);
		gameOverSkeletonRight.setScaleX(1);
		gameOverSkeletonRight.setScaleY(1);
		
		
		gameOver.setLayoutX(900);
		gameOver.setLayoutY(350);
		gameOver.setScaleX(4);
		gameOver.setScaleY(4);

		gameOverFrame.setLayoutX(520);
		gameOverFrame.setLayoutY(0);
		gameOverFrame.setScaleX(1);
		gameOverFrame.setScaleY(1);

		enterGame.setLayoutX(310);
		enterGame.setLayoutY(435);
		enterGame.setScaleX(.5);
		enterGame.setScaleY(.8);
		enterGame.setRotate(90);
		enterGame.setMouseTransparent(true);
		exitGame.setLayoutX(310);
		exitGame.setLayoutY(635);
		exitGame.setScaleX(.5);
		exitGame.setScaleY(.8);
		exitGame.setRotate(90);
		exitGame.setMouseTransparent(true);
		
		newRunText.setLayoutX(895);
		newRunText.setLayoutY(775);
		newRunText.setScaleX(2);
		newRunText.setScaleY(2);
		newRunText.setMouseTransparent(true);

		play.setLayoutX(935); // Adjust X for play button
		play.setLayoutY(750); // Adjust Y for play button
		play.setScaleX(4.8);
		play.setScaleY(5);
		play.setOpacity(0);

		exit.setLayoutX(945); // Adjust X for play button
		exit.setLayoutY(950); // Adjust Y for play button
		exit.setScaleX(5.8);
		exit.setScaleY(5);
		exit.setOpacity(0);


		exitGameText.setLayoutX(905);
		exitGameText.setLayoutY(975);
		exitGameText.setScaleX(2);
		exitGameText.setScaleY(2);
		exitGameText.setMouseTransparent(true);


	    play.setOnMouseEntered(e -> {newRunText.setFill(Color.web("#d10000"));});
	    play.setOnMouseExited(e -> {newRunText.setFill(Color.web("#d5d5d5"));});
	    play.setOnAction(e -> {
	        battleScene(primaryStage);
	        mediaPlayer.stop();
	    });
	    
	    exit.setOnMouseEntered(e -> {exitGameText.setFill(Color.web("#d10000"));});
	    exit.setOnMouseExited(e -> {exitGameText.setFill(Color.web("#d5d5d5"));});
	    exit.setOnAction(e -> {
	        homePage(primaryStage);
	        mediaPlayer.stop();
	    });
		
		
	    Image image = new Image("applicationImagesBackgrounds/gameOverBackground.png");
	    // Define the background size and position
	    BackgroundSize size = new BackgroundSize(1, 1, true, true, false, false);
	    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		
		Pane root = new Pane();
		root.getChildren().addAll(gameOverFrame,gameOverSkeletonLeft,gameOverSkeletonRight);
		root.getChildren().addAll(play, exit); 
		root.getChildren().addAll(enterGame, exitGame);
		root.getChildren().addAll(exitGameText, newRunText, gameOver);

	    root.setBackground(new Background(backgroundImage)); 
		
		root.setPadding(new Insets(20));
		Scene scene = new Scene(root, 1920, 1080);
		scene.setCursor(customCursor);

		scene.setCursor(customCursor);
		primaryStage.setTitle("Endless Mountain of Monsters");
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> mediaPlayer.stop());
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
	
//	// putting this on the sideline, in case we decide we need a play options tab.
//	private void playOptions(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//
//		back.setOnAction(e -> homePage(primaryStage));
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(back);
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button width to 40% of the scene's width and height
//		root.getChildren().forEach(button -> {
//			((Button) button).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//			((Button) button).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//		});
//
//		root.setStyle("-fx-background-color: black;");
//		Scene scene = new Scene(root, 1920, 1080);
//
////        primaryStage.setFullScreen(true);
//		primaryStage.setScene(scene);
////        primaryStage.requestFocus();
//
//	}



//	private void unlocks(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//		back.setOnAction(e -> homePage(primaryStage));
//
//		// Create a Label for displaying text
//		Label tutorialText = new Label("This is where we list all the unlocks the player has completed.");
//		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button size properties to scale with the window
//		root.getChildren().forEach(node -> {
//			if (node instanceof Button) {
//				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//			}
//		});
//
//		root.setStyle("-fx-background-color: black;"); // Set background color to black
//		Scene scene = new Scene(root, 1920, 1080);
//		scene.setCursor(customCursor);
//
//		primaryStage.setScene(scene);
//	}
//
//	private void stats(Stage primaryStage) {
//		Image cursorImage = new Image("GUIAssets/cursor.png");
//		Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//		Button back = new Button("Back");
//		back.setOnAction(e -> homePage(primaryStage));
//
//		// Create a Label for displaying text
//		Label tutorialText = new Label("This is where we will keep track of all the player's statistics.");
//		tutorialText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // Set text color to white and font size
//
//		VBox root = new VBox(10);
//		root.getChildren().addAll(tutorialText, back); // Add the Label above the Back button
//		root.setSpacing(10);
//		root.setPadding(new Insets(20));
//
//		// Bind button size properties to scale with the window
//		root.getChildren().forEach(node -> {
//			if (node instanceof Button) {
//				((Button) node).prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.1));
//				((Button) node).prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.04));
//			}
//		});

//		root.setStyle("-fx-background-color: black;"); // Set background color to black
//		Scene scene = new Scene(root, 1920, 1080);
//		scene.setCursor(customCursor);
//
//		primaryStage.setScene(scene);
//	}


//	 private void setupLoadingStage(Stage primaryStage) {
//			Image cursorImage = new Image("GUIAssets/cursor.png");
//			Cursor customCursor = Cursor.cursor(cursorImage.getUrl());
//	        loadingStage = new Stage();
//	        StackPane loadingRoot = new StackPane();
//	        loadingRoot.setStyle("-fx-background-color: black;");
//	        Scene scene = new Scene(loadingRoot, 1920, 1080);
//	        
//		    loadingRoot.sceneProperty().addListener((obs, oldScene, newScene) -> {
//		    });
//	        
//			scene.setCursor(customCursor);
//	        loadingStage.setScene(scene);
//		    primaryStage.setMaximized(true);
//	    }
	public static void main(String[] args) {
		launch(args);
	}
}
