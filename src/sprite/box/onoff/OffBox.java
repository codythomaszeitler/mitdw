package sprite.box.onoff;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class OffBox {
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

    //64, 160 --- 80, 176

    public OffBox(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        SX_1 = 64; // 52
        SY_1 = 160; // 39
        SX_2 = 80;
        SY_2 = 176;

        setDx1(x);
        setDy1(y);
        setDx2(x + width);
        setDy2(y + height);
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
