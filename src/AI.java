import java.awt.*;

public class AI extends GameObject {

    Enemy[] enemies;
    private final double SPEED = .1;
    private final double GRAVITY = .5;
    private boolean shoot;

    public AI(Enemy[] e) {
        super();
        enemies = e;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    @Override
    public void update() {
        Enemy e = targetEnemy();

        if (x < e.getX())
            xVel = 5;
        else if (x > e.getX())
            xVel = -5;
        else
            xVel = 0;
        x += xVel;

        if (x < e.getX() + 5 && x > e.getX() - 5) {
            shoot = true;
        } else {
            shoot = false;
        }
    }

    public boolean getShoot() {
        return shoot;
    }

    public Enemy targetEnemy(){
        Enemy max = enemies[0];
        for(int i = 0; i < enemies.length; i++){
            if(max.getY() < y + 20 && max.getY() < enemies[i].getY())
                max = enemies[i];
        }
        return max;
    }
}
