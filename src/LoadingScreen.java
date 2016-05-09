import javax.swing.*;
import java.awt.*;

/**
 * Created by Cody Thomas Zeitler on 5/9/2016.
 */
//Ignore this class for right now.
public class LoadingScreen extends JPanel implements Runnable {

    private int x; //x_coordinate of where Loading... will be printed on screen.
    private int y; //y_coordinate of where Loading... will be printed on screen.

    private boolean hasLoaded; //tells you whether an object has loaded.
    public boolean getHasLoaded(){return hasLoaded;}
    public void setHasLoaded(boolean hasLoaded){this.hasLoaded = hasLoaded;}

    public LoadingScreen(){
        x = 150;
        y = 750;

        hasLoaded = false;
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,2000,2000);

        g.setColor(Color.WHITE);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("Loading...", x, y);
    }

    @Override
    public void run() {

    }
}
