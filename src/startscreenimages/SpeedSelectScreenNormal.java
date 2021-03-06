package startscreenimages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 5/7/2016.
 */
public class SpeedSelectScreenNormal {
    private BufferedImage image;

    public SpeedSelectScreenNormal(){

        try{
            image = ImageIO.read(new File("src/images/CodyGameScreenSpeedsNormal.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getImage(){
        return image;
    }
}
