package application;

import java.util.List;

public class Alchemist extends Characters {
    private static final int ACID_PUDDLE_DAMAGE = 4; // Damage from acid over time
    private static final int ACID_PUDDLE_DURATION = 3; // Duration of poison effect
    private static final int HEALING_ELIXIR_AMOUNT = 10; // Healing amount
    private static final int INVIGORATION_BOOST_MIN = 1; // Minimum attack boost
    private static final int INVIGORATION_BOOST_MAX = 2; // Maximum attack boost
    private static final int EXPLOSIVE_FLASK_DAMAGE = 5; // Damage from explosive flask
	Alchemist() {
        name = "Reynauld";
        maxHealth = 54;
        health = 54;
        damage = 18;
        defense = 0;
        accuracy = 70;
        crit_chance = 14;
        dodge_chance = 0;
        position = 1;
        speed = 5;
    }
	// Acid Puddle: Creates a puddle that poisons enemies
    public void useAcidPuddle(List<Characters> enemies) {
        for (Characters enemy : enemies) {
            enemy.takeDamage(ACID_PUDDLE_DAMAGE); // Initial damage
            applyAcidDamage(enemy, ACID_PUDDLE_DURATION); // Apply damage over time
            System.out.println(enemy.name + " is poisoned by the Acid Puddle!");
        }
    }

    // Healing Elixir: Throws a healing potion to a teammate
    public void useHealingElixir(Characters target) {
        target.healSelf(HEALING_ELIXIR_AMOUNT);
    }

    // Invigoration: Buff that increases the attack of the entire team
    public void useInvigoration(List<Characters> team) {
        int boostAmount = (int) (Math.random() * (INVIGORATION_BOOST_MAX - INVIGORATION_BOOST_MIN + 1) + INVIGORATION_BOOST_MIN);
        System.out.println(name + " uses Invigoration! All teammates gain +" + boostAmount + " attack for the next turn.");
        for (Characters teammate : team) {
            teammate.damage += boostAmount; // Increase attack
        }
    }

    // Explosive Flask: Attacks the front two enemies with an explosive flask
    public void useExplosiveFlask(List<Characters> enemies) {
        if (enemies.size() >= 2) {
            Characters enemy1 = enemies.get(0); // Target 1
            Characters enemy2 = enemies.get(1); // Target 2

            enemy1.takeDamage(EXPLOSIVE_FLASK_DAMAGE);
            enemy2.takeDamage(EXPLOSIVE_FLASK_DAMAGE);
            System.out.println("Dealt " + EXPLOSIVE_FLASK_DAMAGE + " damage to " + enemy1.name + " and " + enemy2.name + " with Explosive Flask!");
        } else {
            System.out.println("Not enough enemies to target with Explosive Flask!");
        }
    }

    // Method to apply acid damage over time
    private void applyAcidDamage(Characters enemy, int duration) {
        for (int i = 0; i < duration; i++) {
            enemy.takeDamage(ACID_PUDDLE_DAMAGE); // Apply acid damage each turn
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    // Heal self method
    public void healSelf(int amount) {
        this.health += amount;
        if (this.health > maxHealth) {
            this.health = maxHealth; // Cap healing at max health
        }
        System.out.println(name + " healed for " + amount + " health. Current health: " + this.health);
    }
}
