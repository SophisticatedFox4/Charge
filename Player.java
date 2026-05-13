import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
    private Settings s = new Settings();

    private Rectangle bound;
    public int vX, vY;
    private boolean onGround = false;
    public State state = State.POSITIVE;

    private boolean left, right, up;
    public boolean stuck;

    public Player(int x, int y) {
        vX = 0;
        vY = 0;
        bound = new Rectangle(x, y, s.pWidth, s.pHeight);
    }

    public void draw(Graphics2D g) {
        if (state.getCharge().equals("positive"))
            g.setColor(new Color(220, 50, 50));
        else
            g.setColor(new Color(50, 50, 220));
        g.fillRect((int) bound.x, (int) bound.y, s.pWidth, s.pHeight);
    }

    public void keyPress(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_LEFT)
            left = true;
        if (key.getKeyCode() == KeyEvent.VK_RIGHT)
            right = true;
        if (key.getKeyCode() == KeyEvent.VK_UP)
            if (!stuck)
                up = true;
        if (key.getKeyCode() == KeyEvent.VK_SPACE) {
            state = state.swap();
            if (stuck)
                stuck = false;
        }
        if (key.getKeyCode() == KeyEvent.VK_R) {
            bound.setLocation(100, 490);
            vX = 0;
            vY = 0;
            onGround = false;
            state = State.POSITIVE;
        }
    }

    public void keyRelease(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_LEFT)
            left = false;
        if (key.getKeyCode() == KeyEvent.VK_RIGHT)
            right = false;
        if (key.getKeyCode() == KeyEvent.VK_UP)
            up = false;
    }

    public void update() {
        if (left)
            vX = -s.speed;
        else if (right)
            vX = s.speed;
        else
            vX = 0;

        if (up && onGround) {
            vY = s.jump;
            onGround = false;
        }
        vY -= s.gravity;

        bound.translate(vX, -vY);

        if (bound.x < 0) {
            bound.setLocation(0, (int) bound.y);
            vX = 0;
        }
        if (bound.x > s.width - s.pWidth) {
            bound.setLocation(s.width - s.pWidth, (int) bound.y);
            vX = 0;
        }
        if (bound.y < 0) {
            bound.setLocation((int) bound.x, 0);
            vY = 0;
        }
        if (bound.y > s.height - s.pHeight) {
            bound.setLocation((int) bound.x, s.width - s.pWidth);
            vY = 0;
        }
    }

    public void checkCollision(Wall w) {
        if (!bound.intersects(w.getBound()))
            return;
        Rectangle error = bound.intersection(w.getBound());
        if (error.width < error.height) {
            bound.translate(vX >= 0 ? -error.width : error.width, 0);
        } else {
            if (vY < 0) {
                bound.translate(0, -error.height);
                onGround = true;
            } else {
                bound.translate(0, error.height);
            }
            vY = 0;
        }
    }

    public void physics(ArrayList<Wall> walls) {
        for (Wall w : walls) {
            Rectangle r = w.getBound();
            if (w.getType().equals("neutral"))
                continue;

            
        }
    }
}
