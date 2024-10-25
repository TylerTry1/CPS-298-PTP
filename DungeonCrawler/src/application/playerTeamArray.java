package application;

import application.Alchemist;
import application.Assassin;
import application.Paladin;
import application.Wizard;

public class playerTeamArray {
	
	Alchemist alchemist = new Alchemist();
	Assassin assassin = new Assassin();
	Paladin paladin = new Paladin();
	Wizard wizard = new Wizard();
	
	Object[] playerTeam;
	
	void addToArray() {
		playerTeam[0] = paladin;
		playerTeam[1] = assassin;
		playerTeam[2] = alchemist;
		playerTeam[3] = wizard;
	}
	
	boolean checkGameOver() {
		int numDead = 0;
		boolean teamDead = false;
		for (Object character : playerTeam) {
			if (((Characters) character).getHealth() != 0) {
				continue;
			}
			else {
				numDead++;
			}
		}
		
		if (numDead == 4) {
			teamDead = true;
		}
		
		return teamDead;
	}
	
	Object[] getTeam() {
		return playerTeam;
	}
	
	void setTeam(Object[] team) {
		this.playerTeam = team;
	}
}