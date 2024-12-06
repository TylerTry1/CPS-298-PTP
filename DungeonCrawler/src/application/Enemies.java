package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional; // For Optional Parameters

import javax.imageio.ImageIO;

public class Enemies extends entities {
	
        String name;
        double maxHealth;
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
        int goldValue;
        int defyChance;
        String idleSprite;
        String attackSprite;
        double bleedResist;
        double burnResist;
        double blightResist;
        double stunResist;
        double moveResist;
        double debuffResist;
        double deathResist;
        double healthBarAmount;
        boolean downed = false;
        int getChallenge() {
        	return challenge;
        }
        
        void setBleedResist(double set) {
        	bleedResist = set;
        }
        
        double getBleedResist() {
        	return bleedResist;
        }
        
        void setBurnResist(double set) {
        	burnResist = set;
        }
        
        double getBurnResist() {
        	return burnResist;
        }
        
        void setBlightResist(double set) {
        	blightResist = set;
        }
        
        double getBlightResist() {
        	return blightResist;
        }
        
        void setStunResist(double set) {
        	stunResist = set;
        }
        
        double getStunResist() {
        	return stunResist;
        }
        
        void setMoveResist(double set) {
        	moveResist = set;
        }
        
        double getMoveResist() {
        	return moveResist;
        }
        
        void setDebuffResist(double set) {
        	debuffResist = set;
        }
        
        double getDebuffResist() {
        	return debuffResist;
        }
        
        void setDeathResist(double set) {
        	deathResist = set;
        }
        
        double getDeathResist() {
        	return deathResist;
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
        
        String getIdleSprite () {
        	return idleSprite;
        }
        
        // Parameter Type made need changed, test later
        void setIdleSprite(String newSprite) {
        	idleSprite = newSprite;
        }
        
        String getAttackSprite() {
        	return attackSprite;
        }
        
        int getGoldValue() {
        	return goldValue;
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
		
        double setHealthBarAmount() {
        	healthBarAmount = (health / maxHealth) * 100;
        	return healthBarAmount;
        }
        
        void setDefy(int set) {
        	defyChance = set;
        }
		
        int getDefy() {
        	return defyChance;
        }

	
	public class goblinScout extends Enemies {
		public goblinScout(Optional <Integer> pos) throws IOException {
			name = "Goblin Scout";
			maxHealth = 24;
			health = 24;
			damage[0] = 3.5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0; 
			crit_chance = 10;
			dodge_chance = 0;
			position = pos.orElse(2);
			size = 1;
			challenge = 1;
			speed = 5;
			goldValue = 50;
			defyChance = 50;
			idleSprite = "/images/Goblin_Scout_1_Idle.png"; 
			attackSprite = "/images/Goblin_Scout_1_Attack.png";
	        bleedResist = 10;
	        burnResist = 12;
	        blightResist = 14;
	        stunResist = 10;
	        moveResist = 5;
	        debuffResist = 6;
	        deathResist = 4;
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
			
				AudioManager.playGoblinScoutSlice();
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
				targetPos[0] = 4.0;
				
				result[0] = goblinScout.damage;
				result[1] = targetPos;
				
				AudioManager.playGoblinScoutRecon();
				return result;
			}
				// This ability will need to iterate over an array of all team members
				// so we will come back to it when Enemy_Teams.java is complete.
		}
		
	}
	
	public class goblinAxeman extends Enemies {
		public goblinAxeman(Optional <Integer> pos) throws IOException {
			name = "Goblin Axeman";
			maxHealth = 32;
			health = 32;
			damage[0] = 7.5;
			placeholderDamage[0] = 0.0;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 2;
			speed = 4;
			goldValue = 60;
			defyChance = 50;
			idleSprite = "/images/Goblin_axeman_1_Idle.png";
			attackSprite = "/images/Goblin_axeman_1_Attack.png";
	        bleedResist = 10;
	        burnResist = 12;
	        blightResist = 14;
	        stunResist = 10;
	        moveResist = 5;
	        debuffResist = 6;
	        deathResist = 4;
			abilities[0] = new AxeSlash();
			abilities[1] = new ShieldStance();
		}
		
		// Axe Slash 
		record AxeSlash() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 3;
				targetPos[1] = 4;
			
				result[0] = goblinAxeman.damage;
				result[1] = targetPos;
			
				AudioManager.playGoblinAxemanAxeSlash();
				return result;
			}
		}
		
