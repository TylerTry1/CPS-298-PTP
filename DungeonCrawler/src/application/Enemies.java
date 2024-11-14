package application;

import java.util.Optional; // For Optional Parameters

public class Enemies extends entities {
	
        String name;
        double health;
        static double[] damage = new double[1];
        static double[] placeholderDamage = new double[1];
        static int defense; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        static int accuracy; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int crit_chance;
        static int dodge_chance; // leave this stat at 0 for all characters, need a 0 value in case we want to change it later.
        int position;
        int size;
        int challenge; // For determine team comps (testing this)
        int speed;
        boolean downed = false;
        int getChallenge() {
        	return challenge;
        }
        
        void setDowned(boolean down) {
        	downed = down;
        }
        
        boolean getDowned() {
        	return downed;
        }
        
        int getSpeed() {
        	return speed;
        }
        
        double getHealth() {
        	return health;
        }
        
        void setHealth(double h) {
        	health = h;
        }
        
        String getName() {
        	return name;
        }
        
        void adjustSpeed(int adjust) {
        	speed += adjust;
        }
        
        
		static double[][] result = new double[2][4]; // Array to store the amount of damage that will be done, and the positions to damage.
		static double[] targetPos = new double[4];
		
		//Function<Object, Object[]>[] functions = new Function[2];
		interface AbilityFunctions {
			double[][] apply();
		}
		

		AbilityFunctions[] abilities = new AbilityFunctions[2];
		
		AbilityFunctions[] getAbilities() {
			
			return abilities;
		}
		

	
	public class goblinScout extends Enemies {
		public goblinScout(Optional <Integer> pos) {
			name = "Goblin Scout";
			health = 24;
			damage[0] = 7.0;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
			
				targetPos = new double[1];
				targetPos[0] = 1.0;
			
				result[0] = goblinScout.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
			
			
		
		// Recon Ability
		record Recon() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				// TODO Auto-generated method stub
				
				
				
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1.0;
				
				result[0] = goblinScout.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
				// This ability will need to iterate over an array of all team members
				// so we will come back to it when Enemy_Teams.java is complete.
		}
		
	}
	
