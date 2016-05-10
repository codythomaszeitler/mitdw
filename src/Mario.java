import animation.mario.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */



import java.awt.Rectangle;


public class Mario implements ActionListener{

    private enum Direction{
        UP, DOWN, LEFT, RIGHT, STOP;
    }

    private Direction mario_direction;

    public void actionPerformed(ActionEvent e){

        if(mario_direction == Direction.STOP){
            //do nothing. This just keeps us from the error branch when starting the game/level.
        }
        //User has hit the left arrow key. Mario is now moving left until collision or until an input that'sBottomPatrollingGreenKoopaLocations not left is inputted.
        else if(mario_direction ==  Direction.LEFT){
            int x = getX() - GameControl.getDifficulty().getDifficulty();
            setX(x);
        }
        //User has hit the right arrow key. Mario is now moving right until collision or until an input from user that is not right.
        else if(mario_direction ==  Direction.RIGHT){
            int x = getX() + GameControl.getDifficulty().getDifficulty();
            setX(x);
        }
        //User has hit the down arrow key. Mario is now moving down until collision or until the user inputs something that isn't down.
        else if (mario_direction ==  Direction.DOWN){
            int y = getY() + GameControl.getDifficulty().getDifficulty();
            setY(y);
        }
        //User has hit the up arrow key. Mario is now moving down until collision or until user inputs something different.
        else if (mario_direction == Direction.UP){
            int y = getY() - GameControl.getDifficulty().getDifficulty();
            setY(y);
        }
        //Error branch, mario is not in one of his enumeration states.
        else {

            System.out.println("Error achieved while in actionPerformed mario_direction Mario object.");
            System.out.println("Printing mario direciton: " + getMarioDirection() );
        }
    }
    private Rectangle collision_rectangle; //mario'sBottomPatrollingGreenKoopaLocations collision rectangle (dynamically changes to mario'sBottomPatrollingGreenKoopaLocations position on screen)

    int x_location; // x coordinate of mario
    int y_location; // y coordinate of mario

    int mario_width; //width of mario
    int mario_height; //height of mario

    private int frameCounter; //counts the number of frames that have passed since it the reset.

    private Animation currentFrame; //what animation state mario is currently in.

    //All of the possible animation states mario can be in.
    private enum Animation{

        WALKING_LEFT, WALKING_RIGHT, STANDING_RIGHT, STANDING_LEFT,
        WALKING_UP, STANDING_UP, WALKING_DOWN, STANDING_DOWN;

    }

    /*
    Returns an array of ints that has 4 elements within it.
    This is to correspond to a drawImage with the following parameters.
    drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
    int sx1, int sy1, int sx2, int sy2, ImageObserver observer)

    Array[0] = sx1
    Array[1] = sy1
    Array[2] = sx2
    Array[3] = sy2

    These four coordinates make up a sub-image of the sprite sheet.
    So if it'sBottomPatrollingGreenKoopaLocations in STANDING_RIGHT enumeration animation, it will return the sub-image
    coordinates within the array. Use these coordinates within the paint method
    of the levels to properly paint mario in the correct animation.

    This is an incredibly long but otherwise simple function.
    Every five frames mario needs to change his animation based on what animation he is currently in.

    If Walking_Left, the you should go to standing_left to give illusion of animation.
    Vice versa.
    So the formula is if Walking_X then it should change to Standing_X to give illusion of animation.
    Or, if Standing_X, then it should change to Walking_X.
    X can be any of the enumeration types listed in Animation within this class.

    BUT if user inputs a different direction than the one mario is currently facing,
    it will immediately change the frame to the standing position of the
    direction that mario should now be moving.
    So if mario is heading down, and the user inputs up, then the frame will now be
    standing up.

    AND if none of these conditions are met (not five frames have passed and no difference
    in user input) then it will just return the frame that mario is currently on.

     */

