package graphics.frames;

import graphics.panels.TwoImagesPanel;
import myUtil.FullScreenException;
import myUtil.FullScreenMacOS;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private TwoImagesPanel twoImagesPanel;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        twoImagesPanel = new TwoImagesPanel();
        add(twoImagesPanel);

        setVisible(true);
        if(FullScreenMacOS.fullScreenAvailable()){
            try {
                FullScreenMacOS.setFullScreenEnabled(this, true);
                FullScreenMacOS.toggleFullScreen(this);
            } catch (FullScreenException ignored) {}
        }
    }
}
