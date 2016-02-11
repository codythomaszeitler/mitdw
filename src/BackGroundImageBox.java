/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */
import spritesheet.GhostHouseSpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.Buffer;

public class BackGroundImageBox {

    public BufferedImage getAssociatedSpriteSheet(){
        return GhostHouseSpriteSheet.getSpriteSheet();
    }

    //Gets a rectangle from the BackGroundImageBox
    public Rectangle getCollisionRectangle(){

        return new Rectangle(x_location, y_location, sprite_width, sprite_height);

    }

    int x_location; //shows how far right on the screen the object is located. (if given positive value)
    public int getX(){
        return x_location;
    }
    public void setX(int x_location){
        this.x_location = x_location;
    }

    int y_location; //shows how far down on the screen the object is located. (if given positive value)
    public int getY(){
        return y_location;
    }
    public void setY(int y_location){
        this.y_location = y_location;
    }

    int sprite_width; //width of the sprite.
    public int getWidth(){
        return sprite_width;
    }
    public void setWidth(int sprite_width){
        this.sprite_width = sprite_width;
    }

    int sprite_height; //height of the sprite.
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
    final int SX_1 = 41;
    final int SY_1 = 4;
    final int SX_2 = 73;
    final int SY_2 = 36;
    //Get methods of the above SX variables.
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

    public int dx1;
    public int getDx1(){return x_location;}
    public void setDx1(int dx1){this.dx1 = dx1;}


    public int dy1;
    public int getDy1(){return y_location;}
    public void setDy1(int dy1){this.dy1 = dy1;}


    public int dx2;
    public int getDx2(){return x_location + sprite_width;}
    public void setDx2(int dx2){this.dx2 = dx2;}


    public int dy2;
    public int getDy2(){return y_location + sprite_height;}
    public void setDy2(int dy2){this.dy2 = dy2;}

    /*
    Params:
        x_location: shows how far right on the screen the object is located. (if given positive value)
        y_location: shows how far down on the screen the object is located. (if given positive value)
        sprite_width: width of the sprite.
        sprite_height: height of the sprite.
     */
    public BackGroundImageBox(int x_location, int y_location,
                                     int sprite_width, int sprite_height){
        this.x_location = x_location;
        this.y_location = y_location;
        this.sprite_width = sprite_width;
        this.sprite_height = sprite_height;
        setDx1(x_location);
        setDy1(y_location);
        setDx2(x_location + sprite_width);
        setDy2(y_location + sprite_height);

    }


}
