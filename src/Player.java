import java.awt.*;

public class Player extends GameObject {

    private boolean leftAccel;
    private boolean rightAccel;
    private final double SPEED = .1;
    private final double GRAVITY = .85;

    public Player() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    @Override
    public void update() {
        if (leftAccel)
            xVel -= SPEED;
        else if (rightAccel)
            xVel += SPEED;
        else if (!leftAccel && !rightAccel)
            xVel *= GRAVITY;

        x += xVel;

        if(x <= 0) {
            x = 0;
            xVel = 0;
        }

        if(x >= Window.FRAME_WIDTH - getWidth()) {
            x = Window.FRAME_WIDTH - getWidth();
            xVel = 0;
        }
    }

    public void setLeftAccel(boolean leftAccel) {
        this.leftAccel = leftAccel;
    }

    public void setRightAccel(boolean rightAccel) {
        this.rightAccel = rightAccel;
    }

    public boolean getLeftAccel() {
        return leftAccel;
    }

    public boolean getRightAccel() {
        return rightAccel;
    }
}