	public class goblinAxeman extends Enemies {
		public goblinAxeman(Optional <Integer> pos) {
			name = "Goblin Axeman";
			health = 32;
			damage[0] = 15.0;
			placeholderDamage[0] = 0.0;
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
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
			
				result[0] = goblinAxeman.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Shield Stance
		record ShieldStance() implements AbilityFunctions {
			@Override
			public double[][] apply() {
				goblinAxeman.accuracy = 25;
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = goblinAxeman.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
	}
	
	public class goblinArcher extends Enemies {
		public goblinArcher(Optional <Integer> pos) {
			name = "Goblin Archer";
			health = 28;
			damage[0] = 10;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 3;
				targetPos[1] = 4;
				
				result[0] = goblinArcher.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Snipe
		record Snipe() implements AbilityFunctions {
			
			static private double[] damage = new double[1];

			@Override
			public double[][] apply() {
				Snipe.damage[0] = goblinArcher.damage[0] * 1.75;
				targetPos = new double[1];
				targetPos[0] = 4;
				
				result[0] = Snipe.damage;
				result[1] = targetPos;
				//damage = 10; // Return Damage to normal.
				return result;
			}
		}
		
	}
	
	public class goblinShaman extends Enemies {
		public goblinShaman(Optional <Integer> pos) {
			name = "Goblin Shaman";
			health = 20;
			damage[0] = 4;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = goblinShaman.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
		// Staff Slam
		record StaffSlam() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = goblinShaman.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
	}
	
	public class skeletonSwordsman extends Enemies {
		public skeletonSwordsman(Optional <Integer> pos) {
			name = "Skeleton Swordsman";
			health = 28;
			damage[0] = 12;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
				
				result[0] = skeletonSwordsman.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Vertical Slash
		record VerticalSlash() implements AbilityFunctions {
			
			static private double[] damage = new double[1];

			@Override
			public double[][] apply() {
				VerticalSlash.damage[0]  = skeletonSwordsman.damage[0] * 1.25;
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = skeletonSwordsman.damage;
				result[1] = targetPos;
			
				skeletonSwordsman.damage[0] = 12; // Return Damage to normal.
				return result;
			}
		}
		
	}
	
	
	public class skeletonCrossbowman extends Enemies {
		public skeletonCrossbowman(Optional <Integer> pos) {
			name = "Skeleton Crossbowmen";
			health = 25;
			damage[0] = 10;
			placeholderDamage[0] = 0;
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
			
			static private double[] damage = new double[1];

			@Override
			public double[][] apply() {
				
				if (damageBoost) {
					BurstShot.damage[0] = skeletonCrossbowman.damage[0] * 1.25;
					damageBoost = false; // Reset Boost
				}
				else {
					BurstShot.damage[0] = skeletonCrossbowman.damage[0] * 0.75;
				}
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
			
				result[0] = BurstShot.damage;
				result[1] = targetPos;
			
				skeletonCrossbowman.damage[0] = 10; // Return Damage to Normal
				return result;
			}
			
		}
		
		// Take Aim
		record TakeAim() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				damageBoost = true;
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = skeletonCrossbowman.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
	}
	
	public class skeletonDefender extends Enemies {
		public skeletonDefender(Optional <Integer> pos) {
			name = "Skeleton Defender";
			health = 35;
			damage[0] = 15;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = skeletonDefender.damage;
				result[1] = targetPos;
			
				return result;
			}
			
		}
		
		//Buckle Down
		record BuckleDown() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				skeletonDefender.defense = 20;
			
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = skeletonDefender.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
	}
	
	public class necromancer extends Enemies {
		public necromancer(Optional <Integer> pos) {
			name = "Necromancer";
			health = 25;
			damage[0] = 12; 
			placeholderDamage[0] = 0;
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
		
		// Summon Undead
		// This ability will need to iterate over an array of all team members
		// so we will come back to it when Enemy_Teams.java is complete.
		// Have to check for body pile
		record SummonUndead() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				// This ability will need to iterate over an array of all team members
				// so we will come back to it when Enemy_Teams.java is complete.
				// Have to check for body pile
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = necromancer.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}

		
		// Dark Magic Blast
		record DarkMagicBlast() implements AbilityFunctions {
			@Override
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = necromancer.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
	}
	
	public class bodyPile extends Enemies {
		public bodyPile(Optional <Integer> pos) {
			name = "Body Pile";
			health = 20;
			damage[0] = 0;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 0;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 1;
			abilities[0] = new Squelch();
			abilities[1] = new Pulse();
		}
		
		record Squelch() implements AbilityFunctions {
			// Placeholder ability for logic 

			@Override
			public double[][] apply() {
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = bodyPile.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
		record Pulse() implements AbilityFunctions {
			// Placeholder ability for logic 

			@Override
			public double[][] apply() {
				
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = bodyPile.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
		
	}
	
	public class giantPummeler extends Enemies {
		public giantPummeler(Optional <Integer> pos) {
			name = "Giant Pummeler";
			health = 50;
			damage[0] = 25;
			placeholderDamage[0] = 0;
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
			
			static private double[] damage = new double[1];

			@Override
			public double[][] apply() {
				Shockwave.damage[0] = giantPummeler.damage[0] * 0.75;
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
				
				result[0] = Shockwave.damage;
				result[1] = targetPos;
			
				giantPummeler.damage[0] = 25; // Return Damage to normal
				return result;
			}
		}
		
		// Ground Slam
		record GroundSlam() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
			
				result[0] = giantPummeler.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
	
	public class giantSlammer extends Enemies {
		public giantSlammer(Optional <Integer> pos) {
			name = "Giant Slammer";
			health = 60;
			damage[0] = 30;
			placeholderDamage[0] = 0;
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
			
			static private double[] damage = new double[1];

			@Override
			public double[][] apply() {
				Sweep.damage[0] = giantSlammer.damage[0] * 0.75;
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
				
				result[0] = Sweep.damage;
				result[1] = targetPos;
			
				giantSlammer.damage[0] = 30; // Return Damage to normal
				return result;
			}
		}
		
		//Mace Crush
		record MaceCrush()  implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
			
				result[0] = giantSlammer.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
	
	public class zombiePeasant extends Enemies {
		public zombiePeasant(Optional <Integer> pos) {
			name = "Zombie Peasant";
			health = 18;
			damage[0] = 10;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = zombiePeasant.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Sway
		record Sway() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				zombiePeasant.dodge_chance = 25;
			
				// Placeholder results
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = zombiePeasant.placeholderDamage;
				result[1] = targetPos;
				
				return result;
			}
		}
	}
	
	public class zombieKnight extends Enemies {
		public zombieKnight(Optional <Integer> pos) {
			name = "Zombie Knight";
			health = 35;
			damage[0] = 25;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = zombieKnight.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Sword Sweep
		record  SwordSweep() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
				
				result[0] = zombieKnight.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
	
	public class minotaur extends Enemies {
		public minotaur(Optional <Integer> pos) {
			name = "Minotaur";
			health = 65;
			damage[0] = 30;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
				
				result[0] = minotaur.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// AxeHalberd Slash (can rename to whatever weapon we give the character model)
		record AxeSlash() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
			
				result[0] = minotaur.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
	
	public class boar extends Enemies {
		public boar(Optional <Integer> pos) {
			name = "Boar";
			health = 40;
			damage[0] = 18;
			placeholderDamage[0] = 0;
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
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 1;
				
				result[0] = boar.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
		
		// Frenzy
		record Frenzy() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
			
				result[0] = boar.damage;
				result[1] = targetPos;
			
				return result;
			}
		}
	}
}
