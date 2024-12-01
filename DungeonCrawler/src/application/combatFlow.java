package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class combatFlow {

	Random rand = new Random(); 
	Button enemyPosition1 = new Button("enemyPosition1");
	Button enemyPosition2 = new Button("enemyPosition2");
	Button enemyPosition3 = new Button("enemyPosition3");
	Button enemyPosition4 = new Button("enemyPosition4");
	Button passTurnButton = new Button("passTurnButton");
	RadioButton skillButtonP1 = new RadioButton("skill 1");
	RadioButton skillButtonP2 = new RadioButton("skill 2");
	RadioButton skillButtonP3 = new RadioButton("skill 3");
	RadioButton skillButtonP4 = new RadioButton("skill 4");
	RadioButton skillButtonA11 = new RadioButton("skill 1");
	RadioButton skillButtonA12 = new RadioButton("skill 2");
	RadioButton skillButtonA13 = new RadioButton("skill 3");
	RadioButton skillButtonA14 = new RadioButton("skill 4");
	RadioButton skillButtonW1 = new RadioButton("skill 1");
	RadioButton skillButtonW2 = new RadioButton("skill 2");
	RadioButton skillButtonW3 = new RadioButton("skill 3");
	RadioButton skillButtonW4 = new RadioButton("skill 4");
	RadioButton skillButtonA21 = new RadioButton("skill 1");
	RadioButton skillButtonA22 = new RadioButton("skill 2");
	RadioButton skillButtonA23 = new RadioButton("skill 3");
	RadioButton skillButtonA24 = new RadioButton("skill 4");
	ImageView skillButtonImageP1 = new ImageView();
	ImageView skillButtonImageP2 = new ImageView();
	ImageView skillButtonImageP3 = new ImageView();
	ImageView skillButtonImageP4 = new ImageView();
	ImageView skillButtonImageA11 = new ImageView();
	ImageView skillButtonImageA12 = new ImageView();
	ImageView skillButtonImageA13 = new ImageView();
	ImageView skillButtonImageA14 = new ImageView();
	ImageView skillButtonImageW1 = new ImageView();
	ImageView skillButtonImageW2 = new ImageView();
	ImageView skillButtonImageW3 = new ImageView();
	ImageView skillButtonImageW4 = new ImageView();
	ImageView skillButtonImageA21 = new ImageView();
	ImageView skillButtonImageA22 = new ImageView();
	ImageView skillButtonImageA23 = new ImageView();
	ImageView skillButtonImageA24 = new ImageView();
	ImageView skillbuttonimagepass = new ImageView();
	ImageView heroTurnTicker1 = new ImageView(); // var
	ImageView heroTurnTicker2 = new ImageView(); // var
	ImageView heroTurnTicker3 = new ImageView(); // var
	ImageView heroTurnTicker4 = new ImageView(); // var
	ImageView enemyTurnTicker1 = new ImageView(); // var
	ImageView enemyTurnTicker2 = new ImageView(); // var
	ImageView enemyTurnTicker3 = new ImageView(); // var
	ImageView enemyTurnTicker4 = new ImageView(); // var
	ImageView heroSelectionIndicator4 = new ImageView();
	ImageView heroSelectionIndicator3 = new ImageView();
	ImageView heroSelectionIndicator2 = new ImageView();
	ImageView heroSelectionIndicator1 = new ImageView();
	ImageView enemySelectionIndicator1 = new ImageView();
	ImageView enemySelectionIndicator2 = new ImageView();
	ImageView enemySelectionIndicator3 = new ImageView();
	ImageView enemySelectionIndicator4 = new ImageView();
	ImageView skillButtonSelectedFrame1 = new ImageView();
	ImageView skillButtonSelectedFrame2 = new ImageView();
	ImageView skillButtonSelectedFrame3 = new ImageView();
	ImageView skillButtonSelectedFrame4 = new ImageView();
	ImageView deathblowEnemy1 = new ImageView();
	ImageView deathblowEnemy2 = new ImageView();
	ImageView deathblowEnemy3 = new ImageView();
	ImageView deathblowEnemy4 = new ImageView();
	ImageView enemyInPosition1 = new ImageView();
	ImageView enemyInPosition2 = new ImageView();
	ImageView enemyInPosition3 = new ImageView();
	ImageView enemyInPosition4 = new ImageView();
	ImageView heroInPosition1 = new ImageView();
	ImageView heroInPosition2 = new ImageView();
	ImageView heroInPosition3 = new ImageView();
	ImageView heroInPosition4 = new ImageView();
	Text heroNameText = new Text();
	Text moveDescriptionText = new Text();
	Text moveDescriptionText2 = new Text();

	
	Image corpseSprite = new Image("/images/CorpsePile.png");
	int roundCounter = 0;
	int max = 2;
	int min = -2;
	double currentDamage = 0;
	double tempHealth = 0;
	int speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2.
	boolean teamDead = false;
	boolean buttonClicked = false;
	playerTeamArray playerTeam = new playerTeamArray(4);
	Characters[] tempTeam = playerTeam.getTeam();
	Characters[] tempTeam2;
	Characters currentCharacter = new Characters();
	enemyTeam enemyTeam = new enemyTeam(4, 4);
	Enemies[] tempEnemyTeam = enemyTeam.getTeam();
	Enemies currentEnemy = new Enemies();
	Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams(4, 4, 15);
	entities[] temp3 = new entities[8]; // Extra Array for sorting. Should be big enough, increase size if necessary.
	entities[] turnOrder = new entities[8];
	int count;
	
	Characters getCurrent() {
		return currentCharacter;
	}
	entities[] getTurnOrder() {
		return turnOrder;
	}
	
	playerTeamArray getPlayerTeamArray( ) {
		return playerTeam;
	}
	
	void setPlayerTeamArray(playerTeamArray set) {
		playerTeam = set;
	}
	
	void resetPlayerTeamArray() {
		playerTeam = new playerTeamArray(4);
	}
	
	Characters[] getTempTeam() {
		return tempTeam;
	}
	
	void setTempTeam(Characters[] set) {
		tempTeam = set;
	}
	
	void resetTempTeam() {
		playerTeam = new playerTeamArray(4);
		tempTeam = playerTeam.getTeam();
	}
	
	enemyTeam getEnemyTeam() {
		return enemyTeam;
	}

	
	static public class combatControl extends combatFlow {	
		public combatControl(Button e1, Button e2, Button e3, Button e4,
							RadioButton p1, RadioButton p2, RadioButton p3, RadioButton p4,
							RadioButton a11, RadioButton a12, RadioButton a13, RadioButton a14,
							RadioButton w1, RadioButton w2, RadioButton w3, RadioButton w4,
							RadioButton a21, RadioButton a22, RadioButton a23, RadioButton a24,
							ImageView ip1, ImageView ip2, ImageView ip3, ImageView ip4,
							ImageView ia11, ImageView ia12, ImageView ia13, ImageView ia14,
							ImageView iw1, ImageView iw2, ImageView iw3, ImageView iw4,
							ImageView ia21, ImageView ia22, ImageView ia23, ImageView ia24,
							ImageView htt1, ImageView htt2, ImageView htt3, ImageView htt4,
							ImageView ett1, ImageView ett2, ImageView ett3, ImageView ett4,
							ImageView hsi4, ImageView hsi3, ImageView hsi2, ImageView hsi1,
							ImageView esi1, ImageView esi2, ImageView esi3, ImageView esi4,
							Text hNT, Text mDT, Text mDT2,
							ImageView sbsf1, ImageView sbsf2, ImageView sbsf3, ImageView sbsf4,
							ImageView dbe1, ImageView dbe2, ImageView dbe3, ImageView dbe4,
							ImageView eip1, ImageView eip2, ImageView eip3, ImageView eip4,
							ImageView hip1, ImageView hip2, ImageView hip3, ImageView hip4,
							playerTeamArray pt, Button ptb, ImageView sbip){ 
			
			enemyPosition1 = e1;										  					 
			enemyPosition2 = e2;
			enemyPosition3 = e3;
			enemyPosition4 = e4;
			skillButtonP1 = p1;
			skillButtonP2 = p2;
			skillButtonP3 = p3;
			skillButtonP4 = p4;
			skillButtonA11 = a11;
			skillButtonA12 = a12;
			skillButtonA13 = a13;
			skillButtonA14 = a14;
			skillButtonW1 = w1;
			skillButtonW2 = w2;
			skillButtonW3 = w3;
			skillButtonW4 = w4;
			skillButtonA21 = a21;
			skillButtonA22 = a22;
			skillButtonA23 = a23;
			skillButtonA24 = a24;
			passTurnButton = ptb;
			skillButtonImageP1 = ip1;
			skillButtonImageP2 = ip2;
			skillButtonImageP3 = ip3;
			skillButtonImageP4 = ip4;
			skillButtonImageA11 = ia11;
			skillButtonImageA12 = ia12;
			skillButtonImageA13 = ia13;
			skillButtonImageA14 = ia14;
			skillButtonImageW1 = iw1;
			skillButtonImageW2 = iw2;
			skillButtonImageW3 = iw3;
			skillButtonImageW4 = iw4;
			skillButtonImageA21 = ia21;
			skillButtonImageA22 = ia22;
			skillButtonImageA23 = ia23;
			skillButtonImageA24 = ia24;
			skillbuttonimagepass = sbip;
			heroTurnTicker1 = htt4;
			heroTurnTicker2 = htt3;
			heroTurnTicker3 = htt2;
			heroTurnTicker4 = htt1;
			enemyTurnTicker1 = ett1;
			enemyTurnTicker2 = ett2;
			enemyTurnTicker3 = ett3;
			enemyTurnTicker4 = ett4;
			heroSelectionIndicator4 = hsi4;
			heroSelectionIndicator3 = hsi3;
			heroSelectionIndicator2 = hsi2;
			heroSelectionIndicator1 = hsi1;
			enemySelectionIndicator1 = esi1;
			enemySelectionIndicator2 = esi2;
			enemySelectionIndicator3 = esi3;
			enemySelectionIndicator4 = esi4;
			heroNameText = hNT;
			moveDescriptionText = mDT;
			moveDescriptionText2 = mDT2;
			skillButtonSelectedFrame1 = sbsf1;
			skillButtonSelectedFrame2 = sbsf2;
			skillButtonSelectedFrame3 = sbsf3;
			skillButtonSelectedFrame4 = sbsf4;
			deathblowEnemy1 = dbe1;
			deathblowEnemy2 = dbe2;
			deathblowEnemy3 = dbe3;
			deathblowEnemy4 = dbe4;
			enemyInPosition1 = eip1;
			enemyInPosition2 = eip2;
			enemyInPosition3 = eip3;
			enemyInPosition4 = eip4;
			heroInPosition1 = hip1;
			heroInPosition2 = hip2;
			heroInPosition3 = hip3;
			heroInPosition4 = hip4;
			playerTeam = pt;
		}
	
	// Temp function for testing player input: 
	void tempPlayerChoice(Characters current, int choice, int tempCount) {
		
		currentDamage = current.getDamage();
		tempEnemyTeam = enemyTeam.getTeam();
		boolean downed = false;
		int defyPercent = 0;
		
		switch(choice) {
		case 1:
			currentEnemy = tempEnemyTeam[0];
			if (currentEnemy.getHealth() != 0) {
				tempHealth = currentEnemy.getHealth();
				tempHealth -= currentDamage;
				if (tempHealth <= 0) {
					defyPercent = rand.nextInt(101 - 1) + 1; // Random from 1 to 100
					if (currentEnemy.getDefy() >= defyPercent) {
						tempHealth = 1;
						if (currentEnemy.getDefy() != 0) // If Death Defy is not 0, reduce by 10
							currentEnemy.setDefy(currentEnemy.getDefy() - 10);
					}
					else {
						tempHealth = 0;
						downed = true;
						deathblowEnemy1.setOpacity(1.0); // Ensure the node is visible
						FadeUtils.deathblow(deathblowEnemy1); // Play the animation
						AudioManager.playEnemyDeathSFX(); // Play the sound effect
						tempEnemyTeam[0].setIdleSprite("/images/CorpsePile.png");
						currentEnemy.setDowned(downed);
					}
				}
				currentEnemy.setHealth(tempHealth);
				tempEnemyTeam[0] = currentEnemy;
				enemyTeam.setTeam(tempEnemyTeam);
				System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			}
			else {
				System.out.println(currentEnemy.name + " is downed.");
			}
			tempCount++;
			break;
		
		case 2:
			currentEnemy = tempEnemyTeam[1];
			if (currentEnemy.getHealth() != 0) {
				tempHealth = currentEnemy.getHealth();
				tempHealth -= currentDamage;
				if (tempHealth <= 0) {
					defyPercent = rand.nextInt(101 - 1) + 1; // Random from 1 to 100
					if (currentEnemy.getDefy() >= defyPercent) {
						tempHealth = 1;
						if (currentEnemy.getDefy() != 0) // If Death Defy is not 0, reduce by 10
							currentEnemy.setDefy(currentEnemy.getDefy() - 10);
					}
					else {
						tempHealth = 0;
						downed = true;
						deathblowEnemy2.setOpacity(1.0); // Ensure the node is visible
						FadeUtils.deathblow(deathblowEnemy2); // Play the animation
						AudioManager.playEnemyDeathSFX(); // Play the sound effect
						tempEnemyTeam[1].setIdleSprite("/images/CorpsePile.png");
						currentEnemy.setDowned(downed);
					}
				}
				currentEnemy.setHealth(tempHealth);
				tempEnemyTeam[1] = currentEnemy;
				enemyTeam.setTeam(tempEnemyTeam);
				System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			}
			else {
				System.out.println(currentEnemy.name + " is downed.");
			}
			tempCount++;
			break;
		
		case 3:
			currentEnemy = tempEnemyTeam[2];
			if (currentEnemy.getHealth() != 0) {
				tempHealth = currentEnemy.getHealth();
				tempHealth -= currentDamage;
				if (tempHealth <= 0) {
					defyPercent = rand.nextInt(101 - 1) + 1; // Random from 1 to 100
					if (currentEnemy.getDefy() >= defyPercent) {
						tempHealth = 1;
						if (currentEnemy.getDefy() != 0) // If Death Defy is not 0, reduce by 10
							currentEnemy.setDefy(currentEnemy.getDefy() - 10);
					}
					else {
						tempHealth = 0;
						downed = true;
						deathblowEnemy3.setOpacity(1.0); // Ensure the node is visible
						FadeUtils.deathblow(deathblowEnemy3); // Play the animation
						AudioManager.playEnemyDeathSFX(); // Play the sound effect
						tempEnemyTeam[2].setIdleSprite("/images/CorpsePile.png");
						currentEnemy.setDowned(downed);
					}
				}
				currentEnemy.setHealth(tempHealth);
				tempEnemyTeam[2] = currentEnemy;
				enemyTeam.setTeam(tempEnemyTeam);
				System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			}
			else {
				System.out.println(currentEnemy.name + " is downed.");
			}
			tempCount++;
			break;
		
		case 4:
			currentEnemy = tempEnemyTeam[3];
			if (currentEnemy.getHealth() != 0) {
				tempHealth = currentEnemy.getHealth();
				tempHealth -= currentDamage;
				if (tempHealth <= 0) {
					defyPercent = rand.nextInt(101 - 1) + 1; // Random from 1 to 100
					if (currentEnemy.getDefy() >= defyPercent) {
						tempHealth = 1;
						if (currentEnemy.getDefy() != 0) // If Death Defy is not 0, reduce by 10
							currentEnemy.setDefy(currentEnemy.getDefy() - 10);
					}
					else {
						tempHealth = 0;
						downed = true;
						deathblowEnemy4.setOpacity(1.0); // Ensure the node is visible
						FadeUtils.deathblow(deathblowEnemy4); // Play the animation
						AudioManager.playEnemyDeathSFX(); // Play the sound effect
						tempEnemyTeam[3].setIdleSprite("/images/CorpsePile.png");
						currentEnemy.setDowned(downed);
					}
				}
				currentEnemy.setHealth(tempHealth);
				tempEnemyTeam[3] = currentEnemy;
				enemyTeam.setTeam(tempEnemyTeam);
				System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			}
			else {
				System.out.println(currentEnemy.name + " is downed.");
			}
			tempCount++;
			break;
		
		}
	}
	
	
	// Might need functions for these, not sure.
	/*
	public void resetEnemyTeam() {
		
	}
	
	public void resetPlayerTeam() {
		
	}
	*/
	
	public void createEnemyTeam() throws IOException {
		// Create current Enemy Team:
		enemyTeamsArray = enemyTeamsArray.createSelection(); // Create array of teams to pull from
		int teamSelect = rand.nextInt(15);
		enemyTeam = (enemyTeam) enemyTeamsArray.getTeam(teamSelect); // Select enemy team from array randomly.
		//
	}
	
	public void adjustSpeeds() {
		
		// Create temporary arrays for sorting:
		Characters[] temp1 =  playerTeam.getTeam(); // Temp Arrays for merging for turn order
		Enemies[] temp2 = enemyTeam.getTeam();
		
		for (int i = 0; i < temp3.length - 1; i++) {
			temp3[i] = new entities();
		}
		
		System.arraycopy(temp1, 0, turnOrder, 0, temp1.length); // Merge the player team and the enemy team to determine turn order
		System.arraycopy(temp2, 0, turnOrder, temp1.length, temp2.length);
		//
		
		
		
		
		// Adjust Speed of all characters for variability. 
		int count = 0;
		for (entities entity : turnOrder) {
			speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2. Change it every time the loop runs for variation
			if (entity instanceof Enemies) {
				((Enemies) entity).adjustSpeed(speedAdjust);
				temp3[count] = entity;
				count++;
				System.out.println(((Enemies) entity).getName() +" Speed Set.");
			}
			else if (entity instanceof Characters) {
				((Characters) entity).adjustSpeed(speedAdjust);
				temp3[count] = entity;
				count++;
				System.out.println(((Characters) entity).getName() + " Speed Set.");
			}
		}
		turnOrder = temp3; // Reinitialize.
		// Speed Adjusted
		
		
	}
	
	public void determineTurnOrder() {
		
		// Reinitialize and set variables
		temp3 = new entities[8]; // Reinitialize temp array for turn sorting.
		for (int i = 0; i < temp3.length - 1; i++) {
			temp3[i] = new entities();
		}
		count = 0;
		int i = 0;
		int tempCount = 0;
		
		for (entities now : turnOrder) {
			System.out.println(now.getClass());;
		}
		//
		
		
		
		// Actual Turn Sorting:
		for (entities entity : turnOrder) {
			// Reset Variables
			i = 0;
			tempCount = 0;
			
			
			// If next element in array is enemy:
			if (entity instanceof Enemies) {
				
				if (count == 0) { // Place it in if empty
					temp3[count] = entity;
					count++;
					System.out.println(((Enemies) entity).getName() + " Placed in Order.");
				}
				
				else {
					for (entities entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						
						if (entity2 instanceof Enemies) {
							if (((Enemies) entity).getSpeed() < ((Enemies) entity2).getSpeed()) {
								tempCount++;
								//continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								break;
							}
							
						}
						
						else if (entity2 instanceof Characters) {
							if (((Enemies) entity).getSpeed() < ((Characters) entity2).getSpeed()) {
								tempCount++;
								//continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								break;
							}
						}
						
						else if (entity2 instanceof entities) {
							i = temp3.length - 1;
							while (i > tempCount) {
								temp3[i] = temp3[i - 1];
								i--;
							}
							temp3[tempCount] = entity;
							count++;
							break;
						}
						
					}
					System.out.println(((Enemies) entity).getName() + " Placed in Order.");
				}
			}
			
			
			
			
			// If next element in array is character:
			else if (entity instanceof Characters) {
				if (count == 0) {  // Place it in if empty
					temp3[count] = entity;
					count++;
					System.out.println(((Characters) entity).getName() + " Placed in Order.");
				}
				else {
					for (entities entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						
						if (entity2 instanceof Enemies) {
							if (((Characters) entity).getSpeed() < ((Enemies) entity2).getSpeed()) {
								tempCount++;
								//continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								break;
							}
						}
						
						else if (entity2 instanceof Characters) {
							if (((Characters) entity).getSpeed() < ((Characters) entity2).getSpeed()) {
								tempCount++;
								//continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								break;
							}
						}
						
						else if (entity2 instanceof entities) {
							i = temp3.length - 1;
							while (i > tempCount) {
								temp3[i] = temp3[i - 1];
								i--;
							}
							temp3[tempCount] = entity;
							count++;
							break;
						}
					}
					System.out.println(((Characters) entity).getName() + " Placed in Order.");
				}
			}
			// 
			
			
		
			
			
		}
		turnOrder = temp3; // Finally Ordered Based on Speed.
		System.out.println("Turn Order Set.");
		
		
		// Testing print statement
		for (entities now : turnOrder) {
			System.out.println(now.getClass());;
		}
		
	}
	
	
	
	public void processEnemyDamage(entities current) {
		
		currentDamage = 0;
		int abilitySelect;
		double tempHealth;
		double[] positionsToDamage;
		boolean downed = false;
		tempTeam = playerTeam.getTeam();
		
		int count = 1;
		Enemies activeEnemy = (Enemies) current;
		//System.out.println(activeEnemy.getName()+ "'s Turn.");
		abilitySelect = rand.nextInt(2);
		double[][] result = (double[][]) activeEnemy.abilities[abilitySelect].apply(); // Randomly select and activate ability.
		
		for (double[] damageOrPos : result) { // Iterate through the ability's result (damage and positions)
			if (count == 1) { // Damage
				currentDamage = damageOrPos[0];
				count++;
			}
			else if (count == 2) { // Positions
				positionsToDamage =  damageOrPos;
				tempTeam = playerTeam.getTeam();
				for (double currentPos : positionsToDamage) { // Go through all the positions to damage
					if (currentPos != 0) {
					switch((int) currentPos) { // Damage based on position
					case 1:
						 currentCharacter = (Characters) tempTeam[0];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
								 currentCharacter.setActiveSprite(currentCharacter.getKneelingSprite());
								 currentCharacter.setDowned(downed); // Adjust character sprite when we can.
							 }
							 currentCharacter.setHealth(tempHealth); // Reinitialize character health
							 tempTeam[0] = currentCharacter; // Reinitialize character in team.
							 playerTeam.setTeam(tempTeam); // Reinitialize team.
							 System.out.println(currentCharacter.name + " damaged for " + currentDamage + ".");
							 break;
						 }
						 else {
							 System.out.println(currentCharacter.name + " is downed.");
							 break;
						 }
					case 2:
						currentCharacter = (Characters) tempTeam[1];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
								 currentCharacter.setActiveSprite(currentCharacter.getKneelingSprite());
								 currentCharacter.setDowned(downed); // Adjust character sprite when we can.
							 }
							 currentCharacter.setHealth(tempHealth); // Reinitialize character health
							 tempTeam[1] = currentCharacter; // Reinitialize character in team.
							 playerTeam.setTeam(tempTeam); // Reinitialize team.
							 System.out.println(currentCharacter.name + " damaged for " + currentDamage + ".");
							 break;
						 }
						 else {
							 System.out.println(currentCharacter.name + " is downed.");
							 break;
						 }
					case 3:
						currentCharacter = (Characters) tempTeam[2];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
								 currentCharacter.setActiveSprite(currentCharacter.getKneelingSprite());
								 currentCharacter.setDowned(downed); // Adjust character sprite when we can.
							 }
							 currentCharacter.setHealth(tempHealth); // Reinitialize character health
							 tempTeam[2] = currentCharacter; // Reinitialize character in team.
							 playerTeam.setTeam(tempTeam); // Reinitialize team.
							 System.out.println(currentCharacter.name + " damaged for " + currentDamage + ".");
							 break;
						 }
						 else {
							 System.out.println(currentCharacter.name + " is downed.");
							 break;
						 }
					case 4:
						currentCharacter = (Characters) tempTeam[3];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
								 currentCharacter.setActiveSprite(currentCharacter.getKneelingSprite());
								 currentCharacter.setDowned(downed); // Adjust character sprite when we can.
							 }
							 currentCharacter.setHealth(tempHealth); // Reinitialize character health
							 tempTeam[3] = currentCharacter; // Reinitialize character in team.
							 playerTeam.setTeam(tempTeam); // Reinitialize team.
							 System.out.println(currentCharacter.name + " damaged for " + currentDamage + ".");
							 break;
						 }
						 else {
							 System.out.println(currentCharacter.name + " is downed.");
							 break;
						 }
					}
					}
				}
			}
		}
	}
	
    //Creating EventHandler   
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
       
       @Override  
       public void handle(MouseEvent event) {  
          // TODO Auto-generated method stub  
          if(event.getSource()==enemyPosition1) {  
        	  tempPlayerChoice(currentCharacter, 1, count);
        	  count++;
        	  buttonClicked = true;
        	  return;
          }  
          if(event.getSource()==enemyPosition2) {  
        	  tempPlayerChoice(currentCharacter, 2, count);
        	  count++;
        	  buttonClicked = true;
        	  return;
          }  
          if(event.getSource()==enemyPosition3) {  
        	  tempPlayerChoice(currentCharacter, 3, count);
        	  count++;
        	  buttonClicked = true;
        	  return;
          }  
          if(event.getSource()==enemyPosition4) {  
        	  tempPlayerChoice(currentCharacter, 4, count);
        	  count++;
        	  buttonClicked = true;
        	  return;
          }  
          if(event.getSource()==passTurnButton) {
        	  count++;
        	  buttonClicked = true;
        	  return;
          }
          event.consume();  
       }  
          
    };
	
    
    
    public int runCombat(int c) {
    	
    	
		// Play Loop
		Map<String, String> skillDescriptions = new HashMap<>();
		skillDescriptions.put("Shield of Faith", "A shield that protects an ally from harm.");
		skillDescriptions.put("Divine Smite", "A powerful strike that channels divine energy.");
		skillDescriptions.put("Aura of Courage", "An aura that grants immunity to fear.");
		skillDescriptions.put("Holy Rampart", "Creates a defensive barrier that protects allies.");
		skillDescriptions.put("Shadow Strike", "A high-damage attack from the shadows.");
		skillDescriptions.put("Poisoned Blade", "Applies poison to the enemy, causing damage over time.");
		skillDescriptions.put("Backstab", "Creates smoke to disorient enemies, reducing their accuracy.");
		skillDescriptions.put("Dagger Barrage", "Increases movement speed and grants the ability to avoid enemy detection.");
		skillDescriptions.put("Fireball", "A powerful fire spell that causes explosive damage.");
		skillDescriptions.put("Magic Missile", "A defensive shield made of ice that reduces incoming damage.");
		skillDescriptions.put("Frost Bolt", "Teleports to a target location, avoiding damage.");
		skillDescriptions.put("Staff Strike", "A burst of magical energy that damages all enemies in range.");
		skillDescriptions.put("Acid Puddle", "Restores health to the target.");
		skillDescriptions.put("Healing Elixir", "Throws a bomb that deals explosive damage to enemies.");
		skillDescriptions.put("Invigoration", "Sprays acid that damages and lowers enemy defense.");
		skillDescriptions.put("Explosive Flask", "Makes the user invisible for a short period.");
			
			teamDead = playerTeam.checkGameOver();
			teamDead = enemyTeam.checkGameOver(); // Check initially for player and enemy health errors.
			
			buttonClicked = false;
			count = c;

			entities current;
			current = turnOrder[count];
			//currentCharacter = (Characters) current;
			
			// Automated enemy turn processing
			if (current instanceof Enemies) {
				System.out.println(((Enemies) current).getName() + "'s Turn.");
				
				// Enemy Selection Indicator Visible During Turn
				if (((Enemies) current) == enemyTeam.getTeam()[0]) {
					enemySelectionIndicator1.setVisible(true);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[1]) {
					enemySelectionIndicator2.setVisible(true);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[2]) {
					enemySelectionIndicator3.setVisible(true);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[3]) {
					enemySelectionIndicator4.setVisible(true);
				}
				
				if (((Enemies) current).getHealth() != 0.0)
					processEnemyDamage(current);
				else
					System.out.println(((Enemies) current).getName() + " is downed.");
				
				// Turn Ticker's Invisible After Processing Damage
				if (((Enemies) current) == enemyTeam.getTeam()[0]) {
					enemySelectionIndicator1.setVisible(false);
					enemyTurnTicker1.setVisible(false);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[1]) {
					enemySelectionIndicator2.setVisible(false);
					enemyTurnTicker2.setVisible(false);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[2]) {
					enemySelectionIndicator3.setVisible(false);
					enemyTurnTicker3.setVisible(false);
				}
				else if (((Enemies) current) == enemyTeam.getTeam()[3]) {
					enemySelectionIndicator4.setVisible(false);
					enemyTurnTicker4.setVisible(false);
				}
				
				count++;
				//playerTeam.setTeam(tempTeam);
				return count;
			}
			
			// Player turn, waits for button click
			// Add skip turn/skill buttons, etc in here. 
			else if (current instanceof Characters) {
				
				currentCharacter = (Characters) current;
				
				if (current instanceof Paladin) {
					
					
					System.out.println(((Characters) current).getName() + "'s Turn.");
					heroNameText.setText(((Characters) current).getName());
					moveDescriptionText.setText("DMG: " + ((Characters) current).getDamage() + " Crit %: " + ((Characters) current).getCritChance());
					moveDescriptionText2.setText("Test");
					
					if(((Characters)current).getHealth() == 0) {
						System.out.println(((Characters) current).getName() + " is downed.");
						heroTurnTicker1.setVisible(false);
						count++;
					}
					else {
						while (!buttonClicked) {
							//all other buttons and images cannot be clicked or seen.
							skillButtonImageP1.setOpacity(100); //image is visible
							skillButtonImageP2.setOpacity(100);
							skillButtonImageP3.setOpacity(100);
							skillButtonImageP4.setOpacity(100);
							skillbuttonimagepass.setOpacity(100);
							skillButtonP1.setMouseTransparent(false); //button is clickable
							skillButtonP2.setMouseTransparent(false);
							skillButtonP3.setMouseTransparent(false);
							skillButtonP4.setMouseTransparent(false);
							passTurnButton.setMouseTransparent(false);
							heroSelectionIndicator1.setVisible(true);
							enemyPosition1.setOnMouseClicked(handler);
							enemyPosition2.setOnMouseClicked(handler);
							enemyPosition3.setOnMouseClicked(handler);
							enemyPosition4.setOnMouseClicked(handler);
							passTurnButton.setOnMouseClicked(handler);
							
							skillButtonP1.setOnMouseClicked(event -> {
								
				                moveDescriptionText2.setText("Skill: Shield of Faith\n" + skillDescriptions.get("Shield of Faith"));
				            });
				            skillButtonP2.setOnMouseClicked(event -> {
				                moveDescriptionText2.setText("Skill: Divine Smite\n" + skillDescriptions.get("Divine Smite"));
				            });
				            skillButtonP3.setOnMouseClicked(event -> {
				                moveDescriptionText2.setText("Skill: Aura of Courage\n" + skillDescriptions.get("Aura of Courage"));
				            });
				            skillButtonP4.setOnMouseClicked(event -> {
				                moveDescriptionText2.setText("Skill: Holy Rampart\n" + skillDescriptions.get("Holy Rampart"));
				            });
				            
				        }
							
						skillButtonImageP1.setOpacity(0); 
						skillButtonImageP2.setOpacity(0);
						skillButtonImageP3.setOpacity(0);
						skillButtonImageP4.setOpacity(0);
						skillbuttonimagepass.setOpacity(0);
						skillButtonP1.setMouseTransparent(true); 
						skillButtonP2.setMouseTransparent(true);
						skillButtonP3.setMouseTransparent(true);
						skillButtonP4.setMouseTransparent(true);
						passTurnButton.setMouseTransparent(true);
						skillButtonSelectedFrame1.setVisible(false);
						skillButtonSelectedFrame2.setVisible(false);
						skillButtonSelectedFrame3.setVisible(false);
						skillButtonSelectedFrame4.setVisible(false);
						heroTurnTicker1.setVisible(false);
						heroSelectionIndicator1.setVisible(false);
						heroNameText.setText("");
						moveDescriptionText.setText("");
					}
					//playerTeam.setTeam(tempTeam);
					return count;
				}
				else if (current instanceof Assassin) {
					
					System.out.println(((Characters) current).getName() + "'s Turn.");
					heroNameText.setText(((Characters) current).getName());
					moveDescriptionText.setText("DMG: " + ((Characters) current).getDamage() + " Crit %: " + ((Characters) current).getCritChance());
					moveDescriptionText2.setText("");

					
					if(((Characters)current).getHealth() == 0) {
						System.out.println(((Characters) current).getName() + " is downed.");
						heroTurnTicker2.setVisible(false);
						count++;
					}
					else {
						while (!buttonClicked) {
							
							//all other buttons and images cannot be clicked or seen.
							skillButtonImageA11.setOpacity(100); //image is visible
							skillButtonImageA12.setOpacity(100);
							skillButtonImageA13.setOpacity(100);
							skillButtonImageA14.setOpacity(100);
							skillbuttonimagepass.setOpacity(100);
							skillButtonA11.setMouseTransparent(false); //button is clickable
							skillButtonA12.setMouseTransparent(false);
							skillButtonA13.setMouseTransparent(false);
							skillButtonA14.setMouseTransparent(false);
							passTurnButton.setMouseTransparent(false);
							heroSelectionIndicator2.setVisible(true);
							enemyPosition1.setOnMouseClicked(handler);
							enemyPosition2.setOnMouseClicked(handler);
							enemyPosition3.setOnMouseClicked(handler);
							enemyPosition4.setOnMouseClicked(handler);
							passTurnButton.setOnMouseClicked(handler);
							
							skillButtonA11.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Shadow Strike\n" + skillDescriptions.get("Shadow Strike"));
							});
							skillButtonA12.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Poisoned Blade\n" + skillDescriptions.get("Poisoned Blade"));
							});
							skillButtonA13.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Smoke Bomb\n" + skillDescriptions.get("Backstab"));
							});
							skillButtonA14.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Silent Step\n" + skillDescriptions.get("Dagger Barrage"));
							});
							
						}
						skillButtonImageA11.setOpacity(0);
						skillButtonImageA12.setOpacity(0);
						skillButtonImageA13.setOpacity(0);
						skillButtonImageA14.setOpacity(0);
						skillbuttonimagepass.setOpacity(0);
						skillButtonA11.setMouseTransparent(true);
						skillButtonA12.setMouseTransparent(true);
						skillButtonA13.setMouseTransparent(true);
						skillButtonA14.setMouseTransparent(true);
						passTurnButton.setMouseTransparent(true);
						skillButtonSelectedFrame1.setVisible(false);
						skillButtonSelectedFrame2.setVisible(false);
						skillButtonSelectedFrame3.setVisible(false);
						skillButtonSelectedFrame4.setVisible(false);
						heroTurnTicker2.setVisible(false);
						heroSelectionIndicator2.setVisible(false);
						heroNameText.setText("");
						moveDescriptionText.setText("");
						moveDescriptionText2.setText("");
					}
					//playerTeam.setTeam(tempTeam);
					return count;
				}
					
				else if (current instanceof Wizard) {
					
					System.out.println(((Characters) current).getName() + "'s Turn.");
					heroNameText.setText(((Characters) current).getName());
					moveDescriptionText.setText("DMG: " + ((Characters) current).getDamage() + " Crit %: " + ((Characters) current).getCritChance());
					moveDescriptionText2.setText("");

					
					if(((Characters)current).getHealth() == 0) {
						System.out.println(((Characters) current).getName() + " is downed.");
						heroTurnTicker3.setVisible(false);
						count++;
					}
					else {
						while (!buttonClicked) {
							//all other buttons and images cannot be clicked or seen.
							skillButtonImageW1.setOpacity(100); //image is visible
							skillButtonImageW2.setOpacity(100);
							skillButtonImageW3.setOpacity(100);
							skillButtonImageW4.setOpacity(100);
							skillbuttonimagepass.setOpacity(100);
							skillButtonW1.setMouseTransparent(false); //button is clickable
							skillButtonW2.setMouseTransparent(false);
							skillButtonW3.setMouseTransparent(false);
							skillButtonW4.setMouseTransparent(false);
							passTurnButton.setMouseTransparent(false);
							heroSelectionIndicator3.setVisible(true);
							enemyPosition1.setOnMouseClicked(handler);
							enemyPosition2.setOnMouseClicked(handler);
							enemyPosition3.setOnMouseClicked(handler);
							enemyPosition4.setOnMouseClicked(handler);
							passTurnButton.setOnMouseClicked(handler);
							
							skillButtonW1.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Fireball\n" + skillDescriptions.get("Fireball"));
							});
							skillButtonW2.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Ice Shield\n" + skillDescriptions.get("Magic Missile"));
							});
							skillButtonW3.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Teleport\n" + skillDescriptions.get("Frost Bolt"));
							});
							skillButtonW4.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Arcane Blast\n" + skillDescriptions.get("Staff Strike"));
							});
						}
						skillButtonImageW1.setOpacity(0); //image is visible
						skillButtonImageW2.setOpacity(0);
						skillButtonImageW3.setOpacity(0);
						skillButtonImageW4.setOpacity(0);
						skillbuttonimagepass.setOpacity(0);
						skillButtonW1.setMouseTransparent(true); //button is clickable
						skillButtonW2.setMouseTransparent(true);
						skillButtonW3.setMouseTransparent(true);
						skillButtonW4.setMouseTransparent(true);
						passTurnButton.setMouseTransparent(true);
						skillButtonSelectedFrame1.setVisible(false);
						skillButtonSelectedFrame2.setVisible(false);
						skillButtonSelectedFrame3.setVisible(false);
						skillButtonSelectedFrame4.setVisible(false);
						heroTurnTicker3.setVisible(false);
						heroSelectionIndicator3.setVisible(false);
						heroNameText.setText("");
						moveDescriptionText.setText("");
						moveDescriptionText2.setText("");

					}
					//playerTeam.setTeam(tempTeam);
					return count;
				}
				else if (current instanceof Alchemist) {
					
					System.out.println(((Characters) current).getName() + "'s Turn.");
					heroNameText.setText(((Characters) current).getName());
					moveDescriptionText.setText("DMG: " + ((Characters) current).getDamage() + " Crit %: " + ((Characters) current).getCritChance());
					moveDescriptionText2.setText("");

					
					if(((Characters)current).getHealth() == 0) {
						System.out.println(((Characters) current).getName() + " is downed.");
						heroTurnTicker4.setVisible(false);
						count++;
					}
					else {
						while (!buttonClicked) {
							//all other buttons and images cannot be clicked or seen.
							skillButtonImageA21.setOpacity(100); //image is visible
							skillButtonImageA22.setOpacity(100);
							skillButtonImageA23.setOpacity(100);
							skillButtonImageA24.setOpacity(100);
							skillbuttonimagepass.setOpacity(100);
							skillButtonA21.setMouseTransparent(false); //button is clickable
							skillButtonA22.setMouseTransparent(false);
							skillButtonA23.setMouseTransparent(false);
							skillButtonA24.setMouseTransparent(false);
							passTurnButton.setMouseTransparent(false);
							heroSelectionIndicator4.setVisible(true);
							enemyPosition1.setOnMouseClicked(handler);
							enemyPosition2.setOnMouseClicked(handler);
							enemyPosition3.setOnMouseClicked(handler);
							enemyPosition4.setOnMouseClicked(handler);
							passTurnButton.setOnMouseClicked(handler);
							
							skillButtonA21.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Healing Potion\n" + skillDescriptions.get("Acid Puddle"));
							});
							skillButtonA22.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Bomb Toss\n" + skillDescriptions.get("Healing Elixir"));
							});
							skillButtonA23.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Acid Spray\n" + skillDescriptions.get("Invigoration"));
							});
							skillButtonA24.setOnMouseClicked(event -> {
							    moveDescriptionText2.setText("Skill: Cloak of Invisibility\n" + skillDescriptions.get("Explosive Flask"));
							});
						}
						skillButtonImageA21.setOpacity(0);
						skillButtonImageA22.setOpacity(0);
						skillButtonImageA23.setOpacity(0);
						skillButtonImageA24.setOpacity(0);
						skillbuttonimagepass.setOpacity(0);
						skillButtonA21.setMouseTransparent(true);
						skillButtonA22.setMouseTransparent(true);
						skillButtonA23.setMouseTransparent(true);
						skillButtonA24.setMouseTransparent(true);
						passTurnButton.setMouseTransparent(true);
						skillButtonSelectedFrame1.setVisible(false);
						skillButtonSelectedFrame2.setVisible(false);
						skillButtonSelectedFrame3.setVisible(false);
						skillButtonSelectedFrame4.setVisible(false);
						heroTurnTicker4.setVisible(false);
						heroSelectionIndicator4.setVisible(false);
						heroNameText.setText("");
						moveDescriptionText.setText("");
						moveDescriptionText2.setText("");

					}
					//playerTeam.setTeam(tempTeam);
					return count;
				}
			}
			//System.out.println("Round Over.");
			
			else {
				System.out.println("Round Over.");
				//playerTeam.setTeam(tempTeam);
				return count;
			}
			
			// Throwaway return statement to avoid compile errors
			//playerTeam.setTeam(tempTeam);
			return count;
			
	}
	}
}
