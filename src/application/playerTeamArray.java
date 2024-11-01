package application;

public class playerTeamArray {
	
	Alchemist alchemist = new Alchemist();
	Assassin assassin = new Assassin();
	Paladin paladin = new Paladin();
	Wizard wizard = new Wizard();
	
	Characters[] playerTeam;
	
	
	void addToArray() {
		playerTeam[0] = paladin;
		playerTeam[1] = assassin;
		playerTeam[2] = alchemist;
		playerTeam[3] = wizard;
	}
	
	public playerTeamArray(int size) {
		this.playerTeam = new Characters[4];
		addToArray();
	}
	
	boolean checkGameOver() {
		boolean teamDead = false;
		for (Object character : this.playerTeam) {
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
	
	Characters[] getTeam() {
		return this.playerTeam;
	}
	
	void setTeam(Characters[] team) {
		this.playerTeam = team;
	}
}