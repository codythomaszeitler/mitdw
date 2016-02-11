package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class UndergroundSpriteSheet {

    private static BufferedImage underground_sprite_sheet;

    public UndergroundSpriteSheet(){

        try{
            underground_sprite_sheet = ImageIO.read(new File("src/SNES - Super Mario World - Underground Tiles.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return underground_sprite_sheet;
    }


}