    public int[] getSLocations(){

        frameCounter++;

        if(frameCounter == 5){

            frameCounter = 0;

            if(mario_direction == Direction.RIGHT){

                if(currentFrame == Animation.STANDING_RIGHT){


                    s = walkingRight.getSLocations();

                    currentFrame = Animation.WALKING_RIGHT;

                    return s;

                }
                else if (currentFrame == Animation.WALKING_RIGHT){



                    s = standingRight.getSLocations();

                    currentFrame = Animation.STANDING_RIGHT;

                    return s;
                }
                else{ // Mario is not going right in any sense of the matter.

                    s = walkingRight.getSLocations();

                    currentFrame = Animation.WALKING_RIGHT;

                    return s;
                }
            }
            else if (mario_direction == Direction.LEFT){

                if(currentFrame == Animation.STANDING_LEFT){
                    s = walkingLeft.getSLocations();
                    currentFrame = Animation.WALKING_LEFT;
                    return s;

                }
                else if(currentFrame == Animation.WALKING_LEFT) {
                    s = standingLeft.getSLocations();
                    currentFrame = Animation.STANDING_LEFT;
                    return s;
                }
                else {
                    s = walkingLeft.getSLocations();
                    currentFrame = Animation.WALKING_LEFT;
                    return s;
                }
            }
            else if (mario_direction == Direction.DOWN){

                if(currentFrame == Animation.WALKING_DOWN){
                    s = standingDown.getSLocations();
                    currentFrame = Animation.STANDING_DOWN;
                    return s;
                }
                else if (currentFrame == Animation.STANDING_DOWN){
                    s = walkingDown.getSLocations();
                    currentFrame = Animation.WALKING_DOWN;
                    return s;
                }
                else{
                    s = standingDown.getSLocations();
                    currentFrame = Animation.STANDING_DOWN;
                    return s;
                }
            }
            else if (mario_direction == Direction.UP){

                if(currentFrame == Animation.WALKING_UP){

                    s = standingUp.getSLocations();

                    currentFrame = Animation.STANDING_UP;

                    return s;


                }
                else if (currentFrame == Animation.STANDING_UP){


                    s = walkingUp.getSLocations();

                    currentFrame = Animation.WALKING_UP;

                    return s;
                }
                else {


                    s = standingUp.getSLocations();

                    currentFrame = Animation.STANDING_UP;

                    return s;
                }


            }


            else if (mario_direction == Direction.STOP){

                s = standingRight.getSLocations();

                currentFrame = Animation.STANDING_RIGHT;
                return s;

            }

            else {
                System.out.println("Error branch found in getSLocations() in Mario class.");
            }
        }
        else{
            if (mario_direction == Direction.STOP){


                s = standingRight.getSLocations();

                currentFrame = Animation.STANDING_RIGHT;

            }
            else if(mario_direction == Direction.UP){

                if(currentFrame != Animation.STANDING_UP && currentFrame != Animation.WALKING_UP) {

                    s = standingUp.getSLocations();

                    currentFrame = Animation.STANDING_UP;

                    return s;
                }
            }
            else if(mario_direction ==Direction.DOWN){

                if(currentFrame != Animation.STANDING_DOWN && currentFrame != Animation.WALKING_DOWN) {



                    s = standingDown.getSLocations();

                    currentFrame = Animation.STANDING_DOWN;

                    return s;
                }
            }
            else if (mario_direction == Direction.RIGHT){

                if(currentFrame != Animation.STANDING_RIGHT && currentFrame != Animation.WALKING_RIGHT) {



                    s = standingRight.getSLocations();

                    currentFrame = Animation.STANDING_RIGHT;

                    return s;
                }
            }
            else if (mario_direction == Direction.LEFT){

                if(currentFrame != Animation.WALKING_LEFT && currentFrame != Animation.STANDING_LEFT) {


                    s = standingLeft.getSLocations();

                    currentFrame = Animation.STANDING_LEFT;
                    return s;
                }
            }
            else{
                System.out.println("Error branch in else of getSLocations of mario object");
            }

            return s;
        }
        return s;

    }


