/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */

import java.awt.Font;

public class Life {

    private static int number_of_lives;
    private static String lives_remaining;

    private int x; //x coordinate on where to draw string on screen.
    private int y; //y coordinate on where to draw string on screen.

    private int width; //width of string;
    private int height; //height of string

    private Font font; //how the string will be printed.

    public Life(){
        number_of_lives = 99;
        lives_remaining = "Lives Remaining: " + number_of_lives;
        font = new Font("LIVES REMAINING", Font.PLAIN, 50);
    }

    public Font getFont(){
        return font;
    }

    public static int getNumberOfLives(){
        return number_of_lives;
    }
    public static void setNumberOfLives(int t_number_of_lives){
        number_of_lives = t_number_of_lives;
    }

    public static String getString(){

        lives_remaining = "Lives Remaining: " + number_of_lives;
        return lives_remaining;
    }
    public static void setString(String t_lives_remaining){
        lives_remaining = t_lives_remaining;
    }

    public int getX(){
        return  x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }








}
