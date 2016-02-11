import java.awt.*;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class RedBossDoor {

    private final int SX_1;
    private final int SY_1;
    private final int SX_2;
    private final int SY_2;

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

    public RedBossDoor(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        SX_1 = 154;
        SY_1 = 139;
        SX_2 = 187;
        SY_2 = 189;
    }

    public int getSX1(){return SX_1;}
    public int getSY1(){return SY_1;}
    public int getSX2(){return SX_2;}
    public int getSY2(){return SY_2;}


    public Rectangle getVictoryBox(){
        return new Rectangle (x,y,width,height);
    }






}
