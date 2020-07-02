import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Apple extends Fruit {
    private int radius = 45;
    public Apple() {
        super("Apple");
        super.setScore(15);
        super.setColor(Color.RED);
        setShape();
    }
    public void setShape() {
        super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radius, radius), 0);
    }
    public void update() {
        if(!super.getCut()) {
            super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radius, radius), 0);
        } else if (super.getCut() && super.getShape(1) == null) {
            super.cut(radius, radius);
        } else if(super.getCut() && super.getShape(1) != null) {
            super.updateCut(radius, radius);
        }
        super.update();
    }
    public int getRadius() {
        return radius;
    }
}
