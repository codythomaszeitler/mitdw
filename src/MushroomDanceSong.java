import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
public class MushroomDanceSong {

    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
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

    public MushroomDanceSong(){

        soundFile = new File("src/sounds/mushroom_dance.wav");


    }


}
