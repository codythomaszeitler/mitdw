package startscreenimages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cody Thomas Zeitler on 2/26/2016.
 */
public class StartScreenHard {
    private BufferedImage image;
    private int x_1;
    private int x_2;
    private int y_1;
    private int y_2;
    public StartScreenHard(){

        try{
            image = ImageIO.read(new File("src/images/CodyGameScreenHARD.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        x_1 = 1019;
        y_1 = 651;
        x_2 = 1614;
        y_2 = 725;
    }
    public Rectangle getRectangle(){

        return new Rectangle(x_1, y_1, x_2 - x_1, y_2 - y_1);

    }
    public BufferedImage getImage(){
        return image;
    }
}
