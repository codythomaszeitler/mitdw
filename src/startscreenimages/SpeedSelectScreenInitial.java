package startscreenimages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 5/8/2016.
 */
public class SpeedSelectScreenInitial {

    private BufferedImage image;

    public SpeedSelectScreenInitial(){

        try{
            image = ImageIO.read(new File("src/images/CodyGameScreenSpeeds.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getImage(){
        return image;
    }
}
