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

	public Items(String name, String imageLocation, String itemToolTip, String itemDescription, int itemPrice) {
		this.name = name;
		this.itemDescription = itemDescription;
		this.itemToolTip = itemToolTip;
		this.imageLocation = imageLocation;
		this.itemPrice = itemPrice;
		
	}
	
	public void statIncrease(Characters hero) {
		// TODO Auto-generated method stub
		
	}

	public void fullHeal(Characters hero1, Characters hero2, Characters hero3, Characters hero4) {
		// TODO Auto-generated method stub
		
	}


	public class Clinic extends Items {
		Clinic() {
	        super("Clinic", "shopAssets/clinic.png", "heals the entire team to full HP", "a little help impeccably timed.", 400);
	    }
		
		//fully restores heros to max health
		@Override public void fullHeal(Characters hero1, Characters hero2, Characters hero3, Characters hero4) {
			hero1.setHealth(hero1.getMaxHealth());
			hero2.setHealth(hero2.getMaxHealth());
			hero3.setHealth(hero3.getMaxHealth());
			hero4.setHealth(hero4.getMaxHealth());
		}
	}
	
	public class Whetstone extends Items {
		Whetstone() {
	        super("Whetstone", "shopAssets/whetstone.png", "increases character’s damage by 1.1x", "sharpened to a razor’s edge.", 700 );
	    }
		
		//increases one characters damage by 1.1x
		@Override public void statIncrease(Characters hero) {
			hero.setDamage((double) (hero.getDamage()*1.1));
		}
	}
	
	public class SmellingSalts extends Items {
		SmellingSalts() {
	        super("Smelling Salts", "shopAssets/smellingSalts.png", "increases character’s speed by 1", "null",  600);
	    }
		
		//increases one characters speed by 1
		@Override public void statIncrease(Characters hero) {
			hero.adjustSpeed(1);
		}
	}
	
	public class StrengtheningTonic extends Items {
		StrengtheningTonic() {
	        super("Strengthening Tonic", "shopAssets/strengtheningTonic.png", "increases character’s max hp by 1.1x", "null", 600);
	    }
		
		//increases one characters health by 1.1x
		@Override public void statIncrease(Characters hero) {
			double temp = hero.getMaxHealth();
			double healthIncrease = (double) (temp * 1.1); //gets the total amount of health that character gains
			hero.setMaxHealth(hero.getMaxHealth()+healthIncrease); //sets the max health to current max health added to the increase
			hero.setHealth(hero.getHealth()+healthIncrease); //sets current health to current health plus the health increase to keep damage done
		}
	}
	
	public class FortifyingSupplements extends Items {
		FortifyingSupplements() {
	        super("Fortifying Supplements", "shopAssets/fortifyingSupplements.png", "increases character’s total resistances by +10%", "null", 400);
	    }
		
		//increases one characters resistances by 10%
		@Override public void statIncrease(Characters hero) {
			hero.setBlightResist(hero.getBlightResist()+10);
			hero.setBleedResist(hero.getBleedResist()+10);
			hero.setBurnResist(hero.getBurnResist()+10);
		}
	}
	
	public class RabbitsFoot extends Items {
		RabbitsFoot() {
	        super("Rabbit’s Foot", "shopAssets/rabbitFoot.png", "increases character’s cit chance by +5%.", "null", 500);
	    }
		
		//increases one characters crit chance by 5%
		@Override public void statIncrease(Characters hero) {
			hero.setCritChance(hero.getCritChance()+5);
		}
	}
}


