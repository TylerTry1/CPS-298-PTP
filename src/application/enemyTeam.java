package application;


public class enemyTeam extends Enemies {
	

			int totalChallenge = 0;
			int[] positions;
			Enemies[] team;
			
			void addToArray() { // Generic Array Creation to Avoid compile errors
				team[0] = new Enemies();
				team[1] = new Enemies();
				team[2] = new Enemies();
				team[3] = new Enemies();
			}
			
			public enemyTeam(int pos, int size) {
				this.positions = new int[pos];
				this.team = new Enemies[size];
				addToArray();
				
			}
			
			int getTotalChallenge() {
				return totalChallenge;
			}
			
			boolean getPosFull(int pos) { // Method to check if position is taken, mostly for fourth position
				
				return positions[pos] == 0;
			}
			
			void addToTeam(int pos, Enemies enemy) { // Method for adding an enemy to the current team
				this.team[pos] = enemy;
			}
			
			Enemies[] getTeam() {
				return team;
			}
			
			void setTeam(Enemies[] tempTeam) {
				team = tempTeam;
			}
			
			boolean checkGameOver() {
				boolean teamDead = false;
				for (Object enemy : this.team) {
					if (((Enemies) enemy).getHealth() != 0) {
						continue;
					}
					else {
						teamDead = true;
						return teamDead;
					}
				}
				return teamDead;
			}


}
		
