import java.awt.Font;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class LevelTitle {

    private String nameOfLevel;
    public String getNameOfLevel(){return nameOfLevel;}
    private int x;
    public int getX(){return x;}
    private int y;
    public int getY(){return y;}

    private int size;
    public int getSize(){return size;}

    private Font font;
    public Font getFont(){return font;}


    public LevelTitle(int x, int y, int size, String nameOfLevel){

        this.x = x;
        this.y = y;
        this.size = size;
        this.nameOfLevel = "Level: " + nameOfLevel;

        font = new Font(nameOfLevel, Font.PLAIN, size);

    }



}
