import java.awt.*;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class Door extends Rectangle {

    private final int SX1;
    public int getSX1(){
        return SX1;
    }

    private final int SY1;
    public int getSY1(){
        return SY1;
    }

    private final int SX2;
    public int getSX2(){
        return SX2;
    }

    private final int SY2;
    public int getSY2(){
        return SY2;
    }

    private int dx1;
    public void setDx1(int dx1){this.dx1 = dx1;}
    public int  getDx1(){return dx1;}

    private int dy1;
    public void setDy1(int dy1){this.dy1 = dy1;}
    public int getDy1(){return dy1;}

    private int dx2;
    public void setDx2(int dx2){this.dx2 = dx2;}
    public int getDx2(){return x + width;}

    private int dy2;
    public void setDy2(int dy2){this.dy2 = dy2;}
    public int getDy2(){return y + height;}

    //0, 192 ---- 15, 223
    public Door(int x, int y, int width, int height){
        SX1 = 0; // 37
        SY1 = 192;
        SX2 = 15; // 20
        SY2 = 223;

        setDx1(x);
        setDy1(y);
        setDx2(x + width);
        setDy2(y + height);

        setBounds(x, y, width, height);
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
