package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class ForestTilesSpriteSheet {

    private static BufferedImage forestTilesSpriteSheet;

    public ForestTilesSpriteSheet(){

        try{
            forestTilesSpriteSheet = ImageIO.read(new File("src/SNES - Super Mario World - Forest and Athletic Tiles.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return forestTilesSpriteSheet;
    }
}
