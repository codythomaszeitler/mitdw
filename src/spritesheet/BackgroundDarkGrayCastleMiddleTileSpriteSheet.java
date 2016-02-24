package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody on 12/22/2015.
 */
public class BackgroundDarkGrayCastleMiddleTileSpriteSheet {
    private static BufferedImage background_tiles_level_three_sprite_sheet;

    public BackgroundDarkGrayCastleMiddleTileSpriteSheet(){

        try{
            background_tiles_level_three_sprite_sheet = ImageIO.read(new File("src/BackgroundTilesLevelThree.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return background_tiles_level_three_sprite_sheet;
    }
}
