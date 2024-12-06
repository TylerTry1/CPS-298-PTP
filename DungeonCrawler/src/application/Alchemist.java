package application;

import java.util.List;
import java.util.Random;

import javafx.scene.text.Text;

public class Alchemist extends Characters {

	private int acidPuddleDamage;
	private int slashDamage;
	private int acidRainDamage;
	private int explosiveFlaskDamage;

	Alchemist() {
		name = "Reynauld";
		idleSprite = "applicationImagesPlayerSprites/Alchemist_Idle.png";
		attackSprite = "applicationImagesPlayerSprites/Alchemist_Attack.png";
		kneelingSprite = "applicationImagesHeroSprites/Alchemist/Alchemist_Kneeling.png";
		activeSprite = "applicationImagesPlayerSprites/Alchemist_Idle.png";
		maxHealth = 33;
		health = 33;
		damage = 5;
		defense = 0;
		accuracy = 70;
		crit_chance = 14;
		dodge_chance = 0;
		position = 4;
		speed = 5;
		defyChance = 50;
	}

	public Alchemist(String name, int health, int damage, int critChance, int acidPuddleDamage, int slashDamage,
			int acidRainDamage, int explosiveFlaskDamage) {
		super(); // Calling the constructor of the parent Characters class
		this.acidPuddleDamage = acidPuddleDamage;
		this.slashDamage = slashDamage;
		this.acidRainDamage = acidRainDamage;
		this.explosiveFlaskDamage = explosiveFlaskDamage;
	}

// Getters and Setters for Alchemist-specific skills
	public int getAcidPuddleDamage() {
		return acidPuddleDamage;
	}

	public void setAcidPuddleDamage(int acidPuddleDamage) {
		this.acidPuddleDamage = acidPuddleDamage;
	}

	public int getSlashDamage() {
		return slashDamage;
	}

	public void setSlashDamage(int slashDamage) {
		this.slashDamage = slashDamage;
	}

	public int getAcidRainDamage() {
		return acidRainDamage;
	}

	public void setAcidRainDamage(int acidRainDamage) {
		this.acidRainDamage = acidRainDamage;
	}

	public int getExplosiveFlaskDamage() {
		return explosiveFlaskDamage;
	}

	public void setExplosiveFlaskDamage(int explosiveFlaskDamage) {
		this.explosiveFlaskDamage = explosiveFlaskDamage;
	}


	public void acidPuddle(Enemies targetEnemy) {
        double damage = this.getDamage() * 1.2;
		targetEnemy.applyDamage(damage);

	}

	public void slash(Enemies targetEnemy) {
        double damage = this.getDamage() * 1.5;
		targetEnemy.applyDamage(damage);
	}

	public void acidRain(Enemies[] enemyTeam) {
		for (Enemies enemy : enemyTeam) {
	        double damage = this.getDamage() * 0.5;
			enemy.applyDamage(damage);
		}
	}

	public void explosiveFlask(Enemies[] enemyTeam) {
		for (Enemies enemy : enemyTeam) {
	        double damage = this.getDamage() * 0.5;
			enemy.applyDamage(damage);
		}
	}
}