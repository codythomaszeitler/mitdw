import turtle.green.GreenTurtleStandingLeft;
import turtle.green.GreenTurtleStandingRight;
import turtle.green.GreenTurtleWalkingLeft;
import turtle.green.GreenTurtleWalkingRight;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/9/2015.
 */
public class GreenTurtle implements ActionListener {

    private GreenTurtleStandingRight standingRight;
    private GreenTurtleWalkingRight walkingRight;
    private GreenTurtleStandingLeft standingLeft;
    private GreenTurtleWalkingLeft walkingLeft;
    private int frame_counter;

    int index_in_linked_list;
    public int getIndexInLinkedList(){return index_in_linked_list;}
    public void setIndexInLinkedList(int index_in_linked_list){this.index_in_linked_list = index_in_linked_list;}

    private int x;
    public int getX(){return x;}
    public void setX(int x){this.x = x;}

    private int y;
    public int getY(){return y;}
    public void setY(int y){this.y = y;}

    private int width;
    public int getWidth(){return width;}
    public void setWidth(int width){this.width = width;}

    private int height;
    public int getHeight(){return height;}
    public void setHeight(int height){this.height = height;}


    private Animation current_frame;
    public void setCurrentFrame(String frame){

        if(frame.equals("STANDING_RIGHT")){
            current_frame = Animation.STANDING_RIGHT;
        }
        else if (frame.equals("WALKING_RIGHT")){
            current_frame = Animation.WALKING_RIGHT;
        }
        else if (frame.equals("STANDING_LEFT")){
            current_frame = Animation.STANDING_LEFT;
        }
        else if (frame.equals("WALKING_LEFT")){
            current_frame = Animation.WALKING_LEFT;
        }
        else{
            System.out.println("Error occurred in setCurrentFrame in GreenTurtle object.");
        }


    }
    public Animation getCurrentFrame(){return current_frame;}

    public GreenTurtle(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //isGoingRight = true;

        standingRight = new GreenTurtleStandingRight();
        walkingRight = new GreenTurtleWalkingRight();
        standingLeft = new GreenTurtleStandingLeft();
        walkingLeft = new GreenTurtleWalkingLeft();
        s_locations = new int[4];
        frame_counter = 0;
        current_frame = Animation.STANDING_RIGHT;

        environment = new LinkedList<Rectangle>();

        index_in_linked_list = 0;
    }

    public Rectangle getCollisionRectangle(){
        return new Rectangle(x,y,width,height);
    }

    //What state the GreenTurtle is in.
    private enum Animation{

        STANDING_RIGHT, WALKING_RIGHT, STANDING_LEFT, WALKING_LEFT;
    }

    private int[] s_locations; //Which part of the sub-image is displayed.
    //0 will hold SX1
    //1 will hold SY2
    //2 will hold SX2
    //3 will hold SY2
    public int[] getSLocations(){
        frame_counter++; //Advancing this counter whenever a frame advances in the game (this DOES NOT advance a frame)

        //This if and the connected else change the frame, the rest of the code within this function
        // just returns the sub-image associated with the current frame enumeration you're at.
        if(frame_counter == 15){ //every fifteen frames go into here.

            //If the koopa is going currently right
            if(current_frame == Animation.STANDING_RIGHT || current_frame == Animation.WALKING_RIGHT){//if(isGoingRight) {
                //If you're standing, change to walking.
                if (current_frame == Animation.STANDING_RIGHT) {
                    current_frame = Animation.WALKING_RIGHT; //changing the current_frame.
                    frame_counter = 0; //resetting the frame counter.
                }
                //If you're walking, change to standing.
                else if (current_frame == Animation.WALKING_RIGHT) {
                    current_frame = Animation.STANDING_RIGHT; //changing the current_frame.
                    frame_counter = 0; //resetting  the frame counter.
                }
                //Error branch.
                else {
                    System.out.println("Error occurred in getSLocation() of GreenTurtle.");
                    frame_counter = 0; //resetting the frame counter.
                }
            }
            //The koopa is going left.
            else{
                //If you're standing left, change to walking left.
                if(current_frame == Animation.STANDING_LEFT){
                    current_frame = Animation.WALKING_LEFT;
                    frame_counter = 0;
                }
                //If you're walking left, change to standing left.
                else if(current_frame == Animation.WALKING_LEFT){
                    current_frame = Animation.STANDING_LEFT;
                    frame_counter = 0;
                }
                //Error branch.
                else{
                    System.out.println("Error occurred in getSLocation else statement of Green Turtle left");
                    frame_counter = 0;
                }
            }
        }

        //Whatever current frame is, return the sub-image (based on s coordinates) that associates with the current frame.
        if(current_frame == Animation.STANDING_RIGHT){

            s_locations[0] = standingRight.getSX1();
            s_locations[1] = standingRight.getSY1();
            s_locations[2] = standingRight.getSX2();
            s_locations[3] = standingRight.getSY2();

            return s_locations;

        }
        //Whatever current frame is, return the sub-image (based on s coordinates) that associates with the current frame.
        else if (current_frame == Animation. WALKING_RIGHT){

            s_locations[0] = walkingRight.getSX1();
            s_locations[1] = walkingRight.getSY1();
            s_locations[2] = walkingRight.getSX2();
            s_locations[3] = walkingRight.getSY2();

            return s_locations;
        }
        //Whatever current frame is, return the sub-image (based on s coordinates) that associates with the current frame.
        else if (current_frame == Animation.WALKING_LEFT){

            s_locations[0] = walkingLeft.getSX1();
            s_locations[1] = walkingLeft.getSY1();
            s_locations[2] = walkingLeft.getSX2();
            s_locations[3] = walkingLeft.getSY2();

            return s_locations;
        }
        //Whatever current frame is, return the sub-image (based on s coordinates) that associates with the current frame.
        else if (current_frame == Animation.STANDING_LEFT){

            s_locations[0] = standingLeft.getSX1();
            s_locations[1] = standingLeft.getSY1();
            s_locations[2] = standingLeft.getSX2();
            s_locations[3] = standingLeft.getSY2();

            return s_locations;
        }
        //Error branch, if current_frame is somehow none of the enumeration types set back to standing right.
        else{

            s_locations[0] = standingRight.getSX1();
            s_locations[1] = standingRight.getSY1();
            s_locations[2] = standingRight.getSX2();
            s_locations[3] = standingRight.getSY2();

            System.out.println("Error occurred. Current frame is not equivalent to STANDING or WALKING" +
                    " in getSLocation() in GreenTurtle");

            return s_locations;
        }
    }



