package utils;

import javafx.scene.media.AudioClip;

public class SoundManager {
    public static void playSound(String filename) {
        AudioClip sound = new AudioClip(SoundManager.class.getResource("/assets/sounds/" + filename).toString());
        sound.play();
    }
}
