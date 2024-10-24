package application;

import java.util.Comparator;
import java.util.Random;

public class combatFlow {
	
	void runCombat() {
		Random rand = new Random(); 
		int roundCounter = 0;
		int max = 2;
		int min = -2;
		int speedAdjust = rand.nextInt(max - min) + max; // Random Speed adjust with range -2 to 2.
		boolean teamDead = false;
		Object[] order; // Array to use for storing the combat order.
		playerTeamArray playerTeam = new playerTeamArray();
		enemyTeam enemyTeam = new enemyTeam();
		Arrays_Enemy_Teams enemyTeamsArray = new Arrays_Enemy_Teams();
		Object[] turnOrder = new Object[20];
		
		
		enemyTeamsArray = enemyTeamsArray.createSelection(); // Create array of teams to pull from
		int teamSelect = rand.nextInt(15);
		enemyTeam = (enemyTeam) enemyTeamsArray.getTeam(teamSelect); // Select enemy team from array randomly.
		
		
		
		Characters[] temp1 = (Characters[]) playerTeam.getTeam(); // Temp Arrays for merging for turn order
		Enemies[] temp2 = (Enemies[]) enemyTeam.getTeam();
		Object[] temp3 = new Object[20]; // Extra Array for sorting. Should be big enough, increase size if necessary.
		
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
		
		
		// Play Loop
		while (!(teamDead)) {
			teamDead = playerTeam.checkGameOver();
			
		}
	
	}
}