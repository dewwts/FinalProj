package utils;

import java.util.Random;

public class Randomizer {
    private static String[] words = {"heal", "joy", "magic", "dandelion", "ghost", "pet", "sword", "lucky", "flower", "game"};

    public static String getRandomWord() {
        Random rand = new Random();
        return words[rand.nextInt(words.length)];
    }
}
