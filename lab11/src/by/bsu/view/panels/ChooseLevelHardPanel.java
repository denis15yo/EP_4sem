package by.bsu.view.panels;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChooseLevelHardPanel extends JPanel {
    private final JSpinner spinner;

    public ChooseLevelHardPanel(int levelsCount) {
        super(new BorderLayout());

        Integer[] levels = new Integer[levelsCount];
        for(int i = 0; i < levelsCount; ++i){
            levels[i] = i + 1;
        }

        SpinnerModel spinnerModel = new SpinnerListModel(levels);
        spinner = new JSpinner(spinnerModel);
        spinner.setValue(1);

        add(spinner, BorderLayout.SOUTH);
    }

    public int getChosenLevel(){
        return (int) spinner.getModel().getValue();
    }
}
