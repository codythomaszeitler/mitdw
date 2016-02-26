import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class VictoryScreen extends JPanel implements ActionListener {

    private  Timer timer;
    private LivelyMeadowSong livelyMeadowSong;
    public LivelyMeadowSong getLivelyMeadowSong(){
        return livelyMeadowSong;
    }

    public VictoryScreen(){
        livelyMeadowSong = new LivelyMeadowSong();

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                livelyMeadowSong.playSound();
            }

        });




    }

    public  Timer getTimer(){

        timer = new Timer(1000/60, new VictoryScreen());
        return timer;

    }

    public void actionPerformed(ActionEvent e){

        System.out.println("Timer in victory screen has been fired.");

        GameControl.getMainGameFrame().repaint();

    }


    public void paintComponent(Graphics g){

        g.setColor(Color.BLACK);
        g.fillRect(0,0,2000,2000);

        g.setColor(Color.WHITE);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
        g.drawString("VICTORY!", 150, 750);



    }




}