		// Shield Stance
		record ShieldStance() implements AbilityFunctions {
			@Override
			public double[][] apply() {
				goblinAxeman.accuracy = 25;
				
				// Placeholder results
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
				
				result[0] = goblinAxeman.damage;
				result[1] = targetPos;
				
				AudioManager.playGoblinAxemanShieldStance();
				return result;
			}
		}
		
	}
	
	public class goblinArcher extends Enemies {
		public goblinArcher(Optional <Integer> pos) throws IOException {
			name = "Goblin Archer";
			maxHealth = 28;
			health = 28;
			damage[0] = 5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 15;
			dodge_chance = 0;
			position = pos.orElse(3);
			size = 1;
			challenge = 1;
			speed = 6;
			goldValue = 60;
			defyChance = 50;
			idleSprite = "/images/Goblin_Archer_1_Idle.png";
			attackSprite = "/images/Goblin_Archer_1_Attack.png";
	        bleedResist = 10;
	        burnResist = 12;
	        blightResist = 14;
	        stunResist = 10;
	        moveResist = 5;
	        debuffResist = 6;
	        deathResist = 4;
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
			
				AudioManager.playGoblinArcherVolleyShot();
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
				
				AudioManager.playGoblinArcherSnipe();
				return result;
			}
		}
		
	}
	
	public class goblinShaman extends Enemies {
		public goblinShaman(Optional <Integer> pos) throws IOException {
			name = "Goblin Shaman";
			maxHealth = 20;
			health = 20;
			damage[0] = 2;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 10;
			dodge_chance = 0;
			position = pos.orElse(4);
			size = 1;
			challenge = 1;
			speed = 6;
			goldValue = 60;
			defyChance = 50;
			idleSprite = "/images/Goblin_Shaman_1_Idle.png";
			attackSprite = "/images/Goblin_Shaman_1_Attack.png";
	        bleedResist = 10;
	        burnResist = 12;
	        blightResist = 14;
	        stunResist = 10;
	        moveResist = 5;
	        debuffResist = 6;
	        deathResist = 4;
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
				targetPos = new double[2];
				targetPos[0] = 1;
				targetPos[1] = 2;
				
				result[0] = goblinShaman.damage;
				result[1] = targetPos;
				
				AudioManager.playGoblinShamanRally();
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
			
				AudioManager.playGoblinShamanStaffSlam();
				return result;
			}
		}
		
	}
	
	public class skeletonSwordsman extends Enemies {
		public skeletonSwordsman(Optional <Integer> pos) throws IOException {
			name = "Skeleton Swordsman";
			maxHealth = 28;
			health = 28;
			damage[0] = 6;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 12;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 2;
			speed = 6;
			goldValue = 75;
			defyChance = 50;
			idleSprite = "/images/Skeleton_Swordman_Idle.png";
			attackSprite = "/images/Skeleton_Swordman_Attack.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 10;
	        debuffResist = 6;
	        deathResist = 4;
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
			
				AudioManager.playSkeletonSwordsmanRushSlash();
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
				
				result[0] = VerticalSlash.damage;
				result[1] = targetPos;
			
				skeletonSwordsman.damage[0] = 12; // Return Damage to normal.
				AudioManager.playSkeletonSwordsmanVerticalSlash();
				return result;
			}
		}
		
	}
	
	
	public class skeletonCrossbowman extends Enemies {
		public skeletonCrossbowman(Optional <Integer> pos) throws IOException {
			name = "Skeleton Crossbowmen";
			maxHealth = 25;
			health = 25;
			damage[0] = 5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(2);
			size = 1;
			challenge = 2;
			speed = 7;
			goldValue = 75;
			defyChance = 50;
			idleSprite = "/images/Skeleton-Crossbowman_Idle.png";
			attackSprite = "/images/Skeleton-Crossbowman_Attack.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 10;
	        debuffResist = 6;
	        deathResist = 4;
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
				AudioManager.playSkeletonCrossbowmanBurstShot();
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
				
				AudioManager.playSkeletonCrossbowmanTakeAim();
				return result;
			}
		}
		
	}
	
	public class skeletonDefender extends Enemies {
		public skeletonDefender(Optional <Integer> pos) throws IOException {
			name = "Skeleton Defender";
			maxHealth = 35;
			health = 35;
			damage[0] = 7.5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 15;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 3;
			speed = 5;	  
			goldValue = 75;
			defyChance = 50;
			idleSprite = "/images/Skeleton_Defender_Idle.png";
			attackSprite = "/images/Skeleton_Defender_Attack.png";
			bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 10;
	        debuffResist = 6;
	        deathResist = 4;
			abilities[0] = new MaceSlam();
			abilities[1] = new BuckleDown();
		}
		
		// Mace Slam
		record MaceSlam() implements AbilityFunctions {
			@Override
			public double[][] apply() {
				targetPos = new double[2];
				targetPos[0] = 3;
				targetPos[1] = 4;
				
				result[0] = skeletonDefender.damage;
				result[1] = targetPos;
			
				AudioManager.playSkeletonDefenderMaceSlam();
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
				
				result[0] =  skeletonDefender.damage;
				result[1] = targetPos;
				
				AudioManager.playSkeletonDefenderBuckleDown();
				return result;
			}
		}
		
	}
	
	public class necromancer extends Enemies {
		public necromancer(Optional <Integer> pos) throws IOException {
			name = "Necromancer";
			maxHealth = 25;
			health = 25;
			damage[0] = 6; 
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 0;
			dodge_chance = 0;
			position = pos.orElse(4);
			size = 1;
			challenge = 2;
			speed = 8;
			goldValue = 80;
			defyChance = 50;
			idleSprite = "/images/Necromancer_Idle.png";
			attackSprite = "/images/Necromancer_Attack.png";
	        bleedResist = 10;
	        burnResist = 15;
	        blightResist = 20;
	        stunResist = 5;
	        moveResist = 5;
	        debuffResist = 6;
	        deathResist = 4;
			abilities[0] = new DrainLife();
			abilities[1] = new DarkMagicBlast();
		}
		
		// Drain Life
		record DrainLife() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				
				// Placeholder results
				targetPos = new double[4];
				targetPos[0] = 1;
				targetPos[1] = 2;
				targetPos[2] = 3;
				targetPos[3] = 4;
				
				result[0] = necromancer.damage;
				result[1] = targetPos;
				
				AudioManager.playNecromancerSummonUndead();
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
			
				AudioManager.playNecromancerDarkMagicBlast();
				return result;
			}
		}
		
	}
	
	public class bodyPile extends Enemies {
		public bodyPile(Optional <Integer> pos) throws IOException {
			name = "Body Pile";
			maxHealth = 20;
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
			speed = 0;
			goldValue = 40;
			defyChance = 50;
			idleSprite = "/images/CorpsePile.png";
			attackSprite = "/images/CorpsePile.png";
	        bleedResist = 0;
	        burnResist = 0;
	        blightResist = 0;
	        stunResist = 0;
	        moveResist = 0;
	        debuffResist = 0;
	        deathResist = 0;
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
		public giantPummeler(Optional <Integer> pos) throws IOException {
			name = "Giant Pummeler";
			maxHealth = 50;
			health = 50;
			damage[0] = 12.5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 5;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
			challenge = 4;
			speed = 5;
			goldValue = 120;
			defyChance = 50;
			idleSprite = "/images/Giant_Pummeler_Idle.png";
			attackSprite = "/images/Giant_Pummeler_Attack.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 25;
	        debuffResist = 6;
	        deathResist = 10;
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
				AudioManager.playGiantPummelerShockwave();
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
			
				AudioManager.playGiantPummelerGroundSlam();
				return result;
			}
		}
	}
	
	public class giantSlammer extends Enemies {
		public giantSlammer(Optional <Integer> pos) throws IOException {
			name = "Giant Slammer";
			maxHealth = 60;
			health = 60;
			damage[0] = 15;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 8;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
			challenge = 4;
			speed = 5;
			goldValue = 135;
			defyChance = 50;
			idleSprite = "/images/Giant_Slammer_Idle.png";
			attackSprite = "/images/Giant_Slammer_Attack.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 25;
	        debuffResist = 6;
	        deathResist = 10;
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
				AudioManager.playGiantSlammerSweep();
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
			
				AudioManager.playGiantSlammerMaceCrush();
				return result;
			}
		}
	}
	
	public class zombiePeasant extends Enemies {
		public zombiePeasant(Optional <Integer> pos) throws IOException {
			name = "Zombie Peasant";
			maxHealth = 18;
			health = 18;
			damage[0] = 5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 5;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 2;
			speed = 8;
			goldValue = 65;
			defyChance = 50;
			idleSprite = "/images/Zombie_Peasant_Idle.png";
			attackSprite = "/images/Zombie_Peasant-Attack.png";
	        bleedResist = 25;
	        burnResist = 5;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 10;
	        debuffResist = 6;
	        deathResist = 10;
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
			
				AudioManager.playZombiePeasantBite();
				return result;
			}
		}
		
		// Sway
		record Sway() implements AbilityFunctions {
			
			@Override
			public double[][] apply() {
				zombiePeasant.dodge_chance = 25;
			
				// Placeholder results
				targetPos = new double[2];
				targetPos[0] = 2;
				targetPos[1] = 3;
				
				result[0] = zombiePeasant.damage;
				result[1] = targetPos;
				
				AudioManager.playZombiePeasantSway();
				return result;
			}
		}
	}
	
	public class zombieKnight extends Enemies {
		public zombieKnight(Optional <Integer> pos) throws IOException {
			name = "Zombie Knight";
			maxHealth = 35;
			health = 35;
			damage[0] = 12.5;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance =15;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 3;
			speed = 5;
			goldValue = 85;
			defyChance = 50;
			idleSprite = "/images/Zombie_Knight_Idle.png";
			attackSprite = "/images/Zombie_Knight_Attack.png";
	        bleedResist = 25;
	        burnResist = 10;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 10;
	        debuffResist = 6;
	        deathResist = 10;
			abilities[0] = new ShoulderSlam();
			abilities[1] = new SwordSweep();
			
		}
		
		// Shoulder Slam
		record ShoulderSlam() implements AbilityFunctions {

			@Override
			public double[][] apply() {
				targetPos = new double[1];
				targetPos[0] = 4;
				
				result[0] = zombieKnight.damage;
				result[1] = targetPos;
			
				AudioManager.playZombieKnightShoulderSlam();
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
			
				AudioManager.playZombieKnightSwordSweep();
				return result;
			}
		}
	}
	
	public class minotaur extends Enemies {
		public minotaur(Optional <Integer> pos) throws IOException {
			name = "Minotaur";
			maxHealth = 65;
			health = 65;
			damage[0] = 15;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 25;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 2;
			challenge = 4;
			speed = 15;
			goldValue = 150;
			defyChance = 50;
			idleSprite = "/images/Minotaur_Idle.png";
			attackSprite = "/images/Minotaur_Attack.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 10;
	        moveResist = 25;
	        debuffResist = 6;
	        deathResist = 10;
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
			
				AudioManager.playMinotaurRush();
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
			
				AudioManager.playMinotaurAxeSlash();
				return result;
			}
		}
	}
	
	public class boar extends Enemies {
		public boar(Optional <Integer> pos) throws IOException {
			name = "Boar";
			maxHealth = 40;
			health = 40;
			damage[0] = 9;
			placeholderDamage[0] = 0;
			defense = 0;
			accuracy = 0;
			crit_chance = 20;
			dodge_chance = 0;
			position = pos.orElse(1);
			size = 1;
			challenge = 3;
			speed = 12;
			goldValue = 100;
			defyChance = 50;
			idleSprite = "/images/Boar.png";
			attackSprite = "/images/Boar.png";
	        bleedResist = 25;
	        burnResist = 15;
	        blightResist = 10;
	        stunResist = 5;
	        moveResist = 15;
	        debuffResist = 6;
	        deathResist = 10;
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
			
				AudioManager.playBoarTuskSwipe();
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
			
				AudioManager.playBoarFrenzy();
				return result;
			}
		}
	}
	public void applyDamage(double damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;  // Ensure health doesn't go negative
    }


}
