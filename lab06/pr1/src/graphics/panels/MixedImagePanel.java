package graphics.panels;

import graphics.buttons.PuzzleButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MixedImagePanel extends JPanel {
    private BufferedImage image;

    private static final int rowCount = 4, columnCount = 4;
    private int widthPuzzle, heightPuzzle;
    private int x0, y0;
    private PuzzleButton[][] puzzles;

    public MixedImagePanel(int x0, int y0) {
        super(null);
        this.x0 = x0;
        this.y0 = y0;
    }

    public void setImage(BufferedImage image) {
        removeAll();
        repaint();
        this.image = image;
        widthPuzzle = image.getWidth() / columnCount;
        heightPuzzle = image.getHeight() / rowCount;
        puzzles = new PuzzleButton[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                puzzles[i][j] = new PuzzleButton(x0 + j * widthPuzzle, y0 + i * heightPuzzle,
                        new ImageIcon(image.getSubimage(j * widthPuzzle, i * heightPuzzle, widthPuzzle, heightPuzzle)));
                int finalI = i;
                int finalJ = j;
                puzzles[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        int x = e.getXOnScreen(), y = e.getYOnScreen();
                        if(x >= x0 && x <= x0 + image.getWidth()
                                && y >= y0 && y <= y0 + image.getHeight()){
                            puzzles[finalI][finalJ].setLocation(x0 + (x - x0) / widthPuzzle * widthPuzzle, y0 + (y - y0) / heightPuzzle * heightPuzzle);
                            if(check()){
                                JOptionPane.showMessageDialog(null, "Собрали!", "Победа!", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                });
            }
        }
    }

    public void mix(){
        if(image == null){
            return;
        }
        removeAll();
        for(PuzzleButton[] row : puzzles){
            for(PuzzleButton pb : row){
                pb.setLocation((int) (Math.random() * (getWidth() - widthPuzzle)),
                        (int) (Math.random() * (getHeight() - heightPuzzle - image.getHeight())) + image.getHeight());
                add(pb);
            }
        }
        repaint();
    }

    private boolean check(){
        for(PuzzleButton[] row : puzzles){
            for(PuzzleButton pb : row){
                if(pb.getX() != pb.getShouldX() || pb.getY() != pb.getShouldY()){
                    return false;
                }
            }
        }
        return true;
    }
}
