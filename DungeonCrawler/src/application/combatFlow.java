package application;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class combatFlow {
	
	//public Main main;
	Random rand = new Random(); 
	Button enemyPosition1 = new Button("enemyPosition1");
	Button enemyPosition2 = new Button("enemyPosition2");
	Button enemyPosition3 = new Button("enemyPosition3");
	Button enemyPosition4 = new Button("enemyPosition4");
	int roundCounter = 0;
	int max = 2;
	int min = -2;
	double currentDamage = 0;
	double tempHealth = 0;
	int speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2.
	boolean teamDead = false;
	boolean buttonClicked = false;
	playerTeamArray playerTeam = new playerTeamArray(4);
	Characters currentCharacter = new Characters();
	enemyTeam enemyTeam = new enemyTeam(4, 4);
	Enemies currentEnemy = new Enemies();
	Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams(4, 4, 15);
	entities[] turnOrder = new entities[8];
	int count;
	
	entities[] getTurnOrder() {
		return turnOrder;
	}
	
	playerTeamArray getPlayerTeamArray( ) {
		return playerTeam;
	}
	
	enemyTeam getEnemyTeam() {
		return enemyTeam;
	}
	
	static public class combatControl extends combatFlow {	
		public combatControl(Button e1, Button e2, Button e3, Button e4){ // This may be the only way to reference the buttons
			enemyPosition1 = e1;										  // in main for enemy position...
			enemyPosition2 = e2;
			enemyPosition3 = e3;
			enemyPosition4 = e4;
	
		}
	
	// Temp function for testing player input: 
	void tempPlayerChoice(Characters current, int choice, int tempCount) {
		
		currentDamage = current.getDamage();
		Enemies[] tempEnemyTeam = enemyTeam.getTeam();
		
		switch(choice) {
		case 1:
			currentEnemy = (Enemies) tempEnemyTeam[0];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[0] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			tempCount++;
			break;
		
		case 2:
			currentEnemy = (Enemies) tempEnemyTeam[1];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[1] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			tempCount++;
			break;
		
		case 3:
			currentEnemy = (Enemies) tempEnemyTeam[2];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[2] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			tempCount++;
			break;
		
		case 4:
			currentEnemy = (Enemies) tempEnemyTeam[3];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[3] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
			tempCount++;
			break;
		
		}
	}
	
	public void determineTurnOrder() {
		
		// Create current Enemy Team:
		enemyTeamsArray = enemyTeamsArray.createSelection(); // Create array of teams to pull from
		int teamSelect = rand.nextInt(15);
		enemyTeam = (enemyTeam) enemyTeamsArray.getTeam(teamSelect); // Select enemy team from array randomly.
		//
		
		
		// Create temporary arrays for sorting:
		Characters[] temp1 =  playerTeam.getTeam(); // Temp Arrays for merging for turn order
		Enemies[] temp2 = enemyTeam.getTeam();
		entities[] temp3;
		temp3 = new entities[8]; // Extra Array for sorting. Should be big enough, increase size if necessary.
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
		Characters[] tempTeam = playerTeam.getTeam();
		
		int count = 1;
		Enemies activeEnemy = (Enemies) current;
		System.out.println(activeEnemy.getName()+ "'s Turn.");
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
						 }
					case 2:
						currentCharacter = (Characters) tempTeam[1];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
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
						 }
					case 3:
						currentCharacter = (Characters) tempTeam[2];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
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
						 }
					case 4:
						currentCharacter = (Characters) tempTeam[3];
						 if (currentCharacter.getHealth() != 0) { // If the character is not already down, process damage. 
							 tempHealth = currentCharacter.getHealth();
							 tempHealth -= currentDamage; 
							 if (tempHealth <= 0) {
								 tempHealth = 0;
								 downed = true;
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
          event.consume();  
       }  
          
    };
	
	public int runCombat(int c) {
		// Play Loop
			
			teamDead = playerTeam.checkGameOver();
			teamDead = enemyTeam.checkGameOver(); // Check initially for player and enemy health errors.
			
			buttonClicked = false;
			count = c;

			entities current;
			current = turnOrder[count];
			
			// Automated enemy turn processing
			if (current instanceof Enemies) {
				processEnemyDamage(current);
				count++;
				return count;
			}
			
			// Player turn, waits for button click
			else if (current instanceof Characters) {
				
				if (current instanceof Paladin) {
					System.out.println(((Characters) current).getName() + "'s Turn.");
					while (!buttonClicked) {
						enemyPosition1.setOnMouseClicked(handler);
						enemyPosition2.setOnMouseClicked(handler);
						enemyPosition3.setOnMouseClicked(handler);
						enemyPosition4.setOnMouseClicked(handler);
					}
					return count;
				}
				else if (current instanceof Assassin) {
					System.out.println(((Characters) current).getName() + "'s Turn.");
					while (!buttonClicked) {
						enemyPosition1.setOnMouseClicked(handler);
						enemyPosition2.setOnMouseClicked(handler);
						enemyPosition3.setOnMouseClicked(handler);
						enemyPosition4.setOnMouseClicked(handler);
					}
					return count;
				}
					
				else if (current instanceof Wizard) {
					System.out.println(((Characters) current).getName() + "'s Turn.");
					while (!buttonClicked) {
						enemyPosition1.setOnMouseClicked(handler);
						enemyPosition2.setOnMouseClicked(handler);
						enemyPosition3.setOnMouseClicked(handler);
						enemyPosition4.setOnMouseClicked(handler);
					}
					return count;
				}
				else if (current instanceof Alchemist) {
					System.out.println(((Characters) current).getName() + "'s Turn.");
					while (!buttonClicked) {
						enemyPosition1.setOnMouseClicked(handler);
						enemyPosition2.setOnMouseClicked(handler);
						enemyPosition3.setOnMouseClicked(handler);
						enemyPosition4.setOnMouseClicked(handler);
					}
					return count;
				}
			}
			//System.out.println("Round Over.");
			
			else {
				System.out.println("Round Over.");
				return count;
			}
			
			// Throwaway return statement to avoid compile errors
			return count;
			
	}
	}
}