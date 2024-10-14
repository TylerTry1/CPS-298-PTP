package application;

import java.util.Random;

public class Damage {
    private Random random;

    public Damage() {
        this.random = new Random();
    }

    public int calculateDamage(characterStats attacker, characterStats defender) {
        int baseDamage = attacker.damage;

        if (isCriticalHit(attacker)) {
            baseDamage *= 2;
            System.out.println(attacker.name + " scored a critical hit!");
        }
        return baseDamage;
    }

    private boolean isCriticalHit(characterStats attacker) {
        return random.nextInt(100) < attacker.crit_chance;
    }

    public void applyDamage(characterStats defender, int damage) {
        defender.health -= damage;
        if (defender.health < 0) {
            defender.health = 0;
        }
        System.out.println(defender.name + " takes " + damage + " damage! Remaining health: " + defender.health);
    }
}

