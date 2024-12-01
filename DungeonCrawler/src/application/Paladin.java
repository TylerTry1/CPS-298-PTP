package application;

import java.util.List;
import javafx.scene.text.Text;

public class Paladin extends Characters {
    private static final int BASE_HEAL_PERCENTAGE = 10; // Heal percentage for Divine Smite
    private static final double AURA_BOOST = 0.5; // 50% increase for Aura of Courage
    private static final int SHIELD_DEFENSE_INCREASE = 5; // Example defense increase for Shield of Faith
    private static final int AURA_TURNS = 3; // Duration for Aura of Courage

    private boolean isAuraActive = false; // Track if Aura of Courage is active
    private int auraTurnsRemaining = 0;
    
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
    }

    // Shield of Faith: Buff that increases defense and redirects attacks from an ally to the paladin
    public void useShieldOfFaith(Characters ally, Text moveNameText, Text moveDescriptionText) {
        String moveName = "Shield of Faith";
        moveNameText.setText(moveName);
        String description = "Increase your defense and protect your ally.";
        moveDescriptionText.setText(description);

        this.defense += SHIELD_DEFENSE_INCREASE; // Increase paladin's defense
        System.out.println("Defense increased to " + this.defense);
        // Logic for redirecting attacks can be implemented here
    }

    // Divine Smite: Attack that deals damage to an enemy and heals the paladin
    public void useDivineSmite(Characters enemy, Text moveNameText, Text moveDescriptionText) {
        String moveName = "Divine Smite";
        moveNameText.setText(moveName);
        String description = "Strike the enemy and heal yourself based on damage dealt.";
        moveDescriptionText.setText(description);

        double damageDealt = this.damage; // Use normal damage
        enemy.takeDamage(damageDealt); // Assuming you implement takeDamage method
        healSelf(damageDealt * BASE_HEAL_PERCENTAGE / 100);
        System.out.println("Used Divine Smite on " + enemy.name + " for " + damageDealt + " damage. Healed for " + (damageDealt * BASE_HEAL_PERCENTAGE / 100));
    }

    // Aura of Courage: Buff that increases the attack and defense of a team member
    public void useAuraOfCourage(Characters target, Text moveNameText, Text moveDescriptionText) {
        String moveName = "Aura of Courage";
        moveNameText.setText(moveName);
        String description = "Increase your allies' attack and defense.";
        moveDescriptionText.setText(description);

        target.damage += (int) (target.damage * AURA_BOOST); // Increase attack
        target.defense += (int) (target.defense * AURA_BOOST); // Increase defense
        isAuraActive = true;
        auraTurnsRemaining = AURA_TURNS;
        System.out.println("Aura of Courage activated! Attack and defense of " + target.name + " increased.");
    }

    // Holy Rampart: Attack that bashes an enemy, knocking them back and stunning them
    public void useHolyRampart(Characters enemy, Text moveNameText, Text moveDescriptionText) {
        String moveName = "Holy Rampart";
        moveNameText.setText(moveName);
        String description = "Bash the enemy with a shield, dealing damage and knocking them back.";
        moveDescriptionText.setText(description);

        int damage = 2; // Low damage value
        enemy.takeDamage(damage);
        // Logic for knockback and stun can be implemented here
        System.out.println("Dealt " + damage + " damage and knocked back the enemy.");
    }

    // Update method to handle the Aura of Courage duration
    public void updateAuraStatus(Text moveDescriptionText) {
        if (isAuraActive) {
            auraTurnsRemaining--;
            if (auraTurnsRemaining <= 0) {
                // Reset the buffs
                System.out.println("Aura of Courage has expired for " + name);
                isAuraActive = false;
                moveDescriptionText.setText("Aura of Courage expired.");
            }
        }
    }

    // Heal self method
    private void healSelf(double d) {
        this.health += d;
        System.out.println(name + " healed for " + d + " health. Current health: " + this.health);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Current health: " + this.health);
    }
}