    /*
    The environment is the collection of rectangles.
    Whenever the koops moves it will check if it is colliding with any of these rectangles.
    If the koops does collide with any of these rectangles the green koops will reverse it's direction
    completely.
     */
    private LinkedList environment; //The environment in question.
    public LinkedList getEnvironment(){return environment;} //Returns the environment associated with the koopa.
    //Sets the environment, removes the koopa itself from the environment (since the koopa itself is part of
    //the environment, and if it was not removed the koopa will infinitely turn back and forth in it's initial spot)
    public void setEnvironment(LinkedList<Rectangle> environment, int index_of_turtle_collision_box){

        LinkedList<Rectangle> collision_boxes = (LinkedList) environment.clone();

        collision_boxes.remove(index_of_turtle_collision_box);

        this.environment = collision_boxes;
    }
    //Same as other setEnvironment just uses CollisionBoxList instead LinkedList
    //CollisionBoxList is a LinkedList.
    public void setEnvironment(CollisionBoxList collisionBoxes, int index_of_turtle_collision_box){

        CollisionBoxList _collision_boxes = (CollisionBoxList)collisionBoxes.clone();

        _collision_boxes.remove(index_of_turtle_collision_box);

        environment =_collision_boxes;
    }
    public void actionPerformed(ActionEvent e){

        //Go through every collision box possible on the map. If the turtle collides with one of these collision
        //boxes he needs to turn around.

        boolean collisionDetected = false;

        ListIterator<Rectangle> collision_detector = environment.listIterator();

        //Checking if the green turtle has collided with anything from the environment.
        while(collision_detector.hasNext()){

            Rectangle temp_rectangle = collision_detector.next();

            if(temp_rectangle.intersects(getCollisionRectangle())) {

                collisionDetected = true;
            }
        }

        /*
        If you detect a collision AND are going left, then change to going right.
         */
        if((getCurrentFrame() == Animation.STANDING_LEFT
                || getCurrentFrame() == Animation.WALKING_LEFT)
                 && collisionDetected){


            current_frame = Animation.STANDING_RIGHT;

            int x = getX() + 5;
            setX(x);

        }
        /*
        If you detect a collision AND are going right, then change to going left.
         */
        else if ((getCurrentFrame() == Animation.STANDING_RIGHT
                || getCurrentFrame() == Animation.WALKING_RIGHT)
                && collisionDetected){


            current_frame = Animation.STANDING_LEFT;

            int x = getX() - 5;
            setX(x);
        }

        /*
        If no collision is detected, you head into this else.
         */
        else {
            //If you're going left and no collision, then update the green turtle location to left 5 more units.
            if(current_frame == Animation.STANDING_LEFT || current_frame == Animation.WALKING_LEFT){

                int new_x_location = getX() - 5;
                setX(new_x_location);
            }
            //If you're going right and no collision, then update the green turtle location to right 5 more units.
            else if (current_frame == Animation.STANDING_RIGHT || current_frame == Animation.WALKING_RIGHT){

                int new_x_location = getX() + 5;
                setX(new_x_location);
            }
            //Error branch.
            else {
                //No action is taken on green turtle, error is printed to console.
                System.out.println("Error updating X coordinate of Green Koopa in GreenTurtle object.");
            }
        }

    }



}
