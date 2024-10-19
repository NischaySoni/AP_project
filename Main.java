import java.io.File;
import  java.io.IOException;
import javax.sound.sampled.*;
public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        new Screen();
        File file = new File("bgm.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }
}
