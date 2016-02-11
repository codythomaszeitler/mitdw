/**
 * Created by Cody Thomas Zeitler on 12/13/2015.
 */

//Adheres from the SNES - Super Mario World Ground Tiles sprite sheet.
public class LightBrownDirtMiddle {

    //52, 39 --- 85, 38

    private final int SX_1;
    public int getSX1(){return SX_1;}
    private final int SY_1;
    public int getSY1(){return SY_1;}
    private final int SX_2;
    public int getSX2(){return SX_2;}
    private final int SY_2;
    public int getSY2(){return SY_2;}

    public int x;
    public int getX(){return x;}
    public void setX(int x){this.x = x;}

    public int y;
    public int getY(){return y;}
    public void setY(int y){this.y =y;}

    public int width;
    public int getWidth(){return width;}
    public void setWidth(int width){this.width = width;}

    public int height;
    public int getHeight(){return height;}
    public void setHeight(int height){this.height = height;}

    public LightBrownDirtMiddle(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        SX_1 = 52; // 52
        SY_1 = 39; // 39
        SX_2 = 68;
        SY_2 = 55;
    }

    public int[] getSLocations(){

        int[] s_locations = new int[4];

        s_locations[0] = getSX1();
        s_locations[1] = getSY1();
        s_locations[2] = getSX2();
        s_locations[3] = getSY2();

        return s_locations;
    }
}
