package application;

public class Money {
	private int gold;
	
	void resetMoney() {
		gold = 0;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void increaseGold(int profit) {
		gold += profit;
	}
	
	public boolean spendGold(int cost) {
		//Checks if players has the required gold to purchase an item, If the players has enough gold it returns true and subtracts the gold
		//if the players dos'nt have enough gold it will return false
		if(gold >= cost) {
			gold -= cost;
			return true;
		}
		else {
			return false;
		}
	}
}
