package application;

//import java.util.ArrayList;
import java.util.Optional; // For Optional Parameters

public class Enemies {
	
        String name;
        int health;
        static double damage;
        static int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        static int accuracy; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int crit_chance;
        static int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int position;
        int size;
        int challenge; // For determine team comps (testing this)
        int speed;
        int getChallenge() {
        	return challenge;
        }
        
        int getSpeed() {
        	return speed;
        }
        
        void adjustSpeed(int adjust) {
        	speed += adjust;
        }
        
		Object[] result = {}; // Array to store the amount of damage that will be done, and the positions to damage.
		// double abilityDamage; // From earlier version of abilities.
		int[] targetPos;
		
		

	
	public class goblinScout extends Enemies {
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
			challenge = 1;
			speed = 5;
		}
		
		// Slice Ability
		public Object[] Slice() {
			
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			return result;
		}
		
		// Recon Ability
		public void Recon() {
			// This ability will need to iterate over an array of all team members
			// so we will come back to it when Enemy_Teams.java is complete.
		}
		
	}
	
	public class goblinAxeman extends Enemies {
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
			challenge = 2;
			speed = 4;
		}
		
		// Axe Slash 
		public Object[] AxeSlash() {

			targetPos[0] = 2;
			targetPos[1] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Shield Stance
		public void ShieldStance() {
			goblinAxeman.accuracy = 25;
		}
		
	}
	
	public class goblinArcher extends Enemies {
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
			challenge = 1;
			speed = 6;
		}
		
		// Volley Shot 
		public Object[] VolleyShot() {

			targetPos[0] = 4;
			targetPos[1] = 3;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Snipe
		public Object[] Snipe() {
			
			damage = damage * 1.75;
			targetPos[0] = 4;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
	}
	
	public class goblinShaman extends Enemies {
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
			challenge = 1;
			speed = 6;
		}
		
		// Rally
		public void Rally() {
			// This ability will need to iterate over an array of all team members
			// so we will come back to it when Enemy_Teams.java is complete.
		}
		
		// Staff Slam
		public Object[] StaffSlam() {

			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
	}
	
	public class skeletonSwordsman extends Enemies {
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
			challenge = 2;
			speed = 6;
		}
		
		// Rush Slash
		public Object[] RushSlash() {

			targetPos[0] = 2;
			targetPos[1] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Vertical Slash
		public Object[] VerticalSlash() {
			
			damage = damage * 1.25;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
	}
	
	
	public class skeletonCrossbowman extends Enemies {
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
			challenge = 2;
			speed = 7;
		}
		
		boolean damageBoost = false;
		// Burst Shot
		public Object[] BurstShot() {
			
			if (damageBoost) {
				damage = skeletonCrossbowman.damage * 1.25;
				damageBoost = false; // Reset Boost
			}
			else {
				damage = skeletonCrossbowman.damage * 0.75;
			}
			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Take Aim
		public void TakeAim() {
			damageBoost = true;
		}
		
	}
	
	public class skeletonDefender extends Enemies {
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
			challenge = 3;
			speed = 5;
		}
		
		// Mace Slam
		public Object[] MaceSlam() {
			
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		//Buckle Down
		public void BuckleDown() {
			skeletonDefender.defense = 20;
		}
		
	}
	
	public class necromancer extends Enemies {
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
			challenge = 2;
			speed = 8;
		}
		
		// Summon Undead
		// This ability will need to iterate over an array of all team members
		// so we will come back to it when Enemy_Teams.java is complete.
		// Have to check for body pile
		
		// Dark Magic Blast
		public Object[] DarkMagicBlast() {

			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class bodyPile extends Enemies {
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
			challenge = 1;
		}
		
		public void squelch() {
			// Placeholder ability for logic flow
		}
	}
	
	public class giantPummeler extends Enemies {
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
			challenge = 4;
		}
		
		// Shockwave
		public Object[] Shockwave() {
			
			damage = giantPummeler.damage * 0.75;
			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Ground Slam
		public Object[] GroundSlam() {

			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class giantSlammer extends Enemies {
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
			challenge = 4;
		}
		
		// Sweep
		public Object[] Sweep() {
			
			damage = giantSlammer.damage * 0.75;
			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		//Mace Crush
		public Object[] MaceCrush() {

			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class zombiePeasant extends Enemies {
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
			challenge = 2;
			
		}
		
		// Bite 
		public Object[] Bite() {

			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Sway
		public void Sway() {
			zombiePeasant.dodge_chance = 25;
		}
	}
	
	public class zombieKnight extends Enemies {
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
			challenge = 3;
			
		}
		
		// Shoulder Slam
		public Object[] ShoulderSlam() {

			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Sword Sweep
		public Object[] SwordSweep() {
			
			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class minotaur extends Enemies {
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
			challenge = 4;
		}
		
		// Rush
		public Object[] Rush() {

			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// AxeHalberd Slash (can rename to whatever weapon we give the character model)
		public Object[] AxeSlash() {

			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class boar extends Enemies {
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
			challenge = 3;
		}
		
		// Tusk Swipe
		public Object[] TuskSwipe() {

			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Frenzy
		public Object[] Frenzy() {

			targetPos[0] = 2;
			targetPos[1] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
}
