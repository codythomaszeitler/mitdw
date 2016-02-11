package animation.mario;

/**
 * Created by Cody Thomas Zeitler on 12/10/2015.
 */
public class MarioWalkingRight {

    private final int SX_1;
    public int getSX1(){return SX_1;}

    private final int SY_1;
    public int getSY1(){return SY_1;}

    private final int SX_2;
    public int getSX2(){return SX_2;}

    private final int SY_2;
    public int getSY2(){return SY_2;}

    //31, 10 --- 46, 30

    public MarioWalkingRight(){
        SX_1 = 31;
        SY_1 = 10;
        SX_2 = 46;
        SY_2 = 30;
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
