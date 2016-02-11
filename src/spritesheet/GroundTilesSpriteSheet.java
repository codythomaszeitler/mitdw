package spritesheet;

import javax.imageio.ImageIO;
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

    }
    public static BufferedImage getSpriteSheet(){
        return groundTilesSpriteSheet;
    }
}
