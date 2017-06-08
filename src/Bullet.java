import java.awt.*;

public class Bullet  extends GameObject implements Collidable {

    private boolean active = false;

    public Bullet() {
        super(2, 5, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x, (int)y, getWidth(), getHeight());
    }

    @Override
    public void update() {
        if(active)
            y -= yVel;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, getWidth(), getHeight());
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
