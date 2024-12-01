package application;

import javafx.scene.media.AudioClip;

public class AudioManager {
	private static final AudioClip buttonClickSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/buttonClick.wav").toString());
	private static final AudioClip sceneTransitionSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/sceneTransition.wav").toString());
	private static final AudioClip combatStartSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/combatStart.wav").toString());
	private static final AudioClip successSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/goldEarnedScreenSuccessSFX.wav").toString());
	private static final AudioClip EnemyDeathSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/enemyDeathSFX.wav").toString());
	private static final AudioClip DeathsDoorSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/deathsDoorCheckSFX.wav").toString());
	private static final AudioClip newRoundSFX = new AudioClip(
			AudioManager.class.getResource("/SFX/newRound.wav").toString());
	
	// Alchemist
	private static final AudioClip alchemistAcidPuddle = new AudioClip(
			AudioManager.class.getResource("/SFX/AlchimistAttackSFK/Acid_Puddle.wav").toString());
	private static final AudioClip alchemistExplosiveFlask = new AudioClip(
			AudioManager.class.getResource("/SFX/AlchimistAttackSFK/Explosive_Flask.wav").toString());
	private static final AudioClip alchemistHealingElixer = new AudioClip(
			AudioManager.class.getResource("/SFX/AlchimistAttackSFK/HealingElixir.wav").toString());
	private static final AudioClip alchemistInvigoration = new AudioClip(
			AudioManager.class.getResource("/SFX/AlchimistAttackSFK/Invigoration.wav").toString());
	// Assassin
	private static final AudioClip assassinBackstab = new AudioClip(
			AudioManager.class.getResource("/SFX/AssassinAttackSFK/Backstab.wav").toString());
	private static final AudioClip assassinDaggerBarrage = new AudioClip(
			AudioManager.class.getResource("/SFX/AssassinAttackSFK/Dagger_Barrage.wav").toString());
	private static final AudioClip assassinPiercingStab = new AudioClip(
			AudioManager.class.getResource("/SFX/AssassinAttackSFK/Piercing_Stab.wav").toString());
	private static final AudioClip assassinPoisonedBlade = new AudioClip(
			AudioManager.class.getResource("/SFX/AssassinAttackSFK/Poisoned_Blade.wav").toString());
	// Paladin
	private static final AudioClip paladinAuraOfCourage = new AudioClip(
			AudioManager.class.getResource("/SFX/PaladinAttackSFK/Aura_Of_Courage.wav").toString());
	private static final AudioClip paladinDivineSmite = new AudioClip(
			AudioManager.class.getResource("/SFX/PaladinAttackSFK/Divine_Smite.wav").toString());
	private static final AudioClip paladinHolyRampart = new AudioClip(
			AudioManager.class.getResource("/SFX/PaladinAttackSFK/Holy_Rampart.wav").toString());
	private static final AudioClip paladinShieldOfFaith = new AudioClip(
			AudioManager.class.getResource("/SFX/PaladinAttackSFK/Shield_Of_Faith.wav").toString());
	// Wizard
	private static final AudioClip wizardFireball = new AudioClip(
			AudioManager.class.getResource("/SFX/WizardAttackSFK/Fireball.wav").toString());
	private static final AudioClip wizardFrostBolt = new AudioClip(
			AudioManager.class.getResource("/SFX/WizardAttackSFK/Frost_Bolt.wav").toString());
	private static final AudioClip wizardMagicMissile = new AudioClip(
			AudioManager.class.getResource("/SFX/WizardAttackSFK/Magic_Missile.wav").toString());
	private static final AudioClip wizardStaffStrike = new AudioClip(
			AudioManager.class.getResource("/SFX/WizardAttackSFK/Staff_Strike.wav").toString());
	// Boar
	private static final AudioClip boarFrenzy = new AudioClip(
			AudioManager.class.getResource("/SFX/BoarAttackSFK/Frenzy.wav").toString());
	private static final AudioClip boarTuskSwipe = new AudioClip(
			AudioManager.class.getResource("/SFX/BoarAttackSFK/Tusk_Swipe.wav").toString());
	// GiantPummeler
	private static final AudioClip giantPummelerGroundSlam = new AudioClip(
			AudioManager.class.getResource("/SFX/GiantPummelerAttackSFK/Ground_Slam.wav").toString());
	private static final AudioClip giantPummelerShockwave = new AudioClip(
			AudioManager.class.getResource("/SFX/GiantPummelerAttackSFK/Shockwave.wav").toString());

// GiantSlammer
	private static final AudioClip giantSlammerMaceCrush = new AudioClip(
			AudioManager.class.getResource("/SFX/GiantSlammerAttackSFK/Mace_Crush.wav").toString());
	private static final AudioClip giantSlammerSweep = new AudioClip(
			AudioManager.class.getResource("/SFX/GiantSlammerAttackSFK/Sweep.wav").toString());

//GoblinArcher
	private static final AudioClip goblinArcherSnipe = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinArcherAttackSFK/Snipe.wav").toString());
	private static final AudioClip goblinArcherVolleyShot = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinArcherAttackSFK/Volley_Shot.wav").toString());

//GoblinAxeman
	private static final AudioClip goblinAxemanAxeSlash = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinAxemanAttackSFK/Axe_Slash.wav").toString());
	private static final AudioClip goblinAxemanShieldStance = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinAxemanAttackSFK/Shield_Stance.wav").toString());

//GoblinScout
	private static final AudioClip goblinScoutRecon = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinScoutAttackSFK/Recon.wav").toString());
	private static final AudioClip goblinScoutSlice = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinScoutAttackSFK/Slice.wav").toString());

//GoblinShaman
	private static final AudioClip goblinShamanRally = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinShamanAttackSFK/Rally.wav").toString());
	private static final AudioClip goblinShamanStaffSlam = new AudioClip(
			AudioManager.class.getResource("/SFX/GoblinShamanAttackSFK/Staff_Slam.wav").toString());

//Minotaur
	private static final AudioClip minotaurAxeSlash = new AudioClip(
			AudioManager.class.getResource("/SFX/MinotaurAttackSFK/Axe_Slash.wav").toString());
	private static final AudioClip minotaurRush = new AudioClip(
			AudioManager.class.getResource("/SFX/MinotaurAttackSFK/Rush.wav").toString());

//Necromancer
	private static final AudioClip necromancerDarkMagicBlast = new AudioClip(
			AudioManager.class.getResource("/SFX/NecromancerAttackSFK/Dark_Magic_Blast.wav").toString());
	private static final AudioClip necromancerSummonUndead = new AudioClip(
			AudioManager.class.getResource("/SFX/NecromancerAttackSFK/Summon_Undead.wav").toString());

//Skeleton Crossbowman
	private static final AudioClip skeletonCrossbowmanBurstShot = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonCrossbowmanAttackSFK/Burst_Shot.wav").toString());
	private static final AudioClip skeletonCrossbowmanTakeAim = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonCrossbowmanAttackSFK/Take_Aim.wav").toString());

//Skeleton Defender
	private static final AudioClip skeletonDefenderBuckleDown = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonDefenderAttackSFK/Buckle_Down.wav").toString());
	private static final AudioClip skeletonDefenderMaceSlam = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonDefenderAttackSFK/Mace_Slam.wav").toString());

//Skeleton Swordsman
	private static final AudioClip skeletonSwordsmanRushSlash = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonSwordsmanAttackSFK/Rush_Slash.wav").toString());
	private static final AudioClip skeletonSwordsmanVerticalSlash = new AudioClip(
			AudioManager.class.getResource("/SFX/SkeletonSwordsmanAttackSFK/Vertical_Slash.wav").toString());

//Zombie Knight
	private static final AudioClip zombieKnightShoulderSlam = new AudioClip(
			AudioManager.class.getResource("/SFX/ZombieKnightAttackSFK/Shoulder_Slam.wav").toString());
	private static final AudioClip zombieKnightSwordSweep = new AudioClip(
			AudioManager.class.getResource("/SFX/ZombieKnightAttackSFK/Sword_Sweep.wav").toString());

//Zombie Peasant
	private static final AudioClip zombiePeasantBite = new AudioClip(
			AudioManager.class.getResource("/SFX/ZombiePeasantAttackSFK/Bite.wav").toString());
	private static final AudioClip zombiePeasantSway = new AudioClip(
			AudioManager.class.getResource("/SFX/ZombiePeasantAttackSFK/Sway.wav").toString());

