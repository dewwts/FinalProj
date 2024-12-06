package utils;

import javafx.scene.image.Image;

public class ImageLoader {
    public static Image load(String path) {
        return new Image(ImageLoader.class.getResourceAsStream("/" + path));
    }
}
