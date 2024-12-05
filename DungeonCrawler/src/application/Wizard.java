package application;

import java.util.List;
import javafx.scene.text.Text;

public class Wizard extends Characters {
	private int fireballDamage = 10;
	private int magicMissileDamage = 4;
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


    public void fireball(Enemies enemy) {
        double damage = this.getDamage() + this.fireballDamage;
        enemy.takeDamage(damage);
    }

    public void magicMissile(Enemies enemy) {
        double damage = this.getDamage()+this.getDamage();
        enemy.takeDamage(damage);
    }

    public void frostBolt(Enemies enemy) {
        double damage = this.frostBoltDamage;
        enemy.takeDamage(damage);
        
    }

    public void staffStrike(Enemies enemy) {
        double damage = this.staffStrikeDamage;
        enemy.takeDamage(damage);
    }
}
