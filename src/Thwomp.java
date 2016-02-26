import sprite.thwomp.frame.ThwompDropping;
import sprite.thwomp.frame.ThwompRaising;
import sprite.thwomp.frame.ThwompSitting;
import spritesheet.EnemySpriteSheet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
public class Thwomp extends Rectangle implements ActionListener{

    //Gets associated sprite sheet.
    public static BufferedImage getAssociatedSpriteSheet(){
        return EnemySpriteSheet.getEnemySpriteSheet();
    }

    //All the possible animation states a thwomp can be in.
    private enum Animation {
        RISING, DROPPING, SITTING
    }
    private ThwompDropping thwmopDropping;
    private ThwompRaising thwompRising;
    private ThwompSitting thwompSitting;

    private int x; //denotes the x coordinate of where the Thwomp will be located on screen.
    private int starting_x; // denotes the starting position of the thwomp.
    private int y; //denotes the y coordinate of where the Thwomp will be located on screen.
    private int starting_y; // the starting y coordinate of where the Thwomp of the thwomp.
    private int width; // denotes how long the horizontal width of the Thwomp is.
    private int height; // denotes how long the vertical width of the Thwomp is.
    private int reset_y_coordinate; //y coordinate thwomp has to hit to start ascending.
    private Animation current_frame;
    private int thwompDropDifferential; // How far away the drop discovery rectangle is vertically from the thwomp. (stats from (x,y))
    private Rectangle thwompDropRectangle; //rectangle that senses if mario has entered, if that is the case, the thwomp will start to descend.
    private void setThwompDropRectangle(Rectangle thwompDropRectangle){
        this.thwompDropRectangle = thwompDropRectangle;
    }

    private int dropSpeed;
    private Rectangle startingRectangle;

    /*
    Sets the thwmop drop differential (how far away the action rectangle is from the thwomp)
    Can not be less than the thwomps height. If argument is less than height, then it does nothing.
     */
    public void setThwompDropDifferential(int thwompDropDifferential){
        if (thwompDropDifferential < this.thwompDropDifferential){
            this.thwompDropDifferential = thwompDropDifferential;
        }

    }

    /*
    Creates a Thwomp
    x: denotes the x coordinate of where the Thwomp will be located on screen.
    y: denotes the y coordinate of where the Thwomp will be located on screen.
    width: denotes how long the horizontal width of the Thwomp is.
    height: denotes how long the vertical width of the Thwomp is.

     */
    public Thwomp(int x, int y, int width, int height) {
        this.x = x;
        starting_x = this.x;
        this.y = y;
        starting_y = this.y;
        this.width = width;
        this.height = height;

        thwompDropDifferential = GameControl.getMainGameFrame().getHeight() - height; //the standard drop differential is 2 times the height
        dropSpeed = 1;
        thwompDropRectangle = new Rectangle(x - width, height, 3 * width, thwompDropDifferential);
        setBounds(x,y,width,height);

        current_frame = Animation.SITTING; //Starting frame of thwomp.
        thwmopDropping = new ThwompDropping();
        thwompRising = new ThwompRaising();
        thwompSitting = new ThwompSitting();

        startingRectangle = new Rectangle(); //the rectangle that holds the data of the starting position of the thwomp.
        startingRectangle.setBounds(getBounds()); //setting the bounds to the original coordinates of the thwomp.

        reset_y_coordinate = y + thwompDropDifferential; //Where the thwomp will stop dropping y coordinate wise.

        setDx1(x);
        setDy1(y);
        setDx2(x + width); // x + width is just a side effect of the specific drawImage function
        setDy2(y + height); //y + height is just a side effect of the specific drawImage function

        setBounds(x, y, width, height); //setting the bounds of the Thwomp

    }

