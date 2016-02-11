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
        else if(mario_direction ==  Direction.LEFT){
            int x = getX() - GameControl.getDifficulty().getDifficulty();
            setX(x);
        }
        else if(mario_direction ==  Direction.RIGHT){
            int x = getX() + GameControl.getDifficulty().getDifficulty();
            setX(x);
        }
        else if (mario_direction ==  Direction.DOWN){
            int y = getY() + GameControl.getDifficulty().getDifficulty();
            setY(y);
        }
        else if (mario_direction == Direction.UP){
            int y = getY() - GameControl.getDifficulty().getDifficulty();
            setY(y);
        }
        else {
            System.out.println("Error achieved while in actionPerformed mario_direction Mario object.");
            System.out.println("Printing mario direciton: " + getMarioDirection() );
        }
    }
    private Rectangle collision_rectangle;

    int x_location;
    int y_location;

    int mario_width;
    int mario_height;


    /*private boolean isGoingRight;
    private boolean isGoingLeft;
    private boolean isStandingRight;
    private boolean isStandingLeft;
    private boolean isGoingUp;
    private boolean isGoingDown;*/

    private int frameCounter;

    private Animation currentFrame;

    private enum Animation{

        WALKING_LEFT, WALKING_RIGHT, STANDING_RIGHT, STANDING_LEFT,
        WALKING_UP, STANDING_UP, WALKING_DOWN, STANDING_DOWN;

    }

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
