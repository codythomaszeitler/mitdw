package sprite.box;

import sprite.box.onoff.OnBox;
import sprite.box.onoff.OffBox;
import spritesheet.GeneralTilesSpriteSheet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class OnOffBox extends Rectangle{

    private OnBox onBox;
    private OffBox offBox;
    private Animation currentFrame;

    public static BufferedImage getAssociatedSpiteSheet(){
        return GeneralTilesSpriteSheet.getSpriteSheet();
    }

    private enum Animation{
        ON, OFF;
    }

    public OnOffBox(int x, int y, int width, int height){

        onBox = new OnBox(x,y,width,height);
        offBox = new OffBox(x,y,width,height);
        currentFrame = Animation.OFF; //Box is automatically considered to be off during instantiation.

        setDx1(x);
        setDy1(y);
        setDx2(x + width);
        setDy2(y + height);

        setBounds(x, y, width, height);
    }
    public void setOn(){
        currentFrame = Animation.ON;
    }
    public void setOff(){
        currentFrame = Animation.OFF;
    }
    public String getCurrentFrame(){
        if(currentFrame == Animation.OFF){
            return "OFF";
        }
        else if (currentFrame == Animation.ON){
            return "ON";
        }
        else{
            System.out.println("Error occurred within funcion getCurrentFrame in OnOffBox object."
                + "Animation was neither ON or OFF. Returning null");
            return null;
        }
    }
    public int[] getSLocations(){

        int s_locations[];

        if(currentFrame == Animation.ON){
            s_locations = onBox.getSLocations();
        }
        else if (currentFrame == Animation.OFF){
            s_locations = offBox.getSLocations();
        }
        else{
            System.out.println("Error occurred getSLocations in OnOffBox Object."
                + "currentFrame did not equal Animation.ON or Animation.OFF");
            System.out.println("Returning frame Animation.OFF");
            s_locations = offBox.getSLocations();
        }
        return s_locations;

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
