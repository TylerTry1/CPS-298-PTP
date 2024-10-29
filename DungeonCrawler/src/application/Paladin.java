package application;

public class Paladin extends Characters {
    private static final int BASE_HEAL_PERCENTAGE = 10; // Heal percentage for Divine Smite
    private static final double AURA_BOOST = 0.5; // 50% increase for Aura of Courage
    private static final int SHIELD_DEFENSE_INCREASE = 5; // Example defense increase for Shield of Faith
    private static final int AURA_TURNS = 3; // Duration for Aura of Courage

    private boolean isAuraActive = false; // Track if Aura of Courage is active
    private int auraTurnsRemaining = 0;

    public Paladin() {
        name = "Dismas"; // Paladin name
        health = 33;
        max_health = 33;
        damage = 4; 
        defense = 0;
        accuracy = 100;
        crit_chance = 32;
        dodge_chance = 0;
        position = 2;
        speed = 3;
        bleed_resist = 15;
        posion_resist = 5;
        burn_resist = 0;
        }

//    // Shield of Faith: Buff that increases defense and redirects attacks from an ally to the paladin
//    public void useShieldOfFaith(Characters ally) {
//        System.out.println("Using Shield of Faith on " + ally.name);
//        // Increase paladin's defense
//        this.defense += SHIELD_DEFENSE_INCREASE;
//        System.out.println("Defense increased to " + this.defense);
//        // Logic for redirecting attacks can be implemented here
//    }
//
//    // Divine Smite: Attack that deals damage to an enemy and heals the paladin
//    public void useDivineSmite(Characters enemy) {
//        int damageDealt = this.damage; // Use normal damage
//        enemy.takeDamage(damageDealt); // Assuming you implement takeDamage method
//        healSelf(damageDealt * BASE_HEAL_PERCENTAGE / 100);
//        System.out.println("Used Divine Smite on " + enemy.name + " for " + damageDealt + " damage. Healed for " + (damageDealt * BASE_HEAL_PERCENTAGE / 100));
//    }
//
//    // Aura of Courage: Buff that increases the attack and defense of a team member
//    public void useAuraOfCourage(Characters target) {
//        System.out.println("Using Aura of Courage on " + target.name);
//        target.damage += (int) (target.damage * AURA_BOOST); // Increase attack
//        target.defense += (int) (target.defense * AURA_BOOST); // Increase defense
//        isAuraActive = true;
//        auraTurnsRemaining = AURA_TURNS;
//    }
//
//    // Holy Rampart: Attack that bashes an enemy, knocking them back and stunning them
//    public void useHolyRampart(Characters enemy) {
//        System.out.println("Using Holy Rampart on " + enemy.name);
//        int damage = 2; // Low damage value
//        enemy.takeDamage(damage);
//        // Logic for knockback and stun can be implemented here
//        System.out.println("Dealt " + damage + " damage and knocked back the enemy.");
//    }
//
//    // Heal self method
//    private void healSelf(int amount) {
//        this.health += amount;
//        System.out.println(name + " healed for " + amount + " health. Current health: " + this.health);
//    }
//
//    // Update method to handle the Aura of Courage duration
//    public void updateAuraStatus() {
//        if (isAuraActive) {
//            auraTurnsRemaining--;
//            if (auraTurnsRemaining <= 0) {
//                // Reset the buffs
//                System.out.println("Aura of Courage has expired for " + name);
//                isAuraActive = false;
//            }
//        }
//    }
//
//    public void takeDamage(int damage) {
//        this.health -= damage;
//        System.out.println(name + " takes " + damage + " damage. Current health: " + this.health);
//    }
//}
