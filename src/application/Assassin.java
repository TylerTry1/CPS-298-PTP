package application;

public class Assassin extends Characters {
    private static final int POISON_DAMAGE = 5; // Damage per turn from poison
    private static final int POISON_DURATION = 3; // Duration of poison effect
    private static final int BACKSTAB_DAMAGE = 8; // High damage for Backstab
    private static final int DAGGER_BARRAGE_DAMAGE = 3; // Low damage for Dagger Barrage
    private static final int BLEED_DURATION = 3; // Duration of bleed effect
    private boolean isPoisoned = false;
    private int poisonTurnsRemaining = 0;
    
	Assassin() {
		name = "Damian";
		maxHealth = 72;
		health = 72;
		damage = 10;
		defense = 0;
		accuracy = 85;
		crit_chance = 10;
		dodge_chance = 0;
		position = 3;
		speed = 7;
	}

//Poisoned Blade: Buff that applies poison to the enemy
public void usePoisonedBlade() {
    isPoisoned = true;
    poisonTurnsRemaining = POISON_DURATION;
    // The next attack will be guaranteed critical
    accuracy = 100; // Ensures the next attack is accurate
    System.out.println("Next attack will be a guaranteed critical hit!");
}

// Piercing Stab: Attack that ignores the enemy's defense
public void usePiercingStab(Characters enemy) {
    int damageDealt = this.damage; // Average damage
    enemy.takeDamage(damageDealt); // Ignore defense logic
    System.out.println("Dealt " + damageDealt + " damage (ignores defense).");
}

// Backstab: High damage attack that can only be used when stealth is active
public void useBackstab(Characters enemy) {
    int damageDealt = BACKSTAB_DAMAGE; // High damage value
    enemy.takeDamage(damageDealt);
    System.out.println("Dealt " + damageDealt + " damage with a Backstab!");
}

// Dagger Barrage: Attack that hits all enemies
public void useDaggerBarrage(List<Characters> enemies) {
    for (Characters enemy : enemies) {
        enemy.takeDamage(DAGGER_BARRAGE_DAMAGE); // Low damage to each enemy
        enemy.applyBleed(BLEED_DURATION); // Apply bleed effect to each enemy
        System.out.println("Hit " + enemy.name + " for " + DAGGER_BARRAGE_DAMAGE + " damage and applies bleed.");
    }
}

// Update method to handle poison effects
public void updatePoisonStatus() {
    if (isPoisoned) {
        poisonTurnsRemaining--;
        if (poisonTurnsRemaining > 0) {
            // Apply poison damage
            takeDamage(POISON_DAMAGE);
            System.out.println(name + " takes " + POISON_DAMAGE + " poison damage. Health is now " + health);
        } else {
            isPoisoned = false; // Reset poison status
            System.out.println(name + " is no longer poisoned.");
        }
    }
}

public void takeDamage(int damage) {
    this.health -= damage;
    System.out.println(name + " takes " + damage + " damage. Current health: " + this.health);
}

// Method to apply bleed effect to enemies
public void applyBleed(int duration) {
    // Implement bleed effect logic here (e.g., take damage over time)
    System.out.println(name + " applies bleed for " + duration + " turns.");
}
}