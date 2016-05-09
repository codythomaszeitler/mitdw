import startscreenimages.SpeedSelectScreenFast;
import startscreenimages.SpeedSelectScreenInitial;
import startscreenimages.SpeedSelectScreenNormal;
import startscreenimages.SpeedSelectScreenSlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Cody Thomas Zeitler on 5/7/2016.
 */
public class SpeedSelectScreen extends JPanel implements MouseListener, MouseMotionListener {


    private boolean isMouseInSlow;
    private boolean isMouseInNormal;
    private boolean isMouseInFast;

    private boolean hasSpeedBeenSelected;
    public boolean getHasSpeedBeenSelected(){ return hasSpeedBeenSelected; }

    private Rectangle slowMouseOverRectangle; //(1350, 413) to (1668, 495)
    private Rectangle normalMouseOverRectangle; // (1275, 582) to (1682, 653)
    private Rectangle fastMouseOverRectangle; // (1366, 736) to (1650, 817)

    private SpeedSelectScreenInitial speedSelectScreenInitial;
    private SpeedSelectScreenSlow speedSelectScreenSlow;
    private SpeedSelectScreenNormal speedSelectScreenNormal;
    private SpeedSelectScreenFast speedSelectScreenFast;

    private BufferedImage initialImage;
    private BufferedImage slowImage;
    private BufferedImage normalImage;
    private BufferedImage fastImage;

    public SpeedSelectScreen(){
        slowMouseOverRectangle = new Rectangle(1350, 413, 1668 - 1350, 495 - 413);
        normalMouseOverRectangle = new Rectangle(1275, 582, 450, 100);
        fastMouseOverRectangle = new Rectangle(1366, 736, 1650 - 1366, 817 - 736);

        isMouseInSlow = false;
        isMouseInNormal = false;
        isMouseInFast = false;

        speedSelectScreenInitial = new SpeedSelectScreenInitial();
        speedSelectScreenSlow = new SpeedSelectScreenSlow();
        speedSelectScreenNormal = new SpeedSelectScreenNormal();
        speedSelectScreenFast = new SpeedSelectScreenFast();

        initialImage = speedSelectScreenInitial.getImage();
        slowImage = speedSelectScreenSlow.getImage();
        normalImage = speedSelectScreenNormal.getImage();
        fastImage = speedSelectScreenFast.getImage();

    }

    public void paintComponent(Graphics g){


        if(true == isMouseInSlow){
            g.drawImage(slowImage, 0, 0,
                    GameControl.getMainGameFrame().getWidth(),
                    GameControl.getMainGameFrame().getHeight(),
                    null);
        }
        else if (true == isMouseInNormal){
            g.drawImage(normalImage, 0, 0,
                    GameControl.getMainGameFrame().getWidth(),
                    GameControl.getMainGameFrame().getHeight(),
                    null);
        }
        else if (true == isMouseInFast){
            g.drawImage(fastImage, 0, 0,
                    GameControl.getMainGameFrame().getWidth(),
                    GameControl.getMainGameFrame().getHeight(),
                    null);
        }
        else{//The mouse is not over slow, normal, or fast. Thus, no wording should be inflated.
            g.drawImage(initialImage, 0, 0,
                    GameControl.getMainGameFrame().getWidth(),
                    GameControl.getMainGameFrame().getHeight(),
                    null);
        }
    }

    public void mouseMoved(MouseEvent e){

        if(slowMouseOverRectangle.contains(e.getPoint())){
            isMouseInSlow = true;
        }
        else if (normalMouseOverRectangle.contains(e.getPoint())){
            isMouseInNormal = true;
        }
        else if (fastMouseOverRectangle.contains(e.getPoint())){
            isMouseInFast = true;
        }
        else{
            //The mouse is in non of the above boxes.
            isMouseInSlow = false;
            isMouseInNormal = false;
            isMouseInFast = false;
        }
    }
    public void mouseClicked(MouseEvent e){

        if(slowMouseOverRectangle.contains(e.getPoint())){
            GameControl.setDifficulty(GameControl.Difficulty.SLOW);
            hasSpeedBeenSelected = true;
        }
        else if(normalMouseOverRectangle.contains(e.getPoint())){
            GameControl.setDifficulty(GameControl.Difficulty.NORMAL);
            hasSpeedBeenSelected = true;
        }
        else if (fastMouseOverRectangle.contains(e.getPoint())){
            GameControl.setDifficulty(GameControl.Difficulty.FAST);
            hasSpeedBeenSelected = true;
        }
        else{

        }



    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}

}
