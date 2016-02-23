package sprite.box;

import sprite.box.onoff.OnBox;
import sprite.box.onoff.OffBox;
import spritesheet.GeneralTilesSpriteSheet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
/*
Class that holds all data and manipulation of data to be able to create and use an on-off box.
An on-off box is a box that is switched from an off to an on position when Mario collides with the
box.
Currently used in: LevelTwo, frantic forest.

 */

public class OnOffBox extends Rectangle{

    private OnBox onBox; //Object that holds data to present the on frame of the on off box.
    private OffBox offBox; //Object that holds data to present the off frame of the on off box
    private Animation currentFrame; //Current frame that on off box is currently in, from set of {ON, OFF}

    //Gets the sprite sheet from heap memory that this sprite is associated with.
    public static BufferedImage getAssociatedSpiteSheet(){
        return GeneralTilesSpriteSheet.getSpriteSheet();
    }

    //Possible frame states that an On-Off box can be in.
    private enum Animation{
        ON, OFF;
    }

    /*
    Creates an OnOffBox
    x: denotes the x coordinate of where the OnOffBox will be located on screen.
    y: denotes the y coordinate of where the OnOffBox will be located on screen.
    width: denotes how long the horizontal width of the OnOffBox is.
    height: denotes how long the vertical width of the OnOffBox is.

     */
    public OnOffBox(int x, int y, int width, int height){

        onBox = new OnBox(x,y,width,height); //Instantiating the onBox of the OnOffBox
        offBox = new OffBox(x,y,width,height); //Instantiating the offBox of the OnOffBox
        currentFrame = Animation.OFF; //Box is automatically considered to be off during instantiation.

        setDx1(x);
        setDy1(y);
        setDx2(x + width); // x + width is just a side effect of the specific drawImage function
        setDy2(y + height); //y + height is just a side effect of the specific drawImage function

        setBounds(x, y, width, height); //setting the bounds of the OnOffBox
    }
    /*
    Sets the current frame to on.
     */
    public void setOn(){currentFrame = Animation.ON;}
    /*
    Sets the current frame to off.
     */
    public void setOff(){currentFrame = Animation.OFF;}
    /*
    Gets the current frame in String format.
     */
    public String getCurrentFrame(){
        if(currentFrame == Animation.OFF){
            return "OFF";
        }
        else if (currentFrame == Animation.ON){
            return "ON";
        }
        else{
            System.out.println("Error occurred within function getCurrentFrame in OnOffBox object."
                + "Animation was neither ON or OFF. Returning null");
            return null;
        }
    }
    /*
    Returns an int[] array that holds the coordinates of the sprite sheet for the specified image.
     */
    public int[] getSLocations(){

        int s_locations[];

        //If current frame is on, return int array of s coordinates corresponding to an on box.
        if(currentFrame == Animation.ON){
            s_locations = onBox.getSLocations();
        }
        //If current frame is off, return int array of s coordinates corresponding to an off box.
        else if (currentFrame == Animation.OFF){
            s_locations = offBox.getSLocations();
        }
        //Error branch. If Error branch, the box will go into off mode.
        else{
            System.out.println("Error occurred getSLocations in OnOffBox Object."
                + "currentFrame did not equal Animation.ON or Animation.OFF");
            System.out.println("Returning frame Animation.OFF");
            s_locations = offBox.getSLocations();
        }
        return s_locations;

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
