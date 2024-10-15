package application;

//import java.util.ArrayList;
//import java.util.Scanner;
public class Characters {

    //public class characterStats {
        String name;
        int health;
        int damage;
        int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int accuracy;
        int crit_chance;
        int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int position;
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



