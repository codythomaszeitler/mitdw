package spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class EnemySpriteSheet {

    private static BufferedImage enemy_sprite_sheet;

    public EnemySpriteSheet(){

        try{
            enemy_sprite_sheet = ImageIO.read(new File("src/SNES-Super-Mario-World-Enemies.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BufferedImage getEnemySpriteSheet(){
        return enemy_sprite_sheet;
    }


}
