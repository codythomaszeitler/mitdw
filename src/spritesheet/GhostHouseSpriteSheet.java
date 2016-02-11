package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class GhostHouseSpriteSheet {

    private static BufferedImage ghost_house_sprite_sheet;

    public GhostHouseSpriteSheet(){

        try{
            ghost_house_sprite_sheet = ImageIO.read(new File("src/SNES - Super Mario World - Ghost House Tiles.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BufferedImage getSpriteSheet(){
        return ghost_house_sprite_sheet;
    }



}
