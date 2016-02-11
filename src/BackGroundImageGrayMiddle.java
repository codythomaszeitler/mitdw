/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.Buffer;

public class BackGroundImageGrayMiddle {

    int x_location;
    public int getX(){
        return x_location;
    }
    public void setX(int x_location){
        this.x_location = x_location;
    }

    int y_location;
    public int getY(){
        return y_location;
    }
    public void setY(int y_location){
        this.y_location = y_location;
    }

    int sprite_width;
    public int getWidth(){
        return sprite_width;
    }
    public void setWidth(int sprite_width){
        this.sprite_width = sprite_width;
    }

    int sprite_height;
    public int getHeight(){
        return sprite_height;
    }
    public void setHeight(int sprite_height){
        this.sprite_height = sprite_height;
    }

    /*
    These are the most important values of the entire class.
    These are the coordinates associated with the function call:
    drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
    (These variables correspond with the sx1, sy1, sx2, and sy2 coordinates.)
    These correspond to two points.
    sx1, sy1, correspond to the top left of the partial image.
    sx2, sy2, correspond to the bottom right of the partial image.
    With these two coordinates you get a sub-image within an image.
    */
    final int SX_1 = 17;
    final int SY_1 = 381;
    final int SX_2 = 33;
    final int SY_2 = 397;
    //Get methods of the above 4 variables.
    public int getSX1(){
        return SX_1;
    }
    public int getSX2(){
        return SX_2;
    }
    public int getSY1(){
        return SY_1;
    }
    public int getSY2(){
        return SY_2;
    }

    public BackGroundImageGrayMiddle(int x_location, int y_location,
                                     int sprite_width, int sprite_height){
        this.x_location = x_location;
        this.y_location = y_location;
        this.sprite_width = sprite_width;
        this.sprite_height = sprite_height;


    }


}
