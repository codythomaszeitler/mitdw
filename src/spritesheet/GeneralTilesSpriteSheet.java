package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class GeneralTilesSpriteSheet {
    private static BufferedImage generalTilesSpriteSheet;

    public GeneralTilesSpriteSheet(){

        try{
            generalTilesSpriteSheet = ImageIO.read(new File("src/SNES - Super Mario World - General Tiles.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getSpriteSheet(){
        return generalTilesSpriteSheet;
    }


}
