import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public abstract class ObjectMoving implements Cuttable, Moveable {
    private Color color;
    private String name;
    private boolean cut;
    private int timesCut = 0;
    private double velX, velY;
    private Clip sound;
    private double angle;
    private Shape[] listShape = {null, null};
    private Point2D.Double[] listPoint = {null, null};
    public ObjectMoving(String name) {
        cut = false;
        this.name = name;
        setSound();
        int random = (int) (Math.random() * 3);
        if(random == 0)
            velX = (Math.random() * 0.2);
        if(random == 1)
            velX = 0 - velX;
        if(random == 2)
            velX = 0;
        velY = 0 - (Math.random() * (0.5 - 0.4) + 0.4);
        angle = (Math.random() * 90) + 30;
        listPoint[0] = new Point2D.Double((Math.random() * (Frame.Width - 200)) + 100, Frame.Height - 1);
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public boolean getCut() {
        return cut;
    }
    public void cut() {
        cut = true;
        sound.start();
    }
    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }
    public double getPosY(int i) {
        double point = 0;
        if(listPoint[i] != null)
            point = listPoint[i].getY();
        return point;
    }
    public double getPosX(int i) {
        double point = -1;
        if(listPoint[i] != null)
            point = listPoint[i].getX();
        return point;
    }
    public double getAngle() {
        return angle;
    }
    public void setSound() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(name + ".wav"));
            sound = AudioSystem.getClip();
            sound.open(inputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException d) {
            d.printStackTrace();
        }
    }
    public Shape getShape(int i) {
        return listShape[i];
    }
    public void setShape(Shape shape, int i) {
        listShape[i] = shape;
    }
    public void setUpdate() {
        listPoint[1] = new Point2D.Double(listPoint[0].getX(), listPoint[0].getY());
    }
    public void update() {
        velY += 0.0002;
        if(!cut) {
            listPoint[0].setLocation(listPoint[0].getX() + velX, listPoint[0].getY() + velY);
        } else {
            listPoint[0].setLocation(listPoint[0].getX() - 0.05, listPoint[0].getY() + velY);
            listPoint[1].setLocation(listPoint[1].getX() + 0.05, listPoint[1].getY() + velY);
        }
    }
    abstract void setShape();
    abstract int getRadius();
}
