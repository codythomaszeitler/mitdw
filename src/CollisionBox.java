import java.awt.*;
import java.lang.Integer;
/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class CollisionBox extends Rectangle {

    //A string identifier. A different one should be given to each collision box.
    private String identifier;
    public String getId(){return identifier;}
    public void setId(String identifier){this.identifier = identifier;}

    private TypeOfBox type;
    private static int numberOfCollisionBoxes; //how many collision boxes have been instantiated in the program.

    private enum TypeOfBox{

    }

    public CollisionBox(int xCord, int yCord, int width, int height){

        setBounds(xCord, yCord, width, height);

        numberOfCollisionBoxes++;
        identifier = Integer.toString(numberOfCollisionBoxes);
    }

    public CollisionBox(Rectangle rectangle){

        setBounds(rectangle);

        numberOfCollisionBoxes++;
        identifier = Integer.toString(numberOfCollisionBoxes);

    }



}
