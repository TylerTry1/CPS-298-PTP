package application;

//import java.awt.Button;
import java.util.Random;

import application.Enemies.AbilityFunctions;

public class combatFlow {
	
	Random rand = new Random(); 
	int roundCounter = 0;
	int totalRouns = 0; // For Tracking total number of rounds survived across all fights.
	int max = 2;
	int min = -2;
	int speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2.
	boolean teamDead = false;
	playerTeamArray playerTeam = new playerTeamArray();
	enemyTeam enemyTeam = new enemyTeam();
	Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams();
	Object[] turnOrder = new Object[20];
	AbilityFunctions[] abilities;
	AbilityFunctions chosenAbility;
	Object[] result;
	
	
	// Function for determining turn order
	Object[] determineTurnOrder(Object[] temp3, Object[] turnOrder) {
		
		// Adjust Speed of all characters for variability. 
		int count = 0;
		for (Object entity : turnOrder) {
			speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2. Change it every time the loop runs for variation
			if (entity instanceof Enemies) {
				((Enemies) entity).adjustSpeed(speedAdjust);
				temp3[count] = entity;
				count++;
			}
			else if (entity instanceof Characters) {
				((Characters) entity).adjustSpeed(speedAdjust);
				temp3[count] = entity;
				count++;
			}
		}
		turnOrder = temp3; // Reinitialize.
		
		temp3 = new Object[20]; // Reinitialize temp array for turn sorting.
		count = 0;
		int i = 0;
		
		for (Object entity : turnOrder) {
			i = 0;
			// If next thing in array is enemy:
			if (entity instanceof Enemies) {
				if (temp3.length == 0) { // Place it in if empty
					temp3[count] = entity;
					count++;
				}
				else {
					for (Object entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						if (entity2 instanceof Enemies) {
							if (((Enemies) entity).getSpeed() >= ((Enemies) entity2).getSpeed()) {
								temp3[count - 1] = entity; // Readjust indexes
								i = temp3.length;
								while (i > count) {
									temp3[i] = temp3[i -1];
									i--;
								}
								temp3[count] = entity2;
								count++;
							}
						}
						else if (entity2 instanceof Characters) {
							if (((Enemies) entity).getSpeed() >= ((Characters) entity2).getSpeed()) {
								temp3[count - 1] = entity; // Readjust indexes
								i = temp3.length;
								while (i > count) {
									temp3[i] = temp3[i -1];
									i--;
								}
								temp3[count] = entity2;
								count++;
							}
						}
					}
				}
			}
			
			// If next thing in array is character:
			else if (entity instanceof Characters) {
				if (temp3.length == 0) {  // Place it in if empty
					temp3[count] = entity;
					count++;
				}
				else {
					for (Object entity2 : temp3) { // Otherwise iterate over the current turn order for comparison
						if (entity2 instanceof Enemies) {
							if (((Enemies) entity).getSpeed() >= ((Enemies) entity2).getSpeed()) {
								temp3[count - 1] = entity; // Readjust indexes
								i = temp3.length;
								while (i > count) {
									temp3[i] = temp3[i -1];
									i--;
								}
								temp3[count] = entity2;
								count++;
							}
						}
						else if (entity2 instanceof Characters) {
							if (((Enemies) entity).getSpeed() >= ((Characters) entity2).getSpeed()) {
								temp3[count - 1] = entity; // Readjust indexes
								i = temp3.length;
								while (i > count) {
									temp3[i] = temp3[i -1];
									i--;
								}
								temp3[count] = entity2;
								count++;
							}
						}
					}
				}
			}
		}
		
		turnOrder = temp3; // Finally Ordered Based on Speed.
		
		return turnOrder;
	}
	
	
	void runCombat() {
		
		int abilitySelect;
		double currentDamage = 0;
		int resultCount; // For Directing logic on damage or positioning.
		Object[] tempTeam;
		double tempHealth;
		roundCounter = 0; // In case function has to get called repeatedly, reset.
		enemyTeamsArray = enemyTeamsArray.createSelection(); // Create array of teams to pull from
		int teamSelect = rand.nextInt(15);
		enemyTeam = (enemyTeam) enemyTeamsArray.getTeam(teamSelect); // Select enemy team from array randomly.
		
		
		
		Characters[] temp1 = (Characters[]) playerTeam.getTeam(); // Temp Arrays for merging for turn order
		Enemies[] temp2 = (Enemies[]) enemyTeam.getTeam();
		Object[] temp3 = new Object[20]; // Extra Array for sorting. Should be big enough, increase size if necessary.
		
		System.arraycopy(temp1, 0, turnOrder, 0, temp1.length); // Merge the player team and the enemy team to determine turn order
		System.arraycopy(temp2, 0, turnOrder, temp1.length, temp2.length);
		
		// First Round
		roundCounter++;
	
		// Play Loop
		while (!(teamDead)) {
			teamDead = playerTeam.checkGameOver();
			teamDead = enemyTeam.checkGameOver(); // Check if player team or enemy team are dead.
			
			turnOrder = determineTurnOrder(temp3, turnOrder); // Set Turn Order for new Round.
			
			// Give Everyone their turns. 
			for (Object entity : turnOrder) {
				
				teamDead = playerTeam.checkGameOver();
				teamDead = enemyTeam.checkGameOver(); // Check if player team or enemy team are dead every turn.
				
				// Enemy Turn
				if (entity instanceof Enemies) { // If it is an enemy turn, randomly select their ability. Can Specify choice with more logic later.
					abilities = ((Enemies) entity).getAbilities(); // Get array of abilities.
					abilitySelect = rand.nextInt(2); // Rand for ability selection.
					chosenAbility = abilities[abilitySelect]; 
					result = chosenAbility.apply(); // Store Abilities effects in Object Array to iterate over.
					resultCount = 1;
					if (result != null) {
						for (Object temp : result) {
							if (resultCount == 1) { // Store Damage from first result
								currentDamage = (double) temp;
							}
							else if (resultCount == 2) {
								int[] positions = (int[]) temp; // Cast and store in array for iteration over positions
								tempTeam = playerTeam.getTeam(); // Copy Player Team for health adjustment.
								for (int pos : positions) {
									if(((Characters) tempTeam[pos]).getHealth() != 0) {
										tempHealth = ((Characters) tempTeam[pos]).getHealth();
										tempHealth -= currentDamage;
										((Characters) tempTeam[pos]).setHealth(tempHealth);
									}
									else {
										if (pos > 1) {
											pos -= 1;
											if(((Characters) tempTeam[pos]).getHealth() != 0) {
												tempHealth = ((Characters) tempTeam[pos]).getHealth();
												tempHealth -= currentDamage;
												((Characters) tempTeam[pos]).setHealth(tempHealth);
											}
											else {
												continue;
											}
										}
										else {
											continue;
										}
									}
								}
								playerTeam.setTeam(tempTeam); // Reset player team after damage is processed.
								
							}
							resultCount++;
						}
					}
					else {
						continue; // If the ability is passive buffs they will be activated on the "apply" function call above when its applied to result.
					}
				}
				
				
				// Player Turn
				else if (entity instanceof Characters) { // If it's player turn wait for player's choice.
					
				}
			}
			
		}
	
	}
}