    public void actionPerformed(ActionEvent e){




        //If mario intersects the space that will trigger the thwomp to drop.
        if(GameControl.getMario().getCollisionRectangle().intersects(thwompDropRectangle)){
            if(current_frame == Animation.SITTING) {
                current_frame = Animation.DROPPING;
            }
        }
        //If the thwmop hits the space that makes the thwomp drop, the thwomp will raise back up to its starting position.
        if( getDy1() >= reset_y_coordinate){

                setDy1(getDy1() - 3);
                current_frame = Animation.RISING;
                dropSpeed = 2;
        }
        //If the thwomp is in the dropping animation, moving it down, increasing the move speed by 1.
        if(current_frame == Animation.DROPPING){
            int temp_speed = dropSpeed + 1;
            setDy1(getDy1() + temp_speed);
            dropSpeed = temp_speed;
        }
        //If the thwomp is in the Raising animation, start moving it up to it's startign position.
        if(current_frame == Animation.RISING){
            setDy1(getDy1() - dropSpeed);
        }
        //If the current frame is Rising and the thwomp has reached or exceeded its starting  x or y
        //coordinates then change the animation state to Sitting.
        if(current_frame == Animation.RISING && starting_x >= getDx1() && starting_y >= getDy1()){
             System.out.println("Rising has now hit starting rectanlge");
            current_frame = Animation.SITTING;
        }
    }


    /*
    Returns an int[] array that holds the coordinates of the sprite sheet for the specified image.
    Used for the specific function call
    drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
    The returned array holds sx1,sy1,sx2,sy2 in that order. (corresponding to 0, 1, 2 , 3 as array indexes).
    */
    public int[] getSLocations(){

        if(current_frame == Animation.SITTING){
            return thwompSitting.getSLocations();
        }
        else if ( current_frame == Animation.DROPPING){
            return thwmopDropping.getSLocations();
        }
        else if (current_frame == Animation.RISING ){
            return thwompRising.getSLocations();
        }
        //Error branch.
        else{
            System.out.println("getSLocations() reached the error branch. Thwomp will now return"
             + " SITTING frame.");
            return thwompSitting.getSLocations();
        }
    }
    /*
    Sets the frame to one of the given frames, SITTING || DROPPING || RISING.
    Parameters can only be one of these three. Any other parameter will set frame to SITTING.
     */
    public void setFrame(String current_frame){

        if(current_frame.equals("SITTING")){
            this.current_frame = Animation.SITTING;
        }
        else if (current_frame.equals("DROPPING")){
            this.current_frame = Animation.DROPPING;
        }
        else if (current_frame.equals("RISING")){
            this.current_frame = Animation.RISING;
        }
        //Error branch.
        else{
            System.out.println("Incorrect parameter given. Expected: SITTING || DROPPING  || RISING "
                + "inputted: " + current_frame + " Setting frame to SITTING");
            this.current_frame = Animation.SITTING;
        }
    }

    /*
    Get and Set for dx1, the x-coordinate of the Thwomp.
     */
    public int dx1;
    public int getDx1(){return dx1;}
    public void setDx1(int dx1){


        setThwompDropRectangle(new Rectangle(dx1 - width, height, 3 * width, thwompDropDifferential));
        dx2 = dx1 + width;
        setBounds(dx1, y, width, height);
        this.dx1 = dx1;
    }

    /*
    Get and Set for dy1, the y-coordinate of the Thwomp.
     */
    public int dy1;
    public int getDy1(){return dy1;}
    public void setDy1(int dy1){
        //setThwompDropRectangle(new Rectangle(x - width, thwompDropDifferential + dy1, 3 * width, height));
        dy2 = dy1 + height;
        setBounds(dx1, dy1, width, height);
        this.dy1 = dy1;
    }

    /*
    Get and Set for dx2, the width of the Thwomp.
    The width is dx1 + width. ( x + width )
    This is just a side effect of how the semantics work for the specif drawImage function used in the paint function.
     */
    public int dx2;
    public int getDx2(){return dx2;}
    public void setDx2(int dx2){
        setBounds(dx1, dy1, width, height);
        this.dx2 = dx2;
    }

    /*
    Get and Set for dy2, the width of the Thwomp.
    The width is dy1 + height. ( y + height)
    This is just a side effect of how the semantics work for the specif drawImage function used in the paint function.
     */
    public int dy2;
    public int getDy2(){return dy2;}
    public void setDy2(int dy2){
        setBounds(dx1, dy1, width, height);
        this.dy2 = dy2;
    }
}
