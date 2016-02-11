import sprite.box.OnOffBox;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/15/2015.
 */
public class OnOffBoxList extends LinkedList<OnOffBox> {

    //Given a rectangle, see if it collides with any of the OnOffBoxes present in the linked list.

    public void intersects(Rectangle rectangle){

        ListIterator<OnOffBox> iterator = this.listIterator();
        while(iterator.hasNext()){
            OnOffBox temp = iterator.next();
            Rectangle tempRect = temp.getBounds();
            if(tempRect.intersects(rectangle)){
                System.out.println(temp.hashCode());
                temp.setOn();
            }
        }
    }

    public void setAllOff(){

        ListIterator<OnOffBox> iterator = listIterator();

        while(iterator.hasNext()){

            iterator.next().setOff();
        }

    }

    public boolean isAllOn(){

        ListIterator<OnOffBox> iterator = listIterator();

        while(iterator.hasNext()){

            OnOffBox temp = iterator.next();

            if(temp.getCurrentFrame().equals("OFF")){
                return false;
            }
        }
        return true;

    }


}
