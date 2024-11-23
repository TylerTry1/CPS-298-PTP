package application;

import java.util.Random;

public class Heal {
    private Random random;

    public Heal() {
        this.random = new Random();
    }

    public double calculateHeal(Characters healer, Characters target, double baseHealAmount) {
        double healAmount = baseHealAmount;  // This could be a base healing value from the healer.

        if (isCriticalHeal(healer)) {
            healAmount *= 1.5;  // Critical heals can boost the healing by 1.5x.
            System.out.println(healer.name + " scored a critical heal!");
        }
        return healAmount;
    }

    private boolean isCriticalHeal(Characters healer) {
        return random.nextInt(100) < healer.crit_chance;  // Crit chance for healing based on healer's stats.
    }

    public void applyHeal(Characters target, double healAmount) {
        target.health += healAmount;
        // Optional: Limit the healing to the maximum health of the character
        if (target.health > target.maxHealth) {
            target.health = target.maxHealth;
        }
        System.out.println(target.name + " heals for " + healAmount + " health! Remaining health: " + target.health);
    }
}
