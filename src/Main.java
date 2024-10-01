import view.ChessGameFrame;
import view.FirstFrame;
import view.IntroFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FirstFrame start=new FirstFrame(1000, 760);
            start.setVisible(true);
        });
    }
}
