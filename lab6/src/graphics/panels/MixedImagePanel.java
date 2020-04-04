package graphics.panels;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MixedImagePanel extends JPanel {
    private BufferedImage image;

    private int rowCount = 4, columnCount = 4;
    int widthPuzzle, heightPuzzle;
    int x0, y0;
    private MyButton[][] puzzles;

    public MixedImagePanel(int x0, int y0) {
        super(null);
        this.x0 = x0;
        this.y0 = y0;
    }

    public void setImage(BufferedImage image) {
        widthPuzzle = image.getWidth() / columnCount;
        heightPuzzle = image.getHeight() / rowCount;
        this.image = image;
        puzzles = new MyButton[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                puzzles[i][j] = new MyButton(x0 + j * widthPuzzle, y0 + i * heightPuzzle,
                        new ImageIcon(image.getSubimage(j * widthPuzzle, i * heightPuzzle, widthPuzzle, heightPuzzle)));
                add(puzzles[i][j]);
            }
        }
    }

    public void mix(){
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                puzzles[i][j].setLocation((int) (Math.random() * (getWidth() - widthPuzzle)), y0 + image.getHeight());
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
                                JOptionPane.showMessageDialog(null, "Собрали!", "Поздравляю", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                });
            }
        }
        repaint();
    }

    private boolean check(){
        for(MyButton[] r : puzzles){
            for(MyButton b : r){
                if(b.getX() != b.getShouldX() || b.getY() != b.getShouldY()){
                    return false;
                }
            }
        }
        return true;
    }
}
