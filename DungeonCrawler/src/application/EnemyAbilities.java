package application;

import application.Enemies.goblinScout;
import application.Enemies.goblinAxeman;
import application.Enemies.goblinArcher;
import application.Enemies.goblinShaman;
import application.Enemies.skeletonSwordsman;
import application.Enemies.skeletonCrossbowman;
import application.Enemies.skeletonDefender;
import application.Enemies.necromancer;
import application.Enemies.giantPummeler;
import application.Enemies.giantSlammer;
import application.Enemies.zombiePeasant;
import application.Enemies.zombieKnight;
import application.Enemies.minotaur;
import application.Enemies.boar;

// When Abilities hit multiple targets, the farthest back is listed first.
// Will use this later for logic checking to see which positions to actually damage.

public class EnemyAbilities {
	
	// Default to create variables.
	public class enemyAbilitiesDefault {
		Object[] result = {}; // Array to store the amount of damage that will be done, and the positions to damage.
		double damage;
		int[] targetPos;
	}
	
	
	
	public class goblinScoutAbilities extends enemyAbilitiesDefault {
		
		// Slice Ability
		public Object[] Slice() {
			
			damage = goblinScout.damage;
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
	
	public class goblinAxemanAbilities extends enemyAbilitiesDefault{
		
		// Axe Slash 
		public Object[] AxeSlash() {
			
			damage = goblinAxeman.damage;
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
	
	
	public class goblinArcherAbilities extends enemyAbilitiesDefault{
		
		// Volley Shot 
		public Object[] VolleyShot() {
			
			damage = goblinArcher.damage;
			targetPos[0] = 4;
			targetPos[1] = 3;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Snipe
		public Object[] Snipe() {
			
			damage = goblinArcher.damage * 1.75;
			targetPos[0] = 4;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class goblinShamanAbilities extends enemyAbilitiesDefault {
		
		// Rally
		public void Rally() {
			// This ability will need to iterate over an array of all team members
			// so we will come back to it when Enemy_Teams.java is complete.
		}
		
		// Staff Slam
		public Object[] StaffSlam() {
			
			damage = goblinShaman.damage;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class skeletonSwordsmanAbilities extends enemyAbilitiesDefault {
		
		// Rush Slash
		public Object[] RushSlash() {
			
			damage = skeletonSwordsman.damage;
			targetPos[0] = 2;
			targetPos[1] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Vertical Slash
		public Object[] VerticalSlash() {
			
			damage = skeletonSwordsman.damage * 1.25;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
	}
	
	public class skeletonCrossbowmanAbilities extends enemyAbilitiesDefault {
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
	
	public class skeletonDefenderAbilities extends enemyAbilitiesDefault {
		
		// Mace Slam
		public Object[] MaceSlam() {
			
			damage = skeletonDefender.damage;
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
	
	public class necromancerAbilities extends enemyAbilitiesDefault {
		
		// Summon Undead
		// This ability will need to iterate over an array of all team members
		// so we will come back to it when Enemy_Teams.java is complete.
		// Have to check for body pile
		
		// Dark Magic Blast
		public Object[] DarkMagicBlast() {
			
			damage = necromancer.damage;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class giantPummelerAbilities extends enemyAbilitiesDefault {
		
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
			
			damage = giantPummeler.damage;
			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class giantSlammerAbilities extends enemyAbilitiesDefault {
		
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
			
			damage = giantSlammer.damage;
			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class zombiePeasantAbilities extends enemyAbilitiesDefault {
		
		// Bite 
		public Object[] Bite() {
			
			damage = zombiePeasant.damage;
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
	
	public class zombieKnightAbilities extends enemyAbilitiesDefault {
		
		// Shoulder Slam
		public Object[] ShoulderSlam() {
			
			damage = zombieKnight.damage;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Sword Sweep
		public Object[] SwordSweep() {
			
			
			damage = zombieKnight.damage;
			targetPos[0] = 4;
			targetPos[1] = 3;
			targetPos[2] = 2;
			targetPos[3] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class minotaurAbilities extends enemyAbilitiesDefault {
		
		// Rush
		public Object[] Rush() {
			
			damage = minotaur.damage;
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
			
			damage = minotaur.damage;
			targetPos[0] = 2;
			targetPos[1] = 1;

			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
	
	public class boarAbilities extends enemyAbilitiesDefault {
		
		// Tusk Swipe
		public Object[] TuskSwipe() {
			
			damage = boar.damage;
			targetPos[0] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
		
		// Frenzy
		public Object[] Frenzy() {
			
			damage = boar.damage;
			targetPos[0] = 2;
			targetPos[1] = 1;
			
			result[0] = damage;
			result[1] = targetPos;
			
			return result;
		}
	}
}