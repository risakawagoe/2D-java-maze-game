import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * class to implement and manage Background music and sound effects
 */
public class Music {
    Clip[] clip = new Clip[5];
    private String[] musicFilePaths = {
            "src/main/java/music/Click_music_1.wav",// 0
            "src/main/java/music/Click_music_2.wav",// 1
            "src/main/java/music/Click_music_3.wav",// 2
            "src/main/java/music/bgm_mysteriousSound.wav",// 3
            "src/main/java/music/bgm_darkCaveSound.wav"// 4
    };


    /**
     * set file to get music from
     * @param i
     * @return
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public boolean setFile(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("[setFile] setting music file");
        if(i >= 0 && i < musicFilePaths.length) {
            File file = new File(musicFilePaths[i]);
            AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
            clip[i] = AudioSystem.getClip();
            clip[i].open(bgm);
        }else {
            return false;
        }
//        System.out.println("SET_FILE");
//        System.out.println("isOpen(): " + clip[i].isOpen());
//        System.out.println("isRunning(): " + clip[i].isRunning());
//        System.out.println("isActive(): " + clip[i].isActive());
        return true;
    }

    /**
     * play music
     */
    public boolean play(int i) {
        if(i >= 0 && i < musicFilePaths.length) {
            if(clip == null || clip[i] == null) {
//            System.out.println("[play] Clip is null.");
                return false;
            }else {
                clip[i].start();
            }
        }else {
            return false;
        }
//        System.out.println("PLAY");
//        System.out.println("isOpen(): " + clip[i].isOpen());
//        System.out.println("isRunning(): " + clip[i].isRunning());
//        System.out.println("isActive(): " + clip[i].isActive());
        return true;
    }

    /**
     * Loop music
     */
    public boolean loop(int i) {
        if(i >= 0 && i < musicFilePaths.length) {
            if(clip == null || clip[i] == null) {
                return false;
            }else {
                clip[i].loop(Clip.LOOP_CONTINUOUSLY);
            }
        }else {
            return false;
        }
//        System.out.println("LOOP");
//        System.out.println("isOpen(): " + clip[i].isOpen());
//        System.out.println("isRunning(): " + clip[i].isRunning());
//        System.out.println("isActive(): " + clip[i].isActive());
        return true;
    }

    /**
     * Stop music
     */
    public boolean stop(int i) {
        if(i >= 0 && i < musicFilePaths.length) {
            if(clip == null || clip[i] == null) {
                return false;
            }else {
                clip[i].stop();
            }
        }else {
            return false;
        }
//        System.out.println("STOP");
//        System.out.println("isOpen(): " + clip[i].isOpen());
//        System.out.println("isRunning(): " + clip[i].isRunning());
//        System.out.println("isActive(): " + clip[i].isActive());
        return true;
    }
}
