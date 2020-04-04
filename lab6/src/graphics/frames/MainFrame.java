package graphics.frames;

import graphics.panels.TwoImagesPanel;
import myUtil.FullScreenException;
import myUtil.FullScreenMacOS;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private TwoImagesPanel twoImagesPanel;

    public MainFrame() {
        twoImagesPanel = new TwoImagesPanel();

        add(twoImagesPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        disableWarning();
        try {
            FullScreenMacOS.setFullScreenEnabled(this, true);
            FullScreenMacOS.toggleFullScreen(this);
        } catch (FullScreenException ignored) {}
//        pack();
    }

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