    private MarioStandingRight standingRight; //mario image of him standing facing right.
    private MarioWalkingRight walkingRight;
    private MarioStandingLeft standingLeft;
    private MarioWalkingLeft walkingLeft;
    private MarioStandingDown standingDown;
    private MarioWalkingDown walkingDown;
    private MarioStandingUp standingUp;
    private MarioWalkingUp walkingUp;
    private int s[];


    //Default constructor
    public Mario(){

        x_location = 0;
        y_location = 0;
        mario_width = 150;
        mario_width = 150;
    }

    public Mario(int x_location, int y_location, int mario_width, int mario_height){
        this.x_location = x_location;
        this.y_location = y_location;
        this.mario_width = mario_width;
        this.mario_height = mario_height;

        mario_direction = Direction.STOP;

        /*isGoingLeft = false;
        isGoingRight = false;
        isStandingLeft = false;
        isStandingRight = true;
        isGoingUp = false;
        isGoingDown = false;*/
        frameCounter = 0;
        currentFrame = Animation.STANDING_RIGHT;
        s = new int[4];

        standingRight = new MarioStandingRight();
        walkingRight = new MarioWalkingRight();
        standingLeft = new MarioStandingLeft();
        walkingLeft = new MarioWalkingLeft();
        standingDown = new MarioStandingDown();
        walkingDown = new MarioWalkingDown();
        standingUp = new MarioStandingUp();
        walkingUp = new MarioWalkingUp();

        collision_rectangle = new Rectangle(x_location,y_location,mario_width,mario_height);

    }
    //Takes away a life.
    public void resetMarioPosition(Level.Levels level){

        if(level == Level.Levels.ONE){

            setX(125);
            setY(125);
            mario_direction = Direction.STOP;
        }
        if(level == Level.Levels.TWO){
            setX(100);
            setY(800);
            mario_direction = Direction.STOP;
        }
        if(level == Level.Levels.THREE){
            setX(225);
            setY(GameControl.getMainGameFrame().getHeight() / 2 - 50);
            mario_direction = Direction.STOP;
        }
    }
    //Doesn't take away a life.
    public void setMarioPosition(Level.Levels level){

        if(level == Level.Levels.ONE){

            setX(75);
            setY(75);
            mario_direction = Direction.STOP;
        }
        if(level == Level.Levels.TWO){
            setX(100);
            setY(800);
            mario_direction = Direction.STOP;
        }
        if(level == Level.Levels.THREE){
            setX(225);
            setY(GameControl.getMainGameFrame().getHeight() / 2 - 50);
            mario_direction = Direction.STOP;
        }

    }

    public Direction getMarioDirection(){

        return mario_direction;

    }

    public void setMarioDirection(String direction){

        if(direction.equals("UP")) {
            mario_direction = Direction.UP;
        }
        else if(direction.equals("DOWN")){
            mario_direction = Direction.DOWN;
        }
        else if(direction.equals("LEFT")){
            mario_direction = Direction.LEFT;
        }
        else if(direction.equals("RIGHT")){
            mario_direction = Direction.RIGHT;
        }
        else if (direction.equals("STOP")){
            mario_direction = Direction.STOP;
        }
        else {
            System.out.println("Error occurred in setMarioDirection(String direction) in Mario object." +
            "UP DOWN LEFT STOP or RIGHT expected. None of those recieved. Setting Mario direction to STOP.");
            mario_direction = Direction.STOP;
        }
    }

    public Rectangle getCollisionRectangle(){

        collision_rectangle = new Rectangle(x_location, y_location, mario_width, mario_height);
        return collision_rectangle;

    }

    public void setX(int x_location){
        this.x_location = x_location;
    }
    public int getX(){
        return x_location;
    }
    public void setY(int y_location){
        this.y_location = y_location;
    }
    public int getY(){
        return y_location;
    }
    public int getWidth(){
        return mario_width;
    }
    public int getHeight(){
        return mario_height;
    }



}
