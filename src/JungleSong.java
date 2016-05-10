/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class JungleSong {

    //private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    //private SourceDataLine sourceLine;
    private DataLine.Info info;
    private Clip clip;

    public void playSound(){
        try {

            audioStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, audioFormat);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopSound(){
        clip.stop();
    }

    public JungleSong(){

        soundFile = new File("src/sounds/Jungle.wav");


    }
}
