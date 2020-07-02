import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static java.awt.Font.BOLD;

public class FruitNinja extends JPanel implements ActionListener, MouseListener {
    private ArrayList<ObjectMoving> list = new ArrayList<>();
    private ArrayList<Rectangle> trail = new ArrayList<>();
    private Timer timer;
    private Point mouseLocation;
    private int score, lives = 1, peaches = 0, apples = 0, watermelons = 0, bananas = 0, oranges = 0;
    private boolean game;

    public FruitNinja() {
        game = true;
        mouseLocation = new Point(-10, -10);
        for(int i = 0; i < 100; i++)
            trail.add(i, new Rectangle((int) mouseLocation.getX(), (int) mouseLocation.getY(), 10, 10));
        addMouseListener(this);
        setFocusable(true);
        this.setBackground(new Color(139,69,19));
        timer = new Timer(50, this);
        timer.start();
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("FruitNinja.wav"));
            Clip sound = AudioSystem.getClip();
            sound.open(inputStream);
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException d) {
            d.printStackTrace();
        }
    }
    public void createFruits() {
        if((int) (Math.random() * 10000) % 33 == 0) {
            switch((int) (Math.random() * 8)) {
                case 0:
                    list.add(new Apple());
                    break;
                case 1:
                    list.add(new Banana());
                    break;
                default:
                    list.add(new Bomb());
                    break;
                case 3:
                    list.add(new Orange());
                    break;
                case 4:
                    list.add(new Peach());
                    break;
                case 5:
                    list.add(new Watermelon());
                    break;
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        try {
            mouseLocation = new Point((int) (MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX()), (int) (MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY()));
        } catch (IllegalComponentStateException k) {
            mouseLocation = new Point(-10, -10);
        }
        createFruits();
        checkMouseLocation();
        checkFruits();
    }
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        if(lives > 0) {
            for(int i = 0; i < lives; i++) {
                g.setColor(Color.RED);
                g.setFont(new Font("Comic Sans", BOLD, 42));
                g.drawString("\u2665", i * 50 + 30, Frame.Height - 50);
            }
            g.setFont(new Font("Comic Sans", BOLD, 42));
            g.setColor(Color.WHITE);
            g.drawString("Current Score: " + score, 115, 300);
            for(int b = 0; b < list.size(); b++) {
                ObjectMoving n = list.get(b);
                g.setColor(n.getColor());
                for (int i = 0; i < 2; i++)
                    if (n.getShape(i) != null) //depending of it is split, it should either draw or not
                        g.fill(n.getShape(i));
                n.update();
            }
            g.setColor(Color.BLACK);
            for(Rectangle n: trail)
                g.fill(n);
            moveTrail();
        } else {
            if(game)
                endgame();
        }
        repaint();
    }
    public void endgame() {
        game = false;
        JOptionPane.showMessageDialog(null,"You lost!\nPoints Scored: " + score + "\n[" + peaches + " peaches," + apples + " apples," + bananas + " bananas," + oranges + " oranges," + watermelons + " watermelons]", "Fruit Ninja", JOptionPane.PLAIN_MESSAGE);
        int answer = JOptionPane.showConfirmDialog(null, "Try again?", "Fruit Ninja", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION)
            reset();
        else
            System.exit(0);
    }
    public void reset() {
        score = 0;
        peaches = 0;
        watermelons = 0;
        apples = 0;
        oranges = 0;
        bananas = 0;
        lives = 3;
        game = true;
    }
    public void checkFruits() { //out of bounds
        for(int b = 0; b < list.size(); b++) {
            ObjectMoving n = list.get(b);
            boolean offBoardOne = false, offBoardTwo = false;
            for (int i = 0; i < 2; i++)
                if (n.getPosX(i) != -1 && n.getPosY(i) != -1)
                    if (n.getPosX(i) < 0 - n.getRadius() || n.getPosX(i) > Frame.Width || n.getPosY(i) > Frame.Height) {
                        if (i == 0)
                            offBoardOne = true;
                        if (i == 1)
                            offBoardTwo = true;
                    }
            if(offBoardOne && offBoardTwo) {
                list.remove(n);
            }
        }
    }
    public void moveTrail() {
        for(int i = trail.size() - 1; i > 0; i--)
            trail.set(i, trail.get(i - 1));
        trail.set(0, new Rectangle((int) mouseLocation.getX() - 5, (int) mouseLocation.getY() - 5, 10, 10));
    }
    public void addPointsToList(Fruit n) {
        if(n instanceof Watermelon)
            watermelons++;
        else if(n instanceof Peach)
            peaches++;
        else if(n instanceof Orange)
            oranges++;
        else if(n instanceof Banana)
            bananas++;
        else if(n instanceof Apple)
            apples++;
    }
    public void checkMouseLocation() {
        for(ObjectMoving n: list) {
            if(!n.getCut())
                if(collides(n.getShape(0))) {
                    n.cut();
                    if(n instanceof Fruit) {
                        if (n instanceof Peach)
                            score *= 2;
                        else
                            score += ((Fruit) n).getScore();
                        addPointsToList((Fruit) n);
                    } else
                        lives--;
                }
        }
        repaint();
    }
    public boolean collides(Shape n) {
        return n.contains(mouseLocation);
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}