    static {
		buttonClickSFX.setVolume(0.05); // Set default volume
		sceneTransitionSFX.setVolume(0.05);
		combatStartSFX.setVolume(0.05);
		successSFX.setVolume(0.05);
		EnemyDeathSFX.setVolume(0.2);
		DeathsDoorSFX.setVolume(0.2);
		newRoundSFX.setVolume(0.2);

		alchemistAcidPuddle.setVolume(0.2);
		alchemistExplosiveFlask.setVolume(0.2);
		alchemistHealingElixer.setVolume(0.2);
		alchemistInvigoration.setVolume(0.2);

		assassinBackstab.setVolume(0.2);
		assassinDaggerBarrage.setVolume(0.2);
		assassinPiercingStab.setVolume(0.2);
		assassinPoisonedBlade.setVolume(0.2);

		paladinAuraOfCourage.setVolume(0.2);
		paladinDivineSmite.setVolume(0.2);
		paladinHolyRampart.setVolume(0.2);
		paladinShieldOfFaith.setVolume(0.2);

		wizardFireball.setVolume(0.2);
		wizardFrostBolt.setVolume(0.2);
		wizardMagicMissile.setVolume(0.2);
		wizardStaffStrike.setVolume(0.2);

		boarFrenzy.setVolume(0.2);
		boarTuskSwipe.setVolume(0.2);

		giantPummelerGroundSlam.setVolume(0.2);
		giantPummelerShockwave.setVolume(0.2);

		giantSlammerMaceCrush.setVolume(0.2);
		giantSlammerSweep.setVolume(0.2);

		goblinArcherSnipe.setVolume(0.2);
		goblinArcherVolleyShot.setVolume(0.2);

		goblinAxemanAxeSlash.setVolume(0.2);
		goblinAxemanShieldStance.setVolume(0.2);

		goblinScoutRecon.setVolume(0.2);
		goblinScoutSlice.setVolume(0.2);

		goblinShamanRally.setVolume(0.2);
		goblinShamanStaffSlam.setVolume(0.2);

		minotaurAxeSlash.setVolume(0.2);
		minotaurRush.setVolume(0.2);

		necromancerDarkMagicBlast.setVolume(0.2);
		necromancerSummonUndead.setVolume(0.2);

		skeletonCrossbowmanBurstShot.setVolume(0.2);
		skeletonCrossbowmanTakeAim.setVolume(0.2);

		skeletonDefenderBuckleDown.setVolume(0.2);
		skeletonDefenderMaceSlam.setVolume(0.2);

		skeletonSwordsmanRushSlash.setVolume(0.2);
		skeletonSwordsmanVerticalSlash.setVolume(0.2);

		zombieKnightShoulderSlam.setVolume(0.2);
		zombieKnightSwordSweep.setVolume(0.2);

		zombiePeasantBite.setVolume(0.2);
		zombiePeasantSway.setVolume(0.2);

    }

