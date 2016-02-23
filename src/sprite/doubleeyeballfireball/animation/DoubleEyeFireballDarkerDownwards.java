package sprite.doubleeyeballfireball.animation;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
/*
This is a s location holding class.
If you use the s locations with the corresponding sprite sheet to get the frame
denoted by the object class name.

 */
public class DoubleEyeFireballDarkerDownwards {
    private final int SX_1;
    public int getSX1(){return SX_1;}
    private final int SY_1;
    public int getSY1(){return SY_1;}
    private final int SX_2;
    public int getSX2(){return SX_2;}
    private final int SY_2;
    public int getSY2(){return SY_2;}


    //330, 172 --- 347, 189
    public DoubleEyeFireballDarkerDownwards(){

        SX_1 = 330; // 52
        SY_1 = 189; //173
        SX_2 = 347;
        SY_2 = 173;
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
