package sprite.thwomp.frame;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
public class ThwompDropping {

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


    public ThwompDropping(){
        SX1 = 190;
        SY1 = 470;
        SX2 = 214;
        SY2 = 501;
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
