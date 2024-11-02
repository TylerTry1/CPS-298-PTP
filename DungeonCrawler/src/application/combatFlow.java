package application;

import javafx.scene.control.Button;
import java.util.Random;

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
	Object[] order; // Array to use for storing the combat order.
	playerTeamArray playerTeam = new playerTeamArray(4);
	Characters currentCharacter = new Characters();
	enemyTeam enemyTeam = new enemyTeam(4, 4);
	Enemies currentEnemy = new Enemies();
	Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams(4, 4, 15);
	Object[] turnOrder = new Object[8];
	
	
	static public class combatControl extends combatFlow {	
		public combatControl(Button e1, Button e2, Button e3, Button e4){ // This may be the only way to reference the buttons
			enemyPosition1 = e1;										  // in main for enemy position...
			enemyPosition2 = e2;
			enemyPosition3 = e3;
			enemyPosition4 = e4;
	
		}
	
	

	
	// Temp function for testing player input: 
	void tempPlayerChoice(Characters current) {
		
		currentDamage = current.getDamage();
		Enemies[] tempEnemyTeam = enemyTeam.getTeam();
		
		enemyPosition1.setOnAction(e -> {
			currentEnemy = (Enemies) tempEnemyTeam[0];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[0] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
		});
		
		enemyPosition2.setOnAction(e -> {
			currentEnemy = (Enemies) tempEnemyTeam[1];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[1] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
		});
		
		enemyPosition3.setOnAction(e -> {
			currentEnemy = (Enemies) tempEnemyTeam[2];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[2] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
		});
		
		enemyPosition4.setOnAction(e -> {
			currentEnemy = (Enemies) tempEnemyTeam[3];
			tempHealth = currentEnemy.getHealth();
			tempHealth -= currentDamage;
			currentEnemy.setHealth(tempHealth);
			tempEnemyTeam[3] = currentEnemy;
			enemyTeam.setTeam(tempEnemyTeam);
			System.out.println(currentEnemy.name + " damaged for " + currentDamage + ".");
		});
	}
	
	public void determineTurnOrder() {
		
		enemyTeamsArray = enemyTeamsArray.createSelection(); // Create array of teams to pull from
		int teamSelect = rand.nextInt(15);
		enemyTeam = (enemyTeam) enemyTeamsArray.getTeam(teamSelect); // Select enemy team from array randomly.
		
		
		
		Object[] temp1 =  playerTeam.getTeam(); // Temp Arrays for merging for turn order
		Object[] temp2 = enemyTeam.getTeam();
		Object[] temp3;
		temp3 = new Object[8]; // Extra Array for sorting. Should be big enough, increase size if necessary.
		for (int i = 0; i < temp3.length - 1; i++) {
			temp3[i] = new Enemies();
		}
		
		System.arraycopy(temp1, 0, turnOrder, 0, temp1.length); // Merge the player team and the enemy team to determine turn order
		System.arraycopy(temp2, 0, turnOrder, temp1.length, temp2.length);
		
		
		
		// Adjust Speed of all characters for variability. 
		int count = 0;
		for (Object entity : turnOrder) {
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
		
		temp3 = new Object[8]; // Reinitialize temp array for turn sorting.
		for (int i = 0; i < temp3.length - 1; i++) {
			temp3[i] = new Enemies();
		}
		count = 0;
		int i = 0;
		int tempCount = 0;
		
		for (Object entity : turnOrder) {
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
					for (Object entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						if (entity2 instanceof Enemies) {
							if (((Enemies) entity).getSpeed() < ((Enemies) entity2).getSpeed()) {
								tempCount++;
								continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								
							}
							
						}
						else if (entity2 instanceof Characters) {
							if (((Enemies) entity).getSpeed() < ((Characters) entity2).getSpeed()) {
								tempCount++;
								continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
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
					for (Object entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						if (entity2 instanceof Enemies) {
							if (((Characters) entity).getSpeed() < ((Enemies) entity2).getSpeed()) {
								tempCount++;
								continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								
							}
						}
						else if (entity2 instanceof Characters) {
							if (((Characters) entity).getSpeed() < ((Characters) entity2).getSpeed()) {
								tempCount++;
								continue;
							}
							else {
								i = temp3.length - 1;
								while (i > tempCount) {
									temp3[i] = temp3[i - 1];
									i--;
								}
								temp3[tempCount] = entity;
								count++;
								
							}
						}
					}
					System.out.println(((Characters) entity).getName() + " Placed in Order.");
				}
			}
			
		}
		turnOrder = temp3; // Finally Ordered Based on Speed.
		System.out.println("Turn Order Set.");
		
	}
	
	public void runCombat() {
		// Play Loop
			
			teamDead = playerTeam.checkGameOver();
			teamDead = enemyTeam.checkGameOver(); // Check initially for player and enemy health errors.
			
			int abilitySelect;
			double tempHealth;
			int[] positionsToDamage;
			boolean downed = false;
			Characters[] tempTeam = playerTeam.getTeam();
			int turnCount = 0;
			
			System.out.println("Starting Combat:");
			for (Object current : turnOrder) { // Give everyone their turns
				
				System.out.println(current.getClass());
				turnCount++;
				teamDead = playerTeam.checkGameOver();
				teamDead = enemyTeam.checkGameOver(); // Check every character and enemies turn to see if either team is dead.
				System.out.println("Turn " + turnCount);
				/*
				if (teamDead) {
					System.out.println("Combat Over");
				}
				*/
				// Enemy turns: 
				if (current instanceof Enemies) {
					int count = 1;
					Enemies activeEnemy = (Enemies) current;
					System.out.println(activeEnemy.getName()+ "'s Turn.");
					abilitySelect = rand.nextInt(2);
					Object[] result = activeEnemy.abilities[abilitySelect].apply(); // Randomly select and activate ability.
					for (Object damageOrPos : result) { // Iterate through the ability's result (damage and positions)
						if (count == 1) { // Damage
							currentDamage = (double) damageOrPos;
							count++;
						}
						else if (count == 2) { // Positions
							positionsToDamage = (int[]) damageOrPos;
							tempTeam = playerTeam.getTeam();
							for (int currentPos : positionsToDamage) { // Go through all the positions to damage
								switch(currentPos) { // Damage based on position
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
				
				// Player Turns: 
				else if (current instanceof Characters) {
					System.out.println(((Characters) current).getName() + "'s Turn.");
					currentCharacter = (Characters) current;
					tempPlayerChoice(currentCharacter); // Placeholder damage function for testing
				}
				
				
				
			}
			System.out.println("Round Over.");
	
	}
	}
}