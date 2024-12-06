package application;

import java.util.List;
import javafx.scene.text.Text;

public class Wizard extends Characters {
	private int fireballDamage = 6;
	private int magicMissileDamage = 7;
	private int frostBoltDamage = 7;
	private int staffStrikeDamage = 3;

	public Wizard() {
		name = "Merlin"; // Wizard name
		idleSprite = "applicationImagesPlayerSprites/Wizard_Idle.png";
		attackSprite = "applicationImagesPlayerSprites/Wizard_Attack.png";
		kneelingSprite = "applicationImagesHeroSprites/Wizard/Wizzard_Kneeling.png";
		activeSprite = "applicationImagesPlayerSprites/Wizard_Idle.png";
		maxHealth = 30;
		health = 30;
		damage = 4; // Base damage
		defense = 0;
		accuracy = 90;
		crit_chance = 20; // Lower crit chance for magic attacks
		dodge_chance = 10; // Chance to dodge attacks
		position = 3; // Back row position for a ranged attacker
		speed = 4; // Average speed for a wizard
		defyChance = 50;
	}

	public Wizard(String name, int health, int damage, int critChance, int fireballDamage, int magicMissileDamage,
			int frostBoltDamage, int staffStrikeDamage) {
		super(); // Calling the constructor of the parent Characters class
		this.fireballDamage = fireballDamage;
		this.magicMissileDamage = magicMissileDamage;
		this.frostBoltDamage = frostBoltDamage;
		this.staffStrikeDamage = staffStrikeDamage;
	}
	
	public int getFireballDamage() {
        return fireballDamage;
    }

    public void setFireballDamage(int fireballDamage) {
        this.fireballDamage = fireballDamage;
    }

    public int getMagicMissileDamage() {
        return magicMissileDamage;
    }

    public void setMagicMissileDamage(int magicMissileDamage) {
        this.magicMissileDamage = magicMissileDamage;
    }

    public int getFrostBoltDamage() {
        return frostBoltDamage;
    }

    public void setFrostBoltDamage(int frostBoltDamage) {
        this.frostBoltDamage = frostBoltDamage;
    }

    public int getStaffStrikeDamage() {
        return staffStrikeDamage;
    }

    public void setStaffStrikeDamage(int staffStrikeDamage) {
        this.staffStrikeDamage = staffStrikeDamage;
    }


    public void fireball(Enemies[] enemyTeam) {
    	 for (Enemies enemy : enemyTeam) {
 	        double damage = this.getDamage() * 0.5;
 	        enemy.applyDamage(damage);
    	 }
    }

    public void magicMissile(Enemies targetEnemy) {
        double damage = this.getDamage() * 1.0;
        targetEnemy.applyDamage(damage);
    }

    public void frostBolt(Enemies targetEnemy) {
        double damage = this.getDamage() * 1.2;
        targetEnemy.applyDamage(damage);
        
    }

    public void staffStrike(Enemies targetEnemy) {
        double damage = this.getDamage() * 0.5;
        targetEnemy.applyDamage(damage);
    }
}
