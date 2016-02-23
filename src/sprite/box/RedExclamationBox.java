package sprite.box;

import sprite.box.redexclamationbox.RedExclamationBoxEmpty;
import sprite.box.redexclamationbox.RedExclamationBoxFilled;
import spritesheet.GeneralTilesSpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 12/16/2015.
 */
public class RedExclamationBox extends Rectangle{

    private RedExclamationBoxEmpty empty; //empty subimage of the RedExclamation box.
    private RedExclamationBoxFilled filled; //filled sub-imate of the RedExclamation box.
    private Animation currentFrame; //what frame the RedExclamationBox is currently in.

    //The box can either be in the empty state or the filled state.
    private enum Animation{
        EMPTY, FILLED;
    }
    /*
    Creates an RedExclamationBox
    x: denotes the x coordinate of where the RedExclamationBox will be located on screen.
    y: denotes the y coordinate of where the RedExclamationBox will be located on screen.
    width: denotes how long the horizontal width of the RedExclamationBox is.
    height: denotes how long the vertical width of the RedExclamationBox is.

     */
    public RedExclamationBox(int x, int y, int width, int height){

        empty = new RedExclamationBoxEmpty(x,y,width,height);
        filled = new RedExclamationBoxFilled(x,y,width,height);
        currentFrame = Animation.FILLED;

        setBounds(x,y,width,height);

        setDx1(x);
        setDy1(y);
        setDx2(x + width);
        setDy2(y + height);

    }
    public BufferedImage getAssociatedSpriteSheet(){
        return GeneralTilesSpriteSheet.getSpriteSheet();
    }

    /*
    Gets the current frame the RedExclamationBox is currently in.
    Returns a String representation of the frame.
    Can only return either FILLED or EMPTY.
     */
    public String getCurrentFrame(){

        if(currentFrame == Animation.FILLED){
            return "FILLED";
        }
        else if (currentFrame == Animation.EMPTY){
            return "EMPTY";
        }
        else {
            System.out.println("Error occurred in getCurrentFrame in RedExclamationBox."
                + "currentFrame was supposed to be FILLED or EMPTY and was neither."
                + "Returning FILLED");
            return "FILLED";
        }
    }


    /*
    Sets the current frame to empty.
     */
    public void setEmpty(){currentFrame = Animation.EMPTY;}
    /*
    Sets the current frame to filled.
     */
    public void setFilled(){currentFrame = Animation.FILLED;}

    /*
    Returns an int[] array that holds the coordinates of the sprite sheet for the specified image.
    Used for the specific function call
    drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
    The returned array holds sx1,sy1,sx2,sy2 in that order. (corresponding to 0, 1, 2 , 3 as array indexes).
    */
    public int[] getSLocations(){

        //If the current frame is at empty, return the empty subimage.
        if(currentFrame == Animation.EMPTY){
            return empty.getSLocations();
        }
        //If the current frame is at empty, return the filled subimage.
        else if (currentFrame == Animation.FILLED){
            return filled.getSLocations();
        }
        else{
            System.out.println("Error occurred in getSLocations in RedExclamationBox."
                + "EMPTY or FILLED animation is not in currentFrame. Returning FILLED.");
            return filled.getSLocations();
        }


    }
    /*
    Get and Set for dx1, the x-coordinate of the OnOffBox.
     */
    public int dx1;
    public int getDx1(){return dx1;}
    public void setDx1(int dx1){this.dx1 = dx1;}

    /*
    Get and Set for dy1, the y-coordinate of the OnOffBox.
     */
    public int dy1;
    public int getDy1(){return dy1;}
    public void setDy1(int dy1){this.dy1 = dy1;}

    /*
    Get and Set for dx2, the width of the OnOffBox.
    The width is dx1 + width. ( x + width )
    This is just a side effect of how the semantics work for the specif drawImage function used in the paint function.
    */
    public int dx2;
    public int getDx2(){return dx2;}
    public void setDx2(int dx2){this.dx2 = dx2;}

    /*
    Get and Set for dy2, the width of the OnOffBox.
    The width is dy1 + height. ( y + height)
    This is just a side effect of how the semantics work for the specif drawImage function used in the paint function.
    */
    public int dy2;
    public int getDy2(){return dy2;}
    public void setDy2(int dy2){this.dy2 = dy2;}


}
