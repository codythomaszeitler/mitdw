package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class CastleFortressSpriteSheet {

    private static BufferedImage castle_fortress_sprite_sheet;

    public CastleFortressSpriteSheet(){

        try{
            castle_fortress_sprite_sheet = ImageIO.read(new File("src/SNES - Super Mario World - Castle Fortress Tiles copy.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return castle_fortress_sprite_sheet;
    }
}
