import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Charge");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Runner r = new Runner();
        window.add(r);
        window.pack();
        window.setVisible(true);

        r.startGameThread();
    }
}
