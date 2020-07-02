import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Banana extends Fruit {
    int radiusX = 70, radiusY = 30;
    public Banana() {
        super("Banana");
        super.setScore(5);
        super.setColor(Color.YELLOW);
        setShape();
    }
    public void setShape() {
        super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radiusX, radiusY),0);
    }
    public void update() {
        if(!super.getCut()) {
            super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radiusX, radiusY), 0);
        } else if (super.getCut() && super.getShape(1) == null) {
            super.cut(radiusX, radiusY);
        } else if(super.getCut() && super.getShape(1) != null) {
            super.updateCut(radiusX, radiusY);
        }
        super.update();
    }
    public int getRadius() {
        return radiusX;
    }
}
