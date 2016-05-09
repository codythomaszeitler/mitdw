import startscreenimages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Cody Thomas Zeitler on 2/26/2016.
 */
public class StartScreen extends JPanel implements MouseListener, MouseMotionListener{

    private StartScreenInitial startScreenInitial;
    private StartScreenEasy startScreenEasy;
    private Rectangle easyRectangle;
    private StartScreenNormal startScreenNormal;
    private Rectangle normalRectangle;
    private StartScreenHard startScreenHard;
    private Rectangle hardRectangle;
    private StartScreenGodlike startScreenGodlike;
    private Rectangle godlikeRectangle;



    private enum Animation{

        LIVE_SELECT, SPEED_SELECT;

    }

    private Animation state;

    private boolean isMouseInEasy;
    private boolean isMouseInNormal;
    private boolean isMouseInHard;
    private boolean isMouseInGodlike;

    private boolean isGameReadyToLaunch;
    public boolean getIsGameReadyToLaunch(){
        return isGameReadyToLaunch;
    }

    private boolean hasDifficultyBeenSelected;
    public boolean getHasDifficultyBeenSelected() { return hasDifficultyBeenSelected; }

    private Life lives;

    public StartScreen(){

        startScreenInitial = new StartScreenInitial();
        startScreenEasy = new StartScreenEasy();
        startScreenNormal = new StartScreenNormal();
        startScreenHard = new StartScreenHard();
        startScreenGodlike = new StartScreenGodlike();

        easyRectangle = startScreenEasy.getRectangle();
        normalRectangle = startScreenNormal.getRectangle();
        hardRectangle = startScreenHard.getRectangle();
        godlikeRectangle = startScreenGodlike.getRectangle();

        isMouseInEasy = false;
        isMouseInNormal = false;
        isMouseInHard = false;
        isMouseInGodlike = false;


        lives = new Life();

        isGameReadyToLaunch = false;
        state = Animation.LIVE_SELECT;
    }
    public void paintComponent(Graphics g){

        if(state == Animation.LIVE_SELECT) {
            if (isMouseInEasy) {
                g.drawImage(startScreenEasy.getImage(), 0, 0,
                        GameControl.getMainGameFrame().getWidth(),
                        GameControl.getMainGameFrame().getHeight(),
                        null);
            } else if (isMouseInNormal) {
                g.drawImage(startScreenNormal.getImage(), 0, 0,
                        GameControl.getMainGameFrame().getWidth(),
                        GameControl.getMainGameFrame().getHeight(),
                        null);
            } else if (isMouseInHard) {
                g.drawImage(startScreenHard.getImage(), 0, 0,
                        GameControl.getMainGameFrame().getWidth(),
                        GameControl.getMainGameFrame().getHeight(),
                        null);
            } else if (isMouseInGodlike) {
                g.drawImage(startScreenGodlike.getImage(), 0, 0,
                        GameControl.getMainGameFrame().getWidth(),
                        GameControl.getMainGameFrame().getHeight(),
                        null);
            } else { //Initial state
                g.drawImage(startScreenInitial.getImage(), 0, 0,
                        GameControl.getMainGameFrame().getWidth(),
                        GameControl.getMainGameFrame().getHeight(),
                        null);
            }
        }

        else{ //ERROR BRANCH
            System.out.println("Error branch in paint component in Start Screen Animation state.");
        }



    }
    public void mouseDragged(MouseEvent e){

    }
    public void mouseMoved(MouseEvent e){
        if(state == Animation.LIVE_SELECT) {
            if (easyRectangle.contains(e.getPoint())) {
                isMouseInEasy = true;
            } else if (normalRectangle.contains(e.getPoint())) {
                isMouseInNormal = true;
            } else if (hardRectangle.contains(e.getPoint())) {
                isMouseInHard = true;
            } else if (godlikeRectangle.contains(e.getPoint())) {
                isMouseInGodlike = true;
            } else {
                //System.out.println("You're not in anything.");

                isMouseInEasy = false;
                isMouseInNormal = false;
                isMouseInHard = false;
                isMouseInGodlike = false;
            }
        }
        else{ //ERROR BRANCH
            System.out.println("Error branch in paint component in Start Screen Animation state.");
        }
    }


    public void mouseClicked(MouseEvent e){
        if(state == Animation.LIVE_SELECT) {
            if (easyRectangle.contains(e.getPoint())) {
                lives.setNumberOfLives(30);
                //state = Animation.SPEED_SELECT;
                //isGameReadyToLaunch = true;
                hasDifficultyBeenSelected = true;
            } else if (normalRectangle.contains(e.getPoint())) {
                lives.setNumberOfLives(10);
                //state = Animation.SPEED_SELECT;
                //isGameReadyToLaunch = true;
                hasDifficultyBeenSelected = true;

            } else if (hardRectangle.contains(e.getPoint())) {
                lives.setNumberOfLives(3);
                //state = Animation.SPEED_SELECT;
                //isGameReadyToLaunch = true;
                hasDifficultyBeenSelected = true;

            } else if (godlikeRectangle.contains(e.getPoint())) {
                lives.setNumberOfLives(1);
                //state = Animation.SPEED_SELECT;
                //isGameReadyToLaunch = true;
                hasDifficultyBeenSelected = true;

            } else {
                System.out.println("You missed your click." + lives.getNumberOfLives());
            }
        }

        else{ //ERROR BRANCH
            System.out.println("Error branch in paint component in Start Screen Animation state.");
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}


}
