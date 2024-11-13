package application;

import java.util.List;

public class Wizard extends Characters {
    private static final int FIREBALL_DAMAGE = 6; // Base damage for Fireball
    private static final int FIREBALL_DURATION = 3; // Duration of fire damage over time
    private static final int MAGIC_MISSILE_DAMAGE = 5; // Average damage for Magic Missile
    private static final int FROST_BOLT_DAMAGE = 4; // Damage for Frost Bolt
    private static final int FROST_BOLT_SPEED_DECREASE = 2; // Speed reduction from Frost Bolt
    private static final int STAFF_STRIKE_DAMAGE = 3; // Small damage for Staff Strike

    public Wizard() {
        name = "Merlin"; // Wizard name
        maxHealth = 30;
        health = 30;
        damage = 4; // Base damage
        defense = 0;
        accuracy = 90;
        crit_chance = 20; // Lower crit chance for magic attacks
        dodge_chance = 10; // Chance to dodge attacks
        position = 3; // Back row position for a ranged attacker
        speed = 4; // Average speed for a wizard
    }

    // Fireball: Attacks two targets with splash damage and applies fire damage over time
    public void useFireball(List<Characters> enemies) {
        if (enemies.size() >= 2) {
            Characters enemy1 = enemies.get(0); // Target 1
            Characters enemy2 = enemies.get(1); // Target 2

            enemy1.takeDamage(FIREBALL_DAMAGE); // Initial damage to first target
            enemy2.takeDamage(FIREBALL_DAMAGE); // Initial damage to second target
            applyFireDamage(enemy1, FIREBALL_DURATION); // Apply fire damage over time
            applyFireDamage(enemy2, FIREBALL_DURATION); // Apply fire damage over time

            System.out.println("Dealt " + FIREBALL_DAMAGE + " damage to " + enemy1.name + " and " + enemy2.name + " with Fireball!");
        } else {
            System.out.println("Not enough enemies to target with Fireball!");
        }
    }

    // Magic Missile: Fires a bolt of magic dealing average damage to a single target
    public void useMagicMissile(Characters enemy) {
        enemy.takeDamage(MAGIC_MISSILE_DAMAGE);
    }

    // Frost Bolt: Fires an icicle, dealing damage and reducing the target's speed
    public void useFrostBolt(Characters enemy) {
        enemy.takeDamage(FROST_BOLT_DAMAGE);
        enemy.speed -= FROST_BOLT_SPEED_DECREASE; // Reduce enemy speed
    }

    // Staff Strike: Smacks a single enemy over the head for small damage
    public void useStaffStrike(Characters enemy) {
        enemy.takeDamage(STAFF_STRIKE_DAMAGE);
    }

    // Method to apply fire damage over time
    private void applyFireDamage(Characters enemy, int duration) {
        System.out.println(enemy.name + " is set on fire for " + duration + " turns!");
        for (int i = 0; i < duration; i++) {
            enemy.takeDamage(FIREBALL_DAMAGE); // Apply fire damage each turn
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}