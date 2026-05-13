import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Runner extends JPanel implements Runnable, KeyListener{
    public static final int FPS = 60;
    private Thread gameThread;
    private Player p;
    private Settings s = new Settings();
    private ArrayList<Wall> walls;

    public Runner() {
        this.setPreferredSize(new Dimension(s.width, s.height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        p = new Player(100, 490);

        walls = new ArrayList<Wall>();
        walls.add(new Wall(0,   550, 800, 20, "neutral"));
        walls.add(new Wall(200, 470, 150, 20, "neutral"));
        walls.add(new Wall(420, 390, 150, 20, "positive"));
        walls.add(new Wall(650, 310, 150, 20, "negative"));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        p.update();
        p.physics(walls);
        for (Wall w : walls) {
            p.checkCollision(w);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Wall w : walls) {
            w.draw(g2);
        }
        p.draw(g2);
    }

    public void keyPressed(KeyEvent e) {
        p.keyPress(e);
    }

    public void keyReleased(KeyEvent e) {
        p.keyRelease(e);
    }

    public void keyTyped(KeyEvent e) {}
}
