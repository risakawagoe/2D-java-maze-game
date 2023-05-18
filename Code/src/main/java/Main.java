import javax.swing.*;
import java.io.IOException;

/**
 * Main class to start game
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48

        JFrame frame = new JFrame("Survive in the end");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GameFrame gameFrame = new GameFrame(colm, rows, pacSize);
        frame.add(gameFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gameFrame.beginThread();
    }
}

