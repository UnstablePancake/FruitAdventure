import java.awt.*;

public class Player extends GameObject {

    private boolean leftAccel;
    private boolean rightAccel;
    private final double GRAVITY = .94;

    public Player() {
        super();
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public void update() {
        if (leftAccel)
            xVel -= .2;
        else if (rightAccel)
            xVel += .2;
        else if (!leftAccel && !rightAccel)
            xVel *= GRAVITY;

        x += xVel;

        if(x <= 0){
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
}

