import java.awt.*;

public class Enemy extends GameObject implements Collidable {

    private final double MIN_FALL_SPEED = 1;
    private final double MAX_FALL_SPEED = 3;

    public Enemy() {
        super(20, 20, (int)(Math.random() * Window.FRAME_WIDTH), -(int)(Math.random() * 1000));
        yVel = MIN_FALL_SPEED;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int)x, (int)y, getWidth(), getHeight());
    }

    @Override
    public void update() {
        if (y >= Window.FRAME_HEIGHT) {
            Panel.health--;
            reset();
        }

        y += yVel;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, getWidth(), getHeight());
    }

    // temporary values
    public void reset() {
        setX((int)(Math.random() * Window.FRAME_WIDTH - getWidth()));
        setY(-(int)(Math.random() * 250));
        setyVel(MIN_FALL_SPEED);
    }
}
