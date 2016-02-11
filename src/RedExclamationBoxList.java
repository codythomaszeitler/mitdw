import sprite.box.RedExclamationBox;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/16/2015.
 */
public class RedExclamationBoxList extends LinkedList<RedExclamationBox> {

    private boolean isOn;

    public RedExclamationBoxList(){

        isOn = true;

    }

    public boolean collision(Rectangle rectangle){

        ListIterator<RedExclamationBox> iterator = listIterator();

        while(iterator.hasNext()){

            RedExclamationBox temp = iterator.next();

            if(temp.getBounds().intersects(rectangle) && temp.getCurrentFrame().equals("FILLED")){
                return true;
            }
        }
        return  false;
    }

    public void setAllEmpty(){

        ListIterator<RedExclamationBox> iterator = listIterator();

        while(iterator.hasNext()){

            iterator.next().setEmpty();
        }
    }

    public void setAllFilled(){


        ListIterator<RedExclamationBox> iterator = listIterator();

        while(iterator.hasNext()){

            iterator.next().setFilled();
        }


    }



}
