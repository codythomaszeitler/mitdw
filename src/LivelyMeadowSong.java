import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by Cody Thomas Zeitler on 2/25/2016.
 */
public class LivelyMeadowSong {
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

    public LivelyMeadowSong(){

        soundFile = new File("src/Lively Meadow (Victory Fanfare and Song).wav");


    }
}