    public static void playButtonClick() {buttonClickSFX.play();}

    public static void playSceneTransition() {sceneTransitionSFX.play();}
    public static void playcombatStartSFX() {combatStartSFX.play();}
    public static void playsuccessSFX() {successSFX.play();}
    public static void playEnemyDeathSFX() {EnemyDeathSFX.play();}
    public static void playDeathsDoorSFX() {DeathsDoorSFX.play();}
    public static void playnewRoundSFX() {newRoundSFX.play();}
    
    public static void playAlchemistAcidPuddle() {alchemistAcidPuddle.play();}
    public static void playAlchemistExplosiveFlask() {alchemistExplosiveFlask.play();}
    public static void playAlchemistHealingElixer() {alchemistHealingElixer.play();}
    public static void playAlchemistInvigoration() {alchemistInvigoration.play();}
    
    public static void playAssassinBackstab() {assassinBackstab.play();}
    public static void playAssassinDaggerBarrage() {assassinDaggerBarrage.play();}
    public static void playAssassinPiercingStab() {assassinPiercingStab.play();}
    public static void playAssassinPoisonedBlade() {assassinPoisonedBlade.play();}
    
    public static void playPaladinAuraOfCourage() {paladinAuraOfCourage.play();}
    public static void playPaladinDivineSmite() {paladinDivineSmite.play();}
    public static void playPaladinHolyRampart() {paladinHolyRampart.play();}
    public static void playPaladinShieldOfFaith() {paladinShieldOfFaith.play();}
    
