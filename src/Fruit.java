import java.awt.geom.Arc2D;

public abstract class Fruit extends ObjectMoving implements Scoreable {
    private int score;
    public Fruit(String name) {
        super(name);
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public void cut(int radiusX, int radiusY) {
        super.cut();
        super.setUpdate();
        updateCut(radiusX, radiusY);
    }
    public void updateCut(int radiusX, int radiusY) {
        for(int i = 0; i < 2; i++) {
            Arc2D.Double arc;
            if(i == 0)
                arc = new Arc2D.Double(super.getPosX(i), super.getPosY(i), radiusX, radiusY, 90, 180, Arc2D.CHORD);
            else
                arc = new Arc2D.Double(super.getPosX(i), super.getPosY(i), radiusX, radiusY, 90, -180, Arc2D.CHORD);
            super.setShape(arc, i);
        }
    }
}
