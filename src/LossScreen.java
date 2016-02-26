import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cody Thomas Zeitler on 2/25/2016.
 */
public class LossScreen extends JPanel implements ActionListener {

    private  Timer timer;
    public  Timer getTimer(){

        timer = new Timer(1000/60, new VictoryScreen());
        return timer;

    }

    public void actionPerformed(ActionEvent e){

        GameControl.getMainGameFrame().repaint();

    }


    public void paintComponent(Graphics g){

        g.setColor(Color.BLACK);
        g.fillRect(0,0,2000,2000);

        g.setColor(Color.WHITE);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("FAILURE!", 150, 750);



    }




}
