import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameImage {
    // background images
    public Image bgImage;
    public Image bgTitleScreen;
    public Image bgChangeLevelScreen;
    public Image gameWinBg;
    public Image gameFailBg;

    // components (overlay, cursor)
    public Image overlayImage;
    public Image endScreenOverlay;
    public Image titleScreenOverlay;
    public Image cursor;
    public BufferedImage gamewin;
    public BufferedImage gamefail;

    // buttons for end screen
    public Image retryButtonRegular;
    public Image retryButtonLight;
    public Image returnButtonRegular;
    public Image returnButtonLight;


    // tutorial images
    private String[] tutorialImageFilePaths = {
            "src/main/java/picture/GUI_image/tutorial/tutorial_sample.png",
            "src/main/java/picture/GUI_image/tutorial/tutorial1_sample.png",
            "src/main/java/picture/GUI_image/tutorial/tutorial2_sample.png",
            "src/main/java/picture/GUI_image/tutorial/tutorial3_sample.png"
    };
    private Image tutorialImages[] = new Image[tutorialImageFilePaths.length];

    // narration images
    private String[] narrationImageFilePaths = {
            "src/main/java/picture/GUI_image/background_story/background_story1.png",
            "src/main/java/picture/GUI_image/background_story/background_story2.png",
            "src/main/java/picture/GUI_image/background_story/background_story3.png",
            "src/main/java/picture/GUI_image/background_story/background_story4.png",
            "src/main/java/picture/GUI_image/background_story/background_story5.png",
            "src/main/java/picture/GUI_image/background_story/background_story6.png",
            "src/main/java/picture/GUI_image/background_story/background_story7.png",
            "src/main/java/picture/GUI_image/background_story/background_story8.png",
            "src/main/java/picture/GUI_image/background_story/background_story9.png",
            "src/main/java/picture/GUI_image/background_story/background_story10.png",
            "src/main/java/picture/GUI_image/background_story/background_story11.png"
    };
    private Image[] narrationImages = new Image[narrationImageFilePaths.length];


    public GameImage() throws IOException {
        setBackgroundImageFiles();
        setButtonImageFiles();
        setComponentImageFiles();
        setTutorialImageFiles();
        setNarrationImageFiles();
    }

    public void setBackgroundImageFiles() {
        bgImage = new ImageIcon("src/main/java/picture/GUI_image/titleScreenBg.jpg").getImage();
        bgTitleScreen = new ImageIcon("src/main/java/picture/GUI_image/title_screen_bg.png").getImage();
        bgChangeLevelScreen = new ImageIcon("src/main/java/picture/GUI_image/change_level_screen_bg.png").getImage();

        gameWinBg = new ImageIcon("src/main/java/picture/GUI_image/escape_success_bg.jpg").getImage();
        gameFailBg = new ImageIcon("src/main/java/picture/GUI_image/escape_fail_bg.jpg").getImage();
    }

    public void setButtonImageFiles() {
        retryButtonRegular = new ImageIcon("src/main/java/picture/GUI_image/RetryButton_noLight.png").getImage();
        retryButtonLight = new ImageIcon("src/main/java/picture/GUI_image/RetryButton_light.png").getImage();
        returnButtonRegular = new ImageIcon("src/main/java/picture/GUI_image/ReturnButton_noLight.png").getImage();
        returnButtonLight = new ImageIcon("src/main/java/picture/GUI_image/ReturnButton_light.png").getImage();
    }

    public void setComponentImageFiles() throws IOException {
        cursor = new ImageIcon("src/main/java/picture/GUI_image/redHand_icon.png").getImage();
        overlayImage = new ImageIcon("src/main/java/picture/GUI_image/overlay_instructions.png").getImage();
        titleScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/title_screen_overlay.png").getImage();
        endScreenOverlay = new ImageIcon("src/main/java/picture/GUI_image/end_screen_overlay.png").getImage();
        gamewin = ImageIO.read(new File("src/main/java/picture/GUI_image/GameWin_interface_noButtons.png"));
        gamefail = ImageIO.read(new File("src/main/java/picture/GUI_image/GameOver_interface_noButtons.png"));
    }

    public void setTutorialImageFiles() {
        for(int i = 0; i < tutorialImageFilePaths.length; i++) {
            tutorialImages[i] = new ImageIcon(tutorialImageFilePaths[i]).getImage();
        }
    }
    public void setNarrationImageFiles() {
        for(int i = 0; i < narrationImageFilePaths.length; i++) {
            narrationImages[i] = new ImageIcon(narrationImageFilePaths[i]).getImage();
        }
    }
    public Image getTutorialImage(int i) {
        return tutorialImages[i];
    }
    public Image getNarrationImage(int i) {
        return narrationImages[i];
    }
}
