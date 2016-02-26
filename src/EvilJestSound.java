import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 2/25/2016.
 */
public class EvilJestSound {

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

            clip.start();

        }


        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void stopSound(){
        clip.stop();
    }

    public EvilJestSound(){

        soundFile = new File("src/eviljest.wav");


    }

}
