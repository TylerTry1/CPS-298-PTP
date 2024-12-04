package application;

//import java.util.ArrayList;
//import java.util.Scanner;
public class Characters extends entities {

    //public class characterStats {
        String name;
        String idleSprite;
        String attackSprite;
        String kneelingSprite;
        String activeSprite; // Need an active so that data doesn't get overwritten since the same character instance get carried between combat
        double maxHealth;
        double health;
        double damage;
        int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int accuracy;
        int crit_chance;
        int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int position;
        int speed;
        int defyChance;
        int bleedResist;
        int burnResist;
        int blightResist;
        int stunResist;
        int moveResist;
        int debuffResist;
        int deathResist;
        double healthBarAmount;
        
        boolean downed = false;
        
        void setDowned(boolean down) {
        	downed = down;
        }
        
        boolean getDowned() {
        	return downed;
        }
        
        double getHealth() {
        	return health;
        }
        
        double getMaxHealth() {
        	return maxHealth;
        }
        
        void setMaxHealth(double set) {
        	maxHealth = set;
        }
        
        void setHealth(double h) {
        	health = h;
        }
        
        int getSpeed() {
        	return speed;
        }
        
        void adjustSpeed(int adjust) {
        	speed += adjust;
        }
        
        double getDamage() {
        	return damage;
        }
        
        void setDamage(double dam) {
        	damage = dam;
        }
        
        void setBlightResist(int set) {
        	blightResist = set;
        }
        
        int getBlightResist() {
        	return blightResist;
        }
        
        void setBleedResist(int set) {
        	bleedResist = set;
        }
        
        int getBleedResist() {
        	return bleedResist;
        }
        
        void setBurnResist(int set) {
        	burnResist = set;
        }
        
        int getBurnResist() {
        	return burnResist;
        }
        void setCritChance(int set) {
        	crit_chance = set;
        }
        
        int getCritChance() {
        	return crit_chance;
        }
        
        String getName() {
        	return name;
        }
        
        public void takeDamage(double damage) {
            this.health -= damage;
            System.out.println(name + " takes " + damage + " damage. Current health: " + this.health);
        }
        
        public void applyBleed(int duration, double damage) {
        	int numTurns = 0;
        	while (numTurns < duration) {
        		this.health -= damage;
        		numTurns++;
        	}
        }
        
        // Heal self method
        public void healSelf(int amount) {
            this.health += amount;
            if (this.health > maxHealth) {
                this.health = maxHealth; // Cap healing at max health
            }
            System.out.println(name + " healed for " + amount + " health. Current health: " + this.health);
        }
        
        double setHealthBarAmount() {
        	healthBarAmount = (this.health / this.maxHealth) * 100;
        	return healthBarAmount;
        }
        
        void setIdleSprite(String set) {
        	idleSprite = set;
        }
        
        String getIdleSprite() {
        	return idleSprite;
        }
        
        void setAttackSprite(String set) {
        	attackSprite = set;
        }
        
        String getAttackSprite() {
        	return attackSprite;
        }
        
        void setKneelingSprite(String set) {
        	kneelingSprite = set;
        }
        
        String getKneelingSprite() {
        	return kneelingSprite;
        }
        
        void setActiveSprite(String set) {
        	activeSprite = set;
        }
        
        String getActiveSprite() {
        	return activeSprite;
        }
        
        void setDefy(int set) {
        	defyChance = set;
        }
		
        int getDefy() {
        	return defyChance;
        }
        
    //}

    /*public class characterDismas extends characterStats {
        public characterDismas() {
            name = "Dismas";
            health = 33;
            damage = 0; 
            defense = 4;
            accuracy = 100;
            crit_chance = 32;
            dodge_chance = 0;
            position = 2;
        }
    }

    public class characterReynauld extends characterStats {
        public characterReynauld() {
            name = "Reynauld";
            health = 54;
            damage = 18;
            defense = 0;
            accuracy = 70;
            crit_chance = 14;
            dodge_chance = 0;
            position = 1;
        }
    }
    public class characterDamian extends characterStats{
    	public characterDamian() {
    		name = "Damian";
    		health = 72;
    		damage = 10;
    		defense = 0;
    		accuracy = 85;
    		crit_chance = 10;
    		dodge_chance = 0;
    		position = 3;
    	}
    }
    
    public static void main(String args[]) {
        System.out.println("Hello, and welcome to my Character builder");
        System.out.println("Please select a character to view the stats of using the number keys.");
        // Create an instance of the test class to access the non-static inner class
        Characters outerInstance = new Characters();

        // Create an ArrayList to store characters
        ArrayList<characterStats> characters = new ArrayList<>();
        
        // Add characters to the ArrayList
        characters.add(outerInstance.new characterDismas());
        characters.add(outerInstance.new characterReynauld());
        characters.add(outerInstance.new characterDamian());

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. List All Characters");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
        
            switch (choice) {
            
            case 1:
                System.out.println("\n" + "Listing all characters:" + "\n");
                for (characterStats character : characters) {
                    System.out.println("Character name: " + character.name + ".\n" 
                        + "health: " + character.health + "\n"
                        + "damage: " + character.damage + "\n" 
                        + "defense: " + character.defense + "\n" 
                        + "accuracy: " + character.accuracy + "\n" 
                        + "critical strike chance: " + character.crit_chance + "\n"
                        + "dodge chance: " + character.dodge_chance + "\n" 
                        + "position: " + character.position + "\n");
                }
                break;
            case 2:
                exit = true;
                System.out.println("Exiting the Character builder.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
   }
//        // Add characters to the ArrayList
//        characters.add(outerInstance.new characterDismas());
//        characters.add(outerInstance.new characterReynauld());
//        characters.add(outerInstance.new characterDamian());
//        // Iterate through the ArrayList and print character details
//        for (characterStats character : characters) {
//            System.out.println("Character name: " + character.name + ".\n" 
//                + "health: " + character.health + "\n"
//                + "damage: " + character.damage + "\n" 
//                + "defense: " + character.defense + "\n" 
//                + "accuracy: " + character.accuracy + "\n" 
//                + "critical strike chance: " + character.crit_chance + "\n"
//                + "dodge chance: " + character.dodge_chance + "\n" 
//                + "position: " + character.position + "\n");
//        }
    }

    */
   
    }



