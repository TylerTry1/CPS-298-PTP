package application;

import java.util.List;

import javafx.scene.text.Text;

public class Alchemist extends Characters {
    private static final int ACID_PUDDLE_DAMAGE = 4; // Damage from acid over time
    private static final int ACID_PUDDLE_DURATION = 3; // Duration of poison effect
    private static final int HEALING_ELIXIR_AMOUNT = 10; // Healing amount
    private static final int INVIGORATION_BOOST_MIN = 1; // Minimum attack boost
    private static final int INVIGORATION_BOOST_MAX = 2; // Maximum attack boost
    private static final int EXPLOSIVE_FLASK_DAMAGE = 5; // Damage from explosive flask
	Alchemist() {
        name = "Reynauld";
        idleSprite = "applicationImagesPlayerSprites/Alchemist_Idle.png";
        attackSprite = "applicationImagesPlayerSprites/Alchemist_Attack.png";
        kneelingSprite = "applicationImagesHeroSprites/Alchemist/Alchemist_Kneeling.png";
        activeSprite = "applicationImagesPlayerSprites/Alchemist_Idle.png";
        maxHealth = 33;
        health = 33;
        damage = 18;
        defense = 0;
        accuracy = 70;
        crit_chance = 14;
        dodge_chance = 0;
        position = 4;
        speed = 5;
    }
	// Acid Puddle: Creates a puddle that poisons enemies
    public void useAcidPuddle(List<Characters> enemies, Text moveNameText, Text moveDescriptionText) {
    	String moveName = "Acid Puddle";
    	moveNameText.setText(moveName);
    	String description = "Throw a vial of acid to poison the enemy.";
    	moveDescriptionText.setText(description);
        for (Characters enemy : enemies) {
            enemy.takeDamage(ACID_PUDDLE_DAMAGE); // Initial damage
            applyAcidDamage(enemy, ACID_PUDDLE_DURATION); // Apply damage over time
            System.out.println(enemy.name + " is poisoned by the Acid Puddle!");
        }
    }
    

    // Healing Elixir: Throws a healing potion to a teammate
    public void useHealingElixir(Characters target, Text moveNameText, Text moveDescriptionText) {
    	String moveName = "Healing Elixir";
    	moveNameText.setText(moveName);
    	String description = "Heal one hero";
    	moveDescriptionText.setText(description);
    	Heal heal = new Heal();
    	double healAmount = heal.calculateHeal(target, target,	HEALING_ELIXIR_AMOUNT);
    	heal.applyHeal(target, healAmount);

        heal.applyHeal(target, healAmount);
    }

    // Invigoration: Buff that increases the attack of the entire team
    public void useInvigoration(List<Characters> team, Text moveNameText, Text moveDescriptionText) {
    	String moveName = "Invigoration";
    	moveNameText.setText(moveName);
    	String description = "Energizes the team with extra attack for the next turn";
        int boostAmount = (int) (Math.random() * (INVIGORATION_BOOST_MAX - INVIGORATION_BOOST_MIN + 1) + INVIGORATION_BOOST_MIN);
        System.out.println(name + " uses Invigoration! All teammates gain +" + boostAmount + " attack for the next turn.");
        for (Characters teammate : team) {
            teammate.damage += boostAmount; // Increase attack
        }
    }

    // Explosive Flask: Attacks the front two enemies with an explosive flask
    public void useExplosiveFlask(List<Characters> enemies, Text moveNameText, Text moveDescriptionText) {
    	String moveName = "Explosive Flask";
    	moveNameText.setText(moveName);
    	String description = "Throw an explosive flask at the enemy.";
    	moveDescriptionText.setText(description);

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
}
