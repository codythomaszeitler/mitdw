package sprite.doubleeyeballfireball;

import sprite.doubleeyeballfireball.animation.DoubleEyeFireballDarkerDownwards;
import sprite.doubleeyeballfireball.animation.DoubleEyeFireballDarkerUpwards;
import sprite.doubleeyeballfireball.animation.DoubleEyeFireballLighterDownwards;
import sprite.doubleeyeballfireball.animation.DoubleEyeFireballLighterUpwards;
import spritesheet.EnemySpriteSheet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
public class DoubleEyeFireball implements ActionListener{

    private DoubleEyeFireballLighterDownwards doubleEyeFireballLighterDownwards;
    private DoubleEyeFireballLighterUpwards doubleEyeFireballLighterUpwards;
    private DoubleEyeFireballDarkerUpwards doubleEyeFireballDarkerUpwards;
    private DoubleEyeFireballDarkerDownwards doubleEyeFireballDarkerDownwards;

    private int x;
    public int getX(){return x;}
    public void setX(int x){this.x = x;}
    private int y;
    public int getY(){return y;}
    public void setY(int y){this.y = y;}
    private int width;
    public int getWidth(){return width;}
    public void setWidth(int width){this.width = width;}
    private int height;
    public int getHeight(){return height;}
    public void setHeight(int height){this.height = height;}

    private int speedOfFireball;
    public int getSpeedOfFireball(){return speedOfFireball;}
    public void setSpeedOfFireball(int speedOfFireball){this.speedOfFireball = speedOfFireball;}

    private int top_x;
    public void setTopX(int top_x){this.top_x = top_x;}
    private int top_y;
    public void setTopY(int top_y){this.top_y = top_y;}
    private int bottom_x;
    public void setBottomX(int bottom_x){this.bottom_x = bottom_x;}
    private int bottom_y;
    public void setBottomY(int bottom_y){this.bottom_y = bottom_y;}

    private boolean isGoingUpwards;

    private Animation currentFrame;
    private int frameCounter;

    public DoubleEyeFireball(int x, int y, int width, int height, boolean isGoingUpwards){

        this.isGoingUpwards = isGoingUpwards;
        top_x = 0;
        top_y = 0;
        bottom_x = 0;
        bottom_y = 0;
        frameCounter = 0;
        speedOfFireball = 10;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if(isGoingUpwards){
            currentFrame = Animation.LIGHTER_UPWARDS;
        }
        else{
            currentFrame = Animation.LIGHTER_DOWNWARDS;
        }
        setDx1(x);
        setDy1(y);
        setDx2(x + width);
        setDy2(y + height);

        doubleEyeFireballLighterDownwards = new DoubleEyeFireballLighterDownwards();
        doubleEyeFireballDarkerUpwards = new DoubleEyeFireballDarkerUpwards();
        doubleEyeFireballLighterUpwards = new DoubleEyeFireballLighterUpwards();
        doubleEyeFireballDarkerDownwards = new DoubleEyeFireballDarkerDownwards();
    }

    public Rectangle getCollisionBox(){
        return new Rectangle(x,y,width,height);
    }

    public BufferedImage getAssociatedSpriteSheet(){
        return EnemySpriteSheet.getEnemySpriteSheet();
    }

    public int dx1;
    public int getDx1(){return x;}
    public void setDx1(int dx1){this.dx1 = dx1;}

    public int dy1;
    public int getDy1(){return y;}
    public void setDy1(int dy1){this.dy1 = dy1;}

    public int dx2;
    public int getDx2(){return x + width;}
    public void setDx2(int dx2){this.dx2 = dx2;}

    public int dy2;
    public int getDy2(){return y + height;}
    public void setDy2(int dy2){this.dy2 = dy2;}


    private enum Animation{

        LIGHTER_UPWARDS, LIGHTER_DOWNWARDS, DARKER_UPWARDS, DARKER_DOWNWARDS;

    }



    public void actionPerformed(ActionEvent e) {

        if (isGoingUpwards) {

            y = y - speedOfFireball;

        }
        if (!isGoingUpwards){

            y = y + speedOfFireball;

        }

        if(y >= bottom_y){
            isGoingUpwards = true;
        }
        if (y <= top_y){
            isGoingUpwards = false;
        }





    }
    public int[] getSLocations(){

        frameCounter++;

        if(frameCounter == 50){

            if(currentFrame == Animation.LIGHTER_UPWARDS){

                currentFrame = Animation.DARKER_UPWARDS;
                frameCounter = 0;

                return doubleEyeFireballDarkerUpwards.getSLocations();

            }
            else if (currentFrame == Animation.DARKER_UPWARDS){

                currentFrame = Animation.LIGHTER_UPWARDS;
                frameCounter = 0;

                return doubleEyeFireballLighterUpwards.getSLocations();

            }
            else if (currentFrame == Animation.LIGHTER_DOWNWARDS){

                currentFrame = Animation.DARKER_DOWNWARDS;
                frameCounter = 0;

                return doubleEyeFireballDarkerDownwards.getSLocations();
            }
            else if (currentFrame == Animation.DARKER_DOWNWARDS){

                currentFrame = Animation.LIGHTER_DOWNWARDS;
                frameCounter = 0;

                return doubleEyeFireballLighterDownwards.getSLocations();

            }
            else {
                System.out.println("Error occurred in getSLocations of DoubleEyeFireball."
                    + "DARKER_UPWARDS, DARKER_DOWNWARDS, LIGHTER_DOWNWARDS, or LIGHTER_UPWARDS expected."
                    + "Returning LIGHTER_UPWARDS frame");
                frameCounter = 0;

                return doubleEyeFireballLighterUpwards.getSLocations();
            }

        }
        else{//Check for swap in direction, else just return what s is currentyl.

            if((currentFrame == Animation.DARKER_DOWNWARDS || currentFrame == Animation.LIGHTER_DOWNWARDS)
                    && isGoingUpwards){
                currentFrame = Animation.LIGHTER_UPWARDS;
                return doubleEyeFireballLighterUpwards.getSLocations();
            }
            else if ((currentFrame == Animation.LIGHTER_UPWARDS || currentFrame == Animation.DARKER_UPWARDS)
                    && !isGoingUpwards){
                currentFrame = Animation.LIGHTER_DOWNWARDS;
                return doubleEyeFireballLighterDownwards.getSLocations();
            }
            else{

                if(currentFrame == Animation.LIGHTER_DOWNWARDS){
                    return doubleEyeFireballLighterDownwards.getSLocations();
                }
                else if(currentFrame == Animation.LIGHTER_UPWARDS){
                    return doubleEyeFireballLighterUpwards.getSLocations();
                }
                else if (currentFrame == Animation.DARKER_DOWNWARDS){
                    return doubleEyeFireballDarkerDownwards.getSLocations();
                }
                else if (currentFrame == Animation.DARKER_UPWARDS){
                    return doubleEyeFireballDarkerUpwards.getSLocations();
                }
                else {
                    System.out.println("Error occurred in getSLocations else of else in DoubleEyeFireball"
                        + "Returning LIGHTER_UPWARDS");
                    return doubleEyeFireballLighterUpwards.getSLocations();
                }
            }
        }
    }
}
