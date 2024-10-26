package application;

import java.util.Random;

public class enemyTeam extends Enemies {
	

			int totalChallenge = 0;
			int[] positions = new int[4];
			Object[] team;
			
			int getTotalChallenge() {
				return totalChallenge;
			}
			
			boolean getPosFull(int pos) { // Method to check if position is taken, mostly for fourth position
				
				return positions[pos] == 0;
			}
			
			void addToTeam(int pos, Object enemy) { // Method for adding an enemy to the current team
				team[pos] = enemy;
			}
			
			Object[] getTeam() {
				return team;
			}


}
		
