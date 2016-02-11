import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class DoorList extends LinkedList<Door> {

    private int numberOfElements;


    //Default Constructor.
    public DoorList(){
        numberOfElements = 0;
    }


    //Adds a door to the end of the linked list.
    public void addDoor(Door door){

        add(door);

    }

    //Removes the door in the linked list at the specified index.
    public void removeDoor(int index){

        remove(index);

    }

    //Given a rectangle, see if it collides with any of the Doors(Rectangles) stored in DoorList.
    public  boolean intersects(Rectangle input){

        ListIterator<Door> iterator = listIterator(); //creates an iterator for this object to go through linked list.

        while(iterator.hasNext()){ //Seeing if there is another element left in the linked list to check.

            Rectangle temp = iterator.next(); //Getting the next Door(Rectangle) in the linked list.

            if(temp.intersects(input)){
                return true; //the input has come into contact with one of the Doors(Rectangles) in the linked list.
            }
        }
        return false; //the input did not come into contact with any of the Doors(Rectangles) in the linked list.
    }


}
