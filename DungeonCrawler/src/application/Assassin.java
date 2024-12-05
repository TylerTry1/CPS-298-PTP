package application;

import java.util.List;

import javafx.scene.text.Text;

public class Assassin extends Characters {

	private int shadowStrikeDamage;
	private int poisonedBladeDamage;
	private int backstabDamage; // Extra multiplier for Backstab damage
	private int daggerBarrageDamage;

	Assassin() {
		name = "Damian";
		idleSprite = "applicationImagesPlayerSprites/Assassin_Idle.png";
		attackSprite = "applicationImagesPlayerSprites/Assassin_Attack.png";
		kneelingSprite = "applicationImagesHeroSprites/Assassin/Assassin_Kneeling.png";
		activeSprite = "applicationImagesPlayerSprites/Assassin_Idle.png";
		maxHealth = 50;
		health = 45;
		damage = 10;
		defense = 0;
		accuracy = 85;
		crit_chance = 10;
		dodge_chance = 0;
		position = 2;
		speed = 7;
		defyChance = 50;
	}

	public Assassin(String name, int health, int damage, int critChance, int shadowStrikeDamage,
			int poisonedBladeDamage, int backstabMultiplier, int daggerBarrageDamage) {
		super(); 
		this.shadowStrikeDamage = shadowStrikeDamage;
		this.poisonedBladeDamage = poisonedBladeDamage;
		this.backstabDamage = backstabMultiplier;
		this.daggerBarrageDamage = daggerBarrageDamage;
	}

// Getters and Setters
	public int getShadowStrikeDamage() {
		return shadowStrikeDamage;
	}

	public void setShadowStrikeDamage(int shadowStrikeDamage) {
		this.shadowStrikeDamage = shadowStrikeDamage;
	}

	public int getPoisonedBladeDamage() {
		return poisonedBladeDamage;
	}

	public void setPoisonedBladeDamage(int poisonedBladeDamage) {
		this.poisonedBladeDamage = poisonedBladeDamage;
	}

	public int getBackstabMultiplier() {
		return backstabDamage;
	}

	public void setBackstabMultiplier(int backstabMultiplier) {
		this.backstabDamage = backstabMultiplier;
	}

	public int getDaggerBarrageDamage() {
		return daggerBarrageDamage;
	}

	public void setDaggerBarrageDamage(int daggerBarrageDamage) {
		this.daggerBarrageDamage = daggerBarrageDamage;
	}

// Assassin's Skills

	public void shadowStrike(Enemies enemy) {
		double damage = this.getDamage() * 1.5;
		enemy.takeDamage(damage);
	}

	public void poisonedBlade(Enemies enemy) {
		double damage = this.poisonedBladeDamage * 1.2;
		enemy.takeDamage(damage);
	}

	public void backstab(Enemies enemy) {
		double damage = this.getDamage() * 1.5;
		enemy.takeDamage(damage);
	}

	public void daggerBarrage(Enemies[] enemies) {
		for (Enemies enemy : enemies) {
			double damage = this.daggerBarrageDamage * 0.5;
			enemy.takeDamage(damage);
		}
	}
}