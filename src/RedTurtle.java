import turtle.red.RedTurtleStandingLeft;
import turtle.red.RedTurtleStandingRight;
import turtle.red.RedTurtleWalkingLeft;
import turtle.red.RedTurtleWalkingRight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/11/2015.
 */
public class RedTurtle implements ActionListener{

    private RedTurtleStandingLeft standingLeft;
    private RedTurtleWalkingLeft walkingLeft;
    private RedTurtleStandingRight standingRight;
    private RedTurtleWalkingRight walkingRight;
    private int frameCounter;
    private int[] s_locations;

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

    private Animation currentFrame;

    private boolean isGoingRight;
    public boolean getIsGoingRight(){return isGoingRight;}
    public void setIsGoingRight(boolean isGoingRight){
        this.isGoingRight = isGoingRight;
    }

    int index_in_linked_list;
    public int getIndexInLinkedList(){return index_in_linked_list;}
    public void setIndexInLinkedList(int index_in_linked_list){this.index_in_linked_list = index_in_linked_list;}

    public void setCurrentFrame(String frame){

        if(frame.equals("STANDING_RIGHT")){
            currentFrame = Animation.STANDING_RIGHT;
        }
        else if (frame.equals("WALKING_RIGHT")){
            currentFrame = Animation.WALKING_RIGHT;
        }
        else if (frame.equals("STANDING_LEFT")){
            currentFrame = Animation.STANDING_LEFT;
        }
        else if (frame.equals("WALKING_LEFT")){
            currentFrame = Animation.WALKING_LEFT;
        }
        else{
            System.out.println("Error occurred in setCurrentFrame in GreenTurtle object.");
        }


    }


    private enum Animation{

        STANDING_RIGHT, WALKING_RIGHT, STANDING_LEFT, WALKING_LEFT;

    }

    public RedTurtle(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        standingLeft = new RedTurtleStandingLeft();
        standingRight = new RedTurtleStandingRight();
        walkingLeft = new RedTurtleWalkingLeft();
        walkingRight = new RedTurtleWalkingRight();

        currentFrame = Animation.STANDING_LEFT;
        frameCounter = 0;
        s_locations = new int[4];

        isGoingRight = false;

        environment = new LinkedList<Rectangle>();

        index_in_linked_list = 0;
    }

    public int[] getSLocations(){

        frameCounter++;

        if(frameCounter == 15){
            if(currentFrame == Animation.WALKING_LEFT || currentFrame == Animation.STANDING_LEFT) {

                if (currentFrame == Animation.STANDING_LEFT) {

                    currentFrame = Animation.WALKING_LEFT;

                    s_locations = standingLeft.getSLocations();
                    frameCounter = 0;
                    return s_locations;

                }
                else if (currentFrame == Animation.WALKING_LEFT) {

                    currentFrame = Animation.STANDING_LEFT;

                    s_locations = walkingLeft.getSLocations();
                    frameCounter = 0;
                    return s_locations;

                }
                else {
                    System.out.println("1.Error occurred in if statement branch of else if of getSLocations in RedTurtle");
                }
            }
            else if (currentFrame == Animation.STANDING_RIGHT || currentFrame == Animation.WALKING_RIGHT){//else if(isGoingRight)
                if (currentFrame == Animation.STANDING_RIGHT) {

                    currentFrame = Animation.WALKING_RIGHT;

                    s_locations =  standingRight.getSLocations();
                    frameCounter = 0;
                    return s_locations;

                }
                else if (currentFrame == Animation.WALKING_RIGHT) {

                    currentFrame = Animation.STANDING_RIGHT;

                    s_locations = walkingRight.getSLocations();
                    frameCounter = 0;
                    return s_locations;

                }
                else {
                    System.out.println("2.Error occurred in if statement branch of else if of getSLocations in RedTurtle");

                }
            }
            else{
                System.out.println("Boolean not currently set isGoingRight RedTurtle");
            }

        }
        else{

            if(currentFrame == Animation.STANDING_RIGHT || currentFrame == Animation.WALKING_RIGHT) {

                if(currentFrame == Animation.STANDING_RIGHT){
                    s_locations = standingRight.getSLocations();
                    return s_locations;
                }
                else if (currentFrame == Animation.WALKING_RIGHT){
                    s_locations = walkingRight.getSLocations();
                    return s_locations;
                }
                else {
                    System.out.println("1. - Error achieved in else- else branch of getSLocations in RedTurtle");
                }
            }
            if(currentFrame == Animation.STANDING_LEFT || currentFrame == Animation.WALKING_LEFT){

                if(currentFrame == Animation.STANDING_LEFT){
                    s_locations = standingLeft.getSLocations();
                    return s_locations;
                }
                else if (currentFrame == Animation.WALKING_LEFT){
                    s_locations = walkingLeft.getSLocations();
                    return s_locations;
                }
                else{
                    System.out.println("1. - Error achieved in else- else branch of getSLocations in RedTurtle");

                }
            }
        }
        return s_locations;

    }

    public Rectangle getCollisionRectangle(){
        return new Rectangle (x, y, width, height);
    }
    public Animation getCurrentFrame() {
        return currentFrame;
    }

    private LinkedList<Rectangle> environment;

    public LinkedList<Rectangle> getEnvironment(){return environment;}
    public void setEnvironment(LinkedList<Rectangle> environment, int index_of_red_turtle_collision_box){

        LinkedList<Rectangle> collision_boxes = (LinkedList)environment.clone();
        collision_boxes.remove(index_of_red_turtle_collision_box);
        this.environment = collision_boxes;

    }

    public void actionPerformed(ActionEvent e){

        boolean collisionDetected = false;

        ListIterator<Rectangle> collision_detector = environment.listIterator();

        while(collision_detector.hasNext()){

            if(collision_detector.next().intersects(getCollisionRectangle())){
                collisionDetected = true;
            }


        }

        if((getCurrentFrame() == Animation.STANDING_LEFT
                || getCurrentFrame() == Animation.WALKING_LEFT)
                && collisionDetected){


            currentFrame =  Animation.STANDING_RIGHT;

            int x = getX() + 4;
            setX(x);

            int y = getY() + 2;
            setY(y);

        }
        else if ((getCurrentFrame() == Animation.STANDING_RIGHT
                || getCurrentFrame() == Animation.WALKING_RIGHT)
                && collisionDetected){


            currentFrame = Animation.STANDING_LEFT;

            int x = getX() - 4;
            setX(x);
            int y = getY() - 2;
            setY(y);
        }
        else {
            if(currentFrame == Animation.STANDING_LEFT || currentFrame == Animation.WALKING_LEFT){


                int new_x_location = getX() - 4;

                setX(new_x_location);

                int new_y_location = getY() - 2;

                setY(new_y_location);
            }

            else if (currentFrame == Animation.STANDING_RIGHT || currentFrame == Animation.WALKING_RIGHT){

                int new_x_location = getX() + 4;

                setX(new_x_location);

                int new_y_location = getY() + 2;

                setY(new_y_location);
            }
            else {
                System.out.println("Error updating X or Y coordinate of Red Koopa in RedTurtle object.");
            }
        }

    }






}
