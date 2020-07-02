import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Peach extends Fruit {
    private int radius = 30;
    public Peach() {
        super("Peach");
        super.setScore(2);
        super.setColor(Color.PINK);
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