    public static void playWizardFireball() {wizardFireball.play();}
    public static void playWizardFrostBolt() {wizardFrostBolt.play();}
    public static void playWizardMagicMissile() {wizardMagicMissile.play();}
    public static void playWizardStaffStrike() {wizardStaffStrike.play();}
    
    public static void playBoarFrenzy() {boarFrenzy.play();}
    public static void playBoarTuskSwipe() {boarTuskSwipe.play();}
    
    public static void playGiantPummelerGroundSlam() { giantPummelerGroundSlam.play();}
    public static void playGiantPummelerShockwave() { giantPummelerShockwave.play();}
    
    public static void playGiantSlammerMaceCrush() { giantSlammerMaceCrush.play(); }
    public static void playGiantSlammerSweep() { giantSlammerSweep.play(); }
    
    public static void playGoblinArcherSnipe() { goblinArcherSnipe.play(); }
    public static void playGoblinArcherVolleyShot() { goblinArcherVolleyShot.play(); }

    public static void playGoblinAxemanAxeSlash() { goblinAxemanAxeSlash.play(); }
    public static void playGoblinAxemanShieldStance() { goblinAxemanShieldStance.play(); }

    public static void playGoblinScoutRecon() { goblinScoutRecon.play(); }
    public static void playGoblinScoutSlice() { goblinScoutSlice.play(); }

    public static void playGoblinShamanRally() { goblinShamanRally.play(); }
    public static void playGoblinShamanStaffSlam() { goblinShamanStaffSlam.play(); }

    public static void playMinotaurAxeSlash() { minotaurAxeSlash.play(); }
    public static void playMinotaurRush() { minotaurRush.play(); }

    public static void playNecromancerDarkMagicBlast() { necromancerDarkMagicBlast.play(); }
    public static void playNecromancerSummonUndead() { necromancerSummonUndead.play(); }

    public static void playSkeletonCrossbowmanBurstShot() { skeletonCrossbowmanBurstShot.play(); }
    public static void playSkeletonCrossbowmanTakeAim() { skeletonCrossbowmanTakeAim.play(); }

    public static void playSkeletonDefenderBuckleDown() { skeletonDefenderBuckleDown.play(); }
    public static void playSkeletonDefenderMaceSlam() { skeletonDefenderMaceSlam.play(); }

    public static void playSkeletonSwordsmanRushSlash() { skeletonSwordsmanRushSlash.play(); }
    public static void playSkeletonSwordsmanVerticalSlash() { skeletonSwordsmanVerticalSlash.play(); }

    public static void playZombieKnightShoulderSlam() { zombieKnightShoulderSlam.play(); }
    public static void playZombieKnightSwordSweep() { zombieKnightSwordSweep.play(); }

    public static void playZombiePeasantBite() { zombiePeasantBite.play(); }
    public static void playZombiePeasantSway() { zombiePeasantSway.play(); }

}
