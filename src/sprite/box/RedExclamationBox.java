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

    private RedExclamationBoxEmpty empty;
    private RedExclamationBoxFilled filled;
    private Animation currentFrame;

    private enum Animation{
        EMPTY, FILLED;
    }

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



    public void setEmpty(){
        currentFrame = Animation.EMPTY;
    }
    public void setFilled(){
        currentFrame = Animation.FILLED;
    }

    public int[] getSLocations(){

        if(currentFrame == Animation.EMPTY){
            return empty.getSLocations();
        }
        else if (currentFrame == Animation.FILLED){
            return filled.getSLocations();
        }
        else{
            System.out.println("Error occurred in getSLocations in RedExclamationBox."
                + "EMPTY or FILLED animation is not in currentFrame. Returning FILLED.");
            return filled.getSLocations();
        }


    }

    public int dx1;
    public int getDx1(){return dx1;}
    public void setDx1(int dx1){this.dx1 = dx1;}

    public int dy1;
    public int getDy1(){return dy1;}
    public void setDy1(int dy1){this.dy1 = dy1;}

    public int dx2;
    public int getDx2(){return dx2;}
    public void setDx2(int dx2){this.dx2 = dx2;}

    public int dy2;
    public int getDy2(){return dy2;}
    public void setDy2(int dy2){this.dy2 = dy2;}


}
