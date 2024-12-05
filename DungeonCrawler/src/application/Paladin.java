package application;

import java.util.List;
import javafx.scene.text.Text;

public class Paladin extends Characters {

	private int shieldBashDamage;
	private int divineSmiteDamage;
	private int radianceDamage;
	private int dismemberDamage;

	Paladin() {
		name = "Dismas"; // Paladin name
		idleSprite = "applicationImagesPlayerSprites/Paladin_Idle.png";
		attackSprite = "applicationImagesPlayerSprites/Paladin_Attack.png";
		kneelingSprite = "applicationImagesHeroSprites/Paladin/Paladin_Kneeling.png";
		activeSprite = "applicationImagesPlayerSprites/Paladin_Idle.png";
		maxHealth = 72;
		health = 72;
		damage = 10;
		defense = 0;
		accuracy = 100;
		crit_chance = 32;
		dodge_chance = 0;
		position = 1;
		speed = 3;
		defyChance = 50;
	}

	public Paladin(String name, int health, int damage, int critChance, int shieldBashDamage, int divineSmiteDamage,
			int radianceDamage, int dismemberDamage) {
		super(); // Calling the constructor of the parent Characters class
		this.shieldBashDamage = shieldBashDamage;
		this.divineSmiteDamage = divineSmiteDamage;
		this.radianceDamage = radianceDamage;
		this.dismemberDamage = dismemberDamage;
	}

	public int getShieldBashDamage() {
		return shieldBashDamage;
	}

	public void setShieldBashDamage(int shieldBashDamage) {
		this.shieldBashDamage = shieldBashDamage;
	}

	public int getDivineSmiteDamage() {
		return divineSmiteDamage;
	}

	public void setDivineSmiteDamage(int divineSmiteDamage) {
		this.divineSmiteDamage = divineSmiteDamage;
	}

	public int getRadianceDamage() {
		return radianceDamage;
	}

	public void setRadianceDamage(int radianceDamage) {
		this.radianceDamage = radianceDamage;
	}

	public int getDismemberDamage() {
		return dismemberDamage;
	}

	public void setDismemberDamage(int dismemberDamage) {
		this.dismemberDamage = dismemberDamage;
	}

	public void shieldBash(Enemies enemy) {
		double damage = this.getDamage() * 1.5;
		enemy.takeDamage(damage);
	}

	public void divineSmite(Enemies enemy) {
		double damage = this.getDamage() * 2.0;
		enemy.takeDamage(damage);
	}

	public void radiance(Enemies[] enemies) {
		for (Enemies enemy : enemies) {
			double damage = this.getDamage() * 0.5;
			enemy.takeDamage(damage);
		}
	}

	public void dismember(Enemies enemy) {
		double damage = this.getDamage();
		enemy.takeDamage(damage);
	}
}
