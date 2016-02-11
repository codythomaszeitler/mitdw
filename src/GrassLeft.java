/**
 * Created by Cody Thomas Zeitler on 12/13/2015.
 */
public class GrassLeft {

    /*
    These are the most important values of the entire class.
    These are the coordinates associated with the function call:
    drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
    (These variables correspond with the sx1, sy1, sx2, and sy2 coordinates.)
    These correspond to two points.
    sx1, sy1, correspond to the top left of the partial image.
    sx2, sy2, correspond to the bottom right of the partial image.
    With these two coordinates you get a sub-image within an image.
    */
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


    public GrassLeft(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        SX_1 = 86; // 52
        SY_1 = 116; // 39
        SX_2 = 102;
        SY_2 = 132;

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
