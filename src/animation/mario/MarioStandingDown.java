package animation.mario;

/**
 * Created by Cody Thomas Zeitler on 12/11/2015.
 */
/*
This is a s location holding class.
If you use the s locations with the corresponding sprite sheet to get the frame
denoted by the object class name.

 */


public class MarioStandingDown {

    private final int SX_1;
    public int getSX1(){return SX_1;}

    private final int SY_1;
    public int getSY1(){return SY_1;}

    private final int SX_2;
    public int getSX2(){return SX_2;}

    private final int SY_2;
    public int getSY2(){return SY_2;}

    //156,34 --- 172 ,52

    public  MarioStandingDown(){

        SX_1 = 156; // 28
        SY_1 = 34; // 30
        SX_2 = 172; // 14
        SY_2 = 52; // 10

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
