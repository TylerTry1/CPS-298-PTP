package application;

//import java.util.ArrayList;
import java.util.Optional; // For Optional Parameters
public class Enemies {
	
	public class enemyStats {
        String name;
        int health;
        static int damage;
        static int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        static int accuracy; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int crit_chance;
        static int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int position;
        int size;
	}
	
	public class goblinScout extends enemyStats {
		public goblinScout(Optional <Integer> pos) {
			name = "Goblin Scout";
			health = 24;
			damage = 7;
			defense = 0;
			accuracy = 0; 
			crit_chance = 10;
			dodge_chance = 0;
			position = pos.orElse(2);
			size = 1;
		}
		
	}
	
	public class goblinAxeman extends enemyStats {
		public goblinAxeman(Optional <Integer> pos) {
			name = "Goblin Axeman";
			health = 32;
			damage = 15;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
		}
		
	}
	
	public class goblinArcher extends enemyStats {
		public goblinArcher(Optional <Integer> pos) {
			name = "Goblin Archer";
			health = 28;
			damage = 10;
			defense = 0;
			accuracy = 0;
			crit_chance = 15;
			dodge_chance = 0;
			position = pos.orElse(3);
			size = 1;
		}
		
	}
	
	public class goblinShaman extends enemyStats {
		public goblinShaman(Optional <Integer> pos) {
			name = "Goblin Shaman";
			health = 20;
			damage = 4;
			defense = 0;
			accuracy = 0;
			crit_chance = 10;
			dodge_chance = 0;
			position = pos.orElse(4);
			size = 1;
		}
		
	}
	
	public class skeletonSwordsman extends enemyStats {
		public skeletonSwordsman(Optional <Integer> pos) {
			name = "Skeleton Swordsman";
			health = 28;
			damage = 12;
			defense = 0;
			accuracy = 0;
			crit_chance = 12;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
		}
		
	}
	
	
	public class skeletonCrossbowman extends enemyStats {
		public skeletonCrossbowman(Optional <Integer> pos) {
			name = "Skeleton Crossbowmen";
			health = 25;
			damage = 10;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(2);
			size = 1;
		}
		
	}
	
	public class skeletonDefender extends enemyStats {
		public skeletonDefender(Optional <Integer> pos) {
			name = "Skeleton Defender";
			health = 35;
			damage = 15;
			defense = 0;
			accuracy = 0;
			crit_chance = 15;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
		}
		
	}
	
	public class necromancer extends enemyStats {
		public necromancer(Optional <Integer> pos) {
			name = "Necromancer";
			health = 25;
			damage = 12; 
			defense = 0;
			accuracy = 0;
			crit_chance = 0;
			dodge_chance = 0;
			position = pos.orElse(4);
			size = 1;
		}
	}
	
	public class bodyPile extends enemyStats {
		public bodyPile(Optional <Integer> pos) {
			name = "Body Pile";
			health = 20;
			damage = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 0;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
		}
	}
	
	public class giantPummeler extends enemyStats {
		public giantPummeler(Optional <Integer> pos) {
			name = "Giant Pummeler";
			health = 50;
			damage = 25;
			defense = 0;
			accuracy = 0;
			crit_chance = 5;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
		}
	}
	
	public class giantSlammer extends enemyStats {
		public giantSlammer(Optional <Integer> pos) {
			name = "Giant Slammer";
			health = 60;
			damage = 30;
			defense = 0;
			accuracy = 0;
			crit_chance = 8;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
		}
	}
	
	public class zombiePeasant extends enemyStats{
		public zombiePeasant(Optional <Integer> pos) {
			name = "Zombie Peasant";
			health = 18;
			damage = 10;
			defense = 0;
			accuracy = 0;
			crit_chance = 5;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			
		}
	}
	
	public class zombieKnight extends enemyStats{
		public zombieKnight(Optional <Integer> pos) {
			name = "Zombie Knight";
			health = 35;
			damage = 25;
			defense = 0;
			accuracy = 0;
			crit_chance =15;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			
		}
	}
	
	public class minotaur extends enemyStats {
		public minotaur(Optional <Integer> pos) {
			name = "Minotaur";
			health = 65;
			damage = 30;
			defense = 0;
			accuracy = 0;
			crit_chance = 25;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
		}
	}
	
	public class boar extends enemyStats {
		public boar(Optional <Integer> pos) {
			name = "Boar";
			health = 40;
			damage = 18;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
		}
	}
}
