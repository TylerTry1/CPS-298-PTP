package application;


public class Items {
	private String name;
	private String itemDescription;
	private String itemToolTip;
	private String imageLocation;
	private int itemPrice;
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	
	public String getItemToolTip() {
		return itemToolTip;
	}

	public int getItemPrice() {
		return itemPrice;
	}
	
	
	public String getImageLocation() {
		return imageLocation;
	}


	public class Clinic extends Items {
		Clinic() {
	        name = "Clinic";
	        imageLocation = "shopAssets/clinic.png";
	        itemDescription = "heals the entire team to full HP";
	        itemToolTip = "a little help impeccably timed.";
	        itemPrice = 400;
	    }
		
		//fully restores heros to max health
		public void fullHeal(Characters hero1, Characters hero2, Characters hero3, Characters hero4) {
			hero1.setHealth(hero1.getMaxHealth());
			hero2.setHealth(hero2.getMaxHealth());
			hero3.setHealth(hero3.getMaxHealth());
			hero4.setHealth(hero4.getMaxHealth());
		}
	}
	
	public class Whetstone extends Items {
		Whetstone() {
	        name = "Whetstone";
	        imageLocation = "shopAssets/whetstone.png";
	        itemDescription = "increases character’s damage by 1.1x";
	        itemToolTip = "sharpened to a razor’s edge.";
	        itemPrice = 700;
	    }
		
		//increases one characters damage by 1.1x
		public void increaseDamage(Characters hero) {
			hero.setDamage((double) (hero.getDamage()*1.1));
		}
	}
	
	public class SmellingSalts extends Items {
		SmellingSalts() {
	        name = "Smelling Salts";
	        imageLocation = "shopAssets/smellingSalts.png";
	        itemDescription = "increases character’s speed by 1";
	        itemToolTip = "null";
	        itemPrice = 600;
	    }
		
		//increases one characters speed by 1
		public void increaseSpeed(Characters hero) {
			hero.adjustSpeed(1);
		}
	}
	
	public class StrengtheningTonic extends Items {
		StrengtheningTonic() {
	        name = "Strengthening Tonic";
	        imageLocation = "shopAssets/strengtheningTonic.png";
	        itemDescription = "increases character’s max hp by 1.1x";
	        itemToolTip = "null";
	        itemPrice = 600;
	    }
		
		//increases one characters health by 1.1x
		public void increaseMaxHealth(Characters hero) {
			double temp = hero.getMaxHealth();
			double healthIncrease = (double) (temp * 1.1); //gets the total amount of health that character gains
			hero.setMaxHealth(hero.getMaxHealth()+healthIncrease); //sets the max health to current max health added to the increase
			hero.setHealth(hero.getHealth()+healthIncrease); //sets current health to current health plus the health increase to keep damage done
		}
	}
	
	public class FortifyingSupplements extends Items {
		FortifyingSupplements() {
	        name = "Fortifying Supplements";
	        imageLocation = "shopAssets/fortifyingSupplements.png";
	        itemDescription = "increases character’s total BLD/PSN/BRN etc res’ by +10%";
	        itemToolTip = "null";
	        itemPrice = 400;
	    }
		
		//increases one characters resistances by 10%
		public void increaseResistances(Characters hero) {
			hero.setBlightResist(hero.getBlightResist()+10);
			hero.setBleedResist(hero.getBleedResist()+10);
			hero.setBurnResist(hero.getBurnResist()+10);
		}
	}
	
	public class RabbitsFoot extends Items {
		RabbitsFoot() {
	        name = "Rabbit’s Foot";
	        imageLocation = "shopAssets/rabbitsFoot.png";
	        itemDescription = "increases character’s cit chance by +5%.";
	        itemToolTip = "null";
	        itemPrice = 500;
	    }
		
		//increases one characters crit chance by 5%
		public void increaseCritChance(Characters hero) {
			hero.setCritChance(hero.getCritChance()+5);
		}
	}
}


