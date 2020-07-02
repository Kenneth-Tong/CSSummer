import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bomb extends ObjectMoving {
    private int radius = 70;
    public Bomb() {
        super("Bomb");
        super.setColor(Color.BLACK);
        setShape();
    }
    public void setShape() {
        super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radius, radius), 0);
    }
    public void cut() {
        super.cut();
    }
    public void update() {
        if(!super.getCut()) {
            super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radius, radius), 0);
        } else {
            super.setShape(new Ellipse2D.Double(-100, -100, radius, radius), 0);
            super.setUpdate();
            super.setShape(new Ellipse2D.Double(-100, -100, radius, radius), 1);
        }
        super.update();
    }
    public int getRadius() {
        return radius;
    }
}
