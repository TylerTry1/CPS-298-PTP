package application;

//import java.util.ArrayList;
import java.util.Optional; // For Optional Parameters
import java.util.function.Function;

public class Enemies {
	
        String name;
        double health;
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
        
        double getHealth() {
        	return health;
        }
        
		static Object[] result = {}; // Array to store the amount of damage that will be done, and the positions to damage.
		// double abilityDamage; // From earlier version of abilities.
		static int[] targetPos;
		
		//Function<Object, Object[]>[] functions = new Function[2];
		interface AbilityFunctions {
			Object[] apply();
		}
		
		AbilityFunctions[] abilities = new AbilityFunctions[2];
		
		AbilityFunctions[] getAbilities() {
			
			return abilities;
		}
	
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
			abilities[0] = new Slice();
			abilities[1] = new Recon();
		}
		
		// Slice Ability
		record Slice() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
				
				result[0] = damage;
				result[1] = targetPos;
				return result;
			}
			
			
		}
		
		// Recon Ability
		record Recon() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				// TODO Auto-generated method stub
				return null;
			}
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
			abilities[0] = new AxeSlash();
			abilities[1] = new ShieldStance();
		}
		
		// Axe Slash 
		record AxeSlash() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Shield Stance
		record ShieldStance() implements AbilityFunctions {
			@Override
			public Object[] apply() {
				goblinAxeman.accuracy = 25;
				
				return null;
			}
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
			abilities[0] = new VolleyShot();
			abilities[1] = new Snipe();
		}
		
		// Volley Shot 
		record VolleyShot() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 4;
				targetPos[1] = 3;
				
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Snipe
		record Snipe() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				damage = damage * 1.75;
				targetPos[0] = 4;
			
				result[0] = damage;
				result[1] = targetPos;
			
				damage = 10; // Return Damage to normal.
				return result;
			}
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
			abilities[0] = new Rally();
			abilities[1] = new StaffSlam();
		}
		
		// Rally
		record Rally() implements AbilityFunctions{
			// This ability will need to iterate over an array of all team members
			// so we will come back to it when Enemy_Teams.java is complete.
			
			@Override
			public Object[] apply() {
				
				return null;
			}
		}
		
		// Staff Slam
		record StaffSlam() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new RushSlash();
			abilities[1] = new VerticalSlash();
		}
		
		// Rush Slash
		record RushSlash() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Vertical Slash
		record VerticalSlash() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				damage = damage * 1.25;
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				damage = 12; // Return Damage to normal.
				return result;
			}
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
			abilities[0] = new BurstShot();
			abilities[1] = new TakeAim();
		}
		
		static boolean damageBoost = false;
		
		// Burst Shot
		record BurstShot() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				
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
			
				damage = 10; // Return Damage to Normal
				return result;
				}
		}
		
		// Take Aim
		record TakeAim() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				damageBoost = true;
				
				return null;
			}
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
			abilities[0] = new MaceSlam();
			abilities[1] = new BuckleDown();
		}
		
		// Mace Slam
		record MaceSlam() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		//Buckle Down
		record BuckleDown() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				skeletonDefender.defense = 20;
			
				return null;
			}
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
			abilities[0] = new SummonUndead();
			abilities[1] = new DarkMagicBlast();
		}
		
		record SummonUndead() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				// This ability will need to iterate over an array of all team members
				// so we will come back to it when Enemy_Teams.java is complete.
				// Have to check for body pile
				
				return null;
			}
		}

		
		// Dark Magic Blast
		record DarkMagicBlast() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new Squelch();
			abilities[1] = null;
		}
		
		record Squelch() implements AbilityFunctions {
			// Placeholder ability for logic 

			@Override
			public Object[] apply() {
				
				
				return null;
			}
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
			abilities[0] = new Shockwave();
			abilities[1] = new GroundSlam();
		}
		
		// Shockwave
		record Shockwave() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				damage = giantPummeler.damage * 0.75;
				targetPos[0] = 4;
				targetPos[1] = 3;
				targetPos[2] = 2;
				targetPos[3] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				damage = 25; // Return Damage to normal
				return result;
			}
		}
		
		// Ground Slam
		record GroundSlam() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;

			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new Sweep();
			abilities[1] = new MaceCrush();
		}
		
		// Sweep
		record Sweep() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				damage = giantSlammer.damage * 0.75;
				targetPos[0] = 4;
				targetPos[1] = 3;
				targetPos[2] = 2;
				targetPos[3] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				damage = 30; // Return Damage to normal
				return result;
			}
		}
		
		//Mace Crush
		record MaceCrush()  implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;

			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new Bite();
			abilities[1] = new Sway();
			
		}
		
		// Bite 
		record Bite() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Sway
		record Sway() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				zombiePeasant.dodge_chance = 25;
			
				return null;
			}
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
			abilities[0] = new ShoulderSlam();
			abilities[1] = new SwordSweep();
			
		}
		
		// Shoulder Slam
		record ShoulderSlam() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Sword Sweep
		record  SwordSweep() implements AbilityFunctions {
			
			@Override
			public Object[] apply() {
				targetPos[0] = 4;
				targetPos[1] = 3;
				targetPos[2] = 2;
				targetPos[3] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new Rush();
			abilities[1] = new AxeSlash();
		}
		
		// Rush
		record Rush() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 4;
				targetPos[1] = 3;
				targetPos[2] = 2;
				targetPos[3] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// AxeHalberd Slash (can rename to whatever weapon we give the character model)
		record AxeSlash() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;

			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
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
			abilities[0] = new TuskSwipe();
			abilities[1] = new Frenzy();
		}
		
		// Tusk Swipe
		record TuskSwipe() implements AbilityFunctions{

			@Override
			public Object[] apply() {
				targetPos[0] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Frenzy
		record Frenzy() implements AbilityFunctions {

			@Override
			public Object[] apply() {
				targetPos[0] = 2;
				targetPos[1] = 1;
			
				result[0] = damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
}
