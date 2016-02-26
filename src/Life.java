/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */

import java.awt.Font;

public class Life {

    private static int number_of_lives = 10; ; //how many lives mario has left.
    /*
    Gets the string that is to printed on the top most left of the game screen e.g: "Lives Remaining: " + number of lives remaining.
     */
    public static String getString(){
        lives_remaining = "Lives Remaining: " + number_of_lives;
        return lives_remaining;
    }
    /*Sets the string that is to be printed on the top most left part of the game screen.*/
    public static void setString(String t_lives_remaining){ lives_remaining = t_lives_remaining; }

    private static String lives_remaining; //the string literally saying "Lives Remaining", will be printed top most left part of game screen.
    /*gets the number of lives mario has left (number_of_lives).*/
    public static int getNumberOfLives(){ return number_of_lives; }
    /*Sets the number of lives mario has left to a new number.*/
    public static void setNumberOfLives(int t_number_of_lives){ number_of_lives = t_number_of_lives;}

    private int x; //x coordinate on where to draw string on screen. (this coordinate should be printing lives remaining on the top left part of the screen.)
    /* Get x (x coordinate of where the string is to be printed on screen.*/
    public int getX(){ return  x;}
    /*Set x to the new value provided in the parameter.*/
    public void setX(int x){this.x = x;}

    private int y; //y coordinate on where to draw string on screen. (this coordinate should be printing lives remaining on the top left part of the screen.)
    /*Gets y (y coordinate of where the string is to be printed.) */
    public int getY(){ return y; }
    /* Sets y to the parameter value (y coordinate of where the string is to be printed.) */
    public void setY(int y){ this.y = y;}

    private int width; //width of string;
    /*Gets width (width is how long the string is horizontally)*/
    public int getWidth(){ return width;}
    /*Sets width (widths is how long the string is horizontally)*/
    public void setWidth(int width){ this.width = width; }

    private int height; //height of string
    /*Gets height (height is how long the string is vertically)*/
    public int getHeight(){ return height;}
    /*Sets height (height is how long the string is vertically)*/
    public void setHeight(int height){ this.height = height; }

    private Font font; //how the string will be printed.
    /*Returns the font associated with the Life object painted on the most top left part of the game screen.*/
    public Font getFont() { return font; }

    /*
    Default Constructor:
    Sets initial lives to 99.
    Creates string "Lives Remaining" concatenated with the initial number of lives (this is the string that will be printed.)
    Creates a new font object with PLAIN printing and size of 50.
     */
    public Life(){
        //number_of_lives = 100; //Mario starts with 99 lives. (this may or may not be changed to reflect difficulty in later patches.
        lives_remaining = "Lives Remaining: " + number_of_lives; //creating string with concatentation of the initial amount of lives.
        font = new Font("LIVES REMAINING", Font.PLAIN, 50); //Sets to plain text and sets size to 50
    }
}
