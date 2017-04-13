import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMixer {
    private JPanel colorMixerPanel;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JFrame colorFrame;

    private JPainter painter;
    private JPanel displayPanel;

    public ColorMixer(JPainter painter, JPanel displayPanel) {

        this.painter = painter;
        this.displayPanel = displayPanel;

        redSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                painter.changeRedColor(redSlider.getValue());
                displayPanel.updateUI();
            }
        });
        greenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                painter.changeGreenColor(greenSlider.getValue());
                displayPanel.updateUI();
            }
        });
        blueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                painter.changeBlueColor(blueSlider.getValue());
                displayPanel.updateUI();
            }
        });
    }

    public void createAndShowFrame() {
        colorFrame = new JFrame("Color Mixer");
      //  colorFrame.setLocationRelativeTo();
        colorFrame.setContentPane(new ColorMixer(painter,displayPanel).colorMixerPanel);
        colorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorFrame.pack();
        colorFrame.setVisible(true);
    }
}
