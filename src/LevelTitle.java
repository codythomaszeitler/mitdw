import java.awt.Font;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
/*
Holds the data and manipulation of data of the name of the level printed on the top left of the screen
adjacent rightwise to the number of lives.
 */

public class LevelTitle {

    private String nameOfLevel;//String that will be displayed after the amount of lives remaining.
    public String getNameOfLevel(){return nameOfLevel;} //gets nameOfLevel
    private int x; //x coordinate of where the string will be painted onto the screen.
    public int getX(){return x;} //get x
    private int y; //y coordinate of where the string will be painted onto the screen.
    public int getY(){return y;} //get y

    private int size;
    public int getSize(){return size;}

    private Font font; //type of font string will be printed with.
    public Font getFont(){return font;} //get font


    /*
    Constructor:
    x: x - coordinate of where string will be painted on screen.
    y: y - coordinate of where string will be painted on screen.
    size: how large the string will be printed.
    nameOfLevel: the name of the level, this is the string that will finally be printed.
     */
    public LevelTitle(int x, int y, int size, String nameOfLevel){

        this.x = x;
        this.y = y;
        this.size = size;
        this.nameOfLevel = "Level: " + nameOfLevel;

        font = new Font(nameOfLevel, Font.PLAIN, size);

    }



}
