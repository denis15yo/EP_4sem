package graphics.dialogs;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class PosDialog extends JDialog {
    private double posX, posY, posZ;
    private boolean isDone = false;

    private JLabel xLabel, yLabel, zLabel;
    private JTextField xTextField, yTextField, zTextField;
    private JButton okButton;

    public PosDialog() {
        super((Dialog) null, "Позиция", true);
        setResizable(false);

        xLabel = new JLabel("x:");
        yLabel = new JLabel("y:");
        zLabel = new JLabel("z:");
        xTextField = new JTextField(5);
        yTextField = new JTextField(5);
        zTextField = new JTextField(5);
        okButton = new JButton("Окей");

        JPanel xPanel = new JPanel();
        xPanel.add(xLabel);
        xPanel.add(xTextField);

        JPanel yPanel = new JPanel();
        yPanel.add(yLabel);
        yPanel.add(yTextField);

        JPanel zPanel = new JPanel();
        zPanel.add(zLabel);
        zPanel.add(zTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        okButton.addActionListener(e -> {
            try{
                posX = Double.parseDouble(xTextField.getText());
                posY = Double.parseDouble(yTextField.getText());
                posZ = Double.parseDouble(zTextField.getText());
                isDone = true;
                this.dispose();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Некорректные данные",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        Box box = Box.createVerticalBox();
        box.add(xPanel);
        box.add(yPanel);
        box.add(zPanel);
        box.add(buttonPanel);

        add(box);
        pack();
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getPosZ() {
        return posZ;
    }

    public boolean isDone() {
        return isDone;
    }
}
