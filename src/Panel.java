import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private Player p;
    private Enemy[] enemies = new Enemy[10];
    private Bullet[] bullets = new Bullet[10];
    private boolean fireStatus;
    public static int health = 99;
    public static int score = 0;

    private int cooldown;

    public void init() {
        setFocusable(true);
        this.addKeyListener(this);

        //create player
        p = new Player();

        //create enemies
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy();
        }

        // create bullets
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.FRAME_WIDTH, Window.FRAME_HEIGHT);

        // draw enemies
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // draw bullets
        for (Bullet b : bullets) {
            if (b.isActive())
                b.draw(g);
        }

        // draw player
        p.draw(g);

        // draw health
        g.drawString(String.valueOf(health), 10, 20);

        // draw score
        g.drawString(String.valueOf(score), 10, 40);
    }

    @Override
    public void run() {
        while (true) {
            // player
            p.update();

            // enemies
            for (Enemy e : enemies) {
                e.update();
            }

            // bullets
            for (Bullet b : bullets) {
                b.update();
                if (b.getY() <= 0)
                    b.setActive(false);

                if (!b.isActive() && cooldown == 0 && fireStatus) {
                    shoot(b);
                    resetCooldown();
                    break;
                }
            }

            for (Enemy e : enemies) {
                for (Bullet b : bullets) {
                    if (collision(b, e)) {
                        score++;
                        b.setActive(false);
                        e.reset();
                    }
                }
            }

            // lower cooldown
            if (cooldown > 0)
                cooldown--;

            repaint();

            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            p.setLeftAccel(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p.setRightAccel(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            fireStatus = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            p.setLeftAccel(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p.setRightAccel(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            fireStatus = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void shoot(Bullet b) {
        b.setActive(true);
        b.setyVel(5);
        b.setX(p.getX() + (p.getWidth() / 2));
        b.setY(p.getY());
    }

    private void resetCooldown() {
        cooldown = 25;
    }

    public boolean collision(Bullet b, Enemy e) {
        return b.getBounds().intersects(e.getBounds());
    }
}
