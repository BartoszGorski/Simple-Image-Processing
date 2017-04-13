import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class JPainter extends JComponent {

    private BufferedImage image = null;
    private Color[][] originColorArray;
    private double zoom;
    private double percentageZoom;
    DecimalFormat zoomTextFormat;

    public JPainter(File file) {
        super();
        setImage(file);
        originColorArray = getOriginColorArray();

        zoomTextFormat = new DecimalFormat("####0.00");
        zoom = 1.0;
        percentageZoom = 10.0;
    }

    public void setImage(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(zoom,zoom);
        g2d.drawImage(image, 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return (new Dimension(image.getWidth(), image.getHeight()));
        }
        return (new Dimension(0, 0));
    }

    private Color[][] getOriginColorArray(){

        int width = image.getWidth();
        int height = image.getHeight();

        Color[][] colorArray = new Color[width][height];

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                colorArray[w][h] = new Color(image.getRGB(w,h));
            }
        }

        return colorArray;
    }

    public void changeRedColor(int multipleChangeValue){

        int width = image.getWidth();
        int height = image.getHeight();

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {

                Color c = new Color(image.getRGB(w,h));

                float red = (float)originColorArray[w][h].getRed()/255;
                float newRed = red * ((float)multipleChangeValue / 255);

                float green = (float)c.getGreen()/255;
                float blue = (float)c.getBlue()/255;

                Color newColor = new Color(newRed,green,blue);
                image.setRGB(w,h,newColor.getRGB());
            }
        }
    }

    public void changeGreenColor(int multipleChangeValue){

        int width = image.getWidth();
        int height = image.getHeight();

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {

                Color c = new Color(image.getRGB(w,h));

                float red = (float)c.getRed()/255;
                float green = (float)originColorArray[w][h].getGreen()/255;
                float newGreen = green * ((float)multipleChangeValue / 255);
                float blue = (float)c.getBlue()/255;

                Color newColor = new Color(red,newGreen,blue);
                image.setRGB(w,h,newColor.getRGB());
            }
        }
    }

    public void changeBlueColor(int multipleChangeValue){

        int width = image.getWidth();
        int height = image.getHeight();

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {

                Color c = new Color(image.getRGB(w,h));

                float red = (float)c.getRed()/255;
                float green = (float)c.getGreen()/255;
                float blue = (float)originColorArray[w][h].getBlue()/255;
                float newBlue = blue * ((float)multipleChangeValue / 255);

                Color newColor = new Color(red,green,newBlue);
                image.setRGB(w,h,newColor.getRGB());
            }
        }
    }

    public void originalSize(JLabel zoomLabel) {
        zoom = 1;
        zoomLabel.setText(zoomTextFormat.format(getZoomPercentageImageSize())+"%");
    }

    public void zoomIn(JLabel zoomLabel) {
        zoom = zoom + (zoom * (percentageZoom/100));
        zoomLabel.setText(zoomTextFormat.format(getZoomPercentageImageSize())+"%");
    }

    public void zoomOut(JLabel zoomLabel) {
        zoom = zoom - (zoom * (percentageZoom/100));
        zoomLabel.setText(zoomTextFormat.format(getZoomPercentageImageSize())+"%");
    }

    public double getZoomPercentageImageSize(){
        return (zoom * 100);
    }
}