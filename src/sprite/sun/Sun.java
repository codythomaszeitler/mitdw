package sprite.sun;

import sprite.sun.animation.SunEyesClosed;
import sprite.sun.animation.SunEyesOpen;

/**
 * Created by Cody Thomas Zeitler on 12/21/2015.
 */
public class Sun {

    private int frameCounter;

    private SunEyesOpen sunEyesOpen;
    private SunEyesClosed sunEyesClosed;

    private int x;
    private int y;
    private int width;
    private int height;

    private enum Animation{
        EYES_OPEN, EYES_CLOSED;
    }

    public Sun(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


    }
    public int getDx1(){return x;}
    public int getDy1(){return y;}
    public int getDx2(){return x + width;}
    public int getDy2(){return y + height;}

}
