package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class MarioSpriteSheet {

    private static BufferedImage mario_sprite_sheet;

    public MarioSpriteSheet(){

        try{
            mario_sprite_sheet = ImageIO.read(new File("src/SNES - Super Mario World - Mario copy.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return mario_sprite_sheet;
    }


}
