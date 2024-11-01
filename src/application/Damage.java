package application;

import java.util.Random;

public class Damage {
    private Random random;

    public Damage() {
        this.random = new Random();
    }

    public double calculateDamage(Characters attacker, Characters defender) {
        double baseDamage = attacker.damage;

        if (isCriticalHit(attacker)) {
            baseDamage *= 1.5;
            System.out.println(attacker.name + " scored a critical hit!");
        }
        return baseDamage;
    }

    private boolean isCriticalHit(Characters attacker) {
        return random.nextInt(100) < attacker.crit_chance;
    }

    public void applyDamage(Characters defender, int damage) {
        defender.health -= damage;
        if (defender.health < 0) {
            defender.health = 0;
        }
        System.out.println(defender.name + " takes " + damage + " damage! Remaining health: " + defender.health);
    }
}

