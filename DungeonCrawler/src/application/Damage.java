package application;

import java.util.Random;

public class Damage {
    private Random random;

    public Damage() {
        this.random = new Random();
    }

    // Calculates damage considering attacker's damage, defender's defense, and critical hits
    public double calculateDamage(Characters attacker, Characters defender) {
        double baseDamage = attacker.damage;

        // Apply buffs/debuffs
//        baseDamage = applyBuffsAndDebuffs(attacker, defender, baseDamage);

        // Apply critical hit chance
        if (isCriticalHit(attacker)) {
            baseDamage *= 1.5;  // Critical hit multiplier
            System.out.println(attacker.name + " scored a critical hit!");
        }

        // Subtract defender's defense from base damage (to make it more realistic)
        double finalDamage = baseDamage - defender.defense;
        if (finalDamage < 0) {
            finalDamage = 0;  // Damage should not be negative
        }

        return finalDamage;
    }

    // Checks if the attack is a critical hit
    private boolean isCriticalHit(Characters attacker) {
        return random.nextInt(100) < attacker.crit_chance;  // Random check based on crit chance
    }

    // Apply buffs/debuffs or other modifiers to the base damage
//    private double applyBuffsAndDebuffs(Characters attacker, Characters defender, double baseDamage) {
//        // Example of a potential buff or debuff: Aura of Courage might increase attack
//        if (attacker instanceof Paladin && ((Paladin) attacker).isAuraActive) {
//            baseDamage *= 1.2;  // Increase attack damage by 20% for example (just an example)
//        }
//
//        // You can add additional conditions for other buffs/debuffs here
//        return baseDamage;
//    }

    // Applies the calculated damage to the defender
    public void applyDamage(Characters defender, double damage) {
        defender.health -= damage;
        if (defender.health < 0) {
            defender.health = 0;
        }
        System.out.println(defender.name + " takes " + damage + " damage! Remaining health: " + defender.health);
    }
}

