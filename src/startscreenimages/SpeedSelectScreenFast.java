package startscreenimages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 5/8/2016.
 */
public class SpeedSelectScreenFast {
    private BufferedImage image;

    public SpeedSelectScreenFast(){

        try{
            image = ImageIO.read(new File("src/images/CodyGameScreenSpeedsFast.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getImage(){
        return image;
    }
}
