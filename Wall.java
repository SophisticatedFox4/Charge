import java.awt.*;

public class Wall {
    private Rectangle bound;
    private State state;

    public Wall(int x, int y, int width, int height, String type) {
        bound = new Rectangle(x, y, width, height);
        state = State.valueOf(type.toUpperCase());
    }

    public void draw(Graphics2D g) {
        if (state.getCharge().equals("positive"))
            g.setColor(new Color(220, 50, 50));
        else if (state.getCharge().equals("neutral"))
            g.setColor(new Color(150, 150, 150));
        else
            g.setColor(new Color(50, 50, 220));

        g.fillRect((int) bound.getX(), (int) bound.getY(), (int) bound.getWidth(), (int) bound.getHeight());
    }

    public String getType() {
        return state.getCharge();
    }

    public Rectangle getBound() {
        return bound;
    }
}
