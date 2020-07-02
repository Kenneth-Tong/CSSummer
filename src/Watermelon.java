import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Watermelon extends Fruit {
    private int radiusX = 75, radiusY = 50;
    public Watermelon() {
        super("Watermelon");
        super.setScore(10);
        super.setColor(Color.GREEN);
        setShape();
    }
    public void setShape() {
        super.setShape(new Ellipse2D.Double(super.getPosX(0), super.getPosY(0), radiusX, radiusY), 0);
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
