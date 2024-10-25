package application;

import java.util.Random;


public class Arrays_Enemy_Teams extends enemyTeam {

	int challengeRating = 6; // Hardcoded Challenge Rating for now
	int numOfTeams = 15; // Make 15 Random teams. (Can change if we want)
	Random rand = new Random(); // Random for selection
	Object[] teams; // Array to store the teams
		
	void addToArray(int index, enemyTeam enemyTeam) {
			
		teams[index] = enemyTeam;
	}
		
	int getCR() {
		return challengeRating;
	}
		
	int getNumOfTeams() {
		return numOfTeams;
	}
	
	Object getTeam(int index) { // Get Chosen Team out of array
		return teams[index];
	}
	
	public enemyTeam createTeams(int challengeRating) {
			
			Random rand = new Random(); // Random for selection
			enemyTeam teamComp = new enemyTeam();
			
			// Create Instances of Enemies
			goblinScout gS = new goblinScout(java.util.Optional.empty());
			goblinAxeman gA = new goblinAxeman(java.util.Optional.empty());
			goblinArcher gAr = new goblinArcher(java.util.Optional.empty());
			goblinShaman gSh = new goblinShaman(java.util.Optional.empty());
			skeletonSwordsman sSw = new skeletonSwordsman(java.util.Optional.empty());
			skeletonCrossbowman sCb = new skeletonCrossbowman(java.util.Optional.empty());
			skeletonDefender sD = new skeletonDefender(java.util.Optional.empty());
			necromancer N = new necromancer(java.util.Optional.empty());
			bodyPile bP = new bodyPile(java.util.Optional.empty());
			giantPummeler gPu = new giantPummeler(java.util.Optional.empty());
			giantSlammer gSl = new giantSlammer(java.util.Optional.empty());
			zombiePeasant zP = new zombiePeasant(java.util.Optional.empty());
			zombieKnight zK = new zombieKnight(java.util.Optional.empty());
			minotaur M = new minotaur(java.util.Optional.empty());
			boar B = new boar(java.util.Optional.empty());
			
			// Array to store them in and pull from
			Object[] enemies = new Enemies[15]; // We can change the size as/if we make more enemies
			
			// Put Them In the Array
			enemies[0] = gS;
			enemies[1] = gA;
			enemies[2] = gAr;
			enemies[3] = gSh;
			enemies[4] = sSw;
			enemies[5] = sCb;
			enemies[6] = sD;
			enemies[7] = N;
			enemies[8] = bP;
			enemies[9] = gPu;
			enemies[10] = gSl;
			enemies[11] = zP;
			enemies[12] = zK;
			enemies[13] = M;
			enemies[14] = B;
			
			
			int nextIndex = 0; // Counter to Check if the positions are full
			while (teamComp.getTotalChallenge() < challengeRating && teamComp.getPosFull(3)) { // Check if teams challenge level exceeds 
				int rand1 = rand.nextInt(15);												   // current max or team is full
				Object tempEnemy = enemies[rand1];
				
				teamComp.addToTeam(nextIndex, tempEnemy); // Add the random enemy
				nextIndex++;
			}
		
			return teamComp;
	
	}

	
	public Arrays_Enemy_Teams createSelection() {
		
		Arrays_Enemy_Teams enemyTeamArray = new Arrays_Enemy_Teams();
		int i = 0;
		
		while (i < enemyTeamArray.getNumOfTeams()) {
			enemyTeam tempTeamComp = createTeams(enemyTeamArray.getCR());
			enemyTeamArray.addToArray(i, tempTeamComp);
			i++;
		}
		
		return enemyTeamArray;
	}
	
}
