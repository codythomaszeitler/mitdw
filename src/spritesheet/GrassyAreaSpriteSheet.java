package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class GrassyAreaSpriteSheet {

    private static BufferedImage grassyAreaSpriteSheet;

    public GrassyAreaSpriteSheet(){

        try{
            grassyAreaSpriteSheet = ImageIO.read(new File("src/SNES - Grassy Area 1.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BufferedImage getSpriteSheet(){
        return grassyAreaSpriteSheet;
    }



}
