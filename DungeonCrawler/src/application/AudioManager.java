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
    
    static {
        buttonClickSFX.setVolume(0.05); // Set default volume
        sceneTransitionSFX.setVolume(0.05);
        combatStartSFX.setVolume(0.05);
        successSFX.setVolume(0.05);
        EnemyDeathSFX.setVolume(0.2);
    }

    public static void playButtonClick() {
        buttonClickSFX.play();
    }

    public static void playSceneTransition() {
        sceneTransitionSFX.play();
    }
    public static void playcombatStartSFX() {
    	combatStartSFX.play();
    }
    public static void playsuccessSFX() {
    successSFX.play();
    }
    public static void playEnemyDeathSFX() {
    	EnemyDeathSFX.play();
    }
}
