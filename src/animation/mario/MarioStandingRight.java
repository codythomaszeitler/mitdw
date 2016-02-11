package animation.mario;

/**
 * Created by Cody Thomas Zeitler on 12/11/2015.
 */
public class MarioStandingRight {


    private final int SX_1;
    public int getSX1(){return SX_1;}

    private final int SY_1;
    public int getSY1(){return SY_1;}

    private final int SX_2;
    public int getSX2(){return SX_2;}

    private final int SY_2;
    public int getSY2(){return SY_2;}

    public MarioStandingRight(){
        SX_1 = 14;
        SY_1 = 10;
        SX_2 = 28;
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
