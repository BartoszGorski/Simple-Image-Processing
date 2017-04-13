import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppWindow {
    private JPanel mainPanel;

    private JButton loadButton;
    private JPanel displayPanel;
    private JButton colorMixerButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton originSizeButton;
    private JLabel zoomLabel;

    private JPainter painter;

    public AppWindow() {

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    painter = new JPainter(fileChooser.getSelectedFile());
                    displayPanel.add(painter, BorderLayout.CENTER);
                    displayPanel.updateUI();
                    zoomLabel.setText(painter.getZoomPercentageImageSize()+"%");

                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });

        colorMixerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorMixer colorMixer = new ColorMixer(painter, displayPanel);
                colorMixer.createAndShowFrame();
            }
        });

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.zoomIn(zoomLabel);
                displayPanel.updateUI();
            }
        });
        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.zoomOut(zoomLabel);
                displayPanel.updateUI();
            }
        });
        originSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painter.originalSize(zoomLabel);
                displayPanel.updateUI();
            }
        });
    }

    public void createAndShowFrame() {
        JFrame appFrame = new JFrame("Image Processing");
        appFrame.setSize(500, 530);
        appFrame.setLocationRelativeTo(null);
        appFrame.setContentPane(new AppWindow().mainPanel);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //appFrame.pack();
        appFrame.setVisible(true);
    }

    private void createUIComponents() {

        displayPanel = new JPanel();
        displayPanel.setBackground(Color.black);
    }
}


