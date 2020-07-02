import javax.swing.*;

public class Frame extends JFrame{
    public static final int Height = 600, Width = 600;

    public Frame() {
        super("Fruit Ninja");
        JPanel game = new FruitNinja();
        this.add(game);
    }
    public static void main(String[] args) {
        Frame window = new Frame();
        window.setBounds(0, 0, Height, Width);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
