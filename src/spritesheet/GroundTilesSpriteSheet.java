package spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
/**
 * Created by Cody Thomas Zeitler on 12/13/2015.
 */
public class GroundTilesSpriteSheet {

    private static BufferedImage groundTilesSpriteSheet;

    public GroundTilesSpriteSheet(){

        try{
            groundTilesSpriteSheet = ImageIO.read(new File("src/SNES - Super Mario World - Ground Tiles.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        groundTilesSpriteSheet = toCompatibleImage(groundTilesSpriteSheet);

    }
    public static BufferedImage getSpriteSheet(){
        return groundTilesSpriteSheet;
    }
    private BufferedImage toCompatibleImage(BufferedImage image)
    {
        // obtain the current system graphical settings
        GraphicsConfiguration gfx_config = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

	/*
	 * if image is already compatible and optimized for current system
	 * settings, simply return it
	 */
        if (image.getColorModel().equals(gfx_config.getColorModel()))
            return image;

        // image is not optimized, so create a new image that is
        BufferedImage new_image = gfx_config.createCompatibleImage(
                image.getWidth(), image.getHeight(), image.getTransparency());

        // get the graphics context of the new image to draw the old image on
        Graphics2D g2d = (Graphics2D) new_image.getGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // return the new optimized image
        return new_image;
    }
}
