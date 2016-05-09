package startscreenimages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 2/26/2016.
 */
public class StartScreenInitial {
    private BufferedImage image;

    public StartScreenInitial(){

        try{
            image = ImageIO.read(new File("src/CodyGameScreen1.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    public BufferedImage getImage(){
        return image;
    }
}
