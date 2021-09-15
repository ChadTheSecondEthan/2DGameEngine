package com.chad.engine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Resources {

    // holds all of the image paths loaded along with the image it corresponds to
    private static final HashMap<String, BufferedImage> imagesLoaded = new HashMap<>();

    private final static File resources;

    static {
        resources = new File("./res/");
    }

    /** loads an image within the resources folder
     * @param path the path of the image, not beginning
     * with a / but ending with the file extension
     * @return the image within the resources folder corresponding
     * to the image path given or null if none exist
     */
    public static BufferedImage loadImage(String path) {
        try {
            // if the image has already been loaded, return that image
            if (imagesLoaded.containsKey(path))
                return imagesLoaded.get(path);

            // otherwise find the image, remember it, and return it
            BufferedImage img = ImageIO.read(new File("./res/" + path));
            imagesLoaded.put(path, img);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return whitePixel();
    }

    private static BufferedImage whitePixel() {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, 255);
        return image;
    }
}
