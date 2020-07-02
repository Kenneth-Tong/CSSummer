import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Orange extends Fruit {
    private int radius = 50;
    public Orange() {
        super("Orange");
        super.setScore(10);
        super.setColor(Color.ORANGE);
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
