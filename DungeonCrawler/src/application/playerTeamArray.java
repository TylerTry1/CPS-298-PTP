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
		boolean teamDead = false;
		for (Object character : playerTeam) {
			if (((Characters) character).getHealth() != 0) {
				continue;
			}
			else {
				teamDead = true;
				return teamDead;
			}
		}
		return teamDead;
	}
	
	Object[] getTeam() {
		return playerTeam;
	}
}