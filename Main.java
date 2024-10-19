import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Screen();
        File file = new File("bgm.wav");
        if (file.exists()) {
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start(); 
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        else {
            System.out.println("Audio file not found: " + file.getAbsolutePath());
        }
    }
}
